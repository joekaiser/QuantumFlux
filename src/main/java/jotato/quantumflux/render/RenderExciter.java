package jotato.quantumflux.render;

import jotato.quantumflux.blocks.BlockRFExciter;
import jotato.quantumflux.blocks.QFBlocks;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.world.IBlockAccess;

public class RenderExciter extends RenderBlockBase
{
	
	
	@Override
	public void renderInventoryBlock(Block block, int metadata, int modelId, RenderBlocks renderer)
	{
		renderer.setRenderBounds(.15, .15, .88, .85, .85, 1);
		super.renderInventoryBlock(block, metadata, modelId, renderer);
	}
	@Override
	public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer)
	{
		renderer.setRenderBoundsFromBlock(world.getBlock(x, y, z));
		renderer.renderStandardBlock(QFBlocks.rfExciter, x, y, z);

		return true;
	}

	@Override
	public int getRenderId()
	{
		return BlockRFExciter.renderType;
	}

}
