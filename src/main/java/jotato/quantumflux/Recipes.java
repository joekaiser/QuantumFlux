package jotato.quantumflux;

import jotato.quantumflux.blocks.QFBlocks;
import jotato.quantumflux.items.ItemBattleSuit;
import jotato.quantumflux.items.QFItems;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;

public class Recipes

{
	ItemStack lapis = new ItemStack(Items.dye, 1, 4);
	ItemStack redstone = new ItemStack(Items.redstone);
	ItemStack diamond = new ItemStack(Items.diamond);
	ItemStack quartz = new ItemStack(Items.quartz);
	ItemStack stone = new ItemStack(Blocks.stone);
	ItemStack enderPearl = new ItemStack(Items.ender_pearl);
	ItemStack goldIngot = new ItemStack(Items.gold_ingot);
	ItemStack ironIngot = new ItemStack(Items.iron_ingot);
	ItemStack ironBlock = new ItemStack(Blocks.iron_block);
	ItemStack amplificationCrystal = new ItemStack(QFItems.amplificationCrystal);
	ItemStack ironCasing = new ItemStack(QFItems.ironCasing);
	ItemStack goldCasing = new ItemStack(QFItems.goldCasing);
	ItemStack coal = new ItemStack(Items.coal);
	ItemStack furnace = new ItemStack(Blocks.furnace);
	ItemStack lavaBucket = new ItemStack(Items.lava_bucket);
	ItemStack quibitCrystal = new ItemStack(QFItems.quibitCrystal);
	ItemStack quibitCluster_1 = new ItemStack(QFBlocks.quibitCluster_1);
	ItemStack quibitCluster_2 = new ItemStack(QFBlocks.quibitCluster_2);
	ItemStack quibitCluster_3 = new ItemStack(QFBlocks.quibitCluster_3);
	ItemStack quibitCluster_4 = new ItemStack(QFBlocks.quibitCluster_4);
	ItemStack quibitCluster_5 = new ItemStack(QFBlocks.quibitCluster_5);
	ItemStack redstoneBlock = new ItemStack(Blocks.redstone_block);
	ItemStack lapisBlock = new ItemStack(Blocks.lapis_block);
	ItemStack diamondBlock = new ItemStack(Blocks.diamond_block);
	ItemStack goldBlock = new ItemStack(Blocks.gold_block);
	ItemStack zbq7 = new ItemStack(QFItems.zbq7);
	ItemStack rfExciter = new ItemStack(QFBlocks.rfExciter);
	ItemStack goldNugget = new ItemStack(Items.gold_nugget);
	ItemStack energizedCrystal = new ItemStack(QFItems.energizedCrystal);
	ItemStack netherStar = new ItemStack(Items.nether_star);
	ItemStack glowingMangalloy = new ItemStack(QFItems.glowingMangalloy);
	ItemStack battlesuite_plate = new ItemStack(QFItems.battlesuit_plate);
	ItemStack feather = new ItemStack(Items.feather);
	ItemStack upgradeToken = new ItemStack(QFItems.upgradeToken);
	ItemStack spiderEye = new ItemStack(Items.spider_eye);
	ItemStack eviscerator_rod = new ItemStack(QFItems.eviscerator_rod);
	ItemStack eviscerator_head = new ItemStack(QFItems.eviscerator_head);

	String manganese = "dustManganese";
	String mangalloy = "ingotMangalloy";
	String steelIngot = "ingotSteel";

	public void init()
	{
		shapedRecipes();
		shapelessRecipes();
		furnaceRecipes();
	}

