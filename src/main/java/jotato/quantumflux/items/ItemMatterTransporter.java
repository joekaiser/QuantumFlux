package jotato.quantumflux.items;

import java.util.List;

import jotato.quantumflux.Logger;
import jotato.quantumflux.helpers.NbtHelpers;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemMatterTransporter extends ItemBase {

	private static final String HAS_BLOCK = "hasblock";
	private static final String HAS_TILEENTITY = "hasentity";
	private static final String STORED_BLOCK = "block";
	private static final String STORED_TILEENTITY = "tileentity";
	private static final String STORED_METADATA = "metadata";
	private int maxDamage = 50;

	public ItemMatterTransporter() {
		super("matterTransporter");
		setMaxStackSize(1);
		setMaxDamage(maxDamage);
	}

	@Override
	public void initModel() {
		Logger.devLog("    Registering model for %s", getSimpleName());
		ModelLoader.setCustomModelResourceLocation(this, 0, new ModelResourceLocation(getRegistryName(), "inventory"));
	}

	@Override
	@SideOnly(Side.CLIENT)
	public boolean hasEffect(ItemStack item) {
		return NbtHelpers.getBoolean(item, HAS_BLOCK, false);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public void addInformation(ItemStack itemstack, EntityPlayer player, List list, boolean p4) {
		if (itemstack.getTagCompound() == null) {
			return;
		}

		if (NbtHelpers.getBoolean(itemstack, HAS_BLOCK, false)) {
			Block block = getBlockFromNbt(itemstack);
			String name = new ItemStack(block).getDisplayName();
			list.add(name);
		}
	}

	@Override
	public EnumActionResult onItemUse(EntityPlayer playerIn, World worldIn, BlockPos pos,
			EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {

		ItemStack stackIn = playerIn.getHeldItem(hand);

		boolean hasBlock = NbtHelpers.getBoolean(stackIn, HAS_BLOCK, false);
		boolean hasTE = NbtHelpers.getBoolean(stackIn, HAS_TILEENTITY, false);

		if (hasBlock) {
			int metadata = NbtHelpers.getInt(stackIn, STORED_METADATA, 0);
			Block storedBlock = getBlockFromNbt(stackIn);

			BlockPos targetPos = pos.add(facing.getDirectionVec());
			Block targetBlock = worldIn.getBlockState(targetPos).getBlock();

			if ((worldIn.isAirBlock(targetPos)) || targetBlock.isReplaceable(worldIn, targetPos)) {

				if (storedBlock.canPlaceBlockAt(worldIn, targetPos)) {

					worldIn.setBlockState(targetPos, storedBlock.getStateFromMeta(metadata));

					if (hasTE) {
						NBTTagCompound tileEntityNbtData = NbtHelpers.getCompoundTag(stackIn, STORED_TILEENTITY);
						TileEntity te = worldIn.getTileEntity(targetPos);
						if (te != null) {
							tileEntityNbtData.setInteger("x", targetPos.getX());
							tileEntityNbtData.setInteger("y", targetPos.getY());
							tileEntityNbtData.setInteger("z", targetPos.getZ());
							te.readFromNBT(tileEntityNbtData);
							te.markDirty();
							// worldIn.markBlockForUpdate(targetPos);

						}
					}

					NbtHelpers.setBoolean(stackIn, HAS_BLOCK, false);
					NbtHelpers.setBoolean(stackIn, HAS_TILEENTITY, false);

					if (storedBlock.getLocalizedName().toLowerCase().contains("spawner")) {
						stackIn.damageItem(maxDamage / 4, playerIn);
					} else {
						stackIn.damageItem(1, playerIn);
					}
					worldIn.notifyNeighborsOfStateChange(targetPos, storedBlock, true);

					playerIn.playSound(SoundEvents.ENTITY_ITEM_PICKUP, 1f,
							((worldIn.rand.nextFloat() - worldIn.rand.nextFloat()) * 0.7F + 1.0F) * 2.0F);
				}
			}
		} else if (!worldIn.isAirBlock(pos)) {
			IBlockState targetBlockState = worldIn.getBlockState(pos);
			Block targetBlock = targetBlockState.getBlock();
			int metadata = targetBlock.getMetaFromState(targetBlockState);

			if (targetBlock.blockParticleGravity == -1.0F
					|| targetBlock.getBlockHardness(targetBlockState, worldIn, pos) < 0) {
				return EnumActionResult.FAIL;
			}
			if (worldIn.getTileEntity(pos) != null) {
				NBTTagCompound savedTE = new NBTTagCompound();
				TileEntity te = worldIn.getTileEntity(pos);
				te.writeToNBT(savedTE);

				NbtHelpers.setBoolean(stackIn, HAS_TILEENTITY, true);
				NbtHelpers.setTag(stackIn, STORED_TILEENTITY, savedTE);

			}

			NBTTagCompound blockNbt = new NBTTagCompound();
			ItemStack i = new ItemStack(targetBlock);

			i.writeToNBT(blockNbt);
			NbtHelpers.setTag(stackIn, STORED_BLOCK, blockNbt);
			NbtHelpers.setInt(stackIn, STORED_METADATA, metadata);

			// some blocks (like redstone) crash to client
			// so before we do the deed, make sure we can recover later
			try {
				getBlockFromNbt(stackIn);
			} catch (Exception e) {
				return EnumActionResult.FAIL;
			}

			worldIn.removeTileEntity(pos);
			worldIn.setBlockToAir(pos);

			playerIn.playSound(SoundEvents.ENTITY_ITEM_PICKUP, .15f,
					((worldIn.rand.nextFloat() - worldIn.rand.nextFloat()) * 0.7F + 1.0F) * 2.0F);
			NbtHelpers.setBoolean(stackIn, HAS_BLOCK, true);

		}
		return EnumActionResult.SUCCESS;
	}

	@Override
	public void onCreated(ItemStack itemstack, World world, EntityPlayer player) {
		NbtHelpers.setBoolean(itemstack, HAS_BLOCK, false);
		NbtHelpers.setBoolean(itemstack, HAS_TILEENTITY, false);

	}

	private Block getBlockFromNbt(ItemStack itemstack) {

		NBTTagCompound storedBlock = NbtHelpers.getCompoundTag(itemstack, STORED_BLOCK);
		ItemStack storedStack = new ItemStack(storedBlock);
		Block block = Block.getBlockFromItem(storedStack.getItem());
		return block;

	}
}