package main.de.grzb.szeibernaeticks.szeibernaeticks.capability;

import java.util.Set;

import main.de.grzb.szeibernaeticks.Szeibernaeticks;
import net.minecraft.item.ItemStack;
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
public class SzeibernaetickStorageCapabilityStorage implements IStorage<ISzeibernaetickStorageCapability> {

  @Override
  public void readNBT(Capability<ISzeibernaetickStorageCapability> capability, ISzeibernaetickStorageCapability instance, EnumFacing side, NBTBase nbt) {
    // Attempt to read the NBT as an Compound. If this fails, the Capability
    // wasn't saved correctly.
    NBTTagCompound tag;
    try {
      tag = (NBTTagCompound) nbt;
    }
    catch(ClassCastException e) {
      Szeibernaeticks.getLogger().error("Failed loading of ISzeibernaetickCapability. NBT is not a NBTTagCompound.");
      return;
    }

    // All Tags should be compound tags, as the ItemStacks should have been
    // serialized into those. So we can simply generate the ItemStacks from
    // the NBTTags
    Set<String> keys = tag.getKeySet();
    for(String s : keys) {
      NBTTagCompound compound = tag.getCompoundTag(s);
      if(compound != null) {
        instance.addSzeibernaetick(new ItemStack(compound));
      }
    }
  }

  @Override
  public NBTBase writeNBT(Capability<ISzeibernaetickStorageCapability> capability, ISzeibernaetickStorageCapability instance, EnumFacing side) {
    // ItemStacks have NBT-representations, so we simply get those and write
    // them to the NBT.
    NBTTagCompound tag = new NBTTagCompound();
    for(ItemStack szeiber : instance.getSzeibernaeticks()) {
      tag.setTag(szeiber.getUnlocalizedName(), szeiber.serializeNBT());
    }

    return tag;
  }

}
