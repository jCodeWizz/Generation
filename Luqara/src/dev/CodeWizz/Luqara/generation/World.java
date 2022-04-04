package dev.CodeWizz.Luqara.generation;

import dev.CodeWizz.Luqara.Generation;
import dev.CodeWizz.engine.GameContainer;
import dev.CodeWizz.engine.Renderer;
import dev.CodeWizz.engine.gfx.light.Light;
import dev.CodeWizz.engine.util.Textures;
import dev.CodeWizz.engine.util.WNoise;

public class World {

	public Chunk[] chunks;
	public WNoise noise;

	public static final int waterLevel = 25;
	public static final int riverLevel = 28;

	public World() {
		chunks = new Chunk[5];
		noise = new WNoise();

		chunks[0] = new Chunk(0, 0, 0, Biome.Plains);
		chunks[1] = new Chunk(1 * 16 * 16, 0, 1, Biome.Plains);
		chunks[2] = new Chunk(2 * 16 * 16, 0, 2, Biome.Desert);
		chunks[3] = new Chunk(3 * 16 * 16, 0, 3, Biome.Desert);
		chunks[4] = new Chunk(4 * 16 * 16, 0, 4, Biome.Desert);

		for (int i = 0; i < chunks.length; i++) {
			chunks[i].init();
		}
	}

	public void render(GameContainer gc, Renderer r) {
		if (!Generation.lines) {
			for (int i = 0; i < chunks.length; i++) {
				for (int j = 0; j < chunks[i].tiles.length; j++) {
					for (int k = 0; k < chunks[i].tiles[j].length; k++) {
						Tile t = chunks[i].tiles[j][k];

						if (t.getType() == TileType.Grass) {
							r.drawImage(Textures.get("grassBlock"), t.getX(), t.getY() + Generation.moveY);
						} else if (t.getType() == TileType.Stone) {
							r.drawImage(Textures.get("stone"), t.getX(), t.getY() + Generation.moveY);
						} else if (t.getType() == TileType.Dirt) {
							r.drawImage(Textures.get("dirt"), t.getX(), t.getY() + Generation.moveY);
						} else if (t.getType() == TileType.Water) {
							r.drawImage(Textures.get("water"), t.getX(), t.getY() + Generation.moveY);
						} else if (t.getType() == TileType.Sand) {
							r.drawImage(Textures.get("sand"), t.getX(), t.getY() + Generation.moveY);
						}
					}
				}

				if (Generation.debug) {
					r.drawRect(chunks[i].x, chunks[i].y + Generation.moveY, chunks[i].tiles.length * 16,
							chunks[i].tiles[0].length * 16, 0xffffff00, Light.NONE);
					r.drawRect(0, waterLevel * 16, 1280, 2, 0xff0000ff, Light.NONE);
					r.drawRect(0, riverLevel * 16, 1280, 2, 0xff00ffff, Light.NONE);

				}
			}
		} else {
			for(int i = 0; i < chunks.length; i++) {
				if(i < 4) {
					for(int j = 0; j < chunks[i].height.length; j++) {
						if(j < 15) {
							r.drawLine(0xffff0000, chunks[i].tiles[j][0].getX(), chunks[i].height[j]*16,  chunks[i].tiles[j+1][0].getX(), chunks[i].height[j+1]*16);
						} else {
							r.drawLine(0xffff0000, chunks[i].tiles[j][0].getX(), chunks[i].height[j]*16,  chunks[i+1].tiles[0][0].getX(), chunks[i+1].height[0]*16);
						}
					}
				} else {
					for(int j = 0; j < chunks[i].height.length; j++) {
						if(j < 15) {
							r.drawLine(0xffff0000, chunks[i].tiles[j][0].getX(), chunks[i].height[j]*16,  chunks[i].tiles[j+1][0].getX(), chunks[i].height[j+1]*16);
						} else {
							r.drawLine(0xffff0000, chunks[i].tiles[j][0].getX(), chunks[i].height[j]*16,  chunks[i].tiles[j][0].getX(), chunks[i].height[j]*16);
						}
					}
				}
			}
		}
	}

	public void clear() {
		for (int i = 0; i < chunks.length; i++) {
			chunks[i].clear();
		}
	}

	public void generate() {
		clear();
		for (int i = 0; i < chunks.length; i++) {
			if (i > 0) {
				chunks[i].startGeneration(chunks[i - 1], noise);
			} else {
				chunks[i].startGeneration(noise);
			}
		}
	}
}
