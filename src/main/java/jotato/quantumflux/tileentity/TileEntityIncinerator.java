package jotato.quantumflux.tileentity;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import jotato.quantumflux.ConfigurationManager;
import jotato.quantumflux.Logger;
import jotato.quantumflux.core.IActive;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;

public class TileEntityIncinerator extends TileEntity implements IInventory, IActive
{
    private ItemStack fuelStack;;
    private int currentBurnTime = 0;
    private int currentStorage = 0;

    public final int outputRate = ConfigurationManager.incinerator_output;
    public final int maxStorage = ConfigurationManager.incinerator_buffer;
    public final int maxBurnTime = ConfigurationManager.incinerator_burnTime;

    public TileEntityIncinerator()
    {

    }

    @Override
    public int getSizeInventory()
    {
        return 1;
    }

    @Override
    public ItemStack getStackInSlot(int slot)
    {
        return fuelStack;
    }

    @Override
    public ItemStack decrStackSize(int slot, int size)
    {
        if (fuelStack != null)
        {
            ItemStack itemstack;
            if (fuelStack.stackSize <= size)
            {
                itemstack = fuelStack;
                fuelStack = null;
                markDirty();
                return itemstack;
            } else
            {
                itemstack = fuelStack.splitStack(size);
                if (fuelStack.stackSize == 0)
                    fuelStack = null;
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

        fuelStack = itemstack;

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

    @Override
    public boolean isActive()
    {
        return hasFuel() && this.currentStorage < this.maxStorage;
    }

    @Override
    public void writeToNBT(NBTTagCompound tag)
    {
        super.writeToNBT(tag);
        tag.setShort("currentBurnTime", (short) this.currentBurnTime);
        tag.setShort("currentStorage", (short) this.currentStorage);

        NBTTagCompound tagCompound1 = new NBTTagCompound();
        if (this.fuelStack != null)
        {
            this.fuelStack.writeToNBT(tagCompound1);
        }

        tag.setTag("Items", tagCompound1);
    }

    @Override
    public void readFromNBT(NBTTagCompound tag)
    {
        super.readFromNBT(tag);
        NBTTagCompound tagCompound1 = tag.getCompoundTag("Items");

        this.fuelStack = ItemStack.loadItemStackFromNBT(tagCompound1);
        this.currentBurnTime = tag.getShort("currentBurnTime");
        this.currentStorage = tag.getShort("currentStorage");
    }

    @Override
    public void updateEntity()
    {
        super.updateEntity();

        if (!worldObj.isRemote)
        {
            if (isActive())
            {
                if (this.currentBurnTime == 0)
                {
                    this.fuelStack.stackSize--;
                    if (this.fuelStack.stackSize == 0)
                        this.fuelStack = null;
                }

                this.currentStorage = Math.min(this.currentStorage + this.outputRate, this.maxStorage);

                this.currentBurnTime++;
                if (this.currentBurnTime >= this.maxBurnTime)
                {
                    Logger.info(""+this.currentStorage);
                    this.currentBurnTime = 0;
                }
                this.markDirty();
            }
        }
    }

    private boolean hasFuel()
    {
        return this.fuelStack != null && this.fuelStack.stackSize > 0;
    }
    
    @SideOnly(Side.CLIENT)
    public int getBurnTimeRemainingScaled(int scale) {
        if (this.currentBurnTime == 0) {
            this.currentBurnTime = maxBurnTime;
        }

        return this.maxBurnTime * scale / this.currentBurnTime;
    }
}
