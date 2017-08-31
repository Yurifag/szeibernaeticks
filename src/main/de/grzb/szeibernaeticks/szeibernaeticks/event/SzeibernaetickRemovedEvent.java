package main.de.grzb.szeibernaeticks.szeibernaeticks.event;

import main.de.grzb.szeibernaeticks.control.Log;
import main.de.grzb.szeibernaeticks.control.LogType;
import main.de.grzb.szeibernaeticks.szeibernaeticks.capability.ISzeibernaetickCapability;
import main.de.grzb.szeibernaeticks.szeibernaeticks.capability.armoury.IArmouryCapability;
import net.minecraftforge.fml.common.eventhandler.Event;

public class SzeibernaetickRemovedEvent extends Event {

    public final IArmouryCapability armoury;
    public final ISzeibernaetickCapability removedSzeibernaetick;

    public SzeibernaetickRemovedEvent(IArmouryCapability armoury, ISzeibernaetickCapability removedSzeibernaetick) {
        this.armoury = armoury;
        this.removedSzeibernaetick = removedSzeibernaetick;

        Log.log("Removed Szeibernaetick: " + removedSzeibernaetick.getIdentifier(), LogType.INFO);
    }

}
