package main.de.grzb.szeibernaeticks.szeibernaeticks.event;

import main.de.grzb.szeibernaeticks.control.Log;
import main.de.grzb.szeibernaeticks.control.LogType;
import main.de.grzb.szeibernaeticks.szeibernaeticks.capability.SzeibernaetickDynamoJointsCapability;
import main.de.grzb.szeibernaeticks.szeibernaeticks.capability.armoury.ISzeibernaetickArmouryCapability;
import main.de.grzb.szeibernaeticks.szeibernaeticks.capability.armoury.SzeibernaetickArmouryProvider;
import main.de.grzb.szeibernaeticks.szeibernaeticks.energy.EnergyProductionEvent;
import main.de.grzb.szeibernaeticks.szeibernaeticks.energy.feedback.EnergyFeedbackDamage;
import net.minecraftforge.common.MinecraftForge;
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
                int energyProduced = dynamo.produce(height);
                Log.log("Producing energy: " + energyProduced, LogType.DEBUG, LogType.SZEIBER_ENERGY, LogType.SPAMMY);
                e.setDistance(height / 2);

                EnergyProductionEvent production = new EnergyProductionEvent(e.getEntity(), energyProduced);
                MinecraftForge.EVENT_BUS.post(production);

                if(production.getRemainingAmount() > 0) {
                    e.getEntityLiving().attackEntityFrom(new EnergyFeedbackDamage(dynamo),
                            production.getRemainingAmount());
                }
            }
        }
    }

}
