package com.Clivet268.Druid.Entity.Renderer;// Made with Blockbench 3.7.5
// Exported for Minecraft version 1.12
// Paste this class into your mod and generate all required imports


import com.google.common.collect.ImmutableList;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.entity.model.IHasHead;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class DruidEntityModel<T extends Entity> extends EntityModel<T> implements IHasHead {
	private float headRotationAngleX;
	private float bodyRotationAngleX;
	private float saveHead;
	private float saveArms;
	private ModelRenderer head;
	private ModelRenderer nose_r1;
	private ModelRenderer hat;
	private ModelRenderer hattip;
	private ModelRenderer cube_r1;
	private ModelRenderer cube_r2;
	private ModelRenderer cube_r3;
	private ModelRenderer rightarm;
	private ModelRenderer cube_r4;
	private ModelRenderer cube_r5;
	private ModelRenderer cube_r6;
	private ModelRenderer cube_r7;
	private ModelRenderer leftarm;
	private ModelRenderer cube_r8;
	private ModelRenderer cube_r9;
	private ModelRenderer cube_r10;
	private ModelRenderer cube_r11;
	private ModelRenderer body;
	private ModelRenderer leftleg;
	private ModelRenderer rightleg;

	public DruidEntityModel() {
		textureWidth = 64;
		textureHeight = 64;

		head = new ModelRenderer(this);
		head.setRotationPoint(0.0F, 16.0F, 2.0F);
		head.setTextureOffset( 0, 33).addBox( -3.0F, -3.0F, -4.0F, 6, 3, 4, 0.0F, false);

		nose_r1 = new ModelRenderer(this);
		nose_r1.setRotationPoint(0.0F, -1.5F, -3.25F);
		head.addChild(nose_r1);
		setRotationAngle(nose_r1, 0.7854F, 0.7854F, 0.0F);
		nose_r1.setTextureOffset( 3, 43).addBox( -0.5F, -0.5F, -0.75F, 1, 1, 1, 0.0F, false);

		hat = new ModelRenderer(this);
		hat.setRotationPoint(0.0F, 0.0F, 0.0F);
		head.addChild(hat);
		hat.setTextureOffset(0, 0).addBox( -6.0F, -4.0F, -8.0F, 12, 1, 12, 0.0F, false);
		hat.setTextureOffset(0, 13).addBox( -5.0F, -5.0F, -7.0F, 10, 1, 10, 0.0F, false);
		hat.setTextureOffset(0, 24).addBox( -4.0F, -6.0F, -6.0F, 8, 1, 8, 0.0F, false);
		hat.setTextureOffset(36, 5).addBox( 0.0F, -9.0F, -3.0F, 4, 1, 4, 0.0F, false);
		hat.setTextureOffset(36, 0).addBox( -2.0F, -8.0F, -3.0F, 6, 1, 4, 0.0F, false);
		hat.setTextureOffset(30, 13).addBox( -3.0F, -7.0F, -5.0F, 6, 1, 7, 0.0F, false);

		hattip = new ModelRenderer(this);
		hattip.setRotationPoint(6.5F, -2.25F, 0.0F);
		hat.addChild(hattip);


		cube_r1 = new ModelRenderer(this);
		cube_r1.setRotationPoint(1.5F, -2.25F, 0.0F);
		hattip.addChild(cube_r1);
		setRotationAngle(cube_r1, 0.0F, 0.0F, 0.3054F);
		cube_r1.setTextureOffset(0, 0).addBox( -5.0F, -3.0F, -2.0F, 2, 2, 3, 0.0F, false);

		cube_r2 = new ModelRenderer(this);
		cube_r2.setRotationPoint(0.0F, 0.0F, 0.0F);
		hattip.addChild(cube_r2);
		setRotationAngle(cube_r2, 0.0F, 0.0F, 0.9599F);
		cube_r2.setTextureOffset(0, 19).addBox( -5.0F, -3.0F, -2.0F, 2, 1, 3, 0.0F, false);

		cube_r3 = new ModelRenderer(this);
		cube_r3.setRotationPoint(-0.25F, 1.25F, 0.5F);
		hattip.addChild(cube_r3);
		setRotationAngle(cube_r3, 0.0F, 0.0F, 1.1781F);
		cube_r3.setTextureOffset(0, 24).addBox( -5.0F, -3.0F, -2.0F, 2, 1, 2, 0.0F, false);

		rightarm = new ModelRenderer(this);
		rightarm.setRotationPoint(-5.0F, 17.5F, 0.0F);
		rightarm.setTextureOffset(0, 5).addBox( -1.0F, -1.0F, -1.0F, 2, 4, 2, 0.0F, false);
		rightarm.setTextureOffset(7, 0).addBox( -0.5F, 3.0F, -0.5F, 1, 1, 1, 0.0F, false);

		cube_r4 = new ModelRenderer(this);
		cube_r4.setRotationPoint(-1.0F, 4.0F, -1.0F);
		rightarm.addChild(cube_r4);
		setRotationAngle(cube_r4, 0.0F, 0.0F, 0.1745F);
		cube_r4.setTextureOffset( 6, 3).addBox( -0.1F, -1.0F, 0.0F, 0, 1, 2, 0.0F, false);

		cube_r5 = new ModelRenderer(this);
		cube_r5.setRotationPoint(0.0F, 4.0F, 1.1F);
		rightarm.addChild(cube_r5);
		setRotationAngle(cube_r5, 0.1745F, 0.0F, 0.0F);
		cube_r5.setTextureOffset(8, 7).addBox( -1.0F, -1.0F, 0.0F, 2, 1, 0, 0.0F, false);

		cube_r6 = new ModelRenderer(this);
		cube_r6.setRotationPoint(1.0F, 4.0F, -1.1F);
		rightarm.addChild(cube_r6);
		setRotationAngle(cube_r6, -0.1745F, 0.0F, 0.0F);
		cube_r6.setTextureOffset(0, 11).addBox( -2.0F, -1.0F, 0.0F, 2, 1, 0, 0.0F, false);

		cube_r7 = new ModelRenderer(this);
		cube_r7.setRotationPoint(1.1F, 4.0F, -1.0F);
		rightarm.addChild(cube_r7);
		setRotationAngle(cube_r7, 0.0F, 0.0F, -0.1745F);
		cube_r7.setTextureOffset(6, 4).addBox( 0.0F, -1.0F, 0.0F, 0, 1, 2, 0.0F, false);

		leftarm = new ModelRenderer(this);
		leftarm.setRotationPoint(5.0F, 17.5F, 0.0F);
		leftarm.setTextureOffset(0, 13).addBox( -1.0F, -1.0F, -1.0F, 2, 4, 2, 0.0F, false);
		leftarm.setTextureOffset(8, 8).addBox( -0.5F, 3.0F, -0.5F, 1, 1, 1, 0.0F, false);

		cube_r8 = new ModelRenderer(this);
		cube_r8.setRotationPoint(-1.0F, 4.0F, 0.0F);
		leftarm.addChild(cube_r8);
		setRotationAngle(cube_r8, 0.0F, 0.0F, 0.1745F);
		cube_r8.setTextureOffset(7, 0).addBox( -0.1F, -1.0F, -1.0F, 0, 1, 2, 0.0F, false);

		cube_r9 = new ModelRenderer(this);
		cube_r9.setRotationPoint(1.0F, 4.0F, 0.0F);
		leftarm.addChild(cube_r9);
		setRotationAngle(cube_r9, 0.1745F, 0.0F, 0.0F);
		cube_r9.setTextureOffset( 4, 11).addBox( -2.0F, -1.0F, 1.1F, 2, 1, 0, 0.0F, false);

		cube_r10 = new ModelRenderer(this);
		cube_r10.setRotationPoint(0.0F, 3.5F, -1.0F);
		leftarm.addChild(cube_r10);
		setRotationAngle(cube_r10, -0.1745F, 0.0F, 0.0F);
		cube_r10.setTextureOffset( 8, 11).addBox( -1.0F, -0.5F, -0.1F, 2, 1, 0, 0.0F, false);

		cube_r11 = new ModelRenderer(this);
		cube_r11.setRotationPoint(1.0F, 4.0F, 0.0F);
		leftarm.addChild(cube_r11);
		setRotationAngle(cube_r11, 0.0F, 0.0F, -0.1745F);
		cube_r11.setTextureOffset(8, 8).addBox( 0.1F, -1.0F, -1.0F, 0, 1, 2, 0.0F, false);

		body = new ModelRenderer(this);
		body.setRotationPoint(0.0F, 24.0F, 0.0F);
		body.setTextureOffset(26, 27).addBox( -4.0F, -8.0F, -3.0F, 8, 6, 6, 0.0F, false);

		leftleg = new ModelRenderer(this);
		leftleg.setRotationPoint(0.0F, 24.0F, 0.0F);
		leftleg.setTextureOffset(16, 36).addBox( 0.0F, -2.0F, -2.0F, 3, 2, 4, 0.0F, false);

		rightleg = new ModelRenderer(this);
		rightleg.setRotationPoint(0.0F, 24.0F, 0.0F);
		rightleg.setTextureOffset( 30, 39).addBox( -3.0F, -2.0F, -2.0F, 3, 2, 4, 0.0F, false);
	}

	@Override
	public void render(MatrixStack matrixStackIn, IVertexBuilder bufferIn, int packedLightIn, int packedOverlayIn, float red, float green, float blue, float alpha) {

			matrixStackIn.push();
			matrixStackIn.scale(1F, 1F, 1F);
			//matrixStackIn.translate(0.0D, 2.0D, 0.0D);
			ImmutableList.of(this.head, this.rightarm, this.leftarm, this.body, this.leftleg, this.rightleg).forEach((p_228290_8_) -> {
				p_228290_8_.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
			});
			matrixStackIn.pop();


	}

	//TODO make bend over
	public void setRotationAngle(ModelRenderer ModelRenderer, float x, float y, float z) {
		ModelRenderer.rotateAngleX = x;
		ModelRenderer.rotateAngleY = y;
		ModelRenderer.rotateAngleZ = z;
	}

	public void setLivingAnimations(T entitylivingbaseIn, float limbSwing, float limbSwingAmount, float partialTickTime)
	{
		super.setLivingAnimations(entitylivingbaseIn, limbSwing, limbSwingAmount, partialTickTime);


	}

	public void setRotationAngles(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch)
	{
		this.rightarm.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI) * 2.0F * limbSwingAmount * 0.5F ;
		this.leftarm.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 2.0F * limbSwingAmount * 0.5F ;
		this.rightarm.rotateAngleZ = 0.0F;
		this.leftarm.rotateAngleZ = 0.0F;
		this.rightleg.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount ;
		this.leftleg.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount;
		this.rightleg.rotateAngleY = 0.0F;
		this.leftleg.rotateAngleY = 0.0F;
		this.rightleg.rotateAngleZ = 0.0F;
		this.leftleg.rotateAngleZ = 0.0F;
	}


	@Override
	public ModelRenderer getModelHead() {
		return this.head;
	}
}