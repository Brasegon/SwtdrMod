package fr.brangers.swtdrmod;

import java.io.File;

import fr.brangers.swtdrmod.entity.EntityBalls;
import fr.brangers.swtdrmod.network.MyMessage;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import net.minecraftforge.fml.relauncher.Side;

public class SwtdrCommon {
	public static SimpleNetworkWrapper network;
	@EventHandler
	    public void preInit(File configFile)
	    {
		 System.out.println("pre init côté tout");
		 network = NetworkRegistry.INSTANCE.newSimpleChannel("Channel");
	 	 network.registerMessage(MyMessage.Handler.class, MyMessage.class, 0, Side.SERVER);
	    }
	 
	 	public void init()
	    {
	 		EntityRegistry.registerModEntity(new ResourceLocation(swtdrMod.MODID + ":" + "magiball"), EntityBalls.class, "magiball", 1050, swtdrMod.instance, 64, 10, true);
	    }
	 	public void registerItemRenderer(Item item, int meta, String id) {
	 	}
}
