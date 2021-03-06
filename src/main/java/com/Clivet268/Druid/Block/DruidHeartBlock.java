package com.Clivet268.Druid.Block;

import com.Clivet268.Druid.Tile.TileEntityDruidHeart;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityEnchantmentTable;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.Random;

public class DruidHeartBlock extends BlockBase
    {
        protected static final AxisAlignedBB AABB = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.75D, 1.0D);

        public DruidHeartBlock(String name)
        {
            super(name,Material.ROCK, CreativeTabs.DECORATIONS);
            this.setLightOpacity(255);
            this.setLightLevel(3);
        }

        public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos)
        {
            return AABB;
        }
        public BlockFaceShape getBlockFaceShape(IBlockAccess worldIn, IBlockState state, BlockPos pos, EnumFacing face)
        {
            return BlockFaceShape.UNDEFINED;
        }

        public boolean isFullCube(IBlockState state)
        {
            return false;
        }

        @SideOnly(Side.CLIENT)
        public void randomDisplayTick(IBlockState stateIn, World worldIn, BlockPos pos, Random rand)
        {
            super.randomDisplayTick(stateIn, worldIn, pos, rand);

            for (int i = -2; i <= 2; ++i)
            {
                for (int j = -2; j <= 2; ++j)
                {
                    if (i > -2 && i < 2 && j == -1)
                    {
                        j = 2;
                    }

                    if (rand.nextInt(16) == 0)
                    {
                        for (int k = 0; k <= 1; ++k)
                        {
                            BlockPos blockpos = pos.add(i, k, j);

                            if (net.minecraftforge.common.ForgeHooks.getEnchantPower(worldIn, blockpos) > 0)
                            {
                                if (!worldIn.isAirBlock(pos.add(i / 2, 0, j / 2)))
                                {
                                    break;
                                }

                                worldIn.spawnParticle(EnumParticleTypes.ENCHANTMENT_TABLE, (double)pos.getX() + 0.5D, (double)pos.getY() + 2.0D, (double)pos.getZ() + 0.5D, (double)((float)i + rand.nextFloat()) - 0.5D, (float)k - rand.nextFloat() - 1.0F, (double)((float)j + rand.nextFloat()) - 0.5D);
                            }
                        }
                    }
                }
            }
        }

        public boolean isOpaqueCube(IBlockState state)
        {
            return false;
        }

        public EnumBlockRenderType getRenderType(IBlockState state)
        {
            return EnumBlockRenderType.MODEL;
        }

        public TileEntity createTileEntity(World worldIn, IBlockState state)
        {
            return new TileEntityDruidHeart();
        }

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

