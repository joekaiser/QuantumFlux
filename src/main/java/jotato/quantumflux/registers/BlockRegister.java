package jotato.quantumflux.registers;

import jotato.quantumflux.Logger;
import jotato.quantumflux.blocks.BlockBase;
import jotato.quantumflux.blocks.darkstone.BlockDarkstone;
import jotato.quantumflux.machines.cluster.BlockQuibitCluster;
import jotato.quantumflux.machines.entropyaccelerator.BlockEntropyAccelerator;
import jotato.quantumflux.machines.entropyaccelerator.TileEntropyAccelerator;
import jotato.quantumflux.machines.imaginarytime.BlockImaginaryTime;
import jotato.quantumflux.machines.imaginarytime.TileImaginaryTime;
import jotato.quantumflux.machines.zpe.BlockZeroPointExtractor;
import jotato.quantumflux.machines.zpe.TileZeroPointExtractor;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public final class BlockRegister {

	public static BlockBase imaginaryTime;
	public static BlockBase zeroPointExtractor;
	public static BlockBase entropyAccelerator;
	public static BlockBase quibitCluster;
	public static BlockBase darkstone;

	public static void init() {
		imaginaryTime = new BlockImaginaryTime();
		zeroPointExtractor = new BlockZeroPointExtractor();
		entropyAccelerator = new BlockEntropyAccelerator();
		quibitCluster = new BlockQuibitCluster();
		darkstone = new BlockDarkstone();
		
		initTileEntities();
	}

	private static void initTileEntities() {
		Logger.info("Registering TileEntities");
		GameRegistry.registerTileEntity(TileImaginaryTime.class, "QFTILEimaginaryTime");
		GameRegistry.registerTileEntity(TileZeroPointExtractor.class, "QFTILEzeroPointExtractor");
		GameRegistry.registerTileEntity(TileEntropyAccelerator.class, "QFTILEentropyAccelerator");
	}

	@SideOnly(Side.CLIENT)
	public static void registerRenders() {
		Logger.info("Registering Blocks");
		imaginaryTime.initModel();
		zeroPointExtractor.initModel();
		entropyAccelerator.initModel();
		quibitCluster.initModel();
		darkstone.initModel();
	}
}
