package jotato.quantumflux.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class QFBlocks
{
    public static Block entropyAccelerator;
    public static Block quibitCluster_1;
    public static Block quibitCluster_2;
    public static Block quibitCluster_3;
    public static Block quibitCluster_4;
    public static Block quibitCluster_5;
    public static Block zpe;
    public static Block rfEntangler;
    public static Block rfExciter;
    public static Block rfExciterL2;
    public static Block energyCore_off;
    public static Block energyCore_on;
public static Block molecularInfuser;

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
        //rfExciterL2 = new BlockRFExciterL2();
        molecularInfuser = new BlockMolecularInfuser();
        energyCore_off = new BlockBase(Material.portal,"energyCore_off",1,"pickaxe",0).setCreativeTab(null);
        energyCore_on = new BlockBase(Material.portal,"energyCore_on",1,"pickaxe",0).setCreativeTab(null);
    }
}
