package com.Clivet268.Druid.World.Gen;

import com.Clivet268.Druid.Druid;
import net.minecraft.util.Rotation;
import net.minecraft.util.SharedSeedRandom;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.structure.ScatteredStructure;
import net.minecraft.world.gen.feature.structure.StructureStart;

public class DruidTempleStructure extends ScatteredStructure<NoFeatureConfig> {
    protected String getStructureName() {
        return "DruidBuilding";
    }

    public int getSize() {
        return 3;
    }

    protected StructureStart makeStart(IWorld worldIn, IChunkGenerator<?> generator, SharedSeedRandom random, int x, int z) {
        Biome biome = generator.getBiomeProvider().getBiome(new BlockPos((x << 4) + 9, 0, (z << 4) + 9), (Biome)null);
        return new DruidTempleStructure.Start(worldIn, generator, random, x, z, biome);
    }

    protected int getSeedModifier() {
        return 142069360;
    }

    protected int getBiomeFeatureDistance(IChunkGenerator<?> chunkGenerator) {
        return chunkGenerator.getSettings().func_204748_h();
    }

    protected int func_211745_b(IChunkGenerator<?> chun) {
        return chun.getSettings().func_211730_k();
    }

    public static class Start extends StructureStart {


        public Start(IWorld p_i48901_1_, IChunkGenerator<?> chunkGenerator, SharedSeedRandom seed, int p_i48901_4_, int p_i48901_5_, Biome biome) {
            super(p_i48901_4_, p_i48901_5_, biome, seed, p_i48901_1_.getSeed());
            NoFeatureConfig structureConfig = (NoFeatureConfig)chunkGenerator.getStructureConfig(biome, Druid.DRUID_TEMPLE);
            Rotation rotation = Rotation.values()[seed.nextInt(Rotation.values().length)];
            BlockPos blockpos = new BlockPos(p_i48901_4_ * 16, 90, p_i48901_5_ * 16);
            DruidTemplePiece.genStructure(p_i48901_1_.getSaveHandler().getStructureTemplateManager(), blockpos, rotation, this.components, seed, structureConfig);
            this.recalculateStructureSize(p_i48901_1_);
        }
    }
}