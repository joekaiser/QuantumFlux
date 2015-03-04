package jotato.quantumflux.tileentity;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import jotato.quantumflux.ConfigMan;
import jotato.quantumflux.redflux.IRedfluxExciter;
import jotato.quantumflux.redflux.RedfluxField;
import cofh.api.energy.EnergyStorage;
import cofh.api.energy.IEnergyProvider;
import cofh.api.energy.IEnergyReceiver;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;

public class TileEntityRFExciter extends TileEntity implements IEnergyProvider,
		IRedfluxExciter {

	public UUID owner;
	private EnergyStorage energy;

	public TileEntityRFExciter() {
		energy = new EnergyStorage(ConfigMan.rfExciter_buffer,
				ConfigMan.rfExciter_output);
	}

	@Override
	public String getOwner() {
		return owner == null ? null : owner.toString();
	}

	@Override
	public boolean canReceive() {
		return true;
	}

	@Override
	public boolean canSend() {
		return false;
	}

	@Override
	public boolean canConnectEnergy(ForgeDirection from) {
		return true;
	}

	@Override
	public int receiveEnergy(int energy, boolean simulate) {
		energy -= this.energy.receiveEnergy(energy, simulate);
		return energy;
	}

	@Override
	public int extractEnergy(ForgeDirection from, int maxExtract,
			boolean simulate) {

		return this.energy.extractEnergy(maxExtract, simulate);
	}

	@Override
	public int getEnergyStored(ForgeDirection from) {
		return this.energy.getEnergyStored();
	}

	@Override
	public int getMaxEnergyStored(ForgeDirection from) {
		return this.energy.getMaxEnergyStored();
	}

	@Override
	public void onChunkUnload() {
		super.onChunkUnload();
		deregisterWithField();
	}

	@Override
	public void invalidate() {
		super.invalidate();
		deregisterWithField();
	}

	@Override
	public void validate() {
		super.validate();
		registerWithField();
	}

	public void deregisterWithField() {
		if (worldObj.isRemote) {
			return;
		}
		RedfluxField.removeLink(this);
	}

	public void registerWithField() {
		if (worldObj.isRemote) {
			return;
		}
		RedfluxField.registerLink(this);
	}

	@Override
	public void writeToNBT(NBTTagCompound tag) {
		super.writeToNBT(tag);

		NBTTagCompound energyTag = new NBTTagCompound();
		this.energy.writeToNBT(energyTag);
		tag.setTag("Energy", energyTag);
		tag.setString("owner", owner.toString());
	}

	@Override
	public void readFromNBT(NBTTagCompound tag) {
		super.readFromNBT(tag);
		NBTTagCompound energyTag = tag.getCompoundTag("Energy");
		this.energy.readFromNBT(energyTag);
		this.owner = UUID.fromString(tag.getString("owner"));

		registerWithField();

	}

	@Override
	public void updateEntity() {
		super.updateEntity();

		for (ForgeDirection dir : ForgeDirection.VALID_DIRECTIONS) {
			int targetX = xCoord + dir.offsetX;
			int targetY = yCoord + dir.offsetY;
			int targetZ = zCoord + dir.offsetZ;

			TileEntity tile = worldObj.getTileEntity(targetX, targetY, targetZ);
			if (tile instanceof IEnergyReceiver) {
				int tosend = energy.extractEnergy(ConfigMan.rfExciter_output,
						true);
				int used = ((IEnergyReceiver) tile).receiveEnergy(
						dir.getOpposite(), tosend, false);
				energy.extractEnergy(used, false);
			}

		}
	}

}
