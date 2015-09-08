package jotato.quantumflux.machine.exciter;

import java.util.List;

import jotato.quantumflux.ConfigMan;
import jotato.quantumflux.blocks.BlockBase;
import jotato.quantumflux.items.ModItems;
import jotato.quantumflux.proxy.ClientProxy;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.StatCollector;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

public class BlockRFExciter extends BlockBase {
	public static int renderType;

	public BlockRFExciter() {
		super(Material.circuits, "rfExciter", .25f, "pickaxe", 0, ItemBlockRFExciter.class);
	}

	@Override
	protected String getTexture(String name) {
		// TODO Auto-generated method stub
		return super.getTexture("exciter/" + name);
	}

	@SuppressWarnings("rawtypes")
	@Override
	public void addCollisionBoxesToList(World world, int x, int y, int z, AxisAlignedBB mask, List list, Entity entity)
	{
		this.setBlockBoundsBasedOnState(world, x, y, z);
		super.addCollisionBoxesToList(world, x, y, z, mask, list, entity);
	}

	@Override
	public void setBlockBoundsBasedOnState(IBlockAccess world, int x, int y, int z) {
		float minx = 0, miny = 0, minz = 0, maxx = 1, maxy = 1, maxz = 1;

		switch (world.getBlockMetadata(x, y, z)) {
		case 1: // top (ypos)
			maxx = .85f;
			maxy = 1f;
			maxz = .85f;

			minx = .15f;
			miny = .88f;
			minz = .15f;
			break;
		case 2: // north (zneg)
			maxx = .85f;
			maxy = .85f;
			maxz = .12f;

			minx = .15f;
			miny = .15f;
			minz = 0f;
			break;
		case 3: // south (zpos)
			maxx = .85f;
			maxy = .85f;
			maxz = 1;

			minx = .15f;
			miny = .15f;
			minz = .88f;
			break;
		case 4: // west (xneg)
			maxx = .12f;
			maxy = .85f;
			maxz = .85f;

			minx = 0f;
			miny = .15f;
			minz = .15f;
			break;
		case 5: // east (xpos)
			maxx = 1f;
			maxy = .85f;
			maxz = .85f;

			minx = .88f;
			miny = .15f;
			minz = .15f;
			break;

		default: // bottom (yneg)
			maxx = .85f;
			maxy = .12f;
			maxz = .85f;

			minx = .15f;
			miny = 0f;
			minz = .15f;
			break;
		}
		setBlockBounds(minx, miny, minz, maxx, maxy, maxz);
	}

	@Override
	public boolean hasTileEntity(int metadata) {
		return true;
	}

	@Override
	public TileEntity createTileEntity(World world, int metadata) {
		return new TileEntityRFExciter();
	}

	@Override
	public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase entity, ItemStack itemStack) {
		super.onBlockPlacedBy(world, x, y, z, entity, itemStack);

		if (entity instanceof EntityPlayer) {
			EntityPlayer player = (EntityPlayer) entity;
			if (world.getTileEntity(x, y, z) instanceof TileEntityRFExciter) {
				TileEntityRFExciter te = (TileEntityRFExciter) world.getTileEntity(x, y, z);

				// todo: allow the owner to unlock and prevent other from
				// stealing the lock
				if (te.owner == null) {
					te.owner = player.getGameProfile().getId();
				}

				te.targetDirection = ForgeDirection.getOrientation(world.getBlockMetadata(x, y, z));
			}
		}

	}

	@Override
	public int onBlockPlaced(World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ, int meta) {
		return ForgeDirection.getOrientation(side).getOpposite().ordinal();
	}

	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int p_149727_6_,
			float p_149727_7_, float p_149727_8_, float p_149727_9_) {

		if (!world.isRemote && player.isSneaking()) {
			TileEntity entity = world.getTileEntity(x, y, z);
			if (entity instanceof TileEntityRFExciter) {
				String used = String.format(StatCollector.translateToLocal("chat.rfExciterUsed"),
						((TileEntityRFExciter) entity).lastEnergyUsed, ((TileEntityRFExciter) entity).getNetPower());
				player.addChatMessage(new ChatComponentText(EnumChatFormatting.LIGHT_PURPLE + used));
			}
		}

		return super.onBlockActivated(world, x, y, z, player, p_149727_6_, p_149727_7_, p_149727_8_, p_149727_9_);
	}

	/**
	 * called when an exciterUpgrade is r-clicked on the block
	 *
	 * @return the number of upgrades applied
	 */
	public int addUpgrade(World world, int x, int y, int z, int count) {
		TileEntity entity = world.getTileEntity(x, y, z);
		if (entity instanceof TileEntityRFExciter) {
			TileEntityRFExciter exciter = (TileEntityRFExciter) entity;
			int toApply = Math.min(count, ConfigMan.rfExciter_maxUpgrades - exciter.upgradeCount);

			exciter.upgradeCount += toApply;
			exciter.markDirty();
			return toApply;

		}
		return 0;
	}

	@Override
	public void breakBlock(World world, int x, int y, int z, Block block, int meta) {
		// TODO Auto-generated method stub
		TileEntity entity = world.getTileEntity(x, y, z);
		if (entity instanceof TileEntityRFExciter) {
			TileEntityRFExciter exciter = (TileEntityRFExciter) entity;
			int upgrades = exciter.upgradeCount;

			for (int i = 0; i < upgrades; i++) {

				float f = world.rand.nextFloat() * 0.6F + 0.1F;
				float f1 = world.rand.nextFloat() * 0.6F + 0.1F;
				float f2 = world.rand.nextFloat() * 0.6F + 0.1F;
				float f3 = 0.025F;

				EntityItem entityitem = new EntityItem(world, (double) ((float) x + f), (double) ((float) y + f1),
						(double) ((float) z + f2), new ItemStack(ModItems.exciterUpgrade, 1));

				entityitem.motionX = (double) ((float) world.rand.nextGaussian() * f3);
				entityitem.motionY = (double) ((float) world.rand.nextGaussian() * f3 + 0.1F);
				entityitem.motionZ = (double) ((float) world.rand.nextGaussian() * f3);
				world.spawnEntityInWorld(entityitem);
			}

		}

		super.breakBlock(world, x, y, z, block, meta);
	}

	@Override
	public boolean renderAsNormalBlock() {
		return false;
	}

	@Override
	public boolean isOpaqueCube() {
		return false;
	}

	@Override
	public int getRenderType() {
		return renderType;
	}

	@Override
	public boolean canRenderInPass(int pass) {
		ClientProxy.renderPass = pass;
		return true;
	}

	@Override
	public boolean isNormalCube() {
		return false;
	}

}
