package main.de.grzb.szeibernaeticks.szeibernaeticks.capability;

import main.de.grzb.szeibernaeticks.szeibernaeticks.BodyPart;
import main.de.grzb.szeibernaeticks.szeibernaeticks.energy.EnergyPriority;
import main.de.grzb.szeibernaeticks.szeibernaeticks.energy.IEnergyConsumer;
import main.de.grzb.szeibernaeticks.szeibernaeticks.energy.IEnergyProducer;
import net.minecraft.nbt.NBTTagCompound;

public class SzeibernaetickDynamoJointsCapability
        implements ISzeibernaetickCapability, IEnergyConsumer, IEnergyProducer {

    private int maxStorage;
    private int storage;
    private float fractionalStorage;

    @Override
    public String getIdentifier() {
        return "dynamoJoints";
    }

    @Override
    public NBTTagCompound toNBT() {
        NBTTagCompound tag = new NBTTagCompound();
        tag.setInteger("maxStorage", maxStorage);
        tag.setInteger("storage", storage);
        tag.setFloat("fractionalStorage", fractionalStorage);
        return tag;
    }

    @Override
    public void fromNBT(NBTTagCompound nbt) {
        maxStorage = nbt.getInteger("maxStorage");
        storage = nbt.getInteger("storage");
        fractionalStorage = nbt.getFloat("fractionalStorage");

        if(maxStorage == 0) {
            maxStorage = 100;
        }

        return;
    }

    @Override
    public BodyPart getBodyPart() {
        return BodyPart.JOINTS;
    }

    // IEnergyConsumer Implementation

    @Override
    public EnergyPriority currentConsumptionPrio() {
        return EnergyPriority.NEEDED_SPACE;
    }

    @Override
    public boolean canStillConsume() {
        return storage < maxStorage;
    }

    @Override
    public int consume() {
        if(canStillConsume()) {
            storage++;
            return 1;
        }
        return 0;
    }

    // IEnergyProducer Implementation

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

    /**
     * Produces energy based on the given fall height.
     *
     * @param height
     *            How far this fell.
     * @return The amount of energy produced.
     */
    public int produce(float height) {
        fractionalStorage += height / 4;
        int production = 0;
        if(fractionalStorage > 0) {
            production = (int) fractionalStorage;
            fractionalStorage = fractionalStorage - production;
        }
        return production;
    }

}
