package jotato.quantumflux;

import java.io.IOException;
import java.io.InputStream;

import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.IReloadableResourceManager;
import net.minecraft.client.resources.IResourceManager;
import net.minecraft.client.resources.IResourceManagerReloadListener;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StringTranslate;
import org.apache.commons.io.Charsets;
import org.apache.commons.io.IOUtils;

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
