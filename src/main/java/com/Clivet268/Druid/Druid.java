package com.Clivet268.Druid;

import com.Clivet268.Druid.Entity.Renderer.DruidRenderFactory;
import com.Clivet268.Druid.Particle.LifeParticle;
import com.Clivet268.Druid.Util.RegistryHandler;
import net.minecraft.client.Minecraft;
import net.minecraftforge.client.event.ParticleFactoryRegisterEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.DeferredWorkQueue;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;


@Mod(value = Druid.MODID)
public class Druid {
    public static final String MODID = "druid";

    public Druid() {

        final IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setupClient);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setupCommon);
        RegistryHandler.init();
        MinecraftForge.EVENT_BUS.register(this);
    }

    public void setupClient(final FMLClientSetupEvent event) {
        System.out.println("LOADcodeBODEFODEAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
        RenderingRegistry.registerEntityRenderingHandler(RegistryHandler.DRUID_ENTITY.get(), DruidRenderFactory.instance);
    }

    public void setupCommon(final FMLCommonSetupEvent event) {
        DeferredWorkQueue.runLater(RegistryHandler::addSpawn);
    }

}