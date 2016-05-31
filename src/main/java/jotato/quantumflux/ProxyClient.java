package jotato.quantumflux;

import jotato.quantumflux.registers.BlockRegister;
import jotato.quantumflux.registers.ItemRegister;
import net.minecraftforge.client.model.obj.OBJLoader;

public class ProxyClient extends ProxyCommon {

	@Override
	public void preInit() {
		super.preInit();
		 OBJLoader.INSTANCE.addDomain(QuantumFluxMod.MODID);
		registerRenders();
	}
	
	@Override
	public void init() {
		super.init();
		KeyBindings.init();
	}
	
	public void registerRenders() {
		ItemRegister.registerRenders();
		BlockRegister.registerRenders();
	}
	
}
