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
	public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
		ItemStack itemStackIn = playerIn.getHeldItem(handIn);
		if (worldIn.isRemote)
			new ActionResult(EnumActionResult.PASS, itemStackIn);

		
		RayTraceResult movingobjectposition = this.rayTrace(worldIn, playerIn, true);

		if (movingobjectposition != null) {

			if (movingobjectposition.typeOfHit == RayTraceResult.Type.BLOCK) {

				Block target = worldIn.getBlockState(movingobjectposition.getBlockPos()).getBlock();

				if (target == BlockRegister.rfExciter) {

					int upgradesToApply = playerIn.isSneaking() ? itemStackIn.getCount() : 1;
					int used = ((BlockRFExciter) BlockRegister.rfExciter).addUpgrade(worldIn,
							movingobjectposition.getBlockPos(), upgradesToApply);
					itemStackIn.shrink(used);

				}
			}

		}
		return new ActionResult(EnumActionResult.PASS, itemStackIn);
	}

}
