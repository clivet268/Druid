package com.Clivet268.Druid.Entity.Renderer;// Made with Blockbench 3.7.5
// Exported for Minecraft version 1.12
// Paste this class into your mod and generate all required imports


import net.minecraft.client.renderer.entity.model.ModelBase;
import net.minecraft.client.renderer.entity.model.ModelBox;
import net.minecraft.client.renderer.entity.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class DruidEntityModel extends ModelBase {
	private float headRotationAngleX;
	private float bodyRotationAngleX;
	private float saveHead;
	private float saveArms;
	private final ModelRenderer head;
	private final ModelRenderer nose_r1;
	private final ModelRenderer hat;
	private final ModelRenderer hattip;
	private final ModelRenderer cube_r1;
	private final ModelRenderer cube_r2;
	private final ModelRenderer cube_r3;
	private final ModelRenderer rightarm;
	private final ModelRenderer cube_r4;
	private final ModelRenderer cube_r5;
	private final ModelRenderer cube_r6;
	private final ModelRenderer cube_r7;
	private final ModelRenderer leftarm;
	private final ModelRenderer cube_r8;
	private final ModelRenderer cube_r9;
	private final ModelRenderer cube_r10;
	private final ModelRenderer cube_r11;
	private final ModelRenderer body;
	private final ModelRenderer leftleg;
	private final ModelRenderer rightleg;

	public DruidEntityModel() {
		textureWidth = 64;
		textureHeight = 64;

		head = new ModelRenderer(this);
		head.setRotationPoint(0.0F, 16.0F, 2.0F);
		head.cubeList.add(new ModelBox(head, 0, 33, -3.0F, -3.0F, -4.0F, 6, 3, 4, 0.0F, false));

		nose_r1 = new ModelRenderer(this);
		nose_r1.setRotationPoint(0.0F, -1.5F, -3.25F);
		head.addChild(nose_r1);
		setRotationAngle(nose_r1, 0.7854F, 0.7854F, 0.0F);
		nose_r1.cubeList.add(new ModelBox(nose_r1, 3, 43, -0.5F, -0.5F, -0.75F, 1, 1, 1, 0.0F, false));

		hat = new ModelRenderer(this);
		hat.setRotationPoint(0.0F, 0.0F, 0.0F);
		head.addChild(hat);
		hat.cubeList.add(new ModelBox(hat, 0, 0, -6.0F, -4.0F, -8.0F, 12, 1, 12, 0.0F, false));
		hat.cubeList.add(new ModelBox(hat, 0, 13, -5.0F, -5.0F, -7.0F, 10, 1, 10, 0.0F, false));
		hat.cubeList.add(new ModelBox(hat, 0, 24, -4.0F, -6.0F, -6.0F, 8, 1, 8, 0.0F, false));
		hat.cubeList.add(new ModelBox(hat, 36, 5, 0.0F, -9.0F, -3.0F, 4, 1, 4, 0.0F, false));
		hat.cubeList.add(new ModelBox(hat, 36, 0, -2.0F, -8.0F, -3.0F, 6, 1, 4, 0.0F, false));
		hat.cubeList.add(new ModelBox(hat, 30, 13, -3.0F, -7.0F, -5.0F, 6, 1, 7, 0.0F, false));

		hattip = new ModelRenderer(this);
		hattip.setRotationPoint(6.5F, -2.25F, 0.0F);
		hat.addChild(hattip);


		cube_r1 = new ModelRenderer(this);
		cube_r1.setRotationPoint(1.5F, -2.25F, 0.0F);
		hattip.addChild(cube_r1);
		setRotationAngle(cube_r1, 0.0F, 0.0F, 0.3054F);
		cube_r1.cubeList.add(new ModelBox(cube_r1, 0, 0, -5.0F, -3.0F, -2.0F, 2, 2, 3, 0.0F, false));

		cube_r2 = new ModelRenderer(this);
		cube_r2.setRotationPoint(0.0F, 0.0F, 0.0F);
		hattip.addChild(cube_r2);
		setRotationAngle(cube_r2, 0.0F, 0.0F, 0.9599F);
		cube_r2.cubeList.add(new ModelBox(cube_r2, 0, 19, -5.0F, -3.0F, -2.0F, 2, 1, 3, 0.0F, false));

		cube_r3 = new ModelRenderer(this);
		cube_r3.setRotationPoint(-0.25F, 1.25F, 0.5F);
		hattip.addChild(cube_r3);
		setRotationAngle(cube_r3, 0.0F, 0.0F, 1.1781F);
		cube_r3.cubeList.add(new ModelBox(cube_r3, 0, 24, -5.0F, -3.0F, -2.0F, 2, 1, 2, 0.0F, false));

		rightarm = new ModelRenderer(this);
		rightarm.setRotationPoint(-5.0F, 17.5F, 0.0F);
		rightarm.cubeList.add(new ModelBox(rightarm, 0, 5, -1.0F, -1.0F, -1.0F, 2, 4, 2, 0.0F, false));
		rightarm.cubeList.add(new ModelBox(rightarm, 7, 0, -0.5F, 3.0F, -0.5F, 1, 1, 1, 0.0F, false));

		cube_r4 = new ModelRenderer(this);
		cube_r4.setRotationPoint(-1.0F, 4.0F, -1.0F);
		rightarm.addChild(cube_r4);
		setRotationAngle(cube_r4, 0.0F, 0.0F, 0.1745F);
		cube_r4.cubeList.add(new ModelBox(cube_r4, 6, 3, -0.1F, -1.0F, 0.0F, 0, 1, 2, 0.0F, false));

		cube_r5 = new ModelRenderer(this);
		cube_r5.setRotationPoint(0.0F, 4.0F, 1.1F);
		rightarm.addChild(cube_r5);
		setRotationAngle(cube_r5, 0.1745F, 0.0F, 0.0F);
		cube_r5.cubeList.add(new ModelBox(cube_r5, 8, 7, -1.0F, -1.0F, 0.0F, 2, 1, 0, 0.0F, false));

		cube_r6 = new ModelRenderer(this);
		cube_r6.setRotationPoint(1.0F, 4.0F, -1.1F);
		rightarm.addChild(cube_r6);
		setRotationAngle(cube_r6, -0.1745F, 0.0F, 0.0F);
		cube_r6.cubeList.add(new ModelBox(cube_r6, 0, 11, -2.0F, -1.0F, 0.0F, 2, 1, 0, 0.0F, false));

		cube_r7 = new ModelRenderer(this);
		cube_r7.setRotationPoint(1.1F, 4.0F, -1.0F);
		rightarm.addChild(cube_r7);
		setRotationAngle(cube_r7, 0.0F, 0.0F, -0.1745F);
		cube_r7.cubeList.add(new ModelBox(cube_r7, 6, 4, 0.0F, -1.0F, 0.0F, 0, 1, 2, 0.0F, false));

		leftarm = new ModelRenderer(this);
		leftarm.setRotationPoint(5.0F, 17.5F, 0.0F);
		leftarm.cubeList.add(new ModelBox(leftarm, 0, 13, -1.0F, -1.0F, -1.0F, 2, 4, 2, 0.0F, false));
		leftarm.cubeList.add(new ModelBox(leftarm, 8, 8, -0.5F, 3.0F, -0.5F, 1, 1, 1, 0.0F, false));

		cube_r8 = new ModelRenderer(this);
		cube_r8.setRotationPoint(-1.0F, 4.0F, 0.0F);
		leftarm.addChild(cube_r8);
		setRotationAngle(cube_r8, 0.0F, 0.0F, 0.1745F);
		cube_r8.cubeList.add(new ModelBox(cube_r8, 7, 0, -0.1F, -1.0F, -1.0F, 0, 1, 2, 0.0F, false));

		cube_r9 = new ModelRenderer(this);
		cube_r9.setRotationPoint(1.0F, 4.0F, 0.0F);
		leftarm.addChild(cube_r9);
		setRotationAngle(cube_r9, 0.1745F, 0.0F, 0.0F);
		cube_r9.cubeList.add(new ModelBox(cube_r9, 4, 11, -2.0F, -1.0F, 1.1F, 2, 1, 0, 0.0F, false));

		cube_r10 = new ModelRenderer(this);
		cube_r10.setRotationPoint(0.0F, 3.5F, -1.0F);
		leftarm.addChild(cube_r10);
		setRotationAngle(cube_r10, -0.1745F, 0.0F, 0.0F);
		cube_r10.cubeList.add(new ModelBox(cube_r10, 8, 11, -1.0F, -0.5F, -0.1F, 2, 1, 0, 0.0F, false));

		cube_r11 = new ModelRenderer(this);
		cube_r11.setRotationPoint(1.0F, 4.0F, 0.0F);
		leftarm.addChild(cube_r11);
		setRotationAngle(cube_r11, 0.0F, 0.0F, -0.1745F);
		cube_r11.cubeList.add(new ModelBox(cube_r11, 8, 8, 0.1F, -1.0F, -1.0F, 0, 1, 2, 0.0F, false));

		body = new ModelRenderer(this);
		body.setRotationPoint(0.0F, 24.0F, 0.0F);
		body.cubeList.add(new ModelBox(body, 26, 27, -4.0F, -8.0F, -3.0F, 8, 6, 6, 0.0F, false));

		leftleg = new ModelRenderer(this);
		leftleg.setRotationPoint(0.0F, 24.0F, 0.0F);
		leftleg.cubeList.add(new ModelBox(leftleg, 16, 36, 0.0F, -2.0F, -2.0F, 3, 2, 4, 0.0F, false));

		rightleg = new ModelRenderer(this);
		rightleg.setRotationPoint(0.0F, 24.0F, 0.0F);
		rightleg.cubeList.add(new ModelBox(rightleg, 30, 39, -3.0F, -2.0F, -2.0F, 3, 2, 4, 0.0F, false));
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		this.head.render(f5);
		this.rightarm.render(f5);
		this.leftarm.render(f5);
		this.body.render(f5);
		this.leftleg.render(f5);
		this.rightleg.render(f5);
	}

	//TODO make bend over
	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}

	public void setLivingAnimations(EntityLivingBase entitylivingbaseIn, float limbSwing, float limbSwingAmount, float partialTickTime)
	{
		super.setLivingAnimations(entitylivingbaseIn, limbSwing, limbSwingAmount, partialTickTime);


	}

	public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor, Entity entityIn)
	{
		super.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scaleFactor, entityIn);
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


}