package jotato.quantumflux.items;

import java.util.List;

import jotato.quantumflux.helpers.NbtHelpers;
import jotato.quantumflux.machines.telepad.BlockTelepad;
import jotato.quantumflux.machines.telepad.TileTelepad;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class ItemLinkingCard extends ItemBase
{
	private static final String DIMKEY = "linked_dimension";
	private static final String BLOCKPOS = "blockPOS";

	public ItemLinkingCard()
	{
		super("linkingCard");
		setMaxStackSize(1);
	}

	@Override
	public EnumActionResult onItemUse(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand,
			EnumFacing facing, float hitX, float hitY, float hitZ)
	{
		ItemStack stack = player.getHeldItem(hand);
		if (worldIn.getBlockState(pos).getBlock() instanceof BlockTelepad)
		{
			if (NbtHelpers.keyExists(stack, BLOCKPOS))
			{
				return linkTelepads(stack, player, worldIn, pos);
			} else
			{
				return setLocation(stack, player, worldIn, pos);
			}
		}
		return EnumActionResult.PASS;

	}

	private EnumActionResult setLocation(ItemStack stack, EntityPlayer playerIn, World worldIn, BlockPos pos)
	{

		NbtHelpers.setInt(stack, DIMKEY, playerIn.dimension);

		BlockPos linkedPostion = new BlockPos(pos.getX(), pos.getY() + .25, pos.getZ());
		NbtHelpers.setLong(stack, BLOCKPOS, linkedPostion.toLong());
		
		if (!worldIn.isRemote)
		{
			ITextComponent msg = new TextComponentTranslation("chat.telepad.link.stored");
			playerIn.sendMessage(msg);
		}
		return EnumActionResult.SUCCESS;

	}

	private EnumActionResult linkTelepads(ItemStack stack, EntityPlayer playerIn, World worldIn, BlockPos pos)
	{

		if (NbtHelpers.keyExists(stack, BLOCKPOS))
		{
			
			BlockPos storedPos = BlockPos.fromLong(NbtHelpers.getLong(stack, BLOCKPOS));
			int storedDimension = NbtHelpers.getInt(stack, DIMKEY);

			if(storedPos.equals(pos)){
				return EnumActionResult.PASS;
			}
			
			if (playerIn.dimension != storedDimension)
			{
				if (!worldIn.isRemote)
				{
					ITextComponent msg = new TextComponentTranslation("chat.telepad.link.error.1");
					playerIn.sendMessage(msg);
				}
			}

			if (worldIn.getTileEntity(storedPos) instanceof TileTelepad && worldIn.getTileEntity(pos) instanceof TileTelepad)
			{
				TileTelepad first = (TileTelepad) worldIn.getTileEntity(storedPos);
				TileTelepad second = (TileTelepad) worldIn.getTileEntity(pos);

				first.linkTelepad(pos, storedDimension);
				second.linkTelepad(storedPos, storedDimension);

				if (!worldIn.isRemote)
				{
					ITextComponent msg = new TextComponentTranslation("chat.telepad.link.success");
					playerIn.sendMessage(msg);
				}
				
				NbtHelpers.deleteKey(stack, DIMKEY);
				NbtHelpers.deleteKey(stack, BLOCKPOS);

			}
		}

		return EnumActionResult.SUCCESS;

	}

	@Override
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn)
	{

		if (NbtHelpers.keyExists(stack, DIMKEY))
		{
			BlockPos pos = BlockPos.fromLong(NbtHelpers.getLong(stack, BLOCKPOS));
			tooltip.add(String.format("%s", pos.toString()));
			tooltip.add(String.format("Dimension{%d}", NbtHelpers.getInt(stack, DIMKEY)));

		}
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn)
	{
		ItemStack itemStackIn = playerIn.getHeldItem(handIn);

		if (playerIn.isSneaking())
		{
			NbtHelpers.deleteKey(itemStackIn, DIMKEY);
			NbtHelpers.deleteKey(itemStackIn, BLOCKPOS);
			if (!worldIn.isRemote)
			{
				ITextComponent msg = new TextComponentTranslation("chat.telepad.link.clear");
				playerIn.sendMessage(msg);
			}
		}
		
		
		
		return super.onItemRightClick(worldIn, playerIn, handIn);
	}

}