	private void shapedRecipes()
	{
		GameRegistry.addShapedRecipe(new ItemStack(QFItems.ironCasing, 4), " l ", "iii", 'l', lapis, 'i', ironIngot);
		GameRegistry.addShapedRecipe(new ItemStack(QFItems.goldCasing, 4), " r ", "ggg", 'r', redstone, 'g', goldIngot);
		GameRegistry.addShapedRecipe(new ItemStack(QFItems.amplificationCrystal), "q q", " d ", "sss", 'q', quartz, 'd', diamond, 's',
				stone);
		GameRegistry.addShapedRecipe(new ItemStack(QFItems.steelIngot), "c c", " i ", "c c", 'c', coal, 'i', ironIngot);
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(QFBlocks.entropyAccelerator), "ccc", "sfs", "sls", 'c', ironCasing, 'f',
				furnace, 'l', lavaBucket, 's', steelIngot));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(QFItems.quibitCrystal, 4), " r ", "qdq", " r ", 'r', redstone, 'q',
				quartz, 'd', diamond));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(QFBlocks.quibitCluster_1), "qcq", "cqc", "sss", 'q', quibitCrystal, 'c',
				goldCasing, 's', steelIngot));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(QFBlocks.quibitCluster_2), "qqa", "qqa", 'q', quibitCluster_1, 'a',
				amplificationCrystal));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(QFBlocks.quibitCluster_3), "qqa", "qqa", 'q', quibitCluster_2, 'a',
				amplificationCrystal));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(QFBlocks.quibitCluster_4), "qqa", "qqa", 'q', quibitCluster_3, 'a',
				amplificationCrystal));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(QFBlocks.quibitCluster_5), "qqa", "qqa", 'q', quibitCluster_4, 'a',
				amplificationCrystal));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(QFItems.manganese), "grg", "rir", "grg", 'r', redstone, 'i', ironIngot,
				'g', goldNugget));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(QFItems.mangalloy), "sss", "sms", "sss", 's', steelIngot, 'm', manganese));

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(QFItems.zbq7), "rlr", "gdg", "rlr", 'r', redstoneBlock, 'l', lapisBlock,
				'g', goldBlock, 'd', diamondBlock));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(QFBlocks.rfEntangler), "zaz", "zqz", "mmm", 'm', mangalloy, 'a',
				amplificationCrystal, 'q', quibitCluster_4, 'z', zbq7));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(QFBlocks.rfExciter, 2), "qeq", "mgm", 'q', quibitCrystal, 'e', enderPearl,
				'm', steelIngot, 'g', goldCasing));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(QFBlocks.rfExciterL2), "qeq", " a ", "qeq", 'q', quibitCrystal, 'e',
				rfExciter, 'a', amplificationCrystal));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(QFItems.quibitCell), "eae", "aqa", "eae", 'q', quibitCluster_2, 'e',
				rfExciter, 'a', amplificationCrystal));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(QFItems.magnet), "r l", "a a", " m ", 'l', lapis, 'r', redstone, 'a',
				amplificationCrystal, 'm', mangalloy));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(QFItems.energizedCrystal, 4), "zcz", "csc", "zcz", 'c', quibitCrystal,
				'z', zbq7, 's', netherStar));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(QFItems.glowingMangalloy, 8), "mmm", "mem", "mmm", 'm', mangalloy, 'e',
				energizedCrystal));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(QFItems.eviscerator_rod), "pgp", "pdp", "pdp", 'g', goldNugget, 'p',
				battlesuite_plate, 'd', diamond));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(QFItems.eviscerator_head), "dp ", "p n", 'd', diamond, 'p',
				battlesuite_plate, 'n', netherStar));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(QFItems.eviscerator), "h", "r", "r", 'r', eviscerator_rod, 'h',
				eviscerator_head));

		// battlesuit
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(QFItems.battlesuit_plate), "gg", "gg", 'g', glowingMangalloy));
		GameRegistry.addRecipe(new ShapedOreRecipe(ItemBattleSuit.newArmorPiece(QFItems.battlesuit_helm, 0, false), "mmm", "m m", 'm',
				battlesuite_plate));
		GameRegistry.addRecipe(new ShapedOreRecipe(ItemBattleSuit.newArmorPiece(QFItems.battlesuit_chest, 0, false), "m m", "mmm", "mmm",
				'm', battlesuite_plate));
		GameRegistry.addRecipe(new ShapedOreRecipe(ItemBattleSuit.newArmorPiece(QFItems.battlesuit_legs, 0, false), "mmm", "m m", "m m",
				'm', battlesuite_plate));
		GameRegistry.addRecipe(new ShapedOreRecipe(ItemBattleSuit.newArmorPiece(QFItems.battlesuit_boots, 0, false), "m m", "m m", 'm',
				battlesuite_plate));

		if (ConfigMan.zpe_enabled)
		{
			GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(QFBlocks.zpe), "aza", "cqc", "mmm", 'm', mangalloy, 'z', zbq7, 'a',
					amplificationCrystal, 'q', quibitCluster_1, 'c', quibitCrystal));
		}
	}

	private void furnaceRecipes()
	{

	}

	private void shapelessRecipes()
	{
		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(QFItems.upgradeToken), netherStar, zbq7));
		GameRegistry.addRecipe(new ShapelessOreRecipe(ItemBattleSuit.newArmorPiece(QFItems.battlesuit_chest, 1, true), upgradeToken,
				feather, ItemBattleSuit.newArmorPiece(QFItems.battlesuit_chest, 0, true)));
		GameRegistry.addRecipe(new ShapelessOreRecipe(ItemBattleSuit.newArmorPiece(QFItems.battlesuit_boots, 1, true), upgradeToken,
				redstone, ItemBattleSuit.newArmorPiece(QFItems.battlesuit_boots, 0, true)));
		GameRegistry.addRecipe(new ShapelessOreRecipe(ItemBattleSuit.newArmorPiece(QFItems.battlesuit_legs, 1, true), upgradeToken, lapis,
				ItemBattleSuit.newArmorPiece(QFItems.battlesuit_legs, 0, true)));
		GameRegistry.addRecipe(new ShapelessOreRecipe(ItemBattleSuit.newArmorPiece(QFItems.battlesuit_helm, 1, true), upgradeToken,
				spiderEye, ItemBattleSuit.newArmorPiece(QFItems.battlesuit_helm, 0, true)));
	}

}
