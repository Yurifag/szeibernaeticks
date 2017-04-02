package main.de.grzb.szeibernaeticks.item;

import main.de.grzb.szeibernaeticks.Names;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class ModItems {
	public static ItemBase ingot_copper;
	public static ItemBase metal_bones;
	
	/**
	 * Initializes the mod items.
	 * 
	 * This is called from {@link main.de.grzb.szeibernaeticks.CommonProxy}
	 */
	public static void init() {
		ingot_copper = register(new ItemBase(Names.COPPER_INGOT).setCreativeTab(CreativeTabs.MATERIALS));
		metal_bones = register(new ItemBase(Names.METAL_BONES).setCreativeTab(CreativeTabs.MISC));
	}
	
	private static <T extends Item> T register(T item) {
		GameRegistry.register(item);
		
		if(item instanceof ItemBase) {
			((ItemBase)item).registerItemModel();
		}
		
		return item;
	}
}
