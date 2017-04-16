package main.de.grzb.szeibernaeticks.szeibernaeticks.capability.armoury;

import java.util.Set;

import main.de.grzb.szeibernaeticks.control.Log;
import main.de.grzb.szeibernaeticks.control.LogType;
import main.de.grzb.szeibernaeticks.szeibernaeticks.SzeibernaetickMapper;
import main.de.grzb.szeibernaeticks.szeibernaeticks.capability.ISzeibernaetickCapability;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.Capability.IStorage;

/**
 * Responsible for converting ISzeibernaetickStorageCapabilities to NBT and
 * back, for saving and loading.
 *
 * @author DemRat
 *
 */
public class SzeibernaetickArmouryCapabilityStorage implements IStorage<ISzeibernaetickArmouryCapability> {

    @Override
    public void readNBT(Capability<ISzeibernaetickArmouryCapability> capability,
            ISzeibernaetickArmouryCapability instance, EnumFacing side, NBTBase nbt) {
        // Attempt to read the NBT as an Compound. If this fails, the Capability
        // wasn't saved correctly.
        NBTTagCompound tag;
        try {
            tag = (NBTTagCompound) nbt;
        }
        catch(ClassCastException e) {
            Log.log("Failed loading of ISzeibernaetickCapability. NBT is not a NBTTagCompound.", LogType.ERROR);
            Log.logThrowable(e);
            return;
        }

        // All Tags should be compound tags, as the ItemStacks should have been
        // serialized into those. So we can simply generate the ItemStacks from
        // the NBTTags
        Set<String> keys = tag.getKeySet();
        for(String s : keys) {
            NBTTagCompound compound = tag.getCompoundTag(s);
            if(compound != null) {
                ISzeibernaetickCapability cap;
                Class<? extends ISzeibernaetickCapability> capClass = SzeibernaetickMapper.instance
                        .getCapabilityFromIdentifier(s);
                try {
                    cap = capClass.newInstance();
                    cap.fromNBT(compound);
                    instance.addSzeibernaetick(cap);
                }
                catch(InstantiationException e) {
                    Log.log("Could not instantiate ISzeibernaetickCapability of class: " + capClass.toString(),
                            LogType.ERROR);
                    Log.logThrowable(e);
                    e.printStackTrace();
                }
                catch(IllegalAccessException e) {
                    Log.log("Could not access the constructor of the class " + capClass.toString(), LogType.ERROR);
                    Log.logThrowable(e);
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public NBTBase writeNBT(Capability<ISzeibernaetickArmouryCapability> capability,
            ISzeibernaetickArmouryCapability instance, EnumFacing side) {
        // ItemStacks have NBT-representations, so we simply get those and write
        // them to the NBT.
        NBTTagCompound tag = new NBTTagCompound();
        for(ISzeibernaetickCapability szeiber : instance.getSzeibernaeticks()) {
            tag.setTag(szeiber.getIdentifier(), szeiber.toNBT());
        }

        return tag;
    }

}
