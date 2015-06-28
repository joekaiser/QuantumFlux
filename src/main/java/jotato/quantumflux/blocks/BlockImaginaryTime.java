package jotato.quantumflux.blocks;

import jotato.quantumflux.tileentity.TileEntityImaginaryTime;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

public class BlockImaginaryTime extends BlockBase implements ITileEntityProvider
{
	@SideOnly(Side.CLIENT)
	protected IIcon icon_side;
	@SideOnly(Side.CLIENT)
	protected IIcon icon_top;
	

	protected BlockImaginaryTime()
	{
		super(Material.iron, "imaginaryTime", 1.3f, "pickaxe", 0);
		setStepSound(soundTypeMetal);
	
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	public void registerBlockIcons(IIconRegister ir)
	{
		this.icon_side = ir.registerIcon(getTexture("imaginaryBlock_side1"));
		this.icon_top = ir.registerIcon(getTexture("imaginaryBlock_top"));
	}
	
	@Override
    @SideOnly(Side.CLIENT)
	public IIcon getIcon(int side, int meta)
	{

		if (side == 1 || side == 0)
		{
			return this.icon_top;
		}
		
		//TODO: switch between the two side textures
		return this.icon_side;
	}

	@Override
	public TileEntity createNewTileEntity(World world, int meta)
	{
		return new TileEntityImaginaryTime();
	}
	
}
