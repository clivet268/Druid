package com.Clivet268.Druid.World.Gen;

import com.Clivet268.Druid.Util.Square;
import net.minecraft.block.Block;
import net.minecraft.block.BlockStoneBrick;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Biomes;
import net.minecraft.init.Blocks;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3i;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraftforge.fml.common.IWorldGenerator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
//TODO well
public class WorldGenCustomStructures implements IWorldGenerator {
    public static final Biome[] biomesnotallwed = new Biome[] {Biomes.ICE_MOUNTAINS, Biomes.TAIGA_HILLS, Biomes.COLD_TAIGA_HILLS, Biomes.DEEP_OCEAN, Biomes.SAVANNA_PLATEAU, Biomes.EXTREME_HILLS, Biomes.EXTREME_HILLS_EDGE, Biomes.EXTREME_HILLS_WITH_TREES, Biomes.MUTATED_EXTREME_HILLS_WITH_TREES, Biomes.MUTATED_EXTREME_HILLS, Biomes.ROOFED_FOREST, Biomes.REDWOOD_TAIGA_HILLS, Biomes.MUTATED_ROOFED_FOREST, Biomes.MUTATED_REDWOOD_TAIGA_HILLS, Biomes.JUNGLE, Biomes.JUNGLE_HILLS, Biomes.MUTATED_JUNGLE};
    public static final WorldGenStructure DRUID_HUT = new WorldGenStructure("druid_hut");
    public static final WorldGenStructure DRUID_WELL = new WorldGenStructure("druid_well");
    public static final WorldGenStructure DRUID_TEMPLE = new WorldGenStructure("druid_main_temple");
    public static final WorldGenStructure DRUID_TEMPLE_OG = new WorldGenStructure("druid_main_temple_overgrown");

    private static class SussyPiece{
        WorldGenStructure we;
        int x;
        int z;
        SussyPiece(WorldGenStructure win, int x, int z){
            this.we = win;
            this.x = x;
            this.z = z;
        }
    }

    int rprev = 4;

