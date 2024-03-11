package com.Clivet268.Druid.Entity.AI;

import com.Clivet268.Druid.Entity.DruidEntity;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.HashMap;
import java.util.Random;

import static com.Clivet268.Druid.Util.RegistryHandler.REVIVAL;

public class DruidAIRegrow extends Goal {
    private final DruidEntity druid;
    private final World entityWorld;
    int regrowTimer;
    private BlockPos targetPos = null;
    HashMap<Block, Block> bloks = new HashMap<>();

    public DruidAIRegrow(DruidEntity grassEaterEntityIn) {
        super();
        this.druid = grassEaterEntityIn;
        this.entityWorld = grassEaterEntityIn.world;
        this.setRegrowth();

    }

    public void setRegrowth() {
        bloks.put(Blocks.DIRT, Blocks.GRASS_BLOCK);
        bloks.put(Blocks.SAND, Blocks.DIRT);
    }

    //TODO was (poorly) based on how many blocks there were to regrow around it, i dont think its a good idea but something to revisit
    @Override
    public boolean shouldExecute() {
        if (this.druid.getRNG().nextInt(1000) != 0) {
            return false;
        } else {
            //TODO clean
            BlockPos nearGrowable = this.findBlockRegrow(8);
            if (nearGrowable != null) {
                this.targetPos = nearGrowable;
                return this.druid.getBrews() > 0;
            }

            return false;

        }
    }

    @Override
    public void startExecuting() {
        this.regrowTimer = 40;
        this.entityWorld.setEntityState(this.druid, (byte) 10);

    }

    @Override
    public void resetTask() {
        this.targetPos = null;
        this.regrowTimer = 0;
    }

    @Override
    public boolean shouldContinueExecuting() {

        return (!this.druid.getNavigator().noPath()) && this.regrowTimer > 0 && this.bloks.containsKey(this.entityWorld.getBlockState(targetPos).getBlock());

    }

    public int getRegrowTimer() {
        return this.regrowTimer;
    }

    @Override
    public void tick() {
        if (this.druid.getDistanceSq(targetPos.getX(), targetPos.getY(), targetPos.getZ()) <= 4.0D) {
            //TODO necicary?
            this.regrowTimer = Math.max(0, this.regrowTimer - 1);
            if (net.minecraftforge.event.ForgeEventFactory.getMobGriefingEvent(this.entityWorld, this.druid)) {
                this.druid.brew();
                this.druid.setBrews(this.druid.getBrews() - 1);
                this.entityWorld.setBlockState(targetPos, bloks.get(this.entityWorld.getBlockState(targetPos).getBlock()).getDefaultState(), 3);
                for (int i = 5 + new Random().nextInt(12); i >= 0; i--) {
                    //TODO clean
                    druid.world.addParticle(REVIVAL.get(), this.druid.getPosX(), this.druid.getPosY(), this.druid.getPosZ(), (new Random().nextDouble() - 0.5) / 3, 0, (new Random().nextDouble() - 0.3) / 3);
                }
                this.druid.getNavigator().clearPath();
            }

        } else {
            this.druid.getNavigator().tryMoveToXYZ(this.targetPos.getX(), this.targetPos.getY(), this.targetPos.getZ(), 0.6);
        }
    }

    public BlockPos findBlockRegrow(int range) {
        BlockPos entityPos = new BlockPos(druid);
        for (int i = 100; i > 0; i--) {
            int x = new Random().nextInt(range * 2) - range;
            int y = new Random().nextInt(range * 2) - range;
            int z = new Random().nextInt(range * 2) - range;

            if (this.bloks.containsKey(druid.world.getBlockState(entityPos.add(x, y, z)).getBlock())) {
                if (this.entityWorld.getBlockState(entityPos.add(x, y, z).up()).getBlock() == Blocks.AIR) {
                    return entityPos.add(x, y, z);

                }
            }
        }

        return null;
    }


}


