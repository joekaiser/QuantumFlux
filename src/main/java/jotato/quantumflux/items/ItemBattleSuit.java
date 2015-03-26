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
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.common.ISpecialArmor;
import net.minecraftforge.common.util.EnumHelper;

public class ItemBattleSuit extends ItemArmor implements IEnergyContainerItem, ISpecialArmor
{

	public static ArmorMaterial material = EnumHelper.addArmorMaterial("battleSuitMatieral", 33, new int[] { 4, 9, 7, 4 }, 50);
	private static final String energy_tag = "Energy";
	private int energyUsedPerDamageTaken = 100;

	public ItemBattleSuit(String name, int type)
	{
		super(material, 0, type);
		setUnlocalizedName(name);
		setTextureName("quantumflux:" + name);
		setCreativeTab(QuantumFlux.tab);
		GameRegistry.registerItem(this, name);
		setMaxStackSize(1);
		setMaxDamage(0);
		canRepair=false;

	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public void getSubItems(Item item, CreativeTabs tab, List list)
	{
		list.add(ItemBattleSuit.newArmorPiece(item, 0, false));
		list.add(ItemBattleSuit.newArmorPiece(item, 0, true));
		list.add(ItemBattleSuit.newArmorPiece(item, 1, true));
	}

	@Override
	public boolean hasEffect(ItemStack item)
	{
		return item.getItemDamage() == 1;
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
		if (stack.getItem() == QFItems.battlesuit_chest && isArmorSpecialCapable(stack))
		{
			info.add("Capability: Flight");
		}

		if (stack.getItem() == QFItems.battlesuit_boots && isArmorSpecialCapable(stack))
		{
			info.add("Capability: Speed boost");
		}
		
		if (stack.getItem() == QFItems.battlesuit_legs && isArmorSpecialCapable(stack))
		{
			info.add("Capability: Strength");
		}
		
		if (stack.getItem() == QFItems.battlesuit_helm && isArmorSpecialCapable(stack))
		{
			info.add("Capability: Visibility");
		}

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
		double percentFull = (double) getEnergyStored(armor) / (double) getMaxEnergyStored(armor);
		int maxToAbsorb = getEnergyStored(armor) / energyUsedPerDamageTaken * MathHelper.ceiling_double_int(percentFull);

		return new ArmorProperties(1, .25D, (int) maxToAbsorb);
	}

	@Override
	public int getArmorDisplay(EntityPlayer player, ItemStack armor, int slot)
	{
		double percentFull = (double) getEnergyStored(armor) / (double) getMaxEnergyStored(armor);

		return percentFull <= 0 ? 0 : 1 + MathHelper.ceiling_double_int(4 * percentFull);

	}

	@Override
	public void damageArmor(EntityLivingBase entity, ItemStack stack, DamageSource source, int damage, int slot)
	{
		int totalEnergyCost = damage * energyUsedPerDamageTaken;
		extractEnergy(stack, totalEnergyCost, false);

	}

	public static ItemStack newArmorPiece(Item item, int meta, boolean charged)
	{
		if (charged)
			return NbtUtils.setInt(new ItemStack(item, 1, meta), energy_tag, ConfigMan.battlesuit_maxEnergy);
		else
			return NbtUtils.setInt(new ItemStack(item, 1, meta), energy_tag, 0);
	}

	/**
	 * Applies special effect from armor
	 * @param slot
	 *      armor slot. 0:boots; 3: helm
	 * @param player
	 */
	public static void doSpecial(int slot, EntityPlayer player)
	{
		switch(slot){
		case 0:
			player.capabilities.setFlySpeed(.185f);
			player.capabilities.setPlayerWalkSpeed(.3f);
			break;
		case 1:
			player.addPotionEffect(new PotionEffect(Potion.digSpeed.id,40,2));
			player.addPotionEffect(new PotionEffect(Potion.damageBoost.id,40,2));			
			break;
			
		case 2:
			player.capabilities.allowFlying = true;
			break;
		case 3:
			player.addPotionEffect(new PotionEffect(Potion.nightVision.id,40));
			player.addPotionEffect(new PotionEffect(Potion.waterBreathing.id,40));
			break;
		}
		
	}
	
	/**
	 * removes special effect from armor
	 * @param slot
	 * 		armor slot. 0:boots; 3: helm
	 * @param player
	 */
	public static void removeSpecial(int slot, EntityPlayer player){
		switch(slot){
		case 0:
			player.capabilities.setFlySpeed(.05f);
			player.capabilities.setPlayerWalkSpeed(.1f);
			break;
		case 1:
			break;
		case 2:
			player.capabilities.allowFlying = false;
			player.capabilities.isFlying = false;
			break;
		case 3:
			break;
		}
	}

	public static boolean isArmorSpecialCapable(ItemStack armor)
	{
		return armor.getItemDamage() == 1;
	}

}