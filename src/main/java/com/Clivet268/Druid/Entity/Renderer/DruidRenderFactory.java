package com.Clivet268.Druid.Entity.Renderer;

import com.Clivet268.Druid.Entity.DruidEntity;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.loading.FMLEnvironment;

public class DruidRenderFactory implements IRenderFactory<DruidEntity> {
    public static final DruidRenderFactory instance = new DruidRenderFactory();

    @Override
    public EntityRenderer<DruidEntity> createRenderFor(EntityRendererManager manager) {
        if (FMLEnvironment.dist.isDedicatedServer())
            throw new IllegalStateException("Only run this on client!");

        return new RenderDruid(manager);
    }
}
