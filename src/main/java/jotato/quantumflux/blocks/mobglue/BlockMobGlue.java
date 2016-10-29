package jotato.quantumflux.blocks.mobglue;

import java.util.Random;

import jotato.quantumflux.Logger;
import jotato.quantumflux.blocks.BlockBase;
import jotato.quantumflux.helpers.BlockHelpers;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockMobGlue extends BlockBase
{

	public BlockMobGlue()
	{
		super(Material.SPONGE, "mobGlue", ItemBlockMobGlue.class, .2f);
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

		return new AxisAlignedBB(.1, 0, .1, .9, .0625, .9);

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

	@Override
	public void onEntityCollidedWithBlock(World worldIn, BlockPos pos, IBlockState state, Entity entityIn)
	{

		if(worldIn.rand.nextDouble() < .00016){ //on avg the block will break every 6000 ticks (roughly 5 minutes)
			Logger.devLog("glue trap should break");
 			worldIn.setBlockToAir(pos);
 			entityIn.playSound(SoundEvents.ENTITY_ITEMFRAME_BREAK, 1, 1);

		}
		
		double x = pos.getX() + .5;
		double y = pos.getY();
		double z = pos.getZ() + .5;

		entityIn.lastTickPosX = entityIn.prevPosX = entityIn.posX = x;
		entityIn.lastTickPosY = entityIn.prevPosY = entityIn.posY = y;
		entityIn.lastTickPosZ = entityIn.prevPosZ = entityIn.posZ = z;

		entityIn.setPosition(entityIn.posX, entityIn.posY, entityIn.posZ);

	}

	@Override
	public int quantityDropped(IBlockState state, int fortune, Random random)
	{
		return 0;
	}

	@Override
	public boolean canSilkHarvest(World world, BlockPos pos, IBlockState state, EntityPlayer player)
	{
		return false;
	}
	

}
