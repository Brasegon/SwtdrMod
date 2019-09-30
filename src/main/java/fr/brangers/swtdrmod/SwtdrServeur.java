package fr.brangers.swtdrmod;

import java.io.File;

public class SwtdrServeur extends SwtdrCommon
{
	@Override
    public void preInit(File configFile)
    {
        super.preInit(configFile);
        System.out.println("pre init côté serveur");
    }
 
    @Override
    public void init()
    {
        super.init();
    }
}
