package main.de.grzb.szeibernaeticks.tileentity;

import main.de.grzb.szeibernaeticks.client.gui.GuiId;
import main.de.grzb.szeibernaeticks.container.GuiContainerBase;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;

/**
 * Base class for a tile entity handling an inventory with GUI.
 * 
 * @author yuri
 *
 */
public abstract class TileEntityGuiContainerBase extends TileEntityContainerBase {
    protected final GuiId guiId;

    protected TileEntityGuiContainerBase(String tileEntityName, int containerSize, GuiId guiId) {
        this(tileEntityName, null, containerSize, guiId);
    }

    protected TileEntityGuiContainerBase(String tileEntityName, Block block, int containerSize, GuiId guiId) {
        super(tileEntityName, block, containerSize);
        this.guiId = guiId;
    }

    /**
     * Get a new instance of the corresponding container.
     * 
     * @param playerInventory
     * @return {@link GuiContainerBase}
     */
    public abstract GuiContainerBase getContainer(IInventory playerInventory);

    /**
     * Only lets the player open the inventory if they're in range.
     * 
     * @return {@link Boolean}
     */
    public boolean canInteractWith(EntityPlayer player) {
        return !this.isInvalid() && player.getDistanceSq(this.pos.add(0.5D, 0.5D, 0.5D)) <= 64D;
    }

    public GuiId getGuiId() {
        return this.guiId;
    }

}
