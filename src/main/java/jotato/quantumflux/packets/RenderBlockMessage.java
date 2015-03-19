package jotato.quantumflux.packets;

import net.minecraft.tileentity.TileEntity;
import jotato.quantumflux.render.IRenderState;
import io.netty.buffer.ByteBuf;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;

public class RenderBlockMessage implements IMessage
{

	private int x;
	private int y;
	private int z;
	private int state;

	public RenderBlockMessage()
	{
	}

	public RenderBlockMessage(int x, int y, int z, int state)
	{
		this.x = x;
		this.y = y;
		this.z = z;
		this.state = state;
	}

	@Override
	public void fromBytes(ByteBuf buf)
	{
		this.x = buf.readInt();
		this.y = buf.readInt();
		this.z = buf.readInt();
		this.state = buf.readInt();
	}

	@Override
	public void toBytes(ByteBuf buf)
	{
		buf.writeInt(this.x);
		buf.writeInt(this.y);
		buf.writeInt(this.z);
		buf.writeInt(this.state);
	}

	public static class RenderBlockMessageHandler implements IMessageHandler<RenderBlockMessage, IMessage>
	{
		@Override
		public IMessage onMessage(RenderBlockMessage message, MessageContext ctx)
		{
			TileEntity tile = FMLClientHandler.instance().getWorldClient().getTileEntity(message.x, message.y, message.z);
			if (tile instanceof IRenderState)
			{
				((IRenderState) tile).setState(message.state);
				FMLClientHandler.instance().getWorldClient()
						.markBlockRangeForRenderUpdate(message.x, message.y, message.z, message.x, message.y, message.z);
			}
			return null; // no response in this case
		}
	}
}
