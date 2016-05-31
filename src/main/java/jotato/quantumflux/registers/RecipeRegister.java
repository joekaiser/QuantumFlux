package jotato.quantumflux.registers;

import org.omg.CORBA.IRObjectOperations;

import jotato.quantumflux.ConfigMan;
import jotato.quantumflux.blocks.darkstone.EnumDarkstone;
import jotato.quantumflux.machines.cluster.EnumQuibitCluster;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.ShapedOreRecipe;

public class RecipeRegister
{
	// vanilla resources
	String ingotIron = "ingotIron";
	String ingotGold = "ingotGold";
	String nuggetGold = "nuggetGold";
	String dyeBlack = "dyeBlack";

	ItemStack redstone = new ItemStack(Items.REDSTONE);
	ItemStack quartz = new ItemStack(Items.QUARTZ);
	ItemStack diamond = new ItemStack(Items.DIAMOND);
	ItemStack enderPearl = new ItemStack(Items.ENDER_PEARL);
	ItemStack paper = new ItemStack(Items.PAPER);
	ItemStack cobblestone = new ItemStack(Blocks.COBBLESTONE);
	ItemStack stone = new ItemStack(Blocks.STONE);
	ItemStack lapis = new ItemStack(Items.DYE, 1, 4);
	ItemStack furnace = new ItemStack(Blocks.FURNACE);
	ItemStack lavaBucket = new ItemStack(Items.LAVA_BUCKET);
	ItemStack netherStar = new ItemStack(Items.NETHER_STAR);
	ItemStack stick = new ItemStack(Items.STICK);
	ItemStack slimeBall = new ItemStack(Items.SLIME_BALL);

	// modItems
	ItemStack goldCasing = ItemRegister.craftingPieces.getSubItem("goldCasing");
	ItemStack amplificationCrystal = ItemRegister.craftingPieces.getSubItem("amplificationCrystal");
	ItemStack quibitCrystal = ItemRegister.craftingPieces.getSubItem("quibitCrystal");
	ItemStack enderCrystal = ItemRegister.craftingPieces.getSubItem("enderCrystal");
	ItemStack quantumDisk = ItemRegister.craftingPieces.getSubItem("quantumDisk");
	ItemStack advancedCircuit = ItemRegister.craftingPieces.getSubItem("advancedCircuit");
	ItemStack darkstone = EnumDarkstone.getDarkstoneType(EnumDarkstone.plain);
	ItemStack hyperDiamond = ItemRegister.craftingPieces.getSubItem("industrialDiamond");

	ItemStack quibitCluster1 = EnumQuibitCluster.getQuibitClusterFromType(EnumQuibitCluster.one);
	ItemStack quibitCluster2 = EnumQuibitCluster.getQuibitClusterFromType(EnumQuibitCluster.two);
	ItemStack quibitCluster3 = EnumQuibitCluster.getQuibitClusterFromType(EnumQuibitCluster.three);
	ItemStack quibitCluster4 = EnumQuibitCluster.getQuibitClusterFromType(EnumQuibitCluster.four);
	ItemStack quibitCluster5 = EnumQuibitCluster.getQuibitClusterFromType(EnumQuibitCluster.five);

	public void init()
	{
		shapedRecipes();
		shapelessRecipes();
	}

