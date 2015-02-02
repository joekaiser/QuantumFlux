package jotato.quantumflux.inventory;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import jotato.quantumflux.tileentity.TileEntityQuibitCluster;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.Slot;

public class ContainerQuibitCluster extends ContainerBase
{
    int lastInternalStorage;

    private TileEntityQuibitCluster cluster;

    public ContainerQuibitCluster(InventoryPlayer playerInventory, TileEntityQuibitCluster cluster)
    {
        super(cluster);
        this.cluster = cluster;
        addSlotToContainer(new Slot(cluster, 0, 80, 52));
        addPlayerInventorySlots(playerInventory);
    }

    @Override
    public void addCraftingToCrafters(ICrafting craft)
    {
        super.addCraftingToCrafters(craft);
        craft.sendProgressBarUpdate(this, 0, this.cluster.getEnergyStored(null));
    }

    @Override
    public void detectAndSendChanges()
    {
        super.detectAndSendChanges();

        for (int i = 0; i < this.crafters.size(); ++i)
        {
            ICrafting craft = (ICrafting) this.crafters.get(i);
            if (this.lastInternalStorage != this.cluster.getEnergyStored(null))
            {
                craft.sendProgressBarUpdate(this, 0, this.cluster.getEnergyStored(null));
            }
        }

        this.lastInternalStorage = this.cluster.getEnergyStored(null);
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void updateProgressBar(int id, int value)
    {
        if (id == 0)
        {
            this.cluster.setEnergyStored(value);
        }
    }
}
