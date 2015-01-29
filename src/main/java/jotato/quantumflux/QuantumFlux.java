package jotato.quantumflux;

import jotato.quantumflux.blocks.QFBlocks;
import jotato.quantumflux.items.QFItems;
import jotato.quantumflux.proxy.CommonProxy;
import jotato.quantumflux.tileentity.TileEntityIncinerator;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraftforge.common.config.Configuration;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@Mod(modid = Reference.MODID, version = Reference.VERSION, name = Reference.MODNAME)
public class QuantumFlux
{

    @Instance(Reference.MODID)
    public static QuantumFlux instance;

    @SidedProxy(clientSide = "jotato.quantumflux.proxy.ClientProxy", serverSide = "jotato.quantumflux.proxy.CommonProxy")
    public static CommonProxy proxy;

    @EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        ConfigurationManager.init(new Configuration(event.getSuggestedConfigurationFile()));
        QFBlocks.init();
        QFItems.init();
        regiterTileEntities();
    }

    private void regiterTileEntities()
    {
        GameRegistry.registerTileEntity(TileEntityIncinerator.class, "teIncinerator");
    }

    @EventHandler
    public void init(FMLInitializationEvent event)
    {
        proxy.initCommon();
        proxy.initServer();
        proxy.initClient();
        new Recipes().init();
    }

    @EventHandler
    public void postInit(FMLPostInitializationEvent event)
    {
    }

    public static CreativeTabs tab = new CreativeTabs("tabQuantumFlux") {
        @Override
        @SideOnly(Side.CLIENT)
        public Item getTabIconItem()
        {
            return QFItems.amplificationCrystal;
        }
    };
}
