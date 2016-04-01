package jotato.quantumflux.registers;

import jotato.quantumflux.items.netherbane.EntityNetherbane;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.item.ItemTossEvent;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class EventRegister {


	@SubscribeEvent
	public void onItemToss(ItemTossEvent event) {
		if(event.entity.worldObj.isRemote) 
			return;
		
		if (event.entityItem.dimension == -1) {
			ItemStack itemTossed = event.entityItem.getEntityItem();
			if (itemTossed.getDisplayName().equals("Netherbane") && itemTossed.getItem().equals(Items.diamond_sword)) {
				event.entity.worldObj.spawnEntityInWorld(EntityNetherbane.convert(event.entityItem));
				event.setCanceled(true);
				
			}
		}
	}
}
