package jotato.quantumflux.world;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import cofh.lib.world.WorldGenSparseMinableCluster;
import cpw.mods.fml.common.IWorldGenerator;
import jotato.quantumflux.blocks.ModBlocks;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;

public class OreGenerator implements IWorldGenerator {

	public static final OreGenerator INSTANCE = new OreGenerator();
	private final List<WorldGenInfo> generators = new ArrayList();

	public  OreGenerator() {
		//titanium is about .4% or ore distribution 
		WorldGenSparseMinableCluster titaniumGen = new WorldGenSparseMinableCluster(new ItemStack(ModBlocks.titaniumOre), 4, Blocks.stone);
		generators.add(new WorldGenInfo(titaniumGen, 7, 11, 4, .15));
	}

	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator,
			IChunkProvider chunkProvider) {
		for (WorldGenInfo gen : generators) {
			genOre(gen, world, random, chunkX * 16, chunkZ * 16);
		}
	}

	private void genOre(WorldGenInfo info, World world, Random random, int x, int z) {
		for (int i = 0; i < info.chances; i++) {
			if (random.nextDouble() < info.rarity) {
				
				int avgX = x + random.nextInt(16);
				int avgY = info.minY + random.nextInt(info.maxY - info.minY) + 1;
				int avgZ = z + random.nextInt(16);
				info.generator.generate(world, random, avgX, avgY, avgZ);
			}
		}
	}

}
