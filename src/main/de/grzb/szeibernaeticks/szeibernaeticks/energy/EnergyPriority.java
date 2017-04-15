package main.de.grzb.szeibernaeticks.szeibernaeticks.energy;

public enum EnergyPriority {
    DESTRUCTION(2), SIDE_EFFECTS(1), NOTHING(0), NEEDED_SPACE(-1), REQUIRED_SPACE(-2);

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
