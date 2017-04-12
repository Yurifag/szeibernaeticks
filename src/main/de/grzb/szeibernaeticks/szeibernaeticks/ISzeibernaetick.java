package main.de.grzb.szeibernaeticks.szeibernaeticks;

import main.de.grzb.szeibernaeticks.szeibernaeticks.capability.BodyPart;

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
public interface ISzeibernaetick {

  /**
   * Returns what kind of {@code BodyPart} this {@code ISzeibernaetick} is.
   *
   * @return What kind of {@code BodyPart} this {@code ISzeibernaetick} is.
   */
  public BodyPart getBodyPart();
}
