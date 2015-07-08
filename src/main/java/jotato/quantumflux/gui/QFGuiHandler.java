package jotato.quantumflux.gui;

import jotato.quantumflux.machine.cluster.ContainerQuibitCluster;
import jotato.quantumflux.machine.cluster.GuiQuibitCluster;
import jotato.quantumflux.machine.cluster.TileEntityQuibitCluster;
import jotato.quantumflux.machine.entropyaccelerator.ContainerEntropyAccelerator;
import jotato.quantumflux.machine.entropyaccelerator.GuiEntropyAccelerator;
import jotato.quantumflux.machine.entropyaccelerator.TileEntityEntropyAccelerator;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import cpw.mods.fml.common.network.IGuiHandler;

public class QFGuiHandler implements IGuiHandler
{

    public enum GUI
    {
        INCINERATOR(0), QUIBIT_CLUSTER_1(1), QUIBIT_CLUSTER_2(2), QUIBIT_CLUSTER_3(3), QUIBIT_CLUSTER_4(4), QUIBIT_CLUSTER_5(5), ;

        public int ordinal;

        private GUI(int id)
        {
            this.ordinal = id;
        }

        private static GUI[] allValues = values();

        public static GUI fromOrdinal(int n)
        {
            return allValues[n];
        }
    }

    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
    {
        GUI gui = GUI.fromOrdinal(ID);

        if (gui == GUI.INCINERATOR)
        {
            TileEntityEntropyAccelerator incinerator = (TileEntityEntropyAccelerator) world.getTileEntity(x, y, z);
            return new ContainerEntropyAccelerator(player.inventory, incinerator);
        }
        if (gui == GUI.QUIBIT_CLUSTER_1)
        {
            TileEntityQuibitCluster cluster = (TileEntityQuibitCluster) world.getTileEntity(x, y, z);
            return new ContainerQuibitCluster(player, cluster);
        }
        if (gui == GUI.QUIBIT_CLUSTER_2)
        {
            TileEntityQuibitCluster cluster = (TileEntityQuibitCluster) world.getTileEntity(x, y, z);
            return new ContainerQuibitCluster(player, cluster);
        }
        if (gui == GUI.QUIBIT_CLUSTER_3)
        {
            TileEntityQuibitCluster cluster = (TileEntityQuibitCluster) world.getTileEntity(x, y, z);
            return new ContainerQuibitCluster(player, cluster);
        }
        if (gui == GUI.QUIBIT_CLUSTER_4)
        {
            TileEntityQuibitCluster cluster = (TileEntityQuibitCluster) world.getTileEntity(x, y, z);
            return new ContainerQuibitCluster(player, cluster);
        }
        if (gui == GUI.QUIBIT_CLUSTER_5)
        {
            TileEntityQuibitCluster cluster = (TileEntityQuibitCluster) world.getTileEntity(x, y, z);
            return new ContainerQuibitCluster(player, cluster);
        }
        return null;

    }

    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
    {
        GUI gui = GUI.fromOrdinal(ID);

        if (gui == GUI.INCINERATOR)
        {
            TileEntityEntropyAccelerator incinerator = (TileEntityEntropyAccelerator) world.getTileEntity(x, y, z);
            return new GuiEntropyAccelerator(player.inventory, incinerator);
        }
        
        if (gui == GUI.QUIBIT_CLUSTER_1)
        {
            TileEntityQuibitCluster cluster = (TileEntityQuibitCluster) world.getTileEntity(x, y, z);
            return new GuiQuibitCluster(player, cluster);
        }
        if (gui == GUI.QUIBIT_CLUSTER_2)
        {
            TileEntityQuibitCluster cluster = (TileEntityQuibitCluster) world.getTileEntity(x, y, z);
            return new GuiQuibitCluster(player, cluster);
        }
        if (gui == GUI.QUIBIT_CLUSTER_3)
        {
            TileEntityQuibitCluster cluster = (TileEntityQuibitCluster) world.getTileEntity(x, y, z);
            return new GuiQuibitCluster(player, cluster);
        }
        if (gui == GUI.QUIBIT_CLUSTER_4)
        {
            TileEntityQuibitCluster cluster = (TileEntityQuibitCluster) world.getTileEntity(x, y, z);
            return new GuiQuibitCluster(player, cluster);
        }
        if (gui == GUI.QUIBIT_CLUSTER_5)
        {
            TileEntityQuibitCluster cluster = (TileEntityQuibitCluster) world.getTileEntity(x, y, z);
            return new GuiQuibitCluster(player, cluster);
        }

        return null;
    }

}
