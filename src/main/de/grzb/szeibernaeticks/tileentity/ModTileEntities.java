package main.de.grzb.szeibernaeticks.tileentity;

import main.de.grzb.szeibernaeticks.Szeibernaeticks;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.fml.common.registry.GameRegistry;

/**
 * Registers all tile entities during pre-init.
 * 
 * @see main.de.grzb.szeibernaeticks.CommonProxy CommonProxy
 * @author yuri
 */
public final class ModTileEntities {
    
    public static TileEntity test;
    
    public static void init() {
        test = register(new TileEntityGuiContainerTest());
    }
    
    private static <T extends TileEntityBase> T register(T tileEntity) {
        GameRegistry.registerTileEntity(tileEntity.getClass(), Szeibernaeticks.RESOURCE_PREFIX + tileEntity.getName());
        
        return tileEntity;
    }
    
}
