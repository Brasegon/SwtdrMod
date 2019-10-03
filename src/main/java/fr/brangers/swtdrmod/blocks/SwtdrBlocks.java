package fr.brangers.swtdrmod.blocks;


import fr.brangers.swtdrmod.blocks.tileEntity.TileCustomFurnace;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.registries.IForgeRegistry;


public class SwtdrBlocks {
	public static BlockBase oreCopper = new BlockBase(Material.CACTUS, "ore_copper").setCreativeTab(CreativeTabs.MATERIALS);
	public static BlockFurnace TEST = new BlockFurnace(Material.CACTUS, "furnacator");
	public static void register(IForgeRegistry<Block> registry) {
		registry.registerAll(
				oreCopper,
				TEST
		);
		GameRegistry.registerTileEntity(TileCustomFurnace.class, TEST.getRegistryName().toString());
	}
	
	public static void registerItemBlocks(IForgeRegistry<Item> registry) {
		registry.registerAll(
				oreCopper.createItemBlock(),
				TEST.createItemBlock()
		);
	}
	
	public static void registerModels() {
		oreCopper.registerItemModel(Item.getItemFromBlock(oreCopper));
		TEST.registerItemModel(Item.getItemFromBlock(TEST));
	}
}
