package com.Clivet268.Druid.World.Gen;

import com.Clivet268.Druid.Druid;
import com.Clivet268.Druid.Util.IStructure;
import net.minecraft.block.state.IBlockState;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraft.world.gen.structure.template.PlacementSettings;
import net.minecraft.world.gen.structure.template.Template;
import net.minecraft.world.gen.structure.template.TemplateManager;

import java.util.Random;

public class WorldGenStructure extends WorldGenerator implements IStructure {

    private PlacementSettings settings = new PlacementSettings().setChunk(null).setIgnoreEntities(false);


    public String structureName;

    public WorldGenStructure(String name){
        structureName = name;
    }

    public boolean generate(World worldIn, Random rand, BlockPos position, Rotation rot) {
        this.settings.setRotation(rot);
        generate(worldIn, rand, position);
        return true;
    }

    @Override
    public boolean generate(World worldIn, Random rand, BlockPos position) {
        generateStructure(worldIn, position);
        return true;
    }
    public void generateStructure(World world, BlockPos pos){
        MinecraftServer mServer= world.getMinecraftServer();
        TemplateManager manager = worldserver.getStructureTemplateManager();
        ResourceLocation location = new ResourceLocation(Druid.MODID, structureName);
        Template template = manager.get(mServer, location);
        if(template != null){
            IBlockState state = world.getBlockState(pos);
            world.notifyBlockUpdate(pos, state, state, 3);
            template.addBlocksToWorld(world, pos, settings);
        }



    }
}
