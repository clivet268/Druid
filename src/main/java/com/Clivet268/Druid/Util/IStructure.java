package com.Clivet268.Druid.Util;

import net.minecraft.world.WorldServer;
import net.minecraftforge.fml.common.FMLCommonHandler;

public interface IStructure {
    WorldServer worldserver = FMLCommonHandler.instance().getMinecraftServerInstance().getWorld(0);
}
