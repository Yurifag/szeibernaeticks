package main.de.grzb.szeibernaeticks.item.szeibernaetick;

import main.de.grzb.szeibernaeticks.szeibernaeticks.capability.SzeibernaetickConductiveVeinsCapability;
import main.de.grzb.szeibernaeticks.szeibernaeticks.event.SzeibernaetickConductiveVeinsHandler;
import net.minecraft.creativetab.CreativeTabs;

public class ItemConductiveVeins extends SzeibernaetickBase {

    public ItemConductiveVeins(String name) {
        super(name, SzeibernaetickConductiveVeinsCapability.class, SzeibernaetickConductiveVeinsHandler.class);
        setCreativeTab(CreativeTabs.COMBAT);
    }

}
