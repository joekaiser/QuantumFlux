package jotato.quantumflux.items;

import java.util.List;

import org.lwjgl.input.Keyboard;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.StatCollector;

@SuppressWarnings({ "unchecked", "rawtypes" })
public class ItemBlockBase extends ItemBlock {

	public ItemBlockBase(Block block) {
		super(block);
	}

	@Override
	public void addInformation(ItemStack itemstack, EntityPlayer player,
			List list, boolean bool) {
		addSimpleTooltipInformation(itemstack, player, list);

		if (hasAdvancedTooltip()) {
			if (showAdvancedTooltip()) {
				addAdvancedTooltipInformation(itemstack, player, list);
			} else {
				addShowDetailsTooltip(list);
			}
		}
	}

	public void addSimpleTooltipInformation(ItemStack itemstack,
			EntityPlayer player, List list) {
	}

	public void addAdvancedTooltipInformation(ItemStack itemstack,
			EntityPlayer player, List list) {
	}

	public boolean hasAdvancedTooltip() {
		return false;
	}

	public boolean showAdvancedTooltip() {
		return Keyboard.isKeyDown(Keyboard.KEY_LSHIFT)
				|| Keyboard.isKeyDown(Keyboard.KEY_RSHIFT);
	}

	public void addShowDetailsTooltip(List list) {
		list.add(EnumChatFormatting.WHITE + "" + EnumChatFormatting.ITALIC
				+ StatCollector.translateToLocal("tooltip.showDetails"));
	}
	
	@Override
	public String getUnlocalizedName(ItemStack itemStack) {
		if (this.hasSubtypes) {
			int metadata = itemStack.getItemDamage();
			return super.getUnlocalizedName(itemStack) + "."
					+ Integer.toString(metadata);
		} else {
			return super.getUnlocalizedName(itemStack);
		}
	}

	@Override
	public String getUnlocalizedName() {
		if (this.hasSubtypes) {
			return super.getUnlocalizedName() + ".0";
		} else {
			return super.getUnlocalizedName();
		}
	}
}
