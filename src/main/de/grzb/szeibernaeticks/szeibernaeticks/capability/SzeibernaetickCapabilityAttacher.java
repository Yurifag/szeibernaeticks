package main.de.grzb.szeibernaeticks.szeibernaeticks.capability;

import main.de.grzb.szeibernaeticks.Szeibernaeticks;
import main.de.grzb.szeibernaeticks.networking.SzeiberCapMessage;
import main.de.grzb.szeibernaeticks.networking.SzeibernaeticksNetworkWrapper;
import main.de.grzb.szeibernaeticks.szeibernaeticks.capability.armoury.SzeibernaetickArmouryProvider;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
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

        if(event.getObject() instanceof EntityLivingBase) {
            event.addCapability(new ResourceLocation(Szeibernaeticks.MOD_ID), new SzeibernaetickArmouryProvider());
        }
    }

    @SubscribeEvent
    public void attachToJoining(EntityJoinWorldEvent event) {
        Entity entity = event.getEntity();
        if(entity instanceof EntityPlayerMP && entity.hasCapability(SzeibernaetickArmouryProvider.ARMOURY_CAP, null)) {
            for(ISzeibernaetickCapability szeiber : entity
                    .getCapability(SzeibernaetickArmouryProvider.ARMOURY_CAP, null).getSzeibernaeticks()) {
                SzeibernaeticksNetworkWrapper.INSTANCE.sendToAll(new SzeiberCapMessage(
                        szeiber)/* , (EntityPlayerMP) entity */);
            }
        }
    }
}
