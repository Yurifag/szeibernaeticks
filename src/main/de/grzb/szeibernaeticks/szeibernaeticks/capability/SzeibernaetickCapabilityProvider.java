package main.de.grzb.szeibernaeticks.szeibernaeticks.capability;

import main.de.grzb.szeibernaeticks.item.szeibernaetick.SzeibernaetickBase;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.ICapabilityProvider;

public class SzeibernaetickCapabilityProvider implements ICapabilityProvider {

    @CapabilityInject(ISzeibernaetickCapability.class)
    public static final Capability<ISzeibernaetickCapability> szeibernaetickCapability = null;

    private ISzeibernaetickCapability instance;

    public SzeibernaetickCapabilityProvider(SzeibernaetickBase item) {
        instance = item.getCapabilityInstance();
    }

    @Override
    public boolean hasCapability(Capability<?> capability, EnumFacing facing) {
        return capability == szeibernaetickCapability;
    }

    @Override
    public <T> T getCapability(Capability<T> capability, EnumFacing facing) {
        // Test whether the Capability<T> is the existing and injected
        // Capability<ISzeibernaetickCapability> of which there is only one
        if(capability == szeibernaetickCapability) {
            // If it is, return the instance
            return szeibernaetickCapability.<T>cast(instance);
        }
        return null;
    }

    public String getIdentifier() {
        return instance.getIdentifier();
    }

}
