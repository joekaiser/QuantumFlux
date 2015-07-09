package jotato.quantumflux.machine.infuser;


import org.lwjgl.opengl.GL11;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;

public class GuiInfuser extends GuiContainer{
	  private static final ResourceLocation guiScreen = new ResourceLocation("quantumflux:textures/gui/molecularInfuser.png");
	    private TileEntityMolecularInfuser infuser;
	    private String displayName;


	    public GuiInfuser(EntityPlayer player, TileEntityMolecularInfuser infuser)
	    {
	        super(new ContainerInfuser(player, infuser));
	        this.infuser = infuser;
	        setupDisplay();
	    }

	    private void setupDisplay()
	    {
	        this.displayName = StatCollector.translateToLocal("gui.molecularinfuser");
	    }

	    @Override
	    protected void drawGuiContainerForegroundLayer(int p1, int p2)
	    {
	        this.fontRendererObj.drawString(displayName, 6, 5, 4210752);
	    }

	    @Override
	    protected void drawGuiContainerBackgroundLayer(float p1, int p2, int p3)
	    {
	        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
	        this.mc.getTextureManager().bindTexture(guiScreen);
	        int k = (this.width - this.xSize) / 2;
	        int l = (this.height - this.ySize) / 2;
	        this.drawTexturedModalRect(k, l, 0, 0, this.xSize, this.ySize);

	        int bufferScale = this.infuser.getBufferScaled(76);
	        this.drawTexturedModalRect(k + 154, l + 80 - bufferScale + 1, 0, 241 - bufferScale, 12, bufferScale);

	    }
}
