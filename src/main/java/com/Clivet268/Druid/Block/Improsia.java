package com.Clivet268.Druid.Block;

import com.Clivet268.Druid.Tile.ImprosiaTileEntity;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.IGrowable;
import net.minecraft.state.IntegerProperty;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.DecoratedFeatureConfig;
import net.minecraft.world.gen.feature.FlowersFeature;
import net.minecraft.world.server.ServerWorld;

import java.util.List;
import java.util.Random;

public class Improsia extends WildPlantBlock {
    IntegerProperty AGE = BlockStateProperties.AGE_0_3;

    public Improsia(Attribute[] ain, double[] ints) {
        super(ain, ints);
        //super(Block.Properties.create(Material.PLANTS).doesNotBlockMovement());
    }

    static final VoxelShape SHAPE = Block.makeCuboidShape(2.0D, 0.0D, 2.0D, 14.0D, 13.0D, 14.0D);


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


    @Override
    public TileEntity createNewTileEntity(IBlockReader worldIn) {
        return (new ImprosiaTileEntity()).setAttributes(this.attributes);
    }

    @Override
    public void tick(BlockState state, ServerWorld worldIn, BlockPos pos, Random rand) {
        int i = state.get(AGE);
        if (i < 3 && worldIn.getLightSubtracted(pos.up(), 0) >= 9 && net.minecraftforge.common.ForgeHooks.onCropsGrowPre(worldIn, pos, state, rand.nextInt(100) == 0)) {
            worldIn.setBlockState(pos, state.with(AGE, Integer.valueOf(i + 1)), 2);
            net.minecraftforge.common.ForgeHooks.onCropsGrowPost(worldIn, pos, state);
        }

    }

    @Override
    public void grow(ServerWorld worldIn, Random rand, BlockPos pos, BlockState state) {
        BlockPos[] diags = {pos.east().north(), pos.west().north(), pos.east().south(), pos.west().south()};
        if (isValidGround(worldIn.getBlockState(diags[rand.nextInt(3)]), worldIn, pos)) {

        }


        BlockPos lvt_5_1_ = pos.up();
        BlockState lvt_6_1_ = Blocks.GRASS.getDefaultState();

        label48:
        for (int lvt_7_1_ = 0; lvt_7_1_ < 128; ++lvt_7_1_) {
            BlockPos lvt_8_1_ = lvt_5_1_;

            for (int lvt_9_1_ = 0; lvt_9_1_ < lvt_7_1_ / 16; ++lvt_9_1_) {
                lvt_8_1_ = lvt_8_1_.add(rand.nextInt(3) - 1, (rand.nextInt(3) - 1) * rand.nextInt(3) / 2, rand.nextInt(3) - 1);
                if (worldIn.getBlockState(lvt_8_1_.down()).getBlock() != this || worldIn.getBlockState(lvt_8_1_).isCollisionShapeOpaque(worldIn, lvt_8_1_)) {
                    continue label48;
                }
            }

            BlockState lvt_9_2_ = worldIn.getBlockState(lvt_8_1_);
            if (lvt_9_2_.getBlock() == lvt_6_1_.getBlock() && rand.nextInt(10) == 0) {
                ((IGrowable) lvt_6_1_.getBlock()).grow(worldIn, rand, lvt_8_1_, lvt_9_2_);
            }

            if (lvt_9_2_.isAir()) {
                BlockState lvt_10_2_;
                if (rand.nextInt(8) == 0) {
                    List<ConfiguredFeature<?, ?>> lvt_11_1_ = worldIn.getBiome(lvt_8_1_).getFlowers();
                    if (lvt_11_1_.isEmpty()) {
                        continue;
                    }

                    ConfiguredFeature<?, ?> lvt_12_1_ = ((DecoratedFeatureConfig) lvt_11_1_.get(0).config).feature;
                    lvt_10_2_ = ((FlowersFeature) lvt_12_1_.feature).getFlowerToPlace(rand, lvt_8_1_, lvt_12_1_.config);
                } else {
                    lvt_10_2_ = lvt_6_1_;
                }

                if (lvt_10_2_.isValidPosition(worldIn, lvt_8_1_)) {
                    worldIn.setBlockState(lvt_8_1_, lvt_10_2_, 3);
                }
            }
        }

    }

    protected boolean isValidGround(BlockState state, IBlockReader worldIn, BlockPos pos) {
        Block block = state.getBlock();
        return block == Blocks.SAND;
    }


}
