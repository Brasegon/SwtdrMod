package fr.brangers.swtdrmod.gui;

import java.io.IOException;

import org.lwjgl.input.Keyboard;

import fr.brangers.swtdrmod.SwtdrCommon;
import fr.brangers.swtdrmod.swtdrMod;
import fr.brangers.swtdrmod.blocks.tileEntity.TileEntityForge;
import fr.brangers.swtdrmod.container.ContainerForge;
import fr.brangers.swtdrmod.network.MyMessage;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiOptions;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraft.util.ResourceLocation;

public class GuiForge extends GuiContainer{

	private static final ResourceLocation FURNACE_GUI_TEXTURES = new ResourceLocation(swtdrMod.MODID, "textures/gui/forge.png");
    /** The player inventory bound to this GUI. */
    private final InventoryPlayer playerInventory;
    private final IInventory tileFurnace;
    public boolean clicked = false;

    public GuiForge(InventoryPlayer playerInv, IInventory furnaceInv)
    {
        super(new ContainerForge(playerInv, furnaceInv));
        this.playerInventory = playerInv;
        this.tileFurnace = furnaceInv;
    }
    @Override
	public void initGui() {
		super.initGui();
		this.guiLeft = (this.width - 176) / 2;
		this.guiTop = (this.height - 166) / 2;
		Keyboard.enableRepeatEvents(true);
		this.buttonList.clear();
		this.buttonList.add(new GuiButton(0, this.guiLeft + 69, this.guiTop + 49, 40, 20, "test"));
	}
    public void updateScreen()
    {
    }
    protected void actionPerformed(GuiButton button) throws IOException
    {
        if (button.id == 0)
        {
        	SwtdrCommon.network.sendToServer(new MyMessage("Salut"));
        }
    }
    public void drawScreen(int mouseX, int mouseY, float partialTicks)
    {
        this.drawDefaultBackground();
        super.drawScreen(mouseX, mouseY, partialTicks);
        this.renderHoveredToolTip(mouseX, mouseY);
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
	@Override
	public void onGuiClosed() {
		super.onGuiClosed();
		Keyboard.enableRepeatEvents(false);
	}


}
