package main.de.grzb.szeibernaeticks.szeibernaeticks.event;

import main.de.grzb.szeibernaeticks.szeibernaeticks.capability.ISzeibernaetickCapability;
import main.de.grzb.szeibernaeticks.szeibernaeticks.capability.armoury.ISzeibernaetickArmouryCapability;
import net.minecraftforge.fml.common.eventhandler.Event;

public class SzeibernaetickInstalledEvent extends Event {

    public final ISzeibernaetickArmouryCapability szeiberArmoury;
    public final ISzeibernaetickCapability installedSzeibernaetick;

    public SzeibernaetickInstalledEvent(ISzeibernaetickArmouryCapability armoury,
            ISzeibernaetickCapability installedSzeiber) {
        szeiberArmoury = armoury;
        installedSzeibernaetick = installedSzeiber;
    }

}
