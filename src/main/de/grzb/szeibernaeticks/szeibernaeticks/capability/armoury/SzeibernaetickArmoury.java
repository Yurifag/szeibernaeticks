package main.de.grzb.szeibernaeticks.szeibernaeticks.capability.armoury;

import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;

import main.de.grzb.szeibernaeticks.szeibernaeticks.BodyPart;
import main.de.grzb.szeibernaeticks.szeibernaeticks.capability.ISzeibernaetickCapability;

public class SzeibernaetickArmoury implements ISzeibernaetickArmouryCapability {
    
    ConcurrentHashMap<BodyPart, ISzeibernaetickCapability> bodyMap;
    ConcurrentHashMap<Class<? extends ISzeibernaetickCapability>, ISzeibernaetickCapability> itemMap;
    
    public SzeibernaetickArmoury() {
        bodyMap = new ConcurrentHashMap<BodyPart, ISzeibernaetickCapability>();
        itemMap = new ConcurrentHashMap<Class<? extends ISzeibernaetickCapability>, ISzeibernaetickCapability>();
    }
    
    @Override
    public boolean addSzeibernaetick(ISzeibernaetickCapability szeiber) {
        if (bodyMap.get(szeiber.getBodyPart()) == null) {
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
    
}
