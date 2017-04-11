package main.de.grzb.szeibernaeticks.szeibernaeticks.event;

import main.de.grzb.szeibernaeticks.szeibernaeticks.ISzeibernaetick;
import main.de.grzb.szeibernaeticks.szeibernaeticks.capability.ISzeibernaetickStorageCapability;
import main.de.grzb.szeibernaeticks.szeibernaeticks.capability.SzeibernaetickStorageProvider;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

/**
 * Implements the functionality of the ItemMetalBones ISzeibernaetick via Event
 * Subscription.
 *
 * @author DemRat
 *
 */
public class SzeibernaetickEventMetalBones implements ISzeibernaetickEventHandler {
	
	private ISzeibernaetick item;

	/**
	 * Constructs this EventHandler while storing the required Item.
	 *
	 * @param szeiber
	 *            The Item this belongs to.
	 */
	public SzeibernaetickEventMetalBones(ISzeibernaetick szeiber) {
		item = szeiber;
	}
	
	@SubscribeEvent
	public void onEntityHurt(LivingHurtEvent e) {
		ISzeibernaetickStorageCapability c = e.getEntity().getCapability(SzeibernaetickStorageProvider.SZEIBER_CAP,
				null);

		if (c.getSzeibernaetick(item) != null) {
			e.setAmount(e.getAmount() / 2);
		}
	}
}
