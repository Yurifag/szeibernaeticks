package main.de.grzb.szeibernaeticks.szeibernaeticks.event;

import main.de.grzb.szeibernaeticks.szeibernaeticks.capability.GeneratorStomachCapability;
import main.de.grzb.szeibernaeticks.szeibernaeticks.capability.armoury.ArmouryProvider;
import main.de.grzb.szeibernaeticks.szeibernaeticks.capability.armoury.IArmouryCapability;
import net.minecraft.item.ItemFood;
import net.minecraftforge.event.entity.living.LivingEntityUseItemEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class GeneratorStomachHandler implements ISzeibernaetickEventHandler {

    @SubscribeEvent
    public void onFoodEaten(LivingEntityUseItemEvent.Finish event) {
        if(event.getItem().getItem() instanceof ItemFood) {
            ItemFood food = (ItemFood) event.getItem().getItem();

            IArmouryCapability armoury = event.getEntity().getCapability(ArmouryProvider.ARMOURY_CAP, null);
            if(armoury != null) {
                GeneratorStomachCapability genStomach = (GeneratorStomachCapability) armoury.getSzeibernaetick(GeneratorStomachCapability.class);
                if(genStomach != null) {
                    int totalGeneration = (int) ((food.getHealAmount(event.getItem()) + food.getSaturationModifier(event.getItem())) / 2);
                    genStomach.produce(totalGeneration, event.getEntity());
                }
            }
        }
    }

}
