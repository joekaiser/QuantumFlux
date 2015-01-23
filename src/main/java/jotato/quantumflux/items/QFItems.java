package jotato.quantumflux.items;

import net.minecraftforge.oredict.OreDictionary;

public class QFItems
{
    public static ItemBase amplificationCrystal;
    public static ItemBase ironCasing;
    public static ItemBase goldCasing;
    public static ItemBase steelIngot;
    public static ItemBase steelDust;

    public static void init()
    {
        amplificationCrystal = new ItemBase("amplificationCrystal");
        ironCasing = new ItemBase("ironCasing");
        goldCasing = new ItemBase("goldCasing");
        steelIngot = new ItemBase("steelIngot");
        
        registerOreDictionary();
    }

    private static void registerOreDictionary()
    {
        OreDictionary.registerOre("ingotSteel", steelIngot);
    }
}
