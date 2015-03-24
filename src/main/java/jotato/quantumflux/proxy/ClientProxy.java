package jotato.quantumflux.proxy;

import jotato.quantumflux.blocks.BlockEntropyAccelerator;
import jotato.quantumflux.blocks.BlockMolecularInfuser;
import jotato.quantumflux.blocks.BlockRFEntangler;
import jotato.quantumflux.blocks.BlockRFExciter;
import jotato.quantumflux.render.RenderEntangler;
import jotato.quantumflux.render.RenderEntropyAccelerator;
import jotato.quantumflux.render.RenderExciter;
import jotato.quantumflux.render.RenderMolecularInfuser;
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
		
        BlockRFExciter.renderType = RenderingRegistry.getNextAvailableRenderId();
        RenderingRegistry.registerBlockHandler(new RenderExciter());
        
        BlockMolecularInfuser.renderType = RenderingRegistry.getNextAvailableRenderId();
        RenderingRegistry.registerBlockHandler(new RenderMolecularInfuser());
		
	}

}
