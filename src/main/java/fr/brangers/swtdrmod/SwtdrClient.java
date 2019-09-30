package fr.brangers.swtdrmod;

import java.io.File;

import fr.brangers.swtdrmod.event.ClientEvent;
import net.minecraftforge.common.MinecraftForge;

public class SwtdrClient extends SwtdrCommon
{
	 @Override
	    public void preInit(File configFile)
	    {
	        // TODO Auto-generated method stub
	        super.preInit(configFile);
	        System.out.println("pre init côté client");
	    }
	 
	    @Override
	    public void init()
	    {
	        // TODO Auto-generated method stub
	        super.init();
	        MinecraftForge.EVENT_BUS.register(new ClientEvent());
	    }
}
