package jotato.quantumflux.machines.imaginarytime;

import jotato.quantumflux.blocks.BlockBase;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockImaginaryTime extends BlockBase implements ITileEntityProvider{

	public BlockImaginaryTime() {
		super("imaginaryTime");
	}
	
	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		return new TileImaginaryTime();
	}

}
