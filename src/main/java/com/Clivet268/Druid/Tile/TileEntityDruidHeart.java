package com.Clivet268.Druid.Tile;

import com.Clivet268.Druid.Entity.DruidEntity;
import com.Clivet268.Druid.Util.RegistryHandler;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;

public class TileEntityDruidHeart extends TileEntity implements ITickableTileEntity {

    //TODO should this be usable by all entities or just the druid entity?
    public DruidEntity bound = null;
    boolean naturalState = true;

    public TileEntityDruidHeart() {
        super(RegistryHandler.DRUID_HEART_TILE.get());
    }

    public TileEntityDruidHeart(boolean natural) {
        super(RegistryHandler.DRUID_HEART_TILE.get());
        naturalState = natural;
    }



    public void tick() {
        if (naturalState){
            DruidEntity originator = new DruidEntity(this.getWorld());
            originator.setPosition(this.pos.getX(), this.pos.getY() + 1, this.pos.getZ());
            originator.heart = this;
            this.naturalState = false;
            this.bound = originator;
            this.world.addEntity(originator);
        }
        //PlayerEntity entityplayer = this.world.getClosestPlayer((float) this.pos.getX() + 0.5F, (float) this.pos.getY() + 0.5F, (float) this.pos.getZ() + 0.5F, 3.0D, false);
        else if(bound != null){
            if(!bound.isAlive()) {
                bound.revive();
            }
        }
    }
    public void bind(DruidEntity toBeBound){
        this.bound = toBeBound;
    }

    @Override
    public void remove(){
        this.bound.heart = null;
        super.remove();
    }
    //TODO should render face?

}
