package jotato.quantumflux.packets;

import net.minecraft.tileentity.TileEntity;
import io.netty.buffer.ByteBuf;
import jotato.quantumflux.redflux.ISetEnergy;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;


  public class EnergyStorageMessage implements IMessage
  {
	  private int x;
	  private int y;
	  private int z;
	  private int energyStored;

    public EnergyStorageMessage() {}
    
    public EnergyStorageMessage(int x, int y, int z, int energyStored)
    {
      this.x=x;
      this.y=y;
      this.z=z;
      this.energyStored = energyStored;
    }
    
    @Override
    public void fromBytes(ByteBuf buf)
    {
      this.x=buf.readInt();
      this.y=buf.readInt();
      this.z=buf.readInt();
      this.energyStored = buf.readInt();
     
    }
    
    @Override
    public void toBytes(ByteBuf buf)
    {
    	 buf.writeInt(this.x);
    	 buf.writeInt(this.y);
    	 buf.writeInt(this.z);
    	 buf.writeInt(this.energyStored);
    }
    
    public static class EnergyStorageMessageHandler implements IMessageHandler<EnergyStorageMessage, IMessage>{
        @Override
        public IMessage onMessage(EnergyStorageMessage message, MessageContext ctx) {
        	TileEntity tile = FMLClientHandler.instance().getWorldClient().getTileEntity(message.x, message.y,message.z);
        	if(tile instanceof ISetEnergy){
        		((ISetEnergy)tile).setEnergyStored(message.energyStored);
        	}
        	
            return null; 
        }
    }
  }

