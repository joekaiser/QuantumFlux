package jotato.quantumflux;

import jotato.quantumflux.items.ItemBattleSuit;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent.PlayerTickEvent;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public class EventHooks
{

	@SubscribeEvent
	public void playerTick(PlayerTickEvent event)
	{
		EntityPlayer player = event.player;
		for (int i = 0; i < 4; i++)
		{
			ItemStack armor = player.inventory.armorItemInSlot(i);
			if (armor != null && armor.getItem() instanceof ItemBattleSuit && ItemBattleSuit.isArmorSpecialCapable(armor))
			{
				ItemBattleSuit.doSpecial(i, player);
			} else
			{
				ItemBattleSuit.removeSpecial(i, player);
			}
		}

	}

}