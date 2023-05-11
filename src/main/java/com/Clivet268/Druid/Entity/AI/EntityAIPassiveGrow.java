package com.Clivet268.Druid.Entity.AI;

import com.Clivet268.Druid.Entity.DruidEntity;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.IGrowable;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.server.ServerWorld;

import java.util.Random;


public class EntityAIPassiveGrow extends Goal {
    private final DruidEntity druid;
    public BlockPos bebpos = null;
    private boolean got = false;

    public EntityAIPassiveGrow(DruidEntity ocelotIn) {
        super();
        this.druid = ocelotIn;
    }

    @Override
    public boolean shouldExecute() {
        this.got = false;
        BlockPos nerwater = this.findGrow(4);
        if (nerwater != null) {
            this.bebpos = nerwater;
            return this.druid.getRNG().nextInt(1500) == 0;
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
    public boolean shouldContinueExecuting() {
        if (this.got) {
            return false;
        }
        Block block = this.druid.world.getBlockState(this.bebpos).getBlock();
        return block instanceof IGrowable;


    }

    @Override
    public void tick() {
        //logger.info("yeeit");
        if (this.druid.getDistanceSq(bebpos.getX(), bebpos.getY(), bebpos.getZ()) < 9.0D) {
            BlockState iblockstate = this.druid.world.getBlockState(bebpos);
            this.druid.getNavigator().clearPath();
            IGrowable igrowable = (IGrowable) iblockstate.getBlock();
            if (igrowable.canGrow(this.druid.world, bebpos, iblockstate, this.druid.world.isRemote)) {
                if (this.druid.world instanceof ServerWorld) {
                    igrowable.grow((ServerWorld) this.druid.world, this.druid.world.rand, bebpos, iblockstate);
                }

                if (!this.druid.world.isRemote) {
                    this.druid.world.playEvent(2005, bebpos.add(0, 1, 0), Block.getStateId(Blocks.GRASS.getDefaultState()));
                }

            }
            this.got = true;
        } else {
            this.druid.getNavigator().tryMoveToXYZ(bebpos.getX(), bebpos.getY(), bebpos.getZ(), 0.6);
        }

    }

    public BlockPos findGrow(int range) {
        for (int i = 100; i > 0; i--) {
            int x = new Random().nextInt(range * 2) - range;
            int y = new Random().nextInt(range * 2) - range;
            int z = new Random().nextInt(range * 2) - range;
            BlockPos entityPos = new BlockPos(druid).add(x, y, z);
            BlockState iblockstate = this.druid.world.getBlockState(entityPos);
            if (iblockstate.getBlock() instanceof IGrowable) {
                this.bebpos = null;
                return entityPos;
            }
        }
        return null;
    }
}
