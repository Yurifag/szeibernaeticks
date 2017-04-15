package main.de.grzb.szeibernaeticks.networking;

import io.netty.buffer.ByteBuf;
import main.de.grzb.szeibernaeticks.szeibernaeticks.capability.armoury.ISzeibernaetickArmouryCapability;
import main.de.grzb.szeibernaeticks.szeibernaeticks.capability.armoury.SzeibernaetickArmouryCapabilityStorage;
import main.de.grzb.szeibernaeticks.szeibernaeticks.capability.armoury.SzeibernaetickArmouryProvider;

public class SzeiberArmMessage extends NBTMessage {

    private ISzeibernaetickArmouryCapability arm;

    public SzeiberArmMessage(ISzeibernaetickArmouryCapability arm) {
        this.arm = arm;
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        SzeibernaetickArmouryCapabilityStorage armStore = new SzeibernaetickArmouryCapabilityStorage();
        armStore.readNBT(SzeibernaetickArmouryProvider.ARMOURY_CAP, arm, null, readTag(buf));
    }

    @Override
    public void toBytes(ByteBuf buf) {
        SzeibernaetickArmouryCapabilityStorage armStore = new SzeibernaetickArmouryCapabilityStorage();
        writeTag(buf, armStore.writeNBT(SzeibernaetickArmouryProvider.ARMOURY_CAP, arm, null));
    }

    /*
     * public static class SzeiberArmMessageHandler implements
     * IMessageHandler<SzeiberArmMessage, IMessage> {
     * 
     * @Override public IMessage onMessage(SzeiberArmMessage message,
     * MessageContext ctx) { // Get the correct PlayerEntity final
     * EntityPlayerMP serverPlayer = ctx.getServerHandler().playerEntity;
     * 
     * // Retrieve data from the message final ISzeibernaetickArmouryCapability
     * arm = message.arm;
     * 
     * // Find out which side this is on and set the listener to schedule // the
     * task on accordingly. IThreadListener listener; if (ctx.side.isClient()) {
     * listener = Minecraft.getMinecraft(); } else { return null; }
     * 
     * // To access the Player Object and all that, schedule the task by //
     * creating an anonymous class with the logic in the run function
     * listener.addScheduledTask(new Runnable() {
     * 
     * @Override public void run() { serverPlayer.get } try { cap =
     * SzeibernaetickMapper.instance.getCapabilityFromIdentifier(identifier).
     * newInstance(); } catch (InstantiationException e) {
     * Szeibernaeticks.getLogger()
     * .error("Error on instantiating Capability while sending it via SzeiberCapMessage!"
     * ); e.printStackTrace(); return; } catch (IllegalAccessException e) {
     * Szeibernaeticks.getLogger()
     * .error("Error on accessing Capability class while sending it via SzeiberCapMessage!"
     * ); e.printStackTrace(); return; } catch (NullPointerException e) {
     * Szeibernaeticks.getLogger()
     * .error("Error on getting Capability class from identifier, probably!");
     * e.printStackTrace(); return; }
     * 
     * cap.fromNBT(comp);
     * 
     * ISzeibernaetickArmouryCapability armoury = serverPlayer
     * .getCapability(SzeibernaetickArmouryProvider.ARMOURY_CAP, null);
     * armoury.addSzeibernaetick(cap); } });
     * 
     * return null; }
     */

}
