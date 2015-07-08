package jotato.quantumflux.machine.entropyaccelerator;

import cofh.api.energy.EnergyStorage;
import cofh.api.energy.IEnergyProvider;
import cofh.api.energy.IEnergyReceiver;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import jotato.quantumflux.ConfigMan;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;

public class TileEntityEntropyAccelerator extends TileEntity implements IInventory, IEnergyProvider
{
	private ItemStack fuelStack;
	private int currentBurnTime = 0;
	private EnergyStorage energy;

	public int maxBurnTime;

	public boolean isBurning = false;

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
		return (hasFuel() || isBurning) && this.energy.getEnergyStored() < this.energy.getMaxEnergyStored();

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
		tag.setBoolean("Burning", isBurning);

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
		this.isBurning = tag.getBoolean("Burning");
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
					isBurning = true;
					this.fuelStack.stackSize--;
					if (this.fuelStack.stackSize == 0)
						this.fuelStack = null;
				}

				this.energy.receiveEnergy(ConfigMan.incinerator_output, false);

				this.currentBurnTime++;
				if (this.currentBurnTime >= this.maxBurnTime)
				{
					this.currentBurnTime = 0;
					isBurning = false;
				}
				this.markDirty();
			}


			for (ForgeDirection dir : ForgeDirection.VALID_DIRECTIONS)
			{
				int targetX = xCoord + dir.offsetX;
				int targetY = yCoord + dir.offsetY;
				int targetZ = zCoord + dir.offsetZ;

				TileEntity tile = worldObj.getTileEntity(targetX, targetY, targetZ);
				if (tile instanceof IEnergyReceiver)
				{
					IEnergyReceiver receiver = (IEnergyReceiver) tile;

					if (receiver.canConnectEnergy(dir.getOpposite()))
					{
						int tosend = energy.extractEnergy(ConfigMan.incinerator_output, true);
						int used = ((IEnergyReceiver) tile).receiveEnergy(dir.getOpposite(), tosend, false);
						if (used > 0)
						{
							this.markDirty();
						}
						energy.extractEnergy(used, false);
					}

				}

			}
		}
	}

	public void setEnergyStored(int value)
	{
		this.markDirty();
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

		int toget = energy.extractEnergy(maxExtract, simulate);
		if (toget > 0 && !simulate)
		{
			this.markDirty();
		}
		return toget;
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
