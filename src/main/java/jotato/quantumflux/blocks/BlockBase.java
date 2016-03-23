package jotato.quantumflux.blocks;

import jotato.quantumflux.Logger;
import jotato.quantumflux.QuantumFluxMod;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockBase extends Block {

	public BlockBase(String name) {
		this(Material.rock, name, null,2);
	}

	public BlockBase(Material material, String name) {
		this(material, name, null,2);
		
	}

	public BlockBase(Material material, String name, Class<? extends ItemBlock> itemclass, float hardness) {
		super(material);
		setUnlocalizedName(name);
		setRegistryName(name);
		if (itemclass != null) {
			GameRegistry.registerBlock(this, itemclass);
		} else {
			GameRegistry.registerBlock(this);
		}
		setCreativeTab(QuantumFluxMod.tab);
		setStepSound(SoundType.STONE);
		setHardness(hardness);
	}

	public String getSimpleName() {
		return getUnlocalizedName().substring(5);
	}

	@SideOnly(Side.CLIENT)
	public void initModel() {
		Logger.info("    Registering model for %s", getSimpleName());
		ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(this), 0,
				new ModelResourceLocation(getRegistryName(), "inventory"));
	}
}
