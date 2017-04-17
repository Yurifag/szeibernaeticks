package main.de.grzb.szeibernaeticks.tileentity;

import java.util.Collection;
import java.util.Iterator;

import io.netty.util.internal.ConcurrentSet;
import main.de.grzb.szeibernaeticks.client.gui.GuiId;
import main.de.grzb.szeibernaeticks.container.GuiContainerAssembler;
import main.de.grzb.szeibernaeticks.container.GuiContainerBase;
import main.de.grzb.szeibernaeticks.container.layout.GuiLayoutDefinition;
import main.de.grzb.szeibernaeticks.container.layout.SlotBodyPartDefinition;
import main.de.grzb.szeibernaeticks.container.slot.SlotType;
import main.de.grzb.szeibernaeticks.szeibernaeticks.BodyPart;
import main.de.grzb.szeibernaeticks.szeibernaeticks.SzeibernaetickMapper;
import main.de.grzb.szeibernaeticks.szeibernaeticks.capability.ISzeibernaetickCapability;
import main.de.grzb.szeibernaeticks.szeibernaeticks.capability.armoury.SzeibernaetickArmouryProvider;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.items.CapabilityItemHandler;

public class TileEntityGuiContainerAssembler extends TileEntityGuiContainerBase {

    public TileEntityGuiContainerAssembler() {
        this("assembler", null, 6);
    }

    public TileEntityGuiContainerAssembler(Block block) {
        this("assembler", block, 6);
    }

    public TileEntityGuiContainerAssembler(String tileEntityName, Block block, int slotSize) {
        super(tileEntityName, block, slotSize, GuiId.ASSEMBLER);
    }

    @Override
    public GuiContainerBase getContainer(EntityPlayer player) {
        Collection<ISzeibernaetickCapability> szeibernaeticks = player.getCapability(SzeibernaetickArmouryProvider.ARMOURY_CAP, null).getSzeibernaeticks();
        int width = 162;
        int height = 118;

        ConcurrentSet<BodyPart> bodyParts = BodyPart.getBodySet();
        SlotBodyPartDefinition[] slotBodyPartDefinitions = new SlotBodyPartDefinition[bodyParts.size()];
        int x = 0;
        int y = 0;
        int i = 0;
        for(Iterator<BodyPart> iterator = bodyParts.iterator(); iterator.hasNext();) {
            BodyPart bodyPart = (BodyPart) iterator.next();
            if(i < 6) {
                x = 0;
                y = i * GuiLayoutDefinition.ITEM_SLOT_SIZE;
            }
            else {
                x = width - GuiLayoutDefinition.ITEM_SLOT_SIZE;
                y = (i - 6) * GuiLayoutDefinition.ITEM_SLOT_SIZE;
            }

            ItemStack itemStack = ItemStack.EMPTY;
            for(Iterator<ISzeibernaetickCapability> iterator2 = szeibernaeticks.iterator(); iterator2.hasNext();) {
                ISzeibernaetickCapability capability = (ISzeibernaetickCapability) iterator2.next();
                if(bodyPart.equals(capability.getBodyPart())) {
                    itemStack = new ItemStack(SzeibernaetickMapper.instance.getItemFromIdentifier(capability.getIdentifier()));
                }
            }

            slotBodyPartDefinitions[i] = new SlotBodyPartDefinition(SlotType.BODYPART, x, y, bodyPart, itemStack);
            i++;
        }

        GuiLayoutDefinition layout = new GuiLayoutDefinition(this.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null), slotBodyPartDefinitions, player.inventory, width, height);
        return new GuiContainerAssembler(this, layout, this.guiId);
    }

}
