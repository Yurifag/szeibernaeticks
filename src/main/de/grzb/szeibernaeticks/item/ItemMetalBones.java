package main.de.grzb.szeibernaeticks.item;

import main.de.grzb.szeibernaeticks.szeibernaeticks.ISzeibernaetick;
import main.de.grzb.szeibernaeticks.szeibernaeticks.capability.BodyPart;
import main.de.grzb.szeibernaeticks.szeibernaeticks.capability.ISzeibernaetickStorageCapability;
import main.de.grzb.szeibernaeticks.szeibernaeticks.capability.SzeibernaetickStorageProvider;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;

public class ItemMetalBones extends ItemBase implements ISzeibernaetick {
	
	public ItemMetalBones(String name) {
		super(name);
		setCreativeTab(CreativeTabs.COMBAT);
	}
	
	@Override
	public BodyPart getBodyPart() {
		return BodyPart.bones;
	}
	
	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
		// Get the Stack currently right-clicked
		ItemStack thisStack = playerIn.getHeldItem(handIn);
		// Make sure that the itemStack actually is of this Item.
		if (thisStack.getItem().getClass() == this.getClass()) {
			// Get the SzeibernaeticksStorage of the Player right-clicking
			ISzeibernaetickStorageCapability szeiberStore = playerIn
					.getCapability(SzeibernaetickStorageProvider.SZEIBER_CAP, null);
			// Add the ItemStack to it.
			if (szeiberStore != null && szeiberStore.addSzeibernaetick(thisStack)) {
				thisStack.shrink(1);
			}
		}
		
		return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, thisStack);
	}
}
