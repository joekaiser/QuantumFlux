package jotato.quantumflux;

import jotato.quantumflux.registers.BlockRegister;
import jotato.quantumflux.registers.EventRegister;
import jotato.quantumflux.registers.ItemRegister;
import jotato.quantumflux.registers.RecipeRegister;
import net.minecraftforge.common.MinecraftForge;

public class ProxyCommon {

	public void preInit() {
		ItemRegister.init();
		BlockRegister.init();
	}

	public void init() {
		
	}

	public void postInit() {
		registerTickHandlers();
		new RecipeRegister().init();
	}

	public void registerKeyBindings() {

	}
	
	public void registerTickHandlers() {
		MinecraftForge.EVENT_BUS.register(new EventRegister());
	}

	

}
