package main.de.grzb.szeibernaeticks.szeibernaeticks.energy;

import net.minecraft.entity.Entity;

/**
 * Fired whenever an {@code IProducer} produces Energy outside of immediate
 * demand, i.e. not in response to an {@code EnergyConsumptionEvent}.
 *
 */
public class EnergyProductionEvent extends EnergyEvent {

    public EnergyProductionEvent(Entity entity, int amount) {
        super(entity, amount);
    }

    /**
     * Consumes the given amount of energy from the produced amount.<br>
     * <br>
     * This can only reduce the remaining amount to 0.
     *
     * @param consumption
     *            The amount to attempt to consume
     * @return How much energy consumption was not met.
     */
    public int consume(int consumption) {
        remainingAmount = remainingAmount - consumption;
        consumption = 0;

        // You cannot consume more than is produced!
        if(remainingAmount < 0) {
            consumption = -remainingAmount;
            remainingAmount = 0;
        }
        return consumption;
    }

}
