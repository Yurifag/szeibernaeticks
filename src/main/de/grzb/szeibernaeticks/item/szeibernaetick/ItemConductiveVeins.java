package main.de.grzb.szeibernaeticks.item.szeibernaetick;

import main.de.grzb.szeibernaeticks.szeibernaeticks.SzeibernaetickMapper;
import main.de.grzb.szeibernaeticks.szeibernaeticks.capability.ISzeibernaetickCapability;
import main.de.grzb.szeibernaeticks.szeibernaeticks.capability.SzeibernaetickCapabilityProvider;
import main.de.grzb.szeibernaeticks.szeibernaeticks.capability.SzeibernaetickConductiveVeinsCapability;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.capabilities.ICapabilityProvider;

public class ItemConductiveVeins extends SzeibernaetickBase {

    public ItemConductiveVeins(String name) {
        super(name);
        setCreativeTab(CreativeTabs.COMBAT);
        SzeibernaetickMapper.instance.register(SzeibernaetickConductiveVeinsCapability.class, this);
    }

    @Override
    public ISzeibernaetickCapability getCapabilityInstance() {
        return new SzeibernaetickConductiveVeinsCapability();
    }

    @Override
    public ICapabilityProvider initCapabilities(ItemStack stack, NBTTagCompound nbt) {
        return new SzeibernaetickCapabilityProvider(getCapabilityInstance());
    }

}
