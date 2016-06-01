package jotato.quantumflux.blocks.mobglue;

import java.util.List;

import jotato.quantumflux.helpers.ItemHelpers;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class ItemBlockMobGlue extends ItemBlock
{

	public ItemBlockMobGlue(Block block)
	{
		super(block);
	
	}

	@Override
	public void addInformation(ItemStack stack, EntityPlayer playerIn, List<String> tooltip, boolean advanced)
	{
		super.addInformation(stack, playerIn, tooltip, advanced);
		ItemHelpers.addFlairToList(tooltip, "block.gluetrap");
	}
	
}
