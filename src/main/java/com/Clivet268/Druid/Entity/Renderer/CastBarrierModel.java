package com.Clivet268.Druid.Entity.Renderer;
// Made with Blockbench 4.9.4
// Exported for Minecraft version 1.15 - 1.16 with Mojang mappings
// Paste this class into your mod and generate all required imports


import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

//TODO are these typings necessary
@OnlyIn(Dist.CLIENT)
public class CastBarrierModel<T extends Entity> extends EntityModel<T> {
	private final ModelRenderer bb_main;
	private final ModelRenderer cube_r1;
	private final ModelRenderer cube_r2;
	private final ModelRenderer cube_r3;
	private final ModelRenderer cube_r4;
	private final ModelRenderer cube_r5;

	//TODO what the hell is setPos
	public CastBarrierModel() {
		this.textureWidth = 64;
		this.textureHeight = 64;

		bb_main = new ModelRenderer(this);
		//bb_main.setPos(0.0F, 24.0F, 0.0F);
		bb_main.setTextureOffset(24, 0).addBox(-6.0F, -13.0F, 0.0F, 12.0F, 13.0F, 0.0F, 0.0F, false);

		cube_r1 = new ModelRenderer(this);
		//cube_r1.setPos(0.0F, 0.0F, 0.0F);
		bb_main.addChild(cube_r1);
		setRotationAngle(cube_r1, -0.6F, 0.0F, 0.0F);
		cube_r1.setTextureOffset(24, 23).addBox(-4.0F, -15.75F, -6.75F, 7.0F, 5.0F, 0.0F, 0.0F, false);

		cube_r2 = new ModelRenderer(this);
		//cube_r2.setPos(0.0F, 0.0F, 0.0F);
		bb_main.addChild(cube_r2);
		setRotationAngle(cube_r2, -0.1F, 0.0F, -0.15F);
		cube_r2.setTextureOffset(0, 0).addBox(-13.0F, -15.0F, 0.0F, 12.0F, 13.0F, 0.0F, 0.0F, false);

		cube_r3 = new ModelRenderer(this);
		//cube_r3.setPos(0.0F, 0.0F, 0.0F);
		bb_main.addChild(cube_r3);
		setRotationAngle(cube_r3, -0.75F, 0.0F, 0.15F);
		cube_r3.setTextureOffset(24, 13).addBox(1.0F, -16.0F, -8.0F, 12.0F, 5.0F, 0.0F, 0.0F, false);

		cube_r4 = new ModelRenderer(this);
		//cube_r4.setPos(0.0F, 0.0F, 0.0F);
		bb_main.addChild(cube_r4);
		setRotationAngle(cube_r4, -0.1F, 0.0F, 0.15F);
		cube_r4.setTextureOffset(0, 13).addBox(1.0F, -15.0F, 0.0F, 12.0F, 13.0F, 0.0F, 0.0F, false);

		cube_r5 = new ModelRenderer(this);
		//cube_r5.setPos(0.0F, 0.0F, 0.0F);
		bb_main.addChild(cube_r5);
		setRotationAngle(cube_r5, -0.75F, 0.0F, -0.15F);
		cube_r5.setTextureOffset(24, 18).addBox(-13.0F, -16.0F, -8.0F, 12.0F, 5.0F, 0.0F, 0.0F, false);
	}

	@Override
	public void setRotationAngles(Entity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {

	}

	@Override
	public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha){
		bb_main.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
	}

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}

}