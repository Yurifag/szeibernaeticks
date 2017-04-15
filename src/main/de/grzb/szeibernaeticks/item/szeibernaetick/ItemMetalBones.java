package main.de.grzb.szeibernaeticks.item.szeibernaetick;

import main.de.grzb.szeibernaeticks.szeibernaeticks.SzeibernaetickMapper;
import main.de.grzb.szeibernaeticks.szeibernaeticks.capability.ISzeibernaetickCapability;
import main.de.grzb.szeibernaeticks.szeibernaeticks.capability.SzeibernaetickMetalBonesCapability;
import main.de.grzb.szeibernaeticks.szeibernaeticks.capability.armoury.ISzeibernaetickArmouryCapability;
import main.de.grzb.szeibernaeticks.szeibernaeticks.capability.armoury.SzeibernaetickArmouryProvider;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;

public class ItemMetalBones extends SzeibernaetickBase {

    public ItemMetalBones(String name) {
        super(name);
        setCreativeTab(CreativeTabs.COMBAT);
        SzeibernaetickMapper.instance.register(SzeibernaetickMetalBonesCapability.class, this);
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
        // Get the Stack currently right-clicked
        ItemStack thisStack = playerIn.getHeldItem(handIn);
        // Make sure that the itemStack actually is of this Item.
        if(thisStack.getItem() == this) {

            ISzeibernaetickArmouryCapability szeiberStore = playerIn
                    .getCapability(SzeibernaetickArmouryProvider.SZEIBER_CAP, null);
            // Add the ItemStack to it.
            if(szeiberStore != null) {
                szeiberStore.addSzeibernaetick(getCapabilityInstance());
                thisStack.shrink(1);
            }
        }

        return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, thisStack);
    }

    @Override
    public ISzeibernaetickCapability getCapabilityInstance() {
        return new SzeibernaetickMetalBonesCapability();
    }
}
