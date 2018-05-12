package jotato.quantumflux.blocks.mobglue;

import java.util.List;

import jotato.quantumflux.helpers.ItemHelpers;
import net.minecraft.block.Block;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class ItemBlockMobGlue extends ItemBlock
{

	public ItemBlockMobGlue(Block block)
	{
		super(block);
	
	}

	@Override
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn)
	{
		super.addInformation(stack, worldIn, tooltip, flagIn);
		ItemHelpers.addFlairToList(tooltip, "block.gluetrap");
	}
	
}
