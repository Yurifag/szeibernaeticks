package main.de.grzb.szeibernaeticks.szeibernaeticks.capability;

import main.de.grzb.szeibernaeticks.szeibernaeticks.BodyPart;
import net.minecraft.nbt.NBTTagCompound;

/**
 * Stores and retrieves the Data behind an Szeibernaetick.
 *
 * @author DemRat
 *
 */
public interface ISzeibernaetickCapability {

    /**
     * Returns an unique Identifier for this capability _class_, not instance.
     * This is primarily used as part of the key when attaching this capability
     * to items.
     *
     * @return An unique Identifier.
     */
    public String getIdentifier();

    /**
     * Stores this Capability as a NBTTagCompound.
     *
     * @return The NBT storing this capability.
     */
    public NBTTagCompound toNBT();

    /**
     * Restores this capabilities values from an NBTTagCompound.
     *
     * @param nbt
     */
    public void fromNBT(NBTTagCompound nbt);

    /**
     * The {@code BodyPart} this Capability inhabits when installed.
     *
     * @return
     */
    public BodyPart getBodyPart();
}
