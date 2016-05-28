package jotato.quantumflux.items;

import java.util.List;

import com.sun.jna.platform.win32.WinDef.WPARAM;

import jotato.quantumflux.Logger;
import jotato.quantumflux.helpers.ItemHelpers;
import jotato.quantumflux.helpers.NbtHelpers;
import jotato.quantumflux.machines.telepad.BlockTelepad;
import jotato.quantumflux.registers.BlockRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.util.Constants.NBT;

public class ItemLinkingCard extends ItemBase{
	private static final String DIMKEY="linked_dimension";
	private static final String BLOCKPOS="blockPOS";
	
	public ItemLinkingCard() {
		super("linkingCard");
		setMaxStackSize(1);
	}
	
	@Override
	public EnumActionResult onItemUse(ItemStack stack, EntityPlayer playerIn, World worldIn, BlockPos pos,
			EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		
		if(worldIn.getBlockState(pos).getBlock() instanceof BlockTelepad){
			NbtHelpers.setInt(stack, DIMKEY, playerIn.dimension);
			
			BlockPos linkedPostion = new BlockPos(pos.getX(),pos.getY()+.25,pos.getZ());
			NbtHelpers.setLong(stack, BLOCKPOS, linkedPostion.toLong());
			return EnumActionResult.SUCCESS;
		}
		
		return EnumActionResult.PASS;
	}
	
	@Override
	public void addInformation(ItemStack stack, EntityPlayer playerIn, List<String> tooltip, boolean advanced) {
		String loc = "";
		
		if(NbtHelpers.keyExists(stack, DIMKEY)){
			BlockPos pos = BlockPos.fromLong(NbtHelpers.getLong(stack, BLOCKPOS));
			tooltip.add(String.format("%s",pos.toString()));
			tooltip.add(String.format("Dimension{%d}",NbtHelpers.getInt(stack, DIMKEY)));
		
		}
	}
	
	@Override
	public ActionResult<ItemStack> onItemRightClick(ItemStack itemStackIn, World worldIn, EntityPlayer playerIn,
			EnumHand hand) {
		
		if(playerIn.isSneaking()){
			NbtHelpers.deleteKey(itemStackIn, DIMKEY);
			NbtHelpers.deleteKey(itemStackIn, BLOCKPOS);
		}
		return super.onItemRightClick(itemStackIn, worldIn, playerIn, hand);
	}
	

}
