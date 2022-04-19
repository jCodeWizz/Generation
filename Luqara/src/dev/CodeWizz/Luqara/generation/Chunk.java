package dev.CodeWizz.Luqara.generation;

import dev.CodeWizz.Luqara.Generation;
import dev.CodeWizz.engine.util.WNoise;

public class Chunk {

	public Tile[][] tiles;
	public int index, x, y;
	public Biome biome;

	public double[] noise;
	public int[] height;

	public Chunk(int x, int y, int index, Biome biome) {
		tiles = new Tile[16][48];
		noise = new double[tiles.length];
		height = new int[tiles.length];
		this.biome = biome;

		this.x = x;
		this.y = y;
		this.index = index;
	}

	public void clear() {
		for (int i = 0; i < tiles.length; i++) {
			for (int j = 0; j < tiles[i].length; j++) {
				tiles[i][j].setType(TileType.Air);
			}
		}
	}

	public void fill(TileType type) {
		for (int i = 0; i < tiles.length; i++) {
			for (int j = 0; j < tiles[i].length; j++) {
				tiles[i][j].setType(type);
			}
		}
	}

	public void init() {
		for (int i = 0; i < tiles.length; i++) {
			for (int j = 0; j < tiles[i].length; j++) {
				tiles[i][j] = new Tile(x + i * 16, y + j * 16, x, y);
			}
		}
	}

	public void startGeneration(WNoise noise) {
		generate(noise);
	}

	private void generate(WNoise noise) {
		for (int i = 0; i < tiles.length; i++) {
			this.noise[i] = noise.noise(index * 16 + i);
			height[i] = (int) (this.noise[i] * Generation.amplifier + 20);

			if (height[i] >= 0 && height[i] < tiles[i].length) {
				tiles[i][height[i]].setType(biome.getTop());
			}
			for (int j = 1; j <= 5; j++) {
				if (height[i] + j < tiles[i].length && height[i] + j > 0) {
					tiles[i][height[i] + j].setType(biome.getSoil());
				}
			}
			for (int j = 0; j < tiles[i].length; j++) {
				if (tiles[i][j].getType() == TileType.Air && j >= height[i]) {
					tiles[i][j].setType(TileType.Stone);
				}
			}
			for (int j = 0; j < tiles[i].length; j++) {
				if (tiles[i][j].getType() == TileType.Air && j >= World.waterLevel) {
					tiles[i][j].setType(TileType.Water);
				}
			}
		}

	}

	private void generateTransition(Chunk pre, WNoise noise) {
		int lastY = pre.height[pre.height.length - 1];

		for (int i = 0; i < tiles.length; i++) {
			this.noise[i] = noise.noise(index * 16 + i);
			height[i] = (int) (this.noise[i] * Generation.amplifier + 20);
		}
		
		int riverpoint = World.riverLevel;
		if(height[7] > riverpoint) {
			riverpoint = height[7];
		}
		if(height[8] > riverpoint) {
			riverpoint = height[8];
		}
		int yToGo = height[height.length - 1];

		float coverage1 = Math.abs(lastY - riverpoint);
		float dy1 = coverage1 / 6f;

		float coverage2 = Math.abs(yToGo - riverpoint);
		float dy2 = coverage2 / 6f;

		for (int i = 0; i < 7; i++) {
			height[i] = (int) (lastY + i * dy1);
		}

		height[7] = riverpoint;
		height[8] = riverpoint;

		for (int i = 0; i < 7; i++) {
			height[i+9] = (int) (yToGo + (7-i) * dy2);
		}

		for (int i = 0; i < 8; i++) {
			if (height[i] >= 0 && height[i] < tiles[i].length) {
				tiles[i][height[i]].setType(pre.biome.getTop());
			}
			for (int j = 1; j <= 5; j++) {
				if (height[i] + j < tiles[i].length && height[i] + j > 0) {
					tiles[i][height[i] + j].setType(pre.biome.getSoil());
				}
			}
		}
		for (int i = 8; i < tiles.length; i++) {
			if (height[i] >= 0 && height[i] < tiles[i].length) {
				tiles[i][height[i]].setType(biome.getTop());
			}
			for (int j = 1; j <= 5; j++) {
				if (height[i] + j < tiles[i].length && height[i] + j > 0) {
					tiles[i][height[i] + j].setType(biome.getSoil());
				}
			}
		}
		for(int i = 0; i < tiles.length; i++) {
			for (int j = 0; j < tiles[i].length; j++) {
				if (tiles[i][j].getType() == TileType.Air && j >= height[i]) {
					tiles[i][j].setType(TileType.Stone);
				}
			}
			for (int j = 0; j < tiles[i].length; j++) {
				if (tiles[i][j].getType() == TileType.Air && j >= World.waterLevel) {
					tiles[i][j].setType(TileType.Water);
				}
			}
		}
	}

	public void startGeneration(Chunk pre, WNoise noise) {
		if (pre.biome != biome) {
			generateTransition(pre, noise);
		} else {
			generate(noise);
		}

	}
}
