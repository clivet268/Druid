package com.Clivet268.Druid.Entity.AI;

import com.Clivet268.Druid.Entity.DruidEntity;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;

import static net.minecraft.block.BlockCauldron.LEVEL;

//TODO make constant
public class EntityAIDrinkWater extends EntityAIBase {
    private final DruidEntity druid;
    public BlockPos bebpos =null;
    private boolean got =false;

    public EntityAIDrinkWater(DruidEntity ocelotIn, double p_i45315_2_) {
        super();
        this.druid = ocelotIn;
    }

    @Override
    public boolean shouldExecute() {
        this.got=false;
        BlockPos nerwater = this.findBlockRegrow(7);
        if (nerwater != null) {
            this.bebpos = nerwater;
            return this.druid.getRNG().nextInt(400) == 0
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
         IBlockState iBlockState = block.getDefaultState();
        if (this.druid.world.getBlockState(bebpos).get(LEVEL) >= 1) {
            if (iBlockState.get(LEVEL) < 3) {
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
    public void tick() {
        //logger.info("yeeit");
        if (this.druid.getDistanceSq(bebpos) < 2.0D) {
            if (this.druid.shouldCollectWater()) {
                IBlockState iblockstate = this.druid.world.getBlockState(bebpos);
                Block block = iblockstate.getBlock();
                if (block == Blocks.CAULDRON) {
                    if (!(this.druid.world.getBlockState(this.bebpos).get(LEVEL) <= 0)) {
                        this.druid.getNavigator().clearPath();
                        this.druid.setWater(druid.getWater() + 1);
                        this.druid.world.setBlockState(this.bebpos, druid.world.getBlockState(this.bebpos).with(LEVEL, iblockstate.get(LEVEL) - 1), 3);
                        this.resetTask();
                    }
                } else {
                    System.out.println("yes");
                    this.druid.getNavigator().clearPath();
                    this.druid.setWater(druid.getWater() + 1);
                    this.got = true;
                }
            }
        }
        else{
            this.druid.getNavigator().tryMoveToXYZ(bebpos.getX(), bebpos.getY(), bebpos.getZ(), 0.6);
        }

    }


        public BlockPos findBlockRegrow(int range) {
            for (int x = -range; x <= range; x++) {
                for (int y = -range; y <= range; y++) {
                    for (int z = -range; z <= range; z++) {
                        BlockPos entityPos = new BlockPos(druid).add(x, y, z);
                        IBlockState iblockstate = this.druid.world.getBlockState(entityPos).getBlock().getDefaultState();
                        Block block = iblockstate.getBlock();
                        if (block == Blocks.WATER) {
                            this.bebpos =null;
                            //logger.info("teeyy");
                            return entityPos;
                        } else if (block == Blocks.CAULDRON) {
                            if (this.druid.world.getBlockState(entityPos).get(LEVEL) >= 1) {
                                if (iblockstate.get(LEVEL) < 3) {
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
