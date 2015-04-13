package jotato.quantumflux.items;

import java.util.List;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import jotato.quantumflux.QuantumFlux;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

public class ItemBase extends Item
{

	protected String helpText;

	public ItemBase(String name)
	{
		setUnlocalizedName(name);
		setTextureName("quantumflux:" + name);
		setCreativeTab(QuantumFlux.tab);
		GameRegistry.registerItem(this, name);
	}

	public ItemBase(String name, String helpText)
	{
		this(name);
		this.helpText = helpText;
	}

	@Override
	public boolean onItemUse(ItemStack itemStack, EntityPlayer player, World world, int x, int y, int z, int side, float p8, float p9,
			float p10)
	{
		return super.onItemUse(itemStack, player, world, x, y, z, side, p8, p9, p10);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack itemStack, EntityPlayer player, List info, boolean isExtended)
	{
		super.addInformation(itemStack, player, info, isExtended);

		if (helpText != null)
		{
			info.add(helpText);
		}
	}

	@SuppressWarnings("rawtypes")
	@Override
	@SideOnly(Side.CLIENT)
	public void getSubItems(Item item, CreativeTabs creativeTab, List itemList)
	{
		super.getSubItems(item, creativeTab, itemList);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister register)
	{
		super.registerIcons(register);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIconFromDamage(int damage)
	{
		return super.getIconFromDamage(damage);
	}

	@Override
	public void onUpdate(ItemStack itemStack, World world, Entity entity, int p4, boolean p5)
	{
		super.onUpdate(itemStack, world, entity, p4, p5);
	}

}
