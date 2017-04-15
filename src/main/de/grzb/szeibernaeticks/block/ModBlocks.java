package main.de.grzb.szeibernaeticks.block;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.fml.common.registry.GameRegistry;

/**
 * Initializes the mod blocks and creates corresponding ItemBlocks. Configure
 * src/main/resources as source folder in Eclipse to make resources work while
 * debugging.
 * 
 * @see main.de.grzb.szeibernaeticks.CommonProxy CommonProxy
 * @author yuri
 *
 */
public final class ModBlocks {

    public static BlockBase ore_copper;

    public static void init() {
        ore_copper = register(new BlockOreCopper());
    }

    /**
     * Registers a block and it's corresponding item and item model.
     * 
     * @param block
     * @param itemBlock
     * @return {@link BlockBase}
     */
    private static <T extends Block> T register(T block, ItemBlock itemBlock) {
        GameRegistry.register(block);
        GameRegistry.register(itemBlock);

        if(block instanceof BlockBase) {
            ((BlockBase) block).registerItemModel(itemBlock);
        }

        return block;
    }

    /**
     * Creates the corresponding item to a given block and registers both.
     * 
     * @param block
     * @return {@link BlockBase}
     */
    private static <T extends Block> T register(T block) {
        ItemBlock itemBlock = new ItemBlock(block);
        itemBlock.setRegistryName(block.getRegistryName());
        return register(block, itemBlock);
    }
}
