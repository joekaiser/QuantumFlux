package jotato.quantumflux.machines.imaginarytime;

import cofh.api.energy.EnergyStorage;
import cofh.api.energy.IEnergyReceiver;
import jotato.quantumflux.ConfigMan;
import jotato.quantumflux.blocks.TileBase;
import jotato.quantumflux.helpers.BlockHelpers;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.BlockPos;

public class TileImaginaryTime extends TileBase implements IEnergyReceiver, ITickable {
	protected EnergyStorage localEnergyStorage;

	public TileImaginaryTime() {
		localEnergyStorage = new EnergyStorage(1000, ConfigMan.imaginaryTime_chargeRate);
	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound tag) {
		tag = super.writeToNBT(tag);

		NBTTagCompound energyTag = new NBTTagCompound();
		this.localEnergyStorage.writeToNBT(energyTag);
		tag.setTag("Energy", energyTag);
		
		return tag;
	}

	@Override
	public void readFromNBT(NBTTagCompound tag) {
		super.readFromNBT(tag);
		NBTTagCompound energyTag = tag.getCompoundTag("Energy");
		this.localEnergyStorage.readFromNBT(energyTag);
	}

	@Override
	public void update() {

		if (worldObj.isRemote) {
			return;
		}
		// for an example on how to stop upticking, see http://bit.ly/1H154cK
		if (getEnergyStored(null) >= ConfigMan.imaginaryTime_energyRequirement) {
			int x = getPos().getX();
			int y = getPos().getY();
			int z = getPos().getZ();
			int range = ConfigMan.imaginaryTime_range + 1;
			Block block;

			for (int x2 = x - range; x2 <= x + range; x2++) {
				for (int z2 = z - range; z2 <= z + range; z2++) {
					for (int y2 = y - 2; y2 < y + 2; y2++) {

						BlockPos targetPos = BlockHelpers.getBlockPosFromXYZ(x2, y2, z2);
						IBlockState targetBlockState = worldObj.getBlockState(targetPos);
						block = targetBlockState.getBlock();
						if (block != null) {

							block.updateTick(worldObj, targetPos, targetBlockState, worldObj.rand);

							block = null;
						}
					}
				}
			}
			localEnergyStorage.extractEnergy(ConfigMan.imaginaryTime_energyRequirement, false);
		}

	}

	@Override
	public boolean canConnectEnergy(EnumFacing from) {
		return true;
	}

	@Override
	public int receiveEnergy(EnumFacing from, int maxReceive, boolean simulate) {
		return localEnergyStorage.receiveEnergy(maxReceive, simulate);
	}

	@Override
	public int getEnergyStored(EnumFacing from) {
		return localEnergyStorage.getEnergyStored();
	}

	@Override
	public int getMaxEnergyStored(EnumFacing from) {
		return localEnergyStorage.getMaxEnergyStored();
	}

}