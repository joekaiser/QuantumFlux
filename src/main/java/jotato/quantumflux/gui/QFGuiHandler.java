package jotato.quantumflux.gui;

import jotato.quantumflux.machine.cluster.ContainerQuibitCluster;
import jotato.quantumflux.machine.cluster.ContainerQuibitCluster_deprecated;
import jotato.quantumflux.machine.cluster.GuiQuibitCluster;
import jotato.quantumflux.machine.cluster.GuiQuibitCluster_deprecated;
import jotato.quantumflux.machine.cluster.TileEntityQuibitCluster;
import jotato.quantumflux.machine.cluster.TileEntityQuibitCluster_Deprecated;
import jotato.quantumflux.machine.entropyaccelerator.ContainerEntropyAccelerator;
import jotato.quantumflux.machine.entropyaccelerator.GuiEntropyAccelerator;
import jotato.quantumflux.machine.entropyaccelerator.TileEntityEntropyAccelerator;
import jotato.quantumflux.machine.fabricator.ContainerItemFabricator;
import jotato.quantumflux.machine.fabricator.GUIItemFabricator;
import jotato.quantumflux.machine.fabricator.TileEntityItemFabricator;
import jotato.quantumflux.storehouse.ContainerStorehouse;
import jotato.quantumflux.storehouse.GuiStorehouse;
import jotato.quantumflux.storehouse.TileEntityStorehouse;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import cpw.mods.fml.common.network.IGuiHandler;

public class QFGuiHandler implements IGuiHandler {

	public enum GUI {
		INCINERATOR(0), QUIBIT_CLUSTER_1(1), QUIBIT_CLUSTER_2(2), QUIBIT_CLUSTER_3(3), QUIBIT_CLUSTER_4(
				4), QUIBIT_CLUSTER_5(5), QUIBIT_CLUSTER(6), INFUSER(7), STOREHOUSE(8);

		public int ordinal;

		private GUI(int id) {
			this.ordinal = id;
		}

		private static GUI[] allValues = values();

		public static GUI fromOrdinal(int n) {
			return allValues[n];
		}
	}

	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		GUI gui = GUI.fromOrdinal(ID);
		// todo: once the old cluster is delted, remove these Q_C_N entries
		if (gui == GUI.INCINERATOR) {
			TileEntityEntropyAccelerator incinerator = (TileEntityEntropyAccelerator) world.getTileEntity(x, y, z);
			return new ContainerEntropyAccelerator(player.inventory, incinerator);
		}
		if (gui == GUI.QUIBIT_CLUSTER_1) {
			TileEntityQuibitCluster_Deprecated cluster = (TileEntityQuibitCluster_Deprecated) world.getTileEntity(x, y,
					z);
			return new ContainerQuibitCluster_deprecated(player, cluster);
		}
		if (gui == GUI.QUIBIT_CLUSTER_2) {
			TileEntityQuibitCluster_Deprecated cluster = (TileEntityQuibitCluster_Deprecated) world.getTileEntity(x, y,
					z);
			return new ContainerQuibitCluster_deprecated(player, cluster);
		}
		if (gui == GUI.QUIBIT_CLUSTER_3) {
			TileEntityQuibitCluster_Deprecated cluster = (TileEntityQuibitCluster_Deprecated) world.getTileEntity(x, y,
					z);
			return new ContainerQuibitCluster_deprecated(player, cluster);
		}
		if (gui == GUI.QUIBIT_CLUSTER_4) {
			TileEntityQuibitCluster_Deprecated cluster = (TileEntityQuibitCluster_Deprecated) world.getTileEntity(x, y,
					z);
			return new ContainerQuibitCluster_deprecated(player, cluster);
		}
		if (gui == GUI.QUIBIT_CLUSTER_5) {
			TileEntityQuibitCluster_Deprecated cluster = (TileEntityQuibitCluster_Deprecated) world.getTileEntity(x, y,
					z);
			return new ContainerQuibitCluster_deprecated(player, cluster);
		}
		if (gui == GUI.QUIBIT_CLUSTER) {
			TileEntityQuibitCluster cluster = (TileEntityQuibitCluster) world.getTileEntity(x, y, z);
			return new ContainerQuibitCluster(player, cluster);
		}
		if (gui == GUI.INFUSER) {
			TileEntityItemFabricator tile = (TileEntityItemFabricator) world.getTileEntity(x, y, z);
			return new ContainerItemFabricator(player, tile);
		}
		if (gui == GUI.STOREHOUSE) {
			TileEntityStorehouse tile = (TileEntityStorehouse) world.getTileEntity(x, y, z);
			return new ContainerStorehouse(player, tile);
		}
		return null;

	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		GUI gui = GUI.fromOrdinal(ID);

		if (gui == GUI.INCINERATOR) {
			TileEntityEntropyAccelerator incinerator = (TileEntityEntropyAccelerator) world.getTileEntity(x, y, z);
			return new GuiEntropyAccelerator(player.inventory, incinerator);
		}

		if (gui == GUI.QUIBIT_CLUSTER_1) {
			TileEntityQuibitCluster_Deprecated cluster = (TileEntityQuibitCluster_Deprecated) world.getTileEntity(x, y,
					z);
			return new GuiQuibitCluster_deprecated(player, cluster);
		}
		if (gui == GUI.QUIBIT_CLUSTER_2) {
			TileEntityQuibitCluster_Deprecated cluster = (TileEntityQuibitCluster_Deprecated) world.getTileEntity(x, y,
					z);
			return new GuiQuibitCluster_deprecated(player, cluster);
		}
		if (gui == GUI.QUIBIT_CLUSTER_3) {
			TileEntityQuibitCluster_Deprecated cluster = (TileEntityQuibitCluster_Deprecated) world.getTileEntity(x, y,
					z);
			return new GuiQuibitCluster_deprecated(player, cluster);
		}
		if (gui == GUI.QUIBIT_CLUSTER_4) {
			TileEntityQuibitCluster_Deprecated cluster = (TileEntityQuibitCluster_Deprecated) world.getTileEntity(x, y,
					z);
			return new GuiQuibitCluster_deprecated(player, cluster);
		}
		if (gui == GUI.QUIBIT_CLUSTER_5) {
			TileEntityQuibitCluster_Deprecated cluster = (TileEntityQuibitCluster_Deprecated) world.getTileEntity(x, y,
					z);
			return new GuiQuibitCluster_deprecated(player, cluster);
		}
		if (gui == GUI.QUIBIT_CLUSTER) {
			TileEntityQuibitCluster cluster = (TileEntityQuibitCluster) world.getTileEntity(x, y, z);
			return new GuiQuibitCluster(player, cluster);
		}
		if (gui == GUI.INFUSER) {
			TileEntityItemFabricator tile = (TileEntityItemFabricator) world.getTileEntity(x, y, z);
			return new GUIItemFabricator(player, tile);
		}
		if (gui == GUI.STOREHOUSE) {
			TileEntityStorehouse tile = (TileEntityStorehouse) world.getTileEntity(x, y, z);
			return new GuiStorehouse(player, tile);
		}

		return null;
	}

}
