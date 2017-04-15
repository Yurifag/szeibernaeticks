package main.de.grzb.szeibernaeticks.item.szeibernaetick;

import javax.annotation.Nullable;

import main.de.grzb.szeibernaeticks.item.ItemBase;
import main.de.grzb.szeibernaeticks.szeibernaeticks.BodyPart;
import main.de.grzb.szeibernaeticks.szeibernaeticks.capability.ISzeibernaetickCapability;
import main.de.grzb.szeibernaeticks.szeibernaeticks.capability.armoury.ISzeibernaetickArmouryCapability;
import main.de.grzb.szeibernaeticks.szeibernaeticks.capability.armoury.SzeibernaetickArmouryProvider;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.ICapabilityProvider;

/**
 * Denotes an Item usable as an Szeibernaetick. The only method is to identify
 * in what {@code BodyPart} this is to be installed.<br>
 * <br>
 * To add a new Szeibernaetick, you need a) an Item implementing
 * ISzeibernaetick, b) any Method of adding it to the
 * ISzeibernaetickStorageCapability of an Entity and c) an
 * ISzeibernaetickEventHandler implementing the functionality via
 * EventSubscriptions.
 *
 * @author DemRat
 *
 */
public abstract class SzeibernaetickBase extends ItemBase {

    @CapabilityInject(ISzeibernaetickCapability.class)
    public static final Capability<ISzeibernaetickCapability> capability = null;

    public SzeibernaetickBase(String name) {
        super(name);
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
        // Get the Stack currently right-clicked
        ItemStack thisStack = playerIn.getHeldItem(handIn);
        // Make sure that the itemStack actually is of this Item.
        if(thisStack.getItem() == this) {

            ISzeibernaetickArmouryCapability szeiberStore = playerIn
                    .getCapability(SzeibernaetickArmouryProvider.ARMOURY_CAP, null);
            // Add the ItemStack to it.
            if(szeiberStore != null && szeiberStore.getSzeibernaetick(getCapabilityInstance().getClass()) == null) {
                szeiberStore.addSzeibernaetick(getCapabilityInstance());
                thisStack.shrink(1);
            }
        }

        return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, thisStack);
    }

    protected BodyPart bodyPart;

    /**
     * Returns what kind of {@code BodyPart} this {@code ISzeibernaetick} is.
     *
     * @return What kind of {@code BodyPart} this {@code ISzeibernaetick} is.
     */
    public BodyPart getBodyPart() {
        return bodyPart;
    }

    /**
     * Returns a new instance of the ISzeibernaetickCapability corresponding to
     * this ISzeibernaetick.
     *
     * @return A new instance of the correct ISzeibernaetickCapability.
     */
    public abstract ISzeibernaetickCapability getCapabilityInstance();

    /**
     * Returns the Capability of ISzeibernaetickCapabilites.
     *
     * @return
     */
    public Capability<ISzeibernaetickCapability> getCapability() {
        return capability;
    }

    @Override
    public abstract ICapabilityProvider initCapabilities(ItemStack stack, @Nullable NBTTagCompound nbt);
}
