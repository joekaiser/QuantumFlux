package jotato.quantumflux.items;

import java.util.ArrayList;
import java.util.List;

import gnu.trove.map.TMap;
import gnu.trove.map.hash.THashMap;
import jotato.quantumflux.Logger;
import jotato.quantumflux.QuantumFluxMod;
import jotato.quantumflux.helpers.ItemHelpers;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.oredict.OreDictionary;

import javax.annotation.Nullable;

public class ItemCraftingPiece extends ItemBase {
	public TMap<String, SubItem> subItemMap = new THashMap<String, SubItem>();
	public ArrayList<SubItem> subItemList = new ArrayList<SubItem>();

	public class SubItem {

		public String name;
		public int meta;

		public SubItem(String name, int meta) {

			this.name = name;
			this.meta = meta;
		}
	}

	public ItemCraftingPiece() {
		super("craftingPiece");
		this.setHasSubtypes(true);
	}

	@Override
	public void initModel() {

		for (SubItem item : subItemList) {
			Logger.devLog("    Registering model for %s", item.name);
			ModelLoader.setCustomModelResourceLocation(this, item.meta,
					new ModelResourceLocation(QuantumFluxMod.TEXTURE_BASE + item.name, "inventory"));
		}
	}
	
	public SubItem getUnderlyingItem(ItemStack stack){
		return subItemList.get(stack.getItemDamage());
	}

	public ItemStack addItem(String name, String oreDictName, int number) {


		if (subItemMap.containsKey(String.valueOf(name))) {
			return null;
		}

		SubItem entry = new SubItem(name, number);
		

		subItemMap.put(String.valueOf(name), entry);
		subItemList.add(entry);

		ItemStack item = new ItemStack(this, 1, number);

		if (oreDictName != null && oreDictName.trim().length() > 0) {

			OreDictionary.registerOre(oreDictName.trim(), item);
		}
		
		subItemList.trimToSize();

		return item;
	}

	public ItemStack addItem(String name, int number) {

		return addItem(name, null,number);
	}


	public ItemStack getSubItem(String name) {
		return getSubItem(name, 1);
	}

	public ItemStack getSubItem(String name, int amount) {
		if (subItemMap.containsKey(name)) {
			int meta = subItemMap.get(name).meta;
			return new ItemStack(this, amount, meta);
		}
		return new ItemStack(Items.APPLE, amount);
	}

	@Override
	public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> items) {

		for (int i = 0; i < subItemList.size(); i++) {
			items.add(new ItemStack(this, 1, i));
		}
	}

	@Override
	public String getUnlocalizedName(ItemStack stack) {

		int meta = getDamage(stack);

		if (subItemList.size() <= meta) {
			return "item.invalid";
		}
		SubItem item = subItemList.get(meta);

		return "item." + item.name;
	}
	
	@Override
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
		ItemHelpers.addFlairToList(tooltip, getUnlocalizedName(stack));
	}

}
