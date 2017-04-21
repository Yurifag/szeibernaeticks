package main.de.grzb.szeibernaeticks.item.szeibernaetick;

import main.de.grzb.szeibernaeticks.szeibernaeticks.capability.MetalBonesCapability;
import main.de.grzb.szeibernaeticks.szeibernaeticks.event.MetalBonesHandler;
import net.minecraft.creativetab.CreativeTabs;

public class ItemMetalBones extends SzeibernaetickBase {

    public ItemMetalBones(String name) {
        super(name, MetalBonesCapability.class, MetalBonesHandler.class);
        this.setCreativeTab(CreativeTabs.COMBAT);
    }
}
