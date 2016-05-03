package jotato.quantumflux.items;

import java.util.Iterator;

import jotato.quantumflux.Logger;
import jotato.quantumflux.helpers.EntityHelpers;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.item.EntityXPOrb;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.EntityItemPickupEvent;
import net.minecraftforge.event.entity.player.PlayerPickupXpEvent;
import net.minecraftforge.fml.common.eventhandler.Event.Result;

public class ItemMagnet extends ItemBase {
	protected double distanceFromPlayer;
	protected static String name = "magnet";

	public ItemMagnet() {
		super(name);
		setMaxStackSize(1);
		this.distanceFromPlayer = 6.5;
		canRepair = false;
		setMaxDamage(0);

	}

	@Override
	public void initModel() {
		Logger.info("    Registering model for %s", getSimpleName());
		ModelLoader.setCustomModelResourceLocation(this, 0, new ModelResourceLocation(getRegistryName(), "inventory"));
		ModelLoader.setCustomModelResourceLocation(this, 1, new ModelResourceLocation(getRegistryName(), "inventory"));
	}

	@Override
	public boolean hasEffect(ItemStack item) {
		return isActivated(item);
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(ItemStack itemStackIn, World worldIn, EntityPlayer playerIn,
			EnumHand hand) {
		if (!worldIn.isRemote && playerIn.isSneaking()) {
			itemStackIn.setItemDamage(itemStackIn.getItemDamage() == 0 ? 1 : 0);
		}
		return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, itemStackIn);
	}

	@SuppressWarnings("rawtypes")
	@Override
	public void onUpdate(ItemStack item, World world, Entity entity, int i, boolean f) {
		if (world.isRemote)
			return;
		if (!isActivated(item))
			return;
		if (!(entity instanceof EntityPlayer))
			return;

		EntityPlayer player = (EntityPlayer) entity;

		// items
		Iterator iterator = EntityHelpers.getEntitiesInRange(EntityItem.class, world, player.posX, player.posY,
				player.posZ, this.distanceFromPlayer).iterator();
		while (iterator.hasNext()) {
			EntityItem itemToGet = (EntityItem) iterator.next();
			itemToGet.setPosition(player.posX,player.posY,player.posZ);

			EntityItemPickupEvent pickupEvent = new EntityItemPickupEvent(player, itemToGet);
			MinecraftForge.EVENT_BUS.post(pickupEvent);
			ItemStack itemStackToGet = itemToGet.getEntityItem();
			int stackSize = itemStackToGet.stackSize;

			if (pickupEvent.getResult() == Result.ALLOW && stackSize > 0
					&& player.inventory.addItemStackToInventory(itemStackToGet)) {
				player.onItemPickup(itemToGet, stackSize);
				world.playSound(player, player.getPosition(), SoundEvents.entity_item_pickup, SoundCategory.AMBIENT,
						0.15F, ((world.rand.nextFloat() - world.rand.nextFloat()) * 0.7F + 1.0F) * 2.0F);
			}
		}

		// xp
		iterator = EntityHelpers.getEntitiesInRange(EntityXPOrb.class, world, player.posX, player.posY, player.posZ,
				this.distanceFromPlayer).iterator();
		while (iterator.hasNext()) {
			EntityXPOrb xpToGet = (EntityXPOrb) iterator.next();

			if (xpToGet.isDead || xpToGet.isInvisible()) {
				continue;
			}
			player.xpCooldown = 0;
			xpToGet.delayBeforeCanPickup=0;
			xpToGet.setPosition(player.posX,player.posY,player.posZ);
			PlayerPickupXpEvent xpEvent = new PlayerPickupXpEvent(player, xpToGet);
			MinecraftForge.EVENT_BUS.post(xpEvent);
			if(xpEvent.getResult()==Result.ALLOW){
				xpToGet.onCollideWithPlayer(player);
			}
			
		}

	}

	protected boolean isActivated(ItemStack item) {
		return item.getItemDamage() == 1;
	}
}
