package jotato.quantumflux.machine.cluster;

import java.util.List;

import cofh.api.energy.IEnergyContainerItem;
import jotato.quantumflux.items.ItemBlockBase;
import jotato.quantumflux.util.NbtUtils;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.StatCollector;

public class ItemBlockQuibitCluster extends ItemBlockBase implements IEnergyContainerItem {

	private static final String energy_tag = "Energy";

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
		list.add(EnumChatFormatting.RED + String.format(StatCollector.translateToLocal("tooltip.charge"), getEnergyStored(itemstack)));
	}
	
	@Override
	public void addAdvancedTooltipInformation(ItemStack itemstack, EntityPlayer player, List list) {
		QuibitClusterSettings s = BlockQuibitCluster.getQuibitClusterSettings(itemstack);
		
		list.add(EnumChatFormatting.YELLOW + StatCollector.translateToLocalFormatted("tooltip.quibitcluster.capacity", s.getCapacityFormatted()));
		list.add(EnumChatFormatting.YELLOW + StatCollector.translateToLocalFormatted("tooltip.quibitcluster.transfer", s.getTransferRateFormatted()));
	}

	@Override
	public int receiveEnergy(ItemStack container, int maxReceive, boolean simulate) {
		int stored = getEnergyStored(container);
		int toReceive = Math.min(maxReceive, Math.min(getMaxEnergyStored(container) - stored, getTransferRate(container)));
		
		if (!simulate) {
			stored += toReceive;
			NbtUtils.setInt(container, energy_tag, stored);
		}
		return toReceive;
	}

	@Override
	public int extractEnergy(ItemStack container, int maxExtract, boolean simulate) {
		int stored = getEnergyStored(container);
		int toExtract = Math.min(maxExtract, stored);
		
		if (!simulate) {
			stored -= toExtract;
			NbtUtils.setInt(container, energy_tag, stored);
		}
		return toExtract;
	}

	@Override
	public int getEnergyStored(ItemStack container) {
		return NbtUtils.getInt(container, energy_tag);
	}

	@Override
	public int getMaxEnergyStored(ItemStack container) {
		return BlockQuibitCluster.getQuibitClusterSettings(container).capacity;
	}

	public int getTransferRate(ItemStack container) {
		return BlockQuibitCluster.getQuibitClusterSettings(container).transferRate;
	}

	public void setEnergyStored(ItemStack container, int energy) {
		NbtUtils.setInt(container, energy_tag, energy);
	}
}
