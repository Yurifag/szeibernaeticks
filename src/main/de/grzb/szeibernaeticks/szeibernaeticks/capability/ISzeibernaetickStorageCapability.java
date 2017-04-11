package main.de.grzb.szeibernaeticks.szeibernaeticks.capability;

import java.util.Collection;

import main.de.grzb.szeibernaeticks.szeibernaeticks.ISzeibernaetick;
import net.minecraft.item.ItemStack;

public interface ISzeibernaetickStorageCapability {
	
	/**
	 * Adds a copy of the given {@code ItemStack} to the Capability.
	 *
	 * @param szeiber
	 *            The {@code ItemStack} to add to this Storage.
	 * @return True if successfull
	 */
	public boolean addSzeibernaetick(ItemStack stack);
	
	/**
	 * Returns the {@code ItemStack} of the given {@code ISzeibernaetick}, or
	 * null if it is not installed.
	 *
	 * @param item
	 *            The {@code ISzeibernaetick} to look up.
	 * @return The {@code ItemStack} of the {@code ISzeibernaetick}, or null
	 */
	public ItemStack getSzeibernaetick(ISzeibernaetick item);

	/**
	 * Returns an Array containing all installed {@code ISzeibernaeticks}.
	 *
	 * @return An Array with all installed {@code ISzeibernaeticks}.
	 */
	public Collection<ItemStack> getSzeibernaeticks();
	
	/**
	 * Removes the given {@code ISzeibernaetick}, if it is installed, and
	 * returns its ItemStack.
	 *
	 * @param name
	 *            The {@code ISzeibernaetick} to remove.
	 * @return A copy of the ItemStack of the removed ISzeibernaetick, or null
	 *         if it wasn't installed.
	 */
	public ItemStack removeSzeibernaetick(ISzeibernaetick szeiber);
	
}
