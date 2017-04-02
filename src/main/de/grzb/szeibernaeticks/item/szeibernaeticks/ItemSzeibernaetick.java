package main.de.grzb.szeibernaeticks.items.szeibernaeticks;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;

/**
 * Defines the basic behaviour for cybernetics.
 * 
 * @author DemRat
 *
 */
public abstract class ItemSzeibernaetick extends Item {
	
	public ItemSzeibernaetick() {
		setCreativeTab(CreativeTabs.COMBAT);
	}
	
	public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
		//TODO: Basic szeibernaetic behaviour (Begin with right click installing it, then add logic)
		return super.onItemRightClick(worldIn, playerIn, handIn);
	}

}
