package main.de.grzb.szeibernaeticks.tileentity;

import java.util.stream.IntStream;

import main.de.grzb.szeibernaeticks.client.gui.GuiId;
import main.de.grzb.szeibernaeticks.container.GuiContainerBase;
import main.de.grzb.szeibernaeticks.container.layout.GuiLayoutDefinition;
import main.de.grzb.szeibernaeticks.container.layout.SlotDefinition;
import net.minecraft.block.Block;
import net.minecraft.inventory.IInventory;
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
    public GuiContainerBase getContainer(IInventory playerInventory) {
        SlotDefinition[] slotDefinitions = new SlotDefinition[6];
        IntStream.range(0, 6).forEachOrdered(n -> {
            slotDefinitions[n] = new SlotDefinition(0, n * GuiLayoutDefinition.ITEM_SLOT_SIZE);
        });

        return new GuiContainerBase(this,
                new GuiLayoutDefinition(this.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null),
                        slotDefinitions, playerInventory),
                this.guiId);
    }

}
