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

public class RecipeRegister {
	// vanilla resources
		String ingotIron = "ingotIron";
		String ingotGold = "ingotGold";
		String nuggetGold = "nuggetGold";
		String dyeBlack = "dyeBlack";
		
		ItemStack redstone = new ItemStack(Items.redstone);
		ItemStack quartz = new ItemStack(Items.quartz);
		ItemStack diamond = new ItemStack(Items.diamond);
		ItemStack enderPearl = new ItemStack(Items.ender_pearl);
		ItemStack paper = new ItemStack(Items.paper);
		ItemStack cobblestone = new ItemStack(Blocks.cobblestone);
		ItemStack stone = new ItemStack(Blocks.stone);
		ItemStack lapis = new ItemStack(Items.dye, 1, 4);
		ItemStack furnace = new ItemStack(Blocks.furnace);
		ItemStack lavaBucket = new ItemStack(Items.lava_bucket);
		ItemStack netherStar = new ItemStack(Items.nether_star);

		//modItems
		ItemStack goldCasing = ItemRegister.craftingPieces.getSubItem("goldCasing");
		ItemStack amplificationCrystal = ItemRegister.craftingPieces.getSubItem("amplificationCrystal");
		ItemStack quibitCrystal = ItemRegister.craftingPieces.getSubItem("quibitCrystal");
		ItemStack enderCrystal = ItemRegister.craftingPieces.getSubItem("enderCrystal");
		ItemStack quantumDisk = ItemRegister.craftingPieces.getSubItem("quantumDisk");
		ItemStack advancedCircuit = ItemRegister.craftingPieces.getSubItem("advancedCircuit");
		ItemStack darkstone = EnumDarkstone.getDarkstoneType(EnumDarkstone.plain);
		
		ItemStack quibitCluster1 = EnumQuibitCluster.getQuibitClusterFromType(EnumQuibitCluster.one);
		ItemStack quibitCluster2 = EnumQuibitCluster.getQuibitClusterFromType(EnumQuibitCluster.two);
		ItemStack quibitCluster3 = EnumQuibitCluster.getQuibitClusterFromType(EnumQuibitCluster.three);
		ItemStack quibitCluster4 = EnumQuibitCluster.getQuibitClusterFromType(EnumQuibitCluster.four);
		ItemStack quibitCluster5 = EnumQuibitCluster.getQuibitClusterFromType(EnumQuibitCluster.five);
		

		public void init() {
			shapedRecipes();
			shapelessRecipes();
		}

