package jotato.quantumflux.util;

import java.util.List;

import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;

public final class ModUtils
{
	
	/**
	 * finds all entities of a given type within range of a point
	 * @param entityType
	 * @param world
	 * @param x
	 * @param y
	 * @param z
	 * @param distance
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public static List getEntitiesInRange(Class entityType, World world, int x, int y, int z, int distance)
	{
		return world.getEntitiesWithinAABB(entityType,
				AxisAlignedBB.getBoundingBox(x - distance, y - distance, z - distance, x + distance, y + distance, z + distance));
	}
}
