package com.Clivet268.Druid.Block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.fluid.IFluidState;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.Random;

import static com.Clivet268.Druid.Util.RegistryHandler.LIFE;

//TODO makes up a druids heart and the bindng stone is dropped when a druid dies]
public class Livingstone extends Block {
    public Livingstone() {
        super(Properties.create(Material.ROCK)
                .harvestLevel(0).lightValue(1)
                .variableOpacity());
    }
    @OnlyIn(Dist.CLIENT)
    public void animateTick(World worldIn, BlockPos pos, IFluidState state, Random random) {
        BlockPos blockpos = pos.up();
        if (worldIn.getBlockState(blockpos).isAir() && !worldIn.getBlockState(blockpos).isOpaqueCube(worldIn, blockpos)) {
            if (random.nextInt(100) == 0) {
                double d0 = (float)pos.getX() + random.nextFloat();
                double d1 = pos.getY() + 1;
                double d2 = (float)pos.getZ() + random.nextFloat();
                worldIn.addParticle(LIFE.get(), d0, d1, d2, 0.0D, 0.0D, 0.0D);
                worldIn.playSound(d0, d1, d2, SoundEvents.BLOCK_LAVA_POP, SoundCategory.BLOCKS, 0.2F + random.nextFloat() * 0.2F, 0.9F + random.nextFloat() * 0.15F, false);
            }
        }

    }

    public void randomTick(World p_207186_1_, BlockPos pos, IFluidState state, Random random) {

    }


}