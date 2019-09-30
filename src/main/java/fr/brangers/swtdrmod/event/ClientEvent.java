package fr.brangers.swtdrmod.event;

import fr.brangers.swtdrmod.swtdrMod;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiMainMenu;
import net.minecraftforge.client.event.GuiOpenEvent;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent;

public class ClientEvent {
	@SubscribeEvent
	public void RenderGameOverlayEvent(RenderGameOverlayEvent.Pre event)
    {
		if (event.getType() == RenderGameOverlayEvent.ElementType.DEBUG) {
			Minecraft mc = Minecraft.getMinecraft();
			event.setCanceled(true);
			this.drawString(Minecraft.getMinecraft().fontRenderer, mc.debug.split(",", 2)[0], 10, 50, 0xFF0000);
	
		}
    }
	public void drawString(FontRenderer fontRenderer, String str, int x, int y, int color)
	   {
	       fontRenderer.drawStringWithShadow(str, x, y, color);
	    }
}
