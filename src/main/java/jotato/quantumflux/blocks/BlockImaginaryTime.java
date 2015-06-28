package jotato.quantumflux.blocks;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.IIcon;

public class BlockImaginaryTime extends BlockBase
{
	@SideOnly(Side.CLIENT)
	protected IIcon icon_side1;
	@SideOnly(Side.CLIENT)
	protected IIcon icon_side2;
	@SideOnly(Side.CLIENT)
	protected IIcon icon_sideCurrent;
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
		this.icon_side1 = ir.registerIcon(getTexture("imaginaryBlock_side1"));
		this.icon_side2 = ir.registerIcon(getTexture("imaginaryBlock__side2"));
		this.icon_top = ir.registerIcon(getTexture("imaginaryBlock_top"));
		this.icon_sideCurrent = this.icon_side1;
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
		return this.icon_sideCurrent;
	}


}
