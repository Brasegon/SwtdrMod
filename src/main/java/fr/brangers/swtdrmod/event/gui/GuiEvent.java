package fr.brangers.swtdrmod.event.gui;

import fr.brangers.swtdrmod.gui.GuiCustomIngameMenu;
import fr.brangers.swtdrmod.gui.GuiCustomMainMenu;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiDisconnected;
import net.minecraft.client.gui.GuiIngameMenu;
import net.minecraft.client.gui.GuiMainMenu;
import net.minecraft.client.gui.inventory.GuiInventory;
import net.minecraft.client.renderer.InventoryEffectRenderer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.client.event.GuiContainerEvent;
import net.minecraftforge.client.event.GuiOpenEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class GuiEvent {
	@SubscribeEvent
	public void RenderGameOverlayEvent(RenderGameOverlayEvent.Pre event)
    {
		if (event.getType() == RenderGameOverlayEvent.ElementType.DEBUG) {
			Minecraft mc = Minecraft.getMinecraft();
			String[] test = mc.debug.split("\\(");
			EntityPlayer player = mc.player;
			BlockPos pos = player.getPosition();
			String playerPos = " x=" + pos.getX() + " y=" + pos.getY() + " z=" + pos.getZ();
			event.setCanceled(true);
			this.drawString(Minecraft.getMinecraft().fontRenderer, test[0] + playerPos , 0, 20, 0xFFFFFF);
			this.drawString(Minecraft.getMinecraft().fontRenderer, "SWTDR v1", 0, 5, 0xFFFFFF);
		}
    }
	public void drawString(FontRenderer fontRenderer, String str, int x, int y, int color)
	   {
	       fontRenderer.drawStringWithShadow(str, x, y, color);
	    }
	@SubscribeEvent
    public void onOpenGui(GuiOpenEvent event)
    {
        if(event.getGui() != null && event.getGui().getClass() == GuiMainMenu.class)
        {
            event.setGui(new GuiCustomMainMenu());
        }
        if(event.getGui() != null && event.getGui().getClass() == GuiIngameMenu.class)
        {
            event.setGui(new GuiCustomIngameMenu());
        }
    }
}
