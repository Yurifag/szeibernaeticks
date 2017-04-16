package main.de.grzb.szeibernaeticks.szeibernaeticks.energy.feedback;

import main.de.grzb.szeibernaeticks.szeibernaeticks.capability.ISzeibernaetickCapability;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.DamageSource;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentTranslation;

public class EnergyFeedbackDamage extends DamageSource {

    private ISzeibernaetickCapability szeiber;

    public EnergyFeedbackDamage(ISzeibernaetickCapability szeiberCause) {
        super("energyFeedback");
        setDamageBypassesArmor();
        szeiber = szeiberCause;
    }

    @Override
    public ITextComponent getDeathMessage(EntityLivingBase entityLivingBaseIn) {
        String s = "death.attack." + this.damageType;
        return new TextComponentTranslation(s,
                new Object[] { entityLivingBaseIn.getDisplayName(), szeiber.getIdentifier() });
    }
}
