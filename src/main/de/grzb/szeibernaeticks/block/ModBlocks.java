package main.de.grzb.szeibernaeticks.block;

import main.de.grzb.szeibernaeticks.block.BlockOreCopper;
import net.minecraft.block.Block;
import net.minecraftforge.fml.common.registry.GameRegistry;

public final class ModBlocks {
	public static Block oreCopper;

	public static void init() {
		oreCopper = new BlockOreCopper();
		GameRegistry.register(oreCopper);
	}
}
