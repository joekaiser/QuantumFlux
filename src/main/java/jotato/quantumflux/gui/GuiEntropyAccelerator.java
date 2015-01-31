package jotato.quantumflux.gui;

import java.text.NumberFormat;

import org.lwjgl.opengl.GL11;

import jotato.quantumflux.inventory.ContainerEntropyAccelerator;
import jotato.quantumflux.tileentity.TileEntityEntropyAccelerator;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;

@SideOnly(Side.CLIENT)
public class GuiEntropyAccelerator extends GuiContainer
{
    private static final ResourceLocation guiScreen = new ResourceLocation("quantumflux:textures/gui/incinerator.png");
    private TileEntityEntropyAccelerator incinerator;

    public GuiEntropyAccelerator(InventoryPlayer playerInventory, TileEntityEntropyAccelerator incinerator)
    {
        super(new ContainerEntropyAccelerator(playerInventory, incinerator));
        this.incinerator = incinerator;
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int p1, int p2)
    {
        String name = "Quantum Entropy Accelerator";
        this.fontRendererObj.drawString(name, 6, 5, 4210752);
        String energy = NumberFormat.getIntegerInstance().format(incinerator.getEnergyStored(null));
        this.fontRendererObj.drawString(energy, 6, 15, 4210752);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float p1, int p2, int p3)
    {
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.mc.getTextureManager().bindTexture(guiScreen);
        int k = (this.width - this.xSize) / 2;
        int l = (this.height - this.ySize) / 2;
        this.drawTexturedModalRect(k, l, 0, 0, this.xSize, this.ySize);

        if (this.incinerator.isActive())
        {
            this.drawTexturedModalRect(k + 56, l + 11, 0, 166, 64, 64);
        }

        int bufferScale = this.incinerator.getBufferScaled(76);
        this.drawTexturedModalRect(k + 154, l + 80 - bufferScale + 1, 64, 241 - bufferScale, 12, bufferScale);

    }
}
