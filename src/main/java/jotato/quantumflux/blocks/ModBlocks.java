package jotato.quantumflux.blocks;

import jotato.quantumflux.machine.cluster.BlockCreativeCluster;
import jotato.quantumflux.machine.cluster.BlockQuibitCluster;
import jotato.quantumflux.machine.cluster.BlockQuibitCluster_Depricated;
import jotato.quantumflux.machine.entangler.BlockRFEntangler;
import jotato.quantumflux.machine.entropyaccelerator.BlockEntropyAccelerator;
import jotato.quantumflux.machine.exciter.BlockRFExciter;
import jotato.quantumflux.machine.fabricator.BlockItemFabricator;
import jotato.quantumflux.machine.imaginarytime.BlockImaginaryTime;
import jotato.quantumflux.machine.zpe.BlockZPE;
import jotato.quantumflux.storehouse.BlockStorehouse;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraftforge.oredict.OreDictionary;

public class ModBlocks
{
    public static Block entropyAccelerator;
    public static Block quibitCluster_1;
    public static Block quibitCluster_2;
    public static Block quibitCluster_3;
    public static Block quibitCluster_4;
    public static Block quibitCluster_5;
    public static Block zpe;
    public static Block rfEntangler;
    public static BlockRFExciter rfExciter;
    public static Block darkstoneLamp;
    public static Block darkstone;
    public static Block darkstonePillar;
    public static Block darkstoneTile;
    public static Block darkstoneOrnate;
    public static Block imaginaryTime;
    public static Block itemFabricator;
    public static Block quibitCluster;
    public static Block titaniumOre;
    public static Block storehouse;
    public static Block creativeCluster;
    
    public static void init()
    {
        entropyAccelerator = new BlockEntropyAccelerator();
        quibitCluster_1 = new BlockQuibitCluster_Depricated(1);
        quibitCluster_2 = new BlockQuibitCluster_Depricated(2);
        quibitCluster_3 = new BlockQuibitCluster_Depricated(3);
        quibitCluster_4 = new BlockQuibitCluster_Depricated(4);
        quibitCluster_5 = new BlockQuibitCluster_Depricated(5);
        zpe = new BlockZPE();
        rfEntangler = new BlockRFEntangler();
        rfExciter = new BlockRFExciter();
        darkstoneLamp = new BlockBase(Material.rock,"darkstone_lamp",.5f,null).setLightLevel(2f);
        darkstone = new BlockDarkstone();
        darkstonePillar = new BlockBase(Material.rock,"darkstone_pillar",1,"pickaxe",0,null);
        darkstoneTile = new BlockBase(Material.rock,"darkstone_tile",1,"pickaxe",0,null);
        darkstoneOrnate = new BlockBase(Material.rock,"darkstone_ornate",1,"pickaxe",0,null);
        imaginaryTime = new BlockImaginaryTime();
        itemFabricator = new BlockItemFabricator();
        quibitCluster = new BlockQuibitCluster();
        titaniumOre = new BlockBase(Material.rock,"titaniumOre", 10f, "pickaxe",3,null);
        storehouse = new BlockStorehouse();
        creativeCluster = new BlockCreativeCluster();
        registerOreDictionary();
    }
    
    private static void registerOreDictionary(){
    	OreDictionary.registerOre("oreTitanium", titaniumOre);
    }
}
