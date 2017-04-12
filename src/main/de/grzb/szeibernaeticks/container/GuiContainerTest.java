package main.de.grzb.szeibernaeticks.container;

import main.de.grzb.szeibernaeticks.tileentity.TileEntityGuiContainerTest;
import net.minecraft.inventory.IInventory;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;

public class GuiContainerTest extends GuiContainerBase {
  public static final int CONTAINER_GUI_INNER_MARGIN = 10;

  public GuiContainerTest(IInventory playerInventory, TileEntityGuiContainerTest tileEntityGuiContainer) {
    super(playerInventory, tileEntityGuiContainer, 100);
  }

  protected void addGuiLayout() {
    int x;
    int y;
    IItemHandler itemHandler = this.tileEntityContainer.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null);

    x = CONTAINER_BORDER_WIDTH + CONTAINER_GUI_INNER_MARGIN + ITEM_SLOT_BORDER_WIDTH;
    y = CONTAINER_BORDER_TOP + CONTAINER_GUI_INNER_MARGIN + ITEM_SLOT_BORDER_WIDTH;
    this.addSlotToContainer(new SlotItemHandler(itemHandler, 0, x, y));

    x = this.getGuiWidth() / 2 + ITEM_SLOT_BORDER_WIDTH;
    this.addSlotToContainer(new SlotItemHandler(itemHandler, 1, x, y));
  }
}
