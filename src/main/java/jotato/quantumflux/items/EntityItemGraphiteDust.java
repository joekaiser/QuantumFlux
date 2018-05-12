package jotato.quantumflux.items;

import jotato.quantumflux.registers.ItemRegister;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public class EntityItemGraphiteDust extends EntityItem {
	

	
	public EntityItemGraphiteDust(World world, double x, double y, double z, ItemStack itemStack) {
		super(world, x, y, z, itemStack);
		setPickupDelay(30);
		isImmuneToFire = true;
		
	}

	@Override
	public boolean attackEntityFrom(DamageSource source, float amount) {
		
		if(source.isExplosion()){
			this.setItem(ItemRegister.craftingPieces.getSubItem("industrialDiamond", this.getItem().getCount()));
		}
		
		return false;
	}

	public static EntityItemGraphiteDust convert(EntityItem entity) {
		EntityItemGraphiteDust newEntity = new EntityItemGraphiteDust(entity.world, entity.posX, entity.posY,
				entity.posZ, entity.getItem());
		newEntity.dimension = entity.dimension;
		newEntity.motionX = entity.motionX;
		newEntity.motionY = entity.motionY;
		newEntity.motionZ = entity.motionZ;

		newEntity.hoverStart = entity.hoverStart;
		newEntity.lifespan = entity.lifespan;
		return newEntity;

	}

}
