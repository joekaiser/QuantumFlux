package jotato.quantumflux.registers;

import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class EventRegister {

	@SubscribeEvent
	public void onLivingDrops(LivingDropsEvent event) {
		if (event.entity.worldObj.isRemote)
			return;

		// if (event.source == TileVampiricGenerator.vampiricDamage) {
		// }

		if (event.entityLiving instanceof EntityEnderman) {
			if (event.entity.worldObj.rand.nextDouble() < .5) {

				ItemStack enderCrystal = ItemRegister.craftingPieces.getSubItem("enderCrystal");
				if (event.lootingLevel > 0) {
					int bonus = event.entity.worldObj.rand.nextInt(event.lootingLevel);
					enderCrystal.stackSize += bonus;
				}
				EntityItem enderCrystalEntity = new EntityItem(event.entityLiving.worldObj, event.entityLiving.posX,
						event.entityLiving.posY, event.entityLiving.posZ, enderCrystal);

				enderCrystalEntity.dimension = event.entityLiving.dimension;

				event.drops.add(enderCrystalEntity);

			}
		}
	}
}
