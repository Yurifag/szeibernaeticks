package main.de.grzb.szeibernaeticks.networking;

import io.netty.buffer.ByteBuf;
import main.de.grzb.szeibernaeticks.Szeibernaeticks;
import main.de.grzb.szeibernaeticks.szeibernaeticks.SzeibernaetickMapper;
import main.de.grzb.szeibernaeticks.szeibernaeticks.capability.ISzeibernaetickCapability;
import main.de.grzb.szeibernaeticks.szeibernaeticks.capability.armoury.ISzeibernaetickArmouryCapability;
import main.de.grzb.szeibernaeticks.szeibernaeticks.capability.armoury.SzeibernaetickArmouryProvider;
import net.minecraft.client.Minecraft;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.IThreadListener;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class SzeiberCapMessage extends NBTMessage {

    private NBTTagCompound capTag;
    private String identifier;

    public SzeiberCapMessage() {
    }

    public SzeiberCapMessage(ISzeibernaetickCapability cap) {
        capTag = cap.toNBT();
        identifier = cap.getIdentifier();
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        // entityId = buf.readInt();

        identifier = readString(buf);

        capTag = readCompoundTag(buf);
    }

    @Override
    public void toBytes(ByteBuf buf) {
        // buf.writeInt(entityId);

        writeString(buf, identifier);

        writeCompoundTag(buf, capTag);
    }

    public static class SzeiberCapMessageHandler implements IMessageHandler<SzeiberCapMessage, IMessage> {

        @Override
        public IMessage onMessage(SzeiberCapMessage message, MessageContext ctx) {
            // Retrieve data from the message
            final String identifier = message.identifier;
            final NBTTagCompound comp = message.capTag;

            // Find out which side this is on and set the listener to schedule
            // the task on accordingly.
            IThreadListener listener;
            if(ctx.side.isClient()) {
                listener = Minecraft.getMinecraft();
            }
            else {
                return null;
            }

            // To access the Player Object and all that, schedule the task by
            // creating an anonymous class with the logic in the run function
            listener.addScheduledTask(new Runnable() {

                @Override
                public void run() {
                    ISzeibernaetickCapability cap;
                    try {
                        cap = SzeibernaetickMapper.instance.getCapabilityFromIdentifier(identifier).newInstance();
                    }
                    catch(InstantiationException e) {
                        Szeibernaeticks.getLogger().error("Error on instantiating Capability while sending it via SzeiberCapMessage!");
                        e.printStackTrace();
                        return;
                    }
                    catch(IllegalAccessException e) {
                        Szeibernaeticks.getLogger().error("Error on accessing Capability class while sending it via SzeiberCapMessage!");
                        e.printStackTrace();
                        return;
                    }
                    catch(NullPointerException e) {
                        Szeibernaeticks.getLogger().error("Error on getting Capability class from identifier, probably!");
                        e.printStackTrace();
                        return;
                    }

                    cap.fromNBT(comp);

                    Capability<ISzeibernaetickArmouryCapability> prov = SzeibernaetickArmouryProvider.ARMOURY_CAP;

                    ISzeibernaetickArmouryCapability armoury = Minecraft.getMinecraft().player.getCapability(prov, null);
                    armoury.addSzeibernaetick(cap);
                }
            });

            return null;
        }

    }

}
