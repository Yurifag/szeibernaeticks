package main.de.grzb.szeibernaeticks.szeibernaeticks.capability.armoury;

import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;

import main.de.grzb.szeibernaeticks.szeibernaeticks.BodyPart;
import main.de.grzb.szeibernaeticks.szeibernaeticks.capability.ISzeibernaetickCapability;
import main.de.grzb.szeibernaeticks.szeibernaeticks.event.SzeibernaetickInstalledEvent;
import net.minecraftforge.common.MinecraftForge;

public class SzeibernaetickArmoury implements ISzeibernaetickArmouryCapability {

    ConcurrentHashMap<BodyPart, ISzeibernaetickCapability> bodyMap;
    ConcurrentHashMap<Class<? extends ISzeibernaetickCapability>, ISzeibernaetickCapability> itemMap;

    public SzeibernaetickArmoury() {
        bodyMap = new ConcurrentHashMap<BodyPart, ISzeibernaetickCapability>();
        itemMap = new ConcurrentHashMap<Class<? extends ISzeibernaetickCapability>, ISzeibernaetickCapability>();
    }

    @Override
    public boolean addSzeibernaetick(ISzeibernaetickCapability szeiber) {
        if(bodyMap.get(szeiber.getBodyPart()) == null) {
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
    public ISzeibernaetickCapability removeSzeibernaetick(String identifier) {
        ISzeibernaetickCapability szeiber = itemMap.get(identifier);
        itemMap.remove(identifier);
        bodyMap.remove(szeiber.getBodyPart());
        return szeiber;
    }

    @Override
    public ISzeibernaetickCapability getBodyPart(BodyPart b) {
        return bodyMap.get(b);
    }

}
