package jotato.quantumflux.machine.cluster;

import cofh.api.energy.IEnergyProvider;
import cofh.api.energy.IEnergyReceiver;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;

public class TileEntityCreativeCluster extends TileEntity implements IEnergyProvider {
	public int transferRate=100000;

	public TileEntityCreativeCluster() {

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
	public void updateEntity() {
		for (ForgeDirection dir : ForgeDirection.VALID_DIRECTIONS) {
			int targetX = xCoord + dir.offsetX;
			int targetY = yCoord + dir.offsetY;
			int targetZ = zCoord + dir.offsetZ;

			TileEntity tile = worldObj.getTileEntity(targetX, targetY, targetZ);
			// todo: make configurable sides for the cluster
			if (tile instanceof IEnergyReceiver) {
				IEnergyReceiver receiver = (IEnergyReceiver) tile;

				if (receiver.canConnectEnergy(dir.getOpposite())) {
					receiver.receiveEnergy(dir.getOpposite(), transferRate, false);
				}

			}

		}
		super.updateEntity();
	}
	

	@Override
	public boolean canConnectEnergy(ForgeDirection from) {
		return true;
	}

	@Override
	public int extractEnergy(ForgeDirection from, int maxExtract, boolean simulate) {
		return maxExtract;
	}

	@Override
	public int getEnergyStored(ForgeDirection from) {
		return getMaxEnergyStored(from);
	}

	@Override
	public int getMaxEnergyStored(ForgeDirection from) {
		return 10000000;
	}
}
