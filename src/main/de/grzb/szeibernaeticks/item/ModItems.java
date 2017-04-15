package main.de.grzb.szeibernaeticks.item;

import main.de.grzb.szeibernaeticks.item.szeibernaetick.ItemConductiveVeins;
import main.de.grzb.szeibernaeticks.item.szeibernaetick.ItemMetalBones;
import main.de.grzb.szeibernaeticks.item.szeibernaetick.SzeibernaetickBase;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.registry.GameRegistry;

/**
 * Registers all mod items during pre-init. Configure src/main/resources as
 * source folder in Eclipse to make resources work while debugging.
 *
 * @see main.de.grzb.szeibernaeticks.CommonProxy CommonProxy
 * @author yuri
 */
public final class ModItems {

    public static ItemBase ingot_copper;
    public static SzeibernaetickBase metal_bones;
    public static SzeibernaetickBase conductive_veins;

    /**
     * Initializes the mod items. Configure src/main/resources as source folder
     * in Eclipse to make resources work.
     *
     * This is called from {@link main.de.grzb.szeibernaeticks.CommonProxy}
     */
    public static void init() {
        ingot_copper = register(new ItemBase("ingot_copper").setCreativeTab(CreativeTabs.MATERIALS));
        metal_bones = register(new ItemMetalBones("metal_bones"));
        conductive_veins = register(new ItemConductiveVeins("conductive_veins"));
    }

    private static <T extends Item> T register(T item) {
        GameRegistry.register(item);

        if(item instanceof ItemBase) {
            ((ItemBase) item).registerItemModel();
        }

        return item;
    }
}
