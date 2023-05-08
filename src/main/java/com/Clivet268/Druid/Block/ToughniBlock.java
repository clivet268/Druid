package com.Clivet268.Druid.Block;

import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

import java.util.Random;

public class ToughniBlock extends BushBlock implements IGrowable, net.minecraftforge.common.IShearable {
   protected static final VoxelShape SHAPE = Block.makeCuboidShape(2.0D, 0.0D, 2.0D, 14.0D, 13.0D, 14.0D);

   public ToughniBlock() {
      super(Properties.create(Material.PLANTS));
   }

   public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
      return SHAPE;
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
   protected boolean isValidGround(BlockState state, IBlockReader worldIn, BlockPos pos) {
      Block block = state.getBlock();
      return block == Blocks.SAND || block == Blocks.RED_SAND || block == Blocks.DIRT || block == Blocks.COARSE_DIRT || block == Blocks.PODZOL;
   }

   /**
    * Get the OffsetType for this Block. Determines if the model is rendered slightly offset.
    */
   public OffsetType getOffsetType() {
      return OffsetType.XYZ;
   }
}