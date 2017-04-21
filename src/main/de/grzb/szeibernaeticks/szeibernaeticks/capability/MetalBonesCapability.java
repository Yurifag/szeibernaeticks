package main.de.grzb.szeibernaeticks.szeibernaeticks.capability;

import main.de.grzb.szeibernaeticks.item.szeibernaetick.ItemMetalBones;
import main.de.grzb.szeibernaeticks.szeibernaeticks.BodyPart;
import net.minecraft.nbt.NBTTagCompound;

/**
 * The Capability corresponding to {@link ItemMetalBones}.
 *
 * @author DemRat
 */
public class MetalBonesCapability implements ISzeibernaetickCapability {

    private int damage;

    @Override
    public String getIdentifier() {
        return "MetalBonesCapability";
    }

    public MetalBonesCapability() {
        this.damage = 0;
    }

    @Override
    public NBTTagCompound toNBT() {
        NBTTagCompound base = new NBTTagCompound();
        base.setInteger("damage", this.damage);
        return base;
    }

    @Override
    public void fromNBT(NBTTagCompound nbt) {
        this.damage = nbt.getInteger("damage");
    }

    @Override
    public BodyPart getBodyPart() {
        return BodyPart.BONES;
    }
}
