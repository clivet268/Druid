package com.Clivet268.Druid;

import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

import java.io.File;

//TODO make work
public class Configs
    {
        public static Configuration config;

        public static int ENTITY_DRUID_ID = 203;


        public static void init(File file)
        {
            config = new Configuration(file);

            String category;

            category = "Entity IDs";
            config.addCustomCategoryComment(category, "Set the ID's for the entities to ensure that they don't clash with other mod's ids");
            ENTITY_DRUID_ID = config.getInt("ENTITY_DRUID_ID", category, 201, 201, 999, "Entity IDs below 201 are used by Minecraft");

        }

        public static void registerConfig(FMLPreInitializationEvent event)
        {
            Druid.config = new File(event.getModConfigurationDirectory() + "/" + Druid.MODID);
            Druid.config.mkdirs();
            init(new File(Druid.config.getPath(), Druid.MODID + ".cfg"));
        }
    }

