package jotato.quantumflux.tileentity;

import java.util.UUID;

import jotato.quantumflux.ConfigMan;
import jotato.quantumflux.RedfluxField;
import cofh.api.energy.EnergyStorage;
import cofh.api.energy.IEnergyReceiver;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;

public class TileEntityRFEntangler extends TileEntity implements IEnergyReceiver {

	public UUID owner;
	public EnergyStorage storage = new EnergyStorage(ConfigMan.redfluxField_buffer,Integer.MAX_VALUE);
	
	@Override
	public boolean canConnectEnergy(ForgeDirection from) {
		return true;
	}

	@Override
	public int receiveEnergy(ForgeDirection from, int maxReceive,
			boolean simulate) {
		return storage.receiveEnergy(maxReceive, simulate);
		
	}

	@Override
	public int getEnergyStored(ForgeDirection from) {
		return storage.getEnergyStored();
	}

	@Override
	public int getMaxEnergyStored(ForgeDirection from) {
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
        
        RedfluxField.setField(owner, storage);
    }
	

}
