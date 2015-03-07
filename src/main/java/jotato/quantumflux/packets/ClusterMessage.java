package jotato.quantumflux.packets;

import net.minecraft.tileentity.TileEntity;
import jotato.quantumflux.tileentity.TileEntityQuibitCluster;
import io.netty.buffer.ByteBuf;
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

    
    // this constructor is required otherwise you'll get errors (used somewhere in fml through reflection)
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
      // the order is important
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
    
    public static class Handler implements IMessageHandler<ClusterMessage, IMessage>{
        @Override
        public IMessage onMessage(ClusterMessage message, MessageContext ctx) {
        	TileEntity tile = FMLClientHandler.instance().getWorldClient().getTileEntity(message.x, message.y,message.z);
        	if(tile instanceof TileEntityQuibitCluster){
        		((TileEntityQuibitCluster)tile).setEnergyStored(message.energryStored);
        	}
            return null; // no response in this case
        }
    }
  }

