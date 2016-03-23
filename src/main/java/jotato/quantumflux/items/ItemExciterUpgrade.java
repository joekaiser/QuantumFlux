package jotato.quantumflux.items;

import jotato.quantumflux.machines.exciter.BlockRFExciter;
import jotato.quantumflux.registers.BlockRegister;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

public class ItemExciterUpgrade extends ItemBase {

	public ItemExciterUpgrade() {
		super("exciterUpgrade");
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(ItemStack itemStackIn, World worldIn, EntityPlayer playerIn,
			EnumHand hand) {
		if (worldIn.isRemote)
			new ActionResult(EnumActionResult.PASS, itemStackIn);

		RayTraceResult movingobjectposition = this.getMovingObjectPositionFromPlayer(worldIn, playerIn, true);

		if (movingobjectposition != null) {

			if (movingobjectposition.typeOfHit == RayTraceResult.Type.BLOCK) {

				Block target = worldIn.getBlockState(movingobjectposition.getBlockPos()).getBlock();

				if (target == BlockRegister.rfExciter) {

					int upgradesToApply = playerIn.isSneaking() ? itemStackIn.stackSize : 1;
					int used = ((BlockRFExciter) BlockRegister.rfExciter).addUpgrade(worldIn,
							movingobjectposition.getBlockPos(), upgradesToApply);
					itemStackIn.stackSize -= used;

				}
			}

		}
		return new ActionResult(EnumActionResult.PASS, itemStackIn);
	}

}
