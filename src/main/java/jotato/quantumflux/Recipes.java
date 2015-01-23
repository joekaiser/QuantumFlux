package jotato.quantumflux;

import jotato.quantumflux.items.QFItems;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

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

    public void init()
    {
        GameRegistry.addShapedRecipe(new ItemStack(QFItems.ironCasing, 2), " l ", "iii", 'l', lapis, 'i', ironIngot);
        GameRegistry.addShapedRecipe(new ItemStack(QFItems.goldCasing, 2), " r ", "ggg", 'r', redstone, 'g', goldIngot);
        GameRegistry.addShapedRecipe(new ItemStack(QFItems.amplificationCrystal), "q q", " d ", "sss", 'q', quartz, 'd', diamond, 's', stone);
    }
}
