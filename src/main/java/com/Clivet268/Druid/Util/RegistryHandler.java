package com.Clivet268.Druid.Util;

import com.Clivet268.Druid.Block.DruidHeartBlock;
import com.Clivet268.Druid.Druid;
import com.Clivet268.Druid.Entity.DruidEntity;
import com.google.common.base.CaseFormat;
import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemSpawnEgg;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.ObjectHolder;

import java.util.List;
import java.util.function.Function;

@Mod.EventBusSubscriber(modid = Druid.MODID, bus = Mod.EventBusSubscriber.Bus.MOD) //This line tells forge this class has events we want to listen to, we also tell forge we want to listen to the Mod bus. (This is new in 1.13)
@ObjectHolder(Druid.MODID) //We use ObjectHolder to let forge inject the block into our variables, this to make sure when people replace our block we use the correct one.
public class RegistryHandler {



    private static List<EntityType> entities = Lists.newArrayList();
    private static List<Item> spawnEggs = Lists.newArrayList();

    public static final EntityType<DruidEntity> DRUID_ENTITY = createEntity(DruidEntity.class, DruidEntity::new, 0x000000, 0xFFFFFF);

    private static EntityType<DruidEntity> createEntity(Class<? extends Entity> entityClass, Function<? super World, ? extends Entity> entityInstance, int eggPrimary, int eggSecondary) {
        ResourceLocation location = new ResourceLocation(Druid.MODID, classToString(entityClass));
        EntityType entity = EntityType.Builder.create(entityClass, entityInstance).tracker(64, 1, true).build(location.toString());
        entity.setRegistryName(location);
        //PenguinRegistry.biomes = biomes;
        entities.add(entity);
        return entity;
    }

    private static String classToString(Class<? extends Entity> entityClass) {
        System.out.println(entityClass.getSimpleName());
        return CaseFormat.UPPER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, entityClass.getSimpleName()).replace("_entity", "");
    }

    @SubscribeEvent(priority = EventPriority.LOWEST)
    public static void registerPenguins(RegistryEvent.Register<EntityType<?>> event) {
        for (EntityType entity : entities) {
            Preconditions.checkNotNull(entity.getRegistryName(), "registryName");
            event.getRegistry().register(entity);
        }
    }


    public static final Block DRUID_HEART = new DruidHeartBlock()
            .setRegistryName(Druid.MODID, "druid_heart");
    public static final Item DRUID_SPAWN_EGG = new ItemSpawnEgg(DRUID_ENTITY, 12992, 21313, (new Item.Properties()).group(ItemGroup.MISC));

    public static void makeSpawnEgg(){
        DRUID_SPAWN_EGG.setRegistryName(new ResourceLocation(Druid.MODID, "druid_spawn_egg"));


    }

    @SubscribeEvent
    public static void registerBlocks(RegistryEvent.Register<Block> event) {
        event.getRegistry().registerAll(
            DRUID_HEART
        );
    }

    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> event) {
        makeSpawnEgg();
        event.getRegistry().registerAll(
                DRUID_SPAWN_EGG,
                createItemBlockForBlock(RegistryHandler.DRUID_HEART,new Item.Properties().group(ItemGroup.DECORATIONS).maxStackSize(64))
        );
    }


    private static ItemBlock createItemBlockForBlock(Block block, Item.Properties properties) {
        return (ItemBlock) new ItemBlock(block, properties).setRegistryName(block.getRegistryName());
    }

    public static SoundEvent ENTITY_DRUID_AMBIENT, ENTITY_DRUID_HURT, ENTITY_DRUID_DEATH;

    public static void registerSounds()
    {
        ENTITY_DRUID_AMBIENT = registerSound("entity.druid.ambient");
        ENTITY_DRUID_HURT = registerSound("entity.druid.hurt");
        ENTITY_DRUID_DEATH = registerSound("entity.druid.death");
    }

    private static SoundEvent registerSound(String name)
    {
        ResourceLocation location = new ResourceLocation(Druid.MODID, name);
        SoundEvent event = new SoundEvent(location);
        event.setRegistryName(name);
        ForgeRegistries.SOUND_EVENTS.register(event);
        return event;
    }
    @SubscribeEvent
    public static void registerSounds(RegistryEvent.Register<SoundEvent> event) {
        registerSounds();
        event.getRegistry().registerAll(
                    ENTITY_DRUID_AMBIENT,ENTITY_DRUID_DEATH,ENTITY_DRUID_HURT
                );
    }



}