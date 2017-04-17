package main.de.grzb.szeibernaeticks.szeibernaeticks.event;

import main.de.grzb.szeibernaeticks.control.Log;
import main.de.grzb.szeibernaeticks.control.LogType;
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

    @SubscribeEvent
    public void onEntityHurt(LivingHurtEvent e) {
        ISzeibernaetickArmouryCapability c = e.getEntity().getCapability(SzeibernaetickArmouryProvider.ARMOURY_CAP,
                null);

        if(c.getSzeibernaetick(szeiberClass) != null) {
            Log.log("Bones attempting to negate Damage!", LogType.DEBUG, LogType.SZEIBER_HANDLER);
            Log.log("Amount: " + e.getAmount(), LogType.DEBUG, LogType.SZEIBER_HANDLER, LogType.SPECIFIC);
            Log.log("Also, source: " + e.getSource().damageType, LogType.DEBUG, LogType.SZEIBER_HANDLER,
                    LogType.SPECIFIC);
            if(!e.getSource().isUnblockable()) {
                e.setAmount(e.getAmount() / 2);
            }
        }
    }
}
