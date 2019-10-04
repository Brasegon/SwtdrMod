package fr.brangers.swtdrmod.gui;

import fr.brangers.swtdrmod.container.ContainerForge;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraft.util.ResourceLocation;

public class GuiForge extends GuiContainer{

	private static final ResourceLocation FURNACE_GUI_TEXTURES = new ResourceLocation("textures/gui/container/furnace.png");
    /** The player inventory bound to this GUI. */
    private final InventoryPlayer playerInventory;
    private final IInventory tileFurnace;

    public GuiForge(InventoryPlayer playerInv, IInventory furnaceInv)
    {
        super(new ContainerForge(playerInv, furnaceInv));
        this.playerInventory = playerInv;
        this.tileFurnace = furnaceInv;
    }
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY)
    {
        String s = this.tileFurnace.getDisplayName().getUnformattedText();
        this.fontRenderer.drawString(s, this.xSize / 2 - this.fontRenderer.getStringWidth(s) / 2, 6, 4210752);
        this.fontRenderer.drawString(this.playerInventory.getDisplayName().getUnformattedText(), 8, this.ySize - 96 + 2, 4210752);
    }
	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
		GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        this.mc.getTextureManager().bindTexture(FURNACE_GUI_TEXTURES);
        int i = (this.width - this.xSize) / 2;
        int j = (this.height - this.ySize) / 2;
        this.drawTexturedModalRect(i, j, 0, 0, this.xSize, this.ySize);
		
	}

}
