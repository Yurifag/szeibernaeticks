package main.de.grzb.szeibernaeticks.szeibernaeticks.event;

import main.de.grzb.szeibernaeticks.control.Log;
import main.de.grzb.szeibernaeticks.control.LogType;
import main.de.grzb.szeibernaeticks.szeibernaeticks.capability.ISzeibernaetickCapability;
import main.de.grzb.szeibernaeticks.szeibernaeticks.capability.armoury.IArmouryCapability;
import net.minecraftforge.fml.common.eventhandler.Event;

public class SzeibernaetickInstalledEvent extends Event {

    public final IArmouryCapability armoury;
    public final ISzeibernaetickCapability installedSzeibernaetick;

    public SzeibernaetickInstalledEvent(IArmouryCapability armoury, ISzeibernaetickCapability installedSzeibernaetick) {
        this.armoury = armoury;
        this.installedSzeibernaetick = installedSzeibernaetick;

        Log.log("Installed Szeibernaetick: " + installedSzeibernaetick.getIdentifier(), LogType.INFO);
    }

}
