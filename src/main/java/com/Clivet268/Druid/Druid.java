package com.Clivet268.Druid;

import com.Clivet268.Druid.Entity.Renderer.DruidRenderFactory;
import com.Clivet268.Druid.Util.RegistryHandler;
import com.Clivet268.Druid.World.Features.Features;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraft.client.renderer.color.BlockColors;
import net.minecraft.client.renderer.color.IBlockColor;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.IFeatureConfig;
import net.minecraft.world.gen.placement.IPlacementConfig;
import net.minecraft.world.gen.placement.Placement;
import net.minecraftforge.client.event.ColorHandlerEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.DeferredWorkQueue;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.IForgeRegistryEntry;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static com.Clivet268.Druid.Util.RegistryHandler.*;


@Mod(value = Druid.MODID)
public class Druid {
    public static final String MODID = "druid";

    public static final Logger LOGGER = LogManager.getLogger();

    public Druid() {
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
        for (Biome biome : ForgeRegistries.BIOMES)
        {
            biome.addStructure(Features.DRUID_VILLAGE.withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG));
            biome.addFeature(GenerationStage.Decoration.SURFACE_STRUCTURES, Features.DRUID_VILLAGE.withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG)
                    .withPlacement(Placement.NOPE.configure(IPlacementConfig.NO_PLACEMENT_CONFIG)));
        }
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
    @Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class RegistryEvents
    {

        /**
         * This method will be called by Forge when it is time for the mod to register features.
         */
        @SubscribeEvent
        public static void onRegisterFeatures(final RegistryEvent.Register<Feature<?>> event)
        {
            //registers the structures/features.
            //If you don't do this, you'll crash.
            Features.registerFeatures(event);

            LOGGER.log(Level.INFO, "features/structures registered.");
        }
    }

    /*
     * Helper method to quickly register features, blocks, items, structures, biomes, anything that can be registered.
     */
    public static <T extends IForgeRegistryEntry<T>> T register(IForgeRegistry<T> registry, T entry, String registryKey)
    {
        entry.setRegistryName(new ResourceLocation(Druid.MODID, registryKey));
        registry.register(entry);
        return entry;
    }
}
