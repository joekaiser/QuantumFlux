package jotato.quantumflux.machine.imaginarytime;

import java.util.List;

import jotato.quantumflux.ConfigMan;
import jotato.quantumflux.items.ItemBlockBase;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.StatCollector;

public class ItemBlockImaginaryTime extends ItemBlockBase {

	public ItemBlockImaginaryTime(Block block) {
		super(block);
	}
	
	@Override
	public boolean hasAdvancedTooltip() {
		return true;
	}
	
	@Override
	public void addSimpleTooltipInformation(ItemStack itemstack, EntityPlayer player, List list) {
		list.add(StatCollector.translateToLocal("tooltip.imaginarytime.help"));
	}
	
	@Override
	public void addAdvancedTooltipInformation(ItemStack itemstack, EntityPlayer player, List list) {
		list.add(EnumChatFormatting.YELLOW + StatCollector.translateToLocalFormatted("tooltip.imaginarytime.requirements",ConfigMan.imaginaryTime_energyRequirement));
	}

}
