package com.Clivet268.Druid.Block;

import com.Clivet268.Druid.Tile.TileEntityDruidSoulLight;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class DruidSoulLight extends BlockBase
    {
        protected static final AxisAlignedBB AABB = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.75D, 1.0D);

        public DruidSoulLight(String name)
        {
            super(name,Material.ROCK, CreativeTabs.DECORATIONS);
            this.setLightOpacity(255);
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
            return new TileEntityDruidSoulLight();
        }

    }

