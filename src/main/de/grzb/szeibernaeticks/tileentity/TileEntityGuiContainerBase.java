package main.de.grzb.szeibernaeticks.tileentity;

import main.de.grzb.szeibernaeticks.client.gui.container.GuiContainerRenderer;
import main.de.grzb.szeibernaeticks.container.GuiContainerBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;

/**
 * Base class for a tile entity handling an inventory.
 * 
 * @author yuri
 *
 */
public abstract class TileEntityGuiContainerBase extends TileEntityContainerBase {
    
    public TileEntityGuiContainerBase(String tileEntityName) {
        super(tileEntityName);
    }
    
    /**
     * Get a new instance of the corresponding container.
     * 
     * @param playerInventory
     * @return {@link GuiContainerBase}
     */
    public abstract GuiContainerBase getContainer(IInventory playerInventory);
    
    /**
     * Get a new instance of the corresponding (client-side) container GUI.
     * 
     * @param playerInventory
     * @return {@link GuiContainerRenderer}
     */
    public GuiContainerRenderer getGuiContainerRenderer(IInventory playerInventory) {
        return new GuiContainerRenderer(this.getContainer(playerInventory));
    }
    
    /**
     * Only lets the player open the inventory if they're in range.
     * 
     * @return {@link Boolean}
     */
    public boolean canInteractWith(EntityPlayer player) {
        return !this.isInvalid() && player.getDistanceSq(pos.add(0.5D, 0.5D, 0.5D)) <= 64D;
    }
}
