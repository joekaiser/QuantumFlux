package jotato.quantumflux.items.netherbane;

import java.util.List;


import jotato.quantumflux.Logger;
import jotato.quantumflux.QuantumFluxMod;
import jotato.quantumflux.helpers.ItemHelpers;
import jotato.quantumflux.registers.ItemRegister;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;

public class ItemNetherbane extends ItemSword {

	public ItemNetherbane() {
		super(ItemRegister.netherBaneMaterial);
		String name = "netherbane";
		setRegistryName(name);
		ForgeRegistries.ITEMS.register(this);
		setUnlocalizedName(name);
		setCreativeTab(QuantumFluxMod.tab);
		setMaxStackSize(1);
	}

	@SideOnly(Side.CLIENT)
	public void initModel() {
		Logger.devLog("    Registering model for netherbane");
		ModelLoader.setCustomModelResourceLocation(this, 0, new ModelResourceLocation(getRegistryName(), "inventory"));
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
		ItemHelpers.addFlairToList(tooltip, "item.netherBane");
	}

	@Override
	public boolean hitEntity(ItemStack stack, EntityLivingBase target, EntityLivingBase attacker) {

		if (attacker.world.getWorldTime() >= 13000)
			attacker.heal(.7f);

		return super.hitEntity(stack, target, attacker);
	}

}