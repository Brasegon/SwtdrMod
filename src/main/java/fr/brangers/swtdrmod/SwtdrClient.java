package fr.brangers.swtdrmod;

import java.io.File;

import fr.brangers.swtdrmod.entity.EntityBalls;
import fr.brangers.swtdrmod.event.gui.GuiEvent;
import fr.brangers.swtdrmod.items.SwtdrItem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.entity.RenderSnowball;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.client.registry.RenderingRegistry;

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
	        MinecraftForge.EVENT_BUS.register(new GuiEvent());
	        
	        RenderingRegistry.registerEntityRenderingHandler(EntityBalls.class, new RenderSnowball<EntityBalls>(Minecraft.getMinecraft().getRenderManager(), SwtdrItem.test, Minecraft.getMinecraft().getRenderItem()));
	        
	    }
	    @Override
	    public void registerItemRenderer(Item item, int meta, String id) {
	    	ModelLoader.setCustomModelResourceLocation(item, meta, new ModelResourceLocation(swtdrMod.MODID + ":" + id, "inventory"));
	    }
}
