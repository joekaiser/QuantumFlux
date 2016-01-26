package jotato.quantumflux.machines.zpe;

import jotato.quantumflux.blocks.BlockBase;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockZeroPointExtractor extends BlockBase implements ITileEntityProvider{

	public BlockZeroPointExtractor() {
		super("zeroPointExtractor");
	}

	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		return new TileZeroPointExtractor();
	}
}
