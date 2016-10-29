package jotato.quantumflux.machines.telepad;

import jotato.quantumflux.Logger;
import jotato.quantumflux.blocks.TileBase;
import jotato.quantumflux.helpers.TimeTracker;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.SoundEvents;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.math.BlockPos;

public class TileTelepad extends TileBase
{
	public int targetDim;
	public BlockPos targetPos;
	private boolean isLinked;

	private static final String DIMKEY = "linked_dimension";
	private static final String BLOCKPOS = "blockPOS";
	private static final String LINKEDKEY = "isLinked";

	public TileTelepad()
	{
	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound tag)
	{
		tag = super.writeToNBT(tag);

		if (isLinked)
		{
			tag.setLong(BLOCKPOS, targetPos.toLong());
			tag.setInteger(DIMKEY, targetDim);
			tag.setBoolean(LINKEDKEY, isLinked);
		}
		return tag;

	}

	@Override
	public void readFromNBT(NBTTagCompound tag)
	{
		super.readFromNBT(tag);

		this.isLinked = tag.getBoolean(LINKEDKEY);
		if (isLinked)
		{
			this.targetDim = tag.getInteger(DIMKEY);
			this.targetPos = BlockPos.fromLong(tag.getLong(BLOCKPOS));
		}
	}

	public void linkTelepad(BlockPos targetPos, int dimension)
	{
		this.targetDim = dimension;
		this.targetPos = targetPos;
		this.isLinked = true;
	}

	public void teleportEntity(EntityLivingBase entity)
	{
		if (entity.timeUntilPortal > 0)
		{
			return;
		}

		if (targetPos != null && entity.dimension == targetDim)
		{
			Logger.devLog("Teleporting to %s", targetPos.toString());
			
			entity.playSound(SoundEvents.ENTITY_ENDERMEN_TELEPORT, 1, 1);
			entity.timeUntilPortal = 50;
			entity.setPositionAndUpdate(targetPos.getX() + .5, targetPos.getY() + .25, targetPos.getZ() + .5);

		}
	}

}
