package jotato.quantumflux.items.netherbane;

import jotato.quantumflux.helpers.TimeTracker;
import jotato.quantumflux.registers.ItemRegister;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public class EntityItemNetherbane extends EntityItem {

	private TimeTracker timer;
	private int step = 0;
	private int subStep = 0;
	private boolean conversionComplete = false;

	private static final String[] names = new String[] { "Ghost Rider", "The Lich", "Jack Skellington", "Skeletor",
			"Bonejangles", "Dry Bones", "Smitty Werbenjagermanjensen" };

	public EntityItemNetherbane(World world, double x, double y, double z, ItemStack itemStack) {
		super(world, x, y, z, itemStack);
		this.isImmuneToFire = true;
		setNoDespawn();
		setPickupDelay(100);
		timer = new TimeTracker();

	}

	@Override
	public boolean isEntityInvulnerable(DamageSource source) {
		return true;
	}

	@Override
	public boolean isImmuneToExplosions() {
		return true;
	}

	@Override
	public void onUpdate() {

		if (worldObj.isRemote || conversionComplete) {
			return;
		}

		super.onUpdate();

		if (timer.hasTimePartPassed(worldObj, TimeTracker.TIME_PART_HALF + rand.nextInt(15))) {
			EntityLightningBolt lightning = new EntityLightningBolt(worldObj, posX - rand.nextInt(7) + rand.nextInt(7),
					posY, posZ - rand.nextInt(7) + rand.nextInt(7), true);

			lightning.dimension = this.dimension;
			worldObj.addWeatherEffect(lightning);
		}

		switch (step) {
		case 0:
			if (onGround) {
				if (timer.hasDelayPassed(worldObj, 100)) {
					nextStep();
				}
			}
			break;
		case 1:
			if (timer.hasDelayPassed(worldObj, 8 + rand.nextInt(15))) {

				worldObj.createExplosion(this, posX + rand.nextDouble(), posY, posZ + rand.nextDouble(), .1f, false);

				EntitySkeleton skele = new EntitySkeleton(worldObj);
				skele.dimension = this.dimension;
				skele.setLocationAndAngles(posX, posY, posZ, MathHelper.wrapDegrees(rand.nextFloat() * 360.0F),
						0.0F);

				skele.addPotionEffect(
						new PotionEffect(Potion.getPotionFromResourceLocation("strength"), 20, 2, false, false));
				skele.addPotionEffect(
						new PotionEffect(Potion.getPotionFromResourceLocation("regeneration"), 20, 1, false, false));
				skele.addPotionEffect(
						new PotionEffect(Potion.getPotionFromResourceLocation("speed"), 20, 1, false, false));
				skele.setCustomNameTag(names[subStep]);
				//skele.setSkeletonType(1);
				skele.playLivingSound();
				skele.hurtResistantTime = 200;

				worldObj.spawnEntityInWorld(skele);

				subStep++;
				if (subStep > 6) {
					nextStep();
				}
			}
			break;
		case 2:
			if (timer.hasDelayPassed(worldObj, 400)) {
				this.setEntityItemStack(new ItemStack(ItemRegister.netherbane));
				conversionComplete = true;
				nextStep();

			}
			break;
		}

	}

	private void nextStep() {
		step++;
		subStep = 0;
	}

	public static EntityItemNetherbane convert(EntityItem entity) {
		EntityItemNetherbane newEntity = new EntityItemNetherbane(entity.worldObj, entity.posX, entity.posY, entity.posZ,
				entity.getEntityItem());
		newEntity.dimension = entity.dimension;
		newEntity.motionX = entity.motionX;
		newEntity.motionY = entity.motionY;
		newEntity.motionZ = entity.motionZ;

		newEntity.hoverStart = entity.hoverStart;
		newEntity.lifespan = entity.lifespan;
		return newEntity;

	}

}
