package jotato.quantumflux.machine.zpe;

import java.util.List;


import cofh.api.energy.EnergyStorage;
import cofh.api.energy.IEnergyProvider;
import cofh.api.energy.IEnergyReceiver;
import jotato.quantumflux.ConfigMan;
import jotato.quantumflux.util.ModUtils;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;

public class TileEntityZeroPointExtractor extends TileEntity implements IEnergyProvider
{
	private EnergyStorage energy;

	public TileEntityZeroPointExtractor()
	{
		energy = new EnergyStorage(ConfigMan.zpe_maxPowerGen, Integer.MAX_VALUE);
	}

	@Override
	public void writeToNBT(NBTTagCompound tag)
	{
		super.writeToNBT(tag);

		NBTTagCompound energyTag = new NBTTagCompound();
		this.energy.writeToNBT(energyTag);
		tag.setTag("Energy", energyTag);

	}

	@Override
	public void readFromNBT(NBTTagCompound tag)
	{
		super.readFromNBT(tag);
		NBTTagCompound energyTag = tag.getCompoundTag("Energy");
		this.energy.readFromNBT(energyTag);
	}

	@SuppressWarnings("unchecked")
	@Override
	public void updateEntity()
	{
		super.updateEntity();

		if (worldObj.isRemote)
		{
			return;
		}

		this.energy.receiveEnergy(Math.max(ConfigMan.zpe_maxPowerGen - this.yCoord, 1), false);

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
					int tosend = energy.extractEnergy(ConfigMan.zpe_maxPowerGen, true);
					int used = ((IEnergyReceiver) tile).receiveEnergy(dir.getOpposite(), tosend, false);
					//TODO: need this? It doesn't really *need* state saved
					if (used > 0)
					{
						this.markDirty();
					}
					energy.extractEnergy(used, false);
				}

			}

		}
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
