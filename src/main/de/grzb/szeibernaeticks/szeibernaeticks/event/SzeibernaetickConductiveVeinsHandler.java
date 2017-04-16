package main.de.grzb.szeibernaeticks.szeibernaeticks.event;

import main.de.grzb.szeibernaeticks.control.Log;
import main.de.grzb.szeibernaeticks.control.LogType;
import main.de.grzb.szeibernaeticks.szeibernaeticks.capability.ISzeibernaetickCapability;
import main.de.grzb.szeibernaeticks.szeibernaeticks.capability.SzeibernaetickConductiveVeinsCapability;
import main.de.grzb.szeibernaeticks.szeibernaeticks.capability.armoury.ISzeibernaetickArmouryCapability;
import main.de.grzb.szeibernaeticks.szeibernaeticks.capability.armoury.SzeibernaetickArmouryProvider;
import main.de.grzb.szeibernaeticks.szeibernaeticks.energy.EnergyConsumptionEvent;
import main.de.grzb.szeibernaeticks.szeibernaeticks.energy.EnergyProductionEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class SzeibernaetickConductiveVeinsHandler implements ISzeibernaetickEventHandler {

    @SubscribeEvent
    public void onSzeibernaetickInstalled(SzeibernaetickInstalledEvent e) {
        Log.log("SzeiberVeins checking Installation!", LogType.DEBUG, LogType.SZEIBER_ENERGY, LogType.SZEIBER_HANDLER);

        // If these veins were just installed
        if(e.installedSzeibernaetick instanceof SzeibernaetickConductiveVeinsCapability) {
            SzeibernaetickConductiveVeinsCapability veins = (SzeibernaetickConductiveVeinsCapability) e.installedSzeibernaetick;
            // Register all other Szeibernaeticks to these veins.
            for(ISzeibernaetickCapability szeiber : e.szeiberArmoury.getSzeibernaeticks()) {
                veins.register(szeiber);
            }
        }
        else {
            // Otherwise, check if these veins are installed right now.
            if(e.szeiberArmoury.getSzeibernaetick(SzeibernaetickConductiveVeinsCapability.class) != null) {
                // If they are, register the newly installed Szeibernaetick.
                ((SzeibernaetickConductiveVeinsCapability) e.szeiberArmoury
                        .getSzeibernaetick(SzeibernaetickConductiveVeinsCapability.class))
                                .register(e.installedSzeibernaetick);
            }
        }
    }

    @SubscribeEvent
    public void onEnergyConsumptionEvent(EnergyConsumptionEvent e) {
        ISzeibernaetickArmouryCapability armoury = e.getEntity()
                .getCapability(SzeibernaetickArmouryProvider.ARMOURY_CAP, null);
        SzeibernaetickConductiveVeinsCapability veins = (SzeibernaetickConductiveVeinsCapability) armoury
                .getSzeibernaetick(SzeibernaetickConductiveVeinsCapability.class);
        if(veins != null) {
            veins.handleConsumptionEvent(e);
        }
    }

    @SubscribeEvent
    public void onEnergyProductionEvent(EnergyProductionEvent e) {
        ISzeibernaetickArmouryCapability armoury = e.getEntity()
                .getCapability(SzeibernaetickArmouryProvider.ARMOURY_CAP, null);
        SzeibernaetickConductiveVeinsCapability veins = (SzeibernaetickConductiveVeinsCapability) armoury
                .getSzeibernaetick(SzeibernaetickConductiveVeinsCapability.class);
        if(veins != null) {
            veins.handleProductionEvent(e);
        }

    }
}
