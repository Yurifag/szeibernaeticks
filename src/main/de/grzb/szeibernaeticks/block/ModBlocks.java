package main.de.grzb.szeibernaeticks.block;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.fml.common.registry.GameRegistry;

/**
 * Initializes the mod blocks and creates corresponding ItemBlocks.
 * 
 * This is called from {@link main.de.grzb.szeibernaeticks.CommonProxy}
 * @author yuri
 *
 */
public final class ModBlocks {
	public static BlockBase ore_copper;
	
	public static void init() {
		ore_copper = register(new BlockOreCopper());
	}

	private static <T extends Block> T register(T block, ItemBlock itemBlock) {
		GameRegistry.register(block);
		GameRegistry.register(itemBlock);
		
		if(block instanceof BlockBase) {
			((BlockBase)block).registerItemModel(itemBlock);
		}
		
		return block;
	}
	
	private static <T extends Block> T register(T block) {
		ItemBlock itemBlock = new ItemBlock(block);
		itemBlock.setRegistryName(block.getRegistryName());
		return register(block, itemBlock);
	}
}
