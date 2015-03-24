package jotato.quantumflux.items;


import cofh.api.energy.IEnergyContainerItem;
import jotato.quantumflux.ConfigMan;
import jotato.quantumflux.redflux.RedfluxField;
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

		EntityPlayer player = (EntityPlayer) entity;
		String owner = player.getGameProfile().getId().toString();
		for (ItemStack target : player.inventory.mainInventory)
		{
			if (target != null && target.getItem() instanceof IEnergyContainerItem)
			{
				int tosend = RedfluxField.requestEnergy(ConfigMan.quibitcell_output, true, owner);
				int used = ((IEnergyContainerItem) target.getItem()).receiveEnergy(target, tosend, false);
				RedfluxField.requestEnergy(used, false, owner);
			}
		}
		
		for (ItemStack target : player.inventory.armorInventory)
		{
			if (target != null && target.getItem() instanceof IEnergyContainerItem)
			{
				int tosend = RedfluxField.requestEnergy(ConfigMan.quibitcell_output, true, owner);
				int used = ((IEnergyContainerItem) target.getItem()).receiveEnergy(target, tosend, false);
				RedfluxField.requestEnergy(used, false, owner);
			}
		}

	}
}
