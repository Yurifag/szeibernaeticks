package main.de.grzb.szeibernaeticks.tileentity;

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
    
    public TileEntityBase(String tileEntityName) {
        super();
        this.tileEntityName = tileEntityName;
    }
    
    public String getName() {
        return this.tileEntityName;
    }
}
