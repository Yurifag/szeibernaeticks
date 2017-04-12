package main.de.grzb.szeibernaeticks.container;

import main.de.grzb.szeibernaeticks.tileentity.TileEntityContainerBase;
import main.de.grzb.szeibernaeticks.tileentity.TileEntityGuiContainerBase;
import net.minecraft.inventory.Container;

public abstract class ContainerBase extends Container {
  protected TileEntityContainerBase tileEntityContainer;

  public ContainerBase(TileEntityGuiContainerBase tileEntityGuiContainer) {
    super();
    this.tileEntityContainer = tileEntityGuiContainer;
  }
}
