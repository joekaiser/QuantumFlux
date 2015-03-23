package jotato.quantumflux.items;

import java.util.List;

import cofh.api.energy.IEnergyContainerItem;
import jotato.quantumflux.ConfigMan;
import jotato.quantumflux.NbtUtils;
import jotato.quantumflux.QuantumFlux;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;
import net.minecraftforge.common.ISpecialArmor;
import net.minecraftforge.common.util.EnumHelper;

public class ItemBattleSuit extends ItemArmor implements IEnergyContainerItem, ISpecialArmor
{

	public static ArmorMaterial material = EnumHelper.addArmorMaterial("battleSuitMatieral", 33, new int[] { 4, 9, 7, 4 }, 50);
	private final String energy_tag = "Energy";
	private int energyUsedPerDamageTaken = 100;

	public ItemBattleSuit(String name, int type)
	{
		super(material, 0, type);
		setUnlocalizedName(name);
		setTextureName("quantumflux:" + name);
		setCreativeTab(QuantumFlux.tab);
		GameRegistry.registerItem(this, name);
		setMaxStackSize(1);

	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public void getSubItems(Item item, CreativeTabs tab, List list)
	{
		ItemStack emptyItem = new ItemStack(item);
		emptyItem = NbtUtils.setInt(emptyItem, energy_tag, 0);

		ItemStack chargedItem = new ItemStack(item);
		chargedItem = NbtUtils.setInt(chargedItem, energy_tag, ConfigMan.battlesuit_maxEnergy);

		list.add(emptyItem);
		list.add(chargedItem);
	}

	@Override
	public boolean isItemTool(ItemStack p_77616_1_)
	{
		return true;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, EntityPlayer player, List info, boolean p_77624_4_)
	{
		info.add(EnumChatFormatting.RED + "Charge: " + getEnergyStored(stack));
	}

	@Override
	public double getDurabilityForDisplay(ItemStack stack)
	{
		return 1D - (double) NbtUtils.getInt(stack, energy_tag) / (double) getMaxEnergyStored(stack);
	}

	@Override
	public boolean showDurabilityBar(ItemStack stack)
	{
		return getEnergyStored(stack) < getMaxEnergyStored(stack);
	}

	@Override
	public String getArmorTexture(ItemStack stack, Entity entity, int slot, String type)
	{
		// TODO Auto-generated method stub
		return "quantumflux:textures/armor/battlesuit_layer_" + (this.armorType == 2 ? "2" : "1") + ".png";
	}

	@Override
	public void onArmorTick(World world, EntityPlayer player, ItemStack itemStack)
	{
		super.onArmorTick(world, player, itemStack);

	}

	@Override
	public int receiveEnergy(ItemStack container, int maxReceive, boolean simulate)
	{
		int stored = getEnergyStored(container);
		int maxICanReceive = Math.min(ConfigMan.battlesuit_maxEnergy - stored, ConfigMan.battlesuit_chargeRate);
		int toGet = Math.min(maxReceive, maxICanReceive);

		if (simulate)
		{
			return toGet;
		}

		stored += toGet;
		NbtUtils.setInt(container, energy_tag, stored);

		return toGet;
	}

	// energy can not be pulled out
	@Override
	public int extractEnergy(ItemStack container, int maxExtract, boolean simulate)
	{
		int stored = getEnergyStored(container);
		int maxICanSend = Math.min(stored, ConfigMan.battlesuit_chargeRate);
		int toSend = Math.min(maxExtract, maxICanSend);
		if (simulate)
		{
			return toSend;
		}

		stored -= toSend;
		NbtUtils.setInt(container, energy_tag, stored);

		return toSend;
	}

	@Override
	public int getEnergyStored(ItemStack container)
	{
		return NbtUtils.getInt(container, energy_tag);
	}

	@Override
	public int getMaxEnergyStored(ItemStack container)
	{
		return ConfigMan.battlesuit_maxEnergy;
	}

	@Override
	public ArmorProperties getProperties(EntityLivingBase player, ItemStack armor, DamageSource source, double damage, int slot)
	{
		int maxToAbsorb = getEnergyStored(armor) / energyUsedPerDamageTaken;

		return new ArmorProperties(1, .25D, maxToAbsorb);
	}

	@Override
	public int getArmorDisplay(EntityPlayer player, ItemStack armor, int slot)
	{
		return 5;
	}

	@Override
	public void damageArmor(EntityLivingBase entity, ItemStack stack, DamageSource source, int damage, int slot)
	{
		int totalEnergyCost = damage * energyUsedPerDamageTaken;
		extractEnergy(stack, totalEnergyCost, false);

	}

}