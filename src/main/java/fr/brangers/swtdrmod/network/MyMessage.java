package fr.brangers.swtdrmod.network;

import fr.brangers.swtdrmod.blocks.tileEntity.TileEntityForge;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class MyMessage implements IMessage {

	private int i;
	 
    public MyMessage() { }
 
    public MyMessage(int i) {
        this.i = i;
    }
 
    @Override
    public void fromBytes(ByteBuf buf) {
        i = buf.readInt(); // this class is very useful in general for writing more complex objects
    }
 
    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeInt(i);
    }
 
    public static class MyMessageHandler implements IMessageHandler<MyMessage, IMessage> {
    	  // Do note that the default constructor is required, but implicitly defined in this case

    	  @Override public IMessage onMessage(MyMessage message, MessageContext ctx) {
    	    // This is the player the packet was sent to the server from
    	    EntityPlayerMP serverPlayer = ctx.getServerHandler().player;
    	    // The value that was sent
    	    int amount = message.i;
    	    // Execute the action on the main server thread by adding it as a scheduled task
    	    if (amount > 0) {
    	    	if (TileEntityForge.canSendForge) {
    	    		TileEntityForge.openButton = true;
    	    	}
    	    }
    	    // No response packet
    	    return null;
    	  }
    	}

}
