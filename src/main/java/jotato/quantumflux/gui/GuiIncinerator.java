package jotato.quantumflux.gui;

import org.lwjgl.opengl.GL11;

import jotato.quantumflux.inventory.ContainerIncinerator;
import jotato.quantumflux.tileentity.TileEntityIncinerator;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;

@SideOnly(Side.CLIENT)
public class GuiIncinerator extends GuiContainer
{
    private static final ResourceLocation guiScreen = new ResourceLocation("quantumflux:textures/gui/incinerator.png");
    private TileEntityIncinerator incinerator;
    
    public GuiIncinerator(InventoryPlayer playerInventory, TileEntityIncinerator incinerator)
    {
        super(new ContainerIncinerator(playerInventory,incinerator));
        this.incinerator = incinerator;
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int p1, int p2)
    {
        String name = "Incinerator";
        this.fontRendererObj.drawString(name, 6, 5, 4210752);
    }
    
    
    @Override
    protected void drawGuiContainerBackgroundLayer(float p1, int p2, int p3) {
         GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
            this.mc.getTextureManager().bindTexture(guiScreen);
            int k = (this.width - this.xSize) / 2;
            int l = (this.height - this.ySize) / 2;
            this.drawTexturedModalRect(k, l, 0, 0, this.xSize, this.ySize);

    }
}
