package jotato.quantumflux.machines.cluster;

import cofh.api.energy.IEnergyContainerItem;
import jotato.quantumflux.helpers.NbtHelpers;
import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class ItemBlockQuibitCluster extends ItemBlock implements IEnergyContainerItem {

	private static final String energy_tag = "Energy";

	public ItemBlockQuibitCluster(Block block) {
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
		EnumQuibitCluster level = EnumQuibitCluster.byMetadata(stack.getMetadata());
	    return super.getUnlocalizedName() + "." + level.toString();
	}

	@Override
	public int receiveEnergy(ItemStack container, int maxReceive, boolean simulate) {
		int stored = getEnergyStored(container);
		int toReceive = Math.min(maxReceive,
				Math.min(getMaxEnergyStored(container) - stored, getTransferRate(container)));

		if (!simulate) {
			stored += toReceive;
			NbtHelpers.setInt(container, energy_tag, stored);
		}
		return toReceive;
	}

	@Override
	public int extractEnergy(ItemStack container, int maxExtract, boolean simulate) {
		int stored = getEnergyStored(container);
		int toExtract = Math.min(maxExtract, stored);

		if (!simulate) {
			stored -= toExtract;
			NbtHelpers.setInt(container, energy_tag, stored);
		}
		return toExtract;
	}

	@Override
	public int getEnergyStored(ItemStack container) {
		return NbtHelpers.getInt(container, energy_tag);
	}

	@Override
	public int getMaxEnergyStored(ItemStack container) {
		return BlockQuibitCluster.getQuibitClusterSettings(container).capacity;
	}

	public int getTransferRate(ItemStack container) {
		return BlockQuibitCluster.getQuibitClusterSettings(container).transferRate;
	}

	public void setEnergyStored(ItemStack container, int energy) {
		NbtHelpers.setInt(container, energy_tag, energy);
	}
}