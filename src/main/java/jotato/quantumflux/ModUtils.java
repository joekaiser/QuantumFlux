package jotato.quantumflux;

import java.util.List;

import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;

public final class ModUtils
{
	// todo: I can see this being useful if extract out to a "helper" class of
	// some sort
	@SuppressWarnings("rawtypes")
	public static List getEntitiesInRange(Class entityType, World world, int x, int y, int z, int distance)
	{
		return world.getEntitiesWithinAABB(entityType,
				AxisAlignedBB.getBoundingBox(x - distance, y - distance, z - distance, x + distance, y + distance, z + distance));
	}
}
