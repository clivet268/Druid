package com.Clivet268.Druid.Entity.Renderer;

import com.Clivet268.Druid.Tile.TileEntityDruidHeart;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.*;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class DruidHeartRenderer extends TileEntityRenderer<TileEntityDruidHeart> {
    private static final ResourceLocation DRAGON_FIREBALL_TEXTURE = new ResourceLocation("textures/entity/enderdragon/dragon_fireball.png");
    private static final RenderType renderType = RenderType.getEntityCutoutNoCull(DRAGON_FIREBALL_TEXTURE);

    public DruidHeartRenderer(TileEntityRendererDispatcher rendererDispatcher) {
        super(rendererDispatcher);
    }

    public void render(TileEntityDruidHeart tileEntityIn, float partialTicks, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int combinedLightIn, int combinedOverlayIn) {
        matrixStackIn.push();
        matrixStackIn.scale(0.5F, 0.5F, 0.5F);
        Quaternion q = this.renderDispatcher.renderInfo.getRotation().copy();
        q.set(0, q.getY(), 0, q.getW());
        matrixStackIn.rotate(q);
        matrixStackIn.rotate(Vector3f.YP.rotationDegrees(180.0F));
        MatrixStack.Entry matrixstack$entry = matrixStackIn.getLast();
        Matrix4f matrix4f = matrixstack$entry.getMatrix();
        Matrix3f matrix3f = matrixstack$entry.getNormal();
        IVertexBuilder ivertexbuilder = bufferIn.getBuffer(renderType);
        buildVertex(ivertexbuilder, matrix4f, matrix3f, combinedLightIn, 0.0F, 0, 0, 1);
        buildVertex(ivertexbuilder, matrix4f, matrix3f, combinedLightIn, 1.0F, 0, 1, 1);
        buildVertex(ivertexbuilder, matrix4f, matrix3f, combinedLightIn, 1.0F, 1, 1, 0);
        buildVertex(ivertexbuilder, matrix4f, matrix3f, combinedLightIn, 0.0F, 1, 0, 0);
        matrixStackIn.pop();
    }

    private static void buildVertex(IVertexBuilder iVertexBuilder, Matrix4f matrix4f, Matrix3f matrix3f, int lightmapuv, float x, int y, int u, int v) {
        iVertexBuilder.pos(matrix4f, x - 1f, y + 0.5f, -1f)
                .color(255, 255, 255, 255)
                .tex((float)u, (float)v)
                .overlay(OverlayTexture.NO_OVERLAY)
                .lightmap(lightmapuv)
                .normal(matrix3f, 0.0F, 0.5F, 0.0F)
                .endVertex();
    }
}