package main.de.grzb.szeibernaeticks.szeibernaeticks.event;

import main.de.grzb.szeibernaeticks.control.Log;
import main.de.grzb.szeibernaeticks.control.LogType;
import main.de.grzb.szeibernaeticks.szeibernaeticks.capability.SzeibernaetickSyntheticEyesCapability;
import main.de.grzb.szeibernaeticks.szeibernaeticks.capability.armoury.ISzeibernaetickArmouryCapability;
import main.de.grzb.szeibernaeticks.szeibernaeticks.capability.armoury.SzeibernaetickArmouryProvider;
import net.minecraft.init.MobEffects;
import net.minecraft.potion.PotionEffect;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class SzeibernaetickSynteticEyesHandler implements ISzeibernaetickEventHandler {

    {
        Log.log("Creating instance of " + this.getClass(), LogType.DEBUG, LogType.INSTANTIATION,
                LogType.SZEIBER_HANDLER);
    }

    @SubscribeEvent
    public void onLivingUpdateEvent(LivingUpdateEvent e) {
        ISzeibernaetickArmouryCapability armoury = e.getEntity()
                .getCapability(SzeibernaetickArmouryProvider.ARMOURY_CAP, null);

        if(armoury != null) {
            SzeibernaetickSyntheticEyesCapability eyes = (SzeibernaetickSyntheticEyesCapability) armoury
                    .getSzeibernaetick(SzeibernaetickSyntheticEyesCapability.class);

            if(eyes != null) {
                if(!e.getEntityLiving().isPotionActive(MobEffects.NIGHT_VISION)) {
                    if(eyes.grantVision(e.getEntity())) {
                        e.getEntityLiving()
                                .addPotionEffect(new PotionEffect(MobEffects.NIGHT_VISION, 30, 1, false, false));
                    }
                }
            }
        }
    }

}
