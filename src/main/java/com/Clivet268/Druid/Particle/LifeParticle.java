package com.Clivet268.Druid.Particle;

import com.Clivet268.Druid.Druid;
import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.IParticleFactory;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.entity.Entity;
import net.minecraft.particles.BasicParticleType;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nullable;

@OnlyIn(Dist.CLIENT)
public class LifeParticle extends Particle {
    private final Vec3d target;
    private float rot;
    public enum LifeType{
        REGROWTH("regrow"),
        NEW_GROWTH("new_growth"),
        GROW("grow");
        private final String names;
        LifeType(String name) {
            this.names=name;
        }
        public String getParticleName()
        {
            return this.names;
        }
    }
    public LifeType ttype;

    public static final ResourceLocation REGROW = new ResourceLocation(Druid.MODID, "particles/regrow");

    public LifeParticle(World world, double x, double y, double z, double vx, double vy, double vz, LifeType teype) {
        this(world, x, y, z, vx, vy, vz, 1.0F,teype);
    }

    public LifeParticle(World world, double x, double y, double z, double vx, double vy, double vz, float scale, LifeType teype) {
        super(world, x, y, z, 0.0D, 0.0D, 0.0D);
        target = new Vec3d(x, y, z);
        this.motionX *= 0.10000000149011612D;
        this.motionY *= 0.10000000149011612D;
        this.motionZ *= 0.10000000149011612D;
        this.motionX += vx * 0.4D;
        this.motionY += vy * 0.4D;
        this.motionZ += vz * 0.4D;
        this.particleRed = this.particleGreen = this.particleBlue = 1.0F;
        this.particleAlpha = 0F;
        this.particleScale *= 4.0F;
        this.particleScale *= scale;
        this.maxAge = 90 + ((int) (rand.nextFloat() * 30F));
        this.maxAge = (int) ((float) this.maxAge * scale);
        this.canCollide = true;
        this.ttype = teype;


        this.particleTexture = Minecraft.getInstance().getTextureMap().getAtlasSprite(REGROW.toString());

    }

    @Override
    public void tick() {
        this.prevPosX = this.posX;
        this.prevPosY = this.posY;
        this.prevPosZ = this.posZ;

        if (this.age++ >= this.maxAge) {
            this.setExpired();
        }
        this.move(this.motionX, this.motionY, this.motionZ);

        this.motionY+= 0.0001D / (((double)this.age)/4.5D);

        if (this.onGround) {
            this.motionX *= 0.699999988079071D;
            this.motionZ *= 0.699999988079071D;
        } else {
            rot += 5F;
            if (motionX == 0)
                motionX += (rand.nextBoolean() ? 1 : -1) * 0.001F;
            if (motionZ == 0)
                motionZ += (rand.nextBoolean() ? 1 : -1) * 0.001F;
            if (rand.nextInt(5) == 0)
                motionX += Math.signum(target.x - posX) * rand.nextFloat() * 0.005F;
            if (rand.nextInt(5) == 0)
                motionZ += Math.signum(target.z - posZ) * rand.nextFloat() * 0.005F;
        }
    }

    @Override
    public void renderParticle(BufferBuilder buffer, Entity entityIn, float partialTicks, float rotationX, float rotationZ, float rotationYZ, float rotationXY, float rotationXZ) {
        particleAlpha = Math.min(MathHelper.clamp(age, 0, 20) / 20F, MathHelper.clamp(maxAge - age, 0, 20) / 20F);
        super.renderParticle(buffer, entityIn, partialTicks, rotationX, rotationZ, rotationYZ, rotationXY, rotationXZ);
    }

    @Override
    public int getBrightnessForRender(float partialTicks) {
        return 240 | 240 << 16;
    }

    @Override
    public int getFXLayer() {
        return 1;
    }



    @OnlyIn(Dist.CLIENT)
    public static class RegrowthFactory implements IParticleFactory<BasicParticleType>
    {
        @Nullable
        @Override
        public Particle makeParticle(BasicParticleType typeIn, World worldIn, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed) {
            return new LifeParticle(worldIn, x, y, z, xSpeed, ySpeed, zSpeed, LifeType.REGROWTH);

        }
    }/*
    @OnlyIn(Dist.CLIENT)
    public static class NewGrowthFactory implements IParticleFactory
    {
        public Particle createParticle(int particleID, World worldIn, double xCoordIn, double yCoordIn, double zCoordIn, double xSpeedIn, double ySpeedIn, double zSpeedIn, int... p_178902_15_)
        {
            return new LifeParticle(worldIn, xCoordIn, yCoordIn, zCoordIn, xSpeedIn, ySpeedIn, zSpeedIn, LifeType.NEW_GROWTH);
        }
    }
    @OnlyIn(Dist.CLIENT)
    public static class GrowFactory implements IParticleFactory
    {
        public Particle createParticle(int particleID, World worldIn, double xCoordIn, double yCoordIn, double zCoordIn, double xSpeedIn, double ySpeedIn, double zSpeedIn, int... p_178902_15_)
        {
            return new LifeParticle(worldIn, xCoordIn, yCoordIn, zCoordIn, xSpeedIn, ySpeedIn, zSpeedIn, LifeType.GROW);
        }
    }
    */
}