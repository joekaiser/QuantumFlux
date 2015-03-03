package jotato.quantumflux.packets;

import jotato.quantumflux.Logger;
import io.netty.buffer.ByteBuf;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;


  public  class RFMessage implements IMessage
  {
    private int value;

    
    // this constructor is required otherwise you'll get errors (used somewhere in fml through reflection)
    public RFMessage() {}
    
    public RFMessage(int value)
    {
      this.value=value;
    }
    
    @Override
    public void fromBytes(ByteBuf buf)
    {
      // the order is important
      this.value = buf.readInt();
     
    }
    
    @Override
    public void toBytes(ByteBuf buf)
    {
      buf.writeInt(this.value);
    }
    
    public static class Handler implements IMessageHandler<RFMessage, IMessage>{
        @Override
        public IMessage onMessage(RFMessage message, MessageContext ctx) {
           Logger.info(String.format("Received %s from %s", message.value, ctx.getServerHandler().playerEntity.getDisplayName()));
            return null; // no response in this case
        }
    }
  }

