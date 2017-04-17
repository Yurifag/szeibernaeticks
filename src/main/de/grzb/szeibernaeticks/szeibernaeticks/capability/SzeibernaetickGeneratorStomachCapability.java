package main.de.grzb.szeibernaeticks.szeibernaeticks.capability;

import main.de.grzb.szeibernaeticks.control.Log;
import main.de.grzb.szeibernaeticks.control.LogType;
import main.de.grzb.szeibernaeticks.szeibernaeticks.BodyPart;
import main.de.grzb.szeibernaeticks.szeibernaeticks.energy.EnergyPriority;
import main.de.grzb.szeibernaeticks.szeibernaeticks.energy.EnergyProductionEvent;
import main.de.grzb.szeibernaeticks.szeibernaeticks.energy.IEnergyConsumer;
import main.de.grzb.szeibernaeticks.szeibernaeticks.energy.IEnergyProducer;
import main.de.grzb.szeibernaeticks.szeibernaeticks.energy.feedback.EnergyFeedbackDamage;
import net.minecraft.entity.Entity;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.MinecraftForge;

public class SzeibernaetickGeneratorStomachCapability
        implements ISzeibernaetickCapability, IEnergyProducer, IEnergyConsumer {

    private int storage;
    private int maxStorage;

    @Override
    public String getIdentifier() {
        return "GeneratorStomachCapability";
    }

    @Override
    public NBTTagCompound toNBT() {
        NBTTagCompound compound = new NBTTagCompound();
        return compound;
    }

    @Override
    public void fromNBT(NBTTagCompound nbt) {
        return;
    }

    @Override
    public BodyPart getBodyPart() {
        return BodyPart.STOMACH;
    }

    // IEnergyConsumer Implementation

    @Override
    public EnergyPriority currentConsumptionPrio() {
        return EnergyPriority.EMPTY_ASAP;
    }

    @Override
    public boolean canStillConsume() {
        return storage < maxStorage;
    }

    @Override
    public int consume() {
        Log.log("[GenStomach] GenStomach attempting to consume energy!", LogType.DEBUG, LogType.SZEIBER_ENERGY,
                LogType.SZEIBER_CAP, LogType.SPAMMY);
        if(canStillConsume()) {
            storage++;
            Log.log("[GenStomach] GenStomach consuming energy! Now storing: " + storage, LogType.DEBUG,
                    LogType.SZEIBER_ENERGY, LogType.SZEIBER_CAP, LogType.SPAMMY);
            return 1;
        }
        return 0;
    }

    // IEnergyProducer Implementation

    @Override
    public EnergyPriority currentProductionPrio() {
        return EnergyPriority.EMPTY_ASAP;
    }

    @Override
    public boolean canStillProduce() {
        return storage > 0;
    }

    @Override
    public int produceAdHoc() {
        Log.log("[GenStomach] GenStomach attempting to produce energy!", LogType.DEBUG, LogType.SZEIBER_ENERGY,
                LogType.SZEIBER_CAP, LogType.SPAMMY);
        if(canStillProduce()) {
            storage--;
            Log.log("[GenStomach] GenStomach produced energy! Remaining storage: " + storage, LogType.DEBUG,
                    LogType.SZEIBER_ENERGY, LogType.SZEIBER_CAP, LogType.SPAMMY);
            return 1;
        }
        return 0;
    }

    public int produce(int energyProduced, Entity entity) {
        Log.log("[GenStomach] Producing energy: " + energyProduced, LogType.DEBUG, LogType.SZEIBER_ENERGY,
                LogType.SZEIBER_CAP);
        EnergyProductionEvent production = new EnergyProductionEvent(entity, energyProduced);
        MinecraftForge.EVENT_BUS.post(production);

        if(production.getRemainingAmount() > 0) {
            entity.attackEntityFrom(new EnergyFeedbackDamage(this), production.getRemainingAmount());
        }
        return energyProduced;
    }

}
