package main.de.grzb.szeibernaeticks;

import main.de.grzb.szeibernaeticks.block.ModBlocks;
import main.de.grzb.szeibernaeticks.crafting.ModRecipes;
import main.de.grzb.szeibernaeticks.item.ModItems;
import main.de.grzb.szeibernaeticks.world.ModWorldGenerator;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

/**
 * Executes code on both, server and client.
 * 
 * @author yuri
 *
 */
public class CommonProxy {
    public void preInit(FMLPreInitializationEvent e) {
        e.getModLog().debug("CommonProxy, preInit.");
        ModItems.init();
        ModBlocks.init();
    }

    public void init(FMLInitializationEvent e) {
        ModRecipes.init();
        GameRegistry.registerWorldGenerator(new ModWorldGenerator(), 0);
    }

    public void postInit(FMLPostInitializationEvent e) {
        
    }

    public void registerItemRenderer(Item item, int meta, String id) {
        // method stub, client-only method
    }
}
