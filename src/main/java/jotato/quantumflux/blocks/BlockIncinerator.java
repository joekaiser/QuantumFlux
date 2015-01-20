package jotato.quantumflux.blocks;

import net.minecraft.block.material.Material;

public class BlockIncinerator extends BlockBase {

	protected BlockIncinerator() {
		super(Material.iron, "incinerator", 1,"pickaxe",1);
		setStepSound(soundTypeMetal);
	}

}
