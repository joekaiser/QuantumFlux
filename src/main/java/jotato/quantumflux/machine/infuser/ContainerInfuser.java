package jotato.quantumflux.machine.infuser;

import jotato.quantumflux.inventory.ContainerBase;
import jotato.quantumflux.packets.EnergyStorageMessage;
import jotato.quantumflux.packets.PacketHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.inventory.Slot;

public class ContainerInfuser extends ContainerBase {
	int lastInternalStorage;

	private TileEntityMolecularInfuser infuser;
	private EntityPlayer player;

	public ContainerInfuser(EntityPlayer player, TileEntityMolecularInfuser infuser)
	{
		super(null);
		this.infuser = infuser;
		this.player = player;
		addSlotToContainer(new Slot(infuser, 0, 54, 24));
		addSlotToContainer(new Slot(infuser, 1, 106, 24));
		addSlotToContainer(new Slot(infuser, 2, 80, 54));
		addPlayerInventorySlots(player.inventory);
	}

	@Override
	public void detectAndSendChanges()
	{
		super.detectAndSendChanges();
		try
		{
			if (this.lastInternalStorage != this.infuser.getEnergyStored(null))
			{
				EnergyStorageMessage message = new EnergyStorageMessage(infuser.xCoord, infuser.yCoord, infuser.zCoord, infuser.getEnergyStored(null));
				PacketHandler.net.sendTo(message, (EntityPlayerMP) player);
			}
		}
		catch(ClassCastException e)
		{
			//TODO: Fix this catch
			//For some reason, a rare error is thrown here (net.minecraft.client.entity.EntityClientPlayerMP cannot be cast to net.minecraft.entity.player.EntityPlayerMP)
		}

		this.lastInternalStorage = this.infuser.getEnergyStored(null);
	}
}
