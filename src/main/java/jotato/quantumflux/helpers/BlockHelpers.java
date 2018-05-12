package jotato.quantumflux.helpers;

import java.text.NumberFormat;

import cofh.redstoneflux.api.IEnergyHandler;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentTranslation;

public final class BlockHelpers {

	public static final PropertyDirection FACING = PropertyDirection.create("facing");

	public static BlockPos getBlockPosFromXYZ(double x, double y, double z) {
		return new BlockPos(new Vec3d(x, y, z));
	}

	public static boolean BroadcastRFStored(EntityPlayer playerIn, TileEntity te) {
		// if (playerIn.getCurrentEquippedItem() == null) {

		if (te instanceof IEnergyHandler) {
			IEnergyHandler energy = (IEnergyHandler) te;
			
			ITextComponent stored = new TextComponentTranslation("chat.rfStored",
					NumberFormat.getInstance().format(energy.getEnergyStored(null)));
			ITextComponent capacity = new TextComponentTranslation("chat.rfStored.max",
					NumberFormat.getInstance().format(energy.getMaxEnergyStored(null)));
			playerIn.sendMessage(stored);
			playerIn.sendMessage(capacity);
			return true;
		}
		// }

		return false;
	}

}
