package jotato.quantumflux.machine.entropyaccelerator;

import java.util.List;

import jotato.quantumflux.ConfigMan;
import jotato.quantumflux.items.ItemBlockBase;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.StatCollector;

public class ItemBlockEntropyAccelerator extends ItemBlockBase{

	public ItemBlockEntropyAccelerator(Block p_i45328_1_) {
		super(p_i45328_1_);
	}
	
	@Override
	public boolean hasAdvancedTooltip() {
		return true;
	}

	@Override
	public void addSimpleTooltipInformation(ItemStack itemstack, EntityPlayer player, List list) {
		list.add(StatCollector.translateToLocal("tooltip.accelerator.help"));
	}
	
	@Override
	public void addAdvancedTooltipInformation(ItemStack itemstack, EntityPlayer player, List list) {
		list.add(EnumChatFormatting.YELLOW + StatCollector.translateToLocalFormatted("tooltip.accelerator.expanded", ConfigMan.incinerator_output));
	}
		
}
