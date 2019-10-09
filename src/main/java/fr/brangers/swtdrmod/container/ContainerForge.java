package fr.brangers.swtdrmod.container;

import fr.brangers.swtdrmod.blocks.tileEntity.TileCustomFurnace;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.inventory.SlotFurnaceOutput;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;

public class ContainerForge extends Container{

	private final IInventory tileFurnace;
	private int cookTime, totalCookTime, ovenBurnTime, currentItemBurnTime;
	
	public ContainerForge(InventoryPlayer playerInventory, IInventory furnaceInventory) 
	{
		this.tileFurnace = furnaceInventory;
		
		this.addSlotToContainer(new Slot(furnaceInventory, 0, 17, 55));
		this.addSlotToContainer(new Slot(furnaceInventory, 1, 17, 30));
		this.addSlotToContainer(new SlotFurnaceOutput(playerInventory.player, furnaceInventory, 2, 63, 40));
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
	public ItemStack transferStackInSlot(EntityPlayer playerIn, int index) 
	{
		ItemStack itemstack = ItemStack.EMPTY;
        Slot slot = this.inventorySlots.get(index);

        if (slot != null && slot.getHasStack())
        {
            ItemStack itemstack1 = slot.getStack();
            itemstack = itemstack1.copy();

            if (index == 2)
            {
                if (!this.mergeItemStack(itemstack1, 3, 39, true))
                {
                    return ItemStack.EMPTY;
                }

                slot.onSlotChange(itemstack1, itemstack);
            }
            else if (index != 1 && index != 0)
            {
            		if (!this.mergeItemStack(itemstack1, 0, 2, false))
                    {
                        return ItemStack.EMPTY;
                    }
            		
                if (index >= 3 && index < 30)
                {
                    if (!this.mergeItemStack(itemstack1, 30, 39, false))
                    {
                        return ItemStack.EMPTY;
                    }
                }
                else if (index >= 30 && index < 39 && !this.mergeItemStack(itemstack1, 3, 30, false))
                {
                    return ItemStack.EMPTY;
                }
            }
            else if (!this.mergeItemStack(itemstack1, 3, 39, false))
            {
                return ItemStack.EMPTY;
            }

            if (itemstack1.isEmpty())
            {
                slot.putStack(ItemStack.EMPTY);
            }
            else
            {
                slot.onSlotChanged();
            }

            if (itemstack1.getCount() == itemstack.getCount())
            {
                return ItemStack.EMPTY;
            }

            slot.onTake(playerIn, itemstack1);
        }

        return itemstack;
    }
	@Override
	public boolean canInteractWith(EntityPlayer playerIn) {
		// TODO Auto-generated method stub
		return  this.tileFurnace.isUsableByPlayer(playerIn);
	}

}
