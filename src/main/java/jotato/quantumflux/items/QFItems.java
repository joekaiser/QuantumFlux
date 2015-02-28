package jotato.quantumflux.items;

import net.minecraftforge.oredict.OreDictionary;

public class QFItems
{
    public static ItemBase amplificationCrystal;
    public static ItemBase ironCasing;
    public static ItemBase goldCasing;
    public static ItemBase steelIngot;
    public static ItemBase manganese;
    public static ItemBase quibitCrystal;
    public static ItemBase mangalloy;
    public static ItemBase zbq7;

    public static void init()
    {
        amplificationCrystal = new ItemBase("amplificationCrystal");
        ironCasing = new ItemBase("ironCasing");
        goldCasing = new ItemBase("goldCasing");
        steelIngot = new ItemBase("steelIngot");
        quibitCrystal = new ItemBase("quibitCrystal");
        manganese = new ItemBase("manganese");
        mangalloy = new ItemBase("mangalloyIngot");
        zbq7 = new ItemBase("zbq7");
        registerOreDictionary();
    }

    private static void registerOreDictionary()
    {
        OreDictionary.registerOre("ingotSteel", steelIngot);
        OreDictionary.registerOre("dustManganese",manganese);
        OreDictionary.registerOre("ingotMangalloy",mangalloy);
        
    }
}