		private void shapedRecipes() {
			addShapedOreRecipe(true, ItemRegister.craftingPieces.getSubItem("goldCasing",2), 
					" r ",
					"ggg", 'r', redstone,'g',ingotGold);
			
			addShapedOreRecipe(true, amplificationCrystal, 
					"q q",
					" d ",
					"sss", 
					'q', quartz,'d',diamond, 's',darkstone);
			
			addShapedOreRecipe(true, ItemRegister.craftingPieces.getSubItem("quibitCrystal",2), 
					" e ",
					"qdq",
					" e ", 
					'e', enderCrystal, 'q', quartz,'d',diamond);
			
			addShapedOreRecipe(true, enderPearl,
					"ee",
					"ee" ,'e',enderCrystal);
			
			addShapedOreRecipe(true, advancedCircuit,
					"grg",
					"eee",
					"ppp" ,'g', nuggetGold,'r',redstone,'e',enderCrystal,'p',paper);

			addShapedOreRecipe(true, ItemRegister.magnet,
					"e e",
					"a a",
					"iii", 'e', enderCrystal,'a',amplificationCrystal,'i',ingotIron);

			addShapedOreRecipe(true, new ItemStack(ItemRegister.exciterUpgrade,2),
					"rqr",
					"ddd",'r',redstone,'q',quibitCrystal,'d',darkstone);
			
			addShapedOreRecipe(true, EnumDarkstone.getDarkstoneType(EnumDarkstone.plain, 16),
					"ccc",
					"cdc",
					"ccc",'c',cobblestone,'d',dyeBlack);
			
			addShapedOreRecipe(true, EnumDarkstone.getDarkstoneType(EnumDarkstone.lamp, 4),
					"d d",
					" l ",
					"d d",'d',darkstone,'l',lapis);
			
			addShapedOreRecipe(true, EnumDarkstone.getDarkstoneType(EnumDarkstone.tile, 4),
					"dd",
					"dd",'d',darkstone);
			
			addShapedOreRecipe(true, EnumDarkstone.getDarkstoneType(EnumDarkstone.ornate, 4),
					"d d",
					" s ",
					"d d",'d',darkstone, 's', stone);
			
			addShapedOreRecipe(true,BlockRegister.entropyAccelerator,
					"ccc",
					"dfd",
					"ili", 'c', goldCasing,'d',darkstone,'f',furnace,'i',ingotIron,'l',lavaBucket);
			
			addShapedOreRecipe(true, quibitCluster1, 
					"qcq",
					"cqc",
					"iii", 'q', quibitCrystal, 'c', goldCasing,'i',ingotIron);
			
			addShapedOreRecipe(true, quibitCluster2, 
					"c c",
					" a ",
					"c c",'c',quibitCluster1, 'a', amplificationCrystal);
			
			addShapedOreRecipe(true, quibitCluster3, 
					"c c",
					" a ",
					"c c",'c',quibitCluster2, 'a', amplificationCrystal);
			
			addShapedOreRecipe(true, quibitCluster4, 
					"c c",
					" a ",
					"c c",'c',quibitCluster3, 'a', amplificationCrystal);
			
			addShapedOreRecipe(true, quibitCluster5, 
					"c c",
					" a ",
					"c c",'c',quibitCluster4, 'a', amplificationCrystal);
			
			addShapedOreRecipe(true, BlockRegister.imaginaryTime,
					"cqc",
					"s3s",
					"cqc", 'c',goldCasing,'q',quibitCrystal,'s',netherStar,'3',quibitCluster3);
			
			addShapedOreRecipe(ConfigMan.zpe_enabled, BlockRegister.zeroPointExtractor,
					"geg",
					"qcq",
					"ggg", 'g',goldCasing,'e',enderCrystal,'q',quibitCrystal,'c',quibitCluster2);
			
			addShapedOreRecipe(true, BlockRegister.rfEntangler,
					"qsq",
					"xax",
					"ccc", 'q',quibitCrystal,'s',netherStar,'x',advancedCircuit,'a',quibitCluster1,'c',goldCasing);
			
			addShapedOreRecipe(true, BlockRegister.rfExciter,
					"qeq",
					"cqc",'q',quibitCrystal,'e',enderCrystal,'c',goldCasing);
			
			addShapedOreRecipe(true, ItemRegister.quibitCell,
					" q ",
					"gcg",
					"rrr", 'q',quibitCrystal,'g',goldCasing,'c',quibitCluster3,'r',redstone);
			
			addShapedOreRecipe(true, ItemRegister.voidBucket,
					"iei",
					" i " ,'i',ingotIron,'e',enderPearl);
			
		}

		private void shapelessRecipes() {
			

		}

		// below methods snagged from CoFHLib because it isn't compiled for 1.8.8

		public static void addShapelessRecipe(boolean condition, ItemStack out, Object... recipe) {
			if (condition)
				GameRegistry.addShapelessRecipe(out, recipe);
		}

		public static void addShapelessRecipe(boolean condition, Item out, Object... recipe) {

			addShapelessRecipe(condition, new ItemStack(out), recipe);
		}

		public static void addShapelessRecipe(boolean condition, Block out, Object... recipe) {

			addShapelessRecipe(condition, new ItemStack(out), recipe);
		}

		public static void addShapedOreRecipe(boolean condition, ItemStack out, Object... recipe) {
			if (condition)
				GameRegistry.addRecipe(new ShapedOreRecipe(out, recipe));
		}

		public static void addShapedOreRecipe(boolean condition, Item out, Object... recipe) {
			if (condition)
				GameRegistry.addRecipe(new ShapedOreRecipe(out, recipe));
		}

		public static void addShapedOreRecipe(boolean condition, Block out, Object... recipe) {
			if (condition)
				GameRegistry.addRecipe(new ShapedOreRecipe(out, recipe));
		}

}
