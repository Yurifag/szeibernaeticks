package main.de.grzb.szeibernaeticks.container.layout;

import main.de.grzb.szeibernaeticks.container.slot.SlotType;
import main.de.grzb.szeibernaeticks.szeibernaeticks.BodyPart;
import net.minecraft.item.ItemStack;

public class SlotBodyPartDefinition extends SlotDefinition {
    public BodyPart bodyPart;
    public ItemStack installedSzeibernaetick;

    public SlotBodyPartDefinition(SlotType slotType, int x, int y, BodyPart bodyPart, ItemStack installedSzeibernatick) {
        super(slotType, x, y);
        this.bodyPart = bodyPart;
        this.installedSzeibernaetick = installedSzeibernatick;
    }

    public SlotBodyPartDefinition(SlotType slotType, int x, int y, BodyPart bodyPart) {
        this(slotType, x, y, bodyPart, ItemStack.EMPTY);
    }
}
