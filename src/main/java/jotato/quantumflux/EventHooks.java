package jotato.quantumflux;

import jotato.quantumflux.items.ItemBattleSuit;
import jotato.quantumflux.machine.fabricator.ItemFabricatorRecipeManager;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent.Phase;
import cpw.mods.fml.common.gameevent.TickEvent.PlayerTickEvent;
import cpw.mods.fml.common.network.FMLNetworkEvent.ClientConnectedToServerEvent;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public class EventHooks
{

	@SubscribeEvent
	public void playerTick(PlayerTickEvent event)
	{
		if (event.phase == Phase.END)
		{
			EntityPlayer player = event.player;
			for (int i = 0; i < 4; i++)
			{
				ItemStack armor = player.inventory.armorItemInSlot(i);
				if (armor != null && armor.getItem() instanceof ItemBattleSuit && ItemBattleSuit.isArmorSpecialCapable(armor))
				{
					ItemBattleSuit.doSpecial(event.side,i, player);
				} else
				{
					ItemBattleSuit.removeSpecial(i, player);
				}
			}
		}

	}
	
	@SubscribeEvent
	public void ClientConnectedToServer(ClientConnectedToServerEvent  event){
		ItemFabricatorRecipeManager.refreshRecipes();
	}

}