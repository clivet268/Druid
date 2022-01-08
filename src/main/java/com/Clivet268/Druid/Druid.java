package com.Clivet268.Druid;

import com.Clivet268.Druid.Entity.DruidEntity;
import com.Clivet268.Druid.Entity.Renderer.RenderDruid;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

/**
 * This is the main mod class, in 1.12 this contained the preInit, init and postInit methods.
 * In 1.13 it's quite empty for basic blocks and items.
 */
@Mod(value = Druid.MODID)
public class Druid {
    public static final String MODID = "druid";

    public Druid() {

        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setupClient);

        //ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, ConfigurationHandler.spec);
    }

    public void setupClient(final FMLClientSetupEvent event) {
        System.out.println("LOADcodeBODEFODEAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
        RenderingRegistry.registerEntityRenderingHandler(DruidEntity.class, RenderDruid::new);
    }

}