package main.de.grzb.szeibernaeticks.tileentity;

import javax.annotation.Nullable;

import net.minecraft.block.Block;
import net.minecraft.tileentity.TileEntity;

/**
 * Holds the tile entity's name which is used to register it in the
 * {@link net.minecraftforge.fml.common.registry.GameRegistry GameRegistry}.
 * 
 * @see ModTileEntities
 * @author yuri
 *
 */
public abstract class TileEntityBase extends TileEntity {
    private String tileEntityName;
    private Block block;

    protected TileEntityBase(String tileEntityName) {
        this(tileEntityName, null);
    }

    protected TileEntityBase(String tileEntityName, Block block) {
        super();
        this.tileEntityName = tileEntityName;
        this.block = block;
    }

    public String getName() {
        return this.tileEntityName;
    }

    @Nullable
    public Block getBlock() {
        return this.block;
    }
}
