package jotato.quantumflux.registers;

import jotato.quantumflux.Logger;
import jotato.quantumflux.QuantumFluxMod;
import jotato.quantumflux.items.ItemBase;
import jotato.quantumflux.items.ItemCraftingPiece;
import jotato.quantumflux.items.ItemExciterUpgrade;
import jotato.quantumflux.items.ItemLinkingCard;
import jotato.quantumflux.items.ItemMagnet;
import jotato.quantumflux.items.ItemMatterTransporter;
import jotato.quantumflux.items.ItemQuibitCell;
import jotato.quantumflux.items.ItemVoidBucket;
import jotato.quantumflux.items.netherbane.ItemNetherbane;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemFood;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public final class ItemRegister {

	public static final ToolMaterial netherBaneMaterial = EnumHelper.addToolMaterial("netherbane", 4, 5000, 12.0F, 6F,
			24);

	public static ItemCraftingPiece craftingPieces;
	public static ItemBase magnet;
	public static ItemBase exciterUpgrade;
	public static ItemBase quibitCell;
	public static ItemNetherbane netherbane;
	public static ItemBase voidBucket;
	public static ItemBase matterTransporter;
	public static Item hamCheese;
	public static ItemBase graphiteDust;
	public static ItemBase linkingCard;

	public static void init() {

		craftingPieces = new ItemCraftingPiece();
		craftingPieces.addItem("quibitCrystal",0);
		craftingPieces.addItem("goldCasing",1);
		craftingPieces.addItem("amplificationCrystal",2);
		craftingPieces.addItem("enderCrystal",3);
		craftingPieces.addItem("quantumDisk",4);
		craftingPieces.addItem("advancedCircuit",5);
		craftingPieces.addItem("industrialDiamond",6);

		magnet = new ItemMagnet();
		exciterUpgrade = new ItemExciterUpgrade();
		quibitCell = new ItemQuibitCell();
		netherbane = new ItemNetherbane();
		voidBucket = new ItemVoidBucket();
		matterTransporter = new ItemMatterTransporter();
		hamCheese = new ItemFood(10, 1f, true).setUnlocalizedName("hamCheese").setCreativeTab(QuantumFluxMod.tab);
		hamCheese.setRegistryName("hamCheese");
		ForgeRegistries.ITEMS.register(hamCheese);
		graphiteDust = new ItemBase("graphiteDust");
		linkingCard = new ItemLinkingCard();

	}

	public static void registerRenders() {
		Logger.devLog("Registering items");

		craftingPieces.initModel();
		magnet.initModel();
		exciterUpgrade.initModel();
		quibitCell.initModel();
		netherbane.initModel();
		voidBucket.initModel();
		matterTransporter.initModel();
		registerGenericItem(hamCheese, 0);
		graphiteDust.initModel();
		linkingCard.initModel();

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
