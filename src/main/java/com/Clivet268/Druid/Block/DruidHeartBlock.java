package com.Clivet268.Druid.Block;

//import com.Clivet268.Druid.Tile.TileEntityDruidHeart;
//import com.Clivet268.Druid.Util.RegistryHandler;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Particles;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityEnchantmentTable;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.IBooleanFunction;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.Random;
import java.util.stream.Stream;

public class DruidHeartBlock extends BlockBase
    {
        public DruidHeartBlock()
        {
            super(Material.ROCK);
        }

        @Override
        public boolean isFullCube(IBlockState state)
        {
            return false;
        }

        @OnlyIn(Dist.CLIENT)
        public void animateTick(IBlockState stateIn, World worldIn, BlockPos pos, Random rand) {
            super.animateTick(stateIn, worldIn, pos, rand);

            for(int i = -2; i <= 2; ++i) {
                for(int j = -2; j <= 2; ++j) {
                    if (i > -2 && i < 2 && j == -1) {
                        j = 2;
                    }

                    if (rand.nextInt(16) == 0) {
                        for(int k = 0; k <= 1; ++k) {
                            BlockPos blockpos = pos.add(i, k, j);
                            if (worldIn.getBlockState(blockpos).getEnchantPowerBonus(worldIn, pos) > 0) {
                                if (!worldIn.isAirBlock(pos.add(i / 2, 0, j / 2))) {
                                    break;
                                }

                                worldIn.spawnParticle(Particles.ENCHANT, (double)pos.getX() + 0.5D, (double)pos.getY() + 2.0D, (double)pos.getZ() + 0.5D, (double)((float)i + rand.nextFloat()) - 0.5D, (double)((float)k - rand.nextFloat() - 1.0F), (double)((float)j + rand.nextFloat()) - 0.5D);
                            }
                        }
                    }
                }
            }

        }

        @Override
        public boolean isNormalCube(IBlockState state, IBlockReader world, BlockPos pos)
        {
            return false;
        }

        @Override
        public VoxelShape getShape(IBlockState state, IBlockReader source, BlockPos pos){
            return Stream.of(
                    Block.makeCuboidShape(7, 0, 7, 9, 1, 9),
                    Block.makeCuboidShape(6, 1, 6, 10, 2, 10),
                    Block.makeCuboidShape(5, 2, 5, 11, 4, 11),
                    Block.makeCuboidShape(4, 4, 4, 12, 6, 12),
                    Block.makeCuboidShape(5, 10, 5, 11, 12, 11),
                    Block.makeCuboidShape(4, 8, 4, 12, 10, 12),
                    Block.makeCuboidShape(6, 12, 6, 10, 13, 10),
                    Block.makeCuboidShape(7, 13, 7, 9, 14, 9),
                    Block.makeCuboidShape(3, 5, 4, 4, 9, 5),
                    Block.makeCuboidShape(4, 5, 3, 5, 9, 4),
                    Block.makeCuboidShape(11, 5, 3, 12, 9, 4),
                    Block.makeCuboidShape(3, 5, 11, 4, 9, 12),
                    Block.makeCuboidShape(4, 5, 12, 5, 9, 13),
                    Block.makeCuboidShape(11, 5, 12, 12, 9, 13),
                    Block.makeCuboidShape(12, 5, 11, 13, 9, 12),
                    Block.makeCuboidShape(12, 5, 4, 13, 9, 5)
            ).reduce((v1, v2) -> VoxelShapes.combine(v1, v2, IBooleanFunction.OR)).get();
        }

        public EnumBlockRenderType getRenderType(IBlockState state)
        {
            return EnumBlockRenderType.MODEL;
        }

        //public TileEntity createNewTileEntity(World worldIn, int meta)
        //{
            //return new TileEntityDruidHeart(RegistryHandler.);
        //}

        public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
        {
            if (worldIn.isRemote)
            {
                return true;
            }
            else
            {
                TileEntity tileentity = worldIn.getTileEntity(pos);

                if (tileentity instanceof TileEntityEnchantmentTable)
                {
                    playerIn.displayGui((TileEntityEnchantmentTable)tileentity);
                }

                return true;
            }
        }


    }

