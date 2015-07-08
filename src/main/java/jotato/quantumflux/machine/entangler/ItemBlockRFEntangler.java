package jotato.quantumflux.machine.entangler;

import java.util.List;

import jotato.quantumflux.ConfigMan;
import jotato.quantumflux.items.ItemBlockBase;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.StatCollector;

public class ItemBlockRFEntangler extends ItemBlockBase {

	public ItemBlockRFEntangler(Block block) {
		super(block);
	}

	@Override
	public boolean hasAdvancedTooltip() {
		return true;
	}
	

	@Override
	public void addSimpleTooltipInformation(ItemStack itemstack, EntityPlayer player, List list) {
		list.add(StatCollector.translateToLocal("tooltip.entangler.help"));
	}
	
	@Override
	public void addAdvancedTooltipInformation(ItemStack itemstack, EntityPlayer player, List list) {
		list.add(EnumChatFormatting.YELLOW + StatCollector.translateToLocalFormatted("tooltip.entangler.buffer", ConfigMan.redfluxField_buffer));
		list.add(EnumChatFormatting.YELLOW + StatCollector.translateToLocal("tooltip.entangler.output"));
		list.add(EnumChatFormatting.YELLOW + StatCollector.translateToLocalFormatted("tooltip.entangler.efficiency", ConfigMan.rfExciter_Efficiency*100));
	}
	
	
}
