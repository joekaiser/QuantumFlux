package jotato.quantumflux;

import jotato.quantumflux.blocks.ModBlocks;
import jotato.quantumflux.items.ModItems;
import jotato.quantumflux.machine.cluster.TileEntityQuibitCluster;
import jotato.quantumflux.machine.cluster.TileEntityQuibitCluster_1;
import jotato.quantumflux.machine.cluster.TileEntityQuibitCluster_2;
import jotato.quantumflux.machine.cluster.TileEntityQuibitCluster_3;
import jotato.quantumflux.machine.cluster.TileEntityQuibitCluster_4;
import jotato.quantumflux.machine.cluster.TileEntityQuibitCluster_5;
import jotato.quantumflux.machine.entangler.TileEntityRFEntangler;
import jotato.quantumflux.machine.entropyaccelerator.TileEntityEntropyAccelerator;
import jotato.quantumflux.machine.exciter.TileEntityRFExciter;
import jotato.quantumflux.machine.imaginarytime.TileEntityImaginaryTime;
import jotato.quantumflux.machine.infuser.TileEntityMolecularInfuser;
import jotato.quantumflux.machine.zpe.TileEntityZeroPointExtractor;
import jotato.quantumflux.packets.PacketHandler;
import jotato.quantumflux.proxy.CommonProxy;
import jotato.quantumflux.redflux.RedfluxField;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraftforge.common.config.Configuration;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStoppingEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@Mod(modid = Reference.MODID, version = Reference.VERSION, name = Reference.MODNAME, dependencies="required-after:CoFHAPI")
public class QuantumFlux
{

    @Instance(Reference.MODID)
    public static QuantumFlux instance;

    @SidedProxy(clientSide = "jotato.quantumflux.proxy.ClientProxy", serverSide = "jotato.quantumflux.proxy.CommonProxy")
    public static CommonProxy proxy;
  
    @EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
    	
        ConfigMan.init(new Configuration(event.getSuggestedConfigurationFile()));
        ModBlocks.init();
        ModItems.init();
        regiterTileEntities();
        FMLCommonHandler.instance().bus().register(new EventHooks());
    }

    //todo: maybe this should be moved into its own thing
    private void regiterTileEntities()
    {
        GameRegistry.registerTileEntity(TileEntityEntropyAccelerator.class, "tileIncinerator");
        GameRegistry.registerTileEntity(TileEntityZeroPointExtractor.class, "tileZeroPointExtractor");
        GameRegistry.registerTileEntity(TileEntityRFEntangler.class, "tileRFEntangler");
        GameRegistry.registerTileEntity(TileEntityRFExciter.class, "tileRFExciter");
        GameRegistry.registerTileEntity(TileEntityQuibitCluster_1.class, "tileQuibitCluster1");
        GameRegistry.registerTileEntity(TileEntityQuibitCluster_2.class, "tileQuibitCluster2");
        GameRegistry.registerTileEntity(TileEntityQuibitCluster_3.class, "tileQuibitCluster3");
        GameRegistry.registerTileEntity(TileEntityQuibitCluster_4.class, "tileQuibitCluster4");
        GameRegistry.registerTileEntity(TileEntityQuibitCluster_5.class, "tileQuibitCluster5");
        GameRegistry.registerTileEntity(TileEntityImaginaryTime.class, "tileImaginaryTime");
        GameRegistry.registerTileEntity(TileEntityMolecularInfuser.class, "tileMolecularInfuser");
        GameRegistry.registerTileEntity(TileEntityQuibitCluster.class, "tileQuibitCluster");
    }

    @EventHandler
    public void init(FMLInitializationEvent event)
    {
        proxy.initCommon();
        proxy.initServer();
        proxy.initClient();
        new Recipes().init();
        PacketHandler.initPackets();
    }

    @EventHandler
    public void postInit(FMLPostInitializationEvent event)
    {
    }
    
    @EventHandler
    public void serverStopping(FMLServerStoppingEvent event){
    	RedfluxField.purge();
    }

    public static CreativeTabs tab = new CreativeTabs("tabQuantumFlux") {
        @Override
        @SideOnly(Side.CLIENT)
        public Item getTabIconItem()
        {
            return ModItems.quibitCrystal;
        }
    };
}
