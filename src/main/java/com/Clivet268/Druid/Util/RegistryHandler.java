package com.Clivet268.Druid.Util;

import com.Clivet268.Druid.Block.DruidHeartBlock;
import com.Clivet268.Druid.Druid;
import com.Clivet268.Druid.Entity.DruidEntity;
import com.Clivet268.Druid.Item.BlockItemBase;
import com.Clivet268.Druid.Particle.LifeParticle;
import com.google.common.base.CaseFormat;
import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.ParticleManager;
import net.minecraft.command.impl.DeOpCommand;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.projectile.EggEntity;
import net.minecraft.item.BlockItem;
import net.minecraft.item.EggItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.particles.BasicParticleType;
import net.minecraft.particles.ParticleType;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import net.minecraftforge.client.event.ParticleFactoryRegisterEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.ObjectHolder;

import java.util.List;
import java.util.function.Function;

import static com.Clivet268.Druid.Druid.MODID;

@Mod.EventBusSubscriber(modid = MODID, bus = Mod.EventBusSubscriber.Bus.MOD) //This line tells forge this class has events we want to listen to, we also tell forge we want to listen to the Mod bus. (This is new in 1.13)
//@ObjectHolder(MODID) //We use ObjectHolder to let forge inject the block into our variables, this to make sure when people replace our block we use the correct one.
public class RegistryHandler {
   public static final DeferredRegister<SoundEvent> SOUNDS = new DeferredRegister<>(ForgeRegistries.SOUND_EVENTS, MODID);
   public static final DeferredRegister<Item> ITEMS = new DeferredRegister<>(ForgeRegistries.ITEMS, MODID);
    public static final DeferredRegister<Block> BLOCKS = new DeferredRegister<>(ForgeRegistries.BLOCKS, MODID);
    public static final DeferredRegister<EntityType<?>> ENTITIES = new DeferredRegister<>(ForgeRegistries.ENTITIES, MODID);
   public static final  DeferredRegister<ParticleType<?>> PARTICLES = new DeferredRegister<>(ForgeRegistries.PARTICLE_TYPES, MODID);

    public static void init() {
        System.out.println(DeOpCommand.class.getSimpleName() + " cringe");
        SOUNDS.register(FMLJavaModLoadingContext.get().getModEventBus());
        ENTITIES.register(FMLJavaModLoadingContext.get().getModEventBus());
        ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());
        BLOCKS.register(FMLJavaModLoadingContext.get().getModEventBus());
        PARTICLES.register(FMLJavaModLoadingContext.get().getModEventBus());
    }
    //Entities
    public static final RegistryObject<EntityType<DruidEntity>> DRUID_ENTITY = ENTITIES.register("druid",
            () -> EntityType.Builder.<DruidEntity>create(DruidEntity::new, EntityClassification.MISC).size(0.5F, 0.5F).build("druid"));

    //Blocks
    public static final RegistryObject<Block> DRUID_HEART = BLOCKS.register("druid_heart", DruidHeartBlock::new);

    //Block Items
    public static final RegistryObject<Item> DRUID_HEART_ITEM = ITEMS.register("druid_heart", () -> new BlockItemBase(DRUID_HEART.get()));
    public static SoundEvent ENTITY_DRUID_AMBIENT, ENTITY_DRUID_HURT, ENTITY_DRUID_DEATH;

    public static void registerSounds()
    {
        ENTITY_DRUID_AMBIENT = registerSound("entity.druid.ambient");
        ENTITY_DRUID_HURT = registerSound("entity.druid.hurt");
        ENTITY_DRUID_DEATH = registerSound("entity.druid.death");
    }

    private static SoundEvent registerSound(String name)
    {
        ResourceLocation location = new ResourceLocation(MODID, name);
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

    //Particles
    public static final RegistryObject<BasicParticleType> REGROW = PARTICLES.register("regrow", () -> new BasicParticleType(false));

    @SubscribeEvent
    public static void registerFactories(ParticleFactoryRegisterEvent event) {
        ParticleManager particles = Minecraft.getInstance().particles;

        particles.registerFactory(REGROW.get(), LifeParticle.RegrowthFactory::new);
    }
}

