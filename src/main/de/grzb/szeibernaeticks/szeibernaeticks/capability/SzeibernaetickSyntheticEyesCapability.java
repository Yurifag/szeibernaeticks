package main.de.grzb.szeibernaeticks.szeibernaeticks.capability;

import main.de.grzb.szeibernaeticks.control.Log;
import main.de.grzb.szeibernaeticks.control.LogType;
import main.de.grzb.szeibernaeticks.szeibernaeticks.BodyPart;
import main.de.grzb.szeibernaeticks.szeibernaeticks.energy.EnergyConsumptionEvent;
import main.de.grzb.szeibernaeticks.szeibernaeticks.energy.EnergyPriority;
import main.de.grzb.szeibernaeticks.szeibernaeticks.energy.IEnergyProducer;
import net.minecraft.entity.Entity;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.MinecraftForge;

public class SzeibernaetickSyntheticEyesCapability implements ISzeibernaetickCapability, IEnergyProducer {

    private int maxStorage;
    private int storage = 0;
    private int cost = 5;

    {
        Log.log("Creating instance of " + this.getClass(), LogType.SZEIBER_CAP, LogType.DEBUG, LogType.INSTANTIATION);
    }

    @Override
    public String getIdentifier() {
        return "synthEyes";
    }

    @Override
    public NBTTagCompound toNBT() {
        NBTTagCompound tag = new NBTTagCompound();
        tag.setInteger("storage", storage);
        tag.setInteger("maxStorage", maxStorage);
        return tag;
    }

    @Override
    public void fromNBT(NBTTagCompound nbt) {
        storage = nbt.getInteger("storage");
        maxStorage = nbt.getInteger("maxStorage");
    }

    @Override
    public BodyPart getBodyPart() {
        return BodyPart.EYES;
    }

    public boolean grantVision(Entity target) {
        EnergyConsumptionEvent event = new EnergyConsumptionEvent(target, cost);
        MinecraftForge.EVENT_BUS.post(event);
        if(event.getRemainingAmount() > 0) {
            return false;
        }
        return true;
    }

    @Override
    public EnergyPriority currentProductionPrio() {
        return EnergyPriority.DESTRUCTION;
    }

    @Override
    public boolean canStillProduce() {
        return storage > 0;
    }

    @Override
    public int produceAdHoc() {
        if(canStillProduce()) {
            storage--;
            return 1;
        }
        return 0;
    }

}
