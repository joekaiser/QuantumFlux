package jotato.quantumflux.tileentity;

import cofh.api.energy.EnergyStorage;
import cofh.api.energy.IEnergyProvider;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import jotato.quantumflux.ConfigMan;
import jotato.quantumflux.core.IWirelessCapable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;

public class TileEntityEntropyAccelerator extends TileEntity implements IInventory, IEnergyProvider, IWirelessCapable
{
    private ItemStack fuelStack;;
    private int currentBurnTime = 0;
    private EnergyStorage energy;

    public int maxBurnTime;

    public TileEntityEntropyAccelerator()
    {
        this.maxBurnTime = ConfigMan.incinerator_burnTime;
        energy = new EnergyStorage(ConfigMan.incinerator_buffer, Integer.MAX_VALUE, ConfigMan.incinerator_output);
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

    public boolean isActive()
    {
        return hasFuel() && this.energy.getEnergyStored() <= this.energy.getMaxEnergyStored();
    }

    @Override
    public void writeToNBT(NBTTagCompound tag)
    {
        super.writeToNBT(tag);
        tag.setShort("currentBurnTime", (short) this.currentBurnTime);

        NBTTagCompound fuelTag = new NBTTagCompound();
        if (this.fuelStack != null)
        {
            this.fuelStack.writeToNBT(fuelTag);
        }
        tag.setTag("Items", fuelTag);

        NBTTagCompound energyTag = new NBTTagCompound();
        this.energy.writeToNBT(energyTag);
        tag.setTag("Energy", energyTag);

    }

    @Override
    public void readFromNBT(NBTTagCompound tag)
    {
        super.readFromNBT(tag);
        NBTTagCompound fuelTag = tag.getCompoundTag("Items");
        NBTTagCompound energyTag = tag.getCompoundTag("Energy");

        this.fuelStack = ItemStack.loadItemStackFromNBT(fuelTag);
        this.currentBurnTime = tag.getShort("currentBurnTime");
        this.energy.readFromNBT(energyTag);
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

                this.energy.receiveEnergy(this.energy.getMaxExtract(), false);

                this.currentBurnTime++;
                if (this.currentBurnTime >= this.maxBurnTime)
                {
                    this.currentBurnTime = 0;
                }
                this.markDirty();
            }
        }
    }


    public void setEnergyStored(int value)
    {
        this.energy.setEnergyStored(value);
    }

    private boolean hasFuel()
    {
        return this.fuelStack != null && this.fuelStack.stackSize > 0;
    }

    @SideOnly(Side.CLIENT)
    public int getBufferScaled(int scale)
    {
        return getEnergyStored(null) * scale / getMaxEnergyStored(null);
    }

    @Override
    public boolean canConnectEnergy(ForgeDirection from)
    {
        return true;
    }

    @Override
    public int extractEnergy(ForgeDirection from, int maxExtract, boolean simulate)
    {
        return energy.extractEnergy(maxExtract, simulate);
    }

    @Override
    public int getEnergyStored(ForgeDirection from)
    {
        return energy.getEnergyStored();
    }

    @Override
    public int getMaxEnergyStored(ForgeDirection from)
    {
        return energy.getMaxEnergyStored();
    }
}
