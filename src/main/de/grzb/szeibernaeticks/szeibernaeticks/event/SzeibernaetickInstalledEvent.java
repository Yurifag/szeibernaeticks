package main.de.grzb.szeibernaeticks.szeibernaeticks.event;

import main.de.grzb.szeibernaeticks.control.Log;
import main.de.grzb.szeibernaeticks.control.LogType;
import main.de.grzb.szeibernaeticks.szeibernaeticks.capability.ISzeibernaetickCapability;
import main.de.grzb.szeibernaeticks.szeibernaeticks.capability.armoury.ISzeibernaetickArmouryCapability;
import net.minecraftforge.fml.common.eventhandler.Event;

public class SzeibernaetickInstalledEvent extends Event {

    public final ISzeibernaetickArmouryCapability armoury;
    public final ISzeibernaetickCapability installedSzeibernaetick;

    public SzeibernaetickInstalledEvent(ISzeibernaetickArmouryCapability armoury, ISzeibernaetickCapability installedSzeibernaetick) {
        this.armoury = armoury;
        this.installedSzeibernaetick = installedSzeibernaetick;

        Log.log("Installed Szeibernaetick: " + installedSzeibernaetick.getIdentifier(), LogType.INFO);
    }

}
