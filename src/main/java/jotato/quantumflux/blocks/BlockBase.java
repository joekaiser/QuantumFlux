package jotato.quantumflux.blocks;

import cpw.mods.fml.common.registry.GameRegistry;
import jotato.quantumflux.QuantumFlux;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class BlockBase extends Block {

	protected BlockBase(Material material, String name, float hardness, String harvestTool, int harvestLevel) {
		super(material);

		setBlockName(name);
		setCreativeTab(QuantumFlux.tab);
		setBlockTextureName("quantumflux:" + name);
		setHardness(hardness);
		setHarvestLevel(harvestTool, harvestLevel);
		GameRegistry.registerBlock(this, name);
		

	}

}
