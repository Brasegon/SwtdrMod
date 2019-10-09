package fr.brangers.swtdrmod.network;

import io.netty.buffer.ByteBuf;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class MyMessage implements IMessage {

	private String text;
	 
    public MyMessage() { }
 
    public MyMessage(String text) {
        this.text = text;
    }
 
    @Override
    public void fromBytes(ByteBuf buf) {
        text = ByteBufUtils.readUTF8String(buf); // this class is very useful in general for writing more complex objects
    }
 
    @Override
    public void toBytes(ByteBuf buf) {
        ByteBufUtils.writeUTF8String(buf, text);
    }
 
    public static class Handler implements IMessageHandler <MyMessage, IMessage> {
 
        @Override
        public IMessage onMessage(MyMessage message, MessageContext ctx) {
            System.out.println(String.format("Received %s from %s", message.text, ctx.getServerHandler().player.getDisplayName()));
            return null; // no response in this case
        }

    }

}
