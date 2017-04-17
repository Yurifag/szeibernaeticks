package main.de.grzb.szeibernaeticks.szeibernaeticks.energy;

public enum EnergyPriority {
    /**
     *
     */
    FILL_ASAP(2),
    /**
     *
     */
    FILL_FAST(1),
    /**
     *
     */
    DONT_CARE(0),
    /**
     *
     */
    EMPTY_FAST(-1),
    /**
     *
     */
    EMPTY_ASAP(-2);

    private int prio;

    private EnergyPriority(int prio) {
        this.prio = prio;
    }

    /**
     * Returns true if the arguments prio is lower than this ones.
     *
     * @return Duh.
     */
    public boolean largerThan(EnergyPriority ePrio) {
        return prio > ePrio.prio;
    }
}
