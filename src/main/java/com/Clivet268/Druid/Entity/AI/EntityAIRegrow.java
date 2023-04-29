package com.Clivet268.Druid.Entity.AI;

import com.Clivet268.Druid.Entity.DruidEntity;
import com.Clivet268.Druid.Particle.LifeParticle;
import com.Clivet268.Druid.Util.RegistryHandler;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.HashMap;
import java.util.Random;

public class EntityAIRegrow extends Goal
{
    private final CreatureEntity druid;
    private final World entityWorld;
    int eatingGrassTimer;
    private BlockPos nearbysans = null;
    HashMap<Block,Block> bloks = new HashMap<>();

    public EntityAIRegrow(CreatureEntity grassEaterEntityIn)
    {
        super();
        this.druid = grassEaterEntityIn;
        this.entityWorld = grassEaterEntityIn.world;
        //this.setMutexBits(7);
        this.setRegrowth();

    }
    public void setRegrowth(){
        bloks.put(Blocks.DIRT,Blocks.GRASS_BLOCK);
        bloks.put(Blocks.SAND,Blocks.DIRT);
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
                this.nearbysans = neargrowable;
                return ((DruidEntity) this.druid).getBrews() > 0 ;
            }

            return false;

        }
    }
    @Override
    public void startExecuting()
    {
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
    public void tick() {
        if (this.druid.getDistanceSq(nearbysans.getX(), nearbysans.getY(), nearbysans.getZ()) <= 4.0D) {
            this.eatingGrassTimer = Math.max(0, this.eatingGrassTimer - 1);

            //if (this.eatingGrassTimer == 4) {
                /*
                BlockPos blockpos = new BlockPos(this.druid.posX, this.druid.posY, this.druid.posZ);
                BlockPos blockpos1 = blockpos.down();

                if (this.bloks.containsKey(this.entityWorld.getBlockState(blockpos1).getBlock())) {

                 */
                    if (net.minecraftforge.event.ForgeEventFactory.getMobGriefingEvent(this.entityWorld, this.druid)){
                        ((DruidEntity) (this.druid)).brew();
                    ((DruidEntity) (this.druid)).setBrews(((DruidEntity) (this.druid)).getBrews() - 1);
                    this.entityWorld.setBlockState(nearbysans, bloks.get(this.entityWorld.getBlockState(nearbysans).getBlock()).getDefaultState(), 3);
                    System.out.println("sandsundetale");
                    for (int i = 5 + new Random().nextInt(12); i >= 0; i--) {
                        Minecraft.getInstance().world.addParticle(RegistryHandler.REGROW.get(),this.druid.getPosX(), this.druid.getPosY(), this.druid.getPosZ(),(new Random().nextDouble()-0.5)/3, 0, (new Random().nextDouble()-0.3)/3);
                    }
                    this.druid.getNavigator().clearPath();
                    //this.grassEaterEntity.eatGrassBonus();
                }

        }
        else{
            //System.out.println("AAAAAAAAAA " + this.druid.getNavigator().tryMoveToXYZ(this.nearbysans.getX(), this.nearbysans.getY(), this.nearbysans.getZ(), 0.6) + " " + this.nearbysans);
            this.druid.getNavigator().tryMoveToXYZ(this.nearbysans.getX(), this.nearbysans.getY(), this.nearbysans.getZ(), 0.6);
        }
    }
    public BlockPos findBlockRegrow(int range) {
        BlockPos entityPos = new BlockPos(druid);
        for(int i = 100; i > 0; i--) {
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


