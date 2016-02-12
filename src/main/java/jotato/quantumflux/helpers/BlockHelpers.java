package jotato.quantumflux.helpers;

import java.text.NumberFormat;

import cofh.api.energy.IEnergyHandler;
import cofh.api.energy.IEnergyReceiver;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.StatCollector;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

public final class BlockHelpers {
	
	public static final PropertyDirection FACING = PropertyDirection.create("facing");
	
	public static BlockPos getBlockPosFromXYZ(double x, double y, double z) {
		return new BlockPos(new Vec3(x, y, z));
	}
	
	public static boolean BroadcastRFStored(EntityPlayer playerIn, TileEntity te){
		
		if (playerIn.getCurrentEquippedItem() == null) {

			if (te instanceof IEnergyHandler) {
				IEnergyHandler energy = (IEnergyHandler)te;
				String stored = StatCollector.translateToLocalFormatted("chat.rfStored", NumberFormat.getInstance().format(energy.getEnergyStored(null)));
				String capacity = StatCollector.translateToLocalFormatted("chat.rfStored.max", NumberFormat.getInstance().format(energy.getMaxEnergyStored(null)));
				playerIn.addChatMessage(new ChatComponentText(EnumChatFormatting.LIGHT_PURPLE + stored + EnumChatFormatting.GOLD + capacity));
				return true;
			}
		}
		
		return false;
	}
	
}
