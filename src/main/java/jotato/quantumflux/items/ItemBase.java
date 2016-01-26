package jotato.quantumflux.items;

import java.util.List;

import jotato.quantumflux.Logger;
import jotato.quantumflux.QuantumFluxMod;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemBase extends Item  {

	public ItemBase(String name) {
		setUnlocalizedName(name);
		GameRegistry.registerItem(this, name);
		setCreativeTab(QuantumFluxMod.tab);
	}

	@Override
	public void addInformation(ItemStack stack, EntityPlayer playerIn, List<String> tooltip, boolean advanced) {

	}
	
	public String getSimpleName(){
		return this.getUnlocalizedName().substring(5);
	}

    @SideOnly(Side.CLIENT)
    public void initModel() {
    	Logger.info("    Registering model for %s",getSimpleName());
        ModelLoader.setCustomModelResourceLocation(this, 0, new ModelResourceLocation(getRegistryName(), "inventory"));
    }

}
