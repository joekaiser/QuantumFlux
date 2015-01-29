package jotato.quantumflux.inventory;

import jotato.quantumflux.tileentity.TileEntityIncinerator;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Slot;

public class ContainerIncinerator extends ContainerBase
{
    
    private TileEntityIncinerator incinerator;
    public  ContainerIncinerator(InventoryPlayer playerInventory, TileEntityIncinerator incinerator)
    {
        super(incinerator);
        this.incinerator = incinerator;
        addSlotToContainer(new Slot(incinerator, 0, 80, 52));
        addPlayerInventorySlots(playerInventory);
    }
    
    


}
