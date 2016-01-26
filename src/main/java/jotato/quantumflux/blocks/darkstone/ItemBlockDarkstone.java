package jotato.quantumflux.blocks.darkstone;

import cofh.api.energy.IEnergyContainerItem;
import jotato.quantumflux.helpers.NbtHelpers;
import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class ItemBlockDarkstone extends ItemBlock {


	public ItemBlockDarkstone(Block block) {
		super(block);
		setHasSubtypes(true);
		setMaxDamage(0);
	}

	@Override
	public int getMetadata(int metadata) {
		return metadata;
	}

	@Override
	public String getUnlocalizedName(ItemStack stack) {
		EnumDarkstone level = EnumDarkstone.byMetadata(stack.getMetadata());
	    return super.getUnlocalizedName() + "." + level.toString();
	}
}