package jotato.quantumflux.items;

import jotato.quantumflux.ConfigMan;
import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.oredict.OreDictionary;

public class ModItems
{
	public static ToolMaterial titaniumMaterial = EnumHelper.addToolMaterial("titaniumMaterial", 4, 3072, 11.5f, 6.5f, 24);
	public static ArmorMaterial titaniumArmor = EnumHelper.addArmorMaterial("titaniumArmor", 45, new int[]{3,9,7,3}, 30);
	
	public static Item amplificationCrystal;
	public static Item ironCasing;
	public static Item goldCasing;
	public static Item steelIngot;
	public static Item manganese;
	public static Item quibitCrystal;
	public static Item mangalloy;
	public static Item zbq7;
	public static Item quibitCell;
	public static Item energizedCrystal;
	public static Item glowingMangalloy;
	public static Item magnet;
	public static Item battlesuit_helm;
	public static Item battlesuit_chest;
	public static Item battlesuit_legs;
	public static Item battlesuit_boots;
	public static Item battlesuit_plate;
    public static Item eviscerator;
    public static Item eviscerator_rod;
    public static Item eviscerator_head;
	public static Item upgradeToken;
	public static Item crystalizedRedstone;
	public static Item exciterUpgrade;
	public static Item silica;
	public static Item blankCircuit;
	public static Item advancedCircuit;
	public static Item quantumDisk;
	public static Item titaniumIngot;
	public static Item mysticQuartz;
	public static Item harmonicOscillator;
	public static Item enderCrystal;
	public static Item titaniumPlate;
	public static Item titaniumSword;
	public static Item titanium_chest;
	public static Item titanium_legs;
	public static Item titanium_boots;
	public static Item titanium_helm;
	
	public static void init()
	{
		
		amplificationCrystal = new ItemBase("amplificationCrystal");
		ironCasing = new ItemBase("ironCasing");
		goldCasing = new ItemBase("goldCasing").setCreativeTab(null);
		if(ConfigMan.enableSteelRecipe)
		{
			steelIngot = new ItemBase("steelIngot");
		}
		quibitCrystal = new ItemBase("quibitCrystal");
		manganese = new ItemBase("manganese").setCreativeTab(null);
		mangalloy = new ItemBase("mangalloyIngot");
		zbq7 = new ItemBase("zbq7").setMaxStackSize(1);
		quibitCell = new ItemQuibitCell();
		energizedCrystal = new ItemBase("energizedCrystal");
		magnet = new ItemMagnet();
		glowingMangalloy = new ItemBase("glowingMangalloy");
        eviscerator = new ItemEviscerator("eviscerator");
        eviscerator_head = new ItemBase("eviscerator_head");
        eviscerator_rod = new ItemBase("eviscerator_rod");
		upgradeToken = new ItemBase("upgradeToken").setMaxStackSize(1);
		crystalizedRedstone = new ItemBase("redstoneCrystal");
		exciterUpgrade =new ItemExciterUpgrade();
		silica = new ItemBase("silica");
		blankCircuit = new ItemBase("blankCircuit");
		advancedCircuit = new ItemBase("advancedCircuit");
		quantumDisk = new ItemBase("quantumDisk");
		titaniumIngot = new ItemBase("ingotTitanium");
		mysticQuartz = new ItemBase("mysticQuartz");
		harmonicOscillator = new ItemBase("harmoicOscillator").setCreativeTab(null);
		enderCrystal = new ItemBase("enderCrystal");
		titaniumPlate = new ItemBase("titaniumPlate").setCreativeTab(null);
		titaniumSword =new ItemTitaniumSword("titaniumSword");
		
		battlesuit_plate = new ItemBase("battlesuit_plate");
		battlesuit_helm = new ItemBattleSuit("battlesuit_helmet", 0);
		battlesuit_chest = new ItemBattleSuit("battlesuit_chestplate", 1);
		battlesuit_legs = new ItemBattleSuit("battlesuit_leggings", 2);
		battlesuit_boots = new ItemBattleSuit("battlesuit_boots", 3);
		
		titanium_helm = new ItemTitaniumArmor("titanium_helmet", 0);
		titanium_chest = new ItemTitaniumArmor("titanium_chestplate", 1);
		titanium_legs = new ItemTitaniumArmor("titanium_leggings", 2);
		titanium_boots = new ItemTitaniumArmor("titanium_boots", 3);
		
		registerOreDictionary();
	
	}

	private static void registerOreDictionary()
	{
		if(ConfigMan.enableSteelRecipe)
		{
			OreDictionary.registerOre("ingotSteel", steelIngot);
		}

		OreDictionary.registerOre("ingotMangalloy", mangalloy);
		OreDictionary.registerOre("ingotTitanium", titaniumIngot);
	}
	
	
}
