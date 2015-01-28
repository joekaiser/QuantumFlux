package jotato.quantumflux.inventory;

import jotato.quantumflux.tileentity.TileEntityIncinerator;
import net.minecraft.entity.player.InventoryPlayer;

public class ContainerIncinerator extends ContainerBase
{
    
    private TileEntityIncinerator incinerator;
    public  ContainerIncinerator(InventoryPlayer playerInventory, TileEntityIncinerator incinerator)
    {
        this.incinerator = incinerator;
        
        addPlayerInventorySlots(playerInventory);
    }
    
    


}
