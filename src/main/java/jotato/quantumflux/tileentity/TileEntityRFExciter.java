package jotato.quantumflux.tileentity;

import java.util.UUID;

import jotato.quantumflux.ConfigMan;
import jotato.quantumflux.redflux.RedfluxField;
import cofh.api.energy.IEnergyProvider;
import cofh.api.energy.IEnergyReceiver;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;

public class TileEntityRFExciter extends TileEntity implements IEnergyProvider
{
	public UUID owner;

	public TileEntityRFExciter()
	{
	}

	public String getOwner()
	{
		return owner == null ? null : owner.toString();
	}

	public boolean canReceive()
	{
		return true;
	}

	@Override
	public boolean canConnectEnergy(ForgeDirection from)
	{
		return true;
	}

	@Override
	public int extractEnergy(ForgeDirection from, int maxExtract, boolean simulate)
	{
		return RedfluxField.requestEnergy(maxExtract, simulate, this.getOwner());
	}

	@Override
	public int getEnergyStored(ForgeDirection from)
	{
		return 0; // todo: should this pull from the network?
	}

	@Override
	public int getMaxEnergyStored(ForgeDirection from)
	{
		return 0; // todo: should this pull from the network?
	}

	@Override
	public void onChunkUnload()
	{
		super.onChunkUnload();
	}

	@Override
	public void invalidate()
	{
		super.invalidate();
	}

	@Override
	public void validate()
	{
		super.validate();
	}

	@Override
	public void writeToNBT(NBTTagCompound tag)
	{
		super.writeToNBT(tag);
		tag.setString("owner", owner.toString());
	}

	@Override
	public void readFromNBT(NBTTagCompound tag)
	{
		super.readFromNBT(tag);
		this.owner = UUID.fromString(tag.getString("owner"));

	}

	@Override
	public void updateEntity()
	{
		super.updateEntity();

		if (worldObj.isRemote)
		{
			return;
		}

		for (ForgeDirection dir : ForgeDirection.VALID_DIRECTIONS)
		{
			int targetX = xCoord + dir.offsetX;
			int targetY = yCoord + dir.offsetY;
			int targetZ = zCoord + dir.offsetZ;

			TileEntity tile = worldObj.getTileEntity(targetX, targetY, targetZ);
			if (tile instanceof IEnergyReceiver)
			{
				int tosend = extractEnergy(null, ConfigMan.rfExciter_output, true);
				int used = ((IEnergyReceiver) tile).receiveEnergy(dir.getOpposite(), tosend, false);
				if (used > 0)
				{
					this.markDirty();
				}
				extractEnergy(null, used, false);
			}

		}
	}

}
