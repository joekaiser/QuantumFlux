package jotato.quantumflux.blocks;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

public class BlockDarkstone extends BlockBase
{
	@SideOnly(Side.CLIENT)
	protected IIcon[] icons;
	
	public BlockDarkstone()
	{
		super(Material.rock, "darkstone", 1f, "pickaxe", 0,null);
		setStepSound(soundTypeMetal);
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	public void registerBlockIcons(IIconRegister ir)
	{
		icons=new IIcon[4];
		icons[0]=ir.registerIcon(getTexture("darkstone_0"));
		icons[1]=ir.registerIcon(getTexture("darkstone_1"));
		icons[2]=ir.registerIcon(getTexture("darkstone_2"));
		icons[3]=ir.registerIcon(getTexture("darkstone_3"));
	} 
	
	@SideOnly(Side.CLIENT)
	@Override
	public IIcon getIcon(int side, int meta)
	{
		return icons[meta];
	}
	
	@Override
	public int onBlockPlaced(World world, int x, int y, int z, int side, float hitx,
			float hity, float hitz, int meta)
	{
		return world.rand.nextInt(4);
	}
	
	@Override
	public void onBlockPreDestroy(World world, int x, int y, int z, int meta)
	{
		world.setBlockMetadataWithNotify(x, y, z, 0, 2);
	}
}
