package main.de.grzb.szeibernaeticks.szeibernaeticks.energy;

public interface IEnergyConsumer {

    /**
     * Consumes a single unit of energy and returns 1.<br>
     * <br>
     * This consumes a single unit of energy, if possible. If for whatever
     * reason this cannot consume, this returns 0.
     *
     * @return 1 if consumption was successfull, 0 otherwise.
     */
    public int consume();

    /**
     * Returns whether this Consumer can still consume energy this tick.<br>
     * <br>
     * This returns false exactly when consume() would return 0.
     *
     * @return Duh
     */
    public boolean canStillProduce();

    /**
     * Returns with what priority this Consumer currently needs to consume
     * energy.
     *
     * @return
     */
    public EnergyPriority currentPrio();

}
