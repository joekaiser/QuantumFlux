package jotato.quantumflux;

import jotato.quantumflux.items.QFItems;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public class Recipes {
	 ItemStack diamond = new ItemStack(Items.diamond);
	 ItemStack quartz = new ItemStack(Items.quartz);
	 ItemStack stone = new ItemStack(Blocks.stone);
	
	
	public  void init(){
		GameRegistry.addShapedRecipe(new ItemStack(QFItems.amplificationCrystal),"q q", " d ", "sss", 'q', quartz, 'd', diamond, 's', stone);
	}
}
