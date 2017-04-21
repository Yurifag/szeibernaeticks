package main.de.grzb.szeibernaeticks.szeibernaeticks.event;

import main.de.grzb.szeibernaeticks.control.Log;
import main.de.grzb.szeibernaeticks.control.LogType;
import main.de.grzb.szeibernaeticks.szeibernaeticks.capability.DynamoJointsCapability;
import main.de.grzb.szeibernaeticks.szeibernaeticks.capability.armoury.ArmouryProvider;
import main.de.grzb.szeibernaeticks.szeibernaeticks.capability.armoury.IArmouryCapability;
import net.minecraftforge.event.entity.living.LivingFallEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class DynamoJointsHandler implements ISzeibernaetickEventHandler {

    @SubscribeEvent
    public void onEntityFall(LivingFallEvent e) {
        IArmouryCapability armoury = e.getEntity().getCapability(ArmouryProvider.ARMOURY_CAP, null);
        if(armoury != null) {
            DynamoJointsCapability dynamo = (DynamoJointsCapability) armoury.getSzeibernaetick(DynamoJointsCapability.class);
            if(dynamo != null) {
                Log.log("Entity with dynamo Fell! Dynamo Joints ENGAGE!", LogType.DEBUG, LogType.SZEIBER_HANDLER, LogType.SPAMMY);

                float height = e.getDistance();
                dynamo.produce(height, e.getEntity());
                e.setDistance(height / 2);
            }
        }
    }

}
