package jotato.quantumflux.blocks;

import java.text.NumberFormat;

import com.mojang.realmsclient.gui.ChatFormatting;

import jotato.quantumflux.proxy.ClientProxy;
import jotato.quantumflux.tileentity.TileEntityRFExciter;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChatComponentText;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

public class BlockRFExciter extends BlockBase implements ITileEntityProvider
{
	public static int renderType;

	protected BlockRFExciter()
	{
		super(Material.rock, "rfExciter", 2, "pickaxe", 0);
		this.setBlockBounds(.15f, 0f, .15f, .85f, .12f, .85f);
	}

	@Override
	public void setBlockBoundsBasedOnState(IBlockAccess world, int x, int y, int z)
	{
		float minx = 0, miny = 0, minz = 0, maxx = 1, maxy = 1, maxz = 1;

		switch (world.getBlockMetadata(x, y, z))
		{
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
	public TileEntity createNewTileEntity(World world, int meta)
	{
		return new TileEntityRFExciter();
	}

	@Override
	public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase entity, ItemStack itemStack)
	{
		super.onBlockPlacedBy(world, x, y, z, entity, itemStack);

		if (entity instanceof EntityPlayer)
		{
			EntityPlayer player = (EntityPlayer) entity;
			if (world.getTileEntity(x, y, z) instanceof TileEntityRFExciter)
			{
				TileEntityRFExciter te = (TileEntityRFExciter) world.getTileEntity(x, y, z);

				// todo: allow the owner to unlock and prevent other from
				// stealing the lock
				if (te.owner == null)
				{
					te.owner = player.getGameProfile().getId();
				}

				te.targetDirection = ForgeDirection.getOrientation(world.getBlockMetadata(x, y, z));
			}
		}

	}

	@Override
	public int onBlockPlaced(World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ, int meta)
	{
		return ForgeDirection.getOrientation(side).getOpposite().ordinal();
	}

	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int p_149727_6_, float p_149727_7_,
			float p_149727_8_, float p_149727_9_)
	{

		if (!world.isRemote && player.isSneaking())
		{
			TileEntity entity = world.getTileEntity(x, y, z);
			if (entity instanceof TileEntityRFExciter)
			{
				String used = NumberFormat.getIntegerInstance().format(((TileEntityRFExciter) entity).lastEnergyUsed);

				player.addChatMessage(new ChatComponentText(ChatFormatting.LIGHT_PURPLE + used + " RF used last tick"));
			}
		}

		return super.onBlockActivated(world, x, y, z, player, p_149727_6_, p_149727_7_, p_149727_8_, p_149727_9_);
	}

	@Override
	public boolean renderAsNormalBlock()
	{
		return false;
	}

	@Override
	public boolean isOpaqueCube()
	{
		return false;
	}

	@Override
	public int getRenderType()
	{
		return renderType;
	}

	@Override
	public boolean canRenderInPass(int pass)
	{
		ClientProxy.renderPass = pass;
		return true;
	}

	@Override
	public int getRenderBlockPass()
	{
		return 1;
	}

	@Override
	public boolean isNormalCube()
	{
		return false;
	}

	@Override
	public boolean shouldSideBeRendered(IBlockAccess p_149646_1_, int p_149646_2_, int p_149646_3_, int p_149646_4_, int p_149646_5_)
	{
		return true;
	}

}
