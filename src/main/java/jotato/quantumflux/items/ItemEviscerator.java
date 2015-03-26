package jotato.quantumflux.items;

import jotato.quantumflux.QuantumFlux;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.common.util.EnumHelper;

public class ItemEviscerator extends ItemPickaxe
{

	public static ToolMaterial material = EnumHelper.addToolMaterial("eviscerator", 10000, 1000, 50, 30, 1000);
	
	protected ItemEviscerator(String name)
	{
		super(material);
		setUnlocalizedName(name);
		setTextureName("quantumflux:" + name);
		setCreativeTab(QuantumFlux.tab);
		GameRegistry.registerItem(this, name);
		setMaxStackSize(1);
		setMaxDamage(0);
		canRepair=false;
		
	}
	
	@Override
	public boolean hitEntity(ItemStack stack, EntityLivingBase p_77644_2_, EntityLivingBase p_77644_3_)
	{
		setDamage(stack, 0);
		return true;
	}
	
	@Override
	public boolean onBlockDestroyed(ItemStack stack, World p_150894_2_, Block p_150894_3_, int p_150894_4_, int p_150894_5_,
			int p_150894_6_, EntityLivingBase p_150894_7_)
	{
		setDamage(stack, 0);
		return true;
	}
	
	@Override
	public void setDamage(ItemStack stack, int damage)
	{
		
		super.setDamage(stack, 0);
	}
	
	@Override
	public float getDigSpeed(ItemStack stack, Block block, int meta)
	{
		return efficiencyOnProperMaterial;
	}

}
