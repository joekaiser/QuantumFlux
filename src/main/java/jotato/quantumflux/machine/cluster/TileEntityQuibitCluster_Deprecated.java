package jotato.quantumflux.machine.cluster;

import cofh.api.energy.EnergyStorage;
import cofh.api.energy.IEnergyHandler;
import cofh.api.energy.IEnergyReceiver;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import jotato.quantumflux.ConfigMan;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;

public abstract class TileEntityQuibitCluster_Deprecated extends TileEntity implements IEnergyHandler
{
	protected EnergyStorage localEnergyStorage;
	private int transferRate;
	private int capacity;
	public int level;

	public TileEntityQuibitCluster_Deprecated(int transferRate, int capacity, int level)
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
		tag.setInteger("Level", this.level);

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
		double v = ((stored / max) * scale);
		return (int) v;
	}

	@Override
	public void markDirty()
	{
		super.markDirty();
		worldObj.markBlockForUpdate(this.xCoord, this.yCoord, this.zCoord);
	}

	@Override
	public void updateEntity()
	{
		for (ForgeDirection dir : ForgeDirection.VALID_DIRECTIONS)
		{
			int targetX = xCoord + dir.offsetX;
			int targetY = yCoord + dir.offsetY;
			int targetZ = zCoord + dir.offsetZ;

			TileEntity tile = worldObj.getTileEntity(targetX, targetY, targetZ);
			// todo: make configurable sides for the cluster
			if (tile instanceof TileEntityQuibitCluster_Deprecated)
				return;
			if (tile instanceof IEnergyReceiver)
			{
				IEnergyReceiver receiver = (IEnergyReceiver) tile;

				if (receiver.canConnectEnergy(dir.getOpposite()))
				{
					int tosend = localEnergyStorage.extractEnergy(transferRate, true);
					int used = ((IEnergyReceiver) tile).receiveEnergy(dir.getOpposite(), tosend, false);
					if (used > 0)
					{
						this.markDirty();
					}
					localEnergyStorage.extractEnergy(used, false);
				}

			}

		}
		super.updateEntity();
	}
}
