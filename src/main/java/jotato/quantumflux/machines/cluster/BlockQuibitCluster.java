package jotato.quantumflux.machines.cluster;

import java.util.List;

import cofh.api.energy.IEnergyProvider;
import jotato.quantumflux.Logger;
import jotato.quantumflux.QuantumFluxMod;
import jotato.quantumflux.blocks.BlockBase;
import jotato.quantumflux.helpers.BlockHelpers;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockQuibitCluster extends BlockBase implements ITileEntityProvider {

	public static final PropertyEnum LEVEL = PropertyEnum.create("level", EnumQuibitCluster.class);

	public BlockQuibitCluster() {
		super(Material.rock, "quibitCluster", ItemBlockQuibitCluster.class,3);
	}

	@Override
	public void initModel() {

		Item itemBlock = GameRegistry.findItem(QuantumFluxMod.MODID, "quibitCluster");
		EnumQuibitCluster[] allLevels = EnumQuibitCluster.values();
		for (EnumQuibitCluster level : allLevels) {
			String name = String.format("%s_%s", getRegistryName(), level.name());
			Logger.info("    Registering model for %s", name);
			ModelLoader.setCustomModelResourceLocation(itemBlock, level.getMetadata(),
					new ModelResourceLocation(name, "inventory"));
		}

	}

	@Override
	public int damageDropped(IBlockState state) {
		EnumQuibitCluster enumLevel = (EnumQuibitCluster) state.getValue(LEVEL);
		return enumLevel.getMetadata();
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void getSubBlocks(Item itemIn, CreativeTabs tab, List list) {
		EnumQuibitCluster[] allLevels = EnumQuibitCluster.values();
		for (EnumQuibitCluster level : allLevels) {
			list.add(new ItemStack(itemIn, 1, level.getMetadata()));
		}
	}

	@Override
	public IBlockState getStateFromMeta(int meta) {
		int bits = meta;
		EnumQuibitCluster level = EnumQuibitCluster.byMetadata(bits);
		return this.getDefaultState().withProperty(LEVEL, level);
	}

	@Override
	public int getMetaFromState(IBlockState state) {
		EnumQuibitCluster level = (EnumQuibitCluster) state.getValue(LEVEL);

		int bits = level.getMetadata();
		return bits;
	}

	@Override
	protected BlockState createBlockState() {
		return new BlockState(this, new IProperty[] { LEVEL });
	}

	@Override
	public IBlockState onBlockPlaced(World worldIn, BlockPos pos, EnumFacing blockFaceClickedOn, float hitX, float hitY,
			float hitZ, int meta, EntityLivingBase placer) {
		EnumQuibitCluster level = EnumQuibitCluster.byMetadata(meta);

		return this.getDefaultState().withProperty(LEVEL, level);
	}

	public static QuibitClusterSettings getQuibitClusterSettings(ItemStack item) {
		return new QuibitClusterSettings(item.getItemDamage());
	}

	public static QuibitClusterSettings getQuibitClusterSettings(int level) {
		return new QuibitClusterSettings(level);
	}

	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		if (meta == 0) {
			return new TileCreativeCluster();
		}
		return new TileQuibitCluster(getQuibitClusterSettings(meta));
	}

	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn,
			EnumFacing side, float hitX, float hitY, float hitZ) {

		if (worldIn.isRemote)
			return false;

		return BlockHelpers.BroadcastRFStored(playerIn, worldIn.getTileEntity(pos));

	}

}
