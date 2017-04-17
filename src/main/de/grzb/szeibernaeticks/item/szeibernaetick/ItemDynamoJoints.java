package main.de.grzb.szeibernaeticks.item.szeibernaetick;

import main.de.grzb.szeibernaeticks.szeibernaeticks.capability.SzeibernaetickDynamoJointsCapability;
import main.de.grzb.szeibernaeticks.szeibernaeticks.event.SzeibernaetickDynamoJointsHandler;
import net.minecraft.creativetab.CreativeTabs;

public class ItemDynamoJoints extends SzeibernaetickBase {

    public ItemDynamoJoints(String name) {
        super(name, SzeibernaetickDynamoJointsCapability.class, SzeibernaetickDynamoJointsHandler.class);
        setCreativeTab(CreativeTabs.COMBAT);
    }

}
