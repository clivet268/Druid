package com.Clivet268.Druid.Entity.AI;

import com.Clivet268.Druid.Entity.DruidEntity;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.RandomPositionGenerator;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.ArrayList;

//TODO make brew based?
public class EntityAIPlaceFlowers extends EntityAIBase
    {
        private final EntityCreature grassEaterEntity;
        private final World entityWorld;
        int eatingGrassTimer;
        ArrayList<IBlockState> flora = new ArrayList<>();
        int[] weight;

        public EntityAIPlaceFlowers(EntityCreature grassEaterEntityIn)
        {
            this.grassEaterEntity = grassEaterEntityIn;
            this.entityWorld = grassEaterEntityIn.world;
            this.setMutexBits(7);
            this.setFloraProbability();
        }


        public boolean shouldExecute()
        {
            if (this.grassEaterEntity.getRNG().nextInt(1850) != 0)
            {
                return false;
            }
            else
            {
                BlockPos blockpos = new BlockPos(this.grassEaterEntity.posX, this.grassEaterEntity.posY, this.grassEaterEntity.posZ);

                return this.entityWorld.getBlockState(blockpos.down()).getBlock() == Blocks.GRASS;

            }
        }
        public void setFloraProbability(){
            Block[] types = new Block[]{
                Blocks.DANDELION,Blocks.OXEYE_DAISY,Blocks.ORANGE_TULIP,Blocks.PINK_TULIP,Blocks.RED_TULIP,
                    Blocks.WHITE_TULIP, Blocks.AZURE_BLUET, Blocks.POPPY, Blocks.ALLIUM, Blocks.BLUE_ORCHID};

            for(Block e : types) {
                IBlockState state = e.getDefaultState();
                flora.add(state);
            }

            this.weight = new int[flora.size()];
            this.weight[0] = 30;
            this.weight[1] = 1;
            this.weight[2] = 3;
            this.weight[3] = 4;
            this.weight[4] = 3;
            this.weight[5] = 4;
            this.weight[6] = 7;
            this.weight[7] = 3;
            this.weight[8] = 28;
            this.weight[9] = 9;




        }
        public IBlockState getFlora(){
            boolean check = true;
            int rando = ((int)((Math.random() * 100) + 1));
            int spotto = ((int)((Math.random() * flora.size())));
            while(check){
                System.out.println("yup");
                if(weight[spotto] >= rando){
                    check=false;

                }
                else{
                    spotto = ((int)((Math.random() * flora.size())));
                    rando = ((int)((Math.random() * 100) + 1));
                }
            }
            return flora.get(spotto);
        }
        public void startExecuting()
        {
            Vec3d vec3d = this.getPosition();

            if(vec3d == null){
                vec3d = new Vec3d(0,0,0);
            }
            double x = vec3d.x;
            double y = vec3d.y;
            double z = vec3d.z;
            this.grassEaterEntity.getNavigator().clearPath();
            this.grassEaterEntity.getNavigator().tryMoveToXYZ(x , y, z, 0.5D);
            //logger.info("yeet");
            this.eatingGrassTimer = 40;
            this.entityWorld.setEntityState(this.grassEaterEntity, (byte)10);
        }

        public void resetTask()
        {
            this.eatingGrassTimer = 0;
        }

        public boolean shouldContinueExecuting()
        {
            return this.eatingGrassTimer > 0 && !this.grassEaterEntity.getNavigator().noPath();

        }

        public int getEatingGrassTimer()
        {
            return this.eatingGrassTimer;
        }

        public void updateTask()
        {
            this.eatingGrassTimer = Math.max(0, this.eatingGrassTimer - 1);
            //logger.info("yeet");
            if (this.eatingGrassTimer == 4)
            {
                BlockPos blockpos = new BlockPos(this.grassEaterEntity.posX, this.grassEaterEntity.posY, this.grassEaterEntity.posZ);
                    BlockPos blockpos1 = blockpos.down();
                    BlockPos blockpos2 = blockpos.offset(grassEaterEntity.getHorizontalFacing());
                if (this.entityWorld.getBlockState(blockpos1).getBlock() == Blocks.GRASS &&
                    this.entityWorld.getBlockState(blockpos).getBlock() == Blocks.AIR)
                    {
                        if (net.minecraftforge.event.ForgeEventFactory.getMobGriefingEvent(this.entityWorld, this.grassEaterEntity))

                            //this.entityWorld.playEvent(2001, blockpos, Block.getIdFromBlock(Blocks.GRASS));
                            ((DruidEntity)(this.grassEaterEntity)).brew();
                        ((DruidEntity)(this.grassEaterEntity)).setBrews(((DruidEntity)(this.grassEaterEntity)).getBrews() -1);
                        this.entityWorld.setBlockState(blockpos2, getFlora(), 3);


                        //this.grassEaterEntity.eatGrassBonus();
                    }
                }
            }
        @Nullable
        protected Vec3d getPosition()
        {
            return RandomPositionGenerator.findRandomTarget(this.grassEaterEntity, 7, 3);
        }
        }


