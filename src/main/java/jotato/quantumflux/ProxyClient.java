package jotato.quantumflux;

import jotato.quantumflux.registers.BlockRegister;
import jotato.quantumflux.registers.ItemRegister;

public class ProxyClient extends ProxyCommon {

	@Override
	public void preInit() {
		super.preInit();
		registerRenders();
	}
	
	@Override
	public void init() {
		super.init();
	}
	
	public void registerRenders() {
		ItemRegister.registerRenders();
		BlockRegister.registerRenders();
	}
	
}
