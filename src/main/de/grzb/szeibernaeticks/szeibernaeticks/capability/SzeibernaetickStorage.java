package main.de.grzb.szeibernaeticks.szeibernaeticks.capability;

import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;

import main.de.grzb.szeibernaeticks.szeibernaeticks.ISzeibernaetick;
import net.minecraft.item.ItemStack;

public class SzeibernaetickStorage implements ISzeibernaetickStorageCapability {
	
	ConcurrentHashMap<BodyPart, ItemStack> bodyMap;
	ConcurrentHashMap<ISzeibernaetick, ItemStack> itemMap;

	public SzeibernaetickStorage() {
		bodyMap = new ConcurrentHashMap<BodyPart, ItemStack>();
		itemMap = new ConcurrentHashMap<ISzeibernaetick, ItemStack>();
	}
	
	@Override
	public boolean addSzeibernaetick(ItemStack stack) {
		if (stack.getItem() instanceof ISzeibernaetick) {
			// Create a copy to add to prevent bullshit
			ItemStack workingCopy = new ItemStack(stack.serializeNBT());
			ISzeibernaetick szeiber = (ISzeibernaetick) stack.getItem();
			if (bodyMap.get(szeiber.getBodyPart()) == null) {
				bodyMap.put(szeiber.getBodyPart(), workingCopy);
				itemMap.put(szeiber, workingCopy);
				return true;
			}
		}
		return false;
	}

	@Override
	public ItemStack getSzeibernaetick(ISzeibernaetick szeiber) {
		return itemMap.get(szeiber);
	}

	@Override
	public Collection<ItemStack> getSzeibernaeticks() {
		return bodyMap.values();
	}

	@Override
	public ItemStack removeSzeibernaetick(ISzeibernaetick szeiber) {
		ItemStack returnStack = new ItemStack(itemMap.get(szeiber).serializeNBT());
		itemMap.remove(szeiber);
		bodyMap.remove(szeiber.getBodyPart());
		return returnStack;
	}
	
}
