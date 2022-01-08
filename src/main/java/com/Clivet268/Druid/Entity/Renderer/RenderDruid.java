package com.Clivet268.Druid.Entity.Renderer;

import com.Clivet268.Druid.Druid;
import com.Clivet268.Druid.Entity.DruidEntity;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.model.ModelEnderman;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class RenderDruid extends RenderLiving<DruidEntity>
    {
        public static final ResourceLocation TEXTURES = new ResourceLocation(Druid.MODID,"textures/entity/druid/druid.png");

        public RenderDruid(RenderManager manager)
        {
            super(manager, new DruidEntityModel(), 0.5F);

        }

        public DruidEntityModel getMainModel() {
            return (DruidEntityModel)super.getMainModel();
        }

        @Override
        protected ResourceLocation getEntityTexture(DruidEntity entity)
        {
            //Druid.logger.info(TEXTURES);

            return TEXTURES;
        }

        @Override
        protected void applyRotations(DruidEntity entityLiving, float p_77043_2_, float rotationYaw, float partialTicks)
        {
            super.applyRotations(entityLiving, p_77043_2_, rotationYaw, partialTicks);
        }
    }

