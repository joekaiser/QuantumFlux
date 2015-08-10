package jotato.quantumflux.machine.storehouse;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;

import jotato.quantumflux.inventory.SlotSearchable;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Slot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

public class GuiStorehouse extends GuiContainer {
	private static final ResourceLocation guiTexture = new ResourceLocation(
			"quantumflux:textures/gui/storehouse.png");
	private float currentScroll;
	private boolean isScrolling;
	private boolean wasClicking;
	private GuiTextField searchField;
	// private boolean unknownBool;
	public TileEntityStorehouse storehouse;
	private ContainerStorehouse container;
	private ItemSorter sorter = new ItemSorter();
	private List<SlotSearchable> items = new ArrayList();
	private boolean hasBeenDrawn = false;

	public GuiStorehouse(EntityPlayer player, TileEntityStorehouse storehouse) {
		super(new ContainerStorehouse(player, storehouse));

		this.ySize = 222;
		this.xSize = 195;
		this.storehouse = storehouse;
		this.allowUserInput = true;

	}

	@Override
	public void initGui() {
		super.initGui();
		this.container = ((ContainerStorehouse) this.inventorySlots);
		Keyboard.enableRepeatEvents(true);
		this.searchField = new GuiTextField(this.fontRendererObj, this.guiLeft + 81, this.guiTop + 7, 85,
				this.fontRendererObj.FONT_HEIGHT);
		this.searchField.setMaxStringLength(22);
		this.searchField.setEnableBackgroundDrawing(false);
		this.searchField.setVisible(true);
		this.searchField.setTextColor(16777215);
		this.searchField.setCanLoseFocus(false);
		this.searchField.setFocused(true);

	}

	public void onGuiClosed() {
		super.onGuiClosed();
		Keyboard.enableRepeatEvents(false);
	}

	public void drawScreen(int par1, int par2, float par3) {
		if (!hasBeenDrawn) {
			// this will just sort the inventory for the first time it is opened
			hasBeenDrawn = true;
			updateSearch(true);
		}
		boolean flag = Mouse.isButtonDown(0);
		int k = this.guiLeft;
		int l = this.guiTop;
		int i1 = k + 175;
		int j1 = l + 22;
		int k1 = i1 + 12;
		int l1 = j1 + 108;

		if (!this.wasClicking && flag && par1 >= i1 && par2 >= j1 && par1 < k1 && par2 < l1) {
			this.isScrolling = this.needsScrollBars();
		}

		if (!flag) {
			this.isScrolling = false;
		}

		this.wasClicking = flag;

		if (this.isScrolling) {
			this.currentScroll = ((float) (par2 - j1) - 7.5F) / ((float) (l1 - j1) - 15.0F);

			if (this.currentScroll < 0.0F) {
				this.currentScroll = 0.0F;
			}

			if (this.currentScroll > 1.0F) {
				this.currentScroll = 1.0F;
			}

			scrollTo(this.currentScroll);
		}
		this.updateScreen();

		super.drawScreen(par1, par2, par3);
	}

	private boolean needsScrollBars() {
		return storehouse.getSizeInventory() > 54;
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float f, int i, int j) {
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		this.mc.renderEngine.bindTexture(guiTexture);
		int var5 = (this.width - this.xSize) / 2;
		int var6 = (this.height - this.ySize) / 2;
		this.drawTexturedModalRect(var5, var6, 20, 0, this.xSize, this.ySize);

		int i1 = this.guiLeft + 175;
		int k = this.guiTop + 22;
		int l = k + 108;
		if (this.needsScrollBars()) {
			int x1 = i1;
			// int x2 = x1 + 12;
			int y1 = k + (int) ((float) (l - k - 17) * this.currentScroll);
			// int y2 = y1 + 15;

			this.drawTexturedModalRect(x1, y1, 0, 0, 12, 15);
		} else {
			this.drawTexturedModalRect(i1, k, 0, 16, 12, 15);
		}

		//String title = StatCollector.translateToFallback("gui.storehouse");
		//this.fontRendererObj.drawString(title, this.guiLeft + 6, this.guiTop + 9, 4210752);

		this.searchField.drawTextBox();

		if (storehouse.isDirty) {
			updateSearch(false);
			storehouse.isDirty = false;
		}

	}

