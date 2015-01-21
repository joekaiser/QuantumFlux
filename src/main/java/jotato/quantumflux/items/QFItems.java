package jotato.quantumflux.items;

public class QFItems {
	public static ItemBase amplificationCrystal;
	public static ItemBase ironCasing;
	public static ItemBase goldCasing;

	public static void init() {
		amplificationCrystal = new ItemBase("amplificationCrystal");
		ironCasing = new ItemBase("ironCasing");
		goldCasing = new ItemBase("goldCasing");
	}
}
