package main.de.grzb.szeibernaeticks.client.gui.container;

import main.de.grzb.szeibernaeticks.container.GuiContainerBase;
import main.de.grzb.szeibernaeticks.container.layout.GuiLayoutDefinition;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.util.ResourceLocation;

/**
 * Base class to create a GUI for a container. Not relevant for the server.
 *
 * @author yuri
 */
public abstract class GuiContainerRendererBase extends GuiContainer {
    protected final ResourceLocation background;

    public GuiContainerRendererBase(GuiContainerBase container) {
        super(container);
        this.xSize = container.getLayoutDefinition().getWidth();
        this.ySize = container.getLayoutDefinition().getHeight();
        this.background = container.getGuiBackground();
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
        this.mc.getTextureManager().bindTexture(this.background);
        this.drawTexturedModalRect((this.width - this.xSize) / 2, (this.height - this.ySize) / 2, 0, 0, this.xSize, this.ySize);
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
        this.fontRendererObj.drawString("", GuiLayoutDefinition.BORDER_SIZE, GuiLayoutDefinition.BORDER_SIZE, 4210752);
    }

}
