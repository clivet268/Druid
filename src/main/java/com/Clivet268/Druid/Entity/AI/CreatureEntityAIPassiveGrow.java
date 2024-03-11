package com.Clivet268.Druid.Entity.AI;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.IGrowable;
import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.server.ServerWorld;

import java.util.Random;


public class CreatureEntityAIPassiveGrow extends Goal {
    private final CreatureEntity creatureEntity;
    public BlockPos tagetPos = null;
    private boolean got = false;

    public CreatureEntityAIPassiveGrow(CreatureEntity creatureEntity) {
        super();
        this.creatureEntity = creatureEntity;
    }

    @Override
    public boolean shouldExecute() {
        if(this.creatureEntity.getRNG().nextInt(1500) != 0){
            return false;
        }

        this.got = false;
        BlockPos growPos = this.findGrow(4);
        if (growPos != null) {
            this.tagetPos = growPos;
            return true;
        }
        return false;

    }

    //TODO
    @Override
    public void startExecuting() {
        //super.startExecuting();
    }

    @Override
    public void resetTask() {
        this.creatureEntity.getNavigator().clearPath();
        this.tagetPos = null;
    }

    @Override
    public boolean shouldContinueExecuting() {
        if (this.got) {
            return false;
        }
        Block block = this.creatureEntity.world.getBlockState(this.tagetPos).getBlock();
        return block instanceof IGrowable;
    }

    //TODO clean
    @Override
    public void tick() {
        if (this.creatureEntity.getDistanceSq(tagetPos.getX(), tagetPos.getY(), tagetPos.getZ()) < 9.0D) {
            BlockState iblockstate = this.creatureEntity.world.getBlockState(tagetPos);
            this.creatureEntity.getNavigator().clearPath();
            IGrowable igrowable = (IGrowable) iblockstate.getBlock();
            if (igrowable.canGrow(this.creatureEntity.world, tagetPos, iblockstate, this.creatureEntity.world.isRemote)) {
                if (this.creatureEntity.world instanceof ServerWorld) {
                    igrowable.grow((ServerWorld) this.creatureEntity.world, this.creatureEntity.world.rand, tagetPos, iblockstate);
                    //TODO check this dosent cause an infinte loop
                    this.got = true;
                }else {
                    //TODO hmmmmmm, is this how things are still done in new versions?
                    this.creatureEntity.world.playEvent(2005, tagetPos.add(0, 1, 0), Block.getStateId(Blocks.GRASS.getDefaultState()));
                }

            }
        } else {
            //TODO is this passive like it just happens or is it passive in that the druid just kinda does it
            this.creatureEntity.getNavigator().tryMoveToXYZ(tagetPos.getX(), tagetPos.getY(), tagetPos.getZ(), 0.6);
        }

    }

    public BlockPos findGrow(int range) {
        for (int i = 100; i > 0; i--) {
            int x = new Random().nextInt(range * 2) - range;
            int y = new Random().nextInt(range * 2) - range;
            int z = new Random().nextInt(range * 2) - range;
            BlockPos entityPos = new BlockPos(creatureEntity).add(x, y, z);
            BlockState iblockstate = this.creatureEntity.world.getBlockState(entityPos);
            if (iblockstate.getBlock() instanceof IGrowable) {
                this.tagetPos = null;
                return entityPos;
            }
        }
        return null;
    }
}
