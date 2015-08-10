package jotato.quantumflux.machine.storehouse;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;

public class TileEntityStorehouse extends TileEntity implements IInventory {
	protected ItemStack[] inventory;
	public boolean isDirty;

	public TileEntityStorehouse() {
		inventory = new ItemStack[getSizeInventory()];
	}

	@Override
	public int getSizeInventory() {
		return 999;
	}

	@Override
	public ItemStack getStackInSlot(int slot) {
		if (inventory.length > slot) {
			return inventory[slot];
		}
		return null;
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
	public void setInventorySlotContents(int slot, ItemStack item) {
		if (inventory.length > slot) {
			inventory[slot] = item;
		}

	}

	@Override
	public String getInventoryName() {
		return "Storehouse";
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

	}

	@Override
	public void closeInventory() {

	}

	@Override
	public boolean isItemValidForSlot(int slot, ItemStack item) {
		return true;
	}

	@Override
	public void readFromNBT(NBTTagCompound nbtTag) {
		super.readFromNBT(nbtTag);

		int invSize = getSizeInventory();
		this.inventory = new ItemStack[invSize];

		NBTTagList nbttaglist = nbtTag.getTagList("Items", 10);

		for (int i = 0; i < nbttaglist.tagCount(); ++i) {
			NBTTagCompound nbttagcompound1 = nbttaglist.getCompoundTagAt(i);
			int j;
			if (nbttagcompound1.hasKey("Slot")) // Compatibility check
				j = nbttagcompound1.getByte("Slot") & 255;
			else
				j = nbttagcompound1.getInteger("SlotID");

			if (j >= 0 && j < this.inventory.length) {
				this.inventory[j] = ItemStack.loadItemStackFromNBT(nbttagcompound1);
			}
		}
	}

	@Override
	public void writeToNBT(NBTTagCompound nbtTag) {
		super.writeToNBT(nbtTag);

		NBTTagList nbttaglist = new NBTTagList();
		for (int i = 0; i < this.inventory.length; ++i) {
			if (this.inventory[i] != null) {
				NBTTagCompound nbttagcompound1 = new NBTTagCompound();
				nbttagcompound1.setInteger("SlotID", i);
				this.inventory[i].writeToNBT(nbttagcompound1);
				nbttaglist.appendTag(nbttagcompound1);
			}
		}
		nbtTag.setTag("Items", nbttaglist);

	}

	@Override
	public void markDirty() {
		isDirty = true;
		super.markDirty();
	}

}
