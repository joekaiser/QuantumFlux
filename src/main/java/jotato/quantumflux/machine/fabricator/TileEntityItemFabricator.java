package jotato.quantumflux.machine.fabricator;

import cofh.api.energy.EnergyStorage;
import cofh.api.energy.IEnergyReceiver;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import jotato.quantumflux.machine.fabricator.ItemFabricatorRecipeManager.InfuserRecipe;
import jotato.quantumflux.redflux.ISetEnergy;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;

public class TileEntityItemFabricator extends TileEntity implements IEnergyReceiver, ISidedInventory, ISetEnergy {
	EnergyStorage energyStorage;
	private ItemStack[] inventory = new ItemStack[3];
	private static final int[] inputSlots = { 0, 1 };
	private static final int[] outputSlot = { 2 };
	private int energyNeeded = 5600;
	public int energyReserved = 0;
	private int energyConsumedPerTick = 20;
	private InfuserRecipe currentRecipe;

	public TileEntityItemFabricator() {
		energyStorage = new EnergyStorage(50000, 80);
	}

	@Override
	public boolean canConnectEnergy(ForgeDirection from) {
		return true;
	}

	@Override
	public int receiveEnergy(ForgeDirection from, int maxReceive, boolean simulate) {
		return energyStorage.receiveEnergy(maxReceive, simulate);
	}

	@Override
	public int getEnergyStored(ForgeDirection from) {
		return energyStorage.getEnergyStored();
	}

	@Override
	public int getMaxEnergyStored(ForgeDirection from) {
		return energyStorage.getMaxEnergyStored();
	}

	@Override
	public int getSizeInventory() {
		return inventory.length;
	}

	public void setEnergyStored(int amount) {
		energyStorage.setEnergyStored(amount);
	}

	@Override
	public ItemStack getStackInSlot(int slot) {
		return inventory[slot];
	}

	@Override
	public ItemStack decrStackSize(int slot, int size) {
		if (inventory.length > slot) {
			if (inventory[slot] != null) {
				ItemStack itemstack;
				if (inventory[slot].stackSize <= size) {
					itemstack = inventory[slot];
					inventory[slot] = null;
					markDirty();
					return itemstack;
				} else {
					itemstack = inventory[slot].splitStack(size);
					if (inventory[slot].stackSize == 0)
						inventory[slot] = null;
					markDirty();
					return itemstack;
				}
			}
		}
		return null;
	}

	@Override
	public ItemStack getStackInSlotOnClosing(int p_70304_1_) {
		return null;
	}

	@Override
	public void setInventorySlotContents(int slot, ItemStack itemstack) {
		if (inventory.length > slot) {
			inventory[slot] = itemstack;
		}
	}

	@Override
	public String getInventoryName() {
		return "Fabricator";
	}

	@Override
	public boolean hasCustomInventoryName() {
		return false;
	}

	@Override
	public int getInventoryStackLimit() {
		return 64;
	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer p_70300_1_) {
		return true;
	}

	@Override
	public void openInventory() {
		// TODO Auto-generated method stub

	}

	@Override
	public void closeInventory() {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean isItemValidForSlot(int slot, ItemStack item) {
		if (slot == outputSlot[0]) {
			return false;
		}
		return true;
	}

	@Override
	public int[] getAccessibleSlotsFromSide(int side) {

		if (side == 0 || side == 1) {
			return outputSlot;
		} else {
			return inputSlots;
		}
	}

	@Override
	public boolean canInsertItem(int slot, ItemStack item, int side) {
		return isItemValidForSlot(slot, item);
	}

	@Override
	public boolean canExtractItem(int slot, ItemStack item, int side) {
		return slot == outputSlot[0];
	}

	@Override
	public void updateEntity() {
		if (canDoWork()) {
			int energyUsed = energyStorage.extractEnergy(energyConsumedPerTick, false);
			if (energyReserved < energyNeeded) {
				energyReserved += energyUsed;
			}

			if (energyReserved >= energyNeeded) {

				if (canOutputItem(currentRecipe.getResult())) {
					decrStackSize(0, currentRecipe.getFirstInput().stackSize);
					decrStackSize(1, currentRecipe.getSecondInput().stackSize);
					if (getCurrentOutputStack() == null) {
						setInventorySlotContents(2, currentRecipe.getResult());
					} else {
						getCurrentOutputStack().stackSize += currentRecipe.getResult().stackSize;
					}
					currentRecipe = null;
					energyReserved = 0;
				}
			}
		}
		super.updateEntity();
	}

	private boolean canOutputItem(ItemStack item) {
		
		//if this doesn't match it means the user swapped in new items mid-processing
		if(!currentRecipe.matches(inventory[0],inventory[1])){
			return false;
		}
		
		if (getCurrentOutputStack() == null) {
			return true;
		}
		if (getCurrentOutputStack().getItem() != item.getItem()) {
			return false;
		}
		if (getCurrentOutputStack().stackSize + item.stackSize <= getCurrentOutputStack().getMaxStackSize()) {
			return true;
		}

		return false;
	}

	private boolean canDoWork() {

		if (inventory[0] == null || inventory[1] == null) {
			return false;
		}

		if (currentRecipe != null) {
			return true;
		}

		currentRecipe = ItemFabricatorRecipeManager.getRecipe(inventory[0], inventory[1]);
		if (currentRecipe != null) {
			return true;
		}
		
		return false;
	}

	private ItemStack getCurrentOutputStack() {
		return inventory[2];
	}

	@SideOnly(Side.CLIENT)
	public int getBufferScaled(int scale) {
		double stored = getEnergyStored(null);
		double max = getMaxEnergyStored(null);
		double v = ((stored / max) * scale);
		return (int) v;
	}

	@SideOnly(Side.CLIENT)
	public int getProgressScaled(int scale) {
		double done = energyReserved;
		double max = energyNeeded;
		double v = ((done / max) * scale);
		return (int) v;
	}

	// todo: NBT State
}
