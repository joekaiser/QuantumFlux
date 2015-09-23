package jotato.quantumflux.machine.exciter;

import java.util.UUID;

import jotato.quantumflux.ConfigMan;
import jotato.quantumflux.Logger;
import jotato.quantumflux.redflux.RedfluxField;
import cofh.api.energy.IEnergyProvider;
import cofh.api.energy.IEnergyReceiver;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;

public class TileEntityRFExciter extends TileEntity implements IEnergyProvider
{
	public UUID owner;
	public int lastEnergyUsed;
	public ForgeDirection targetDirection;
	public int maxOut;
	public int upgradeCount;
	public float wirelessEfficiency;

	public TileEntityRFExciter()
	{
		maxOut=ConfigMan.rfExciter1_output;
		wirelessEfficiency = ConfigMan.rfExciter_Efficiency;
	}

	public String getOwner()
	{
		return owner == null ? null : owner.toString();
	}

	public boolean canReceive()
	{
		return true;
	}

	@Override
	public boolean canConnectEnergy(ForgeDirection from)
	{
		return true;
	}

	@Override
	public int extractEnergy(ForgeDirection from, int maxExtract, boolean simulate)
	{
		return RedfluxField.requestEnergy(maxExtract, simulate, this.getOwner());
	}

	@Override
	public int getEnergyStored(ForgeDirection from)
	{
		return 0; // todo: should this pull from the network?
	}

	@Override
	public int getMaxEnergyStored(ForgeDirection from)
	{
		return 0; // todo: should this pull from the network?
	}

	@Override
	public void onChunkUnload()
	{
		super.onChunkUnload();
	}

	@Override
	public void invalidate()
	{
		super.invalidate();
	}

	@Override
	public void validate()
	{
		super.validate();
	}

	@Override
	public void writeToNBT(NBTTagCompound tag)
	{
		super.writeToNBT(tag);
		
		tag.setInteger("upgradeCount", upgradeCount);
		
		if(targetDirection!=null){
			tag.setInteger("direction",targetDirection.ordinal());
		}
		
		if(owner !=null)
		{
			tag.setString("owner", owner.toString());
		}
		
		
	}

	@Override
	public void readFromNBT(NBTTagCompound tag)
	{
		super.readFromNBT(tag);
		try
		{
			this.owner = UUID.fromString(tag.getString("owner"));
			this.targetDirection = ForgeDirection.getOrientation(tag.getInteger("direction"));
			this.upgradeCount = tag.getInteger("upgradeCount");
		}
		catch(Exception ex)
		{
			Logger.error("HEY YOU! There was an error when trying to load the exciter at %d %d %d. you will want to have the owner brake it and replace it. \r\n\r\n %s", xCoord, yCoord, zCoord, ex);
			
		}
	}

	@Override
	public void updateEntity()
	{
		super.updateEntity();

		if (worldObj.isRemote)
		{
			return;
		}
		
		if(targetDirection == null ||owner==null ){
			return;
		}

		int targetX = xCoord + targetDirection.offsetX;
		int targetY = yCoord + targetDirection.offsetY;
		int targetZ = zCoord + targetDirection.offsetZ;

		TileEntity tile = worldObj.getTileEntity(targetX, targetY, targetZ);
		if (tile instanceof IEnergyReceiver)
		{
		    int netPower = getNetPower();
		    
			int tosend = extractEnergy(null, netPower, true);
			int needed = ((IEnergyReceiver) tile).receiveEnergy(targetDirection.getOpposite(), tosend, true);
			int willSend = Math.round(needed * wirelessEfficiency);
			((IEnergyReceiver) tile).receiveEnergy(targetDirection.getOpposite(), willSend, false);
			
			if (needed > 0)
			{
				this.markDirty();
			}
			lastEnergyUsed = needed;
			extractEnergy(null, needed, false);
		}
	}
	
	public int getNetPower(){
	    int power = maxOut + upgradeCount * 100;
	    return power;
	}
}
