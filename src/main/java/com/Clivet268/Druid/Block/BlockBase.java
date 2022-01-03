package com.Clivet268.Druid.Block;

import com.Clivet268.Druid.Druid;
import com.Clivet268.Druid.Util.RegistryHandler;
import net.minecraft.block.Block;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;

public class BlockBase extends Block 
{
    public BlockBase(String name, Material material, CreativeTabs tab)
    {
        super(material);
        setUnlocalizedName(name);
        setRegistryName(name);
        setCreativeTab(tab);

        RegistryHandler.BLOCKS.add(this);
        RegistryHandler.ITEMS.add(new ItemBlock(this).setRegistryName(name));
    }



}