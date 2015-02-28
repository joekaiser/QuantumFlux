package jotato.quantumflux.tileentity;

import cofh.api.energy.EnergyStorage;
import cofh.api.energy.IEnergyHandler;
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

public abstract class TileEntityQuibitCluster extends TileEntity implements IWirelessCapable, IEnergyHandler, IInventory
{
    protected EnergyStorage localEnergyStorage;
    private int transferRate;
    private int capacity;
    private ItemStack inventoryStack;
    public int level;

    public TileEntityQuibitCluster(int transferRate, int capacity, int level)
    {

        this.transferRate = transferRate;
        this.capacity = capacity;
        this.level = level;
        for (int i = 1; i < level; i++)
        {
            this.transferRate *= ConfigMan.quibitCluster_multiplier;
            this.capacity *= ConfigMan.quibitCluster_multiplier;
        }
        //todo: anything other than this ugly hack!
        
        if(level==5)
            this.transferRate = Integer.MAX_VALUE;
        localEnergyStorage = new EnergyStorage(this.capacity, this.transferRate);
    }

    protected EnergyStorage getEnergyDevice()
    {
        return localEnergyStorage; // todo: update to use the redstoneflux field
    }

    @Override
    public boolean canConnectEnergy(ForgeDirection from)
    {
        return true;
    }
    
    public int getEnergyTransferRate(){
        return this.transferRate;
    }

    @Override
    public int receiveEnergy(ForgeDirection from, int maxReceive, boolean simulate)
    {
        return getEnergyDevice().receiveEnergy(maxReceive, simulate);
    }

    @Override
    public int extractEnergy(ForgeDirection from, int maxExtract, boolean simulate)
    {
        return getEnergyDevice().extractEnergy(maxExtract, simulate);
    }

    @Override
    public int getEnergyStored(ForgeDirection from)
    {
        return getEnergyDevice().getEnergyStored();
    }

    public void setEnergyStored(int energy)
    {
        this.getEnergyDevice().setEnergyStored(energy);
    }

    @Override
    public int getMaxEnergyStored(ForgeDirection from)
    {
        return getEnergyDevice().getMaxEnergyStored();
    }

    @Override
    public int getSizeInventory()
    {
        return 1;
    }

    @Override
    public ItemStack getStackInSlot(int slot)
    {
        return inventoryStack;
    }

    @Override
    public ItemStack decrStackSize(int slot, int size)
    {
        if (inventoryStack != null)
        {
            ItemStack itemstack;
            if (inventoryStack.stackSize <= size)
            {
                itemstack = inventoryStack;
                inventoryStack = null;
                markDirty();
                return itemstack;
            } else
            {
                itemstack = inventoryStack.splitStack(size);
                if (inventoryStack.stackSize == 0)
                    inventoryStack = null;
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

        inventoryStack = itemstack;

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
    public void writeToNBT(NBTTagCompound tag)
    {
        super.writeToNBT(tag);

        NBTTagCompound inventoryTag = new NBTTagCompound();
        if (this.inventoryStack != null)
        {
            this.inventoryStack.writeToNBT(inventoryTag);
        }
        tag.setTag("Items", inventoryTag);

        NBTTagCompound energyTag = new NBTTagCompound();
        this.getEnergyDevice().writeToNBT(energyTag);
        tag.setTag("Energy", energyTag);

    }

    @Override
    public void readFromNBT(NBTTagCompound tag)
    {
        super.readFromNBT(tag);
        NBTTagCompound fuelTag = tag.getCompoundTag("Items");
        NBTTagCompound energyTag = tag.getCompoundTag("Energy");

        this.inventoryStack = ItemStack.loadItemStackFromNBT(fuelTag);
        this.getEnergyDevice().readFromNBT(energyTag);
    }

    @SideOnly(Side.CLIENT)
    public int getBufferScaled(int scale)
    {
        return getEnergyStored(null) * scale / getMaxEnergyStored(null);
    }

}
