package main.de.grzb.szeibernaeticks.szeibernaeticks;

import java.util.concurrent.ConcurrentHashMap;

import org.apache.logging.log4j.Logger;

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

    private Logger logger = Szeibernaeticks.getLogger();

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
        // TODO: Remove Logging
        logger.info("Registering Capability!");
        if(itemMap.put(cap, item) != null) {
            Szeibernaeticks.getLogger().error("Overrode Szeibernaetick Item Mapping! This should not happen. Did you try to register different items for the same Capability?");
        }

        try {
            String identifier = cap.newInstance().getIdentifier();
            logger.info("Mapping " + cap.toString() + " to " + identifier);
            if(idMap.put(identifier, cap) != null) {
                Szeibernaeticks.getLogger().error("Overrode Szeibernaetick Capability Mapping! This should not happen. Did you register 2 Capabilities with identical Identifiers?");
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
        // TODO: Remove Logging
        logger.info("Capability class was requested. Do I have it? Its name is " + identifier + " :" + (idMap.get(identifier) != null));
        return idMap.get(identifier);
    }

}
