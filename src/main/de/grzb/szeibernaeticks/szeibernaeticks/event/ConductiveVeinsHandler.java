package main.de.grzb.szeibernaeticks.szeibernaeticks.event;

import main.de.grzb.szeibernaeticks.control.Log;
import main.de.grzb.szeibernaeticks.control.LogType;
import main.de.grzb.szeibernaeticks.szeibernaeticks.capability.ConductiveVeinsCapability;
import main.de.grzb.szeibernaeticks.szeibernaeticks.capability.ISzeibernaetickCapability;
import main.de.grzb.szeibernaeticks.szeibernaeticks.capability.armoury.ArmouryProvider;
import main.de.grzb.szeibernaeticks.szeibernaeticks.capability.armoury.IArmouryCapability;
import main.de.grzb.szeibernaeticks.szeibernaeticks.energy.EnergyConsumptionEvent;
import main.de.grzb.szeibernaeticks.szeibernaeticks.energy.EnergyProductionEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class ConductiveVeinsHandler implements ISzeibernaetickEventHandler {

    @SubscribeEvent
    public void onSzeibernaetickInstalled(SzeibernaetickInstalledEvent e) {
        Log.log("[ConVeinsHandler] SzeiberVeins checking Installation!", LogType.DEBUG, LogType.SZEIBER_ENERGY,
                LogType.SZEIBER_HANDLER);

        // If these veins were just installed
        if(e.installedSzeibernaetick instanceof ConductiveVeinsCapability) {
            Log.log("[ConVeinsHandler] SzeiberVeins were installed.", LogType.DEBUG, LogType.SZEIBER_ENERGY,
                    LogType.SZEIBER_HANDLER);
            ConductiveVeinsCapability veins = (ConductiveVeinsCapability) e.installedSzeibernaetick;
            // Register all other Szeibernaeticks to these veins.
            for(ISzeibernaetickCapability szeiber : e.armoury.getSzeibernaeticks()) {
                veins.register(szeiber);
            }
        }
        else {
            // Otherwise, check if these veins are installed right now.
            if(e.armoury.getSzeibernaetick(ConductiveVeinsCapability.class) != null) {
                Log.log("[ConVeinsHandler] Something else was installed and SzeiberVeins exist.", LogType.DEBUG,
                        LogType.SZEIBER_ENERGY, LogType.SZEIBER_HANDLER);
                // If they are, register the newly installed Szeibernaetick.
                ((ConductiveVeinsCapability) e.armoury.getSzeibernaetick(ConductiveVeinsCapability.class))
                        .register(e.installedSzeibernaetick);
            }
        }
    }

    @SubscribeEvent
    public void onSzeibernaetickRemoved(SzeibernaetickRemovedEvent e) {
        Log.log("[ConVeinsHandler] SzeiberVeins checking Removal!", LogType.DEBUG, LogType.SZEIBER_ENERGY,
                LogType.SZEIBER_HANDLER);
        if(e.removedSzeibernaetick instanceof ConductiveVeinsCapability) {
            Log.log("[ConVeinsHandler] SzeiberVeins were removed.", LogType.DEBUG, LogType.SZEIBER_ENERGY,
                    LogType.SZEIBER_HANDLER);
            ConductiveVeinsCapability veins = (ConductiveVeinsCapability) e.removedSzeibernaetick;
            // Register all other Szeibernaeticks to these veins.
            for(ISzeibernaetickCapability szeiber : e.armoury.getSzeibernaeticks()) {
                veins.unregister(szeiber);
            }
        }
        else {
            // Otherwise, check if these veins are installed right now.
            if(e.armoury.getSzeibernaetick(ConductiveVeinsCapability.class) != null) {
                Log.log("[ConVeinsHandler] Something else was removed and SzeiberVeins exist.", LogType.DEBUG,
                        LogType.SZEIBER_ENERGY, LogType.SZEIBER_HANDLER);
                // If they are, register the newly installed Szeibernaetick.
                ((ConductiveVeinsCapability) e.armoury.getSzeibernaetick(ConductiveVeinsCapability.class))
                        .unregister(e.removedSzeibernaetick);
            }
        }
    }

    @SubscribeEvent
    public void onEnergyConsumptionEvent(EnergyConsumptionEvent e) {
        Log.log("[ConVeinsHandler] An EnergyConsumptionEvent happened", LogType.DEBUG, LogType.SZEIBER_ENERGY,
                LogType.SZEIBER_HANDLER, LogType.SPAMMY);
        IArmouryCapability armoury = e.getEntity().getCapability(ArmouryProvider.ARMOURY_CAP, null);
        ConductiveVeinsCapability veins = (ConductiveVeinsCapability) armoury
                .getSzeibernaetick(ConductiveVeinsCapability.class);
        if(veins != null) {
            Log.log("[ConVeinsHandler] An EnergyConsumptionEvent is being handled.", LogType.DEBUG,
                    LogType.SZEIBER_ENERGY, LogType.SZEIBER_HANDLER, LogType.SPAMMY);
            veins.handleConsumptionEvent(e);
        }
    }

    @SubscribeEvent
    public void onEnergyProductionEvent(EnergyProductionEvent e) {
        Log.log("[ConVeinsHandler] An EnergyProductionEvent happened", LogType.DEBUG, LogType.SZEIBER_ENERGY,
                LogType.SZEIBER_HANDLER, LogType.SPAMMY);
        IArmouryCapability armoury = e.getEntity().getCapability(ArmouryProvider.ARMOURY_CAP, null);
        ConductiveVeinsCapability veins = (ConductiveVeinsCapability) armoury
                .getSzeibernaetick(ConductiveVeinsCapability.class);
        if(veins != null) {
            Log.log("[ConVeinsHandler] An EnergyProductionEvent is being handled.", LogType.DEBUG,
                    LogType.SZEIBER_ENERGY, LogType.SZEIBER_HANDLER, LogType.SPAMMY);
            veins.handleProductionEvent(e);
        }

    }
}
