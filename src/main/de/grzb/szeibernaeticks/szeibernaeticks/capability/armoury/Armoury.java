package main.de.grzb.szeibernaeticks.szeibernaeticks.capability.armoury;

import main.de.grzb.szeibernaeticks.control.Log;
import main.de.grzb.szeibernaeticks.control.LogType;
import main.de.grzb.szeibernaeticks.szeibernaeticks.BodyPart;
import main.de.grzb.szeibernaeticks.szeibernaeticks.capability.ISzeibernaetickCapability;
import main.de.grzb.szeibernaeticks.szeibernaeticks.event.SzeibernaetickInstalledEvent;
import net.minecraft.entity.Entity;
import net.minecraftforge.common.MinecraftForge;

import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;

public class Armoury implements IArmouryCapability {

    private Entity entityAttachedTo;
    ConcurrentHashMap<BodyPart, ISzeibernaetickCapability> bodyMap;
    ConcurrentHashMap<Class<? extends ISzeibernaetickCapability>, ISzeibernaetickCapability> itemMap;

    public Armoury(Entity entity) {
        this.bodyMap = new ConcurrentHashMap<BodyPart, ISzeibernaetickCapability>();
        this.itemMap = new ConcurrentHashMap<Class<? extends ISzeibernaetickCapability>, ISzeibernaetickCapability>();
        this.entityAttachedTo = entity;
    }

    @Override
    public boolean addSzeibernaetick(ISzeibernaetickCapability szeiber) {
        Log.log("Attempting to add " + szeiber.getIdentifier(), LogType.DEBUG, LogType.SZEIBER_ARM);
        Log.log("BodyPart is: " + szeiber.getBodyPart().toString(), LogType.DEBUG, LogType.SZEIBER_ARM, LogType.SPECIFIC);
        Log.log("Szeiber in that Slot is: " + this.bodyMap.get(szeiber.getBodyPart()), LogType.DEBUG, LogType.SZEIBER_ARM, LogType.SPECIFIC);

        if(this.bodyMap.get(szeiber.getBodyPart()) == null) {
            Log.log("Body Part is not used.", LogType.DEBUG, LogType.SZEIBER_ARM, LogType.SPECIFIC);
            // Tell anyone interested that you are installing a Szeibernaetick
            MinecraftForge.EVENT_BUS.post(new SzeibernaetickInstalledEvent(this, szeiber));
            this.bodyMap.put(szeiber.getBodyPart(), szeiber);
            this.itemMap.put(szeiber.getClass(), szeiber);
            return true;
        }
        return false;
    }

    @Override
    public ISzeibernaetickCapability getSzeibernaetick(Class<? extends ISzeibernaetickCapability> szeiberClass) {
        return this.itemMap.get(szeiberClass);
    }

    @Override
    public Collection<ISzeibernaetickCapability> getSzeibernaeticks() {
        return this.bodyMap.values();
    }

    @Override
    public ISzeibernaetickCapability removeSzeibernaetick(ISzeibernaetickCapability szeibernaetick) {
        Class<? extends ISzeibernaetickCapability> szeibernaetickClass = szeibernaetick.getClass();
        if(this.itemMap.get(szeibernaetickClass) != null) {
            this.itemMap.remove(szeibernaetickClass);
            this.bodyMap.remove(szeibernaetick.getBodyPart());
            return szeibernaetick;
        }
        return null;
    }

    @Override
    public ISzeibernaetickCapability getBodyPart(BodyPart b) {
        return this.bodyMap.get(b);
    }

    @Override
    public Entity getEntity() {
        return this.entityAttachedTo;
    }

}
