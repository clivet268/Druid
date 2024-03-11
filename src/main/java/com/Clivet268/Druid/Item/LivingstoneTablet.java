package com.Clivet268.Druid.Item;

import net.minecraft.entity.passive.PigEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.stats.Stats;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

//TODO This is what druids use to link themselves to their hearts and eventually
//TODO This is also hat can be created if you learn druidy things,
// given if you work ith the druids or taken if you kill a druid
// The type of the tablet ill correspond to how it is gained, maybe a super evil one if you make one AND kill druids to ket supplies for it
public abstract class LivingstoneTablet extends Item {
    public LivingstoneTablet(Item.Properties builder) {
        super(builder);
    }

    /**
     * Called to trigger the item's "innate" right click behavior. To handle when this item is used on a Block, see
     * {@link #onItemUse}.
     */
    public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
        ItemStack itemstack = playerIn.getHeldItem(handIn);
        if (worldIn.isRemote) {
            return ActionResult.resultPass(itemstack);
        } else {
            if (playerIn.isPassenger() && playerIn.getRidingEntity() instanceof PigEntity) {
                PigEntity pigentity = (PigEntity)playerIn.getRidingEntity();
                if (itemstack.getMaxDamage() - itemstack.getDamage() >= 7 && pigentity.boost()) {
                    itemstack.damageItem(7, playerIn, (p_219991_1_) -> {
                        p_219991_1_.sendBreakAnimation(handIn);
                    });
                    if (itemstack.isEmpty()) {
                        ItemStack itemstack1 = new ItemStack(Items.FISHING_ROD);
                        itemstack1.setTag(itemstack.getTag());
                        return ActionResult.resultSuccess(itemstack1);
                    }

                    return ActionResult.resultSuccess(itemstack);
                }
            }

            playerIn.addStat(Stats.ITEM_USED.get(this));
            return ActionResult.resultPass(itemstack);
        }
    }
}