package main.de.grzb.szeibernaeticks.items;

import main.de.grzb.szeibernaeticks.Main;
import main.de.grzb.szeibernaeticks.items.szeibernaeticks.SzeibernaetickMetalBones;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.registry.GameRegistry;

/**
 * Initializes all Mod Items.
 * 
 * @author DemRat
 *
 */
public final class ModItems {
	
	public static Item metalBones;
	
	/**
	 * Initializes the mod items.
	 * 
	 * Item Registry names and unlocalized names are set here for quick refactoring.
	 * This is called from {@link main.de.grzb.szeibernaeticks.CommonProxy}
	 */
	public static void init() {
		metalBones = (new SzeibernaetickMetalBones()).setUnlocalizedName(Main.MODID + ":" + "metalBones").setRegistryName("szeibernaetickMetalBones");
		GameRegistry.register(metalBones);
	}

}
