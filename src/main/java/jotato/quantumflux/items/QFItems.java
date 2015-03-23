package jotato.quantumflux.items;

import net.minecraft.item.Item;
import net.minecraftforge.oredict.OreDictionary;

public class QFItems
{
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
	public static Item magnet;
	public static Item battlesuit_helm;
	public static Item battlesuit_chest;
	public static Item battlesuit_legs;
	public static Item battlesuit_boots;

	public static void init()
	{
		amplificationCrystal = new ItemBase("amplificationCrystal");
		ironCasing = new ItemBase("ironCasing");
		goldCasing = new ItemBase("goldCasing");
		steelIngot = new ItemBase("steelIngot");
		quibitCrystal = new ItemBase("quibitCrystal");
		manganese = new ItemBase("manganese");
		mangalloy = new ItemBase("mangalloyIngot");
		zbq7 = new ItemBase("zbq7").setMaxStackSize(1);
		quibitCell = new ItemQuibitCell();
		energizedCrystal = new ItemBase("energizedCrystal");
		magnet = new ItemMagnet();
		
		battlesuit_helm = new ItemBattleSuit("battlesuit_helmet", 0);
		battlesuit_chest = new ItemBattleSuit("battlesuit_chestplate", 1);
		battlesuit_legs = new ItemBattleSuit("battlesuit_leggings", 2);
		battlesuit_boots = new ItemBattleSuit("battlesuit_boots", 3);
		
		registerOreDictionary();
	
	}

	private static void registerOreDictionary()
	{
		OreDictionary.registerOre("ingotSteel", steelIngot);
		OreDictionary.registerOre("dustManganese", manganese);
		OreDictionary.registerOre("ingotMangalloy", mangalloy);

	}
	
	
}
