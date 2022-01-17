package com.Clivet268.Druid.Util;

import com.Clivet268.Druid.Block.DruidHeartBlock;
import com.Clivet268.Druid.Block.DruidSoulLight;
import com.Clivet268.Druid.Configs;
import com.Clivet268.Druid.Druid;
import com.Clivet268.Druid.Entity.DruidEntity;
import com.Clivet268.Druid.Entity.Renderer.RenderDruid;
import com.Clivet268.Druid.Particle.LifeParticle;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.storage.loot.LootTableList;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.ArrayList;

import static com.Clivet268.Druid.Druid.logger;

@Mod.EventBusSubscriber
public class RegistryHandler {


    //Items
    public static ArrayList<Item> ITEMS = new ArrayList<>();
    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> event) {
        event.getRegistry().registerAll(RegistryHandler.ITEMS.toArray(new Item[0]));
        logger.info("Druid Items Registered");
    }

    //Blocks
    public static ArrayList<Block> BLOCKS = new ArrayList<>();
    public static final Block DRUID_HEART = new DruidHeartBlock("druid_heart");
    public static final Block DRUID_SOUL_LIGHT = new DruidSoulLight("druid_soul_light");
    @SubscribeEvent
    public static void registerBlocks(RegistryEvent.Register<Block> event)
    {

        event.getRegistry().registerAll(RegistryHandler.BLOCKS.toArray(new Block[0]));
        logger.info("Druid Blocks Registered");
    }
    //Entities
    public static void registerEntities()
    {
        registerEntity("druid", DruidEntity.class, Configs.ENTITY_DRUID_ID, 50, 11437146, 000000);
    }
    //Particles
    public static void registerParticles()
    {
        Minecraft.getMinecraft().effectRenderer.registerParticle(268001, new LifeParticle.RegrowthFactory());
        Minecraft.getMinecraft().effectRenderer.registerParticle(268002, new LifeParticle.NewGrowthFactory());
        Minecraft.getMinecraft().effectRenderer.registerParticle(268003, new LifeParticle.GrowFactory());
    }


    private static void registerEntity(String name, Class<? extends Entity> entity, int id, int range, int color1, int color2)
    {

        EntityRegistry.registerModEntity(new ResourceLocation(Druid.MODID + ":" + name), entity, name, id, Druid.instance, range, 1, true, color1, color2);
    }
    public static void registerEntityRenders()
    {
        RenderingRegistry.registerEntityRenderingHandler(DruidEntity.class, RenderDruid::new);
    }
    //Sounds

        public static SoundEvent ENTITY_CENTAUR_AMBIENT, ENTITY_CENTAUR_HURT, ENTITY_CENTAUR_DEATH;

        public static void registerSounds()
        {
            ENTITY_CENTAUR_AMBIENT = registerSound("entity.druid.ambient");
            ENTITY_CENTAUR_HURT = registerSound("entity.druid.hurt");
            ENTITY_CENTAUR_DEATH = registerSound("entity.druid.death");
        }

        private static SoundEvent registerSound(String name)
        {
            ResourceLocation location = new ResourceLocation(Druid.MODID, name);
            SoundEvent event = new SoundEvent(location);
            event.setRegistryName(name);
            ForgeRegistries.SOUND_EVENTS.register(event);
            return event;
        }
    //Loot Table
    public static final ResourceLocation DRUID = LootTableList.register(new ResourceLocation(Druid.MODID, "entities/druid"));


    @SubscribeEvent
    @SideOnly(Side.CLIENT)
    public static void onModelRegister(ModelRegistryEvent event)
    {

        for(Block block : BLOCKS)
        {
            Druid.proxy.registerModel(Item.getItemFromBlock(block), 0, "inventory");
        }
    }
}
