package jotato.quantumflux.machines.cluster;

import cofh.api.energy.IEnergyProvider;
import cofh.api.energy.IEnergyReceiver;
import jotato.quantumflux.ConfigMan;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;

public class TileCreativeCluster extends TileEntity implements IEnergyProvider, ITickable {
	public int transferRate=100000;

	public TileCreativeCluster() {

	}

	@Override
	public void writeToNBT(NBTTagCompound tag) {
		super.writeToNBT(tag);

		tag.setInteger("XferRate", this.transferRate);

	}

	@Override
	public void readFromNBT(NBTTagCompound tag) {
		super.readFromNBT(tag);

		this.transferRate = tag.getInteger("XferRate");
	}

	@Override
	public void update() {
		if (worldObj.isRemote)
			return;

		for (EnumFacing dir : EnumFacing.values()) {
			BlockPos targetBlock = getPos().add(dir.getDirectionVec());

			TileEntity tile = worldObj.getTileEntity(targetBlock);

			if (tile instanceof IEnergyReceiver) {
				IEnergyReceiver receiver = (IEnergyReceiver) tile;

				if (receiver.canConnectEnergy(dir.getOpposite())) {
					receiver.receiveEnergy(dir.getOpposite(), transferRate, false);
				}

			}

		}
	}
	

	@Override
	public boolean canConnectEnergy(EnumFacing from) {
		return true;
	}

	@Override
	public int extractEnergy(EnumFacing from, int maxExtract, boolean simulate) {
		return maxExtract;
	}

	@Override
	public int getEnergyStored(EnumFacing from) {
		return getMaxEnergyStored(from);
	}

	@Override
	public int getMaxEnergyStored(EnumFacing from) {
		return 10000000;
	}
}