package jotato.quantumflux.packets;

import net.minecraft.tileentity.TileEntity;
import io.netty.buffer.ByteBuf;
import jotato.quantumflux.machine.cluster.TileEntityQuibitCluster;
import jotato.quantumflux.machine.cluster.TileEntityQuibitCluster_Deprecated;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;


  public  class ClusterMessage implements IMessage
  {
	  private int x;
	  private int y;
	  private int z;
	  private int energryStored;

    public ClusterMessage() {}
    
    public ClusterMessage(int x, int y, int z, int energyStored)
    {
      this.x=x;
      this.y=y;
      this.z=z;
      this.energryStored = energyStored;
    }
    
    @Override
    public void fromBytes(ByteBuf buf)
    {
      this.x=buf.readInt();
      this.y=buf.readInt();
      this.z=buf.readInt();
      this.energryStored = buf.readInt();
     
    }
    
    @Override
    public void toBytes(ByteBuf buf)
    {
    	 buf.writeInt(this.x);
    	 buf.writeInt(this.y);
    	 buf.writeInt(this.z);
    	 buf.writeInt(this.energryStored);
    }
    
    public static class ClusterMessageHandler implements IMessageHandler<ClusterMessage, IMessage>{
        @Override
        public IMessage onMessage(ClusterMessage message, MessageContext ctx) {
        	TileEntity tile = FMLClientHandler.instance().getWorldClient().getTileEntity(message.x, message.y,message.z);
        	if(tile instanceof TileEntityQuibitCluster_Deprecated){
        		((TileEntityQuibitCluster_Deprecated)tile).setEnergyStored(message.energryStored);
        	}
        	
        	if(tile instanceof TileEntityQuibitCluster){
        		((TileEntityQuibitCluster)tile).setEnergyStored(message.energryStored);
        	}
            return null; 
        }
    }
  }

