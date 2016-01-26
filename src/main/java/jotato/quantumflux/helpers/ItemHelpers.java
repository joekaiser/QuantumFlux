package jotato.quantumflux.helpers;

import java.util.List;

import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.JsonToNBT;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTException;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public final class ItemHelpers {
	
	public static void addFlairToList(List<String> tooltip, String flairName) {
		String flair = "flair." + flairName;
		if (StatCollector.canTranslate(flair)) {
			tooltip.add(StatCollector.translateToLocal(flair));
		} else {
			int i = 0;
			while (StatCollector.canTranslate(flair + "." + i)) {
				tooltip.add(StatCollector.translateToLocal(flair + "." + i));
				i++;
			}
		}
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static boolean isPlayerHolding(Class c, EntityPlayer p) {
		return p.getHeldItem() != null && p.getHeldItem().getItem() != null &&
				c.isAssignableFrom(p.getHeldItem().getItem().getClass());
	}
}
