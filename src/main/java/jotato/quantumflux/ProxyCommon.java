package jotato.quantumflux;

import jotato.quantumflux.registers.BlockRegister;
import jotato.quantumflux.registers.EventRegister;
import jotato.quantumflux.registers.ItemRegister;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.passive.EntityPig;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.item.ItemTossEvent;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class ProxyCommon {

	public void preInit() {
		ItemRegister.init();
		BlockRegister.init();
	}

	public void init() {
		
	}

	public void postInit() {
		registerTickHandlers();
	}

	public void registerKeyBindings() {

	}
	
	public void registerTickHandlers() {
		MinecraftForge.EVENT_BUS.register(new EventRegister());
	}

	

}
