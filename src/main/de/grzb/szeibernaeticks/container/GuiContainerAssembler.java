package main.de.grzb.szeibernaeticks.container;

import java.util.Collection;
import java.util.Iterator;

import main.de.grzb.szeibernaeticks.client.gui.GuiId;
import main.de.grzb.szeibernaeticks.container.layout.GuiLayoutDefinition;
import main.de.grzb.szeibernaeticks.container.slot.SlotBodyPart;
import main.de.grzb.szeibernaeticks.szeibernaeticks.capability.ISzeibernaetickCapability;
import main.de.grzb.szeibernaeticks.szeibernaeticks.capability.SzeibernaetickCapabilityProvider;
import main.de.grzb.szeibernaeticks.szeibernaeticks.capability.armoury.ISzeibernaetickArmouryCapability;
import main.de.grzb.szeibernaeticks.szeibernaeticks.capability.armoury.SzeibernaetickArmouryProvider;
import main.de.grzb.szeibernaeticks.tileentity.TileEntityGuiContainerBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Slot;

public class GuiContainerAssembler extends GuiContainerBase {

    public GuiContainerAssembler(TileEntityGuiContainerBase tileEntity, GuiLayoutDefinition layout, GuiId guiId) {
        super(tileEntity, layout, guiId);
    }
    
    @Override
    public void onContainerClosed(EntityPlayer player) {
        
        ISzeibernaetickArmouryCapability playerCapability = player.getCapability(SzeibernaetickArmouryProvider.ARMOURY_CAP, null);
        Collection<ISzeibernaetickCapability> playerSzeibernaeticks = playerCapability.getSzeibernaeticks();

        for(Iterator<ISzeibernaetickCapability> i = playerSzeibernaeticks.iterator(); i.hasNext();) {
            ISzeibernaetickCapability szeibernaetick = (ISzeibernaetickCapability) i.next();
            playerCapability.removeSzeibernaetick(szeibernaetick.getClass());
        }
        
        for(Iterator<Slot> i = this.inventorySlots.iterator(); i.hasNext();) { 
            Slot slot = (Slot) i.next();
            if(slot instanceof SlotBodyPart && slot.getStack().hasCapability(SzeibernaetickCapabilityProvider.SZEIBER_CAP, null)) {
                ISzeibernaetickCapability szeibernaetick = (ISzeibernaetickCapability) slot.getStack().getCapability(SzeibernaetickCapabilityProvider.SZEIBER_CAP, null);
                boolean installed = false;
                for(Iterator<ISzeibernaetickCapability> j = playerSzeibernaeticks.iterator(); j.hasNext();) {
                    ISzeibernaetickCapability playerSzeibernaetick = (ISzeibernaetickCapability) j.next();
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
