package main.de.grzb.szeibernaeticks.szeibernaeticks.event;

import main.de.grzb.szeibernaeticks.control.Log;
import main.de.grzb.szeibernaeticks.control.LogType;
import main.de.grzb.szeibernaeticks.szeibernaeticks.capability.SzeibernaetickDynamoJointsCapability;
import main.de.grzb.szeibernaeticks.szeibernaeticks.capability.armoury.ISzeibernaetickArmouryCapability;
import main.de.grzb.szeibernaeticks.szeibernaeticks.capability.armoury.SzeibernaetickArmouryProvider;
import net.minecraftforge.event.entity.living.LivingFallEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class SzeibernaetickDynamoJointsHandler implements ISzeibernaetickEventHandler {

    @SubscribeEvent
    public void onEntityFall(LivingFallEvent e) {
        ISzeibernaetickArmouryCapability armoury = e.getEntity()
                .getCapability(SzeibernaetickArmouryProvider.ARMOURY_CAP, null);
        if(armoury != null) {
            SzeibernaetickDynamoJointsCapability dynamo = (SzeibernaetickDynamoJointsCapability) armoury
                    .getSzeibernaetick(SzeibernaetickDynamoJointsCapability.class);
            if(dynamo != null) {
                Log.log("Entity with dynamo Fell! Dynamo Joints ENGAGE!", LogType.DEBUG, LogType.SZEIBER_HANDLER,
                        LogType.SPAMMY);

                float height = e.getDistance();
                dynamo.produce(height, e.getEntity());
                e.setDistance(height / 2);
            }
        }
    }

}
