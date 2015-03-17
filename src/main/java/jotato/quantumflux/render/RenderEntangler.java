package jotato.quantumflux.render;

import jotato.quantumflux.blocks.BlockRFEntangler;
import jotato.quantumflux.blocks.QFBlocks;
import jotato.quantumflux.proxy.ClientProxy;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.world.IBlockAccess;

public class RenderEntangler extends RenderBlockBase
{
	@Override
	public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer)
	{

		if (ClientProxy.renderPass == 0)
		{
			renderer.renderMaxX = .9;
			renderer.renderMaxY = .9;
			renderer.renderMaxZ = .9;
			renderer.renderMinX = .1;
			renderer.renderMinY = .1;
			renderer.renderMinZ = .1;

			renderer.renderStandardBlock(QFBlocks.energyCore_on, x, y, z);

		} else
		{
			// render it slightly smaller to stop z-fighting
			renderer.renderMaxX = .999975;
			renderer.renderMaxY = .999975;
			renderer.renderMaxZ = .999975;
			renderer.renderMinX = .000025;
			renderer.renderMinY = .000025;
			renderer.renderMinZ = .000025;
			renderer.renderFromInside = false;
			renderer.renderStandardBlock(QFBlocks.rfEntangler, x, y, z);

			// adding this second pass renders the inside faces as well
			renderer.renderFromInside = true;
			renderer.renderStandardBlock(QFBlocks.rfEntangler, x, y, z);

		}
		return true;
	}

	@Override
	public int getRenderId()
	{
		return BlockRFEntangler.renderType;
	}

}
