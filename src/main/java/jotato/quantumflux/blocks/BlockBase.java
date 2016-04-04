package jotato.quantumflux.blocks;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

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
		this(Material.rock, name, null, 2);
	}

	public BlockBase(Material material, String name) {
		this(material, name, null, 2);

	}

	public BlockBase(Material material, String name, Class<? extends ItemBlock> itemclass, float hardness) {
		super(material);
		setUnlocalizedName(name);
		setRegistryName(name);
		setCreativeTab(QuantumFluxMod.tab);
		setStepSound(SoundType.STONE);
		setHardness(hardness);
		
		GameRegistry.register(this);
		if (itemclass != null) {
			GameRegistry.register(createItemBlock(itemclass), getRegistryName());
		}
		else{
			GameRegistry.register(new ItemBlock(this),getRegistryName());
		}
	}

	@SideOnly(Side.CLIENT)
	public void initModel() {
		Logger.info("    Registering model for %s", getRegistryName());
		Item item = Item.getItemFromBlock(this);

		ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(this), 0,new ModelResourceLocation(getRegistryName(), "inventory"));
	}

	private ItemBlock createItemBlock(Class<? extends ItemBlock> itemBlockClass) {
		try {
			Class<?>[] ctorArgClasses = new Class<?>[1];
			ctorArgClasses[0] = Block.class;
			Constructor<? extends ItemBlock> itemCtor = itemBlockClass.getConstructor(ctorArgClasses);
			return itemCtor.newInstance(this);
		} catch (NoSuchMethodException | InstantiationException | IllegalAccessException
				| InvocationTargetException e) {
			throw new RuntimeException(e);
		}
	}
}
