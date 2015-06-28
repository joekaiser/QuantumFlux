package jotato.quantumflux.proxy;

import jotato.quantumflux.blocks.BlockRFExciter;
import jotato.quantumflux.render.RenderExciter;
import cpw.mods.fml.client.registry.RenderingRegistry;

public class ClientProxy extends CommonProxy
{
	public static int renderPass;

	@Override
	public void initClient()
	{
		BlockRFExciter.renderType = RenderingRegistry.getNextAvailableRenderId();
		RenderingRegistry.registerBlockHandler(new RenderExciter());

	}

}