    public static final SussyPiece DH = new SussyPiece(DRUID_HUT, 8,7);
    public static final SussyPiece DW = new SussyPiece(DRUID_WELL, 5,7);
    public static final SussyPiece DT = new SussyPiece(DRUID_TEMPLE, 9, 10);
    public static final SussyPiece DTOG = new SussyPiece(DRUID_TEMPLE_OG, 9, 10);
    @Override
    public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider) {

        if( world.provider.getDimension() == 0){
            ArrayList<Biome> ebiom = new ArrayList<>(Arrays.asList(biomesnotallwed));
            if(!ebiom.contains(world.getBiome(new BlockPos(chunkX * 16, 64, chunkZ*16)))) {
                if (random.nextInt(200) == 0) {
                    if (rprev <= 0) {
                        rprev = 4;
                        //System.out.println("yes");
                        ArrayList<Square> xzes = new ArrayList<>();
                        BlockPos posespose = new BlockPos(16 * chunkX, getGroundFromAbove(world, chunkX, chunkZ), 16 * chunkZ);
                        Rotation trot = generateStructure(random.nextInt(100) == 1 ? DTOG.we : DT.we, world, random, chunkX, chunkZ, 0, -2, 0);
                        //int[] e = rotahteay(0,9,0,10, trot);
                        xzes.add(new Square(0 - 6, 0 - 6, DT.x + 6, DT.z + 6));
                        int ee, eee, eeee, eeeee;

                        for (int i = random.nextInt(5) + 2; i > 0; i--) {
                            boolean areanotfound = true;
                            ee = random.nextBoolean() ? 1 : -1;
                            int xx = (ee * (DT.x + 3 + random.nextInt(19)));
                            int xxx = xx + (ee * DH.z);
                            eee = random.nextBoolean() ? 1 : -1;
                            int zz = (eee * (DT.z + 3 + random.nextInt(19)));
                            int zzz = zz + (eee * DH.z);
                            boolean checkeee = true;
                            Square pepe = new Square(xx, zz, xxx, zzz);
                            int checkout = 100;
                            while (checkeee || checkout-- <= 0) {
                                int eggw = 0;
                                for (Square e : xzes) {
                                    while (Square.ovelapsqmark(pepe, e)) {
                                        int eeewef = (random.nextBoolean() ? 1 : -1) * (3 + random.nextInt(19));
                                        int eeewefz = (random.nextBoolean() ? 1 : -1) * (3 + random.nextInt(19));
                                        xx += eeewef;
                                        xxx += eeewef;
                                        zz += eeewefz;
                                        zzz += eeewefz;
                                        pepe = new Square(xx, zz, xxx, zzz);
                                        eggw++;
                                    }
                                }
                                checkout--;
                                checkeee = !(eggw == 0);
                            }

                            if (!(checkout <= 0)) {
                                generateStructure(DRUID_HUT, world, random, chunkX, chunkZ, xx, 0, zz);
                                //BlockPos soise = new BlockPos((chunkX * 16) + xxx, getGroundFromAbove(world, (chunkX * 16) + xxx, (chunkZ * 16) + zzz), (chunkZ * 16) + zzz);
                                /*fillMwithM(world, (chunkX * 16) + xx, getGroundFromAbove(world, (chunkX * 16) + xx, (chunkZ * 16) + zz), (chunkZ * 16) + zz,
                                        (chunkX * 16) + xxx, getGroundFromAbove(world, (chunkX * 16) + xxx, (chunkZ * 16) + zzz), (chunkZ * 16) + zzz, new Random().nextBoolean() ? Blocks.COBBLESTONE : Blocks.STONEBRICK);


                                 */
                                BlockPos posise = new BlockPos((chunkX * 16) + xxx, getGroundFromAbove(world, (chunkX * 16) + xxx, (chunkZ * 16) + zzz), (chunkZ * 16) + zzz);

                                while (((int) posise.distanceSq(new Vec3i(posespose.getX(), posise.getY() + 1, posespose.getZ()))) >= 64) {

                                    //System.out.println(((int) posise.distanceSq(new Vec3i(posise.getX(), posespose.getY(), posespose.getZ()))));
                                    //System.out.println(posise);
                                    world.notifyBlockUpdate(posise, world.getBlockState(posise), Blocks.STONEBRICK.getDefaultState().withProperty(BlockStoneBrick.VARIANT, BlockStoneBrick.EnumType.MOSSY), 3);
                                    generateBlock(world, posise.add(new Random(12319).nextBoolean() ?1 : -1 * new Random().nextInt(3) ,0 ,new Random(12319).nextBoolean() ?1 : -1 * new Random().nextInt(3)), Blocks.STONEBRICK.getDefaultState().withProperty(BlockStoneBrick.VARIANT, BlockStoneBrick.EnumType.MOSSY));
                                    generateBlock(world, posise, Blocks.STONEBRICK.getDefaultState().withProperty(BlockStoneBrick.VARIANT, BlockStoneBrick.EnumType.MOSSY));
                                    BlockPos internalYfindingPos = posise.add(posise.getX() > posespose.getX() ? -random.nextInt(5) : random.nextInt(5),
                                            0,
                                            (posise.getZ() > posespose.getZ() ? -random.nextInt(5) : random.nextInt(5)));
                                    posise = new BlockPos(internalYfindingPos.getX(), getGroundFromAbove(world, internalYfindingPos.getX(), internalYfindingPos.getZ()), internalYfindingPos.getZ());


                                }
                            }
                        }


                        for (int i = random.nextInt(2) + 1; i > 0; i--) {
                            //pm = random.nextBoolean() ? 1 : -1;
                            //generateStructure(DRUID_WELL, world, random, chunkX, chunkZ, (pm * (12 + random.nextInt(7))), 0, (pm * (12 + random.nextInt(7))));
                        }
                    }
                } else {
                    rprev--;
                }
            }ebiom.clear();;
                }

        }

    public void generateBlock(World world, BlockPos pos, IBlockState bs){
        world.setBlockState(pos, bs);


    }
        public void postGenMakePath(World world, BlockPos pos, IBlockState bs){
            generateBlock(world, pos, bs);
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

        int x = (chunkx * 16) + xoffset;
        int z = (chunkz * 16) + zoffset;
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
            if(blockAt == Blocks.WATER || blockAt == Blocks.TALLGRASS){
                y=0;
            }
            foundGround = (blockAt == Blocks.DIRT || blockAt == Blocks.GRASS || blockAt == Blocks.GRAVEL || blockAt.getUnlocalizedName().contains("dirt")
                    || blockAt.getUnlocalizedName().contains("soil") || blockAt.getUnlocalizedName().contains("sand"));

        }
        return y;

    }

    public void fillMwithM(World worldIn, int xMin, int yMin, int zMin, int xMax, int yMax, int zMax, Block insideBlockState)
    {
        ArrayList<Block> possibleBlocks = new ArrayList<>();
        possibleBlocks.add(Blocks.AIR);
        possibleBlocks.add( Blocks.TALLGRASS);
        possibleBlocks.add(Blocks.TALLGRASS);
        possibleBlocks.add(Blocks.WATER);
        possibleBlocks.add(Blocks.SNOW_LAYER);
        possibleBlocks.add(Blocks.CACTUS);
        possibleBlocks.add(Blocks.YELLOW_FLOWER);
        possibleBlocks.add(Blocks.FLOWING_WATER);
        possibleBlocks.add(Blocks.RED_FLOWER);
        for (int i = yMin; i <= yMax; ++i)
        {
            for (int j = xMin; j <= xMax; ++j)
            {
                for (int k = zMin; k <= zMax; ++k)
                {
                    if (possibleBlocks.contains(worldIn.getBlockState( new BlockPos(j, i, k)).getBlock()))
                    {

                            System.out.println("lol");
                            generateBlock(worldIn, new BlockPos(j, i, k), insideBlockState.getDefaultState());

                    }
                }
            }
        }
    }
}
