package jotato.quantumflux.machine.cluster;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.StatCollector;
import jotato.quantumflux.items.ItemBlockBase;

public class ItemBlockQuibitCluster extends ItemBlockBase {

	public ItemBlockQuibitCluster(Block block) {
		super(block);
		setHasSubtypes(true);
	}

	@Override
	public int getMetadata(int i) {
		return i;
	}
	
	
	@Override
	public boolean hasAdvancedTooltip() {
		return true;
	}
	
	@Override
	public void addSimpleTooltipInformation(ItemStack itemstack,
			EntityPlayer player, List list) {
		list.add(StatCollector.translateToLocal("tooltip.quibitcluster.help"));
	}
	
	@Override
	public void addAdvancedTooltipInformation(ItemStack itemstack, EntityPlayer player, List list) {
		QuibitClusterSettings s = BlockQuibitCluster.getQuibitClusterSettings(itemstack);
		
		list.add(EnumChatFormatting.YELLOW + StatCollector.translateToLocalFormatted("tooltip.quibitcluster.capacity", s.getCapacityFormatted()));
		list.add(EnumChatFormatting.YELLOW + StatCollector.translateToLocalFormatted("tooltip.quibitcluster.transfer", s.getTransferRateFormatted()));
	}


}
