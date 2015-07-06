package jotato.quantumflux.machine.exciter;

import jotato.quantumflux.blocks.ModBlocks;
import jotato.quantumflux.render.RenderBlockBase;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.world.IBlockAccess;

public class RenderExciter extends RenderBlockBase
{
	
	
	@Override
	public void renderInventoryBlock(Block block, int metadata, int modelId, RenderBlocks renderer)
	{
		renderer.setRenderBounds(.12, .15, .85, 0, .85, .15);
		super.renderInventoryBlock(block, metadata, modelId, renderer);
	}
	@Override
	public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer)
	{
		renderer.setRenderBoundsFromBlock(world.getBlock(x, y, z));
		renderer.renderStandardBlock(ModBlocks.rfExciter, x, y, z);

		return true;
	}

	@Override
	public int getRenderId()
	{
		return BlockRFExciter.renderType;
	}

}
