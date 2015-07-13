package jotato.quantumflux.machine.fabricator;

import java.util.List;

import jotato.quantumflux.items.ItemBlockBase;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public class ItemBlockItemFabricator extends ItemBlockBase{

	public ItemBlockItemFabricator(Block block) {
		super(block);
	}
	
	@Override
	public int getMetadata(int i) {
		return i;
	}
	
	@Override
	public void addSimpleTooltipInformation(ItemStack itemstack,
			EntityPlayer player, List list) {
	}

}
