package jotato.quantumflux.machines.zpe;

import cofh.api.energy.EnergyStorage;
import cofh.api.energy.IEnergyProvider;
import cofh.api.energy.IEnergyReceiver;
import jotato.quantumflux.ConfigMan;
import jotato.quantumflux.blocks.TileBase;
import jotato.quantumflux.helpers.BlockHelpers;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;

public class TileZeroPointExtractor extends TileBase implements IEnergyProvider, ITickable {

	private EnergyStorage energy;

	public TileZeroPointExtractor() {
		energy = new EnergyStorage(ConfigMan.zpe_maxPowerGen, Integer.MAX_VALUE);
	}

	@Override
	public void writeToNBT(NBTTagCompound tag) {
		super.writeToNBT(tag);

		NBTTagCompound energyTag = new NBTTagCompound();
		this.energy.writeToNBT(energyTag);
		tag.setTag("Energy", energyTag);

	}

	@Override
	public void readFromNBT(NBTTagCompound tag) {
		super.readFromNBT(tag);
		NBTTagCompound energyTag = tag.getCompoundTag("Energy");
		this.energy.readFromNBT(energyTag);
	}

	@SuppressWarnings("unchecked")
	@Override
	public void update() {

		if (worldObj.isRemote) {
			return;
		}

		this.energy.receiveEnergy(Math.max(ConfigMan.zpe_maxPowerGen - getPos().getY(), 1), false);

		for (EnumFacing dir : EnumFacing.values()) {
			int targetX = getPos().getX() + dir.getFrontOffsetX();
			int targetY = getPos().getY() + dir.getFrontOffsetY();
			int targetZ = getPos().getZ() + dir.getFrontOffsetZ();

			TileEntity tile = worldObj.getTileEntity(BlockHelpers.getBlockPosFromXYZ(targetX, targetY, targetZ));
			if (tile instanceof IEnergyReceiver) {
				IEnergyReceiver receiver = (IEnergyReceiver) tile;

				if (receiver.canConnectEnergy(dir.getOpposite())) {
					int tosend = energy.extractEnergy(ConfigMan.zpe_maxPowerGen, true);
					int used = ((IEnergyReceiver) tile).receiveEnergy(dir.getOpposite(), tosend, false);
					// TODO: need this? It doesn't really *need* state saved
					if (used > 0) {
						this.markDirty();
					}
					energy.extractEnergy(used, false);
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
		return energy.extractEnergy(maxExtract, simulate);
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