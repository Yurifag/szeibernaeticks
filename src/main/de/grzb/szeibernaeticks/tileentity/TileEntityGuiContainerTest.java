package main.de.grzb.szeibernaeticks.tileentity;

import main.de.grzb.szeibernaeticks.container.GuiContainerBase;
import main.de.grzb.szeibernaeticks.container.GuiContainerTest;
import net.minecraft.inventory.IInventory;

public class TileEntityGuiContainerTest extends TileEntityGuiContainerBase {
  private final int containerSize = 2;

  public TileEntityGuiContainerTest() {
    super("test");
  }

  public GuiContainerBase getContainer(IInventory playerInventory) {
    return new GuiContainerTest(playerInventory, this);
  }

  @Override
  public int getContainerSize() {
    return this.containerSize;
  }
}
