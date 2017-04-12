package main.de.grzb.szeibernaeticks.container;

import javax.annotation.Nullable;

import main.de.grzb.szeibernaeticks.Szeibernaeticks;
import main.de.grzb.szeibernaeticks.tileentity.TileEntityGuiContainerBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

/**
 * Base class to create container GUIs.
 * 
 * @author yuri
 */
public abstract class GuiContainerBase extends ContainerBase {
  public static final int CONTAINER_BORDER_TOP = 17;
  public static final int CONTAINER_BORDER_WIDTH = 7;
  public static final int ITEM_SLOT_BORDER_WIDTH = 1;
  public static final int ITEM_SLOT_INNER_WIDTH = 16;
  public static final int ITEM_SLOT_SIZE = ITEM_SLOT_BORDER_WIDTH + ITEM_SLOT_INNER_WIDTH + ITEM_SLOT_BORDER_WIDTH;
  public static final int PLAYER_MAIN_INVENTORY_HEIGHT = 3 * (ITEM_SLOT_BORDER_WIDTH + ITEM_SLOT_INNER_WIDTH + ITEM_SLOT_BORDER_WIDTH);
  public static final int HOTBAR_MARGIN_TOP = 4;
  public static final int PLAYER_INVENTORY_HEIGHT = PLAYER_MAIN_INVENTORY_HEIGHT + HOTBAR_MARGIN_TOP + ITEM_SLOT_SIZE;
  public static final int PLAYER_HOTBAR_SLOTS = 9;

  protected int innerGuiHeight;

  private int guiWidth = 176;
  private int guiHeight;
  private ResourceLocation background = new ResourceLocation(Szeibernaeticks.RESOURCE_PREFIX + "textures/gui/container/" + this.tileEntityContainer.getName() + ".png");

  /**
   * Sets up a basic GUI layout. Take care for the player's own inventory and
   * border spacing.
   * 
   * @param playerInventory
   * @param tileEntityGuiContainer
   * @param innerGuiHeight
   *          Height in pixels for the custom GUI section
   */
  public GuiContainerBase(IInventory playerInventory, TileEntityGuiContainerBase tileEntityGuiContainer, int innerGuiHeight) {
    super(tileEntityGuiContainer);
    this.setInnerGuiHeight(innerGuiHeight);
    this.guiHeight = CONTAINER_BORDER_TOP + this.innerGuiHeight + PLAYER_INVENTORY_HEIGHT + CONTAINER_BORDER_WIDTH;

    this.addGuiLayout();
    this.addPlayerSlotLayout(playerInventory);
  }

  protected abstract void addGuiLayout();

  /**
   * Adds the player's inventory and hotbar to the container.
   * 
   * @param playerInventory
   */
  protected void addPlayerSlotLayout(IInventory playerInventory) {
    int x;
    int y;
    int slotId;

    /**
     * Main inventory slots
     */
    for(int row = 0; row < 3; ++row) {
      for(int column = 0; column < 9; ++column) {
        x = CONTAINER_BORDER_WIDTH + ITEM_SLOT_BORDER_WIDTH + column * ITEM_SLOT_SIZE;
        y = CONTAINER_BORDER_TOP + this.innerGuiHeight + ITEM_SLOT_BORDER_WIDTH + row * ITEM_SLOT_SIZE;
        slotId = column + row * 9 + PLAYER_HOTBAR_SLOTS;
        this.addSlotToContainer(new Slot(playerInventory, slotId, x, y));
      }
    }

    /**
     * Hotbar slots
     */
    for(int row = 0; row < PLAYER_HOTBAR_SLOTS; ++row) {
      x = CONTAINER_BORDER_WIDTH + ITEM_SLOT_BORDER_WIDTH + row * ITEM_SLOT_SIZE;
      y = CONTAINER_BORDER_TOP + this.innerGuiHeight + PLAYER_MAIN_INVENTORY_HEIGHT + HOTBAR_MARGIN_TOP + ITEM_SLOT_BORDER_WIDTH;
      slotId = row;
      this.addSlotToContainer(new Slot(playerInventory, slotId, x, y));
    }
  }

  @Nullable
  @Override
  public ItemStack transferStackInSlot(EntityPlayer player, int index) {
    ItemStack itemstack = ItemStack.EMPTY;
    Slot slot = this.inventorySlots.get(index);

    if(slot != null && slot.getHasStack()) {
      ItemStack slotItemStack = slot.getStack();
      itemstack = slotItemStack.copy();

      if(index < this.tileEntityContainer.getContainerSize()) {
        if(!this.mergeItemStack(slotItemStack, this.tileEntityContainer.getContainerSize(), this.inventorySlots.size(), true)) {
          return ItemStack.EMPTY;
        }
      }
      else if(!this.mergeItemStack(slotItemStack, 0, this.tileEntityContainer.getContainerSize(), false)) {
        return ItemStack.EMPTY;
      }

      if(slotItemStack.isEmpty()) {
        slot.putStack(ItemStack.EMPTY);
      }
      else {
        slot.onSlotChanged();
      }
    }

    return itemstack;
  }

  @Override
  public boolean canInteractWith(EntityPlayer player) {
    return ((TileEntityGuiContainerBase) this.tileEntityContainer).canInteractWith(player);
  }

  private void setInnerGuiHeight(int height) {
    if(height > 156) {
      throw new IllegalArgumentException("innerGuiHeight can not be greater than 156 pixels.");
    }
    this.innerGuiHeight = height;
  }

  public int getGuiWidth() {
    return this.guiWidth;
  }

  public int getGuiHeight() {
    return this.guiHeight;
  }

  public ResourceLocation getGuiBackground() {
    return this.background;
  }
}
