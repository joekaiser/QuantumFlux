package jotato.quantumflux.net;

import io.netty.buffer.ByteBuf;
import jotato.quantumflux.items.ItemMagnet;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class PacketToggleMagnet implements IMessage {

    @Override
    public void fromBytes(ByteBuf buf) {

    }

    @Override
    public void toBytes(ByteBuf buf) {

    }

    public PacketToggleMagnet() {
        
    }

    public static class Handler implements IMessageHandler<PacketToggleMagnet, IMessage> {
        @Override
        public IMessage onMessage(PacketToggleMagnet message, MessageContext ctx) {

            FMLCommonHandler.instance().getWorldThread(ctx.netHandler).addScheduledTask(() -> handle(message, ctx));
            return null;
        }

        private void handle(PacketToggleMagnet message, MessageContext ctx) {
        	EntityPlayer p = ctx.getServerHandler().player;
            NonNullList<ItemStack> inv = p.inventory.mainInventory;
        	for (int i = 0; i < inv.size(); i++) {
        		if(inv.get(i).getItem() instanceof ItemMagnet)
        			inv.get(i).setItemDamage(inv.get(i).getItemDamage() == 0 ? 1 : 0);
			}

        }
    }
}