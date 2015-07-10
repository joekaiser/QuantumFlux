package jotato.quantumflux.machine.fabricator;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cofh.lib.inventory.ComparableItemStack;
import jotato.quantumflux.items.ModItems;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

public class ItemFabricatorRecipeManager {

	public static Map<List<ComparableItemStack>, InfuserRecipe> recipeMap = new HashMap<List<ComparableItemStack>, ItemFabricatorRecipeManager.InfuserRecipe>();

	public static void addDefaultRecipes() {
		addRecipe(new ItemStack(Blocks.sand), new ItemStack(Blocks.quartz_block), new ItemStack(ModItems.silica));
		addRecipe(new ItemStack(ModItems.silica), new ItemStack(Items.paper), new ItemStack(ModItems.blankCircuit));
	}

	public static void refreshRecipes(){
		//warning, this will overwrite all recipes with the default
		//if there are any recipes added outside of addDefaultRecipes
		//they will be lost.
		//todo: iterate over the hashmap and create a new one based off of it
		
		recipeMap.clear();
		addDefaultRecipes();
	}
	
	public static InfuserRecipe addRecipe(ItemStack first, ItemStack second, ItemStack result) {

		InfuserRecipe recipe = null;

		if (first == null || second == null || result == null) {
			return recipe;
		}

		recipe = getRecipe(first, second);

		if (recipe == null) {
			recipe = new InfuserRecipe(first, second, result);
			recipeMap.put(Arrays.asList(new ComparableItemStack(first), new ComparableItemStack(second)), recipe);
		}

		return recipe;

	}

	public static InfuserRecipe addRecipe(String first, String second, ItemStack result) {
		List<ItemStack> firstOreList = OreDictionary.getOres(first);
		List<ItemStack> secondOreList = OreDictionary.getOres(second);

		if (firstOreList.size() > 0 && secondOreList.size() > 0) {
			return addRecipe(firstOreList.get(0), secondOreList.get(0), result);
		}

		return null;
	}

	public static InfuserRecipe addRecipe(String first, ItemStack second, ItemStack result) {
		List<ItemStack> firstOreList = OreDictionary.getOres(first);

		if (firstOreList.size() > 0) {
			return addRecipe(firstOreList.get(0).copy(), second, result);
		}

		return null;
	}

	public static InfuserRecipe getRecipe(ItemStack first, ItemStack second) {
		if (first == null || second == null)
			return null;

		ComparableItemStack q1 = new ComparableItemStack(first);
		ComparableItemStack q2 = new ComparableItemStack(second);
		
		InfuserRecipe recipe = recipeMap.get(Arrays.asList(q1, q2));

		if (recipe == null) {
			recipe = recipeMap.get(Arrays.asList(q2, q1));
		}
		
		return recipe;
	}

	public static class InfuserRecipe {

		private final ItemStack first;
		private final ItemStack second;
		private final ItemStack result;

		public InfuserRecipe(ItemStack first, ItemStack second, ItemStack result) {

			first.stackSize = Math.max(first.stackSize, 1);
			second.stackSize = Math.max(second.stackSize, 1);
			result.stackSize = Math.max(result.stackSize, 1);

			this.first = first;
			this.second = second;
			this.result = result;
		}

		public ItemStack getFirstInput() {
			return first.copy();
		}

		public ItemStack getSecondInput() {
			return second.copy();
		}

		public ItemStack getResult() {
			return result.copy();
		}

		public boolean matches(ItemStack first, ItemStack second) {
			ComparableItemStack orig_first = new ComparableItemStack(getFirstInput());
			ComparableItemStack orig_second = new ComparableItemStack(getSecondInput());

			ComparableItemStack new_first = new ComparableItemStack(first);
			ComparableItemStack new_second = new ComparableItemStack(second);

			if (orig_first.isEqual(new_first) && orig_second.isEqual(new_second)) {
				return true;
			}

			if (orig_first.isEqual(new_second) && orig_second.isEqual(new_first)) {
				return true;
			}
			return false;
		}
	}
}
