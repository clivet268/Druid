package com.Clivet268.Druid.Particle;

import net.minecraft.client.particle.*;
import net.minecraft.particles.BasicParticleType;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import static com.Clivet268.Druid.Druid.MODID;

//TODO use the animated or better static one?
@OnlyIn(Dist.CLIENT)
public class RevivalParticle extends SpriteTexturedParticle {
    private final Vec3d target;

    public static final ResourceLocation REGROW = new ResourceLocation(MODID, "particles/regrow");

    public RevivalParticle(World world, double x, double y, double z, double vx, double vy, double vz) {
        this(world, x, y, z, vx, vy, vz, 1.0F);
    }

    public RevivalParticle(World world, double x, double y, double z, double vx, double vy, double vz, float scale) {
        super(world, x, y, z, 0.0D, 0.0D, 0.0D);
        target = new Vec3d(x, y, z);
        this.motionX *= 0.2;
        this.motionY *= 0.2;
        this.motionZ *= 0.2;
        this.motionX += vx * 0.4D;
        this.motionY += vy * 0.4D;
        this.motionZ += vz * 0.4D;
        this.particleRed = this.particleGreen = this.particleBlue = 1.0F;
        this.particleAlpha = 0F;
        this.maxAge = 90 + ((int) (rand.nextFloat() * 30F));
        this.maxAge = (int) ((float) this.maxAge * scale);
        this.canCollide = true;

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

        this.motionY += 0.0001D / (((double) this.age) / 4.5D);

        if (this.onGround) {
            this.motionX *= 0.7D;
            this.motionZ *= 0.7D;
        } else {
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
    public IParticleRenderType getRenderType() {
        return IParticleRenderType.PARTICLE_SHEET_OPAQUE;
    }


    @Override
    public int getBrightnessForRender(float partialTicks) {
        return 240 | 240 << 16;
    }


    @OnlyIn(Dist.CLIENT)
    public static class RegrowthFactory implements IParticleFactory<BasicParticleType> {
        private final IAnimatedSprite spriteSet;

        public RegrowthFactory(IAnimatedSprite p_i50495_1_) {
            this.spriteSet = p_i50495_1_;
        }

        public Particle makeParticle(BasicParticleType typeIn, World worldIn, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed) {

            RevivalParticle revivalParticle = new RevivalParticle(worldIn, x, y, z, xSpeed, ySpeed, zSpeed);
            revivalParticle.selectSpriteRandomly(this.spriteSet);
            return revivalParticle;
        }
    }

    //TODO how will I need this directly, as im pretty sure future versions implement this differently? ?
    //public static class LifeParticleData implements IParticleData{}
}