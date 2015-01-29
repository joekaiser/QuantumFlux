package jotato.quantumflux.tileentity;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;

public class TileEntityIncinerator extends TileEntity implements IInventory
{
    private ItemStack[] fuelStack = new ItemStack[1];

    public int outputRate;
    public boolean isActive;
    public int maxStorage;
    

    public TileEntityIncinerator()
    {
        //todo: use config for values
        this.outputRate = 10;
        this.maxStorage = 100000;
        this.isActive = false;

    }

    @Override
    public int getSizeInventory()
    {
        return fuelStack.length;
    }

    @Override
    public ItemStack getStackInSlot(int slot)
    {
        return fuelStack[slot];
    }

    @Override
    public ItemStack decrStackSize(int slot, int size)
    {
        if (fuelStack.length > slot && fuelStack[slot] != null)
        {
            ItemStack itemstack;
            if (fuelStack[slot].stackSize <= size)
            {
                itemstack = fuelStack[slot];
                fuelStack[slot] = null;
                markDirty();
                return itemstack;
            } else
            {
                itemstack = fuelStack[slot].splitStack(size);
                if (fuelStack[slot].stackSize == 0)
                    fuelStack[slot] = null;
                markDirty();
                return itemstack;
            }
        }
        return null;
    }

    @Override
    public ItemStack getStackInSlotOnClosing(int slot)
    {
        return null;
    }

    @Override
    public void setInventorySlotContents(int slot, ItemStack itemstack)
    {
        if (fuelStack.length > slot)
        {
            fuelStack[slot] = itemstack;
        }
    }

    @Override
    public String getInventoryName()
    {
       
        return null;
    }

    @Override
    public boolean hasCustomInventoryName()
    {
      
        return false;
    }

    @Override
    public int getInventoryStackLimit()
    {
        return 64;
    }

    @Override
    public boolean isUseableByPlayer(EntityPlayer p_70300_1_)
    {
        return true;
    }

    @Override
    public void openInventory()
    {
    }

    @Override
    public void closeInventory()
    {
    }

    @Override
    public boolean isItemValidForSlot(int slot, ItemStack itemstack)
    {
        return true;
    }

}
