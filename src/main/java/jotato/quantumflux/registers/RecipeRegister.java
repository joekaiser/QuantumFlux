package jotato.quantumflux.registers;

import jotato.quantumflux.blocks.darkstone.EnumDarkstone;
import net.minecraft.block.Block;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.chunk.Chunk.EnumCreateEntityType;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.ShapedOreRecipe;

public class RecipeRegister {
	// vanilla resources
		String ingotIron = "ingotIron";
		String ingotGold = "ingotGold";
		ItemStack redstone = new ItemStack(Items.redstone);
		ItemStack quartz = new ItemStack(Items.quartz);
		ItemStack diamond = new ItemStack(Items.diamond);
		ItemStack enderPearl = new ItemStack(Items.ender_pearl);

		//modItems
		ItemStack goldCasing = ItemRegister.craftingPieces.getSubItem("goldCasing");
		ItemStack amplificationCrystal = ItemRegister.craftingPieces.getSubItem("amplificationCrystal");
		ItemStack quibitCrystal = ItemRegister.craftingPieces.getSubItem("quibitCrystal");
		ItemStack enderCrystal = ItemRegister.craftingPieces.getSubItem("enderCrystal");
		ItemStack darkstone = EnumDarkstone.getDarkstoneType(EnumDarkstone.plain);
		

		public void init() {
			shapedRecipes();
			shapelessRecipes();
		}

		private void shapedRecipes() {
			addShapedOreRecipe(true, goldCasing, 
					"r r",
					"ggg",
					"r r", 
					'r', redstone,'g',ingotGold);
			
			addShapedOreRecipe(true, amplificationCrystal, 
					"q q",
					" d ",
					"sss", 
					'q', quartz,'d',diamond, 's',darkstone);
			
			addShapedOreRecipe(true, quibitCrystal, 
					" r ",
					"qdq",
					" r ", 
					'r', redstone, 'q', quartz,'d',diamond);
			
			addShapedOreRecipe(true, enderPearl,
					"ee",
					"ee" ,'e',enderCrystal);

		
			
		}

		private void shapelessRecipes() {
			

		}

		// below methods snagged from CoFHLib because it isn't compiled for 1.8.8

		public static void addShapelessRecipe(boolean condition, ItemStack out, Object... recipe) {
			if (condition)
				GameRegistry.addShapelessRecipe(out, recipe);
		}

		public static void addShapelessRecipe(boolean condition, Item out, Object... recipe) {

			addShapelessRecipe(condition, new ItemStack(out), recipe);
		}

		public static void addShapelessRecipe(boolean condition, Block out, Object... recipe) {

			addShapelessRecipe(condition, new ItemStack(out), recipe);
		}

		public static void addShapedOreRecipe(boolean condition, ItemStack out, Object... recipe) {
			if (condition)
				GameRegistry.addRecipe(new ShapedOreRecipe(out, recipe));
		}

		public static void addShapedOreRecipe(boolean condition, Item out, Object... recipe) {
			if (condition)
				GameRegistry.addRecipe(new ShapedOreRecipe(out, recipe));
		}

		public static void addShapedOreRecipe(boolean condition, Block out, Object... recipe) {
			if (condition)
				GameRegistry.addRecipe(new ShapedOreRecipe(out, recipe));
		}

}
