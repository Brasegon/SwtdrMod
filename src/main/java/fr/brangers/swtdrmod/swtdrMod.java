package fr.brangers.swtdrmod;



import org.apache.logging.log4j.Logger;

import fr.brangers.swtdrmod.blocks.SwtdrBlocks;
import fr.brangers.swtdrmod.event.gui.GuiHandler;
import fr.brangers.swtdrmod.items.SwtdrItem;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;

@Mod(modid = swtdrMod.MODID, name = "SWTDR Mod", version = "0.1", acceptedMinecraftVersions = "[1.12.2]")


public class swtdrMod {
	public static final String MODID = "swtdrmod";
	@Instance(swtdrMod.MODID)
	public static swtdrMod instance;
	
	public static Logger logger;
	 
    @EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
    	logger = event.getModLog();
        proxy.preInit(event.getSuggestedConfigurationFile()); // initialise le logger.  event.getModLog() retourne un logger avec votre modid
        NetworkRegistry.INSTANCE.registerGuiHandler(this, new GuiHandler());
    }
 
    @EventHandler
    public void init(FMLInitializationEvent event)
    {
        proxy.init();
    }
    @EventHandler
    public void onServerStart(FMLServerStartingEvent event)
    {
 
    }
    @Mod.EventBusSubscriber
	public static class RegistrationHandler {
    	@SubscribeEvent
    	public static void registerItems(RegistryEvent.Register<Item> event) {
    		SwtdrItem.register(event.getRegistry());
    		SwtdrBlocks.registerItemBlocks(event.getRegistry());
    	}
    	@SubscribeEvent
    	public static void registerItems(ModelRegistryEvent event) {
    		SwtdrItem.registerModels();
    		SwtdrBlocks.registerModels();
    	}
    	@SubscribeEvent
    	public static void registerBlocks(RegistryEvent.Register<Block> event) {
    		SwtdrBlocks.register(event.getRegistry());
    	}
	}
    @SidedProxy(clientSide = "fr.brangers.swtdrmod.SwtdrClient", serverSide = "fr.brangers.swtdrmod.SwtdrServeur")
    public static SwtdrCommon proxy;
}
