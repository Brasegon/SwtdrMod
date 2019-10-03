package fr.brangers.swtdrmod.items;

import fr.brangers.swtdrmod.swtdrMod;
import fr.brangers.swtdrmod.entity.EntityBalls;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntitySnowball;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatList;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class ItemBalls extends Item {
	protected String name;
	
	public ItemBalls(String name)
    {
		this.name = name;
		setUnlocalizedName(name);
		setRegistryName(name);
    }
	
	public void registerItemModel() {
		swtdrMod.proxy.registerItemRenderer(this, 0, name);
	}
	
	@Override
	public ItemBalls setCreativeTab(CreativeTabs tab) {
		super.setCreativeTab(tab);
		return this;
	}
    /**
     * Called when the equipped item is right clicked.
     */
	public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) 
	{
		ItemStack stack = playerIn.getHeldItem(handIn);
		Vec3d look = playerIn.getLookVec();
		EntityBalls magiball = new EntityBalls(worldIn, 1.0D, 1.0D, 1.0D);
		magiball.setPosition(playerIn.posX + look.x * 1.5D, playerIn.posY + look.y * 1.5D, playerIn.posZ + look.z * 1.5D);
		magiball.shoot(playerIn, playerIn.rotationPitch, playerIn.rotationYaw, 0.0F, 1.5F, 0.5F);
		if(!worldIn.isRemote)
		{
			worldIn.spawnEntity(magiball);
		}
		worldIn.playSound(null, playerIn.getPosition(), SoundEvents.ENTITY_EGG_THROW, SoundCategory.PLAYERS, 1.0F, 1.0F);
		if(!playerIn.capabilities.isCreativeMode)
		{
			stack.shrink(1);
		}
		return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, stack);
	}
}
