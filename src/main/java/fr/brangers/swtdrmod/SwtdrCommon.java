package fr.brangers.swtdrmod;

import java.io.File;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class SwtdrCommon {
	 @EventHandler
	    public void preInit(File configFile)
	    {
		 System.out.println("pre init côté tout");
	    }
	 
	 	public void init()
	    {
	 
	    }
}
