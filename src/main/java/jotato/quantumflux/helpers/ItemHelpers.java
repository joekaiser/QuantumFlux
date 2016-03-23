package jotato.quantumflux.helpers;

import java.util.List;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentTranslation;

public final class ItemHelpers {

	public static void addFlairToList(List<String> tooltip, String flairName) {
		String flair = "flair." + flairName;
		
		ITextComponent message = new TextComponentTranslation(flair);
		tooltip.add(message.getFormattedText());
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static boolean isPlayerHolding(Class c, EntityPlayer p, EnumHand hand) {

		ItemStack heldItem = p.getHeldItem(hand);

		return heldItem != null && heldItem.getItem() != null && c.isAssignableFrom(heldItem.getItem().getClass());
	}
}
