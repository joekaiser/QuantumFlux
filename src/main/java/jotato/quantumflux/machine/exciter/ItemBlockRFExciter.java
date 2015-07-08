package jotato.quantumflux.machine.exciter;

import java.util.List;

import jotato.quantumflux.items.ItemBlockBase;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.StatCollector;

public class ItemBlockRFExciter extends ItemBlockBase{

	public ItemBlockRFExciter(Block block) {
		super(block);
	}
	
	@Override
	public boolean hasAdvancedTooltip() {
		return true;
	}
	
	@Override
	public void addSimpleTooltipInformation(ItemStack itemstack, EntityPlayer player, List list) {
		list.add(StatCollector.translateToLocal("tooltip.rfexciter.help"));
	}
	
	@Override
	public void addAdvancedTooltipInformation(ItemStack itemstack, EntityPlayer player, List list) {
		list.add(EnumChatFormatting.YELLOW + StatCollector.translateToLocal("tooltip.rfexciter.upgrade"));
	}

}
