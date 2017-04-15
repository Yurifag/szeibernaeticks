package main.de.grzb.szeibernaeticks.szeibernaeticks.event;

import main.de.grzb.szeibernaeticks.Szeibernaeticks;
import main.de.grzb.szeibernaeticks.szeibernaeticks.capability.ISzeibernaetickCapability;
import main.de.grzb.szeibernaeticks.szeibernaeticks.capability.SzeibernaetickMetalBonesCapability;
import main.de.grzb.szeibernaeticks.szeibernaeticks.capability.armoury.ISzeibernaetickArmouryCapability;
import main.de.grzb.szeibernaeticks.szeibernaeticks.capability.armoury.SzeibernaetickArmouryProvider;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

/**
 * Implements the functionality of the ItemMetalBones ISzeibernaetick via Event
 * Subscription.
 *
 * @author DemRat
 *
 */
public class SzeibernaetickMetalBonesHandler implements ISzeibernaetickEventHandler {

    private Class<? extends ISzeibernaetickCapability> szeiberClass = SzeibernaetickMetalBonesCapability.class;

    /**
     * Constructs this EventHandler with the String identifying this Handlers
     * ISzeibernaetickCapability.
     *
     * @param szeibernaetickIdentifier
     *            The String that identifies the corresponding
     *            ISzeibernaetickCapability
     */
    public SzeibernaetickMetalBonesHandler(String szeibernaetickIdentifier) {
    }

    @SubscribeEvent
    public void onEntityHurt(LivingHurtEvent e) {
        Szeibernaeticks.getLogger().info("Entity hurt! Checking for Storage!");
        ISzeibernaetickArmouryCapability c = e.getEntity().getCapability(SzeibernaetickArmouryProvider.ARMOURY_CAP,
                null);
        Szeibernaeticks.getLogger().info("Found: " + c.toString());
        Szeibernaeticks.getLogger().info("Getting szeibernaetick: " + c.getSzeibernaetick(szeiberClass));

        if(c.getSzeibernaetick(szeiberClass) != null) {
            Szeibernaeticks.getLogger().info("Negating damage!");
            e.setAmount(e.getAmount() / 2);
        }
    }
}
