package com.Clivet268.Druid.Entity;

import com.Clivet268.Druid.Entity.AI.*;
import com.Clivet268.Druid.Tile.TileEntityDruidHeart;
import com.Clivet268.Druid.Util.RegistryHandler;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.boss.WitherEntity;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.DyeItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Hand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.Tags;

import javax.annotation.Nullable;

public class DruidEntity extends CreatureEntity {

    private EntityAIPlaceFlowers entityAIPlaceFlowers;
    private EntityAIRegrow entityAIRegrow;
    private int actionTimer;
    static Item[] items = new Item[0];
    public static final Ingredient temptItem = Ingredient.fromItems(Tags.Items.DYES.getAllElements().toArray(items));

    public TileEntityDruidHeart heart = null;

    private static final DataParameter<Float> DATA_BREWS = EntityDataManager.createKey(DruidEntity.class, DataSerializers.FLOAT);
    private static final DataParameter<Float> DATA_WATER = EntityDataManager.createKey(DruidEntity.class, DataSerializers.FLOAT);
    private static final DataParameter<Float> DATA_DYES = EntityDataManager.createKey(DruidEntity.class, DataSerializers.FLOAT);

    public DruidEntity(EntityType<DruidEntity> druidEntityEntityType, World world) {
        super(RegistryHandler.DRUID_ENTITY.get(), world);
    }

    public DruidEntity(World worldIn) {
        super(RegistryHandler.DRUID_ENTITY.get(), worldIn);//RegistryHandler.DRUID_ENTITY,worldIn);
        //this.set(0.9F, 0.9F);
    }

    public int getWater() {
        return this.dataManager.get(DATA_WATER).intValue();
    }

    public int getDyes() {
        return this.dataManager.get(DATA_DYES).intValue();
    }

    public int getBrews() {
        return this.dataManager.get(DATA_BREWS).intValue();
    }

    public void setBrews(int brews) {
        this.dataManager.set(DATA_BREWS, (float) brews);
    }

    public void setDyes(int dyes) {
        this.dataManager.set(DATA_DYES, (float) dyes);
    }

    public void setWater(int water) {

        this.dataManager.set(DATA_WATER, (float) water);
        //logger.info("set water");
    }

    public boolean shouldCollectWater() {

        if (this.dataManager.get(DATA_WATER).intValue() + this.dataManager.get(DATA_BREWS).intValue() > 10) {
            setWater(16 - this.dataManager.get(DATA_BREWS).intValue());
            //logger.info("should collect " + false);
            return false;

        }
        return true;
    }

    public boolean shouldCollectDye() {
        if (this.dataManager.get(DATA_DYES).intValue() + this.dataManager.get(DATA_BREWS).intValue() > 10) {
            setDyes(16 - this.dataManager.get(DATA_BREWS).intValue());
            return false;
        }

        return true;
    }

    public boolean brew() {

        if (this.dataManager.get(DATA_WATER).intValue() >= 1 && this.dataManager.get(DATA_DYES).intValue() >= 1) {
            while (this.dataManager.get(DATA_WATER).intValue() >= 1 && this.dataManager.get(DATA_DYES).intValue() >= 1) {
                setWater(this.dataManager.get(DATA_WATER).intValue() + 1);
                setDyes(this.dataManager.get(DATA_DYES).intValue() - 1);
                setBrews(this.dataManager.get(DATA_BREWS).intValue() + 1);
            }
            return true;
        }
        return true;
    }


    protected void registerData() {
        super.registerData();
        this.dataManager.register(DATA_BREWS, (float) 0);
        this.dataManager.register(DATA_DYES, (float) 0);
        this.dataManager.register(DATA_WATER, (float) 0);
    }

