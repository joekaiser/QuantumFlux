package jotato.quantumflux.registers;

import jotato.quantumflux.Logger;
import jotato.quantumflux.QuantumFluxMod;
import jotato.quantumflux.items.ItemBase;
import jotato.quantumflux.items.ItemCraftingPiece;
import jotato.quantumflux.items.ItemExciterUpgrade;
import jotato.quantumflux.items.ItemMagnet;
import jotato.quantumflux.items.ItemQuibitCell;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public final class ItemRegister {

	public static ItemCraftingPiece craftingPieces;
	public static ItemBase magnet;
	public static ItemBase exciterUpgrade;
	public static ItemBase quibitCell;
	
	public static void init() {
		craftingPieces = new ItemCraftingPiece();
		craftingPieces.addItem("quibitCrystal");
		craftingPieces.addItem("goldCasing");
		craftingPieces.addItem("amplificationCrystal");
		craftingPieces.addItem("enderCrystal");
		craftingPieces.addItem("quantumDisk");
		craftingPieces.addItem("advancedCircuit");
		
		magnet = new ItemMagnet();
		exciterUpgrade = new ItemExciterUpgrade();
		quibitCell = new ItemQuibitCell();
	}

	public static void registerRenders() {
		Logger.info("Registering items");

		craftingPieces.initModel();
		magnet.initModel();
		exciterUpgrade.initModel();
		quibitCell.initModel();

	}

	/***
	 * Used for items that extend Minecraft.Item instead of ItemBase
	 * 
	 * @param item
	 * @param meta
	 */
	@SideOnly(Side.CLIENT)
	private static void registerGenericItem(Item item, int meta) {
		String name = item.getUnlocalizedName().substring(5);
		Logger.info("    Registering model for %s", name);
		ModelLoader.setCustomModelResourceLocation(item, meta,
				new ModelResourceLocation(QuantumFluxMod.TEXTURE_BASE + name, "inventory"));

	}

}
