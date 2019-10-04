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
	public static BlockFurnace TEST = new BlockFurnace(Material.CACTUS, "furnacator");
	
	public static BlockBase DURACIER_ORE = new BlockBase(Material.IRON, "duracierOre");
	public static BlockBase DURACIER_BLOCK = new BlockBase(Material.IRON, "duracierBlock");
	public static BlockBase PLASTACIER_BLOCK = new BlockBase(Material.IRON, "plastacierBlock");
	
	public static void register(IForgeRegistry<Block> registry) {
		registry.registerAll(
				DURACIER_ORE,
				DURACIER_BLOCK,
				PLASTACIER_BLOCK,
//				oreCopper,
				TEST
		);
//		GameRegistry.registerTileEntity(TileCustomFurnace.class, TEST.getRegistryName().toString());
	}
	
	public static void registerItemBlocks(IForgeRegistry<Item> registry) {
		registry.registerAll(
//				oreCopper.createItemBlock(),
				TEST.createItemBlock(),
				DURACIER_ORE.createItemBlock(),
				DURACIER_BLOCK.createItemBlock(),
				PLASTACIER_BLOCK.createItemBlock()
		);
	}
	
	public static void registerModels() {
//		oreCopper.registerItemModel(Item.getItemFromBlock(oreCopper));
		TEST.registerItemModel(Item.getItemFromBlock(TEST));
		DURACIER_ORE.registerItemModel(Item.getItemFromBlock(DURACIER_ORE));
		DURACIER_BLOCK.registerItemModel(Item.getItemFromBlock(DURACIER_BLOCK));
		PLASTACIER_BLOCK.registerItemModel(Item.getItemFromBlock(PLASTACIER_BLOCK));
	}
}
