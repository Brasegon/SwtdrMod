package fr.brangers.swtdrmod.entity;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.passive.EntityCow;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.init.MobEffects;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.datafix.DataFixer;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class EntityBalls extends EntityThrowable {
	public EntityBalls(World worldIn)
    {
        super(worldIn);
    }

    public EntityBalls(World worldIn, EntityLivingBase throwerIn)
    {
        super(worldIn, throwerIn);
    }

    public EntityBalls(World worldIn, double x, double y, double z)
    {
        super(worldIn, x, y, z);
    }

    /**
     * Called when this EntityThrowable hits a block or entity.
     */
    @Override
	protected void onImpact(RayTraceResult result) 
	{
		if(!this.world.isRemote)
		{
			setDead();
			if(result.entityHit instanceof EntityLivingBase)
			{
				EntityLivingBase entity = (EntityLivingBase)result.entityHit;	
				entity.attackEntityFrom(DamageSource.causeThrownDamage(this, this.thrower), rand.nextFloat());
				if(entity instanceof EntityCow)
				{
					entity.setGlowing(true);
					entity.setFire(5);
					entity.moveForward = 1.0F;
				}
				else
				{
					entity.addPotionEffect(new PotionEffect(MobEffects.LEVITATION, 1200, 3));
				}
			}
		}
	}
}
