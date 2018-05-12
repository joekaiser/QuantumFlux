package jotato.quantumflux.blocks.darkstone;

import jotato.quantumflux.Logger;
import jotato.quantumflux.QuantumFluxMod;
import jotato.quantumflux.blocks.BlockBase;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockDarkstone extends BlockBase {

	public static final PropertyEnum TYPE = PropertyEnum.create("type", EnumDarkstone.class);

	public BlockDarkstone() {
		super(Material.ROCK, "darkstone", ItemBlockDarkstone.class,3);
	}

	@Override
	public void initModel() {
		Item itemBlock = Item.REGISTRY.getObject(new ResourceLocation(QuantumFluxMod.MODID, "darkstone"));
		EnumDarkstone[] allTypes = EnumDarkstone.values();
		for (EnumDarkstone type : allTypes) {
			String name = String.format("%s_%s", getRegistryName(), type.name());
			Logger.devLog("    Registering model for %s", name);
			ModelLoader.setCustomModelResourceLocation(itemBlock, type.getMetadata(),
					new ModelResourceLocation(name, "inventory"));
		}

	}
	
	@Override
	public int getLightValue(IBlockState state, IBlockAccess world, BlockPos pos) {
		if(state.getValue(TYPE)==EnumDarkstone.lamp){
			return 15;
		}
		return 0;
	}

	@Override
	public int damageDropped(IBlockState state) {
		EnumDarkstone enumType = (EnumDarkstone) state.getValue(TYPE);
		return enumType.getMetadata();
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void getSubBlocks(CreativeTabs itemIn, NonNullList<ItemStack> items) {
		Item itemBlock = Item.REGISTRY.getObject(new ResourceLocation(QuantumFluxMod.MODID, "darkstone"));
		EnumDarkstone[] allTypes = EnumDarkstone.values();
		for (EnumDarkstone type : allTypes) {
			items.add(new ItemStack(itemBlock, 1, type.getMetadata()));
		}
	}

	@Override
	public IBlockState getStateFromMeta(int meta) {
		int typebits = meta;
		EnumDarkstone type = EnumDarkstone.byMetadata(typebits);
		return this.getDefaultState().withProperty(TYPE, type);
	}

	@Override
	public int getMetaFromState(IBlockState state) {
		EnumDarkstone type = (EnumDarkstone) state.getValue(TYPE);

		int typebits = type.getMetadata();
		return typebits;
	}

	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, new IProperty[] { TYPE });
	}
	

	@Override
	public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack) {
		EnumDarkstone type = EnumDarkstone.byMetadata(stack.getMetadata());

		worldIn.setBlockState(pos, state.withProperty(TYPE, type));
	}
}
