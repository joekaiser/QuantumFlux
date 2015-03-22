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
		registerOreDictionary();
	}

	private static void registerOreDictionary()
	{
		OreDictionary.registerOre("ingotSteel", steelIngot);
		OreDictionary.registerOre("dustManganese", manganese);
		OreDictionary.registerOre("ingotMangalloy", mangalloy);

	}
}
