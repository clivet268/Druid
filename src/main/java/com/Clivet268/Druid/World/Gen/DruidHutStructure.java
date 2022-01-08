package com.Clivet268.Druid.World.Gen;

import net.minecraft.init.Biomes;
import net.minecraft.util.SharedSeedRandom;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraft.world.gen.feature.structure.*;

public class DruidHutStructure extends ScatteredStructure<JunglePyramidConfig>{

        protected String getStructureName() {
        return "Druid_Hut";
    }

        public int getSize() {
        return 3;
    }

        protected StructureStart makeStart(IWorld worldIn, IChunkGenerator<?> generator, SharedSeedRandom random, int x, int z) {
        Biome biome = generator.getBiomeProvider().getBiome(new BlockPos((x << 4) + 9, 0, (z << 4) + 9), Biomes.PLAINS);
        return new DruidHutStructure.Start(worldIn, random, x, z, biome);
    }

        protected int getSeedModifier() {
        return 14397699;
    }

        public static class Start extends StructureStart {
            public Start() {
            }

            public Start(IWorld p_i48733_1_, SharedSeedRandom p_i48733_2_, int p_i48733_3_, int p_i48733_4_, Biome p_i48733_5_) {
                super(p_i48733_3_, p_i48733_4_, p_i48733_5_, p_i48733_2_, p_i48733_1_.getSeed());
                JunglePyramidPiece junglepyramidpiece = new JunglePyramidPiece(p_i48733_2_, p_i48733_3_ * 16, p_i48733_4_ * 16);
                this.components.add(junglepyramidpiece);
                this.recalculateStructureSize(p_i48733_1_);
            }
        }
}
