package jotato.quantumflux.nei;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

import codechicken.nei.PositionedStack;
import codechicken.nei.api.API;
import codechicken.nei.api.IConfigureNEI;
import cofh.lib.inventory.ComparableItemStack;
import jotato.quantumflux.Reference;
import jotato.quantumflux.machine.fabricator.ContainerItemFabricator;
import jotato.quantumflux.machine.fabricator.ItemFabricatorRecipeManager;
import jotato.quantumflux.machine.fabricator.ItemFabricatorRecipeManager.InfuserRecipe;
import jotato.quantumflux.util.SimplePosition;

public class NEIQuantumFluxConfig implements IConfigureNEI {
	private static HashMap<List<PositionedStack>, PositionedStack> itemFabricatorRecipes = new HashMap();

	@Override
	public String getName() {
		return Reference.MODNAME;
	}

	@Override
	public String getVersion() {
		return Reference.VERSION;
	}

	@Override
	public void loadConfig() {
		//todo: At some point after 1.3.0 hide the old quibit cluster's from NEI and the creative menu
		loadRecipes();
		API.registerRecipeHandler(ItemFabricatorNEIHandler.INSTANCE);
		API.registerUsageHandler(ItemFabricatorNEIHandler.INSTANCE);
	}

	private void loadRecipes() {
		loadItemFabricator();
	}

	private void loadItemFabricator(){
		
		SimplePosition slot1 = ContainerItemFabricator.slot1;
		SimplePosition slot2 = ContainerItemFabricator.slot2;
		SimplePosition slotOut = ContainerItemFabricator.slotOut;
		
		for(Entry<List<ComparableItemStack>, InfuserRecipe> recipe: ItemFabricatorRecipeManager.getRecipes().entrySet()){
			List<PositionedStack> key = new ArrayList();
			InfuserRecipe iRecipe = recipe.getValue();
			//todo: I don't know why the GUI draws a little off.
			// Figure it out and remove the offset calc
			key.add(new PositionedStack(iRecipe.getFirstInput(), slot1.X-5, slot1.Y-11));
			key.add(new PositionedStack(iRecipe.getSecondInput(), slot2.X-5, slot2.Y-11));
			
			itemFabricatorRecipes.put(key, new PositionedStack(iRecipe.getResult(), slotOut.X-5, slotOut.Y-11));
			
		}
	}
	
	public static Set<Entry<List<PositionedStack>, PositionedStack>> getIitemFabricatorRecipes(){
		return itemFabricatorRecipes.entrySet();
	}

}
