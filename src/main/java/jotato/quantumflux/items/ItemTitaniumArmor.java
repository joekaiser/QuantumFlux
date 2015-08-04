package jotato.quantumflux.items;

import cpw.mods.fml.common.registry.GameRegistry;
import jotato.quantumflux.QuantumFlux;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;

public class ItemTitaniumArmor extends ItemArmor {
	public ItemTitaniumArmor(String name, int type) {
		super(ModItems.titaniumArmor, 0, type);
		setUnlocalizedName(name);
		setTextureName("quantumflux:" + name);
		setCreativeTab(QuantumFlux.tab);
		GameRegistry.registerItem(this, name);
		setMaxStackSize(1);
	}
	
	@Override
	public String getArmorTexture(ItemStack stack, Entity entity, int slot, String type)
	{
		// TODO Auto-generated method stub
		return "quantumflux:textures/armor/titanium_layer_" + (this.armorType == 2 ? "2" : "1") + ".png";
	}
}
