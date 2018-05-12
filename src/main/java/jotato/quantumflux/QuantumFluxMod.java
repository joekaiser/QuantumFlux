package jotato.quantumflux;

import jotato.quantumflux.registers.ItemRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@Mod(modid = QuantumFluxMod.MODID, version = QuantumFluxMod.VERSION, name = QuantumFluxMod.MODNAME)
public class QuantumFluxMod {
	public static final String MODID = "quantumflux";
	public static final String MODNAME = "QuantumFlux";
	public static final String VERSION = "2.0.11";
	public static final String TEXTURE_BASE = MODID + ":";

	public QuantumFluxMod() {
	Logger.info("Helloooooooo, everybody!");
	}
	
	@Instance(QuantumFluxMod.MODID)
	public static QuantumFluxMod instance;
	

	@SidedProxy(clientSide = "jotato.quantumflux.ProxyClient", serverSide = "jotato.quantumflux.ProxyCommon")
	public static ProxyCommon proxy;

	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		ConfigMan.init(new Configuration(event.getSuggestedConfigurationFile()));
		proxy.preInit();
		
	}

	@EventHandler
	public void init(FMLInitializationEvent event) {
		proxy.init();
	}

	@EventHandler
	public void postInit(FMLPostInitializationEvent event) {
		proxy.postInit();
	}

	public static CreativeTabs tab = new CreativeTabs("tabQuantumFlux") {
		@Override
		@SideOnly(Side.CLIENT)
		public ItemStack getTabIconItem() {
			return ItemRegister.craftingPieces.getSubItem("quibitCrystal");
		}
	};

}
