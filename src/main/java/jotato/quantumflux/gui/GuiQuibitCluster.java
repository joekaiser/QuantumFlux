package jotato.quantumflux.gui;

import java.text.NumberFormat;

import org.lwjgl.opengl.GL11;

import jotato.quantumflux.inventory.ContainerQuibitCluster;
import jotato.quantumflux.tileentity.TileEntityQuibitCluster;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;

@SideOnly(Side.CLIENT)
public class GuiQuibitCluster extends GuiContainer
{
    private static final ResourceLocation guiScreen = new ResourceLocation("quantumflux:textures/gui/quibitCluster.png");
    private TileEntityQuibitCluster cluster;
    private String displayName;
    private String maxStorage;
    private String maxTransfer;

    public GuiQuibitCluster(EntityPlayer player, TileEntityQuibitCluster cluster)
    {
        super(new ContainerQuibitCluster(player, cluster));
        this.cluster = cluster;
        setupDisplay();
    }

    private void setupDisplay()
    {
        this.displayName = String.format(StatCollector.translateToLocal("gui.quibitCluster"), cluster.level);
        int transferRate=cluster.getEnergyTransferRate();
        if(transferRate == Integer.MAX_VALUE)
            this.maxTransfer = StatCollector.translateToLocal("gui.quibitClusterTransferRateInfinite");
        else
            this.maxTransfer = String.format(StatCollector.translateToLocal("gui.quibitClusterTransferRate"), cluster.getEnergyTransferRate());
        this.maxStorage = String.format(StatCollector.translateToLocal("gui.quibitClusterMaxStorage"), cluster.getMaxEnergyStored(null));
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int p1, int p2)
    {

        this.fontRendererObj.drawString(displayName, 6, 5, 4210752);
        String energy = NumberFormat.getIntegerInstance().format(cluster.getEnergyStored(null));
        this.fontRendererObj.drawString(energy, 6, 18, 4210752);
        this.fontRendererObj.drawString(maxStorage, 6, 31, 4210752);
        this.fontRendererObj.drawString(maxTransfer, 6, 44, 4210752);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float p1, int p2, int p3)
    {
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.mc.getTextureManager().bindTexture(guiScreen);
        int k = (this.width - this.xSize) / 2;
        int l = (this.height - this.ySize) / 2;
        this.drawTexturedModalRect(k, l, 0, 0, this.xSize, this.ySize);

        int bufferScale = this.cluster.getBufferScaled(76);
        this.drawTexturedModalRect(k + 154, l + 80 - bufferScale + 1, 0, 241 - bufferScale, 12, bufferScale);

    }
}
