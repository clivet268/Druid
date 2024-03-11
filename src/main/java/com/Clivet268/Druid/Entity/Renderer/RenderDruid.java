package com.Clivet268.Druid.Entity.Renderer;

import com.Clivet268.Druid.Druid;
import com.Clivet268.Druid.Entity.DruidEntity;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.loading.FMLEnvironment;

@OnlyIn(Dist.CLIENT)
public class RenderDruid extends MobRenderer<DruidEntity, DruidEntityModel<DruidEntity>> {

    public static final Factory INSTANCE = new RenderDruid.Factory();
    public static final ResourceLocation TEXTURES = new ResourceLocation(Druid.MODID, "textures/entity/druid/druid.png");

    public RenderDruid(EntityRendererManager manager) {
        super(manager, new DruidEntityModel<>(), 0.5F);

    }

    @Override
    public ResourceLocation getEntityTexture(DruidEntity entity) {
        return TEXTURES;
    }

    private static class Factory implements IRenderFactory<DruidEntity> {
        @Override
        public EntityRenderer<DruidEntity> createRenderFor(EntityRendererManager manager) {
            //TODO needed?
            if (FMLEnvironment.dist.isDedicatedServer())
                throw new IllegalStateException("Only run this on client!");

            return new RenderDruid(manager);
        }
    }
}

