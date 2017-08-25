package jotato.quantumflux.items;

import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

public class ItemVoidBucket extends ItemBase {

	public ItemVoidBucket() {
		super("voidBucket");
		setMaxStackSize(1);
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {

		ItemStack itemstack = playerIn.getHeldItem(handIn);
		RayTraceResult movingobjectposition = this.rayTrace(worldIn, playerIn, true);

		if (movingobjectposition != null) {
			if (movingobjectposition.typeOfHit == RayTraceResult.Type.BLOCK) {
				BlockPos blockPos = movingobjectposition.getBlockPos();

				if (!worldIn.canMineBlockBody(playerIn, blockPos)) {
					return new ActionResult<ItemStack>(EnumActionResult.FAIL, itemstack);
				}

				if (!playerIn.canPlayerEdit(blockPos, movingobjectposition.sideHit, itemstack)) {
					return new ActionResult<ItemStack>(EnumActionResult.FAIL, itemstack);
				}

				if (playerIn.isSneaking()) {
					deleteLiquid(worldIn, blockPos);

				} else {

					int i = blockPos.getX();
					int j = blockPos.getY();
					int k = blockPos.getZ();

					for (int j2 = j - 1; j2 < j + 1; j2++) {

						for (int i2 = -2; i2 <= 3; i2++) {
							int count = 2;
							if (i2 < 0)
								count = 2 + i2;
							else if (i2 > 0)
								count = 2 - i2;

							for (int k2 = 0; k2 <= count; k2++) {

								deleteLiquid(worldIn, new BlockPos(i + i2, j2, k + k2));
								deleteLiquid(worldIn, new BlockPos(i + i2, j2, k - k2));
							}
						}
					}
				}

			}
		}

		return new ActionResult<ItemStack>(EnumActionResult.PASS, itemstack);
	}

	private void deleteLiquid(World world, BlockPos pos) {

		Material material = world.getBlockState(pos).getMaterial();

		if (material == Material.WATER) {
			world.setBlockToAir(pos);
		}

		if (material == Material.LAVA) {
			world.setBlockToAir(pos);
		}
		if (material.isLiquid()) {
			world.setBlockToAir(pos);
		}
	}

}