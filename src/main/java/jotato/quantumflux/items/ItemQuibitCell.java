package jotato.quantumflux.items;

import java.util.List;

import cofh.api.energy.IEnergyContainerItem;
import jotato.quantumflux.ConfigMan;
import jotato.quantumflux.redflux.IRedfluxExciter;
import jotato.quantumflux.redflux.RedfluxField;
import jotato.quantumflux.tileentity.TileEntityRFEntangler;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemQuibitCell extends ItemBase
{

	public ItemQuibitCell()
	{
		super("quibitCell");
		setMaxStackSize(1);
		canRepair = false;
		setMaxDamage(0);
	}

	@Override
	public boolean hasEffect(ItemStack item)
	{
		return isActivated(item);
	}

	private boolean isActivated(ItemStack item)
	{
		return item.getItemDamage() == 1;
	}

	@Override
	public ItemStack onItemRightClick(ItemStack item, World world, EntityPlayer player)
	{
		if (!world.isRemote && player.isSneaking())
		{
			item.setItemDamage(item.getItemDamage() == 0 ? 1 : 0);
		}
		return item;
	}

	@Override
	public void onUpdate(ItemStack item, World world, Entity entity, int p4, boolean p5)
	{
		if (world.isRemote)
			return;
		if (!(entity instanceof EntityPlayer))
			return;
		if (!isActivated(item))
			return;

		// todo: the redFlux field API does not allow for ItemStack at this
		// point
		// so instead of having power pushed to the quibitCell and using that
		// you have to pull power from the field manually. This needs to be
		// changed.
		EntityPlayer player = (EntityPlayer) entity;
		List<IRedfluxExciter> network = RedfluxField.getLinks(player.getGameProfile().getId().toString());
		
		if (network == null)
			return;
		
		IRedfluxExciter provider = null;

		for (IRedfluxExciter exciter : network)
		{
			// I want to rely on the canSend check, but just in case there are
			// more than 1 "providers" on the network
			// I only want to pull from the entangler. This should be changed
			// when items can be part of the network
			if (exciter.canSend() && exciter instanceof TileEntityRFEntangler)
			{
				provider = exciter;
				break;
			}
		}

		if (provider != null)
		{
			for (ItemStack target : player.inventory.mainInventory)
			{
				if (target != null && target.getItem() instanceof IEnergyContainerItem)
				{
					int tosend = provider.requestEnergy(ConfigMan.quibitcell_output, true);
					int used = ((IEnergyContainerItem) target.getItem()).receiveEnergy(target, tosend, false);
					provider.requestEnergy(used, false);
				}
			}
		}

	}
}
