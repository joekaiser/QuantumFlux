package jotato.quantumflux.machine.cluster;

import java.util.ArrayList;
import java.util.List;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import jotato.quantumflux.QuantumFlux;
import jotato.quantumflux.blocks.BlockContainerBase;
import jotato.quantumflux.blocks.ModBlocks;
import jotato.quantumflux.gui.QFGuiHandler.GUI;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;


public class BlockQuibitCluster extends BlockContainerBase {

	@SideOnly(Side.CLIENT)
	protected IIcon icon_top;
	@SideOnly(Side.CLIENT)
	protected IIcon icon_bottom;
	@SideOnly(Side.CLIENT)
	protected IIcon[] icon_side;

	private final int numberOfLevels = 5;

	public BlockQuibitCluster() {
		super(Material.iron, "quibitCluster", 3, "pickaxe", 0, ItemBlockQuibitCluster.class);
		setStepSound(soundTypeMetal);
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void registerBlockIcons(IIconRegister ir) {
		this.icon_side = new IIcon[numberOfLevels];
		this.icon_top = ir.registerIcon(getTexture("cluster/quibitCluster_top"));
		this.icon_bottom = ir.registerIcon(getTexture("cluster/quibitCluster_bottom"));

		for (int i = 0; i < numberOfLevels; i++) {
			this.icon_side[i] = ir.registerIcon(getTexture("cluster/quibitCluster_" + (i + 1) + "_side"));
		}

	}

	@Override
	public void getSubBlocks(Item par1, CreativeTabs par2CreativeTabs, List par3List) {
		for (int metadata = 0; metadata < numberOfLevels; metadata++) {
			par3List.add(new ItemStack(ModBlocks.quibitCluster, 1, metadata));
		}
	}

	@Override
	public IIcon getIcon(int side, int meta) {

		if (side == 1) {
			return this.icon_top;
		}
		if (side == 0) {
			return this.icon_bottom;
		}

		return icon_side[meta];
	}

	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int meta, float p7, float p8,
			float p9) {
		player.openGui(QuantumFlux.instance, GUI.QUIBIT_CLUSTER.ordinal, world, x, y, z);
		return true;
	}

	@Override
	public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase entity, ItemStack itemStack) {
		if (itemStack != null && itemStack.getItem() instanceof ItemBlockQuibitCluster) {
			ItemBlockQuibitCluster itemBlock = (ItemBlockQuibitCluster)itemStack.getItem();
			TileEntityQuibitCluster tileEntity = (TileEntityQuibitCluster)world.getTileEntity(x, y, z);
			tileEntity.setEnergyStored(itemBlock.getEnergyStored(itemStack));
		}
	}

	@Override
	public boolean removedByPlayer(World world, EntityPlayer player, int x, int y, int z, boolean willHarvest) {
		if (willHarvest)
			return true;
		
		return super.removedByPlayer(world, player, x, y, z, willHarvest);
	}

	@Override
	public void harvestBlock(World world, EntityPlayer player, int x, int y, int z, int meta) {
		super.harvestBlock(world, player, x, y, z, meta);
		world.setBlockToAir(x, y, z);
	}

	@Override
	public ArrayList<ItemStack> getDrops(World world, int x, int y, int z, int metadata, int fortune) {
		ArrayList<ItemStack> ret = new ArrayList<ItemStack>();
		
		TileEntityQuibitCluster tileEntity = (TileEntityQuibitCluster)world.getTileEntity(x, y, z);
		
		Item item = getItemDropped(metadata, world.rand, fortune);
		if (item != null && item instanceof ItemBlockQuibitCluster) {
			ItemBlockQuibitCluster itemBlock = (ItemBlockQuibitCluster)item;
			ItemStack itemStack = new ItemStack(itemBlock, 1, damageDropped(metadata));
			itemBlock.setEnergyStored(itemStack, tileEntity.getEnergyStored(null));
			ret.add(itemStack);
		}
		return ret;
	}

	@Override
	public TileEntity createNewTileEntity(World world, int meta) {

		return new TileEntityQuibitCluster(new QuibitClusterSettings(meta+1));

	}

	public static QuibitClusterSettings getQuibitClusterSettings(ItemStack itemstack) {
		return new QuibitClusterSettings(itemstack.getItemDamage() + 1);
	}

	@Override
	public int damageDropped(int meta) {
		return meta;
	}

}
