package com.Clivet268.Druid.Entity;

import com.Clivet268.Druid.Entity.AI.EntityAICollectDye;
import com.Clivet268.Druid.Entity.AI.EntityAIDrinkWater;
import com.Clivet268.Druid.Entity.AI.EntityAIPlaceFlowers;
import com.Clivet268.Druid.Entity.AI.EntityAIRegrow;
import com.Clivet268.Druid.Util.RegistryHandler;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemDye;
import net.minecraft.item.ItemStack;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.*;
import net.minecraft.util.datafix.DataFixer;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;
import java.util.Random;

public class DruidEntity extends EntityCreature
{
    private EntityAICollectDye entityAICollectDye;
    private EntityAIPlaceFlowers entityAIPlaceFlowers;
    private EntityAIRegrow entityAIRegrow;
    private int actionTimer;

    private static final DataParameter<Float> DATA_BREWS = EntityDataManager.createKey(DruidEntity.class, DataSerializers.FLOAT);
    private static final DataParameter<Float> DATA_WATER = EntityDataManager.createKey(DruidEntity.class, DataSerializers.FLOAT);
    private static final DataParameter<Float> DATA_DYES = EntityDataManager.createKey(DruidEntity.class, DataSerializers.FLOAT);

    public int getWater(){
        return this.dataManager.get(DATA_WATER).intValue();
    }

    public int getDyes(){
        return this.dataManager.get(DATA_DYES).intValue();
    }

    public int getBrews(){
        return this.dataManager.get(DATA_BREWS).intValue();
    }

    public void setBrews(int brews) {
        this.dataManager.set(DATA_BREWS, (float)brews);
    }

    public void setDyes(int dyes) {
        this.dataManager.set(DATA_DYES, (float)dyes);
    }

    public void setWater(int water) {

        this.dataManager.set(DATA_WATER, (float)water);
        //logger.info("set water");
    }

    public boolean shouldCollectWater() {

        if(this.dataManager.get(DATA_WATER).intValue() + this.dataManager.get(DATA_BREWS).intValue() > 10){
            setWater(16- this.dataManager.get(DATA_BREWS).intValue());
            //logger.info("should collect " + false);
            return false;

        }
        return true;
    }
    public boolean shouldCollectDye() {
        if(this.dataManager.get(DATA_DYES).intValue() + this.dataManager.get(DATA_BREWS).intValue() > 10){
            setDyes( 16- this.dataManager.get(DATA_BREWS).intValue());
            return false;
        }

        return true;
    }
    public boolean brew(){

        if(this.dataManager.get(DATA_WATER).intValue() >=1 && this.dataManager.get(DATA_DYES).intValue() >=1){
            while(this.dataManager.get(DATA_WATER).intValue() >=1 && this.dataManager.get(DATA_DYES).intValue() >=1) {
                setWater(this.dataManager.get(DATA_WATER).intValue() + 1);
                setDyes(this.dataManager.get(DATA_DYES).intValue() -1);
                setBrews(this.dataManager.get(DATA_BREWS).intValue() + 1);
                //logger.info("brew");
                this.world.playSound(this.posX, this.posY, this.posZ, SoundEvents.BLOCK_BREWING_STAND_BREW, SoundCategory.NEUTRAL, 10F, 0.5F, false);
                Minecraft.getMinecraft().effectRenderer.spawnEffectParticle(EnumParticleTypes.SPELL_INSTANT.getParticleID(), this.posX, this.posY + 5, this.posZ, new Random(69).nextDouble()/8 * (new Random().nextBoolean() ? 1:-1), new Random(69).nextDouble(), new Random(69).nextDouble()/8 * (new Random().nextBoolean() ? 1:-1));

            }
            return true;
        }
        return true;
    }
    public DruidEntity(World worldIn)

    {
        super(worldIn);
        this.setSize(0.9F, 0.9F);
    }
    protected void entityInit()
    {
        super.entityInit();
        this.dataManager.register(DATA_BREWS, (float) 0);
        this.dataManager.register(DATA_DYES, (float) 0);
        this.dataManager.register(DATA_WATER, (float) 0);
    }
    public static void registerFixesDruidDAta(DataFixer fixer, Class<?> entityClass)
    {
        EntityLiving.registerFixesMob(fixer, entityClass);
    }

    @Nullable
    @Override
    public IEntityLivingData onInitialSpawn(DifficultyInstance difficulty, @Nullable IEntityLivingData livingdata) {
        this.setHomePosAndDistance(this.getPosition(), 30);
        return super.onInitialSpawn(difficulty, livingdata);
    }

