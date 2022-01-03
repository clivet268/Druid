package com.Clivet268.Druid;

import com.Clivet268.Druid.Entity.DruidEntity;
import com.Clivet268.Druid.Proxy.CommonProxy;
import com.Clivet268.Druid.Util.RegistryHandler;
import com.Clivet268.Druid.World.Gen.WorldGenCustomStructures;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import org.apache.logging.log4j.Logger;

import java.io.File;


@Mod(modid = com.Clivet268.Druid.Druid.MODID, name = com.Clivet268.Druid.Druid.NAME, version = com.Clivet268.Druid.Druid.VERSION)
public class Druid
{
    public static final String MODID = "druid";
    public static final String NAME = "Druid";
    public static final String VERSION = "1.0.0";


    public static final String CLIENT = "com.Clivet268.Druid.Proxy.ClientProxy";
    public static final String SERVER = "com.Clivet268.Druid.Proxy.CommonProxy";

    @Mod.Instance
    public static Druid instance;

    public static Logger logger;
    public static File config;



    @SidedProxy(clientSide = CLIENT, serverSide = SERVER)
    public static CommonProxy proxy;

    @EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {

        logger = event.getModLog();
        RegistryHandler.registerEntities();
        RegistryHandler.registerEntityRenders();
        GameRegistry.registerWorldGenerator(new WorldGenCustomStructures(), 0);
    }

    @EventHandler
    public void init(FMLInitializationEvent event)
    {
        // some example code
        logger.info("Druid Loaded");
        registerDataFixers();
        RegistryHandler.registerSounds();
        RegistryHandler.registerParticles();

    }


    //Data Fixer
    public static void registerDataFixers(){
        DruidEntity.registerFixesDruidDAta(Minecraft.getMinecraft().getDataFixer(), DruidEntity.class);
    }

}
