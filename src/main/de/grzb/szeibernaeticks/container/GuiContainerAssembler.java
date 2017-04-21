package main.de.grzb.szeibernaeticks.container;

import main.de.grzb.szeibernaeticks.client.gui.GuiId;
import main.de.grzb.szeibernaeticks.container.layout.GuiLayoutDefinition;
import main.de.grzb.szeibernaeticks.container.slot.SlotBodyPart;
import main.de.grzb.szeibernaeticks.szeibernaeticks.capability.CapabilityProvider;
import main.de.grzb.szeibernaeticks.szeibernaeticks.capability.ISzeibernaetickCapability;
import main.de.grzb.szeibernaeticks.szeibernaeticks.capability.armoury.ArmouryProvider;
import main.de.grzb.szeibernaeticks.szeibernaeticks.capability.armoury.IArmouryCapability;
import main.de.grzb.szeibernaeticks.tileentity.TileEntityGuiContainerBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Slot;

import java.util.Collection;
import java.util.Iterator;

public class GuiContainerAssembler extends GuiContainerBase {

    public GuiContainerAssembler(TileEntityGuiContainerBase tileEntity, GuiLayoutDefinition layout, GuiId guiId) {
        super(tileEntity, layout, guiId);
    }

    @Override
    public void onContainerClosed(EntityPlayer player) {

        IArmouryCapability playerCapability = player.getCapability(ArmouryProvider.ARMOURY_CAP, null);
        Collection<ISzeibernaetickCapability> playerSzeibernaeticks = playerCapability.getSzeibernaeticks();

        for(Iterator<ISzeibernaetickCapability> iter = playerSzeibernaeticks.iterator(); iter.hasNext(); ) {
            ISzeibernaetickCapability szeibernaetick = iter.next();
            playerCapability.removeSzeibernaetick(szeibernaetick);
        }

        for(Iterator<Slot> iter = this.inventorySlots.iterator(); iter.hasNext(); ) {
            Slot slot = iter.next();
            if(slot instanceof SlotBodyPart && slot.getStack().hasCapability(CapabilityProvider.SZEIBER_CAP, null)) {
                ISzeibernaetickCapability szeibernaetick = slot.getStack().getCapability(CapabilityProvider.SZEIBER_CAP, null);
                boolean installed = false;
                for(Iterator<ISzeibernaetickCapability> iter2 = playerSzeibernaeticks.iterator(); iter2.hasNext(); ) {
                    ISzeibernaetickCapability playerSzeibernaetick = iter2.next();
                    if(playerSzeibernaetick.equals(szeibernaetick)) {
                        // player didn't modify this slot; reattach the player's Szeibernaetick, NOT a new one
                        playerCapability.addSzeibernaetick(playerSzeibernaetick);
                        installed = true;
                        break;
                    }
                }
                // player doesn't have this Szeibernaetick yet, attach it
                if(!installed) playerCapability.addSzeibernaetick(szeibernaetick);
            }
        }
    }

}
