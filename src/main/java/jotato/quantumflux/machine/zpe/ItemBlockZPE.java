package jotato.quantumflux.machine.zpe;

import java.util.List;

import jotato.quantumflux.items.ItemBlockBase;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.StatCollector;

public class ItemBlockZPE extends ItemBlockBase {

	public ItemBlockZPE(Block block) {
		super(block);
	}
	
	@Override
	public boolean hasAdvancedTooltip() {
		return true;
	}
	
	@Override
	public void addSimpleTooltipInformation(ItemStack itemstack, EntityPlayer player, List list) {
		list.add(StatCollector.translateToLocal("tooltip.zpe.help"));
	}
	
	@Override
	public void addAdvancedTooltipInformation(ItemStack itemstack, EntityPlayer player, List list) {
		list.add(EnumChatFormatting.YELLOW + StatCollector.translateToLocal("tooltip.zpe.help2"));
	}

}
