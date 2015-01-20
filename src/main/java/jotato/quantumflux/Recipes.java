package jotato.quantumflux;

import jotato.quantumflux.blocks.QFBlocks;
import jotato.quantumflux.items.QFItems;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public class Recipes
{
    ItemStack diamond = new ItemStack(Items.diamond);
    ItemStack quartz = new ItemStack(Items.quartz);
    ItemStack stone = new ItemStack(Blocks.stone);
    ItemStack enderPearl = new ItemStack(Items.ender_pearl);
    ItemStack goldIngot = new ItemStack(Items.gold_ingot);
    ItemStack ironIngot = new ItemStack(Items.iron_ingot);
    ItemStack ironBlock = new ItemStack(Blocks.iron_block);
    ItemStack amplificationCrystal = new ItemStack(QFItems.amplificationCrystal);

    public void init()
    {
        GameRegistry.addShapedRecipe(new ItemStack(QFItems.amplificationCrystal), "q q", " d ", "sss", 'q', quartz, 'd', diamond, 's', stone);
        GameRegistry.addShapedRecipe(new ItemStack(QFBlocks.molecularInfuser), "gcg", "g g", "gcg", 'c', amplificationCrystal, 'g', goldIngot);
    }
}
