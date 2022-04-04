package dev.CodeWizz.Luqara.generation;

public class Tile {

	private TileType type;
	private int x, y, chunkX, chunkY;
	
	public Tile(int x, int y, int chunkX, int chunkY) {
		this.x = x;
		this.y = y;
		this.chunkX = chunkX;
		this.chunkY = chunkY;
		
		type = TileType.Air;
	}
	
	public Tile(int x, int y, int chunkX, int chunkY, TileType type) {
		this.x = x;
		this.y = y;
		this.chunkX = chunkX;
		this.chunkY = chunkY;
		
		this.type = type;
	}

	public TileType getType() {
		return type;
	}

	public void setType(TileType type) {
		this.type = type;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getChunkX() {
		return chunkX;
	}

	public void setChunkX(int chunkX) {
		this.chunkX = chunkX;
	}

	public int getChunkY() {
		return chunkY;
	}

	public void setChunkY(int chunkY) {
		this.chunkY = chunkY;
	}
}
