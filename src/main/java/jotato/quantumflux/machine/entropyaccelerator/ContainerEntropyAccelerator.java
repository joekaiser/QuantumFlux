package jotato.quantumflux.machine.entropyaccelerator;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import jotato.quantumflux.inventory.ContainerBase;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.Slot;

public class ContainerEntropyAccelerator extends ContainerBase
{
    int lastInternalStorage;

    private TileEntityEntropyAccelerator incinerator;

    public ContainerEntropyAccelerator(InventoryPlayer playerInventory, TileEntityEntropyAccelerator incinerator)
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
        craft.sendProgressBarUpdate(this, 0, this.incinerator.getEnergyStored(null));
    }

    @Override
    public void detectAndSendChanges()
    {
        super.detectAndSendChanges();

        for (int i = 0; i < this.crafters.size(); ++i)
        {
            ICrafting craft = (ICrafting) this.crafters.get(i);
            if (this.lastInternalStorage != this.incinerator.getEnergyStored(null))
            {
                craft.sendProgressBarUpdate(this, 0, this.incinerator.getEnergyStored(null));
            }
        }

        this.lastInternalStorage = this.incinerator.getEnergyStored(null);
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void updateProgressBar(int id, int value)
    {
        if (id == 0)
        {
            this.incinerator.setEnergyStored(value);
        }
    }
}
