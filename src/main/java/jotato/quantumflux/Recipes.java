package jotato.quantumflux;

import jotato.quantumflux.blocks.QFBlocks;
import jotato.quantumflux.items.QFItems;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.ShapedOreRecipe;

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
    
    String steelIngot = "ingotSteel";

    public void init()
    {
        shapedRecipes();
        shapelessRecipes();
        furnaceRecipes();
    }

    private void shapedRecipes()
    {
        GameRegistry.addShapedRecipe(new ItemStack(QFItems.ironCasing, 2), " l ", "iii", 'l', lapis, 'i', ironIngot);
        GameRegistry.addShapedRecipe(new ItemStack(QFItems.goldCasing, 2), " r ", "ggg", 'r', redstone, 'g', goldIngot);
        GameRegistry.addShapedRecipe(new ItemStack(QFItems.amplificationCrystal), "q q", " d ", "sss", 'q', quartz, 'd', diamond, 's', stone);
        GameRegistry.addShapedRecipe(new ItemStack(QFItems.steelIngot), "c c", " i ", "c c", 'c', coal, 'i', ironIngot);
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(QFBlocks.entropyAccelerator), "ccc", "sfs", "sls", 'c', ironCasing, 'f', furnace, 'l', lavaBucket, 's', steelIngot));
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(QFItems.quibitCrystal,3), " r ", "qdq", " r ", 'r', redstone, 'q',quartz,'d',diamond));
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(QFBlocks.quibitCluster_1), "qcq", "cqc", "sss", 'q', quibitCrystal, 'c',goldCasing,'s',steelIngot));
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(QFBlocks.quibitCluster_2), "qqa", "qqa", 'q', quibitCluster_1, 'a',amplificationCrystal));
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(QFBlocks.quibitCluster_3), "qqa", "qqa", 'q', quibitCluster_2, 'a',amplificationCrystal));
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(QFBlocks.quibitCluster_4), "qqa", "qqa", 'q', quibitCluster_3, 'a',amplificationCrystal));
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(QFBlocks.quibitCluster_5), "qqa", "qqa", 'q', quibitCluster_4, 'a',amplificationCrystal));
        
    }

    private void furnaceRecipes()
    {

    }

    private void shapelessRecipes()
    {

    }

}
