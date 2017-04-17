package main.de.grzb.szeibernaeticks.item.szeibernaetick;

import main.de.grzb.szeibernaeticks.szeibernaeticks.capability.SzeibernaetickMetalBonesCapability;
import main.de.grzb.szeibernaeticks.szeibernaeticks.event.SzeibernaetickMetalBonesHandler;
import net.minecraft.creativetab.CreativeTabs;

public class ItemMetalBones extends SzeibernaetickBase {

    public ItemMetalBones(String name) {
        super(name, SzeibernaetickMetalBonesCapability.class, SzeibernaetickMetalBonesHandler.class);
        setCreativeTab(CreativeTabs.COMBAT);
    }
}
