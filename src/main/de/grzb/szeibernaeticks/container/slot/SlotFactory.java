package main.de.grzb.szeibernaeticks.container.slot;

import main.de.grzb.szeibernaeticks.container.layout.SlotBodyPartDefinition;
import main.de.grzb.szeibernaeticks.container.layout.SlotDefinition;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;

public class SlotFactory {

    public static SlotItemHandler getNewSlotItemHandler(SlotDefinition slotDefinition, IItemHandler itemHandler, int index, int x, int y) {
        SlotItemHandler slot;
        switch(slotDefinition.slotType) {
            case BODYPART:
                SlotBodyPartDefinition slotBodyPartDefinition = (SlotBodyPartDefinition) slotDefinition;
                slot = new SlotBodyPart(itemHandler, index, x, y, slotBodyPartDefinition.bodyPart);
                slot.putStack(slotBodyPartDefinition.installedSzeibernaetick);
                break;
            case VANILLA:
            default:
                slot = new SlotItemHandler(itemHandler, index, x, y);
                break;
        }

        return slot;
    }

}
