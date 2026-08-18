package com.wildcard.delightfulbuddycards;

import com.wildcard.buddycards.item.BuddycardSetPackItem;
import com.wildcard.buddycards.registries.BuddycardsItems;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.items.ItemHandlerHelper;

public class DelightfulBuddycardPackItem extends BuddycardSetPackItem {
    public DelightfulBuddycardPackItem() {
        super(RegistryHandler.DELIGHTFUL_SET, 3, 1, BuddycardsItems.DEFAULT_RARITY_WEIGHTS, BuddycardsItems.DEFAULT_PACK_PROPERTIES);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        if (level instanceof ServerLevel) {
            super.use(level, player, hand);
            ItemHandlerHelper.giveItemToPlayer(player, new ItemStack(RegistryHandler.BUDDYGUMMIES.get()));
        }
        return InteractionResultHolder.success(player.getItemInHand(hand));
    }
}
