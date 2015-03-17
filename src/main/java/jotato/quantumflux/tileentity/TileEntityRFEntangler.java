package jotato.quantumflux.tileentity;

import java.util.UUID;

import jotato.quantumflux.ConfigMan;
import jotato.quantumflux.redflux.IRedfluxExciter;
import jotato.quantumflux.redflux.RedfluxField;
import cofh.api.energy.EnergyStorage;
import cofh.api.energy.IEnergyReceiver;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;

public class TileEntityRFEntangler extends TileEntity implements IEnergyReceiver, IRedfluxExciter
{

	public UUID owner;

	private EnergyStorage storage = new EnergyStorage(ConfigMan.redfluxField_buffer, Integer.MAX_VALUE);

	@Override
	public boolean canConnectEnergy(ForgeDirection from)
	{
		return true;
	}

	@Override
	public int receiveEnergy(ForgeDirection from, int maxReceive, boolean simulate)
	{
		if (!simulate)
		{
			this.markDirty();
		}
		return storage.receiveEnergy(maxReceive, simulate);
	}

	@Override
	public int getEnergyStored(ForgeDirection from)
	{
		return storage.getEnergyStored();
	}

	@Override
	public int getMaxEnergyStored(ForgeDirection from)
	{
		return storage.getMaxEnergyStored();
	}

	@Override
	public void writeToNBT(NBTTagCompound tag)
	{
		super.writeToNBT(tag);

		NBTTagCompound energyTag = new NBTTagCompound();
		this.storage.writeToNBT(energyTag);
		tag.setTag("Energy", energyTag);
		tag.setString("owner", owner.toString());
	}

	@Override
	public void readFromNBT(NBTTagCompound tag)
	{
		super.readFromNBT(tag);
		NBTTagCompound energyTag = tag.getCompoundTag("Energy");
		this.storage.readFromNBT(energyTag);
		this.owner = UUID.fromString(tag.getString("owner"));

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
		return this.storage.extractEnergy(energy, simulate);
	}

	@Override
	public int receiveEnergy(int energy, boolean simulate)
	{
		// #3 no support for wireless receiving of energy. only sending
		return energy;
	}

	public void deregisterWithField()
	{
		if (worldObj != null && !worldObj.isRemote)
		{
			RedfluxField.removeLink(this);
		}
	}

	public void registerWithField()
	{
		if (worldObj != null && !worldObj.isRemote)
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
}
