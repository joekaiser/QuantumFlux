package jotato.quantumflux.world;

import net.minecraft.world.gen.feature.WorldGenerator;

public class WorldGenInfo {
	public double rarity;
	public int chances;
	public int minY;
	public int maxY;
	public WorldGenerator generator;

	public WorldGenInfo(WorldGenerator generator, int minY, int maxY, int chances, double rarity) {
		this.minY = minY;
		this.maxY = maxY;
		this.chances = chances;
		this.rarity = rarity;
		this.generator = generator;
	}
}
