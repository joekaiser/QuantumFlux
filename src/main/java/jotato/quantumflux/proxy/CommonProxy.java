package jotato.quantumflux.proxy;

import jotato.quantumflux.QuantumFlux;
import jotato.quantumflux.gui.QFGuiHandler;
import cpw.mods.fml.common.network.NetworkRegistry;

public class CommonProxy {

	public void initCommon() {
	       NetworkRegistry.INSTANCE.registerGuiHandler(QuantumFlux.instance, new QFGuiHandler());
	}

	public void initClient() {

	}

	public void initServer() {

	}

}