    @Nullable
    @Override
    public ILivingEntityData onInitialSpawn(IWorld worldIn, DifficultyInstance difficultyIn, SpawnReason reason, @Nullable ILivingEntityData spawnDataIn, @Nullable CompoundNBT dataTag) {
        spawnDataIn = super.onInitialSpawn(worldIn, difficultyIn, reason, spawnDataIn, dataTag);
        this.setHomePosAndDistance(this.getPosition(), 30);
        this.getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(40.0D);
        this.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.4D);
        return spawnDataIn;
    }

    @Override
    protected void registerGoals() {

        this.entityAIPlaceFlowers = new EntityAIPlaceFlowers(this);
        this.entityAIRegrow = new EntityAIRegrow(this);

        this.goalSelector.addGoal(1, new SwimGoal(this));
        this.goalSelector.addGoal(5, new MoveTowardsRestrictionGoal(this, 0.5D));
        this.goalSelector.addGoal(5, new TemptGoal(this, 0.5D, temptItem, false));
        this.goalSelector.addGoal(5, new WaterAvoidingRandomWalkingGoal(this, 0.5D));
        this.goalSelector.addGoal(4, this.entityAIPlaceFlowers);
        this.goalSelector.addGoal(4, this.entityAIRegrow);
        this.goalSelector.addGoal(1, new EntityAIPassiveGrow(this));
        this.goalSelector.addGoal(3, new EntityAIDrinkWater(this, 0.5));
        this.goalSelector.addGoal(6, new LookAtGoal(this, PlayerEntity.class, 6.0F));
        this.goalSelector.addGoal(7, new LookRandomlyGoal(this));
        this.goalSelector.addGoal(3, new EntityAICollectDye(this));
    }

    protected void updateAITasks() {

        this.actionTimer = this.entityAIPlaceFlowers.getEatingGrassTimer();
        super.updateAITasks();
    }

    public boolean processInteract(PlayerEntity player, Hand hand) {
        ItemStack itemstack = player.getHeldItem(hand);

        if (!itemstack.isEmpty()) {
            boolean flag = this.handleEating(player, itemstack);

            //logger.info(flag);
            if (flag) {
                if (!player.abilities.isCreativeMode) {
                    if (itemstack.getItem() == Items.WATER_BUCKET) {
                        player.setHeldItem(hand, new ItemStack(Items.BUCKET));
                    }
                    itemstack.shrink(1);
                }

                return true;
            }
        } else return itemstack.interactWithEntity(player, this, hand);

        return false;


    }

    @Override
    protected int getExperiencePoints(PlayerEntity player) {
        return 0;
    }

    //TODO the druid does not eat the dye but mehhhh
    //TODO dyes?
    protected boolean handleEating(PlayerEntity player, ItemStack stack) {
        boolean flag = false;
        float f = 0.0F;
        Item item = stack.getItem();

        if (item instanceof DyeItem) {
            f = 0.5F;
            if (this.shouldCollectDye()) {
                this.setDyes(this.dataManager.get(DATA_DYES).intValue() + 1);
            }
            this.brew();
            flag = true;
        }
        if (item == Items.WATER_BUCKET) {
            f = 0.3F;
            if (this.shouldCollectWater()) {
                this.setWater(this.dataManager.get(DATA_WATER).intValue() + 3);
            }
            this.brew();
            flag = true;

        }
        if (this.getHealth() < this.getMaxHealth() && f > 0.0F) {
            this.heal(f);

        }

        return flag;
    }

    @OnlyIn(Dist.CLIENT)
    public void handleStatusUpdate(byte id) {
        if (id == 10) {
            this.actionTimer = 40;
        } else {
            super.handleStatusUpdate(id);
        }
    }

    @Override
    public boolean canDespawn(double distanceToClosestPlayer) {
        return false;
    }


    @OnlyIn(Dist.CLIENT)
    public float getHeadRotationPointY(float p_70894_1_) {
        if (this.actionTimer <= 36 && this.actionTimer >= 0) {
            return 1.0f;
        } else {
            return 0.0f;
        }
    }

    @OnlyIn(Dist.CLIENT)
    public float getHeadRotationAngleX(float p_70890_1_) {
        if (this.actionTimer > 4 && this.actionTimer <= 36) {
            float f = ((float) (this.actionTimer - 4) - p_70890_1_) / 32.0F;
            return ((float) Math.PI / 5F) + ((float) Math.PI * 7F / 100F) * MathHelper.sin(f * 28.7F);
        } else {

            return 0;

        }
    }

    @OnlyIn(Dist.CLIENT)
    public float getBodyRotationAngleX(float p_70890_1_) {
        if (this.actionTimer > 4 && this.actionTimer <= 36) {
            float f = ((float) (this.actionTimer - 4) - p_70890_1_) / 32.0F;
            return ((float) Math.PI / 5F) + ((float) Math.PI * 7F / 100F) * MathHelper.sin(f * 28.7F);
        } else {

            return 0;

        }
    }

    public float getArmRotationAngleX(float p_70890_1_) {
        if (this.actionTimer > 4 && this.actionTimer <= 36) {
            float f = ((float) (this.actionTimer - 4) - p_70890_1_) / 32.0F;
            return ((float) Math.PI / 5F) + ((float) Math.PI * 7F / 100F) * MathHelper.sin(f * 28.7F);
        } else {

            return 0;

        }
    }

    //TODO druids hold grudges


    @Override
    public void onDeath(DamageSource cause) {
        if(heart == null) {
            //TODO different normal death?
            super.onDeath(cause);
        } else {
            if (!this.removed && !this.dead) {
                Entity entity = cause.getTrueSource();
                LivingEntity livingentity = this.getAttackingEntity();
                if (this.scoreValue >= 0 && livingentity != null) {
                    livingentity.awardKillScore(this, this.scoreValue, cause);
                }

                if (entity != null) {
                    entity.onKillEntity(this);
                }

                if (this.isSleeping()) {
                    this.wakeUp();
                }

                this.dead = true;
                this.getCombatTracker().reset();
                if (!this.world.isRemote) {
                    this.spawnDrops(cause);
                    this.createWitherRose(livingentity);
                }

                this.world.setEntityState(this, (byte) 3);
                this.setPose(Pose.DYING);
                //TODO what is the 4th param?
                this.attemptTeleport(heart.getPos().getX(), heart.getPos().getY(), heart.getPos().getZ(), true);
            }
        }

    }


    //TODO Eye height?




    @Override
    protected ResourceLocation getLootTable() {
        //return RegistryHandler.DRUID;
        return null;
    }

    @Override
    protected SoundEvent getAmbientSound() {
        return RegistryHandler.ENTITY_DRUID_AMBIENT;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource source) {
        return RegistryHandler.ENTITY_DRUID_HURT;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return RegistryHandler.ENTITY_DRUID_DEATH;
    }


//TODO make livingstone
    //TODO when a druid dies it makes a livingstone
    protected void createLivingstone(@Nullable LivingEntity p_226298_1_) {
        if (!this.world.isRemote) {
            boolean flag = false;
            if (p_226298_1_ instanceof WitherEntity) {
                if (net.minecraftforge.event.ForgeEventFactory.getMobGriefingEvent(this.world, this)) {
                    BlockPos blockpos = new BlockPos(this);
                    BlockState blockstate = Blocks.WITHER_ROSE.getDefaultState();
                    if (this.world.isAirBlock(blockpos) && blockstate.isValidPosition(this.world, blockpos)) {
                        this.world.setBlockState(blockpos, blockstate, 3);
                        flag = true;
                    }
                }

                if (!flag) {
                    ItemEntity itementity = new ItemEntity(this.world, this.getPosX(), this.getPosY(), this.getPosZ(), new ItemStack(Items.WITHER_ROSE));
                    this.world.addEntity(itementity);
                }
            }

        }
    }
}

