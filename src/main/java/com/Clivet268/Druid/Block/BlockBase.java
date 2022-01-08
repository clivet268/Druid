package com.Clivet268.Druid.Block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;

public class BlockBase extends Block
{
    public BlockBase(Material matin)
    {
        super(Properties.create(matin));

        //RegistryHandler.BLOCKS.add(this);
        //RegistryHandler.ITEMS.add(new BlockItemBase(this).setRegistryName(name));
    }



}