package jotato.quantumflux.registers;

import jotato.quantumflux.items.EntityItemGraphiteDust;
import jotato.quantumflux.items.netherbane.EntityNetherbane;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.entity.passive.EntityPig;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.item.ItemTossEvent;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.event.entity.player.EntityItemPickupEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class EventRegister {

	
	@SubscribeEvent
	public void onLivingDrops_enderman(LivingDropsEvent event) {
		EntityLivingBase entity = event.getEntityLiving();
	
		if (entity.worldObj.isRemote)
			return;
		
		if (entity instanceof EntityEnderman) {
			if (entity.worldObj.rand.nextDouble() < .05) {

				ItemStack enderCrystal = ItemRegister.craftingPieces.getSubItem("enderCrystal");
				if (event.getLootingLevel() > 0) {
					int bonus = entity.worldObj.rand.nextInt(event.getLootingLevel());
					enderCrystal.stackSize += bonus;
				}
				EntityItem enderCrystalEntity = new EntityItem(entity.worldObj, entity.posX,
						entity.posY, entity.posZ, enderCrystal);

				enderCrystalEntity.dimension = entity.dimension;

				event.getDrops().add(enderCrystalEntity);
			}
		}
	}
	
	@SubscribeEvent
	public void onLivingDrops_pig(LivingDropsEvent event) {
		EntityLivingBase entity = event.getEntityLiving();
		if (entity.worldObj.isRemote)
			return;
		
		if(entity instanceof EntityPig){
			if(entity.worldObj.rand.nextDouble() <.0002){
				EntityItem hamCheese = new EntityItem(entity.worldObj,
						entity.posX,entity.posY,entity.posZ,
						new ItemStack(ItemRegister.hamCheese));
				hamCheese.dimension = entity.dimension;
				
				event.getDrops().add(hamCheese);
				
			}
		}
	}

	@SubscribeEvent
	public void onItemToss(ItemTossEvent event) {
		Entity entity = event.getEntity();
		EntityItem entityItem = event.getEntityItem();
		
		if(entity.worldObj.isRemote) 
			return;
		
		ItemStack itemTossed = entityItem.getEntityItem();
		
		if (itemTossed.getItem().equals(ItemRegister.graphiteDust)) {
			entity.worldObj.spawnEntityInWorld(EntityItemGraphiteDust.convert(entityItem));
			event.setCanceled(true);
			return;
		}
		
		
		if (entityItem.dimension == -1) {
			if (itemTossed.getDisplayName().equals("Netherbane") && itemTossed.getItem().equals(Items.diamond_sword)) {
				entity.worldObj.spawnEntityInWorld(EntityNetherbane.convert(entityItem));
				event.setCanceled(true);
				
			}
		}
	}

}
