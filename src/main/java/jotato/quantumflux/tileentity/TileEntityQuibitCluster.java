package jotato.quantumflux.tileentity;

import cofh.api.energy.EnergyStorage;
import cofh.api.energy.IEnergyHandler;
import jotato.quantumflux.ConfigMan;
import jotato.quantumflux.core.IWirelessCapable;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;

public abstract class TileEntityQuibitCluster extends TileEntity implements IWirelessCapable, IEnergyHandler
{
    protected EnergyStorage localEnergyStorage;
    private int transferRate;
    private int capacity;

    public TileEntityQuibitCluster(int transferRate, int capacity, int level)
    {

        this.transferRate = transferRate;
        this.capacity = capacity;

        for (int i = 0; i < level; i++)
        {
            this.transferRate *= ConfigMan.quibitCluster_multiplier;
            this.capacity *= ConfigMan.quibitCluster_multiplier;
        }
        localEnergyStorage = new EnergyStorage(capacity, transferRate);
    }

    protected EnergyStorage getEnergyDevice()
    {
        return localEnergyStorage; // todo: update to use the redstoneflux field
    }

    @Override
    public boolean canConnectEnergy(ForgeDirection from)
    {
        return true;
    }

    @Override
    public int receiveEnergy(ForgeDirection from, int maxReceive, boolean simulate)
    {
        return getEnergyDevice().receiveEnergy(maxReceive, simulate);
    }

    @Override
    public int extractEnergy(ForgeDirection from, int maxExtract, boolean simulate)
    {
        return getEnergyDevice().extractEnergy(maxExtract, simulate);
    }

    @Override
    public int getEnergyStored(ForgeDirection from)
    {
        return getEnergyDevice().getEnergyStored();
    }

    @Override
    public int getMaxEnergyStored(ForgeDirection from)
    {
        return getEnergyDevice().getMaxEnergyStored();
    }
}
