package main.de.grzb.szeibernaeticks.szeibernaeticks.capability;

import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.ICapabilityProvider;

public class SzeibernaetickCapabilityProvider implements ICapabilityProvider {

    @CapabilityInject(ISzeibernaetickCapability.class)
    public static final Capability<ISzeibernaetickCapability> SZEIBER_CAP = null;

    private ISzeibernaetickCapability instance;

    public SzeibernaetickCapabilityProvider(ISzeibernaetickCapability iSzeibernaetickCapability) {
        instance = iSzeibernaetickCapability;
    }

    @Override
    public boolean hasCapability(Capability<?> capability, EnumFacing facing) {
        return capability == SZEIBER_CAP;
    }

    @Override
    public <T> T getCapability(Capability<T> capability, EnumFacing facing) {
        // Test whether the Capability<T> is the existing and injected
        // Capability<ISzeibernaetickCapability> of which there is only one
        if(capability == SZEIBER_CAP) {
            // If it is, return the instance
            return SZEIBER_CAP.<T>cast(instance);
        }
        return null;
    }

    public String getIdentifier() {
        return instance.getIdentifier();
    }

}
