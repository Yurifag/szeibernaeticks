package main.de.grzb.szeibernaeticks.client.gui.container;

import main.de.grzb.szeibernaeticks.container.GuiContainerBase;
import main.de.grzb.szeibernaeticks.container.layout.GuiLayoutDefinition;
import main.de.grzb.szeibernaeticks.control.Log;
import main.de.grzb.szeibernaeticks.control.LogType;
import main.de.grzb.szeibernaeticks.networking.GuiMessage;
import main.de.grzb.szeibernaeticks.networking.NetworkWrapper;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.inventory.GuiInventory;

import java.io.IOException;

public class GuiContainerRendererAssembler extends GuiContainerRendererBase {

    public GuiContainerRendererAssembler(GuiContainerBase container) {
        super(container);
    }

    @Override
    public void initGui() {
        super.initGui();
        int width = 100;
        int height = 20;
        int x = this.guiLeft + (this.xSize - width) / 2;
        int y = this.guiTop + GuiLayoutDefinition.BORDER_SIZE + GuiLayoutDefinition.NAME_FIELD_HEIGHT + 108 - height;
        this.buttonList.add(new GuiButton(0, x, y, width, height, "INSTALL"));
    }


    @Override
    protected void actionPerformed(GuiButton button) throws IOException {
        Log.log("Player pressed INSTALL", LogType.INFO);
        NetworkWrapper.INSTANCE.sendToServer(new GuiMessage("Player pressed INSTALL"));
    }


    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
        super.drawGuiContainerBackgroundLayer(partialTicks, mouseX, mouseY);

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
