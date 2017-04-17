package main.de.grzb.szeibernaeticks.client.gui.container;

import main.de.grzb.szeibernaeticks.container.GuiContainerBase;
import main.de.grzb.szeibernaeticks.container.layout.GuiLayoutDefinition;
import net.minecraft.client.gui.inventory.GuiInventory;

public class GuiContainerRendererAssembler extends GuiContainerRendererBase {

    public GuiContainerRendererAssembler(GuiContainerBase container) {
        super(container);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
        this.mc.getTextureManager().bindTexture(this.background);
        this.drawTexturedModalRect((this.width - this.xSize) / 2, (this.height - this.ySize) / 2, 0, 0, this.xSize, this.ySize);

        int offsetRight = this.xSize / 2;
        int offsetTop = this.ySize / 2 - GuiLayoutDefinition.BORDER_SIZE;
        int posX = this.guiLeft + offsetRight;
        int posY = this.guiTop + offsetTop;
        mouseX = posX - mouseX;
        mouseY = (this.guiTop + offsetTop / 2) - mouseY;
        GuiInventory.drawEntityOnScreen(posX, posY, 30, mouseX, mouseY, this.mc.player);
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
        this.fontRendererObj.drawString("Assembler", GuiLayoutDefinition.BORDER_SIZE, GuiLayoutDefinition.BORDER_SIZE, 4210752);
    }

}