	private void shapedRecipes()
	{
		addShapedOreRecipe(true, ItemRegister.craftingPieces.getSubItem("goldCasing", 2), " r ", "ggg", 'r', redstone, 'g', ingotGold);

		addShapedOreRecipe(true, amplificationCrystal, "q q", " d ", "sss", 'q', quartz, 'd', diamond, 's', darkstone);

		addShapedOreRecipe(true, ItemRegister.craftingPieces.getSubItem("quibitCrystal", 2), " e ", "qdq", " e ", 'e', enderCrystal, 'q',
				quartz, 'd', diamond);

		addShapedOreRecipe(true, enderPearl, "ee", "ee", 'e', enderCrystal);

		addShapedOreRecipe(true, advancedCircuit, "grg", "ehe", "ppp", 'g', nuggetGold, 'r', redstone, 'e', enderCrystal, 'p', paper, 'h',
				hyperDiamond);

		addShapedOreRecipe(true, ItemRegister.magnet, "e e", "a a", " i ", 'e', enderCrystal, 'a', amplificationCrystal, 'i', ingotIron);

		addShapedOreRecipe(true, new ItemStack(ItemRegister.exciterUpgrade, 2), "rqr", "ddd", 'r', redstone, 'q', quibitCrystal, 'd',
				darkstone);

		addShapedOreRecipe(true, EnumDarkstone.getDarkstoneType(EnumDarkstone.plain, 16), "csc", "cdc", "csc", 'c', cobblestone, 'd',
				dyeBlack, 's', stone);

		addShapedOreRecipe(true, EnumDarkstone.getDarkstoneType(EnumDarkstone.lamp, 4), "d d", " l ", "d d", 'd', darkstone, 'l', lapis);

		addShapedOreRecipe(true, EnumDarkstone.getDarkstoneType(EnumDarkstone.tile, 4), "dd", "dd", 'd', darkstone);

		addShapedOreRecipe(true, EnumDarkstone.getDarkstoneType(EnumDarkstone.ornate, 4), "d d", " s ", "d d", 'd', darkstone, 's', stone);

		addShapedOreRecipe(true, BlockRegister.entropyAccelerator, "ccc", "dfd", "ili", 'c', goldCasing, 'd', darkstone, 'f', furnace, 'i',
				ingotIron, 'l', lavaBucket);

		addShapedOreRecipe(true, quibitCluster1, "qcq", "cqc", "iii", 'q', quibitCrystal, 'c', goldCasing, 'i', ingotIron);

		addShapedOreRecipe(true, quibitCluster2, "c c", " a ", "c c", 'c', quibitCluster1, 'a', amplificationCrystal);

		addShapedOreRecipe(true, quibitCluster3, "c c", " a ", "c c", 'c', quibitCluster2, 'a', amplificationCrystal);

		addShapedOreRecipe(true, quibitCluster4, "c c", " a ", "c c", 'c', quibitCluster3, 'a', amplificationCrystal);

		addShapedOreRecipe(true, quibitCluster5, "c c", " a ", "c c", 'c', quibitCluster4, 'a', amplificationCrystal);

		addShapedOreRecipe(true, BlockRegister.imaginaryTime, "cqc", "s3s", "cqc", 'c', goldCasing, 'q', quibitCrystal, 's', netherStar,
				'3', quibitCluster3);

		addShapedOreRecipe(ConfigMan.zpe_enabled, BlockRegister.zeroPointExtractor, "ada", "qcq", "ggg", 'a', advancedCircuit, 'g',
				goldCasing, 'd', hyperDiamond, 'q', quibitCrystal, 'c', quibitCluster2);

		addShapedOreRecipe(true, BlockRegister.rfEntangler, "qsq", "xax", "ccc", 'q', quibitCrystal, 's', netherStar, 'x', advancedCircuit,
				'a', quibitCluster2, 'c', goldCasing);

		addShapedOreRecipe(true, new ItemStack(BlockRegister.rfExciter, 2), "qeq", "cqc", 'q', quibitCrystal, 'e', enderCrystal, 'c',
				goldCasing);

		addShapedOreRecipe(true, ItemRegister.quibitCell, " q ", "gcg", "rar", 'q', quibitCrystal, 'g', goldCasing, 'c', quibitCluster3,
				'r', redstone, 'a', advancedCircuit);

		addShapedOreRecipe(true, ItemRegister.voidBucket, "iei", " i ", 'i', ingotIron, 'e', enderPearl);

		addShapedOreRecipe(true, ItemRegister.craftingPieces.getSubItem("enderCrystal", 4), " q ", "qeq", " q ", 'q', quartz, 'e',
				enderPearl);

		addShapedOreRecipe(true, ItemRegister.matterTransporter, "b b", " c ", " s ", 'b', slimeBall, 'c', advancedCircuit, 's', stick);

		addShapedOreRecipe(true, ItemRegister.craftingPieces.getSubItem("quantumDisk"), "i i", "cac", "i i", 'c', amplificationCrystal,
				'a', advancedCircuit, 'i', ingotIron);
		
		addShapedOreRecipe(true,BlockRegister.telepad, " a ", "ere", "ddd", 'a', advancedCircuit, 'e',enderPearl,'r',redstone,'d',darkstone);

	}

	private void shapelessRecipes()
	{
		addShapelessRecipe(true, ItemRegister.linkingCard, enderCrystal, paper);

	}

	// below methods snagged from CoFHLib because it isn't compiled for 1.8.8

	public static void addShapelessRecipe(boolean condition, ItemStack out, Object... recipe)
	{
		if (condition)
			GameRegistry.addShapelessRecipe(out, recipe);
	}

	public static void addShapelessRecipe(boolean condition, Item out, Object... recipe)
	{

		addShapelessRecipe(condition, new ItemStack(out), recipe);
	}

	public static void addShapelessRecipe(boolean condition, Block out, Object... recipe)
	{

		addShapelessRecipe(condition, new ItemStack(out), recipe);
	}

	public static void addShapedOreRecipe(boolean condition, ItemStack out, Object... recipe)
	{
		if (condition)
			GameRegistry.addRecipe(new ShapedOreRecipe(out, recipe));
	}

	public static void addShapedOreRecipe(boolean condition, Item out, Object... recipe)
	{
		if (condition)
			GameRegistry.addRecipe(new ShapedOreRecipe(out, recipe));
	}

	public static void addShapedOreRecipe(boolean condition, Block out, Object... recipe)
	{
		if (condition)
			GameRegistry.addRecipe(new ShapedOreRecipe(out, recipe));
	}

}
