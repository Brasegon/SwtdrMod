package fr.brangers.swtdrmod.blocks;

import java.util.Random;

import fr.brangers.swtdrmod.swtdrMod;
import fr.brangers.swtdrmod.blocks.tileEntity.TileCustomFurnace;
import net.minecraft.block.BlockContainer;
import fr.brangers.swtdrmod.blocks.tileEntity.TileEntityForge;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockFurnace extends BlockContainer {

	private static boolean keepInventory;
	private String name;
	public BlockFurnace(Material material, String name) {
		super(material);
		
		this.name = name;
	
		setUnlocalizedName(name);
		setRegistryName(name);
	}
	public Item getItemDropped(IBlockState state, Random rand, int fortune)
    {
        return Item.getItemFromBlock(SwtdrBlocks.TEST);
    }
	public void breakBlock(World worldIn, BlockPos pos, IBlockState state)
    {
            TileEntity tileentity = worldIn.getTileEntity(pos);

            if (tileentity instanceof TileEntityForge)
            {
                InventoryHelper.dropInventoryItems(worldIn, pos, (TileEntityForge)tileentity);
                worldIn.updateComparatorOutputLevel(pos, this);
            }
        

        super.breakBlock(worldIn, pos, state);
    }
	@Override
	public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
	    if (world.isRemote) {
	        return true;
	    } else {
	        TileEntity tileentity = world.getTileEntity(pos);
	 
	        if (tileentity instanceof TileEntityForge) {
	            player.openGui(swtdrMod.instance, 0, world, pos.getX(),
	                    pos.getY(), pos.getZ());
	        }
	 
	        return true;
	    }
	}

	public void registerItemModel(Item itemBlock) {
		swtdrMod.proxy.registerItemRenderer(itemBlock, 0, name);
	}
	
	public Item createItemBlock() {
		return new ItemBlock(this).setRegistryName(getRegistryName());
	}
	@Override
	public boolean hasTileEntity() {
	    return true;
	}
	 
	@Override
	public TileEntity createNewTileEntity(World world, int metadata)  {
	    return new TileEntityForge();
	}

}
