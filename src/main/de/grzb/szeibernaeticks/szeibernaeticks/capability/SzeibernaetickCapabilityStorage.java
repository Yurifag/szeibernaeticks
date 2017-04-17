package main.de.grzb.szeibernaeticks.szeibernaeticks.capability;

import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.Capability.IStorage;

/**
 * Class for storing SzeibernaetickCapabilities as NBT's and restoring them
 * again.
 *
 * @author DemRat
 *
 */
public class SzeibernaetickCapabilityStorage implements IStorage<ISzeibernaetickCapability> {

    @Override
    public NBTBase writeNBT(Capability<ISzeibernaetickCapability> capability, ISzeibernaetickCapability instance, EnumFacing side) {
        return instance.toNBT();
    }

    @Override
    public void readNBT(Capability<ISzeibernaetickCapability> capability, ISzeibernaetickCapability instance, EnumFacing side, NBTBase nbt) {
        try {
            instance.fromNBT((NBTTagCompound) nbt);
        }
        catch(ClassCastException e) {
            return;
        }
    }

}
