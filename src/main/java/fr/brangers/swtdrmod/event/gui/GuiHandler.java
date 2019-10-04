package fr.brangers.swtdrmod.event.gui;

import fr.brangers.swtdrmod.blocks.tileEntity.TileCustomFurnace;
import fr.brangers.swtdrmod.blocks.tileEntity.TileEntityForge;
import fr.brangers.swtdrmod.container.ContainerForge;
import fr.brangers.swtdrmod.gui.GuiForge;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

public class GuiHandler implements IGuiHandler{
	public static final int PEDESTAL = 0;
	@Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
    	switch (ID) {
		case PEDESTAL:
			return new ContainerForge(player.inventory, (TileEntityForge)world.getTileEntity(new BlockPos(x, y, z)));
		default:
			return null;
    	}
    }
 
    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
    	switch (ID) {
		case PEDESTAL:
			return new GuiForge(player.inventory, (TileEntityForge)world.getTileEntity(new BlockPos(x, y, z)));
		default:
			return null;
    	}
    }

}
