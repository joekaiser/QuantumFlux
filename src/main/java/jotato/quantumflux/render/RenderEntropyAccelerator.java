package jotato.quantumflux.render;


import jotato.quantumflux.blocks.BlockEntropyAccelerator;
import jotato.quantumflux.blocks.QFBlocks;
import jotato.quantumflux.proxy.ClientProxy;
import jotato.quantumflux.tileentity.TileEntityEntropyAccelerator;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockAccess;

public class RenderEntropyAccelerator extends RenderBlockBase
{
	@Override
	public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer)
	{

		if (ClientProxy.renderPass == 0)
		{
			renderer.renderMaxX = .85;
			renderer.renderMaxY = .85;
			renderer.renderMaxZ = .85;
			renderer.renderMinX = .15;
			renderer.renderMinY = .15;
			renderer.renderMinZ = .15;
			TileEntity entity = world.getTileEntity(x, y, z);
			if (entity instanceof TileEntityEntropyAccelerator)
			{
				if (((TileEntityEntropyAccelerator) entity).isBurning)
				{
					renderer.renderStandardBlock(QFBlocks.energyCore_on, x, y, z);
				} else
				{
					renderer.renderStandardBlock(QFBlocks.energyCore_off, x, y, z);
				}
			} else
			{
				renderer.renderStandardBlock(QFBlocks.energyCore_on, x, y, z);
			}
		} else
		{
			//render it slightly smaller to stop z-fighting
			renderer.renderMaxX = .99996;
			renderer.renderMaxY = .99996;
			renderer.renderMaxZ = .99996;
			renderer.renderMinX = .00003;
			renderer.renderMinY = .00003;
			renderer.renderMinZ = .00003;
			renderer.renderFromInside = false;
			renderer.renderStandardBlock(QFBlocks.entropyAccelerator, x, y, z);

			// adding this second pass renders the inside faces as well
			renderer.renderFromInside = true;
			renderer.renderStandardBlock(QFBlocks.entropyAccelerator, x, y, z);
		}
		return true;
	}


	@Override
	public int getRenderId()
	{
		return BlockEntropyAccelerator.renderType;
	}

}
