package jotato.quantumflux.render;

import jotato.quantumflux.blocks.BlockRFExciterL2;
import jotato.quantumflux.blocks.QFBlocks;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.world.IBlockAccess;

public class RenderExciterL2 extends RenderBlockBase
{
	
	
	@Override
	public void renderInventoryBlock(Block block, int metadata, int modelId, RenderBlocks renderer)
	{
		renderer.setRenderBounds(.15, 0, .15, .85, .12, .85);
		super.renderInventoryBlock(block, metadata, modelId, renderer);
	}
	@Override
	public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer)
	{
		renderer.setRenderBoundsFromBlock(world.getBlock(x, y, z));
		renderer.renderStandardBlock(QFBlocks.rfExciterL2, x, y, z);

		return true;
	}

	@Override
	public int getRenderId()
	{
		return BlockRFExciterL2.renderType;
	}

}
