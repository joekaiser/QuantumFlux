package jotato.quantumflux.helpers;

import net.minecraft.world.World;

/**
 * pull from CoFHLib since there is no 1.8.8 port yet
 * A basic time tracker class. Nothing surprising here.
 *
 * @author King Lemming
 *
 */
public class TimeTracker {
	
	public static final int TIME_PART = 32;
	public static final int TIME_PART_HALF = 16;
	public static final int TIME_PART_QUARTER = 8;
	public static final int TIME_PART_EIGHTH = 4;
	
	
	private long lastMark = Long.MIN_VALUE;
	

	public boolean hasDelayPassed(World world, int delay) {

		long currentTime = world.getTotalWorldTime();

		if (currentTime < lastMark) {
			lastMark = currentTime;
			return false;
		} else if (lastMark + delay <= currentTime) {
			lastMark = currentTime;
			return true;
		}
		return false;
	}

	public void markTime(World world) {

		lastMark = world.getTotalWorldTime();
	}
	public boolean hasTimePartPassed(World world, int part){
		return world.getTotalWorldTime() % part == 0;
	}

}