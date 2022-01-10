package com.Clivet268.Druid.World.Gen;

import com.Clivet268.Druid.Util.Square;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraftforge.fml.common.IWorldGenerator;

import java.util.ArrayList;
import java.util.Random;
//TODO well
public class WorldGenCustomStructures implements IWorldGenerator {
    public static final WorldGenStructure DRUID_HUT = new WorldGenStructure("druid_hut");
    public static final WorldGenStructure DRUID_WELL = new WorldGenStructure("druid_well");
    public static final WorldGenStructure DRUID_TEMPLE = new WorldGenStructure("druid_temple");

    private static class SussyPiece{
        WorldGenStructure we;
        int siz;
        SussyPiece(WorldGenStructure win, int size){
            this.we = win;
            this.siz = size;
        }
    }

    public static final SussyPiece DH = new SussyPiece(DRUID_HUT, 4);
    public static final SussyPiece DW = new SussyPiece(DRUID_WELL, 4);
    public static final SussyPiece DT = new SussyPiece(DRUID_TEMPLE, 6);
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

                if (random.nextInt(20) == 0) {
                    //System.out.println("yes");
                    ArrayList<Square> xzes = new ArrayList<>();
                    Rotation trot = generateStructure(DT.we, world, random, chunkX, chunkZ, 0, 0, 0);
                    //int[] e = rotahteay(0,9,0,10, trot);
                    xzes.add(new Square(-DT.siz, -DT.siz, DT.siz, DT.siz));
                    int ee,eee,eeee,eeeee;

                    for(int i =random.nextInt(5) + 2; i > 0; i--) {
                        boolean areanotfound = true;
                        ee = random.nextBoolean() ? 1 : -1;
                        int xx = (ee * (DT.siz + 3+ random.nextInt(19)));
                        eee = random.nextBoolean() ? 1 : -1;
                        int xxx = (eee * (DT.siz + 3 + random.nextInt(19)));
                        eeee = random.nextBoolean() ? 1 : -1;
                        int yy = (eeee * (DT.siz + 3+ random.nextInt(19)));
                        eeeee = random.nextBoolean() ? 1 : -1;
                        int yyy = (eeeee * (DT.siz + 3+ random.nextInt(19)));


                        }

                        //generateStructure(DRUID_HUT, world, random, chunkX, chunkZ, 0, 0, (pm * (12 + random.nextInt(7))));
                    }

                    for(int i =random.nextInt(2) + 1; i > 0; i--) {
                        //pm = random.nextBoolean() ? 1 : -1;
                        //generateStructure(DRUID_WELL, world, random, chunkX, chunkZ, (pm * (12 + random.nextInt(7))), 0, (pm * (12 + random.nextInt(7))));
                    }
                }

        }


    private int[] rotahteay(int x1, int x2, int z1, int z2, Rotation r){
        int[] nett = new int[4];
        if(r == Rotation.NONE)
        {
            nett[0] = x1;
            nett[1] = x2;
            nett[2] = z1;
            nett[3] = z2;
        }
        else if(r == Rotation.CLOCKWISE_90)
        {
            nett[0] = z1;
            nett[1] = z2;
            nett[2] = x1;
            nett[3] = x2;
        }else if(r == Rotation.CLOCKWISE_180)
        {
            nett[0] = x2;
            nett[1] = x1;
            nett[2] = z2;
            nett[3] = z1;
        }else
        {
            nett[0] = z2;
            nett[1] = z1;
            nett[2] = x2;
            nett[3] = x1;
        }


        return nett;
    }

    private Rotation generateStructure(WorldGenStructure generator, World world, Random random, int chunkx, int chunkz, int xoffset, int yoffset, int zoffset){
        //ArrayList<Class<?>> classList = new ArrayList<Class<?>>(Arrays.asList(classes));

        int x = (chunkx * 16) + random.nextInt(15) + xoffset;
        int z = (chunkz * 16) + random.nextInt(15) + zoffset;
        int y = getGroundFromAbove(world,x,z) + yoffset;
        BlockPos pos = new BlockPos(x,y,z);


        System.out.println("checkmark " + generator.structureName);
        int e = random.nextInt(4);
        Rotation rotation;
        if(e==0) {
            rotation = Rotation.CLOCKWISE_90;
        }else if(e==1) {
            rotation = Rotation.CLOCKWISE_180;
        } else if(e==2) {
            rotation = Rotation.COUNTERCLOCKWISE_90;
        } else{
            rotation = Rotation.NONE;
        }
        generator.generate(world, random, pos,rotation);
        //guaranteedRecurseGen(generatorSecond, world, random, x ,y ,z);
        return  rotation;
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
