package com.Clivet268.Druid.Util;

import com.Clivet268.Druid.Block.*;
import com.Clivet268.Druid.Entity.CastBarrier;
import com.Clivet268.Druid.Entity.DruidEntity;
import com.Clivet268.Druid.Item.BlockItemBase;
import com.Clivet268.Druid.Particle.LifeParticle;
import com.Clivet268.Druid.Particle.RevivalParticle;
import com.Clivet268.Druid.Tile.ImprosiaTileEntity;
import com.Clivet268.Druid.Tile.TileEntityDruidHeart;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.item.Item;
import net.minecraft.particles.BasicParticleType;
import net.minecraft.particles.ParticleType;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biomes;
import net.minecraft.world.gen.feature.Feature;
import net.minecraftforge.client.event.ParticleFactoryRegisterEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import static com.Clivet268.Druid.Block.Attribute.Pure;
import static com.Clivet268.Druid.Druid.MODID;

@Mod.EventBusSubscriber(modid = MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
//This line tells forge this class has events we want to listen to, we also tell forge we want to listen to the Mod bus. (This is new in 1.13)
//@ObjectHolder(MODID) //We use ObjectHolder to let forge inject the block into our variables, this to make sure when people replace our block we use the correct one.
public class RegistryHandler {
    public static final DeferredRegister<SoundEvent> SOUNDS = new DeferredRegister<>(ForgeRegistries.SOUND_EVENTS, MODID);
    public static final DeferredRegister<Item> ITEMS = new DeferredRegister<>(ForgeRegistries.ITEMS, MODID);
    public static final DeferredRegister<Block> BLOCKS = new DeferredRegister<>(ForgeRegistries.BLOCKS, MODID);
    public static final DeferredRegister<EntityType<?>> ENTITIES = new DeferredRegister<>(ForgeRegistries.ENTITIES, MODID);
    public static final DeferredRegister<ParticleType<?>> PARTICLES = new DeferredRegister<>(ForgeRegistries.PARTICLE_TYPES, MODID);
    //TODO what style of features management is okay in 1.15? is this the better way or the current using the bus subscriber in druid main
    public static final DeferredRegister<Feature<?>> FEATURES = new DeferredRegister<>(ForgeRegistries.FEATURES, MODID);

    public static final DeferredRegister<TileEntityType<?>> TILES = new DeferredRegister<>(ForgeRegistries.TILE_ENTITIES, MODID);
    public static void init() {
        SOUNDS.register(FMLJavaModLoadingContext.get().getModEventBus());
        ENTITIES.register(FMLJavaModLoadingContext.get().getModEventBus());
        ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());
        BLOCKS.register(FMLJavaModLoadingContext.get().getModEventBus());
        PARTICLES.register(FMLJavaModLoadingContext.get().getModEventBus());
        TILES.register(FMLJavaModLoadingContext.get().getModEventBus());
        //FEATURES.register(FMLJavaModLoadingContext.get().getModEventBus());
    }

    //Entities
    public static final RegistryObject<EntityType<DruidEntity>> DRUID_ENTITY = ENTITIES.register("druid",
            () -> EntityType.Builder.<DruidEntity>create(DruidEntity::new, EntityClassification.MISC).size(0.5F, 0.5F).build("druid"));

    public static final RegistryObject<EntityType<CastBarrier>> CAST_BARRIER = ENTITIES.register("cast_barrier",
            () -> EntityType.Builder.<CastBarrier>create(CastBarrier::new, EntityClassification.MISC).size(3F, 1.5F).build("cast_barrier"));

    //Blocks
    public static final RegistryObject<Block> DRUID_HEART = BLOCKS.register("druid_heart", DruidHeartBlock::new);
    public static final RegistryObject<Block> DESERT_BRUSH = BLOCKS.register("desert_brush", BaseBushBlock::new);
    public static final RegistryObject<Block> ACRA = BLOCKS.register("acra", BaseBushBlock::new);
    public static final RegistryObject<Block> TOUGHNI = BLOCKS.register("toughni", BaseBushBlock::new);
    public static final RegistryObject<Block> ROQANA = BLOCKS.register("roqana", BaseBushBlock::new);
    public static final RegistryObject<Block> LIVINGSTONE = BLOCKS.register("livingstone", Livingstone::new);
    public static final RegistryObject<Block> IMPROSIA = BLOCKS.register("improsia", () -> new Improsia(new Attribute[]{Pure}, new double[]{1.0D}));

    //Block Items
    public static final RegistryObject<Item> DRUID_HEART_ITEM = ITEMS.register("druid_heart", () -> new BlockItemBase(DRUID_HEART.get()));
    public static final RegistryObject<Item> DESERT_BRUSH_ITEM = ITEMS.register("desert_brush", () -> new BlockItemBase(DESERT_BRUSH.get()));
    public static final RegistryObject<Item> TOUGHNI_ITEM = ITEMS.register("toughni", () -> new BlockItemBase(TOUGHNI.get()));
    public static final RegistryObject<Item> ACRA_ITEM = ITEMS.register("acra", () -> new BlockItemBase(ACRA.get()));
    public static final RegistryObject<Item> ROQANA_ITEM = ITEMS.register("roqana", () -> new BlockItemBase(ROQANA.get()));
    public static final RegistryObject<Item> IMPROSIA_ITEM = ITEMS.register("improsia", () -> new BlockItemBase(IMPROSIA.get()));
    public static final RegistryObject<Item> LIVINGSTONE_ITEM = ITEMS.register("livingstone", () -> new BlockItemBase(LIVINGSTONE.get()));


    //Tile Entities
    public static final RegistryObject<TileEntityType<ImprosiaTileEntity>> IMPROSIA_TILE =
            TILES.register("improsia", () -> TileEntityType.Builder.create(ImprosiaTileEntity::new, IMPROSIA.get()).build(null));

    public static final RegistryObject<TileEntityType<TileEntityDruidHeart>> DRUID_HEART_TILE =
            TILES.register("druid_heart", () -> TileEntityType.Builder.create(TileEntityDruidHeart::new, DRUID_HEART.get()).build(null));


    //Sounds
    public static SoundEvent ENTITY_DRUID_AMBIENT, ENTITY_DRUID_HURT, ENTITY_DRUID_DEATH, LIGHTNING_BUZZ;


    public static void registerSounds() {
        ENTITY_DRUID_AMBIENT = registerSound("entity.druid.ambient");
        ENTITY_DRUID_HURT = registerSound("entity.druid.hurt");
        ENTITY_DRUID_DEATH = registerSound("entity.druid.death");
        LIGHTNING_BUZZ = registerSound("block.lightning_buzz");
    }

    private static SoundEvent registerSound(String name) {
        ResourceLocation location = new ResourceLocation(MODID, name);
        SoundEvent event = new SoundEvent(location);
        event.setRegistryName(name);
        ForgeRegistries.SOUND_EVENTS.register(event);
        return event;
    }

    //TODO duplicious
    @SubscribeEvent
    public static void registerSounds(RegistryEvent.Register<SoundEvent> event) {
        registerSounds();
        event.getRegistry().registerAll(
                ENTITY_DRUID_AMBIENT, ENTITY_DRUID_DEATH, ENTITY_DRUID_HURT, LIGHTNING_BUZZ
        );
    }

    //TODO this way???
    //Features
    //public static final RegistryObject<Feature<?>> DRUID_HUT = FEATURES.register("druid_hut", () -> new RunDownHouseStructure(NoFeatureConfig::deserialize));


    //TODO???
    //Particles
    public static final RegistryObject<BasicParticleType> REVIVAL = PARTICLES.register("revival", () -> new BasicParticleType(false));
    public static final RegistryObject<BasicParticleType> LIFE = PARTICLES.register("life", () -> new BasicParticleType(false));

    @SubscribeEvent
    public static void registerFactories(ParticleFactoryRegisterEvent evt) {
        Minecraft.getInstance().particles.registerFactory(
                RegistryHandler.REVIVAL.get(), RevivalParticle.RegrowthFactory::new);
        Minecraft.getInstance().particles.registerFactory(
                RegistryHandler.LIFE.get(), LifeParticle.RegrowthFactory::new);
    }

    //TODO remove once structures are properly implemented
    private static final Biome[] biiiome = new Biome[]{Biomes.DESERT_HILLS, Biomes.DESERT, Biomes.PLAINS, Biomes.STONE_SHORE, Biomes.SAVANNA, Biomes.BEACH};

    public static void addSpawn() {
        for (Biome biome : biiiome) {
            biome.getSpawns(EntityClassification.CREATURE).add(new Biome.SpawnListEntry(DRUID_ENTITY.get(), 2, 2, 3));
        }
    }
}

