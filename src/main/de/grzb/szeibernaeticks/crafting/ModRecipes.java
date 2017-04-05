package main.de.grzb.szeibernaeticks.crafting;

import main.de.grzb.szeibernaeticks.block.ModBlocks;
import main.de.grzb.szeibernaeticks.item.ModItems;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class ModRecipes {
    public static void init() {
        GameRegistry.addSmelting(ModBlocks.ore_copper, new ItemStack(ModItems.ingot_copper), 0.7F);
    }
}
