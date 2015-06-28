package jotato.quantumflux.inventory;

import jotato.quantumflux.packets.ClusterMessage;
import jotato.quantumflux.packets.PacketHandler;
import jotato.quantumflux.tileentity.TileEntityQuibitCluster;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;

public class ContainerQuibitCluster extends ContainerBase
{
	int lastInternalStorage;

	private TileEntityQuibitCluster cluster;
	private EntityPlayer player;

	public ContainerQuibitCluster(EntityPlayer player, TileEntityQuibitCluster cluster)
	{
		super(null);
		this.cluster = cluster;
		this.player = player;
		addPlayerInventorySlots(player.inventory);
	}

	@Override
	public void detectAndSendChanges()
	{
		super.detectAndSendChanges();
		try
		{
			if (this.lastInternalStorage != this.cluster.getEnergyStored(null))
			{
				ClusterMessage message = new ClusterMessage(cluster.xCoord, cluster.yCoord, cluster.zCoord, cluster.getEnergyStored(null));
				PacketHandler.net.sendTo(message, (EntityPlayerMP) player);
			}
		}
		catch(ClassCastException e)
		{
			//TODO: Fix this catch
			//For some reason, a rare error is thrown here (net.minecraft.client.entity.EntityClientPlayerMP cannot be cast to net.minecraft.entity.player.EntityPlayerMP)
		}

		this.lastInternalStorage = this.cluster.getEnergyStored(null);
	}



	@Override
	public boolean canInteractWith(EntityPlayer p_75145_1_)
	{
		return true;
	}

	@Override
	public ItemStack transferStackInSlot(EntityPlayer entityPlayer, int i)
	{
		return null;
	}
}
