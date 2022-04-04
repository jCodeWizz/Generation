package dev.CodeWizz.Luqara.generation;

public enum Biome {

	Plains(TileType.Grass, TileType.Dirt, TileType.Stone),
	Desert(TileType.Sand, TileType.Sand, TileType.Stone);
	
	private final TileType top;
	private final TileType soil;
	private final TileType bottom;
	
	Biome(TileType top, TileType soil, TileType bottom) {
		this.top = top;
		this.soil = soil;
		this.bottom = bottom;
	}
	
	public TileType getTop() {
		return this.top;
	}
	
	public TileType getSoil() {
		return this.soil;
	}
	
	public TileType getBottom() {
		return this.bottom;
	}
	
	
	
}
