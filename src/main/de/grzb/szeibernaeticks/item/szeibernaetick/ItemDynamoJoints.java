package main.de.grzb.szeibernaeticks.item.szeibernaetick;

import main.de.grzb.szeibernaeticks.szeibernaeticks.capability.DynamoJointsCapability;
import main.de.grzb.szeibernaeticks.szeibernaeticks.event.DynamoJointsHandler;
import net.minecraft.creativetab.CreativeTabs;

public class ItemDynamoJoints extends SzeibernaetickBase {

    public ItemDynamoJoints(String name) {
        super(name, DynamoJointsCapability.class, DynamoJointsHandler.class);
        this.setCreativeTab(CreativeTabs.COMBAT);
    }

}
