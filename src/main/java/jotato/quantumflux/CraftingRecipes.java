package jotato.quantumflux;

import jotato.quantumflux.blocks.ModBlocks;
import jotato.quantumflux.items.ItemBattleSuit;
import jotato.quantumflux.items.ModItems;
import cofh.lib.util.helpers.ItemHelper;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;

//todo: break this out into Crafting Recipes and Furnace Recipes
public class CraftingRecipes {
	ItemStack lapis = new ItemStack(Items.dye, 1, 4);
	ItemStack redstone = new ItemStack(Items.redstone);
	ItemStack diamond = new ItemStack(Items.diamond);
	ItemStack quartz = new ItemStack(Items.quartz);
	ItemStack stone = new ItemStack(Blocks.stone);
	ItemStack enderPearl = new ItemStack(Items.ender_pearl);
	ItemStack goldIngot = new ItemStack(Items.gold_ingot);
	ItemStack ironIngot = new ItemStack(Items.iron_ingot);
	ItemStack ironBlock = new ItemStack(Blocks.iron_block);
	ItemStack amplificationCrystal = new ItemStack(ModItems.amplificationCrystal);
	ItemStack ironCasing = new ItemStack(ModItems.ironCasing);
	ItemStack goldCasing = new ItemStack(ModItems.goldCasing);
	ItemStack coal = new ItemStack(Items.coal);
	ItemStack furnace = new ItemStack(Blocks.furnace);
	ItemStack lavaBucket = new ItemStack(Items.lava_bucket);
	ItemStack quibitCrystal = new ItemStack(ModItems.quibitCrystal);
	ItemStack quibitCluster_1 = new ItemStack(ModBlocks.quibitCluster, 1, 0);
	ItemStack quibitCluster_2 = new ItemStack(ModBlocks.quibitCluster, 1, 1);
	ItemStack quibitCluster_3 = new ItemStack(ModBlocks.quibitCluster, 1, 2);
	ItemStack quibitCluster_4 = new ItemStack(ModBlocks.quibitCluster, 1, 3);
	ItemStack quibitCluster_5 = new ItemStack(ModBlocks.quibitCluster, 1, 4);
	ItemStack redstoneBlock = new ItemStack(Blocks.redstone_block);
	ItemStack lapisBlock = new ItemStack(Blocks.lapis_block);
	ItemStack diamondBlock = new ItemStack(Blocks.diamond_block);
	ItemStack goldBlock = new ItemStack(Blocks.gold_block);
	ItemStack zbq7 = new ItemStack(ModItems.zbq7);
	ItemStack rfExciter = new ItemStack(ModBlocks.rfExciter);
	ItemStack goldNugget = new ItemStack(Items.gold_nugget);
	ItemStack energizedCrystal = new ItemStack(ModItems.energizedCrystal);
	ItemStack netherStar = new ItemStack(Items.nether_star);
	ItemStack glowingMangalloy = new ItemStack(ModItems.glowingMangalloy);
	ItemStack battlesuite_plate = new ItemStack(ModItems.battlesuit_plate);
	ItemStack feather = new ItemStack(Items.feather);
	ItemStack upgradeToken = new ItemStack(ModItems.upgradeToken);
	ItemStack spiderEye = new ItemStack(Items.spider_eye);
	ItemStack eviscerator_rod = new ItemStack(ModItems.eviscerator_rod);
	ItemStack eviscerator_head = new ItemStack(ModItems.eviscerator_head);
	ItemStack crystalizedRedstone = new ItemStack(ModItems.crystalizedRedstone);
	ItemStack darkStone = new ItemStack(ModBlocks.darkstone);
	ItemStack glowStoneDust = new ItemStack(Items.glowstone_dust);
	ItemStack cobblestone = new ItemStack(Blocks.cobblestone);
	ItemStack blankCircuit = new ItemStack(ModItems.blankCircuit);
	ItemStack advancedCircuit = new ItemStack(ModItems.advancedCircuit);
	ItemStack enderCrystal = new ItemStack(ModItems.enderCrystal);
	ItemStack harmonicOscillator = new ItemStack(ModItems.harmonicOscillator);
	ItemStack titaniumPlate = new ItemStack(ModItems.titaniumPlate);
	ItemStack quantumDisk = new ItemStack(ModItems.quantumDisk);

	String manganese = "dustManganese";
	String mangalloy = "ingotMangalloy";
	String steelIngot = "ingotSteel";
	String dyeBlack = "dyeBlack";
	String titaniumIngot = "ingotTitanium";

	public void init() {
		shapedRecipes();
		shapelessRecipes();
		furnaceRecipes();
	}

