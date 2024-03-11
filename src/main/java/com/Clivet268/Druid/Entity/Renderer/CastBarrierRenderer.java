package com.Clivet268.Druid.Entity.Renderer;

import com.Clivet268.Druid.Entity.CastBarrier;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.Vector3f;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.loading.FMLEnvironment;

@OnlyIn(Dist.CLIENT)
public class CastBarrierRenderer extends EntityRenderer<CastBarrier> {

    public static final CastBarrierRenderer.Factory INSTANCE = new CastBarrierRenderer.Factory();
    private static final ResourceLocation CAST_BARRIER = new ResourceLocation("textures/entity/druid/cast_barrier.png");
    private final CastBarrierModel<CastBarrier> model = new CastBarrierModel<>();

    public CastBarrierRenderer(EntityRendererManager renderManagerIn) {
        super(renderManagerIn);
    }

    public void render(CastBarrier entityIn, float entityYaw, float partialTicks, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int packedLightIn) {
        float f = entityIn.getAnimationProgress(partialTicks);
        if (f != 0.0F) {
            float f1 = 2.0F;
            if (f > 0.9F) {
                f1 = (float)((double)f1 * ((1.0D - (double)f) / (double)0.1F));
            }

            matrixStackIn.push();
            matrixStackIn.rotate(Vector3f.YP.rotationDegrees(90.0F - entityIn.rotationYaw));
            matrixStackIn.scale(-f1, -f1, f1);
            matrixStackIn.translate(0.0D, -0.626F, 0.0D);
            matrixStackIn.scale(0.5F, 0.5F, 0.5F);
            this.model.setRotationAngles(entityIn, f, 0.0F, 0.0F, entityIn.rotationYaw, entityIn.rotationPitch);
            IVertexBuilder ivertexbuilder = bufferIn.getBuffer(this.model.getRenderType(CAST_BARRIER));
            this.model.render(matrixStackIn, ivertexbuilder, packedLightIn, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
            matrixStackIn.pop();
            super.render(entityIn, entityYaw, partialTicks, matrixStackIn, bufferIn, packedLightIn);
        }
    }

    @Override
    public ResourceLocation getEntityTexture(CastBarrier entity) {
        return CAST_BARRIER;
    }

    private static class Factory implements IRenderFactory<CastBarrier> {
        @Override
        public EntityRenderer<CastBarrier> createRenderFor(EntityRendererManager manager) {
            //TODO needed?
            if (FMLEnvironment.dist.isDedicatedServer())
                throw new IllegalStateException("Only run this on client!");

            return new CastBarrierRenderer(manager);
        }
    }
}

