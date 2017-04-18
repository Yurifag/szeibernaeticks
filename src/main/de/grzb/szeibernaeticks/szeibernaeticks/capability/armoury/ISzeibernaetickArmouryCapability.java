package main.de.grzb.szeibernaeticks.szeibernaeticks.capability.armoury;

import java.util.Collection;

import main.de.grzb.szeibernaeticks.szeibernaeticks.BodyPart;
import main.de.grzb.szeibernaeticks.szeibernaeticks.capability.ISzeibernaetickCapability;
import net.minecraft.entity.Entity;

/**
 * Stores {@code ISzeibernaeticksCapabilities}.<br>
 * <br>
 * Provides Methods to store and retrieve {@code ISzeibernaetickCapabilites}
 * based on their class or their {@code BodyPart}.
 *
 * @author DemRat
 *
 */
public interface ISzeibernaetickArmouryCapability {

    /**
     * Returns the Entity the armoury is attached to.
     *
     * @return Duh.
     */
    public Entity getEntity();

    /**
     * Adds the given ISzeibernaetickCapability to storage.
     *
     * @param szeiber
     *            The {@code ISzeibernaetickCapability} to add to this Storage.
     * @return True if successfull
     */
    public boolean addSzeibernaetick(ISzeibernaetickCapability szeibernaetick);

    /**
     * Returns the instance of the given {@code ISzeibernaetickCapability}, or
     * null if it is none is installed.
     *
     * @param szeiberClass
     *            The class of the {@code ISzeibernaetickCapability} to look up.
     * @return The {@code ISzeibernaetickCapability} instance of the given
     *         class, or null
     */
    public ISzeibernaetickCapability getSzeibernaetick(Class<? extends ISzeibernaetickCapability> szeiberClass);

    /**
     * Returns an Array containing all installed
     * {@code ISzeibernaetickCapabilities}.
     *
     * @return A Collection of all installed {@code ISzeibernaeticks}.
     */
    public Collection<ISzeibernaetickCapability> getSzeibernaeticks();

    /**
     * Removes the given {@code ISzeibernaetick}, if it is installed, and
     * returns its {@code ItemStack}.
     *
     * @param name
     *            The {@code ISzeibernaetick} to remove.
     * @return A copy of the {@code ItemStack} of the removed
     *         {@code ISzeibernaetick}, or null if it wasn't installed.
     */
    public ISzeibernaetickCapability removeSzeibernaetick(ISzeibernaetickCapability szeibernatick);

    /**
     * Returns the {@code ISzeibernaetickCapability} associated with this
     * BodyPart.
     *
     * @param b
     *            The {@code BodyPart} being asked for.
     * @return The {@code ISzeibernaetickCapability} stored for the given
     *         {@code BodyPart}.
     */
    public ISzeibernaetickCapability getBodyPart(BodyPart b);

}
