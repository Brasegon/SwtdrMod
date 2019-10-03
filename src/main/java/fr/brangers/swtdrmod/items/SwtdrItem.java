package fr.brangers.swtdrmod.items;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraftforge.registries.IForgeRegistry;

public class SwtdrItem {
	
	public static final ItemBalls test = new ItemBalls("magiball").setCreativeTab(CreativeTabs.BUILDING_BLOCKS);
	
	public static void register(IForgeRegistry<Item> registry) {
		registry.registerAll(test);
	}
	
	public static void registerModels() {
		test.registerItemModel();
	}
}
