package jotato.quantumflux.machines.cluster;

import jotato.quantumflux.ConfigMan;
import jotato.quantumflux.Logger;
import jotato.quantumflux.QuantumFluxMod;
import jotato.quantumflux.blocks.BlockBase;
import jotato.quantumflux.helpers.BlockHelpers;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockQuibitCluster extends BlockBase implements ITileEntityProvider {

	public static final PropertyEnum LEVEL = PropertyEnum.create("level", EnumQuibitCluster.class);

	public BlockQuibitCluster() {
		super(Material.ROCK, "quibitCluster", ItemBlockQuibitCluster.class, 3);
	}

	@Override
	public void initModel() {

		Item itemBlock = Item.REGISTRY.getObject(new ResourceLocation(QuantumFluxMod.MODID, "quibitCluster"));
		EnumQuibitCluster[] allLevels = EnumQuibitCluster.values();
		for (EnumQuibitCluster level : allLevels) {
			String name = String.format("%s_%s", getRegistryName(), level.name());
			Logger.devLog("    Registering model for %s", name);
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
	public void getSubBlocks(CreativeTabs itemIn, NonNullList<ItemStack> items) {
		Item itemBlock = Item.REGISTRY.getObject(new ResourceLocation(QuantumFluxMod.MODID, "quibitCluster"));
		EnumQuibitCluster[] allLevels = EnumQuibitCluster.values();
		for (EnumQuibitCluster level : allLevels) {
			items.add(new ItemStack(itemBlock, 1, level.getMetadata()));
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
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, new IProperty[] { LEVEL });
	}

	@Override
	public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack) {
		EnumQuibitCluster level = EnumQuibitCluster.byMetadata(stack.getMetadata());

		worldIn.setBlockState(pos, state.withProperty(LEVEL, level));
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
			EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {

		if (worldIn.isRemote)
			return false;

		TileEntity te = worldIn.getTileEntity(pos);
		if (te instanceof TileQuibitCluster) {
			TileQuibitCluster teqc = (TileQuibitCluster) worldIn.getTileEntity(pos);

			if (ConfigMan.isDebug) {
				playerIn.sendMessage(new TextComponentString(String.valueOf(teqc.lastUsed)));
			}
		}
		return BlockHelpers.BroadcastRFStored(playerIn, te);

	}

}
