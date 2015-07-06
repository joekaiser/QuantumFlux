package jotato.quantumflux.machine.entangler;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import jotato.quantumflux.blocks.BlockBase;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IIcon;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;

public class BlockRFEntangler extends BlockBase
{

	@SideOnly(Side.CLIENT)
	protected IIcon icon_front;
	@SideOnly(Side.CLIENT)
	protected IIcon icon_side;
	@SideOnly(Side.CLIENT)
	protected IIcon icon_top;

	public BlockRFEntangler()
	{
		super(Material.iron, "rfEntangler", 6, "pickaxe", 0);
		setStepSound(soundTypeMetal);
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void registerBlockIcons(IIconRegister ir)
	{
		this.icon_front = ir.registerIcon(getTexture("entangler/rfEntangler_front"));
		this.icon_side = ir.registerIcon(getTexture("entangler/rfEntangler_side"));
		this.icon_top = ir.registerIcon(getTexture("entangler/rfEntangler_top"));
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
	public boolean hasTileEntity(int metadata) {
		return true;
	}
	
	@Override
	public TileEntity createTileEntity(World world, int metadata) {
		return new TileEntityRFEntangler();
	}

	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int p_149727_6_, float p_149727_7_,
			float p_149727_8_, float p_149727_9_)
	{

		if (!world.isRemote && player.isSneaking())
		{

			TileEntity entity = world.getTileEntity(x, y, z);
			if (entity instanceof TileEntityRFEntangler)
			{
				player.addChatMessage(new ChatComponentText("----------"));
				TileEntityRFEntangler entangler = (TileEntityRFEntangler) entity;
				String stored = String.format(StatCollector.translateToLocal("chat.rfEntanglerStored"), entangler.getEnergyStored(null));
				player.addChatMessage(new ChatComponentText(EnumChatFormatting.LIGHT_PURPLE + stored));

				String in = String.format(StatCollector.translateToLocal("chat.rfEntanglerIn"), entangler.reportedIn);
				player.addChatMessage(new ChatComponentText(EnumChatFormatting.RED + in));

				String out = String.format(StatCollector.translateToLocal("chat.rfEntanglerOut"), entangler.reportedOut);
				player.addChatMessage(new ChatComponentText(EnumChatFormatting.GREEN + out));
			}
		}
		return super.onBlockActivated(world, x, y, z, player, p_149727_6_, p_149727_7_, p_149727_8_, p_149727_9_);
	}

}
