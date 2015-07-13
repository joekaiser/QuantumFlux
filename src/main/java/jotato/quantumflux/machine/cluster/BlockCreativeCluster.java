package jotato.quantumflux.machine.cluster;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import jotato.quantumflux.blocks.BlockBase;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

public class BlockCreativeCluster extends BlockBase implements ITileEntityProvider{

	@SideOnly(Side.CLIENT)
	protected IIcon icon_top;
	@SideOnly(Side.CLIENT)
	protected IIcon icon_bottom;
	@SideOnly(Side.CLIENT)
	protected IIcon icon_side;

	public BlockCreativeCluster() {
		super(Material.iron, "creativeCluster", 3, "pickaxe", 0, null);
		setStepSound(soundTypeMetal);
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void registerBlockIcons(IIconRegister ir) {
		this.icon_top = ir.registerIcon(getTexture("cluster/quibitCluster_top"));
		this.icon_bottom = ir.registerIcon(getTexture("cluster/quibitCluster_bottom"));

		this.icon_side = ir.registerIcon(getTexture("cluster/creativeCluster_front"));

	}

	@Override
	public IIcon getIcon(int side, int meta) {

		if (side == 1) {
			return this.icon_top;
		}
		if (side == 0) {
			return this.icon_bottom;
		}

		return icon_side;
	}

	@Override
	public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
		return new TileEntityCreativeCluster();
	}

}
