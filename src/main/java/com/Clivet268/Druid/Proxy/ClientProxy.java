package com.Clivet268.Druid.Proxy;

import com.Clivet268.Druid.Druid;
import com.Clivet268.Druid.Particle.LifeParticle;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.TextureStitchEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
@Mod.EventBusSubscriber(modid = Druid.MODID, value = Side.CLIENT)
public class ClientProxy extends CommonProxy
{

    @SubscribeEvent
    public static void texStitch(TextureStitchEvent.Pre evt) {
        TextureMap map = evt.getMap();

        map.registerSprite(new ResourceLocation(Druid.MODID, "particles/regrow"));
        map.registerSprite(new ResourceLocation(Druid.MODID, "particles/new_growth"));
        map.registerSprite(new ResourceLocation(Druid.MODID, "particles/grow"));

    }


    @Override
    public void registerModel(Item item, int metadata, String id)
    {
        ModelLoader.setCustomModelResourceLocation(item, metadata, new ModelResourceLocation(item.getRegistryName(), id));
    }
}
