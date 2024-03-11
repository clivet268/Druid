package com.Clivet268.Druid.Entity.AI;

import com.Clivet268.Druid.Entity.DruidEntity;
import net.minecraft.block.*;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.util.math.BlockPos;

import java.util.Random;


//TODO make constant
public class EntityAICollectDye extends Goal {
    private final DruidEntity druid;
    public BlockPos goalBlockPos = null;
    private boolean got = false;

    public EntityAICollectDye(DruidEntity ocelotIn) {
        super();
        this.druid = ocelotIn;
    }

    //TODO clean?
    @Override
    public boolean shouldExecute() {
        this.got = false;
        BlockPos nerwater = this.findFlowers(9);
        if (nerwater != null) {
            this.goalBlockPos = nerwater;
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
        this.goalBlockPos = null;
    }

    @Override
    public boolean shouldContinueExecuting() {
        if (this.got) {
            return false;
        }
        return this.druid.world.getBlockState(this.goalBlockPos).getBlock() instanceof FlowerBlock;
    }

    @Override
    public void tick() {
        if (this.druid.getDistanceSq(goalBlockPos.getX(), goalBlockPos.getY(), goalBlockPos.getZ()) < 9.0D) {
            if (this.druid.shouldCollectDye()) {
                BlockState iblockstate = this.druid.world.getBlockState(goalBlockPos);
                Block block = iblockstate.getBlock();
                System.out.println("yes");
                this.druid.getNavigator().clearPath();
                this.druid.setDyes(druid.getDyes() + 1);
                for (int i = 6 + new Random().nextInt(12); i >= 0; i--) {
                    //this.druid.world.addOptionalParticle(new LifeParticle(this.druid.getPosY()));
                    //EnumParticleTypes.CRIT_MAGIC.getParticleID(), this.druid.posX, this.druid.posY + 5, this.druid.posZ, new Random(69).nextDouble()/4 * (new Random().nextBoolean() ? 1:-1), new Random(69).nextDouble()/8, new Random(69).nextDouble()/2 * (new Random().nextBoolean() ? 1:-1))
                }
                this.got = true;
            }
        } else {
            this.druid.getNavigator().tryMoveToXYZ(goalBlockPos.getX(), goalBlockPos.getY(), goalBlockPos.getZ(), 0.6);
        }

    }


    public BlockPos findFlowers(int range) {
        for (int i = 100; i > 0; i--) {
            int x = new Random().nextInt(range * 2) - range;
            int y = new Random().nextInt(range * 2) - range;
            int z = new Random().nextInt(range * 2) - range;
            BlockPos entityPos = new BlockPos(druid).add(x, y, z);
            BlockState iblockstate = this.druid.world.getBlockState(entityPos);
            Block block = iblockstate.getBlock();
            if (block instanceof FlowerBlock) {
                this.goalBlockPos = null;
                return entityPos;
            } else if (block instanceof DoublePlantBlock && block != Blocks.TALL_GRASS) {
                return entityPos;
            }
        }

        return null;
    }
}
