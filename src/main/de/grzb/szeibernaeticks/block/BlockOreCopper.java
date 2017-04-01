package main.de.grzb.szeibernaeticks.block;

import main.de.grzb.szeibernaeticks.Main;
import net.minecraft.block.BlockOre;

/**
 * Copper ore. Used as basis for almost all Items and Blocks.
 * 
 * TODO: Register the corresponding item as oreCopper
 * TODO: EVERYTHING about Copper ore
 * @author DemRat
 *
 */
public class BlockOreCopper extends BlockOre {

	public BlockOreCopper() {
		super();
		setHardness(3.0F);
		setResistance(5.0F);
		setUnlocalizedName("oreCopper");
		setRegistryName(Main.MODID + ":" + getUnlocalizedName());
	}
}
