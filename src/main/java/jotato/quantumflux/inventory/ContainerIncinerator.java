package jotato.quantumflux.inventory;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import jotato.quantumflux.tileentity.TileEntityIncinerator;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.Slot;

public class ContainerIncinerator extends ContainerBase
{
    int lastInternalStorage;

    private TileEntityIncinerator incinerator;

    public ContainerIncinerator(InventoryPlayer playerInventory, TileEntityIncinerator incinerator)
    {
        super(incinerator);
        this.incinerator = incinerator;
        addSlotToContainer(new Slot(incinerator, 0, 80, 52));
        addPlayerInventorySlots(playerInventory);
    }

    @Override
    public void addCraftingToCrafters(ICrafting craft)
    {
        super.addCraftingToCrafters(craft);
        craft.sendProgressBarUpdate(this, 0, this.incinerator.getCurrentStorage());
    }

    @Override
    public void detectAndSendChanges()
    {
        super.detectAndSendChanges();

        for (int i = 0; i < this.crafters.size(); ++i)
        {
            ICrafting craft = (ICrafting) this.crafters.get(i);
            if (this.lastInternalStorage != this.incinerator.getCurrentStorage())
            {
                craft.sendProgressBarUpdate(this, 0, this.incinerator.getCurrentStorage());
            }
        }

        this.lastInternalStorage = this.incinerator.getCurrentStorage();
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void updateProgressBar(int id, int value)
    {
        if (id == 0)
        {
            this.incinerator.setCurrentStorage(value);
        }
    }
}
