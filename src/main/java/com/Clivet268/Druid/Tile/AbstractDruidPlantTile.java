package com.Clivet268.Druid.Tile;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.Random;

public class AbstractDruidPlantTile extends TileEntity implements ITickableTileEntity
{


    public BlockPos heartPos = this.pos;

    public AbstractDruidPlantTile(TileEntityType<?> tileEntityTypeIn) {
        super(tileEntityTypeIn);
    }

    public void tick()
    {

    }

    @OnlyIn(Dist.CLIENT)
    public boolean shouldRenderFace(Direction p_184313_1_)
    {
        return true;
    }

}
