package jotato.quantumflux.machine.imaginarytime;


import jotato.quantumflux.ConfigMan;
import net.minecraft.block.Block;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;
import cofh.api.energy.EnergyStorage;
import cofh.api.energy.IEnergyReceiver;

public class TileEntityImaginaryTime extends TileEntity implements IEnergyReceiver
{
	protected EnergyStorage localEnergyStorage;
	
	public TileEntityImaginaryTime()
	{
		localEnergyStorage = new EnergyStorage(1000, ConfigMan.imaginaryTime_chargeRate);
	}
	
	@Override
	public boolean canConnectEnergy(ForgeDirection from)
	{
		return true;
	}

	@Override
	public int receiveEnergy(ForgeDirection from, int maxReceive, boolean simulate)
	{
		return localEnergyStorage.receiveEnergy(maxReceive, simulate);
	}

	@Override
	public int getEnergyStored(ForgeDirection from)
	{
		return localEnergyStorage.getEnergyStored();
	}

	@Override
	public int getMaxEnergyStored(ForgeDirection from)
	{
		return localEnergyStorage.getMaxEnergyStored();
	}

	@Override
	public void writeToNBT(NBTTagCompound tag)
	{
		super.writeToNBT(tag);

		NBTTagCompound energyTag = new NBTTagCompound();
		this.localEnergyStorage.writeToNBT(energyTag);
		tag.setTag("Energy", energyTag);
	}

	@Override
	public void readFromNBT(NBTTagCompound tag)
	{
		super.readFromNBT(tag);
		NBTTagCompound energyTag = tag.getCompoundTag("Energy");
		this.localEnergyStorage.readFromNBT(energyTag);
	}

	@Override
	public void updateEntity()
	{
		super.updateEntity();

		if (worldObj.isRemote)
		{
			return;
		}
		//for an example on how to stop upticking, see http://bit.ly/1H154cK
		if(getEnergyStored(ForgeDirection.UNKNOWN) >= ConfigMan.imaginaryTime_energyRequirement)
		{
			int x = this.xCoord;
			int y = this.yCoord;
			int z = this.zCoord;
			int range = ConfigMan.imaginaryTime_range+1;
			int passes = ConfigMan.imaginaryTime_tickIncrease;
			Block block;
			
			for(int x2=x-range;x2<x+range; x2++)
			{
				for(int z2=z-range;z2<z+range; z2++)
				{
					for(int y2=y-2;y2<y+2; y2++)
					{
						block = worldObj.getBlock(x2, y2, z2);
						if(block!=null)
						{
							if(block.getTickRandomly())
							{
								for(int pass=0;pass<passes;pass++)
								{
									block.updateTick(worldObj,x2, y2, z2,worldObj.rand);
								}
							}
							
							block=null;
						}
					}
				}
			}
			localEnergyStorage.extractEnergy(ConfigMan.imaginaryTime_energyRequirement, false);
		}
		
	}

}
