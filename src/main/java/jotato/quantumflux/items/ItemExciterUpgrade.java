package jotato.quantumflux.items;

import jotato.quantumflux.machines.exciter.BlockRFExciter;
import jotato.quantumflux.registers.BlockRegister;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

public class ItemExciterUpgrade extends ItemBase {

	public ItemExciterUpgrade() {
		super("exciterUpgrade");
	}
	
	@Override
	public ItemStack onItemRightClick(ItemStack itemStack, World world, EntityPlayer player) {
		MovingObjectPosition movingobjectposition = this.getMovingObjectPositionFromPlayer(world, player, true);
		if (world.isRemote)
			return itemStack;

		if (movingobjectposition != null) {
			if (movingobjectposition.typeOfHit == MovingObjectPosition.MovingObjectType.BLOCK) {

				Block target = world.getBlockState(movingobjectposition.getBlockPos()).getBlock();

				if (target == BlockRegister.rfExciter) {

					int upgradesToApply = player.isSneaking() ? itemStack.stackSize : 1;
					int used = ((BlockRFExciter)BlockRegister.rfExciter).addUpgrade(world, movingobjectposition.getBlockPos(), upgradesToApply);
					itemStack.stackSize -= used;

				}
			}

		}
		return itemStack;

	}

}
