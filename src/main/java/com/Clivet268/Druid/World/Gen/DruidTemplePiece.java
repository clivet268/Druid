package com.Clivet268.Druid.World.Gen;

import com.Clivet268.Druid.Druid;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.Mirror;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.world.IWorld;
import net.minecraft.world.gen.Heightmap;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.structure.StructureIO;
import net.minecraft.world.gen.feature.structure.StructurePiece;
import net.minecraft.world.gen.feature.structure.TemplateStructurePiece;
import net.minecraft.world.gen.feature.template.PlacementSettings;
import net.minecraft.world.gen.feature.template.Template;
import net.minecraft.world.gen.feature.template.TemplateManager;

import java.util.List;
import java.util.Random;

public class DruidTemplePiece {
    //private static final BlockPos STRUCTURE_OFFSET = new BlockPos(4, 0, 15);
    private static final ResourceLocation[] structureSet = new ResourceLocation[]{new ResourceLocation("shipwreck/with_mast"), new ResourceLocation("shipwreck/sideways_full"), new ResourceLocation("shipwreck/sideways_fronthalf"), new ResourceLocation("shipwreck/sideways_backhalf"), new ResourceLocation("shipwreck/rightsideup_full"), new ResourceLocation("shipwreck/rightsideup_fronthalf"), new ResourceLocation("shipwreck/rightsideup_backhalf"), new ResourceLocation("shipwreck/with_mast_degraded"), new ResourceLocation("shipwreck/rightsideup_full_degraded"), new ResourceLocation("shipwreck/rightsideup_fronthalf_degraded"), new ResourceLocation("shipwreck/rightsideup_backhalf_degraded")};

    public static void registerDruidHutPieces() {
        StructureIO.registerStructureComponent(DruidTemplePiece.Piece.class, Druid.MODID + "DruidTemple");
    }

    public static void genStructure(TemplateManager templateManager, BlockPos p_204760_1_, Rotation p_204760_2_, List<StructurePiece> pieces, Random r, NoFeatureConfig conif) {
        ResourceLocation resourcelocation = structureSet[r.nextInt(structureSet.length)];
        pieces.add(new DruidTemplePiece.Piece(templateManager, resourcelocation, p_204760_1_, p_204760_2_));
    }

    public static class Piece extends TemplateStructurePiece {
        private Rotation rotatae;
        private ResourceLocation structurelocation;

        public Piece(TemplateManager p_i48904_1_, ResourceLocation p_i48904_2_, BlockPos p_i48904_3_, Rotation p_i48904_4_) {
            super(0);
            this.templatePosition = p_i48904_3_;
            this.rotatae = p_i48904_4_;
            this.structurelocation = p_i48904_2_;
            this.presetup(p_i48904_1_);
        }

        /**
         * (abstract) Helper method to write subclass data to NBT
         */
        protected void writeStructureToNBT(NBTTagCompound tagCompound) {
            super.writeStructureToNBT(tagCompound);
            tagCompound.setString("Template", this.structurelocation.toString());
            tagCompound.setString("Rot", this.rotatae.name());
        }

        /**
         * (abstract) Helper method to read subclass data from NBT
         */
        protected void readStructureFromNBT(NBTTagCompound tagCompound, TemplateManager p_143011_2_) {
            super.readStructureFromNBT(tagCompound, p_143011_2_);
            this.structurelocation = new ResourceLocation(tagCompound.getString("Template"));
            this.rotatae = Rotation.valueOf(tagCompound.getString("Rot"));
            this.presetup(p_143011_2_);
        }

        private void presetup(TemplateManager templateManager) {
            Template template = templateManager.getTemplateDefaulted(this.structurelocation);
            PlacementSettings placementsettings = (new PlacementSettings()).setRotation(this.rotatae).setReplacedBlock(Blocks.AIR).setMirror(Mirror.NONE);//.setCenterOffset(DruidHutPiece.STRUCTURE_OFFSET);
            this.setup(template, this.templatePosition, placementsettings);
        }

        protected void handleDataMarker(String function, BlockPos pos, IWorld worldIn, Random rand, MutableBoundingBox sbb) {
        }

        /**
         * second Part of Structure generating, this for example places Spiderwebs, Mob Spawners, it closes Mineshafts at
         * the end, it adds Fences...
         */
        public boolean addComponentParts(IWorld worldIn, Random randomIn, MutableBoundingBox structureBoundingBoxIn, ChunkPos p_74875_4_) {
            int i = 256;
            int j = 0;
            BlockPos blockpos = this.templatePosition.add(this.template.getSize().getX() - 1, 0, this.template.getSize().getZ() - 1);

            for(BlockPos blockpos1 : BlockPos.getAllInBox(this.templatePosition, blockpos)) {
                int k = worldIn.getHeight(Heightmap.Type.WORLD_SURFACE_WG, blockpos1.getX(), blockpos1.getZ());
                j += k;
                i = Math.min(i, k);
            }

            System.out.println("E");
            j = j / (this.template.getSize().getX() * this.template.getSize().getZ());
            int l = i - this.template.getSize().getY() / 2 - randomIn.nextInt(3);
            this.templatePosition = new BlockPos(this.templatePosition.getX(), l, this.templatePosition.getZ());
            return super.addComponentParts(worldIn, randomIn, structureBoundingBoxIn, p_74875_4_);
        }
    }
}
