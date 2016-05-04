package jotato.quantumflux.world;

import java.util.Random;

import jotato.quantumflux.ConfigMan;
import jotato.quantumflux.Logger;
import jotato.quantumflux.registers.BlockRegister;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkGenerator;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraftforge.fml.common.IWorldGenerator;

public class QuantumWorldGenerator implements IWorldGenerator {

	public static QuantumWorldGenerator INSTANCE = new QuantumWorldGenerator();

	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator,
			IChunkProvider chunkProvider) {
		
		spawnBlock(BlockRegister.graphiteOre.getDefaultState(),Blocks.stone.getDefaultState(),world,random,chunkX*16,chunkZ*16,6,10,30);
	}

	public void spawnBlock(IBlockState block, IBlockState targetBlock, World world, Random random, int blockXPos,
			int blockZPos, int minVeinSize, int maxVeinSize, int chancesToSpawn, int minY, int maxY) {
		WorldGenMinable minable = new WorldGenMinable(block, (minVeinSize - random.nextInt(maxVeinSize - minVeinSize)),
				state -> state.getBlock() == targetBlock.getBlock());
		for (int i = 0; i < chancesToSpawn; i++) {
			int posX = blockXPos + random.nextInt(16);
			int posY = minY + random.nextInt(maxY - minY);
			int posZ = blockZPos + random.nextInt(16);
			minable.generate(world, random, new BlockPos(posX, posY, posZ));
		}
	}

	public void spawnBlock(IBlockState block, IBlockState targetBlock, World world, Random random, int blockXPos,
			int blockZPos, int veinSize, int minY, int maxY) {
		WorldGenMinable minable = new WorldGenMinable(block, veinSize,
				state -> state.getBlock() == targetBlock.getBlock());
		int posX = blockXPos + random.nextInt(16);
		int posY = minY + random.nextInt(maxY - minY);
		int posZ = blockZPos + random.nextInt(16);
		minable.generate(world, random, new BlockPos(posX, posY, posZ));
	}

}
