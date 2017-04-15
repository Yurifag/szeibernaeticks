package main.de.grzb.szeibernaeticks.szeibernaeticks.capability;

import io.netty.util.internal.ConcurrentSet;
import main.de.grzb.szeibernaeticks.szeibernaeticks.BodyPart;
import main.de.grzb.szeibernaeticks.szeibernaeticks.energy.EnergyConsumptionEvent;
import main.de.grzb.szeibernaeticks.szeibernaeticks.energy.EnergyPriority;
import main.de.grzb.szeibernaeticks.szeibernaeticks.energy.EnergyProductionEvent;
import main.de.grzb.szeibernaeticks.szeibernaeticks.energy.IEnergyConsumer;
import main.de.grzb.szeibernaeticks.szeibernaeticks.energy.IEnergyProducer;
import net.minecraft.nbt.NBTTagCompound;

public class SzeibernaetickConductiveVeinsCapability implements ISzeibernaetickCapability {

    private ConcurrentSet<IEnergyProducer> producers = new ConcurrentSet<IEnergyProducer>();
    private ConcurrentSet<IEnergyConsumer> consumers = new ConcurrentSet<IEnergyConsumer>();

    public SzeibernaetickConductiveVeinsCapability() {
    }

    public void register(ISzeibernaetickCapability szeiber) {
        if(szeiber instanceof IEnergyProducer) {
            producers.add((IEnergyProducer) szeiber);
        }

        if(szeiber instanceof IEnergyConsumer) {
            consumers.add((IEnergyConsumer) szeiber);
        }
    }

    @Override
    public String getIdentifier() {
        return "VeinsCapability";
    }

    @Override
    public NBTTagCompound toNBT() {
        return new NBTTagCompound();
    }

    @Override
    public void fromNBT(NBTTagCompound nbt) {
        return;
    }

    @Override
    public BodyPart getBodyPart() {
        return BodyPart.VEINS;
    }

    /**
     * Handles the given EnergyConsumptionEvent
     *
     * @param e
     *            The given Event.
     */
    public void handleConsumptionEvent(EnergyConsumptionEvent e) {
        // Iterate over all Priorities, in order of severance
        outestLoop: for(EnergyPriority prio : EnergyPriority.values()) {
            boolean canStillProduce = true;
            // As long as at least one producer of the current prio can still
            // produce, repeat
            while(canStillProduce) {
                canStillProduce = false;
                // Ask each Producer to produce
                for(IEnergyProducer producer : producers) {
                    // But only if they belong to the current Priority
                    if(producer.currentPrio() == prio) {
                        e.cover(producer.produceAdHoc());
                        if(e.getRemainingAmount() == 0) {
                            break outestLoop;
                        }
                        // Remember whether there are still Producers able to
                        // produce
                        canStillProduce = canStillProduce || producer.canStillProduce();
                    }
                }
            }
        }
    }

    /**
     * Handles the given EnergyProductionEvent.
     *
     * @param e
     *            The given Event.
     */
    public void handleProductionEvent(EnergyProductionEvent e) {
        // Iterate over all Priorities, in order of severance
        outestLoop: for(EnergyPriority prio : EnergyPriority.values()) {
            boolean canStillProduce = true;
            // As long as at least one producer of the current prio can still
            // produce, repeat
            while(canStillProduce) {
                canStillProduce = false;
                // Ask each Producer to produce
                for(IEnergyConsumer consumer : consumers) {
                    // But only if they belong to the current Priority
                    if(consumer.currentPrio() == prio) {
                        e.consume(consumer.consume());
                        if(e.getRemainingAmount() == 0) {
                            break outestLoop;
                        }
                        // Remember whether there are still Producers able to
                        // produce
                        canStillProduce = canStillProduce || consumer.canStillProduce();
                    }
                }
            }
        }
    }
}