    @Override
    protected void initEntityAI()
    {

        this.entityAIPlaceFlowers = new EntityAIPlaceFlowers(this);
        this.entityAIRegrow = new EntityAIRegrow(this);

        this.tasks.addTask(0, new EntityAISwimming(this));
        this.tasks.addTask(5, new EntityAIMoveTowardsRestriction(this, 0.8D));
        this.tasks.addTask(5, new EntityAITempt(this, 0.6D, Items.DYE, false));
        this.tasks.addTask(5, new EntityAIWanderAvoidWater(this, 0.5D));
        this.tasks.addTask(4, this.entityAIPlaceFlowers);
        this.tasks.addTask(4, this.entityAIRegrow);
        this.tasks.addTask(3, new EntityAIDrinkWater(this, 0.5));
        this.tasks.addTask(6, new EntityAIWatchClosest(this, EntityPlayer.class, 6.0F));
        this.tasks.addTask(7, new EntityAILookIdle(this));
    }

    @Override
    protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(40.0D);
        this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.4D);
    }

    protected void updateAITasks()
    {

        this.actionTimer = this.entityAIPlaceFlowers.getEatingGrassTimer();
        super.updateAITasks();
    }

    public boolean processInteract(EntityPlayer player, EnumHand hand)
    {
        ItemStack itemstack = player.getHeldItem(hand);

            if (!itemstack.isEmpty())
            {
                boolean flag = this.handleEating(player, itemstack);

                //logger.info(flag);
                if (flag)
                {
                    if (!player.capabilities.isCreativeMode)
                    {
                        if(itemstack.getItem() == Items.WATER_BUCKET){
                            player.setHeldItem(hand, new ItemStack(Items.BUCKET));
                        }
                        itemstack.shrink(1);
                    }

                    return true;
                }
            }
            else return itemstack.interactWithEntity(player, this, hand);

                return false;


    }

    protected boolean handleEating(EntityPlayer player, ItemStack stack)
    {
        boolean flag = false;
        float f = 0.0F;
        Item item = stack.getItem();

        if (item instanceof ItemDye)
        {
            f = 0.5F;
            if(this.shouldCollectDye()) {
                this.setDyes(this.dataManager.get(DATA_DYES).intValue() + 1);
            }
            this.brew();
            flag = true;
        }
        if (item == Items.WATER_BUCKET)
        {
            f = 0.3F;
            if(this.shouldCollectWater()) {
                this.setWater(this.dataManager.get(DATA_WATER).intValue() + 3);
            }
            this.brew();
            flag = true;

        }
        if (this.getHealth() < this.getMaxHealth() && f > 0.0F)
        {
            this.heal(f);

        }

        /*if (flag)
        {
            this.eatingHorse();
        }

         */

        return flag;
    }

    @SideOnly(Side.CLIENT)
    public void handleStatusUpdate(byte id)
    {
        if (id == 10)
        {
            this.actionTimer = 40;
        }
        else
        {
            super.handleStatusUpdate(id);
        }
    }
    @Override
    protected boolean canDespawn()
    {
        return false;
    }

    @SideOnly(Side.CLIENT)
    public float getHeadRotationPointY(float p_70894_1_)
    {
       if(this.actionTimer <=36 &&this.actionTimer >=0){
           return 1.0f;
       }
       else{
           return  0.0f;
       }
    }

    @SideOnly(Side.CLIENT)
    public float getHeadRotationAngleX(float p_70890_1_)
    {
        if (this.actionTimer > 4 && this.actionTimer <= 36)
        {
            float f = ((float)(this.actionTimer - 4) - p_70890_1_) / 32.0F;
            return ((float)Math.PI / 5F) + ((float)Math.PI * 7F / 100F) * MathHelper.sin(f * 28.7F);
        }
        else
        {

                return 0;

        }
    }
    @SideOnly(Side.CLIENT)
    public float getBodyRotationAngleX(float p_70890_1_)
    {
        if (this.actionTimer > 4 && this.actionTimer <= 36)
        {
            float f = ((float)(this.actionTimer - 4) - p_70890_1_) / 32.0F;
            return ((float)Math.PI / 5F) + ((float)Math.PI * 7F / 100F) * MathHelper.sin(f * 28.7F);
        }
        else
        {

            return 0;

        }
    }
    public float getArmRotationAngleX(float p_70890_1_)
    {
        if (this.actionTimer > 4 && this.actionTimer <= 36)
        {
            float f = ((float)(this.actionTimer - 4) - p_70890_1_) / 32.0F;
            return ((float)Math.PI / 5F) + ((float)Math.PI * 7F / 100F) * MathHelper.sin(f * 28.7F);
        }
        else
        {

            return 0;

        }
    }

    @Override
    public float getEyeHeight()
    {
        return 0.7f;
    }


    @Override
    protected ResourceLocation getLootTable()
    {
        return RegistryHandler.DRUID;
    }

    @Override
    protected SoundEvent getAmbientSound()
    {
        return RegistryHandler.ENTITY_CENTAUR_AMBIENT;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource source)
    {
        return RegistryHandler.ENTITY_CENTAUR_HURT;
    }

    @Override
    protected SoundEvent getDeathSound()
    {
        return RegistryHandler.ENTITY_CENTAUR_DEATH;
    }
}
