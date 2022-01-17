package com.Clivet268.Druid.Tile;

import net.minecraft.entity.EntityLiving;
import net.minecraft.init.MobEffects;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;
import java.util.List;

public class TileEntityDruidSoulLight extends TileEntity implements ITickable
{
    @Nullable
    private Potion primaryEffect = MobEffects.REGENERATION;
    public void update()
    {
        if (this.world.getTotalWorldTime() % 80L == 0L)
        {
            this.updateBeacon();

            System.out.println("yeeteete");
        }
    }

    public void updateBeacon()
    {
        if (this.world != null)
        {
            this.addEffectsToPlayers();
        }
    }

    private void addEffectsToPlayers()
    {
        if (!this.world.isRemote && this.primaryEffect != null)
        {
            int k = this.pos.getX();
            int l = this.pos.getY();
            int i1 = this.pos.getZ();
            AxisAlignedBB axisalignedbb = (new AxisAlignedBB((double)k, (double)l, (double)i1, (double)(k + 1), (double)(l + 1), (double)(i1 + 1))).grow(90).expand(0.0D, (double)this.world.getHeight(), 0.0D);
            List<EntityLiving> list = this.world.<EntityLiving>getEntitiesWithinAABB(EntityLiving.class, axisalignedbb);

            for (EntityLiving entityplayer : list)
            {
                entityplayer.addPotionEffect(new PotionEffect(this.primaryEffect, 180, 1, true, true));
            }
        }
    }
    @Nullable
    public SPacketUpdateTileEntity getUpdatePacket()
    {
        return new SPacketUpdateTileEntity(this.pos, 3, this.getUpdateTag());
    }

    public NBTTagCompound getUpdateTag()
    {
        return this.writeToNBT(new NBTTagCompound());
    }

    @SideOnly(Side.CLIENT)
    public double getMaxRenderDistanceSquared()
    {
        return 65536.0D;
    }

    @Nullable
    private static Potion isBeaconEffect(int p_184279_0_)
    {
        Potion potion = Potion.getPotionById(p_184279_0_);
        return potion;
    }

    public void readFromNBT(NBTTagCompound compound)
    {
        super.readFromNBT(compound);
        this.primaryEffect = isBeaconEffect(compound.getInteger("Primary"));
    }

    public NBTTagCompound writeToNBT(NBTTagCompound compound)
    {
        super.writeToNBT(compound);
        compound.setInteger("Primary", Potion.getIdFromPotion(this.primaryEffect));
        return compound;
    }

}
