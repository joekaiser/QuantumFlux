package jotato.quantumflux.items;

import java.util.List;

import cofh.api.energy.IEnergyContainerItem;
import jotato.quantumflux.ConfigMan;
import jotato.quantumflux.NbtUtils;
import jotato.quantumflux.QuantumFlux;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import net.minecraftforge.common.util.EnumHelper;

public class ItemEviscerator extends ItemPickaxe implements IEnergyContainerItem
{

    public static ToolMaterial material = EnumHelper.addToolMaterial("eviscerator", 10000, 1000, 32, 24, 100);
    private static final String energy_tag = "Energy";
    private int energyUsedPerHit = 100;

    protected ItemEviscerator(String name)
    {
        super(material);
        setUnlocalizedName(name);
        setTextureName("quantumflux:" + name);
        setCreativeTab(QuantumFlux.tab);
        GameRegistry.registerItem(this, name);
        setMaxStackSize(1);
        setMaxDamage(0);
        canRepair = false;

    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    @Override
    public void getSubItems(Item item, CreativeTabs tab, List list)
    {
        list.add(NbtUtils.setInt(new ItemStack(item), energy_tag, ConfigMan.eviscerator_maxEnergy));
        list.add(NbtUtils.setInt(new ItemStack(item), energy_tag, 0));
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
    @Override
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, EntityPlayer player, List info, boolean p_77624_4_)
    {
        info.add(EnumChatFormatting.RED + String.format(StatCollector.translateToLocal("tooltip.charge"), getEnergyStored(stack)));
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
    public boolean hitEntity(ItemStack stack, EntityLivingBase p_77644_2_, EntityLivingBase entity)
    {
        damageTool(stack, entity);
        return true;
    }

    @Override
    public boolean onBlockDestroyed(ItemStack stack, World p_150894_2_, Block p_150894_3_, int p_150894_4_, int p_150894_5_, int p_150894_6_, EntityLivingBase entity)
    {

        damageTool(stack, entity);
        return true;
    }

    public void damageTool(ItemStack item, EntityLivingBase entity)
    {
        setDamage(item, 0);
        if (getEnergyStored(item) >= energyUsedPerHit)
        {
            extractEnergy(item, energyUsedPerHit, false);
        } else
        {
            entity.attackEntityFrom(DamageSource.generic, 2);
        }
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

    @Override
    public int receiveEnergy(ItemStack container, int maxReceive, boolean simulate)
    {
        int stored = getEnergyStored(container);
        int maxICanReceive = Math.min(ConfigMan.eviscerator_maxEnergy - stored, ConfigMan.eviscerator_chargeRate);
        int toGet = Math.min(maxReceive, maxICanReceive);

        if (simulate)
        {
            return toGet;
        }

        stored += toGet;
        NbtUtils.setInt(container, energy_tag, stored);

        return toGet;
    }

    @Override
    public int extractEnergy(ItemStack container, int maxExtract, boolean simulate)
    {
        int stored = getEnergyStored(container);
        int maxICanSend = Math.min(stored, ConfigMan.eviscerator_chargeRate);
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
        return ConfigMan.eviscerator_maxEnergy;
    }

}
