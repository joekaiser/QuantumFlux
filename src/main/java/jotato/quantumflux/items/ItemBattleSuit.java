package jotato.quantumflux.items;

import jotato.quantumflux.QuantumFlux;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.util.EnumHelper;

public class ItemBattleSuit extends ItemArmor
{
	
	public static ArmorMaterial material = EnumHelper.addArmorMaterial("battleSuitMatieral", 33, new int[]{4,9,7,4}, 50);

	public ItemBattleSuit(String name, int type)
	{
		super(material, 0, type);
		setUnlocalizedName(name);
		setTextureName("quantumflux:" + name);
		setCreativeTab(QuantumFlux.tab);
		GameRegistry.registerItem(this, name);
	
	}
	
	@Override
	public String getArmorTexture(ItemStack stack, Entity entity, int slot, String type)
	{
	    // TODO Auto-generated method stub
	    return  "quantumflux:textures/armor/battlesuit_layer_"+ (this.armorType == 2 ? "2" : "1") + ".png";
	}

}