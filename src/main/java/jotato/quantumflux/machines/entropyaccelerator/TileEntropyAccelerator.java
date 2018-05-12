package jotato.quantumflux.machines.entropyaccelerator;

import cofh.redstoneflux.api.IEnergyProvider;
import cofh.redstoneflux.api.IEnergyReceiver;
import cofh.redstoneflux.impl.EnergyStorage;
import jotato.quantumflux.ConfigMan;
import jotato.quantumflux.blocks.TileSimpleInventory;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class TileEntropyAccelerator extends TileSimpleInventory implements IEnergyProvider, ITickable {

	public TileEntropyAccelerator() {
		super(1, "");
		maxBurnTime = ConfigMan.incinerator_burnTime;
		energy = new EnergyStorage(ConfigMan.incinerator_buffer, Integer.MAX_VALUE, ConfigMan.incinerator_output);
	}

	private int currentBurnTime = 0;
	private EnergyStorage energy;
	public int maxBurnTime;
	public boolean isBurning = false;

	public boolean isActive() {
		return (hasFuel() || isBurning) && this.energy.getEnergyStored() < this.energy.getMaxEnergyStored();

	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound tag) {
		tag = super.writeToNBT(tag);
		tag.setShort("currentBurnTime", (short) this.currentBurnTime);

		NBTTagCompound fuelTag = new NBTTagCompound();

		tag.setTag("Items", fuelTag);

		NBTTagCompound energyTag = new NBTTagCompound();
		this.energy.writeToNBT(energyTag);
		tag.setTag("Energy", energyTag);
		tag.setBoolean("Burning", isBurning);
		
		return tag;

	}

	@Override
	public void readFromNBT(NBTTagCompound tag) {
		super.readFromNBT(tag);
		NBTTagCompound energyTag = tag.getCompoundTag("Energy");

		this.currentBurnTime = tag.getShort("currentBurnTime");
		this.energy.readFromNBT(energyTag);
		this.isBurning = tag.getBoolean("Burning");
	}

	@Override
	public void update() {

		if (!world.isRemote) {
			if (isActive()) {

				if (this.currentBurnTime == 0) {
					isBurning = true;
					this.inventory[0].shrink(1);
					if (this.inventory[0].getCount() == 0)
						this.inventory[0] = null;
				}

				this.energy.receiveEnergy(ConfigMan.incinerator_output, false);

				this.currentBurnTime++;
				if (this.currentBurnTime >= this.maxBurnTime) {
					this.currentBurnTime = 0;
					isBurning = false;
				}
				this.markDirty();
			}

			for (EnumFacing dir : EnumFacing.values()) {
				BlockPos targetBlock = getPos().add(dir.getDirectionVec());

				TileEntity tile = world.getTileEntity(targetBlock);
				if (tile instanceof IEnergyReceiver) {
					IEnergyReceiver receiver = (IEnergyReceiver) tile;

					if (receiver.canConnectEnergy(dir.getOpposite())) {
						int tosend = energy.extractEnergy(ConfigMan.incinerator_output, true);
						int used = ((IEnergyReceiver) tile).receiveEnergy(dir.getOpposite(), tosend, false);
						if (used > 0) {
							this.markDirty();
						}
						energy.extractEnergy(used, false);
					}

				}

			}
		}
	}

	// energy stuff

	public void setEnergyStored(int value) {
		this.markDirty();
		this.energy.setEnergyStored(value);
	}

	private boolean hasFuel() {
		return this.inventory != null && this.inventory.length > 0 && 
				this.inventory[0] != null && this.inventory[0].getCount() > 0;
	}

	@SideOnly(Side.CLIENT)
	public int getBufferScaled(int scale) {
		return getEnergyStored(null) * scale / getMaxEnergyStored(null);
	}

	@Override
	public boolean canConnectEnergy(EnumFacing from) {
		return true;
	}

	@Override
	public int extractEnergy(EnumFacing from, int maxExtract, boolean simulate) {

		int toget = energy.extractEnergy(maxExtract, simulate);
		if (toget > 0 && !simulate) {
			this.markDirty();
		}
		return toget;
	}

	@Override
	public int getEnergyStored(EnumFacing from) {
		return energy.getEnergyStored();
	}

	@Override
	public int getMaxEnergyStored(EnumFacing from) {
		return energy.getMaxEnergyStored();
	}


}