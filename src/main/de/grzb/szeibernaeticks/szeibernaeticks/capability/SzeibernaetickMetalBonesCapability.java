package main.de.grzb.szeibernaeticks.szeibernaeticks.capability;

import main.de.grzb.szeibernaeticks.item.szeibernaetick.ItemMetalBones;
import main.de.grzb.szeibernaeticks.szeibernaeticks.BodyPart;
import net.minecraft.nbt.NBTTagCompound;

/**
 * The Capability corresponding to {@link ItemMetalBones}.
 *
 * @author DemRat
 *
 */
public class SzeibernaetickMetalBonesCapability implements ISzeibernaetickCapability {
    
    private int damage;
    
    @Override
    public String getIdentifier() {
        return "MetalBonesCapability";
    }
    
    public SzeibernaetickMetalBonesCapability() {
        damage = 0;
    }
    
    @Override
    public NBTTagCompound toNBT() {
        NBTTagCompound base = new NBTTagCompound();
        base.setInteger("damage", damage);
        return base;
    }
    
    @Override
    public void fromNBT(NBTTagCompound nbt) {
        damage = nbt.getInteger("damage");
    }
    
    @Override
    public BodyPart getBodyPart() {
        return BodyPart.bones;
    }
}
