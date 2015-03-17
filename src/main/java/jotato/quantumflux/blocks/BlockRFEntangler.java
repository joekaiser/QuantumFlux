package jotato.quantumflux.blocks;

import cofh.api.energy.IEnergyReceiver;

import com.mojang.realmsclient.gui.ChatFormatting;

import jotato.quantumflux.proxy.ClientProxy;
import jotato.quantumflux.tileentity.TileEntityRFEntangler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockRFEntangler extends BlockBase implements ITileEntityProvider
{
	public static int renderType;

	@SideOnly(Side.CLIENT)
	protected IIcon icon_front;
	@SideOnly(Side.CLIENT)
	protected IIcon icon_side;
	@SideOnly(Side.CLIENT)
	protected IIcon icon_top;

	protected BlockRFEntangler()
	{
		super(Material.iron, "rfEntangler", 6, "pickaxe", 1);
		setStepSound(soundTypeMetal);
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void registerBlockIcons(IIconRegister ir)
	{
		this.icon_front = ir.registerIcon(getTexture("rfEntangler_front"));
		this.icon_side = ir.registerIcon(getTexture("rfEntangler_side"));
		this.icon_top = ir.registerIcon(getTexture("rfEntangler_top"));
	}

	@Override
	public IIcon getIcon(int side, int meta)
	{
		int frontSide = getOrientation(meta, false);
		if (side == frontSide)
		{
			return icon_front;
		}

		if (side == 1 || side == 0)
		{
			return this.icon_top;
		}

		return this.icon_side;
	}

	@Override
	public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase entity, ItemStack itemStack)
	{
		int frontSide = determineOrientation(world, x, y, z, entity);
		world.setBlockMetadataWithNotify(x, y, z, frontSide, 2);

		if (entity instanceof EntityPlayer)
		{
			EntityPlayer player = (EntityPlayer) entity;
			if (world.getTileEntity(x, y, z) instanceof TileEntityRFEntangler)
			{
				TileEntityRFEntangler te = (TileEntityRFEntangler) world.getTileEntity(x, y, z);

				// todo: allow the owner to unlock and prevent other from
				// stealing the lock
				if (te.owner == null)
				{
					te.owner = player.getGameProfile().getId();
					te.registerWithField();
				}
			}
		}

	}

	@Override
	public TileEntity createNewTileEntity(World world, int p1)
	{
		return new TileEntityRFEntangler();
	}

	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int p_149727_6_, float p_149727_7_,
			float p_149727_8_, float p_149727_9_)
	{
		
		if (!world.isRemote && player.isSneaking())
		{

			TileEntity entity = world.getTileEntity(x, y, z);
			if (entity instanceof IEnergyReceiver)
			{
				String stored = String.valueOf(((IEnergyReceiver) entity).getEnergyStored(null));
				player.addChatMessage(new ChatComponentText(ChatFormatting.LIGHT_PURPLE + stored + " RF availble to the RedfluxField"));
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
