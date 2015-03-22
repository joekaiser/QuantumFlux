package jotato.quantumflux.items;

import jotato.quantumflux.QuantumFlux;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraftforge.common.util.EnumHelper;

public class ItemBattleSuit extends ItemArmor
{
	
	public static ArmorMaterial material = EnumHelper.addArmorMaterial("battleSuitMatieral", 33, new int[]{3,8,6,3}, 50);

	public ItemBattleSuit(String name, int type)
	{
		super(material, 0, type);
		setUnlocalizedName(name);
		setTextureName("quantumflux:" + name);
		setCreativeTab(QuantumFlux.tab);
		GameRegistry.registerItem(this, name);
	
	}

}