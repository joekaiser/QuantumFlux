package jotato.quantumflux.storehouse;

import jotato.quantumflux.inventory.ContainerBase;
import jotato.quantumflux.inventory.SlotSearchable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Slot;

public class ContainerStorehouse extends ContainerBase {

	private int numRows;
	private TileEntityStorehouse storehouse;

	public ContainerStorehouse(EntityPlayer player, TileEntityStorehouse storehouse) {
		super(storehouse);
		this.storehouse = storehouse;

		this.numRows = storehouse.getSizeInventory() / 9;

		for (int row = 0; row < numRows; ++row) {
			for (int slot = 0; slot < 9; ++slot) {
				this.addSlotToContainer(
						new SlotSearchable(this.storehouse, slot + row * 9, 8 + slot * 18, 22 + row * 18));
			}
		}

		addPlayerInventorySlots(player.inventory);
	}

	@Override
	protected void addPlayerInventorySlots(InventoryPlayer inventory) {
		// player inventory
		for (int row = 0; row < 3; ++row) {
			for (int slot = 0; slot < 9; ++slot) {
				this.addSlotToContainer(new Slot(inventory, slot + row * 9 + 9, 8 + slot * 18, 139 + row * 18));
			}
		}

		// player hotbar
		for (int row = 0; row < 9; ++row) {
			this.addSlotToContainer(new Slot(inventory, row, 8 + row * 18, 196 + 1));

		}
	}
}