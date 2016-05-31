package jotato.quantumflux.machines.telepad;

import java.util.Random;

import jotato.quantumflux.blocks.BlockBase;
import jotato.quantumflux.helpers.BlockHelpers;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockTelepad extends BlockBase implements ITileEntityProvider
{

	public BlockTelepad()
	{
		super(Material.IRON, "telepad", null, 1);
		setDefaultState(blockState.getBaseState().withProperty(BlockHelpers.FACING, EnumFacing.UP));

	}

	@Override
	public boolean isOpaqueCube(IBlockState state)
	{
		return false;
	}

	@Override
	public boolean isFullCube(IBlockState state)
	{
		return false;
	}

	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos)
	{

		return new AxisAlignedBB(0, 0, 0, 1, .1875, 1);

	}

	@Override
	public boolean doesSideBlockRendering(IBlockState state, IBlockAccess world, BlockPos pos, EnumFacing face)
	{
		return false;
	}

	@Override
	public IBlockState getStateFromMeta(int meta)
	{
		return getDefaultState().withProperty(BlockHelpers.FACING, EnumFacing.getFront(meta & 7));
	}

	@Override
	public int getMetaFromState(IBlockState state)
	{
		return state.getValue(BlockHelpers.FACING).getIndex();
	}

	@Override
	protected BlockStateContainer createBlockState()
	{
		return new BlockStateContainer(this, BlockHelpers.FACING);
	}

	@SideOnly(Side.CLIENT)
	public boolean shouldSideBeRendered(IBlockState blockState, IBlockAccess blockAccess, BlockPos pos, EnumFacing side)
	{
		return side == EnumFacing.UP ? true : (blockAccess.getBlockState(pos.offset(side)).getBlock() == this ? true : super
				.shouldSideBeRendered(blockState, blockAccess, pos, side));

	}

	@SideOnly(Side.CLIENT)
	public void randomDisplayTick(IBlockState state, World worldIn, BlockPos pos, Random rand)
	{

		if (isBlockPowered(worldIn, pos))
		{
			if (rand.nextDouble() < .6)
			{
				int j = rand.nextInt(2) * 2 - 1;
				int k = rand.nextInt(2) * 2 - 1;
				double d0 = (double) pos.getX() + 0.5D + 0.25D * (double) j;
				double d1 = (double) ((float) pos.getY() + rand.nextFloat());
				double d2 = (double) pos.getZ() + 0.5D + 0.25D * (double) k;
				double d3 = (double) (rand.nextFloat() * (float) j);
				double d4 = ((double) rand.nextFloat() - 0.5D) * 0.125D;
				double d5 = (double) (rand.nextFloat() * (float) k);
				worldIn.spawnParticle(EnumParticleTypes.PORTAL, d0, d1, d2, d3, d4, d5, new int[0]);
			}
		}
	}

	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta)
	{
		return new TileTelepad();
	}

	@Override
	public void onEntityCollidedWithBlock(World worldIn, BlockPos pos, IBlockState state, Entity entityIn)
	{

		if (isBlockPowered(worldIn, pos))
		{

			if (entityIn instanceof EntityLivingBase)
			{
				TileTelepad te = (TileTelepad) worldIn.getTileEntity(pos);
				te.teleportEntity((EntityLivingBase) entityIn);
			}

		}

	}

}
