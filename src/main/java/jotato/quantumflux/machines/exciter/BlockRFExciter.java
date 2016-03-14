package jotato.quantumflux.machines.exciter;

import jotato.quantumflux.ConfigMan;
import jotato.quantumflux.Logger;
import jotato.quantumflux.blocks.BlockBase;
import jotato.quantumflux.helpers.BlockHelpers;
import jotato.quantumflux.registers.ItemRegister;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumWorldBlockLayer;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockRFExciter extends BlockBase implements ITileEntityProvider {

	public BlockRFExciter() {
		super(Material.circuits,"rfExciter");
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
	protected BlockState createBlockState() {
		return new BlockState(this, BlockHelpers.FACING);
	}

	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		return new TileRFExciter();
	}

	@SideOnly(Side.CLIENT)
	public EnumWorldBlockLayer getBlockLayer() {
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
	public AxisAlignedBB getCollisionBoundingBox(World worldIn, BlockPos pos, IBlockState state) {
		return null;
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

				EntityItem entityitem = new EntityItem(world, (double) ((float) pos.getX() + f),
						(double) ((float) pos.getY() + f1), (double) ((float) pos.getZ() + f2),
						new ItemStack(ItemRegister.exciterUpgrade, 1));

				entityitem.motionX = (double) ((float) world.rand.nextGaussian() * f3);
				entityitem.motionY = (double) ((float) world.rand.nextGaussian() * f3 + 0.1F);
				entityitem.motionZ = (double) ((float) world.rand.nextGaussian() * f3);
				world.spawnEntityInWorld(entityitem);
			}

		}
	}
	
	@Override
	public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player,
			EnumFacing side, float hitX, float hitY, float hitZ) {
		if (!world.isRemote && player.isSneaking()) {
			TileEntity entity = world.getTileEntity(pos);
			if (entity instanceof TileRFExciter) {
				String used = String.format(StatCollector.translateToLocal("chat.rfExciter.maxTransfer"),
						((TileRFExciter) entity).getNetPower());
				player.addChatMessage(new ChatComponentText(EnumChatFormatting.LIGHT_PURPLE + used));
			}
		}

		return true;
	}

}
