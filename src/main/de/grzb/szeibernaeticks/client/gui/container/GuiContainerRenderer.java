package main.de.grzb.szeibernaeticks.client.gui.container;

import main.de.grzb.szeibernaeticks.container.GuiContainerBase;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.util.ResourceLocation;

/**
 * Base class to create a GUI for a container. Not relevant for the server.
 * 
 * @author yuri
 *
 */
public class GuiContainerRenderer extends GuiContainer {
  private final ResourceLocation background;

  public GuiContainerRenderer(GuiContainerBase container) {
    super(container);
    this.xSize = container.getGuiWidth();
    this.ySize = container.getGuiHeight();
    this.background = container.getGuiBackground();
  }

  @Override
  protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
    this.mc.getTextureManager().bindTexture(this.background);
    this.drawTexturedModalRect((this.width - this.xSize) / 2, (this.height - this.ySize) / 2, 0, 0, this.xSize, this.ySize);
  }

}
