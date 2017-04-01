package main.de.grzb.szeibernaeticks;

import main.de.grzb.szeibernaeticks.items.ModItems;
import main.de.grzb.szeibernaeticks.block.ModBlocks;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class CommonProxy {

    public void preInit(FMLPreInitializationEvent e) {
    	e.getModLog().debug("CommonProxy, preInit.");
    }

    public void init(FMLInitializationEvent e) {
    	ModItems.init();
    	ModBlocks.init();
    }

    public void postInit(FMLPostInitializationEvent e) {

    }
}
