package main.de.grzb.szeibernaeticks.szeibernaeticks.capability;

import main.de.grzb.szeibernaeticks.Szeibernaeticks;
import main.de.grzb.szeibernaeticks.item.szeibernaetick.SzeibernaetickBase;
import main.de.grzb.szeibernaeticks.szeibernaeticks.capability.armoury.SzeibernaetickArmouryProvider;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

/**
 * Responsible for attaching the {@code ISzeibernaetickStorageCapability} to
 * Entities.
 *
 * @author DemRat
 *
 */
public class SzeibernaetickCapabilityAttacher {
    
    /**
     * Attaches ISzeibernaetickSotrage to entities.
     *
     * @param event
     *            The event prompting to attach the Capability.
     */
    @SubscribeEvent
    public void attachToLiving(AttachCapabilitiesEvent<Entity> event) {
        Szeibernaeticks.getLogger().info("Attaching Capability to Entity!");
        
        if (event.getObject() instanceof EntityLivingBase) {
            event.addCapability(new ResourceLocation(Szeibernaeticks.MOD_ID), new SzeibernaetickArmouryProvider());
        }
    }
    
    /**
     * Attaches ISzeibernaetickCapabilities to items whose Item is an
     * ISzeibernaetick via Event Subscribtion.
     *
     * @param event
     *            The event prompting to attach the Capability.
     */
    @SubscribeEvent
    public void attachToItem(AttachCapabilitiesEvent<Item> event) {
        Item item = event.getObject();
        if (item instanceof SzeibernaetickBase) {
            SzeibernaetickBase szeiber = (SzeibernaetickBase) item;
            SzeibernaetickCapabilityProvider provider = new SzeibernaetickCapabilityProvider(szeiber);
            event.addCapability(new ResourceLocation(Szeibernaeticks.MOD_ID + provider.getIdentifier()), provider);
        }
    }
}
