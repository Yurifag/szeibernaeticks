package main.de.grzb.szeibernaeticks.szeibernaeticks.event;

import main.de.grzb.szeibernaeticks.control.Log;
import main.de.grzb.szeibernaeticks.control.LogType;
import main.de.grzb.szeibernaeticks.szeibernaeticks.capability.ISzeibernaetickCapability;
import main.de.grzb.szeibernaeticks.szeibernaeticks.capability.MetalBonesCapability;
import main.de.grzb.szeibernaeticks.szeibernaeticks.capability.armoury.IArmouryCapability;
import main.de.grzb.szeibernaeticks.szeibernaeticks.capability.armoury.ArmouryProvider;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

/**
 * Implements the functionality of the ItemMetalBones ISzeibernaetick via Event
 * Subscription.
 *
 * @author DemRat
 */
public class MetalBonesHandler implements ISzeibernaetickEventHandler {

    private Class<? extends ISzeibernaetickCapability> szeiberClass = MetalBonesCapability.class;

    @SubscribeEvent
    public void onEntityHurt(LivingHurtEvent e) {
        IArmouryCapability capability = e.getEntity().getCapability(ArmouryProvider.ARMOURY_CAP, null);

        if(capability != null && capability.getSzeibernaetick(this.szeiberClass) != null) {
            Log.log("Bones attempting to negate Damage!", LogType.DEBUG, LogType.SZEIBER_HANDLER);
            Log.log("Amount: " + e.getAmount(), LogType.DEBUG, LogType.SZEIBER_HANDLER, LogType.SPECIFIC);
            Log.log("Also, source: " + e.getSource().damageType, LogType.DEBUG, LogType.SZEIBER_HANDLER, LogType.SPECIFIC);
            if(!e.getSource().isUnblockable()) {
                e.setAmount(e.getAmount() / 2);
            }
        }
    }
}
