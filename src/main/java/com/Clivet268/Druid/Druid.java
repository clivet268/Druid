package com.Clivet268.Druid;

import com.Clivet268.Druid.Entity.DruidEntity;
import com.Clivet268.Druid.Entity.Renderer.RenderDruid;
import com.Clivet268.Druid.World.Gen.DruidTemplePiece;
import com.Clivet268.Druid.World.Gen.DruidTempleStructure;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraft.world.gen.placement.IPlacementConfig;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.ForgeRegistries;

import static net.minecraft.world.biome.Biome.PASSTHROUGH;

/**
 * This is the main mod class, in 1.12 this contained the preInit, init and postInit methods.
 * In 1.13 it's quite empty for basic blocks and items.
 */
@Mod(value = Druid.MODID)
public class Druid {
    public static final String MODID = "druid";

    public Druid() {

        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setupClient);
        //StructureIO.register
        DruidTemplePiece.registerDruidHutPieces();
        //ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, ConfigurationHandler.spec);
    }

    public void setupClient(final FMLClientSetupEvent event) {
        System.out.println("LOADcodeBODEFODEAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
        RenderingRegistry.registerEntityRenderingHandler(DruidEntity.class, RenderDruid::new);
    }

    public static final Structure<NoFeatureConfig> DRUID_TEMPLE = new DruidTempleStructure();

    @SubscribeEvent
    public static void applyFeatures(final ModConfig.Loading configEvent) {
        for (Biome biome : ForgeRegistries.BIOMES.getValues()) {
            //if (!Config.isBiomeBlacklisted(biome)) {
            biome.addFeature(GenerationStage.Decoration.SURFACE_STRUCTURES, Biome.createCompositeFeature(DRUID_TEMPLE, new NoFeatureConfig(), PASSTHROUGH, IPlacementConfig.NO_PLACEMENT_CONFIG));
            // }
        }

    }

}