package com.Clivet268.Druid.Util;

import com.Clivet268.Druid.Druid;
import com.Clivet268.Druid.Particle.LifeParticle;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.TextureStitchEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Druid.MODID, value = Dist.CLIENT)
public class ParticleTexHandler {

    @SubscribeEvent
    public static void texStitch(TextureStitchEvent.Pre evt) {
        TextureMap map = evt.getMap();
        System.out.println("beestem");
        map.registerSprite(Minecraft.getInstance().getResourceManager(), LifeParticle.REGROW);
        map.registerSprite(Minecraft.getInstance().getResourceManager(),new ResourceLocation(Druid.MODID, "particles/new_growth"));
        map.registerSprite(Minecraft.getInstance().getResourceManager(),new ResourceLocation(Druid.MODID, "particles/grow"));

    }
}
