package com.Clivet268.Druid.Block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.effect.LightningBoltEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import static com.Clivet268.Druid.Block.Attribute.*;
import static com.Clivet268.Druid.Util.RegistryHandler.LIGHTNING_BUZZ;

public class WildPlantBlock extends BaseBushBlock {

    public WildPlantBlock(Attribute[] ain, double[] ints) {
        for (int i = 0; i < ain.length; i++) {
            this.addAttribute(ain[i], ints[i]);
        }
        this.setDefaultState(this.stateContainer.getBaseState().with(BEARING, Boolean.FALSE));
    }


    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(BEARING);
    }

    public void onEntityCollision(BlockState state, World worldIn, BlockPos pos, Entity entityIn) {
        World curworld = Minecraft.getInstance().world;
        System.out.println(worldIn.isRemote);


        double rand = Math.random();

        double s = this.getValue(Sticky) - 20;
        if (s > 0) {
            entityIn.setMotionMultiplier(state, new Vec3d(s, s / 5, s));
        }

        double f = this.getValue(Fire);
        entityIn.setFire((int) f / 20);

        double l = this.getValue(Lightning);
        System.out.println(l + " " + rand * 100);
        if (l >= 0) {
            if ((l / 250) >= rand * 100) {
                worldIn.playSound(null, pos, LIGHTNING_BUZZ, SoundCategory.BLOCKS, 1.0F, 1.0F);
            }
            if ((l / 999) >= rand * 100) {
                LightningBoltEntity lightningboltentity = new LightningBoltEntity(worldIn, pos.getX(), pos.getY() + 1, pos.getZ(), false);
                lightningboltentity.rotationYaw = 0.0F;
                lightningboltentity.rotationPitch = 0.0F;
                worldIn.addEntity(lightningboltentity);
            }
        }

    }

    /**
     * Whether this IGrowable can grow
     */
    public boolean canGrow(IBlockReader worldIn, BlockPos pos, BlockState state, boolean isClient) {
        return false;
    }

    public boolean canUseBonemeal(World worldIn, Random rand, BlockPos pos, BlockState state) {
        return false;
    }

    public void grow(ServerWorld worldIn, Random rand, BlockPos pos, BlockState state) {
        //TODO

    }

    public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
        boolean i = state.get(BEARING);
        if (i) {
            int j = 1 + worldIn.rand.nextInt(2);
            spawnAsEntity(worldIn, pos, new ItemStack(Items.SWEET_BERRIES, j));
            worldIn.playSound(null, pos, SoundEvents.ITEM_SWEET_BERRIES_PICK_FROM_BUSH, SoundCategory.BLOCKS, 1.0F, 0.8F + worldIn.rand.nextFloat() * 0.4F);
            worldIn.setBlockState(pos, state.with(BEARING, Boolean.FALSE), 2);
            return ActionResultType.SUCCESS;
        } else {
            return super.onBlockActivated(state, worldIn, pos, player, handIn, hit);
        }
    }

}
