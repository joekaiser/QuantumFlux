package jotato.quantumflux.machine.imaginarytime;

import jotato.quantumflux.blocks.BlockBase;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

public class BlockImaginaryTime extends BlockBase
{
	@SideOnly(Side.CLIENT)
	protected IIcon icon_side;
	@SideOnly(Side.CLIENT)
	protected IIcon icon_top;
	

	public BlockImaginaryTime()
	{
		super(Material.iron, "imaginaryTime", 1.3f, "pickaxe", 0,ItemBlockImaginaryTime.class);
		setStepSound(soundTypeMetal);
	
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	public void registerBlockIcons(IIconRegister ir)
	{
		this.icon_side = ir.registerIcon(getTexture("time/imaginaryBlock_side1"));
		this.icon_top = ir.registerIcon(getTexture("time/imaginaryBlock_top"));
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
	public boolean hasTileEntity(int metadata) {
		return true;
	}
	
	@Override
	public TileEntity createTileEntity(World world, int metadata) {
		return new TileEntityImaginaryTime();
	}
	
}
