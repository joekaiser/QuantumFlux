package jotato.quantumflux.machines.entangler;

import java.util.UUID;

import cofh.api.energy.EnergyStorage;
import cofh.api.energy.IEnergyReceiver;
import jotato.quantumflux.ConfigMan;
import jotato.quantumflux.Logger;
import jotato.quantumflux.redflux.IRedfluxProvider;
import jotato.quantumflux.redflux.RedfluxField;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;

public class TileRFEntangler extends TileEntity implements IEnergyReceiver, IRedfluxProvider, ITickable
{

	public UUID owner;
	private long lastIn;
	private long lastOut;
	
	public long reportedIn;
	public long reportedOut;

	private EnergyStorage storage = new EnergyStorage(ConfigMan.redfluxField_buffer, Integer.MAX_VALUE);

	@Override
	public boolean canConnectEnergy(EnumFacing from)
	{
		return true;
	}

	@Override
	public int receiveEnergy(EnumFacing from, int maxReceive, boolean simulate)
	{

		int taken = storage.receiveEnergy(maxReceive, simulate);

		if (!simulate)
		{
			if (!world.isRemote)
			{
				this.lastIn += taken;
			}
			this.markDirty();
		}

		return taken;
	}

	@Override
	public int getEnergyStored(EnumFacing from)
	{
		return storage.getEnergyStored();
	}

	@Override
	public int getMaxEnergyStored(EnumFacing from)
	{
		return storage.getMaxEnergyStored();
	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound tag)
	{
		super.writeToNBT(tag);

		NBTTagCompound energyTag = new NBTTagCompound();
		this.storage.writeToNBT(energyTag);
		tag.setTag("Energy", energyTag);

		if(owner != null)
		{
			tag.setString("owner", owner.toString());
		}

		return tag;
	}

	@Override
	public void readFromNBT(NBTTagCompound tag)
	{
		super.readFromNBT(tag);

		NBTTagCompound energyTag = tag.getCompoundTag("Energy");
		this.storage.readFromNBT(energyTag);

		try
		{
			this.owner = UUID.fromString(tag.getString("owner"));
		}
		catch (IllegalArgumentException ex)
		{
			if (!world.isRemote)
				Logger.error("HEY YOU! An RF Entangler at %d, %d, %d has no owner, please replace it.", getPos().getX(), getPos().getY(), getPos().getZ());
		}

		registerWithField();
	}

	@Override
	public boolean canSend()
	{
		return true;
	}

	@Override
	public boolean canReceive()
	{
		// #3 no support for wireless receiving of energy. only sending
		return false;
	}

	@Override
	public int requestEnergy(int energy, boolean simulate)
	{
		int given = this.storage.extractEnergy(energy, simulate);

		if (!simulate)
		{
			this.markDirty();
			if (!world.isRemote)
			{
				this.lastOut += given;
			}
		}

		return given;
	}

	@Override
	public int receiveEnergy(int energy, boolean simulate)
	{
		// #3 no support for wireless receiving of energy. only sending
		return energy;
	}

	public void deregisterWithField()
	{
		if (world != null && !world.isRemote)
		{
			RedfluxField.removeLink(this);
		}
	}

	public void registerWithField()
	{
		if (world != null && !world.isRemote)
		{
			RedfluxField.registerLink(this);
		}
	}

	@Override
	public String getOwner()
	{
		if (this.owner == null)
			return null;
		return this.owner.toString();
	}

	@Override
	public void onChunkUnload()
	{
		super.onChunkUnload();
		deregisterWithField();
	}

	@Override
	public void invalidate()
	{
		super.invalidate();
		deregisterWithField();
	}

	@Override
	public void validate()
	{
		super.validate();
		registerWithField();
	}

	@Override
	public void update()
	{
		reportedOut = lastOut;
		reportedIn = lastIn;
		
		lastIn = 0;
		lastOut = 0;
	}
}