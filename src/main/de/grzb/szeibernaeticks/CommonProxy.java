package main.de.grzb.szeibernaeticks;

import main.de.grzb.szeibernaeticks.block.ModBlocks;
import main.de.grzb.szeibernaeticks.crafting.ModRecipes;
import main.de.grzb.szeibernaeticks.item.ModItems;
import main.de.grzb.szeibernaeticks.szeibernaeticks.capability.ISzeibernaetickStorageCapability;
import main.de.grzb.szeibernaeticks.szeibernaeticks.capability.SzeibernaetickCapabilityAttacher;
import main.de.grzb.szeibernaeticks.szeibernaeticks.capability.SzeibernaetickStorageCapabilityStorage;
import main.de.grzb.szeibernaeticks.szeibernaeticks.capability.SzeibernaetickStorage;
import main.de.grzb.szeibernaeticks.szeibernaeticks.event.SzeibernaetickEventMetalBones;
import main.de.grzb.szeibernaeticks.world.ModWorldGenerator;
import net.minecraft.item.Item;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.capabilities.CapabilityManager;
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
		Szeibernaeticks.setLogger(e.getModLog());
		Szeibernaeticks.getLogger().debug("CommonProxy, preInit.");
		ModItems.init();
		ModBlocks.init();
	}

	public void init(FMLInitializationEvent e) {
		ModRecipes.init();
		GameRegistry.registerWorldGenerator(new ModWorldGenerator(), 0);

		CapabilityManager.INSTANCE.register(ISzeibernaetickStorageCapability.class,
				new SzeibernaetickStorageCapabilityStorage(), SzeibernaetickStorage.class);

		MinecraftForge.EVENT_BUS.register(new SzeibernaetickEventMetalBones(ModItems.metal_bones));
		MinecraftForge.EVENT_BUS.register(new SzeibernaetickCapabilityAttacher());
	}

	public void postInit(FMLPostInitializationEvent e) {

	}

	public void registerItemRenderer(Item item, int meta, String id) {
		// method stub, client-only method
	}
}
