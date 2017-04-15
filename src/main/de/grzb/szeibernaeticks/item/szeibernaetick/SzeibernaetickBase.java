package main.de.grzb.szeibernaeticks.item.szeibernaetick;

import main.de.grzb.szeibernaeticks.item.ItemBase;
import main.de.grzb.szeibernaeticks.szeibernaeticks.BodyPart;
import main.de.grzb.szeibernaeticks.szeibernaeticks.capability.ISzeibernaetickCapability;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;

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
}
