package jotato.quantumflux.machine.cluster;


import java.util.List;

import jotato.quantumflux.items.ItemBlockBase;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;

public class ItemBlockQuibitCluster_Deprecated extends ItemBlockBase{

	public ItemBlockQuibitCluster_Deprecated(Block block) {
		super(block);
		// TODO Auto-generated constructor stub
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public void addSimpleTooltipInformation(ItemStack itemstack, EntityPlayer player, List list) {
		list.add(StatCollector.translateToLocal("tooltip.quibitcluster_d.help"));
	}
	

}
