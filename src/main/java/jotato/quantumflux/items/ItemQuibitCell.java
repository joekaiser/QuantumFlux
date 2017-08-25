package jotato.quantumflux.items;

import cofh.api.energy.IEnergyContainerItem;
import jotato.quantumflux.ConfigMan;
import jotato.quantumflux.Logger;
import jotato.quantumflux.redflux.RedfluxField;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;

public class ItemQuibitCell extends ItemBase {

	public ItemQuibitCell() {
		super("quibitCell");
		canRepair = false;
		setMaxDamage(0);
	}

	@Override
	public void initModel() {
		Logger.devLog("    Registering model for %s", getSimpleName());
		ModelLoader.setCustomModelResourceLocation(this, 0, new ModelResourceLocation(getRegistryName(), "inventory"));
		ModelLoader.setCustomModelResourceLocation(this, 1, new ModelResourceLocation(getRegistryName(), "inventory"));
	}

	@Override
	public boolean hasEffect(ItemStack item) {
		return isActivated(item);
	}

	private boolean isActivated(ItemStack item) {
		return item.getItemDamage() == 1;
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
		ItemStack itemStackIn = playerIn.getHeldItem(handIn);
		if (!worldIn.isRemote && playerIn.isSneaking()) {
			itemStackIn.setItemDamage(itemStackIn.getItemDamage() == 0 ? 1 : 0);
		}
		return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, itemStackIn);
	}

	@Override
	public void onUpdate(ItemStack item, World world, Entity entity, int itemSlot, boolean isSelected) {
		if (world.isRemote)
			return;
		if (!(entity instanceof EntityPlayer))
			return;
		if (!isActivated(item))
			return;

		EntityPlayer player = (EntityPlayer) entity;
		String owner = player.getGameProfile().getId().toString();
		for (ItemStack target : player.inventory.mainInventory) {
			if (target != null && target.getItem() instanceof IEnergyContainerItem) {
				int tosend = RedfluxField.requestEnergy(ConfigMan.quibitcell_output, true, owner);
				int needed = ((IEnergyContainerItem) target.getItem()).receiveEnergy(target, tosend, true);
				int willSend = needed;
				((IEnergyContainerItem) target.getItem()).receiveEnergy(target, willSend, false);
				RedfluxField.requestEnergy(needed, false, owner);
			}
		}

		for (ItemStack target : player.inventory.armorInventory) {
			if (target != null && target.getItem() instanceof IEnergyContainerItem) {
				int tosend = RedfluxField.requestEnergy(ConfigMan.quibitcell_output, true, owner);
				int needed = ((IEnergyContainerItem) target.getItem()).receiveEnergy(target, tosend, true);
				int willSend = needed;
				((IEnergyContainerItem) target.getItem()).receiveEnergy(target, willSend, false);
				RedfluxField.requestEnergy(needed, false, owner);
			}
		}
	}
}
