package jotato.quantumflux.machines.exciter;

import jotato.quantumflux.ConfigMan;
import jotato.quantumflux.blocks.BlockBase;
import jotato.quantumflux.helpers.BlockHelpers;
import jotato.quantumflux.registers.ItemRegister;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockRFExciter extends BlockBase implements ITileEntityProvider {

	public BlockRFExciter() {
		super(Material.circuits, "rfExciter");
		setDefaultState(blockState.getBaseState().withProperty(BlockHelpers.FACING, EnumFacing.NORTH));
		setHardness(.5f);
	}

	@Override
	public void onBlockPlacedBy(World world, BlockPos pos, IBlockState state, EntityLivingBase placer,
			ItemStack stack) {

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

		return super.onBlockPlaced(worldIn, pos, facing, hitX, hitY, hitZ, meta, placer)
				.withProperty(BlockHelpers.FACING, facing.getOpposite());

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
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, BlockHelpers.FACING);
	}

	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		return new TileRFExciter();
	}

	@Override
	public boolean isOpaqueCube(IBlockState state) {
		return false;
	}

	@Override
	public boolean isFullCube(IBlockState state) {
		return false;
	}

	// @Override
	// public AxisAlignedBB getCollisionBoundingBox(IBlockState worldIn, World
	// pos, BlockPos state) {
	// return new AxisAlignedB
	// }

	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
		EnumFacing facing = state.getValue(BlockHelpers.FACING);

		if (facing == EnumFacing.UP) {
			return new AxisAlignedBB(.15, .85, .15, .85, 1, .85);
		}

		if (facing == EnumFacing.DOWN) {
			return new AxisAlignedBB(.15, 0, .15, .85, .15, .85);
		}

		if (facing == EnumFacing.NORTH) {
			return new AxisAlignedBB(.15, .15, 0, .85, .85, .15);
		}

		if (facing == EnumFacing.SOUTH) {
			return new AxisAlignedBB(.15, .15, .85, .85, .85, 1);
		}

		if (facing == EnumFacing.EAST) {
			return new AxisAlignedBB(.85, .15, .15, 1, .85, .85);
		}
		if (facing == EnumFacing.WEST) {
			return new AxisAlignedBB(0, .15, .15, .15, .85, .85);
		}
		
		return super.getBoundingBox(state, source, pos);
		
	}

	/**
	 * called when an exciterUpgrade is r-clicked on the block
	 *
	 * @return the number of upgrades applied
	 */
	public int addUpgrade(World world, BlockPos blockPos, int count) {
		TileEntity entity = world.getTileEntity(blockPos);
		if (entity instanceof TileRFExciter) {
			TileRFExciter exciter = (TileRFExciter) entity;
			int toApply = Math.min(count, ConfigMan.rfExciter_maxUpgrades - exciter.upgradeCount);

			exciter.upgradeCount += toApply;
			exciter.markDirty();
			return toApply;

		}
		return 0;
	}

	@Override
	public void breakBlock(World world, BlockPos pos, IBlockState state) {
		TileEntity entity = world.getTileEntity(pos);
		if (entity instanceof TileRFExciter) {
			TileRFExciter exciter = (TileRFExciter) entity;
			int upgrades = exciter.upgradeCount;

			for (int i = 0; i < upgrades; i++) {

				float f = world.rand.nextFloat() * 0.6F + 0.1F;
				float f1 = world.rand.nextFloat() * 0.6F + 0.1F;
				float f2 = world.rand.nextFloat() * 0.6F + 0.1F;
				float f3 = 0.025F;

				EntityItem entityitem = new EntityItem(world, pos.getX() + f, pos.getY() + f1, pos.getZ() + f2,
						new ItemStack(ItemRegister.exciterUpgrade, 1));

				entityitem.motionX = (float) world.rand.nextGaussian() * f3;
				entityitem.motionY = (float) world.rand.nextGaussian() * f3 + 0.1F;
				entityitem.motionZ = (float) world.rand.nextGaussian() * f3;
				world.spawnEntityInWorld(entityitem);
			}

		}
	}

	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn,
			EnumHand hand, ItemStack heldItem, EnumFacing side, float hitX, float hitY, float hitZ) {

		if (!worldIn.isRemote && playerIn.isSneaking()) {
			TileEntity entity = worldIn.getTileEntity(pos);
			if (entity instanceof TileRFExciter) {
				playerIn.addChatMessage(new TextComponentTranslation("chat.rfExciter.maxTransfer",
						((TileRFExciter) entity).getNetPower()));
			}
		}

		return true;
	}

}
