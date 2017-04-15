package main.de.grzb.szeibernaeticks.tileentity;

import main.de.grzb.szeibernaeticks.ItemStackTileEntityHandler;
import net.minecraft.block.Block;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.items.CapabilityItemHandler;

/**
 * Base class for a tile entity handling an inventory.
 * 
 * @author yuri
 *
 */
public abstract class TileEntityContainerBase extends TileEntityBase {
    protected final int containerSize;
    protected final ItemStackTileEntityHandler itemStackHandler;

    protected TileEntityContainerBase(String tileEntityName, int containerSize) {
        super(tileEntityName);
        this.containerSize = containerSize;
        this.itemStackHandler = new ItemStackTileEntityHandler(containerSize, this);
    }

    protected TileEntityContainerBase(String tileEntityName, Block block, int containerSize) {
        super(tileEntityName, block);
        this.containerSize = containerSize;
        this.itemStackHandler = new ItemStackTileEntityHandler(containerSize, this);
    }

    public int getContainerSize() {
        return this.containerSize;
    }

    @Override
    public void readFromNBT(NBTTagCompound compound) {
        super.readFromNBT(compound);
        if(compound.hasKey("items")) {
            this.itemStackHandler.deserializeNBT((NBTTagCompound) compound.getTag("items"));
        }
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound) {
        super.writeToNBT(compound);
        compound.setTag("items", this.itemStackHandler.serializeNBT());
        return compound;
    }

    @Override
    public boolean hasCapability(Capability<?> capability, EnumFacing facing) {
        if(capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
            return true;
        }
        return super.hasCapability(capability, facing);
    }

    @Override
    public <T> T getCapability(Capability<T> capability, EnumFacing facing) {
        if(capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
            return CapabilityItemHandler.ITEM_HANDLER_CAPABILITY.cast(this.itemStackHandler);
        }
        return super.getCapability(capability, facing);
    }
}
