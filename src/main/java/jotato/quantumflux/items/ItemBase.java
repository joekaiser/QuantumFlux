package jotato.quantumflux.items;

import java.util.List;

import jotato.quantumflux.Logger;
import jotato.quantumflux.QuantumFluxMod;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;

public class ItemBase extends Item  {

	public ItemBase(String name) {
		setUnlocalizedName(name);
		setRegistryName(name);
		ForgeRegistries.ITEMS.register(this);
		setCreativeTab(QuantumFluxMod.tab);
	}

	@Override
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {

	}
	
	public String getSimpleName(){
		return this.getUnlocalizedName().substring(5);
	}

    @SideOnly(Side.CLIENT)
    public void initModel() {
    	Logger.devLog("    Registering model for %s",getSimpleName());
        ModelLoader.setCustomModelResourceLocation(this, 0, new ModelResourceLocation(getRegistryName(), "inventory"));
    }

}
