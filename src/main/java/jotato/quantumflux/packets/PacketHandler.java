package jotato.quantumflux.packets;

import jotato.quantumflux.Reference;
import jotato.quantumflux.packets.ClusterMessage;
import jotato.quantumflux.packets.ClusterMessage.ClusterMessageHandler;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import cpw.mods.fml.relauncher.Side;

public class PacketHandler
{
    public static int nextPacketId = 0;
    public static SimpleNetworkWrapper net;
    
    public static void initPackets(){
        net= NetworkRegistry.INSTANCE.newSimpleChannel(Reference.MODID);
        registerMessage(ClusterMessageHandler.class, ClusterMessage.class);
    }
    

    
    @SuppressWarnings({ "unchecked", "rawtypes" })
    private static void registerMessage(Class packet, Class message){
        net.registerMessage(packet,message,nextPacketId,Side.CLIENT);
        nextPacketId++;
    }

}
