package main.de.grzb.szeibernaeticks.szeibernaeticks.energy;

/**
 * A Szeibernaetick able to produce energy.
 *
 * @author DemRat
 *
 */
public interface IEnergyProducer {

    /**
     * Produces one unit of energy.<br>
     * <br>
     * This produces a single unit of energy, if possible. If for whatever
     * reason this can not produce, this returns 0.
     *
     * @return 1 if production was successfull, 0 otherwise.
     */
    public int produceAdHoc();

    /**
     * Returns whether this Producer can still generate Energy this tick.<br>
     * <br>
     * This returns false exactly when produceAdHoc would return 0.
     *
     * @return
     */
    public boolean canStillProduce();

    /**
     * Returns with what priority this Producer currently needs to get rid of
     * its energy.
     *
     * @return
     */
    public EnergyPriority currentPrio();

}
