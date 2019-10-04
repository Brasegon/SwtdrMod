package fr.brangers.swtdrmod.container;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.inventory.SlotFurnaceOutput;

public class ContainerForge extends Container{

	private final IInventory tileFurnace;
	private int cookTime, totalCookTime, ovenBurnTime, currentItemBurnTime;
	
	public ContainerForge(InventoryPlayer playerInventory, IInventory furnaceInventory) 
	{
		this.tileFurnace = furnaceInventory;
		
		this.addSlotToContainer(new Slot(furnaceInventory, 0, 56, 17));
		this.addSlotToContainer(new Slot(furnaceInventory, 1, 56, 53));
		this.addSlotToContainer(new SlotFurnaceOutput(playerInventory.player, furnaceInventory, 2, 116, 35));
		for(int i = 0; i < 3; i++)
		{
			for(int j = 0; j < 9; ++j)
			{
				this.addSlotToContainer(new Slot(playerInventory, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
			}
		}
		
		for(int k = 0; k < 9; k++)
		{
			this.addSlotToContainer(new Slot(playerInventory, k, 8 + k * 18, 142));
		}
	}

	@Override
	public boolean canInteractWith(EntityPlayer playerIn) {
		// TODO Auto-generated method stub
		return  this.tileFurnace.isUsableByPlayer(playerIn);
	}

}
