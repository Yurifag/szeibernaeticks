package main.de.grzb.szeibernaeticks.control;

/**
 * The types of information one might want to log. Don't make assumptions about
 * which apply, only use those that you are sure of. For example, don't use
 * SETUP unless the method you use it in explicitly is part of the setup
 * process.
 *
 * @author DemRat
 */
public enum LogType {
    /**
     * General Information about the loading and structure of the mod.
     */
    INFO(true), /**
     * Errors during Mod execution.
     */
    ERROR(true), /**
     * Information concerning Exceptions. USE
     * {@code Szeibernaeticks.getLogger().exception()} INSTEAD UNLESS YOU HAVE A
     * GOOD REASON.
     */
    EXCEPTION(true), /**
     * Information concerning the inital loading of the mod.
     */
    SETUP(true), /**
     * General Information about Items.
     */
    ITEM(false), /**
     * Information concerning Szeibernaetick Capabilities.
     */
    SZEIBER_CAP(false), /**
     * Information concerning Szeibernaetick Event Handlers.
     */
    SZEIBER_HANDLER(false), /**
     * Information concerning Szeiberneatick Armouries.
     */
    SZEIBER_ARM(false), /**
     * Information concerning Szeiberneatick Energy.
     */
    SZEIBER_ENERGY(false), /**
     * Information concerning Commands.
     */
    COMMAND(false), /**
     * Logs that produce output that cannot be prevented by the player, i.e. all
     * logs that produce output indefinitely in a newly generated world without
     * mobs or anything else.
     */
    SPAMMY(false), /**
     * Information about the inner workings of the mod.
     */
    DEBUG(false), /**
     * Information concerning the instantiation of classes.
     */
    INSTANTIATION(false), /**
     * Specific Information. For example, exact values used in a method.
     */
    SPECIFIC(false);

    private boolean isEnabledInDefault;

    LogType(boolean b) {
        this.isEnabledInDefault = b;
    }

    public Boolean defaultEnabled() {
        return this.isEnabledInDefault;
    }
}
