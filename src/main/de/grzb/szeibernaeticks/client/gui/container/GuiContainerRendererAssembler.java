package main.de.grzb.szeibernaeticks.client.gui.container;

import main.de.grzb.szeibernaeticks.container.GuiContainerBase;
import net.minecraft.client.gui.inventory.GuiInventory;

public class GuiContainerRendererAssembler extends GuiContainerRendererBase {

    public GuiContainerRendererAssembler(GuiContainerBase container) {
        super(container);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
        this.mc.getTextureManager().bindTexture(this.background);
        this.drawTexturedModalRect((this.width - this.xSize) / 2, (this.height - this.ySize) / 2, 0, 0, this.xSize,
                this.ySize);
        int offset_right = this.xSize - 35;
        int offset_top = 100;
        GuiInventory.drawEntityOnScreen(this.guiLeft + offset_right, this.guiTop + offset_top, 30,
                (float) (this.guiLeft + offset_right) - mouseX, (float) (this.guiTop + offset_top / 2) - mouseY,
                this.mc.player);
    }

}
