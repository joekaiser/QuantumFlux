package jotato.quantumflux.render;


import jotato.quantumflux.blocks.BlockEntropyAccelerator;
import jotato.quantumflux.blocks.ModBlocks;
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
			renderer.renderMaxX = .83;
			renderer.renderMaxY = .83;
			renderer.renderMaxZ = .83;
			renderer.renderMinX = .18;
			renderer.renderMinY = .18;
			renderer.renderMinZ = .18;
			TileEntity entity = world.getTileEntity(x, y, z);
			if (entity instanceof TileEntityEntropyAccelerator)
			{
				if (((TileEntityEntropyAccelerator) entity).isBurning)
				{
					renderer.renderStandardBlock(ModBlocks.energyCore_on, x, y, z);
				} else
				{
					renderer.renderStandardBlock(ModBlocks.energyCore_off, x, y, z);
				}
			} else
			{
				renderer.renderStandardBlock(ModBlocks.energyCore_on, x, y, z);
			}
		} else
		{
			//render it slightly smaller to stop z-fighting
			renderer.renderMaxX = .99993;
			renderer.renderMaxY = .99993;
			renderer.renderMaxZ = .99993;
			renderer.renderMinX = .00006;
			renderer.renderMinY = .00006;
			renderer.renderMinZ = .00006;
			renderer.renderFromInside = false;
			renderer.renderStandardBlock(ModBlocks.entropyAccelerator, x, y, z);

			// adding this second pass renders the inside faces as well
			renderer.renderFromInside = true;
			renderer.renderStandardBlock(ModBlocks.entropyAccelerator, x, y, z);
		}
		return true;
	}


	@Override
	public int getRenderId()
	{
		return BlockEntropyAccelerator.renderType;
	}

}
