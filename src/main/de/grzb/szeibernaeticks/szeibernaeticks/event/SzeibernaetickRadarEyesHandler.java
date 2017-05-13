package main.de.grzb.szeibernaeticks.szeibernaeticks.event;

import main.de.grzb.szeibernaeticks.control.Log;
import main.de.grzb.szeibernaeticks.control.LogType;
import main.de.grzb.szeibernaeticks.szeibernaeticks.capability.SzeibernaetickRadarEyesCapability;
import main.de.grzb.szeibernaeticks.szeibernaeticks.capability.armoury.ArmouryProvider;
import main.de.grzb.szeibernaeticks.szeibernaeticks.capability.armoury.IArmouryCapability;
import net.minecraft.entity.EntityLivingBase;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class SzeibernaetickRadarEyesHandler implements ISzeibernaetickEventHandler {
    @SubscribeEvent
    public void onLivingUpdate(LivingUpdateEvent e) {
        Log.log("[RadEyesHandler] Recieving event!", LogType.DEBUG, LogType.SPAMMY, LogType.SZEIBER_HANDLER);

        EntityLivingBase updated = e.getEntityLiving();
        IArmouryCapability armoury = updated.getCapability(ArmouryProvider.ARMOURY_CAP, null);

        if(armoury != null) {
            Log.log("[RadEyesHandler] Found Armory!", LogType.DEBUG, LogType.SPAMMY, LogType.SZEIBER_HANDLER);
            SzeibernaetickRadarEyesCapability eyes = (SzeibernaetickRadarEyesCapability) armoury
                    .getSzeibernaetick(SzeibernaetickRadarEyesCapability.class);

            if(eyes != null) {
                Log.log("[RadEyesHandler] Found Eyes!", LogType.DEBUG, LogType.SPAMMY, LogType.SZEIBER_HANDLER);
                eyes.grantVision(e);
            }
        }
    }
}
