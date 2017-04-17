package main.de.grzb.szeibernaeticks.szeibernaeticks.event;

import main.de.grzb.szeibernaeticks.szeibernaeticks.capability.SzeibernaetickGeneratorStomachCapability;
import main.de.grzb.szeibernaeticks.szeibernaeticks.capability.armoury.ISzeibernaetickArmouryCapability;
import main.de.grzb.szeibernaeticks.szeibernaeticks.capability.armoury.SzeibernaetickArmouryProvider;
import net.minecraft.item.ItemFood;
import net.minecraftforge.event.entity.living.LivingEntityUseItemEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class SzeibernaetickGeneratorStomachHandler implements ISzeibernaetickEventHandler {

    @SubscribeEvent
    public void onFoodEaten(LivingEntityUseItemEvent.Finish event) {
        if(event.getItem().getItem() instanceof ItemFood) {
            ItemFood food = (ItemFood) event.getItem().getItem();

            ISzeibernaetickArmouryCapability armoury = event.getEntity()
                    .getCapability(SzeibernaetickArmouryProvider.ARMOURY_CAP, null);
            if(armoury != null) {
                SzeibernaetickGeneratorStomachCapability genStomach = (SzeibernaetickGeneratorStomachCapability) armoury
                        .getSzeibernaetick(SzeibernaetickGeneratorStomachCapability.class);
                if(genStomach != null) {
                    int totalGeneration = (int) ((food.getHealAmount(event.getItem())
                            + food.getSaturationModifier(event.getItem())) / 2);
                    genStomach.produce(totalGeneration, event.getEntity());
                }
            }
        }
    }

}
