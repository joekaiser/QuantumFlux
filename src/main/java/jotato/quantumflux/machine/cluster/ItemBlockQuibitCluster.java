package jotato.quantumflux.machine.cluster;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
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
	public void addSimpleTooltipInformation(ItemStack itemstack,
			EntityPlayer player, List list) {
		list.add("Level " + itemstack.getItemDamage());
	}


}
