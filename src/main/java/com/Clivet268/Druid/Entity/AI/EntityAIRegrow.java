package com.Clivet268.Druid.Entity.AI;

import com.Clivet268.Druid.Entity.DruidEntity;
import com.Clivet268.Druid.Particle.LifeParticle;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.HashMap;
import java.util.Random;

import static com.Clivet268.Druid.Druid.logger;

public class EntityAIRegrow extends EntityAIBase
{
    private final EntityCreature druid;
    private final World entityWorld;
    int eatingGrassTimer;
    private BlockPos nearbysans = null;
    HashMap<Block,Block> bloks = new HashMap<>();

    public EntityAIRegrow(EntityCreature grassEaterEntityIn)
    {
        super();
        this.druid = grassEaterEntityIn;
        this.entityWorld = grassEaterEntityIn.world;
        this.setMutexBits(7);
        this.setRegrowth();

    }
    public void setRegrowth(){
        bloks.put(Blocks.DIRT,Blocks.GRASS);
        bloks.put(Blocks.SAND,Blocks.DIRT);
        bloks.put(Blocks.SNOW_LAYER, Blocks.TALLGRASS);
        bloks.put(Blocks.DEADBUSH, Blocks.TALLGRASS);
    }

    @Override
    public boolean shouldExecute()
    {
        int zhongmian = 1200;

        for(int i = -1; i <= 1; i++){
            for(int ii = -1; ii <= 1; ii++){
                if(this.entityWorld.getBlockState(this.druid.getPosition().add(i,-1,ii)).getBlock().getDefaultState().getBlock() == Blocks.SAND){
                    zhongmian -= 105;
                }
            }
        }
        //System.out.println(zhongmian);
        if (this.druid.getRNG().nextInt(zhongmian) != 0)
        {
            //System.out.println((((DruidEntity)this.grassEaterEntity).getBrews()));
            return false;
        }
        else
        {
            BlockPos neargrowable = this.findBlockRegrow(8);
            if (neargrowable != null) {
                System.out.println("epic3");
                this.nearbysans = neargrowable;
                return ((DruidEntity) this.druid).getBrews() > 0 ;
            }

            return false;

        }
    }
    @Override
    public void startExecuting()
    {

        logger.info("yeet");
        this.eatingGrassTimer = 40;
        this.entityWorld.setEntityState(this.druid, (byte)10);

    }
    @Override
    public void resetTask()
    {
        this.nearbysans = null;
        this.eatingGrassTimer = 0;
    }

    @Override
    public boolean shouldContinueExecuting()
    {

        return (!this.druid.getNavigator().noPath()) && this.eatingGrassTimer > 0 && this.bloks.containsKey(this.entityWorld.getBlockState(nearbysans).getBlock());

    }

    public int getEatingGrassTimer()
    {
        return this.eatingGrassTimer;
    }

    @Override
    public void updateTask() {
        if (this.druid.getDistanceSq(nearbysans) <= 4.0D) {
            logger.info("yHet");
            this.eatingGrassTimer = Math.max(0, this.eatingGrassTimer - 1);

            //if (this.eatingGrassTimer == 4) {
                logger.info("yreet");
                /*
                BlockPos blockpos = new BlockPos(this.druid.posX, this.druid.posY, this.druid.posZ);
                BlockPos blockpos1 = blockpos.down();

                if (this.bloks.containsKey(this.entityWorld.getBlockState(blockpos1).getBlock())) {

                 */
                    logger.info("meet");
                    if (net.minecraftforge.event.ForgeEventFactory.getMobGriefingEvent(this.entityWorld, this.druid))
                        ((DruidEntity) (this.druid)).brew();
                    ((DruidEntity) (this.druid)).setBrews(((DruidEntity) (this.druid)).getBrews() - 1);
                    this.entityWorld.setBlockState(nearbysans, bloks.get(this.entityWorld.getBlockState(nearbysans).getBlock()).getDefaultState(), 3);
                    for (int i = new Random().nextInt(12); i >= 0; i--) {
                        Minecraft.getMinecraft().effectRenderer.addEffect(new LifeParticle(this.entityWorld, this.druid.posX, this.druid.posY, this.druid.posZ, (new Random().nextDouble()-0.5)/3, 0, (new Random().nextDouble()-0.3)/3, LifeParticle.LifeType.REGROWTH));
                    }
                    this.druid.getNavigator().clearPath();
                    //this.grassEaterEntity.eatGrassBonus();
                //}

        }
        else{
            System.out.println("AAAAAAAAAA " + this.druid.getNavigator().tryMoveToXYZ(this.nearbysans.getX(), this.nearbysans.getY(), this.nearbysans.getZ(), 0.6) + " " + this.nearbysans);
            this.druid.getNavigator().tryMoveToXYZ(this.nearbysans.getX(), this.nearbysans.getY(), this.nearbysans.getZ(), 0.6);
        }
    }
    public BlockPos findBlockRegrow(int range) {
        BlockPos entityPos = new BlockPos(druid);

        for (int x = -range; x <= range; x++) {
            for (int y = -range; y <= range; y++) {
                for (int z = -range; z <= range; z++) {
                    if (this.bloks.containsKey(druid.world.getBlockState(entityPos.add(x, y, z)).getBlock())) {
                        if(this.entityWorld.getBlockState(entityPos.add(x, y, z).up()).getBlock() == Blocks.AIR){
                            return entityPos.add(x, y, z);
                        }

                    }
                }
            }
        }

        return null;
    }


}


