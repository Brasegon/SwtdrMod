package fr.brangers.swtdrmod;



import org.apache.logging.log4j.Logger;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;

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
    @SidedProxy(clientSide = "fr.brangers.swtdrmod.SwtdrClient", serverSide = "fr.brangers.swtdrmod.SwtdrServeur")
    public static SwtdrCommon proxy;
}
