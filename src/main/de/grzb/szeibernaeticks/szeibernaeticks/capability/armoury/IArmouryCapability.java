package main.de.grzb.szeibernaeticks.szeibernaeticks.capability.armoury;

import main.de.grzb.szeibernaeticks.szeibernaeticks.BodyPart;
import main.de.grzb.szeibernaeticks.szeibernaeticks.capability.ISzeibernaetickCapability;
import net.minecraft.entity.Entity;

import java.util.Collection;

/**
 * Stores {@code ISzeibernaeticksCapabilities}.<br>
 * <br>
 * Provides Methods to store and retrieve {@code ISzeibernaetickCapabilities}
 * based on their class or their {@code BodyPart}.
 *
 * @author DemRat
 */
public interface IArmouryCapability {

    /**
     * Returns the Entity the armoury is attached to.
     *
     * @return Duh.
     */
    Entity getEntity();

    /**
     * Adds the given ISzeibernaetickCapability to storage.
     *
     * @param szeibernaetick The {@code ISzeibernaetickCapability} to add to this Storage.
     * @return True if successful
     */
    boolean addSzeibernaetick(ISzeibernaetickCapability szeibernaetick);

    /**
     * Returns the instance of the given {@code ISzeibernaetickCapability}, or
     * null if it is none is installed.
     *
     * @param szeiberClass The class of the {@code ISzeibernaetickCapability} to look up.
     * @return The {@code ISzeibernaetickCapability} instance of the given
     * class, or null
     */
    ISzeibernaetickCapability getSzeibernaetick(Class<? extends ISzeibernaetickCapability> szeiberClass);

    /**
     * Returns an Array containing all installed
     * {@code ISzeibernaetickCapabilities}.
     *
     * @return A Collection of all installed {@code ISzeibernaeticks}.
     */
    Collection<ISzeibernaetickCapability> getSzeibernaeticks();

    /**
     * Removes the given {@code ISzeibernaetick}, if it is installed, and
     * returns its {@code ItemStack}.
     *
     * @param szeibernaetick The {@code ISzeibernaetick} to remove.
     * @return A copy of the {@code ItemStack} of the removed
     * {@code ISzeibernaetick}, or null if it wasn't installed.
     */
    ISzeibernaetickCapability removeSzeibernaetick(ISzeibernaetickCapability szeibernaetick);

    /**
     * Returns the {@code ISzeibernaetickCapability} associated with this
     * BodyPart.
     *
     * @param bodyPart The {@code BodyPart} being asked for.
     * @return The {@code ISzeibernaetickCapability} stored for the given
     * {@code BodyPart}.
     */
    ISzeibernaetickCapability getBodyPart(BodyPart bodyPart);

}
