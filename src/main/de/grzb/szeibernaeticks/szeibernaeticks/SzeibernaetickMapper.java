package main.de.grzb.szeibernaeticks.szeibernaeticks;

import java.util.concurrent.ConcurrentHashMap;

import main.de.grzb.szeibernaeticks.Szeibernaeticks;
import main.de.grzb.szeibernaeticks.item.szeibernaetick.SzeibernaetickBase;
import main.de.grzb.szeibernaeticks.szeibernaeticks.capability.ISzeibernaetickCapability;

/**
 * Maps the identifiers of Capabilities to the Capability classes as well as
 * those Classes to Items.
 *
 * @author DemRat
 *
 */
public class SzeibernaetickMapper {

    public static final SzeibernaetickMapper instance = new SzeibernaetickMapper();

    private ConcurrentHashMap<Class<? extends ISzeibernaetickCapability>, SzeibernaetickBase> itemMap;
    private ConcurrentHashMap<String, Class<? extends ISzeibernaetickCapability>> idMap;

    private SzeibernaetickMapper() {
        itemMap = new ConcurrentHashMap<Class<? extends ISzeibernaetickCapability>, SzeibernaetickBase>();
        idMap = new ConcurrentHashMap<String, Class<? extends ISzeibernaetickCapability>>();
    }

    /**
     * Registers the given Class as corresponding to the given Item. This also
     * adds the mapping from identifier to Class.
     *
     * @param cap
     * @param item
     */
    public void register(Class<? extends ISzeibernaetickCapability> cap, SzeibernaetickBase item) {
        if(itemMap.put(cap, item) != null) {
            Szeibernaeticks.getLogger().error(
                    "Overrode Szeibernaetick Item Mapping! This should not happen. Did you try to register different items for the same Capability?");
        }

        try {
            if(idMap.put(cap.newInstance().getIdentifier(), cap) != null) {
                Szeibernaeticks.getLogger().error(
                        "Overrode Szeibernaetick Capability Mapping! This should not happen. Did you register 2 Capabilities with identical Identifiers?");
            }
        }
        catch(InstantiationException e) {
            e.printStackTrace();
        }
        catch(IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public SzeibernaetickBase getItemFromIdentifier(String identifier) {
        return itemMap.get(identifier);
    }

    public Class<? extends ISzeibernaetickCapability> getCapabilityFromIdentifier(String identifier) {
        return idMap.get(identifier);
    }

}
