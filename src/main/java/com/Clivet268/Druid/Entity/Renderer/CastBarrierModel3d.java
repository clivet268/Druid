
/*
// Made with Blockbench 4.9.4
// Exported for Minecraft version 1.15 - 1.16 with Mojang mappings
// Paste this class into your mod and generate all required imports


import net.minecraft.client.renderer.model.ModelRenderer;

public class CastBarrier extends EntityModel<Entity> {
	private final ModelRenderer Middle;
	private final ModelRenderer cube_r1;
	private final ModelRenderer cube_r2;
	private final ModelRenderer cube_r3;
	private final ModelRenderer cube_r4;
	private final ModelRenderer cube_r5;
	private final ModelRenderer Left;
	private final ModelRenderer cube_r6;
	private final ModelRenderer cube_r7;
	private final ModelRenderer cube_r8;
	private final ModelRenderer cube_r9;
	private final ModelRenderer cube_r10;
	private final ModelRenderer cube_r11;
	private final ModelRenderer cube_r12;
	private final ModelRenderer cube_r13;
	private final ModelRenderer cube_r14;
	private final ModelRenderer cube_r15;
	private final ModelRenderer cube_r16;
	private final ModelRenderer Right;
	private final ModelRenderer cube_r17;
	private final ModelRenderer cube_r18;
	private final ModelRenderer cube_r19;
	private final ModelRenderer cube_r20;
	private final ModelRenderer cube_r21;
	private final ModelRenderer cube_r22;
	private final ModelRenderer Back;
	private final ModelRenderer cube_r23;
	private final ModelRenderer cube_r24;
	private final ModelRenderer cube_r25;
	private final ModelRenderer cube_r26;

	public CastBarrier() {
		texWidth = 64;
		texHeight = 64;

		Middle = new ModelRenderer(this);
		Middle.setPos(0.0F, 24.0F, 0.0F);
		Middle.texOffs(0, 8).addBox(-1.0F, -13.0F, -1.0F, 2.0F, 13.0F, 2.0F, 0.0F, false);
		Middle.texOffs(31, 33).addBox(-2.0F, -2.0F, 0.0F, 1.0F, 2.0F, 1.0F, 0.0F, false);

		cube_r1 = new ModelRenderer(this);
		cube_r1.setPos(0.0F, 0.0F, 0.0F);
		Middle.addChild(cube_r1);
		setRotationAngle(cube_r1, -2.7925F, 0.0F, 0.0F);
		cube_r1.texOffs(16, 0).addBox(-3.0F, 2.5F, -5.5F, 1.0F, 3.0F, 1.0F, 0.0F, false);

		cube_r2 = new ModelRenderer(this);
		cube_r2.setPos(0.0F, 0.0F, 0.0F);
		Middle.addChild(cube_r2);
		setRotationAngle(cube_r2, -2.3126F, 0.0F, 0.0F);
		cube_r2.texOffs(20, 12).addBox(-2.75F, 2.75F, -10.75F, 1.0F, 1.0F, 4.0F, 0.0F, false);

		cube_r3 = new ModelRenderer(this);
		cube_r3.setPos(0.0F, 0.0F, 0.0F);
		Middle.addChild(cube_r3);
		setRotationAngle(cube_r3, -2.138F, 0.0F, 0.0F);
		cube_r3.texOffs(6, 33).addBox(-2.25F, -0.25F, -7.75F, 1.0F, 5.0F, 1.0F, 0.0F, false);

		cube_r4 = new ModelRenderer(this);
		cube_r4.setPos(0.0F, 0.0F, 0.0F);
		Middle.addChild(cube_r4);
		setRotationAngle(cube_r4, -0.9595F, -0.0334F, -0.0281F);
		cube_r4.texOffs(9, 0).addBox(-2.25F, -10.0F, -8.0F, 1.0F, 4.0F, 1.0F, 0.0F, false);

		cube_r5 = new ModelRenderer(this);
		cube_r5.setPos(0.0F, 0.0F, 0.0F);
		Middle.addChild(cube_r5);
		setRotationAngle(cube_r5, -0.3054F, 0.0F, 0.0F);
		cube_r5.texOffs(16, 15).addBox(-1.75F, -11.25F, -3.0F, 1.0F, 8.0F, 2.0F, 0.0F, false);

		Left = new ModelRenderer(this);
		Left.setPos(0.0F, 24.0F, 0.0F);
		Left.texOffs(26, 6).addBox(-1.0F, -8.0F, 2.0F, 2.0F, 4.0F, 2.0F, 0.0F, false);
		Left.texOffs(0, 28).addBox(-1.0F, -6.0F, 1.0F, 2.0F, 6.0F, 1.0F, 0.0F, false);
		Left.texOffs(30, 22).addBox(-0.5F, -8.0F, 4.0F, 1.0F, 2.0F, 2.0F, 0.0F, false);
		Left.texOffs(12, 24).addBox(-0.25F, -10.0F, 2.75F, 1.0F, 2.0F, 2.0F, 0.0F, false);
		Left.texOffs(19, 32).addBox(0.75F, -14.5F, 3.0F, 1.0F, 6.0F, 1.0F, 0.0F, false);
		Left.texOffs(11, 30).addBox(0.75F, -12.5F, 4.0F, 1.0F, 1.0F, 3.0F, 0.0F, false);
		Left.texOffs(27, 33).addBox(-1.25F, -7.0F, 6.75F, 1.0F, 2.0F, 1.0F, 0.0F, false);

		cube_r6 = new ModelRenderer(this);
		cube_r6.setPos(0.0F, 0.0F, 0.0F);
		Left.addChild(cube_r6);
		setRotationAngle(cube_r6, -0.829F, 0.0F, 0.0F);
		cube_r6.texOffs(0, 23).addBox(0.75F, -13.25F, -3.0F, 1.0F, 1.0F, 4.0F, 0.0F, false);

		cube_r7 = new ModelRenderer(this);
		cube_r7.setPos(0.0F, 0.0F, 0.0F);
		Left.addChild(cube_r7);
		setRotationAngle(cube_r7, 0.0436F, 0.0F, 0.0F);
		cube_r7.texOffs(26, 29).addBox(1.0F, -13.0F, 6.75F, 1.0F, 1.0F, 3.0F, 0.0F, false);

		cube_r8 = new ModelRenderer(this);
		cube_r8.setPos(0.0F, 0.0F, 0.0F);
		Left.addChild(cube_r8);
		setRotationAngle(cube_r8, -0.0436F, 0.0F, 0.0F);
		cube_r8.texOffs(0, 0).addBox(0.0F, -11.75F, 3.25F, 1.0F, 1.0F, 7.0F, 0.0F, false);

		cube_r9 = new ModelRenderer(this);
		cube_r9.setPos(0.0F, 0.0F, 0.0F);
		Left.addChild(cube_r9);
		setRotationAngle(cube_r9, -0.3491F, 0.0F, 0.0F);
		cube_r9.texOffs(31, 29).addBox(-0.5F, -9.5F, 5.75F, 1.0F, 1.0F, 2.0F, 0.0F, false);

		cube_r10 = new ModelRenderer(this);
		cube_r10.setPos(0.0F, 0.0F, 0.0F);
		Left.addChild(cube_r10);
		setRotationAngle(cube_r10, -0.0873F, 0.0F, 0.0F);
		cube_r10.texOffs(6, 24).addBox(-1.5F, -8.0F, 4.75F, 1.0F, 1.0F, 4.0F, 0.0F, false);

		cube_r11 = new ModelRenderer(this);
		cube_r11.setPos(0.0F, 0.0F, 0.0F);
		Left.addChild(cube_r11);
		setRotationAngle(cube_r11, 0.0873F, 0.0F, 0.0F);
		cube_r11.texOffs(24, 17).addBox(-1.25F, -9.0F, 4.75F, 1.0F, 1.0F, 4.0F, 0.0F, false);

		cube_r12 = new ModelRenderer(this);
		cube_r12.setPos(0.0F, 0.0F, 0.0F);
		Left.addChild(cube_r12);
		setRotationAngle(cube_r12, 0.0F, 0.0F, -0.0873F);
		cube_r12.texOffs(16, 12).addBox(1.75F, -14.75F, 4.0F, 2.0F, 1.0F, 1.0F, 0.0F, false);

		cube_r13 = new ModelRenderer(this);
		cube_r13.setPos(0.0F, 0.0F, 0.0F);
		Left.addChild(cube_r13);
		setRotationAngle(cube_r13, 0.7854F, 0.0F, 0.0F);
		cube_r13.texOffs(23, 32).addBox(0.5F, -10.5F, 10.5F, 1.0F, 5.0F, 1.0F, 0.0F, false);

		cube_r14 = new ModelRenderer(this);
		cube_r14.setPos(0.0F, 0.0F, 0.0F);
		Left.addChild(cube_r14);
		setRotationAngle(cube_r14, -2.0944F, 0.0F, 0.0F);
		cube_r14.texOffs(31, 26).addBox(-0.25F, -3.5F, -5.75F, 1.0F, 1.0F, 2.0F, 0.0F, false);

		cube_r15 = new ModelRenderer(this);
		cube_r15.setPos(0.0F, 0.0F, 0.0F);
		Left.addChild(cube_r15);
		setRotationAngle(cube_r15, -0.2182F, 0.0F, 0.0F);
		cube_r15.texOffs(28, 0).addBox(-0.25F, -5.5F, 5.0F, 1.0F, 1.0F, 3.0F, 0.0F, false);

		cube_r16 = new ModelRenderer(this);
		cube_r16.setPos(0.0F, 0.0F, 0.0F);
		Left.addChild(cube_r16);
		setRotationAngle(cube_r16, -0.6109F, 0.0F, 0.0F);
		cube_r16.texOffs(8, 8).addBox(-0.5F, -6.5F, -1.0F, 1.0F, 1.0F, 6.0F, 0.0F, false);

		Right = new ModelRenderer(this);
		Right.setPos(-1.0F, 24.0F, 3.0F);
		Right.texOffs(8, 8).addBox(0.0F, -5.0F, -5.0F, 2.0F, 5.0F, 1.0F, 0.0F, false);
		Right.texOffs(0, 0).addBox(-1.0F, -5.0F, -5.0F, 1.0F, 5.0F, 2.0F, 0.0F, false);
		Right.texOffs(16, 26).addBox(-1.0F, -6.0F, -7.0F, 2.0F, 3.0F, 2.0F, 0.0F, false);
		Right.texOffs(32, 4).addBox(-1.0F, -8.0F, -7.0F, 2.0F, 2.0F, 1.0F, 0.0F, false);
		Right.texOffs(22, 17).addBox(-0.75F, -9.75F, -7.0F, 1.0F, 2.0F, 2.0F, 0.0F, false);
		Right.texOffs(22, 0).addBox(-0.75F, -12.75F, -6.0F, 1.0F, 3.0F, 1.0F, 0.0F, false);
		Right.texOffs(22, 1).addBox(-1.0F, -10.0F, -9.75F, 1.0F, 1.0F, 4.0F, 0.0F, false);
		Right.texOffs(33, 0).addBox(-1.0F, -8.0F, -8.0F, 2.0F, 1.0F, 1.0F, 0.0F, false);

		cube_r17 = new ModelRenderer(this);
		cube_r17.setPos(0.0F, 0.0F, 0.0F);
		Right.addChild(cube_r17);
		setRotationAngle(cube_r17, 0.3491F, 0.0F, 0.0F);
		cube_r17.texOffs(22, 6).addBox(-1.25F, -8.0F, -7.0F, 1.0F, 3.0F, 1.0F, 0.0F, false);

		cube_r18 = new ModelRenderer(this);
		cube_r18.setPos(0.0F, 0.0F, 0.0F);
		Right.addChild(cube_r18);
		setRotationAngle(cube_r18, 0.5672F, 0.0F, 0.0F);
		cube_r18.texOffs(9, 0).addBox(-0.75F, -9.5F, -7.0F, 1.0F, 1.0F, 5.0F, 0.0F, false);

		cube_r19 = new ModelRenderer(this);
		cube_r19.setPos(0.0F, 0.0F, 0.0F);
		Right.addChild(cube_r19);
		setRotationAngle(cube_r19, -0.0873F, 0.0F, 0.0F);
		cube_r19.texOffs(26, 12).addBox(-0.5F, -7.0F, -13.0F, 1.0F, 1.0F, 3.0F, 0.0F, false);
		cube_r19.texOffs(25, 23).addBox(-0.5F, -7.0F, -10.0F, 1.0F, 2.0F, 3.0F, 0.0F, false);
		cube_r19.texOffs(6, 24).addBox(-0.5F, -14.0F, -9.75F, 1.0F, 2.0F, 1.0F, 0.0F, false);
		cube_r19.texOffs(16, 0).addBox(-0.5F, -12.0F, -10.75F, 1.0F, 1.0F, 4.0F, 0.0F, false);

		cube_r20 = new ModelRenderer(this);
		cube_r20.setPos(0.0F, 0.0F, 0.0F);
		Right.addChild(cube_r20);
		setRotationAngle(cube_r20, -0.1745F, 0.0F, 0.0F);
		cube_r20.texOffs(31, 12).addBox(-0.5F, -9.25F, -14.0F, 1.0F, 1.0F, 2.0F, 0.0F, false);

		cube_r21 = new ModelRenderer(this);
		cube_r21.setPos(0.0F, 0.0F, 0.0F);
		Right.addChild(cube_r21);
		setRotationAngle(cube_r21, -0.2182F, 0.0F, 0.0F);
		cube_r21.texOffs(18, 21).addBox(-0.75F, -8.25F, -13.25F, 1.0F, 1.0F, 4.0F, 0.0F, false);

		cube_r22 = new ModelRenderer(this);
		cube_r22.setPos(0.0F, 0.0F, 0.0F);
		Right.addChild(cube_r22);
		setRotationAngle(cube_r22, -0.7418F, 0.0F, 0.0F);
		cube_r22.texOffs(16, 6).addBox(-1.0F, -7.5F, -12.0F, 1.0F, 3.0F, 1.0F, 0.0F, false);

		Back = new ModelRenderer(this);
		Back.setPos(0.0F, 24.0F, 0.0F);
		

		cube_r23 = new ModelRenderer(this);
		cube_r23.setPos(0.0F, 0.0F, 0.0F);
		Back.addChild(cube_r23);
		setRotationAngle(cube_r23, -0.5672F, 0.0F, 0.0F);
		cube_r23.texOffs(0, 23).addBox(1.5F, -5.5F, -5.5F, 1.0F, 3.0F, 1.0F, 0.0F, false);

		cube_r24 = new ModelRenderer(this);
		cube_r24.setPos(0.0F, 0.0F, 0.0F);
		Back.addChild(cube_r24);
		setRotationAngle(cube_r24, -0.4363F, 0.0F, 0.0F);
		cube_r24.texOffs(16, 6).addBox(1.75F, -7.5F, -6.5F, 1.0F, 2.0F, 4.0F, 0.0F, false);
		cube_r24.texOffs(24, 22).addBox(2.0F, -7.25F, -2.5F, 1.0F, 2.0F, 1.0F, 0.0F, false);
		cube_r24.texOffs(33, 15).addBox(2.0F, -10.25F, -1.5F, 1.0F, 5.0F, 1.0F, 0.0F, false);
		cube_r24.texOffs(6, 29).addBox(2.0F, -9.25F, -0.5F, 1.0F, 1.0F, 3.0F, 0.0F, false);

		cube_r25 = new ModelRenderer(this);
		cube_r25.setPos(0.0F, 0.0F, 0.0F);
		Back.addChild(cube_r25);
		setRotationAngle(cube_r25, -0.4378F, 0.0791F, -0.037F);
		cube_r25.texOffs(21, 28).addBox(2.0F, -10.25F, 1.25F, 1.0F, 1.0F, 3.0F, 0.0F, false);

		cube_r26 = new ModelRenderer(this);
		cube_r26.setPos(0.0F, 0.0F, 0.0F);
		Back.addChild(cube_r26);
		setRotationAngle(cube_r26, -0.2182F, 0.0F, 0.0F);
		cube_r26.texOffs(8, 15).addBox(1.0F, -6.5F, -1.75F, 1.0F, 6.0F, 3.0F, 0.0F, false);
	}

	@Override
	public void setupAnim(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch){
		//previously the render function, render code was moved to a method below
	}

	@Override
	public void renderToBuffer(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha){
		Middle.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
		Left.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
		Right.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
		Back.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
	}

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.xRot = x;
		modelRenderer.yRot = y;
		modelRenderer.zRot = z;
	}
}
*/
