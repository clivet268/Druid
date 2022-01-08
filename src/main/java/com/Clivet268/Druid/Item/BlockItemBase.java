package com.Clivet268.Druid.Item;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemGroup;

public class BlockItemBase extends ItemBlock {
        public BlockItemBase(Block block ) {
            super(block, new Item.Properties().group(ItemGroup.DECORATIONS));
        }
    }

