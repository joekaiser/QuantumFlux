package jotato.quantumflux.machines.entangler;

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

public class BlockRFEntangler extends BlockBase implements ITileEntityProvider{

	public BlockRFEntangler(){
		super("rfEntangler");
		setDefaultState(blockState.getBaseState().withProperty(BlockHelpers.FACING, EnumFacing.NORTH));
	}

	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		return new TileRFEntangler();
	}
	
	@Override
	public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer,
			ItemStack stack) {
		super.onBlockPlacedBy(worldIn, pos, state, placer, stack);
		
		worldIn.setBlockState(pos,
				state.withProperty(BlockHelpers.FACING, EntityHelpers.getFacingFromEntity(pos, placer)), 2);
		
		if (placer instanceof EntityPlayer) {
			EntityPlayer player = (EntityPlayer) placer;
			
			if (worldIn.getTileEntity(pos) instanceof TileRFEntangler) {
				TileRFEntangler entangler = (TileRFEntangler) worldIn.getTileEntity(pos);
				
				if(entangler.owner == null){
					entangler.owner = player.getGameProfile().getId();
					entangler.registerWithField();
				}
			}
		}
		
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
	
	
	
}
