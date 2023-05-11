package com.Clivet268.Druid.Tile;

import com.Clivet268.Druid.Block.Attribute;
import com.Clivet268.Druid.Util.RegistryHandler;
import net.minecraft.item.DyeColor;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.*;


import javax.annotation.Nullable;
import java.util.HashMap;
import java.util.Map;

public abstract class AbstractBushBlockTileEntity extends TileEntity implements ITickableTileEntity {

    //TODO this could be made an interface and or dosent need to be used twice accross the block and tile entity
    //TODO is this double saving NBT data?
    Map<Attribute, Double> attributes = new HashMap<>();

    public AbstractBushBlockTileEntity() {
        super(RegistryHandler.IMPROSIA_TILE.get());
    }

    public void tick() {
        //this.moveCollidedEntities();
    }

    public AbstractBushBlockTileEntity addAttribute(Attribute attribute, double intin) {
        attributes.put(attribute, intin);
        return this;
    }

    public AbstractBushBlockTileEntity setAttributes(Map<Attribute, Double> attributesin) {
        this.attributes = attributesin;
        return this;
    }

    public double getValue(Attribute sin) {
        if (this.attributes.containsKey(sin)) {
            return attributes.get(sin);
        }
        return 0;
    }



    public Map<Attribute, Double> getAttributes() {
        return this.attributes;
    }

    public void read(CompoundNBT compound) {
        super.read(compound);
        this.loadFromNbt(compound);
    }

    public CompoundNBT write(CompoundNBT compound) {
        super.write(compound);
        return this.saveToNbt(compound);
    }

    public void loadFromNbt(CompoundNBT compound) {
        String s = compound.getString("attributes");
        String[] st = s.split(" ");
        String v = compound.getString("attributevals");
        String[] vt = v.split(" ");
        for (int i = 0; i < st.length; i++) {
            attributes.put(Attribute.valueOf(st[i]), Double.parseDouble(vt[i]));
        }

    }

    public CompoundNBT saveToNbt(CompoundNBT compound) {
        String s = "";
        String v = "";
        for (Attribute a : attributes.keySet()) {
            s = s + a.toString() + " ";
            v = v + " " + attributes.get(a);
        }
        compound.putString("attributes", s);
        compound.putString("attributevals", v);
        return compound;
    }


}
