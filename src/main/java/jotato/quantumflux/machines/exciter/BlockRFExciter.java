package jotato.quantumflux.machines.exciter;


import jotato.quantumflux.Logger;
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
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumWorldBlockLayer;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockRFExciter extends BlockBase implements ITileEntityProvider{
	
	public BlockRFExciter(){
		super("rfExciter");
		setDefaultState(blockState.getBaseState().withProperty(BlockHelpers.FACING, EnumFacing.NORTH));
		
	}
	
	@Override
	public void onBlockPlacedBy(World world, BlockPos pos, IBlockState state, EntityLivingBase placer,
			ItemStack stack) {
//		world.setBlockState(pos,
//				state.withProperty(BlockHelpers.FACING, EntityHelpers.getFacingFromEntity(pos, placer)), 2);
		
	
		
		if (placer instanceof EntityPlayer) {
			EntityPlayer player = (EntityPlayer) placer;
			if (world.getTileEntity(pos) instanceof TileRFExciter) {
				TileRFExciter te = (TileRFExciter) world.getTileEntity(pos);

				if (te.owner == null) {
					te.owner = player.getGameProfile().getId();
				}
			}
		}
	}
	
	@Override
	public IBlockState onBlockPlaced(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ,
			int meta, EntityLivingBase placer) {

		return super.onBlockPlaced(worldIn, pos, facing, hitX, hitY, hitZ, meta, placer).withProperty(BlockHelpers.FACING, facing.getOpposite());
		
		
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
		return new TileRFExciter();
	}
	
	  @SideOnly(Side.CLIENT)
	  public EnumWorldBlockLayer getBlockLayer()
	  {
	    return EnumWorldBlockLayer.SOLID;
	  }

	  @Override
	  public boolean isOpaqueCube() {
	    return false;
	  }

	  @Override
	  public boolean isFullCube() {
	    return false;
	  }

	  @Override
	  public int getRenderType() {
	    return 3;
	  }

	  @Override
	  public AxisAlignedBB getCollisionBoundingBox(World worldIn, BlockPos pos, IBlockState state)
	  {
	    return null;
	  }
	

}
