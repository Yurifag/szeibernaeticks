package main.de.grzb.szeibernaeticks.szeibernaeticks;

import main.de.grzb.szeibernaeticks.szeibernaeticks.capability.BodyPart;

/**
 * Denotes an Item usable as an Szeibernaetick. The only method is to identify
 * in what {@code BodyPart} this is to be installed.
 *
 * @author DemRat
 *
 */
public interface ISzeibernaetick {

	/**
	 * Returns what kind of {@code BodyPart} this {@code ISzeibernaetick} is.
	 *
	 * @return What kind of {@code BodyPart} this {@code ISzeibernaetick} is.
	 */
	public BodyPart getBodyPart();
}
