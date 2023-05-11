package com.Clivet268.Druid;

import com.Clivet268.Druid.Entity.Renderer.DruidRenderFactory;
import com.Clivet268.Druid.Util.RegistryHandler;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraft.client.renderer.color.BlockColors;
import net.minecraft.client.renderer.color.IBlockColor;
import net.minecraftforge.client.event.ColorHandlerEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.DeferredWorkQueue;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

import static com.Clivet268.Druid.Util.RegistryHandler.*;


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
        RenderTypeLookup.setRenderLayer(RegistryHandler.DESERT_BRUSH.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(RegistryHandler.TOUGHNI.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(RegistryHandler.ACRA.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(ROQANA.get(), RenderType.getCutout());
    }

    public void setupCommon(final FMLCommonSetupEvent event) {
        DeferredWorkQueue.runLater(RegistryHandler::addSpawn);
    }

    //TODO not working and not sure why
    @SubscribeEvent
    public static void registerItemColorHandlers(ColorHandlerEvent.Item event) {
        registerBlockColorHandler(event.getBlockColors(), (state, world, pos, tintIndex) -> {
            int r = (int) (Math.random() * 255);
            int g = (int) (Math.random() * 255);
            int b = (int) (Math.random() * 255);
            return r << 16 | g << 8 | b;
        }, TOUGHNI.get(), ACRA.get(), DESERT_BRUSH.get(), ROQANA.get());
    }

    public static void registerBlockColorHandler(BlockColors blockColors, IBlockColor blockColor, Block... blocks) {
        blockColors.register(blockColor, blocks);
    }
}