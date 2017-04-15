package main.de.grzb.szeibernaeticks.szeibernaeticks.capability.armoury;

import net.minecraft.nbt.NBTBase;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;

/**
 * Does Stuff related to Capabilities. TODO: What exactly, again?
 *
 * @author DemRat
 *
 */
public class SzeibernaetickArmouryProvider implements ICapabilitySerializable<NBTBase> {

    @CapabilityInject(ISzeibernaetickArmouryCapability.class)
    public static final Capability<ISzeibernaetickArmouryCapability> ARMOURY_CAP = null;

    private ISzeibernaetickArmouryCapability instance = ARMOURY_CAP.getDefaultInstance();

    @Override
    public boolean hasCapability(Capability<?> capability, EnumFacing facing) {
        return capability == ARMOURY_CAP;
    }

    @Override
    public <T> T getCapability(Capability<T> capability, EnumFacing facing) {
        return capability == ARMOURY_CAP ? ARMOURY_CAP.<T>cast(this.instance) : null;
    }

    @Override
    public NBTBase serializeNBT() {
        return ARMOURY_CAP.getStorage().writeNBT(ARMOURY_CAP, instance, null);
    }

    @Override
    public void deserializeNBT(NBTBase nbt) {
        ARMOURY_CAP.getStorage().readNBT(ARMOURY_CAP, instance, null, nbt);
    }

}
