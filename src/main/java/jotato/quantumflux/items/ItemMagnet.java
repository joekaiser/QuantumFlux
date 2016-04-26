package jotato.quantumflux.items;

import java.util.Iterator;

import cpw.mods.fml.common.eventhandler.Event.Result;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.gameevent.PlayerEvent.ItemPickupEvent;
import jotato.quantumflux.ConfigMan;
import jotato.quantumflux.util.ModUtils;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.item.EntityXPOrb;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.EntityItemPickupEvent;

public class ItemMagnet extends ItemBase
{

	private int distanceFromPlayer;

	public ItemMagnet()
	{
		super("magnet");
		setMaxStackSize(1);
		this.distanceFromPlayer = ConfigMan.magnet_strength;
		canRepair = false;
		setMaxDamage(0);

	}

	@Override
	public boolean hasEffect(ItemStack item)
	{
		return isActivated(item);
	}

	@Override
	public ItemStack onItemRightClick(ItemStack item, World world, EntityPlayer player)
	{
		if (!world.isRemote && player.isSneaking())
		{
			item.setItemDamage(item.getItemDamage() == 0 ? 1 : 0);
		}
		return item;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public void onUpdate(ItemStack item, World world, Entity entity, int i, boolean f)
	{
		if (world.isRemote)
			return;
		if (!isActivated(item))
			return;
		if (!(entity instanceof EntityPlayer))
			return;

		EntityPlayer player = (EntityPlayer) entity;

		// items
		Iterator iterator = ModUtils.getEntitiesInRange(EntityItem.class, world, (int) player.posX, (int) player.posY, (int) player.posZ,
				this.distanceFromPlayer).iterator();
		while (iterator.hasNext())
		{
			EntityItem itemToGet = (EntityItem) iterator.next();
			itemToGet.delayBeforeCanPickup = 50;

			EntityItemPickupEvent pickupEvent = new EntityItemPickupEvent(player, itemToGet);
			ItemPickupEvent itemPickupEvent = new ItemPickupEvent(player, itemToGet);
			MinecraftForge.EVENT_BUS.post(pickupEvent);
			FMLCommonHandler.instance().bus().post(itemPickupEvent);
			ItemStack itemStackToGet = itemToGet.getEntityItem();
			int stackSize = itemStackToGet.stackSize;
			
			
			if (pickupEvent.getResult() == Result.ALLOW || stackSize <= 0 || itemPickupEvent.pickedUp == itemToGet || player.inventory.addItemStackToInventory(itemStackToGet))
			{

			
				player.onItemPickup(itemToGet, stackSize);

				world.playSoundAtEntity(player, "random.pop", 0.15F,
						((world.rand.nextFloat() - world.rand.nextFloat()) * 0.7F + 1.0F) * 2.0F);

			}
		}

		// xp
		iterator = ModUtils.getEntitiesInRange(EntityXPOrb.class, world, (int) player.posX, (int) player.posY, (int) player.posZ,
				this.distanceFromPlayer).iterator();
		while (iterator.hasNext())
		{
			EntityXPOrb xpToGet = (EntityXPOrb) iterator.next();
			if (xpToGet.isDead || xpToGet.isInvisible())
			{
				continue;
			}
			int xpAmount = xpToGet.xpValue;
			xpToGet.xpValue = 0;
			player.xpCooldown = 0;
			player.addExperience(xpAmount);
			xpToGet.setDead();
			xpToGet.setInvisible(true);
			world.playSoundAtEntity(player, "random.orb", 0.08F, 0.5F * ((world.rand.nextFloat() - world.rand.nextFloat()) * 0.7F + 1.8F));
		}
	}

	protected boolean isActivated(ItemStack item)
	{
		return item.getItemDamage() == 1;
	}

}
