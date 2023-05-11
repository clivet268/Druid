package com.Clivet268.Druid.Block;

import com.Clivet268.Druid.Tile.AbstractBushBlockTileEntity;
import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.state.BooleanProperty;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class BaseBushBlock extends BushBlock implements ITileEntityProvider {


    public static final BooleanProperty BEARING = MoreStateProperties.BEARING;
    Map<Attribute, Double> attributes = new HashMap<>();

    public BaseBushBlock() {
        super(Properties.create(Material.PLANTS).doesNotBlockMovement());
    }

    static final VoxelShape SHAPE = Block.makeCuboidShape(2.0D, 0.0D, 2.0D, 14.0D, 13.0D, 14.0D);


    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        return SHAPE;
    }

    /**
     * Whether this IGrowable can grow
     */
    public boolean canGrow(IBlockReader worldIn, BlockPos pos, BlockState state, boolean isClient) {
        return false;
    }

    public boolean canUseBonemeal(World worldIn, Random rand, BlockPos pos, BlockState state) {
        return false;
    }

    public void grow(ServerWorld worldIn, Random rand, BlockPos pos, BlockState state) {
        //TODO

    }

    protected boolean isValidGround(BlockState state, IBlockReader worldIn, BlockPos pos) {
        Block block = state.getBlock();
        return block == Blocks.SAND || block == Blocks.RED_SAND || block == Blocks.DIRT || block == Blocks.COARSE_DIRT || block == Blocks.PODZOL;
    }

    /**
     * Get the OffsetType for this Block. Determines if the model is rendered slightly offset.
     */
    public OffsetType getOffsetType() {
        return OffsetType.XZ;
    }

    public BaseBushBlock addAttribute(Attribute attribute, double intin) {
        attributes.put(attribute, intin);
        return this;
    }

    public double getValue(Attribute sin) {
        if (this.attributes.containsKey(sin)) {
            return attributes.get(sin);
        }
        return 0;
    }

    public Map<Attribute, Double> getAttributes() {
        return this.attributes;
    }




    @Override
    public ItemStack getItem(IBlockReader worldIn, BlockPos pos, BlockState state) {
        ItemStack itemstack = super.getItem(worldIn, pos, state);
        AbstractBushBlockTileEntity tile = (AbstractBushBlockTileEntity) worldIn.getTileEntity(pos);
        CompoundNBT compoundnbt = tile.saveToNbt(new CompoundNBT());
        if (!compoundnbt.isEmpty()) {
            itemstack.setTagInfo("PlantAttributes", compoundnbt);
        }

        return itemstack;
    }


    @Override
    public TileEntity createNewTileEntity(IBlockReader worldIn) {
        return null;
    }

    @Override
    public void onBlockHarvested(World worldIn, BlockPos pos, BlockState state, PlayerEntity player) {
        TileEntity tileentity = worldIn.getTileEntity(pos);
        if (tileentity instanceof AbstractBushBlockTileEntity) {
            ItemStack itemstack = new ItemStack(this);
            AbstractBushBlockTileEntity tile = (AbstractBushBlockTileEntity) tileentity;
                CompoundNBT compoundnbt = tile.saveToNbt(new CompoundNBT());
                if (!compoundnbt.isEmpty()) {
                    itemstack.setTagInfo("PlantAttributes", compoundnbt);
                }

                ItemEntity itementity = new ItemEntity(worldIn, (double)pos.getX(), (double)pos.getY(), (double)pos.getZ(), itemstack);
                itementity.setDefaultPickupDelay();
                worldIn.addEntity(itementity);
        }

        super.onBlockHarvested(worldIn, pos, state, player);
    }

    @Override
    public void onBlockPlacedBy(World worldIn, BlockPos pos, BlockState state, LivingEntity placer, ItemStack stack) {
        AbstractBushBlockTileEntity t = ((AbstractBushBlockTileEntity)worldIn.getTileEntity(pos));
        if (t != null)
            this.attributes = t.getAttributes();
    }
}
