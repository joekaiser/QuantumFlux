package jotato.quantumflux.registers;

import jotato.quantumflux.ConfigMan;
import jotato.quantumflux.Logger;
import jotato.quantumflux.blocks.BlockBase;
import jotato.quantumflux.blocks.BlockGraphiteOre;
import jotato.quantumflux.blocks.darkstone.BlockDarkstone;
import jotato.quantumflux.blocks.mobglue.BlockMobGlue;
import jotato.quantumflux.machines.cluster.BlockQuibitCluster;
import jotato.quantumflux.machines.cluster.TileCreativeCluster;
import jotato.quantumflux.machines.cluster.TileQuibitCluster;
import jotato.quantumflux.machines.containmentunit.BlockContainmentUnit;
import jotato.quantumflux.machines.entangler.BlockRFEntangler;
import jotato.quantumflux.machines.entangler.TileRFEntangler;
import jotato.quantumflux.machines.entropyaccelerator.BlockEntropyAccelerator;
import jotato.quantumflux.machines.entropyaccelerator.TileEntropyAccelerator;
import jotato.quantumflux.machines.exciter.BlockRFExciter;
import jotato.quantumflux.machines.exciter.TileRFExciter;
import jotato.quantumflux.machines.imaginarytime.BlockImaginaryTime;
import jotato.quantumflux.machines.imaginarytime.TileImaginaryTime;
import jotato.quantumflux.machines.telepad.BlockTelepad;
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
	public static BlockBase rfEntangler;
	public static BlockBase rfExciter;
	public static BlockBase graphiteOre;
	//i can't get the glue from stopping motion. The best i can do is slwo it down
	//public static BlockBase mobGlue;
	public static BlockBase containmentUnit;
	public static BlockBase telepad;

	public static void init() {
		initTileEntities();

		imaginaryTime = new BlockImaginaryTime();
		zeroPointExtractor = new BlockZeroPointExtractor();
		entropyAccelerator = new BlockEntropyAccelerator();
		quibitCluster = new BlockQuibitCluster();
		darkstone = new BlockDarkstone();
		rfEntangler = new BlockRFEntangler();
		rfExciter = new BlockRFExciter();
		graphiteOre = new BlockGraphiteOre();
		//mobGlue = new BlockMobGlue();
		containmentUnit = new BlockContainmentUnit();
		telepad = new BlockTelepad();

	}

	private static void initTileEntities() {
		Logger.info("Registering TileEntities");
		GameRegistry.registerTileEntity(TileImaginaryTime.class, "QFTILE_imaginaryTime");
		GameRegistry.registerTileEntity(TileZeroPointExtractor.class, "QFTILE_zeroPointExtractor");
		GameRegistry.registerTileEntity(TileEntropyAccelerator.class, "QFTILE_entropyAccelerator");
		GameRegistry.registerTileEntity(TileQuibitCluster.class, "QFTILE_quibitCluster");
		GameRegistry.registerTileEntity(TileCreativeCluster.class, "QFTILE_creativeCluster");
		GameRegistry.registerTileEntity(TileRFEntangler.class, "QFTILE_rfEntnagler");
		GameRegistry.registerTileEntity(TileRFExciter.class, "QFTILE_rfExciter");

	}

	@SideOnly(Side.CLIENT)
	public static void registerRenders() {
		Logger.info("Registering Blocks");
		imaginaryTime.initModel();
		zeroPointExtractor.initModel();
		entropyAccelerator.initModel();
		quibitCluster.initModel();
		darkstone.initModel();
		rfEntangler.initModel();
		rfExciter.initModel();
		graphiteOre.initModel();
		//mobGlue.initModel();
		containmentUnit.initModel();
		telepad.initModel();
	}
}
