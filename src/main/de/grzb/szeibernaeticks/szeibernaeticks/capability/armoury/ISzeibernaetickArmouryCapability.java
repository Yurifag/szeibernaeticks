package main.de.grzb.szeibernaeticks.szeibernaeticks.capability.armoury;

import java.util.Collection;

import main.de.grzb.szeibernaeticks.szeibernaeticks.capability.ISzeibernaetickCapability;

/**
 * Stores {@code ISzeibernaeticks}.<br>
 * <br>
 * Provides Methods to store and retrieve {@code ISzeibernaeticks} in the form
 * of {@code ItemStacks}.
 *
 * @author DemRat
 *
 */
public interface ISzeibernaetickArmouryCapability {
    
    /**
     * Adds the given ISzeibernaetick to storage.
     *
     * @param szeiber
     *            The {@code ItemStack} to add to this Storage.
     * @return True if successfull
     */
    public boolean addSzeibernaetick(ISzeibernaetickCapability szeibernaetick);
    
    /**
     * Returns the {@code ISzeibernaetickCapability} of the given
     * {@code ISzeibernaetick}, or null if it is not installed.
     *
     * @param item
     *            The {@code ISzeibernaetick} to look up.
     * @return The {@code ItemStack} of the {@code ISzeibernaetick}, or null
     */
    public ISzeibernaetickCapability getSzeibernaetick(Class<? extends ISzeibernaetickCapability> szeiberClass);
    
    /**
     * Returns an Array containing all installed {@code ISzeibernaeticks}.
     *
     * @return An Array with all installed {@code ISzeibernaeticks}.
     */
    public Collection<ISzeibernaetickCapability> getSzeibernaeticks();
    
    /**
     * Removes the given {@code ISzeibernaetick}, if it is installed, and
     * returns its ItemStack.
     *
     * @param name
     *            The {@code ISzeibernaetick} to remove.
     * @return A copy of the ItemStack of the removed ISzeibernaetick, or null
     *         if it wasn't installed.
     */
    public ISzeibernaetickCapability removeSzeibernaetick(String identifier);
    
}
