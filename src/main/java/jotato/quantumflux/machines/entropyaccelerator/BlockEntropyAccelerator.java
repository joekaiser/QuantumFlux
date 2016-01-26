package jotato.quantumflux.machines.entropyaccelerator;

import jotato.quantumflux.blocks.BlockBase;
import jotato.quantumflux.helpers.BlockHelpers;
import jotato.quantumflux.helpers.EntityHelpers;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;

public class BlockEntropyAccelerator extends BlockBase implements ITileEntityProvider {

	public BlockEntropyAccelerator() {
		super("entropyAccelerator");
		setDefaultState(blockState.getBaseState().withProperty(BlockHelpers.FACING, EnumFacing.NORTH));
	}

	@Override
	public void onBlockPlacedBy(World world, BlockPos pos, IBlockState state, EntityLivingBase placer,
			ItemStack stack) {
		world.setBlockState(pos,
				state.withProperty(BlockHelpers.FACING, EntityHelpers.getFacingFromEntity(pos, placer)), 2);
	}

	@Override
	public IBlockState getStateFromMeta(int meta) {
		return getDefaultState().withProperty(BlockHelpers.FACING, EnumFacing.getFront(meta & 7));
	}

	@Override
	public int getMetaFromState(IBlockState state) {
		return state.getValue(BlockHelpers.FACING).getIndex();
	}

	@Override
	protected BlockState createBlockState() {
		return new BlockState(this, BlockHelpers.FACING);
	}

	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		return new TileEntropyAccelerator();
	}

	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn,
			EnumFacing side, float hitX, float hitY, float hitZ) {
		
		if(worldIn.isRemote) return false;

		TileEntropyAccelerator te = (TileEntropyAccelerator) worldIn.getTileEntity(pos);

		BlockHelpers.BroadcastRFStored(playerIn, te);

		return true;
	}
}
