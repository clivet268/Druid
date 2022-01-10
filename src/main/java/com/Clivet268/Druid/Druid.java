package com.Clivet268.Druid;

import com.Clivet268.Druid.Client.Render.TileEntityDruidHeartRenderer;
import com.Clivet268.Druid.Entity.DruidEntity;
import com.Clivet268.Druid.Proxy.CommonProxy;
import com.Clivet268.Druid.Tile.TileEntityDruidHeart;
import com.Clivet268.Druid.Util.RegistryHandler;
import com.Clivet268.Druid.World.Gen.WorldGenCustomStructures;
import net.minecraft.client.Minecraft;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
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
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityDruidHeart.class, new TileEntityDruidHeartRenderer());
    }

    @EventHandler
    public void init(FMLInitializationEvent event)
    {
        // some example code
        logger.info("Druid Loaded");
        registerDataFixers();
        RegistryHandler.registerSounds();
        RegistryHandler.registerParticles();
        GameRegistry.registerTileEntity(TileEntityDruidHeart.class, new ResourceLocation(Druid.MODID, "druid_heart"));
         }


    //Data Fixer
    public static void registerDataFixers(){
        DruidEntity.registerFixesDruidDAta(Minecraft.getMinecraft().getDataFixer(), DruidEntity.class);
    }

}
