package main.de.grzb.szeibernaeticks.szeibernaeticks.event;

import main.de.grzb.szeibernaeticks.control.Log;
import main.de.grzb.szeibernaeticks.control.LogType;
import main.de.grzb.szeibernaeticks.szeibernaeticks.capability.SzeibernaetickArchersEyesCapability;
import main.de.grzb.szeibernaeticks.szeibernaeticks.capability.armoury.ArmouryProvider;
import main.de.grzb.szeibernaeticks.szeibernaeticks.capability.armoury.IArmouryCapability;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemBow;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingEntityUseItemEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class SzeibernaetickArchersEyesHandler implements ISzeibernaetickEventHandler {

    @SubscribeEvent
    public void onDrawBow(LivingEntityUseItemEvent.Tick e) {
        Log.log("[ArchEyesHandler] Recieving event!", LogType.DEBUG, LogType.SPAMMY, LogType.SZEIBER_HANDLER);
        ItemStack itemstack = e.getItem();
        if(itemstack.getItem() instanceof ItemBow) {
            Log.log("[ArchEyesHandler] Is Bow!", LogType.DEBUG, LogType.SPAMMY, LogType.SZEIBER_HANDLER);

            EntityLivingBase shooter = e.getEntityLiving();
            IArmouryCapability armoury = shooter.getCapability(ArmouryProvider.ARMOURY_CAP, null);

            if(armoury != null) {
                Log.log("[ArchEyesHandler] Found Armory!", LogType.DEBUG, LogType.SPAMMY, LogType.SZEIBER_HANDLER);
                SzeibernaetickArchersEyesCapability eyes = (SzeibernaetickArchersEyesCapability) armoury
                        .getSzeibernaetick(SzeibernaetickArchersEyesCapability.class);

                if(eyes != null) {
                    Log.log("[ArchEyesHandler] Found Eyes!", LogType.DEBUG, LogType.SPAMMY, LogType.SZEIBER_HANDLER);
                    eyes.grantVision(e);
                }
            }
        }
    }
}
