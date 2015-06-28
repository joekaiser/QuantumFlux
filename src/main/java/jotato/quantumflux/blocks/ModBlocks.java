package jotato.quantumflux.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

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
    public static Block imaginaryTime;


    public static void init()
    {
        entropyAccelerator = new BlockEntropyAccelerator();
        quibitCluster_1 = new BlockQuibitCluster(1);
        quibitCluster_2 = new BlockQuibitCluster(2);
        quibitCluster_3 = new BlockQuibitCluster(3);
        quibitCluster_4 = new BlockQuibitCluster(4);
        quibitCluster_5 = new BlockQuibitCluster(5);
        zpe = new BlockZPE();
        rfEntangler = new BlockRFEntangler();
        rfExciter = new BlockRFExciter();
        darkstoneLamp = new BlockBase(Material.iron,"darkstone_lamp",.5f).setLightLevel(20f);
        darkstone = new BlockDarkstone();
        darkstonePillar = new BlockBase(Material.iron,"darkstone_pillar",1,"pickaxe",0);
        darkstoneTile = new BlockBase(Material.iron,"darkstone_tile",1,"pickaxe",0);
        imaginaryTime = new BlockImaginaryTime();
       
    }
}
