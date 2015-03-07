package jotato.quantumflux.tileentity;

import cofh.api.energy.EnergyStorage;
import cofh.api.energy.IEnergyHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import jotato.quantumflux.ConfigMan;
import jotato.quantumflux.core.IWirelessCapable;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;

public abstract class TileEntityQuibitCluster extends TileEntity implements IWirelessCapable, IEnergyHandler
{
	protected EnergyStorage localEnergyStorage;
	private int transferRate;
	private int capacity;
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
		// todo: anything other than this ugly hack!

		if (level == 5)
			this.transferRate = Integer.MAX_VALUE;
		localEnergyStorage = new EnergyStorage(this.capacity, this.transferRate);
	}

	protected EnergyStorage getEnergyDevice()
	{
		return localEnergyStorage;
	}

	@Override
	public boolean canConnectEnergy(ForgeDirection from)
	{
		return true;
	}

	public int getEnergyTransferRate()
	{
		return this.transferRate;
	}

	@Override
	public int receiveEnergy(ForgeDirection from, int maxReceive, boolean simulate)
	{

		int used = getEnergyDevice().receiveEnergy(maxReceive, simulate);
		if (used > 0 && !simulate)
		{
			this.markDirty();
		}
		return used;
	}

	@Override
	public int extractEnergy(ForgeDirection from, int maxExtract, boolean simulate)
	{
		int given = getEnergyDevice().extractEnergy(maxExtract, simulate);

		if (given > 0 && !simulate)
		{
			this.markDirty();
		}
		return given;
	}

	@Override
	public int getEnergyStored(ForgeDirection from)
	{
		return getEnergyDevice().getEnergyStored();
	}

	public void setEnergyStored(int energy)
	{
		this.markDirty();
		this.getEnergyDevice().setEnergyStored(energy);
	}

	@Override
	public int getMaxEnergyStored(ForgeDirection from)
	{
		return getEnergyDevice().getMaxEnergyStored();
	}

	@Override
	public void writeToNBT(NBTTagCompound tag)
	{
		super.writeToNBT(tag);

		NBTTagCompound energyTag = new NBTTagCompound();
		this.getEnergyDevice().writeToNBT(energyTag);
		tag.setTag("Energy", energyTag);
		tag.setInteger("XferRate", this.transferRate);
		tag.setInteger("Capacity", this.capacity);
		tag.setInteger("Level",this.level);

	}

	@Override
	public void readFromNBT(NBTTagCompound tag)
	{
		super.readFromNBT(tag);
		NBTTagCompound energyTag = tag.getCompoundTag("Energy");
		this.getEnergyDevice().readFromNBT(energyTag);
		this.level = tag.getInteger("Level");
		this.capacity = tag.getInteger("Capacity");
		this.transferRate = tag.getInteger("XferRate");
	}

	@SideOnly(Side.CLIENT)
	public int getBufferScaled(int scale)
	{
		double stored = getEnergyStored(null);
		double max = getMaxEnergyStored(null);
		double v= ((stored /max) *scale);
		return (int)v;
	}

	
	@Override
	public void markDirty()
	{
		super.markDirty();
		worldObj.markBlockForUpdate(this.xCoord, this.yCoord, this.zCoord);
	}
}
