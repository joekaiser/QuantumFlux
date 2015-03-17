package jotato.quantumflux.proxy;

import jotato.quantumflux.blocks.BlockEntropyAccelerator;
import jotato.quantumflux.blocks.BlockRFEntangler;
import jotato.quantumflux.render.RenderEntangler;
import jotato.quantumflux.render.RenderEntropyAccelerator;
import cpw.mods.fml.client.registry.RenderingRegistry;

public class ClientProxy extends CommonProxy
{
	public static int renderPass;

	@Override
	public void initClient()
	{
		BlockEntropyAccelerator.renderType = RenderingRegistry.getNextAvailableRenderId();
		RenderingRegistry.registerBlockHandler(new RenderEntropyAccelerator());
		
		BlockRFEntangler.renderType = RenderingRegistry.getNextAvailableRenderId();
		RenderingRegistry.registerBlockHandler(new RenderEntangler());
		
	}

}
