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
import jotato.quantumflux.Logger;
import jotato.quantumflux.Reference;
import jotato.quantumflux.machine.fabricator.ContainerItemFabricator;
import jotato.quantumflux.machine.fabricator.GUIItemFabricator;
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
		loadRecipes();
		API.registerRecipeHandler(ItemFabricatorNEIHandler.INSTANCE);
		API.registerUsageHandler(ItemFabricatorNEIHandler.INSTANCE);
		API.setGuiOffset(GUIItemFabricator.class, -125, -125);
	}

	private void loadRecipes() {
		loadItemFabricator();
	}

	private void loadItemFabricator(){
		
		Logger.info("___Loading ItemFab recipes");
		SimplePosition slot1 = ContainerItemFabricator.slot1;
		SimplePosition slot2 = ContainerItemFabricator.slot2;
		SimplePosition slotOut = ContainerItemFabricator.slotOut;
		
		for(Entry<List<ComparableItemStack>, InfuserRecipe> recipe: ItemFabricatorRecipeManager.getRecipes().entrySet()){
			List<PositionedStack> key = new ArrayList();
			InfuserRecipe iRecipe = recipe.getValue();
			key.add(new PositionedStack(iRecipe.getFirstInput(), slot1.X, slot1.Y));
			key.add(new PositionedStack(iRecipe.getSecondInput(), slot2.X, slot2.Y));
			
			itemFabricatorRecipes.put(key, new PositionedStack(iRecipe.getResult(), slotOut.X, slotOut.Y));
			
		}
	}
	
	public static Set<Entry<List<PositionedStack>, PositionedStack>> getIitemFabricatorRecipes(){
		return itemFabricatorRecipes.entrySet();
	}

}
