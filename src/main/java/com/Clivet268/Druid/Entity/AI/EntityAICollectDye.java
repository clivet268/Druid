package com.Clivet268.Druid.Entity.AI;

import com.Clivet268.Druid.Entity.DruidEntity;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.BlockPos;

import java.util.Random;

import static com.Clivet268.Druid.Druid.logger;
import static net.minecraft.block.BlockCauldron.LEVEL;

//TODO make constant
public class EntityAICollectDye extends EntityAIBase {
    private final DruidEntity druid;
    public BlockPos bebpos =null;
    private boolean got =false;

    public EntityAICollectDye(DruidEntity ocelotIn, double p_i45315_2_) {
        super();
        this.druid = ocelotIn;
    }

    @Override
    public boolean shouldExecute() {
        this.got=false;
        BlockPos nerwater = this.findWater(9);
        if (nerwater != null) {
            this.bebpos = nerwater;
            return this.druid.getRNG().nextInt(350) == 0
                    && this.druid.shouldCollectWater();
        }
        return false;

    }

    @Override
    public void startExecuting() {
        //super.startExecuting();
    }

    @Override
    public void resetTask() {
        this.druid.getNavigator().clearPath();
        this.bebpos = null;
    }
    @Override
    public boolean shouldContinueExecuting()
    {
        if(this.got){
            return false;
        }
        Block block = this.druid.world.getBlockState(this.bebpos).getBlock();
     if (block == Blocks.CAULDRON) {
         IBlockState iBlockState = block.getBlockState().getBaseState();
        if (this.druid.world.getBlockState(bebpos).getValue(LEVEL) >= 1) {
            if (iBlockState.getValue(LEVEL) < 3) {
                logger.info("rerey");
                return true;
            }
        }
        return false;
    }
     else {

         return block==Blocks.WATER;
     }

    }

    @Override
    public void updateTask() {
        //logger.info("yeeit");
        if (this.druid.getDistanceSq(bebpos) < 9.0D) {
            if(this.druid.shouldCollectWater())
                logger.info("te");
                IBlockState iblockstate = this.druid.world.getBlockState(bebpos);
                Block block = iblockstate.getBlock();
                if (block == Blocks.CAULDRON) {
                    if (!(this.druid.world.getBlockState(this.bebpos).getValue(LEVEL) <= 0)) {
                        this.druid.getNavigator().clearPath();
                        this.druid.setWater(druid.getWater() + 1);
                        this.druid.world.setBlockState(this.bebpos, druid.world.getBlockState(this.bebpos).withProperty(LEVEL, iblockstate.getValue(LEVEL) - 1), 3);
                        this.resetTask();
                    }
                }
                else {
                    System.out.println("yes");
                    this.druid.getNavigator().clearPath();
                    this.druid.setWater(druid.getWater() + 1);
                    for (int i = 6 + new Random().nextInt(12); i >= 0; i--) {
                        Minecraft.getMinecraft().effectRenderer.spawnEffectParticle(EnumParticleTypes.WATER_SPLASH.getParticleID(), this.druid.posX, this.druid.posY + 5, this.druid.posZ, new Random(69).nextDouble()/4 * (new Random().nextBoolean() ? 1:-1), new Random(69).nextDouble()/8, new Random(69).nextDouble()/2 * (new Random().nextBoolean() ? 1:-1));
                    }
                    this.got=true;
                }
                }
        else{
            this.druid.getNavigator().tryMoveToXYZ(bebpos.getX(), bebpos.getY(), bebpos.getZ(), 0.6);
        }

    }


        public BlockPos findWater(int range) {
            for (int x = -range; x <= range; x++) {
                for (int y = -range; y <= range; y++) {
                    for (int z = -range; z <= range; z++) {
                        BlockPos entityPos = new BlockPos(druid).add(x, y, z);
                        IBlockState iblockstate = this.druid.world.getBlockState(entityPos).getBlock().getBlockState().getBaseState();
                        Block block = iblockstate.getBlock();
                        if (block == Blocks.WATER) {
                            this.bebpos =null;
                            //logger.info("teeyy");
                            return entityPos;
                        } else if (block == Blocks.CAULDRON) {
                            if (this.druid.world.getBlockState(entityPos).getValue(LEVEL) >= 1) {
                                if (iblockstate.getValue(LEVEL) < 3) {
                                    logger.info("teey");
                                    return entityPos;
                                }
                            }
                        }
                    }
                }
            }

            return null;
        }
}
