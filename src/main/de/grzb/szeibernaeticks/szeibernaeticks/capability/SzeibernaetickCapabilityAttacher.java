package main.de.grzb.szeibernaeticks.szeibernaeticks.capability;

import main.de.grzb.szeibernaeticks.Szeibernaeticks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
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

  @SubscribeEvent
  public void attachToLiving(AttachCapabilitiesEvent<Entity> event) {
    Szeibernaeticks.getLogger().info("Attaching Capability to Entity!");

    if(event.getObject() instanceof EntityLivingBase) {
      event.addCapability(new ResourceLocation(Szeibernaeticks.MOD_ID), new SzeibernaetickStorageProvider());
    }
  }
}
