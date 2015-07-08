package jotato.quantumflux.blocks;

import jotato.quantumflux.machine.cluster.BlockQuibitCluster;
import jotato.quantumflux.machine.entangler.BlockRFEntangler;
import jotato.quantumflux.machine.entropyaccelerator.BlockEntropyAccelerator;
import jotato.quantumflux.machine.exciter.BlockRFExciter;
import jotato.quantumflux.machine.imaginarytime.BlockImaginaryTime;
import jotato.quantumflux.machine.zpe.BlockZPE;
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
    //public static Block molecularInfuser;

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
        darkstoneLamp = new BlockBase(Material.iron,"darkstone_lamp",.5f,null).setLightLevel(2f);
        darkstone = new BlockDarkstone();
        darkstonePillar = new BlockBase(Material.iron,"darkstone_pillar",1,"pickaxe",0,null);
        darkstoneTile = new BlockBase(Material.iron,"darkstone_tile",1,"pickaxe",0,null);
        imaginaryTime = new BlockImaginaryTime();
        //molecularInfuser = new BlockMolecularInfuser();
       
    }
}
