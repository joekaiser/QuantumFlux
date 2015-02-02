package jotato.quantumflux.blocks;

import jotato.quantumflux.QuantumFlux;
import jotato.quantumflux.gui.QFGuiHandler.GUI;
import jotato.quantumflux.tileentity.TileEntityEntropyAccelerator;
import jotato.quantumflux.tileentity.TileEntityQuibitCluster_1;
import jotato.quantumflux.tileentity.TileEntityQuibitCluster_2;
import jotato.quantumflux.tileentity.TileEntityQuibitCluster_3;
import jotato.quantumflux.tileentity.TileEntityQuibitCluster_4;
import jotato.quantumflux.tileentity.TileEntityQuibitCluster_5;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

//todo: feel free to refactor this. It is UGLY and I just want to get it working right now
public class BlockQuibitCluster extends BlockContainerBase
{
    private int level;

    public BlockQuibitCluster(int level)
    {
        super(Material.iron, "quibitCluster_" + level, 1, "pickaxe", 0);
        setStepSound(soundTypeMetal);
        this.level = level;
    }

    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int p6, float p7, float p8, float p9)
    {
        switch (this.level) {
        case 1:
            player.openGui(QuantumFlux.instance, GUI.QUIBIT_CLUSTER_1.ordinal, world, x, y, z);
            break;
        case 2:
            player.openGui(QuantumFlux.instance, GUI.QUIBIT_CLUSTER_2.ordinal, world, x, y, z);
            break;
        case 3:
            player.openGui(QuantumFlux.instance, GUI.QUIBIT_CLUSTER_3.ordinal, world, x, y, z);
            break;
        case 4:
            player.openGui(QuantumFlux.instance, GUI.QUIBIT_CLUSTER_4.ordinal, world, x, y, z);
            break;
        case 5:
            player.openGui(QuantumFlux.instance, GUI.QUIBIT_CLUSTER_5.ordinal, world, x, y, z);
            break;
        }
        return true;
    }

    @Override
    public TileEntity createNewTileEntity(World world, int p1)
    {
        switch (this.level) {
        case 2:
            return new TileEntityQuibitCluster_2();
        case 3:
            return new TileEntityQuibitCluster_3();
        case 4:
            return new TileEntityQuibitCluster_4();
        case 5:
            return new TileEntityQuibitCluster_5();
        case 1:
        default:
            return new TileEntityQuibitCluster_1();
        }

    }

}
