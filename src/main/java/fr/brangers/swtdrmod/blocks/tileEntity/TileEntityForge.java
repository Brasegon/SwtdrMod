package fr.brangers.swtdrmod.blocks.tileEntity;

import fr.brangers.swtdrmod.swtdrMod;
import fr.brangers.swtdrmod.event.gui.TestContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.inventory.SlotFurnaceFuel;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraft.tileentity.TileEntityLockable;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class TileEntityForge extends TileEntityLockable implements ITickable, ISidedInventory{
	
	private NonNullList<ItemStack> forgeItemStacks = NonNullList.<ItemStack>withSize(3, ItemStack.EMPTY);
	private int totalTimeForged;
	private int timeForged;
	public static boolean openButton = false;
	@Override
	public int getSizeInventory() {
		// TODO Auto-generated method stub
		return this.forgeItemStacks.size();
	}

	@Override
	public boolean isEmpty() {
		for (ItemStack itemstack : this.forgeItemStacks)
        {
            if (!itemstack.isEmpty())
            {
                return false;
            }
        }

        return true;
	}
	public void readFromNBT(NBTTagCompound compound)
    {
        super.readFromNBT(compound);
        this.forgeItemStacks = NonNullList.<ItemStack>withSize(this.getSizeInventory(), ItemStack.EMPTY);
        ItemStackHelper.loadAllItems(compound, this.forgeItemStacks);
        
    }
	public NBTTagCompound writeToNBT(NBTTagCompound compound)
    {
        super.writeToNBT(compound);
        ItemStackHelper.saveAllItems(compound, this.forgeItemStacks);
        return compound;
    }
	@Override
	public ItemStack getStackInSlot(int index) {
		return this.forgeItemStacks.get(index);
	}

	@Override
	public ItemStack decrStackSize(int index, int count) {
		return ItemStackHelper.getAndSplit(this.forgeItemStacks, index, count);
	}

	@Override
	public ItemStack removeStackFromSlot(int index) {
		return ItemStackHelper.getAndRemove(this.forgeItemStacks, index);
	}

	@Override
	public void setInventorySlotContents(int index, ItemStack stack) {
		ItemStack itemstack = this.forgeItemStacks.get(index);
        boolean flag = !stack.isEmpty() && stack.isItemEqual(itemstack) && ItemStack.areItemStackTagsEqual(stack, itemstack);
        this.forgeItemStacks.set(index, stack);

        if (stack.getCount() > this.getInventoryStackLimit())
        {
            stack.setCount(this.getInventoryStackLimit());
        }
		
	}

	@Override
	public int getInventoryStackLimit() {
		// TODO Auto-generated method stub
		return 64;
	}

	@Override
	public boolean isUsableByPlayer(EntityPlayer player) {
		// TODO Auto-generated method stub
		 if (this.world.getTileEntity(this.pos) != this)
	        {
	            return false;
	        }
	        else
	        {
	            return player.getDistanceSq((double)this.pos.getX() + 0.5D, (double)this.pos.getY() + 0.5D, (double)this.pos.getZ() + 0.5D) <= 64.0D;
	        }
	}

	@Override
	public void openInventory(EntityPlayer player) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void closeInventory(EntityPlayer player) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isItemValidForSlot(int index, ItemStack stack) {
		if (index == 2)
        {
            return false;
        }
        else if (index != 1)
        {
            return true;
        }
        else
        {
        	return true;
        }
	}

	@Override
	public int getField(int id) {
		switch (id)
        {
            case 0:
                return this.totalTimeForged;
            case 1:
                return this.timeForged;
            default:
                return 0;
        }
	}

	@Override
	public void setField(int id, int value) {
		switch (id)
        {
            case 0:
                this.totalTimeForged = value;
                break;
            case 1:
                this.timeForged = value;
                break;
        }
	}

	@Override
	public int getFieldCount() {
		// TODO Auto-generated method stub
		return 2;
	}

	@Override
	public void clear() {
		 this.forgeItemStacks.clear();
		
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return swtdrMod.MODID + ":" + "container.forge";
	}

	@Override
	public boolean hasCustomName() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Container createContainer(InventoryPlayer playerInventory, EntityPlayer playerIn)
    {
        return new TestContainer(playerInventory, this);
    }
	
	@Override
	public String getGuiID() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int[] getSlotsForFace(EnumFacing side) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean canInsertItem(int index, ItemStack itemStackIn, EnumFacing direction) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean canExtractItem(int index, ItemStack stack, EnumFacing direction) {
		// TODO Auto-generated method stub
		return false;
	}
	private boolean canForge()
    {
		
        if (((ItemStack)this.forgeItemStacks.get(0)).isEmpty())
        {
            return false;
        }
        else
        {
            ItemStack itemstack = FurnaceRecipes.instance().getSmeltingResult(this.forgeItemStacks.get(0));

            if (itemstack.isEmpty())
            {
                return false;
            }
            else
            {
                ItemStack itemstack1 = this.forgeItemStacks.get(2);

                if (itemstack1.isEmpty())
                {
                    return true;
                }
                else if (!itemstack1.isItemEqual(itemstack))
                {
                    return false;
                }
                else if (itemstack1.getCount() + itemstack.getCount() <= this.getInventoryStackLimit() && itemstack1.getCount() + itemstack.getCount() <= itemstack1.getMaxStackSize())  // Forge fix: make furnace respect stack sizes in furnace recipes
                {
                    return true;
                }
                else
                {
                    return itemstack1.getCount() + itemstack.getCount() <= itemstack.getMaxStackSize(); // Forge fix: make furnace respect stack sizes in furnace recipes
                }
            }
        }
    }
	@Override
	public void update() {
		// TODO Auto-generated method stub
		if (!this.world.isRemote) {
			if (!this.forgeItemStacks.get(0).isEmpty()) {
				this.openButton = true;
			}
			else {
				this.openButton = false;
			}
			if (canForge()) {
				ItemStack itemstack = this.forgeItemStacks.get(0);
				ItemStack itemstack1 = FurnaceRecipes.instance().getSmeltingResult(itemstack);
				ItemStack itemstack2 = this.forgeItemStacks.get(2);
				
				if (itemstack2.isEmpty())
	            {
	                this.forgeItemStacks.set(2, itemstack1.copy());
	            }
				else {
					if (itemstack2.getItem() == itemstack1.getItem())
		            {
		                itemstack2.grow(itemstack1.getCount());
		            }
				}
				itemstack.shrink(1);
				}
		}
	}

}
