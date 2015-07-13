package jotato.quantumflux.machine.cluster;

import jotato.quantumflux.inventory.ContainerBase;
import jotato.quantumflux.packets.EnergyStorageMessage;
import jotato.quantumflux.packets.PacketHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;

public class ContainerQuibitCluster_deprecated extends ContainerBase
{
	int lastInternalStorage;

	private TileEntityQuibitCluster_Deprecated cluster;
	private EntityPlayer player;

	public ContainerQuibitCluster_deprecated(EntityPlayer player, TileEntityQuibitCluster_Deprecated cluster)
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
				EnergyStorageMessage message = new EnergyStorageMessage(cluster.xCoord, cluster.yCoord, cluster.zCoord, cluster.getEnergyStored(null));
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
