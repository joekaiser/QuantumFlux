package jotato.quantumflux.machine.infuser;

import jotato.quantumflux.QuantumFlux;
import jotato.quantumflux.blocks.BlockContainerBase;
import jotato.quantumflux.gui.QFGuiHandler.GUI;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

public class BlockMolecularInfuser extends BlockContainerBase
{
	@SideOnly(Side.CLIENT)
	protected IIcon icon_top;
	@SideOnly(Side.CLIENT)
	protected IIcon icon_front;
	@SideOnly(Side.CLIENT)
	protected IIcon icon_side;

	public BlockMolecularInfuser()
	{
		super(Material.iron, "molecularInfuser", 1, "pickaxe", 0,null);
		setStepSound(soundTypeMetal);
		setLightLevel(.3f);
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void registerBlockIcons(IIconRegister ir)
	{
		this.icon_top = ir.registerIcon(getTexture("infuser/molecularInfuser_top"));
		this.icon_front = ir.registerIcon(getTexture("infuser/molecularInfuser_front"));
		this.icon_side = ir.registerIcon(getTexture("infuser/molecularInfuser_side"));
	}

	@Override
	public IIcon getIcon(int side, int meta)
	{
		int frontSide = getOrientation(meta, false);
		if (side == frontSide)
		{
			return icon_front;
		}

		if (side == 1)
		{
			return this.icon_top;
		}
		
		if(side==0){
			return this.icon_top;
		}

		return this.icon_side;
	}

	@Override
	public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase entity, ItemStack itemStack)
	{
		int frontSide = determineOrientation(world, x, y, z, entity);
		world.setBlockMetadataWithNotify(x, y, z, frontSide, 2);

	}

	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int p6, float p7, float p8, float p9)
	{
		player.openGui(QuantumFlux.instance, GUI.INFUSER.ordinal, world, x, y, z);
		return true;

	}
	
	@Override
	public boolean hasTileEntity(int metadata) {
		return true;
	};

	@Override
	public TileEntity createNewTileEntity(World world, int p1)
	{
		return new TileEntityMolecularInfuser();
	}

	@Override
	public void breakBlock(World world, int x, int y, int z, Block block, int meta)
	{
		TileEntityMolecularInfuser tileentity = (TileEntityMolecularInfuser)world.getTileEntity(x, y, z);
			
		dropInventory(world, x, y, z, block, tileentity);

		super.breakBlock(world, x, y, z, block, meta);
	}


}