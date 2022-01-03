package com.Clivet268.Druid.World.Gen;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeDesert;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.fml.common.IWorldGenerator;

import java.util.Random;
//TODO well
public class WorldGenCustomStructures implements IWorldGenerator {
    public static final WorldGenStructure DRUID_TEMPLE = new WorldGenStructure("druid_temple");
    //public static final WorldGenStructure DRUID_WELL = new WorldGenStructure("druid_well");

    public static boolean hailTrip = false;

    @Override
    public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider) {

        if( world.provider.getDimension() == 0){
            int x;
            if (random.nextBoolean()) {
                x = random.nextInt() + 6;
            } else {
                x = random.nextInt() - 6;
            }
            int z;
            if (random.nextBoolean()) {
                z = random.nextInt() + 6;
            } else {
                z = random.nextInt() - 6;
            }
            if(!hailTrip) {

                if (random.nextInt(2000) == 0) {
                    //System.out.println("yes");
                    generateStructure(DRUID_TEMPLE, world, random, chunkX, chunkZ, 0, 0, 0, BiomeDesert.class);
                    hailTrip = true;
                }
            }
                else {
                    //System.out.println("okay...");
                    //generateStructure(DRUID_WELL, world, random, chunkX, chunkZ, x, -1, z, BiomeDesert.class);
                    hailTrip = false;

            }
        }
    }
    private void generateStructure(WorldGenStructure generator, World world, Random random, int chunkx, int chunkz, int xoffset, int yoffset, int zoffset,  Class<?>... classes){
        //ArrayList<Class<?>> classList = new ArrayList<Class<?>>(Arrays.asList(classes));

        int x = (chunkx * 16) + random.nextInt(15) + xoffset;
        int z = (chunkz * 16) + random.nextInt(15) + zoffset;
        int y = getGroundFromAbove(world,x,z) + yoffset;
        BlockPos pos = new BlockPos(x,y,z);


        System.out.println("checkmark " + generator.structureName);
        generator.generate(world, random, pos);
        //guaranteedRecurseGen(generatorSecond, world, random, x ,y ,z);

    }

    //credit to sky_01
    public static int getGroundFromAbove(World world, int x, int z){
        int y = 255;
        boolean foundGround = false;
        while(!foundGround && y-->=0){
            Block blockAt = world.getBlockState(new BlockPos(x,y,z)).getBlock();
            foundGround = (blockAt == Blocks.DIRT || blockAt == Blocks.GRASS || blockAt == Blocks.GRAVEL || blockAt.getUnlocalizedName().contains("dirt")
                    || blockAt.getUnlocalizedName().contains("soil") || blockAt.getUnlocalizedName().contains("sand") || blockAt.getUnlocalizedName().contains("stone"));
        }
        return y;

    }
}
