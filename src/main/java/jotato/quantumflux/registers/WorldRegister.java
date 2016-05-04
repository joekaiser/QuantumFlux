package jotato.quantumflux.registers;

import jotato.quantumflux.world.QuantumWorldGenerator;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.registry.GameRegistry;

public final class WorldRegister {

	public static void init(){
		GameRegistry.registerWorldGenerator(QuantumWorldGenerator.INSTANCE, 10);
        MinecraftForge.EVENT_BUS.register(QuantumWorldGenerator.INSTANCE);
	}
}
