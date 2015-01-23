package jotato.quantumflux.gui;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import cpw.mods.fml.common.network.IGuiHandler;

public class QFGuiHandler implements IGuiHandler
{

    public enum GUI
    {
        MOLECULAR_INFUSER(0);

        public int ordinal;

        private GUI(int id)
        {
            this.ordinal = id;
        }

        private static GUI[] allValues = values();

        public static GUI fromOrdinal(int n)
        {
            return allValues[n];
        }
    }

    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
    {
//        GUI gui = GUI.fromOrdinal(ID);
//
//        if (gui == GUI.MOLECULAR_INFUSER)
//        {
//            TileEntityMolecularInfuser tileEntity = (TileEntityMolecularInfuser) world.getTileEntity(x, y, z);
//            //return new ContainerMolecularInfuser(player.inventory, tileEntityFurnace);
//            return new ContainerMolecularInfuser();
//        }

        return null;

    }

    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
    {
//        GUI gui = GUI.fromOrdinal(ID);
//
//        if (gui == GUI.MOLECULAR_INFUSER)
//        {
//            TileEntityMolecularInfuser tileEntity = (TileEntityMolecularInfuser) world.getTileEntity(x, y, z);
//            return new GuiMolecularInfuser(null);
//        }

        return null;
    }

}
