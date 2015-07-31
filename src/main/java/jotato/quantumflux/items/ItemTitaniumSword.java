package jotato.quantumflux.items;

import cpw.mods.fml.common.registry.GameRegistry;
import jotato.quantumflux.QuantumFlux;
import net.minecraft.item.ItemSword;

public class ItemTitaniumSword extends ItemSword {

	public ItemTitaniumSword(String name) {
		super(ModItems.titaniumMaterial);
        setUnlocalizedName(name);
        setTextureName("quantumflux:" + name);
        setCreativeTab(QuantumFlux.tab);
        GameRegistry.registerItem(this, name);
        setMaxStackSize(1);

       
	}

}
