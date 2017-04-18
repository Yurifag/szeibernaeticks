package main.de.grzb.szeibernaeticks.szeibernaeticks.capability.armoury;

import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;

import main.de.grzb.szeibernaeticks.control.Log;
import main.de.grzb.szeibernaeticks.control.LogType;
import main.de.grzb.szeibernaeticks.szeibernaeticks.BodyPart;
import main.de.grzb.szeibernaeticks.szeibernaeticks.capability.ISzeibernaetickCapability;
import main.de.grzb.szeibernaeticks.szeibernaeticks.event.SzeibernaetickInstalledEvent;
import net.minecraft.entity.Entity;
import net.minecraftforge.common.MinecraftForge;

public class SzeibernaetickArmoury implements ISzeibernaetickArmouryCapability {

    private Entity entityAttachedTo;
    ConcurrentHashMap<BodyPart, ISzeibernaetickCapability> bodyMap;
    ConcurrentHashMap<Class<? extends ISzeibernaetickCapability>, ISzeibernaetickCapability> itemMap;

    public SzeibernaetickArmoury(Entity entity) {
        bodyMap = new ConcurrentHashMap<BodyPart, ISzeibernaetickCapability>();
        itemMap = new ConcurrentHashMap<Class<? extends ISzeibernaetickCapability>, ISzeibernaetickCapability>();
        entityAttachedTo = entity;
    }

    @Override
    public boolean addSzeibernaetick(ISzeibernaetickCapability szeiber) {
        Log.log("Attempting to add " + szeiber.getIdentifier(), LogType.DEBUG, LogType.SZEIBER_ARM);
        Log.log("BodyPart is: " + szeiber.getBodyPart().toString(), LogType.DEBUG, LogType.SZEIBER_ARM, LogType.SPECIFIC);
        Log.log("Szeiber in that Slot is: " + bodyMap.get(szeiber.getBodyPart()), LogType.DEBUG, LogType.SZEIBER_ARM, LogType.SPECIFIC);
        
        if(bodyMap.get(szeiber.getBodyPart()) == null) {
            Log.log("Body Part is not used.", LogType.DEBUG, LogType.SZEIBER_ARM, LogType.SPECIFIC);
            // Tell anyone interested that you are installing a Szeibernaetick
            MinecraftForge.EVENT_BUS.post(new SzeibernaetickInstalledEvent(this, szeiber));
            bodyMap.put(szeiber.getBodyPart(), szeiber);
            itemMap.put(szeiber.getClass(), szeiber);
            return true;
        }
        return false;
    }

    @Override
    public ISzeibernaetickCapability getSzeibernaetick(Class<? extends ISzeibernaetickCapability> szeiberClass) {
        return itemMap.get(szeiberClass);
    }

    @Override
    public Collection<ISzeibernaetickCapability> getSzeibernaeticks() {
        return bodyMap.values();
    }

    @Override
    public ISzeibernaetickCapability removeSzeibernaetick(Class<? extends ISzeibernaetickCapability> szeibernaetickClass) {
        ISzeibernaetickCapability szeibernaetick = itemMap.get(szeibernaetickClass);
        if(szeibernaetick != null) {
          itemMap.remove(szeibernaetick);
          bodyMap.remove(szeibernaetick.getBodyPart());
          return szeibernaetick;
        }
        return null;
    }

    @Override
    public ISzeibernaetickCapability getBodyPart(BodyPart b) {
        return bodyMap.get(b);
    }

    @Override
    public Entity getEntity() {
        return entityAttachedTo;
    }

}