	private void shapedRecipes() {
		GameRegistry.addShapedRecipe(new ItemStack(ModItems.ironCasing, 4), " l ", "iii", 'l', lapis, 'i', ironIngot);
		GameRegistry.addShapedRecipe(new ItemStack(ModItems.goldCasing, 4), " r ", "ggg", 'r', redstone, 'g',
				goldIngot);
		GameRegistry.addShapedRecipe(new ItemStack(ModItems.amplificationCrystal), "q q", " d ", "sss", 'q', quartz,
				'd', diamond, 's', darkStone);
		if (ConfigMan.enableSteelRecipe) {
			GameRegistry.addShapedRecipe(new ItemStack(ModItems.steelIngot), "c c", " i ", "c c", 'c', coal, 'i',
					ironIngot);
		}
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModBlocks.entropyAccelerator), "ccc", "sfs", "sls",
				'c', ironCasing, 'f', furnace, 'l', lavaBucket, 's', steelIngot));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.quibitCrystal, 4), " r ", "qdq", " r ", 'r',
				redstone, 'q', quartz, 'd', diamond));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModBlocks.quibitCluster, 1, 0), "qcq", "cqc", "sss",
				'q', quibitCrystal, 'c', goldCasing, 's', steelIngot));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModBlocks.quibitCluster, 1, 1), "qqa", "qqa", 'q',
				quibitCluster_1, 'a', amplificationCrystal));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModBlocks.quibitCluster, 1, 2), "qqa", "qqa", 'q',
				quibitCluster_2, 'a', amplificationCrystal));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModBlocks.quibitCluster, 1, 3), "qqa", "qqa", 'q',
				quibitCluster_3, 'a', amplificationCrystal));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModBlocks.quibitCluster, 1, 4), "qqa", "qqa", 'q',
				quibitCluster_4, 'a', amplificationCrystal));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.manganese), "grg", "rir", "grg", 'r',
				redstone, 'i', ironIngot, 'g', goldNugget));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.mangalloy), "sss", "sms", "sss", 's',
				steelIngot, 'm', manganese));

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.zbq7), "rlr", "gdg", "rlr", 'r',
				redstoneBlock, 'l', lapisBlock, 'g', goldBlock, 'd', diamondBlock));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModBlocks.rfEntangler), "czc", "mqm", "sss", 'm',
				mangalloy, 's', steelIngot, 'q', quibitCluster_3, 'z', zbq7, 'c', quibitCrystal));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModBlocks.rfExciter, 4), "qeq", "sgs", 'q',
				quibitCrystal, 'e', enderPearl, 's', steelIngot, 'g', goldCasing));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.quibitCell), "eae", "aqa", "eae", 'q',
				quibitCluster_1, 'e', rfExciter, 'a', amplificationCrystal));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.magnet), "r l", "a a", " m ", 'l', lapis, 'r',
				redstone, 'a', amplificationCrystal, 'm', mangalloy));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.energizedCrystal, 4), "zcz", "csc", "zcz",
				'c', quibitCrystal, 'z', zbq7, 's', netherStar));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.glowingMangalloy, 8), "mmm", "mem", "mmm",
				'm', mangalloy, 'e', energizedCrystal));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.eviscerator_rod), "pgp", "pdp", "pdp", 'g',
				goldNugget, 'p', battlesuite_plate, 'd', diamond));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.eviscerator_head), "dp ", "p n", 'd', diamond,
				'p', battlesuite_plate, 'n', netherStar));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.eviscerator), "h", "r", "r", 'r',
				eviscerator_rod, 'h', eviscerator_head));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.exciterUpgrade, 2), "cqc", "sss", 'c',
				crystalizedRedstone, 'q', quibitCrystal, 's', darkStone));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModBlocks.darkstone, 8), "scs", "sis", "scs", 's',
				stone, 'i', dyeBlack, 'c', cobblestone));
		GameRegistry
				.addRecipe(new ShapedOreRecipe(new ItemStack(ModBlocks.darkstonePillar, 2), "s", "s", 's', darkStone));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModBlocks.darkstoneLamp, 8), "ddd", "dld", "ddd", 'd',
				darkStone, 'l', lapis));
		GameRegistry.addRecipe(
				new ShapedOreRecipe(new ItemStack(ModBlocks.darkstoneTile, 9), "ddd", "ddd", "ddd", 'd', darkStone));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModBlocks.imaginaryTime), "igi", "gzg", "mgm", 'i',
				ironCasing, 'g', energizedCrystal, 'z', zbq7, 'm', mangalloy));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.harmonicOscillator), " r ", "g g", "eae", 'r',
				crystalizedRedstone, 'g', goldIngot, 'e', enderCrystal, 'a', advancedCircuit));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModBlocks.itemFabricator), "g g", "tat", "ddd", 'g',
				goldCasing, 't', titaniumIngot, 'a', amplificationCrystal, 'd', darkStone));
		GameRegistry
				.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.titaniumPlate, 3), "ttt", 't', titaniumIngot));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.quantumDisk), " e ", "tat", " e ", 'e',
				enderCrystal, 't', titaniumPlate, 'a', advancedCircuit));
		ItemHelper.addShapedOreRecipe(ModBlocks.storehouse, "tpt", "cqc", "ghg", 't', titaniumPlate, 'p',
				advancedCircuit, 'q', quantumDisk, 'c', quibitCrystal, 'g', goldCasing, 'h', harmonicOscillator);

		// battlesuit
		GameRegistry.addRecipe(
				new ShapedOreRecipe(new ItemStack(ModItems.battlesuit_plate), "gg", "gg", 'g', glowingMangalloy));
		GameRegistry.addRecipe(new ShapedOreRecipe(ItemBattleSuit.newArmorPiece(ModItems.battlesuit_helm, 0, false),
				"mmm", "m m", 'm', battlesuite_plate));
		GameRegistry.addRecipe(new ShapedOreRecipe(ItemBattleSuit.newArmorPiece(ModItems.battlesuit_chest, 0, false),
				"m m", "mmm", "mmm", 'm', battlesuite_plate));
		GameRegistry.addRecipe(new ShapedOreRecipe(ItemBattleSuit.newArmorPiece(ModItems.battlesuit_legs, 0, false),
				"mmm", "m m", "m m", 'm', battlesuite_plate));
		GameRegistry.addRecipe(new ShapedOreRecipe(ItemBattleSuit.newArmorPiece(ModItems.battlesuit_boots, 0, false),
				"m m", "m m", 'm', battlesuite_plate));

		if (ConfigMan.zpe_enabled) {
			GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModBlocks.zpe), "aza", "cqc", "mmm", 'm',
					steelIngot, 'z', zbq7, 'a', amplificationCrystal, 'q', quibitCluster_2, 'c', quibitCrystal));
		}
	}

	private void furnaceRecipes() {
		GameRegistry.addSmelting(redstone, new ItemStack(ModItems.crystalizedRedstone), 5);
		GameRegistry.addSmelting(new ItemStack(ModBlocks.titaniumOre), new ItemStack(ModItems.titaniumIngot), 5);
	}

	private void shapelessRecipes() {
		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(ModItems.upgradeToken), netherStar, zbq7));
		GameRegistry.addRecipe(new ShapelessOreRecipe(ItemBattleSuit.newArmorPiece(ModItems.battlesuit_chest, 1, true),
				upgradeToken, feather, ItemBattleSuit.newArmorPiece(ModItems.battlesuit_chest, 0, true)));
		GameRegistry.addRecipe(new ShapelessOreRecipe(ItemBattleSuit.newArmorPiece(ModItems.battlesuit_boots, 1, true),
				upgradeToken, redstone, ItemBattleSuit.newArmorPiece(ModItems.battlesuit_boots, 0, true)));
		GameRegistry.addRecipe(new ShapelessOreRecipe(ItemBattleSuit.newArmorPiece(ModItems.battlesuit_legs, 1, true),
				upgradeToken, lapis, ItemBattleSuit.newArmorPiece(ModItems.battlesuit_legs, 0, true)));
		GameRegistry.addRecipe(new ShapelessOreRecipe(ItemBattleSuit.newArmorPiece(ModItems.battlesuit_helm, 1, true),
				upgradeToken, spiderEye, ItemBattleSuit.newArmorPiece(ModItems.battlesuit_helm, 0, true)));

		// quibitCluster conversion remove when old code is deleted
		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(ModBlocks.quibitCluster, 1, 0),
				new ItemStack(ModBlocks.quibitCluster_1)));
		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(ModBlocks.quibitCluster, 1, 1),
				new ItemStack(ModBlocks.quibitCluster_2)));
		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(ModBlocks.quibitCluster, 1, 2),
				new ItemStack(ModBlocks.quibitCluster_3)));
		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(ModBlocks.quibitCluster, 1, 3),
				new ItemStack(ModBlocks.quibitCluster_4)));
		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(ModBlocks.quibitCluster, 1, 4),
				new ItemStack(ModBlocks.quibitCluster_5)));

	}

}