	public boolean doesGuiPauseGame() {
		return false;
	}

	public void setInventorySlotContents(int i, ItemStack itemStack) {

		storehouse.setInventorySlotContents(i, itemStack);

		storehouse.markDirty();
	}

	@Override
	protected void keyTyped(char p_73869_1_, int p_73869_2_) {

		// if (this.unknownBool) {
		// this.unknownBool = false;
		// this.searchField.setText("");
		// }

		if (!this.checkHotbarKeys(p_73869_2_)) {
			if (this.searchField.textboxKeyTyped(p_73869_1_, p_73869_2_)) {
				this.updateSearch(true);
			} else {
				super.keyTyped(p_73869_1_, p_73869_2_);
			}
		}
	}

	public void handleMouseInput() {

		// removed because it was too laggy
		// if (Keyboard.isKeyDown(Keyboard.KEY_LSHIFT) ||
		// Keyboard.isKeyDown(Keyboard.KEY_RSHIFT)) {
		// super.handleMouseInput();
		// return;
		// }

		int i = Mouse.getEventDWheel();

		if (i != 0 && this.needsScrollBars()) {

			int j = storehouse.getSizeInventory() / 9 - 6;

			if (i > 0) {
				i = items.size() > 1000 ? 6 : 2;
			}
			if (i < 0) {
				i = items.size() > 1000 ? -6 : -2;
			}

			this.currentScroll = (float) ((double) this.currentScroll - (double) i / (double) j);
			if (this.currentScroll < 0.0F) {
				this.currentScroll = 0.0F;
			}
			if (this.currentScroll > 1.0F) {
				this.currentScroll = 1.0F;
			}
			this.scrollTo(this.currentScroll);
		} else {
			super.handleMouseInput();
		}
	}

	private void scrollTo(float position) {

		int numItems = items.size();

		for (int i = 0; i < storehouse.getSizeInventory(); i++) {
			SlotSearchable slot = (SlotSearchable) container.getSlot(i);
			slot.xDisplayPosition = -10000;
			slot.yDisplayPosition = -10000;
			slot.drawSlot = false;
		}

		// start is the row that should be drawn first
		int start = (int) Math.floor(position * Math.ceil((numItems / 9 - 6) / 1.0F));
		start = Math.max(start, 0);
		int startIndex = start * 9;
		int endIndex = startIndex + 53;
		for (int i = 0; i < numItems; i++) {
			SlotSearchable slot = items.get(i);
			if (i >= startIndex && i <= endIndex) {
				int x = (i - startIndex) % 9;
				int y = (i - startIndex - x) / 9;
				slot.xDisplayPosition = 8 + x * 18;
				slot.yDisplayPosition = 22 + y * 18;
				slot.drawSlot = true;
			}
		}
	}

	private void updateSearch(boolean resetScroll) {
		items.clear();
		for (int i = 0; i < storehouse.getSizeInventory(); i++) {
			SlotSearchable slot = (SlotSearchable) container.getSlot(i);
			slot.setMatchesSearch(searchField.getText());
			if (slot.getMatchesSearch()) {
				items.add(slot);
			}
		}
		Collections.sort(items, sorter);
		if (resetScroll)
			this.currentScroll = 0;
		scrollTo(this.currentScroll);
	}

	public static class ItemSorter implements Comparator<Slot> {
		public int compare(Slot slot1, Slot slot2) {
			if (!slot1.getHasStack()) {
				if (!slot2.getHasStack()) {
					return 0;
				}
				return 1;
			}
			if (!slot2.getHasStack()) {
				return -1;
			}
			// both have a stack if we reach this
			int id1 = Item.getIdFromItem(slot1.getStack().getItem());
			int id2 = Item.getIdFromItem(slot2.getStack().getItem());

			int t = intCompare(id1, id2);

			if (t == 0) {
				int m1 = slot1.getStack().getItemDamage();
				int m2 = slot2.getStack().getItemDamage();

				return intCompare(m1, m2);
			}

			return t;
		}

		public int intCompare(int a, int b) {
			if (a == b) {
				return 0;
			}
			if (a > b) {
				return -1;
			}
			return 1;
		}

	}

}