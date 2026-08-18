package com.wildcard.delightfulbuddycards;

import com.wildcard.buddycards.Buddycards;
import com.wildcard.buddycards.block.BuddycardBoosterBoxBlock;
import com.wildcard.buddycards.core.BuddycardSet;
import com.wildcard.buddycards.item.*;
import com.wildcard.buddycards.registries.BuddycardsBlocks;
import com.wildcard.buddycards.registries.BuddycardsComponents;
import com.wildcard.buddycards.registries.BuddycardsItems;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModList;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;
import vectorwing.farmersdelight.common.block.PieBlock;

public class RegistryHandler {
    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(DelightfulBuddycards.MOD_ID);
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(DelightfulBuddycards.MOD_ID);

    public static void registerAll(IEventBus eventBus) {
        BOOSTER_BOX = BLOCKS.register("buddycard_booster_box_delightful", () -> new BuddycardBoosterBoxBlock(BuddycardsItems.DEFAULT_BUDDYCARD_REQUIREMENT, BuddycardsBlocks.BOOSTER_BOX_PROPERTIES));

        PACK = ITEMS.register("buddycard_pack_delightful", DelightfulBuddycardPackItem::new);
        BINDER = ITEMS.register("buddycard_binder_delightful", () -> new BuddycardBinderItem(BuddycardsItems.DEFAULT_BINDER_PROPERTIES, DELIGHTFUL_SET, ResourceLocation.fromNamespaceAndPath(Buddycards.MOD_ID, "textures/gui/buddycard_binder_delightful.png"), false));
        LARGE_BINDER = ITEMS.register("large_buddycard_binder_delightful", () -> new BuddycardBinderItem(BuddycardsItems.DEFAULT_BINDER_PROPERTIES, DELIGHTFUL_SET, ResourceLocation.fromNamespaceAndPath(Buddycards.MOD_ID, "textures/gui/large_buddycard_binder_delightful.png"), true));
        MEDAL = ITEMS.register("buddysteel_medal_delightful", () -> new BuddysteelSetMedalItem(MedalTypes.DELIGHTFUL_SET, DELIGHTFUL_SET, new Item.Properties().stacksTo(1).component(BuddycardsComponents.COLLECTION_TIER, 0)));

        BOOSTER_BOX_ITEM = ITEMS.register("buddycard_booster_box_delightful", () -> new BuddycardBoosterBoxItem(BOOSTER_BOX.get(), PACK, BuddycardsItems.DEFAULT_UNCOMMON_PROPERTIES));

        DICED_BUDDYCARDS = ITEMS.register("diced_buddycards", () -> new Item(new Item.Properties()));
        BUDDYBEANS = BLOCKS.register("buddybeans", () -> new CropBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.POTATOES)));
        BUDDYBEANS_ITEM = ITEMS.register("buddybeans", () ->  new BlockItem(BUDDYBEANS.get(), new Item.Properties().food(new FoodProperties.Builder().nutrition(2).saturationModifier(0.2F).build())));
        BUDDYBEAN_CRATE = BLOCKS.register("buddybean_crate", () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
        BUDDYBEAN_CRATE_ITEM = ITEMS.register("buddybean_crate", () ->  new BlockItem(BUDDYBEAN_CRATE.get(), new Item.Properties()));
        DICED_BUDDYBEANS = ITEMS.register("diced_buddybeans", () -> new Item(new Item.Properties()));
        BUDDYBEAN_PASTE = ITEMS.register("buddybean_paste", () -> new Item(new Item.Properties().craftRemainder(Items.BOWL)));
        BUDDYGUMMIES = ITEMS.register("buddygummies", () -> new Item(new Item.Properties().food(new FoodProperties.Builder().nutrition(2).saturationModifier(0.2F).fast().build())));
        BUDDYBEAN_PIE_SLICE = ITEMS.register("buddybean_pie_slice", () -> new Item((new Item.Properties()).food(new FoodProperties.Builder().nutrition(3).saturationModifier(0.5F).fast().effect(() -> new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 600, 0), 1.0F).build())));
        BUDDYCOOKIE = ITEMS.register("buddycookie", () ->  new Item((new Item.Properties()).food(new FoodProperties.Builder().nutrition(2).saturationModifier(0.3F).fast().build())));
        BUDDYBEAN_PIE = BLOCKS.register("buddybean_pie", () -> new PieBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.CAKE), BUDDYBEAN_PIE_SLICE));
        BUDDYBEAN_PIE_ITEM = ITEMS.register("buddybean_pie", () -> new BlockItem(BUDDYBEAN_PIE.get(), new Item.Properties()));

        registerCards(1, 7, Rarity.COMMON, DELIGHTFUL_REQUIREMENT);
        registerCards(8, 6, Rarity.UNCOMMON, DELIGHTFUL_REQUIREMENT);
        registerCards(14, 3, Rarity.RARE, DELIGHTFUL_REQUIREMENT);
        registerCards(17, 2, Rarity.EPIC, DELIGHTFUL_REQUIREMENT);

        BLOCKS.register(eventBus);
        ITEMS.register(eventBus);
    }

    public static final BuddycardSet DELIGHTFUL_SET = new BuddycardSet("delightful");

    public static final BuddycardsItems.BuddycardRequirement DELIGHTFUL_REQUIREMENT = () -> ModList.get().isLoaded("farmersdelight");

    public static DeferredBlock<Block> BOOSTER_BOX;

    public static DeferredItem<BuddycardPackItem> PACK;
    public static DeferredItem<BuddycardBinderItem> BINDER;
    public static DeferredItem<BuddycardBinderItem> LARGE_BINDER;
    public static DeferredItem<BuddysteelSetMedalItem> MEDAL;

    public static DeferredItem<BuddycardBoosterBoxItem> BOOSTER_BOX_ITEM;

    public static DeferredItem<Item> DICED_BUDDYCARDS;
    public static DeferredBlock<Block> BUDDYBEANS;
    public static DeferredItem<BlockItem> BUDDYBEANS_ITEM;
    public static DeferredBlock<Block> BUDDYBEAN_CRATE;
    public static DeferredItem<BlockItem> BUDDYBEAN_CRATE_ITEM;
    public static DeferredItem<Item> DICED_BUDDYBEANS;
    public static DeferredItem<Item> BUDDYBEAN_PASTE;
    public static DeferredItem<Item> BUDDYGUMMIES;
    public static DeferredItem<Item> BUDDYCOOKIE;
    public static DeferredItem<Item> BUDDYBEAN_PIE_SLICE;
    public static DeferredBlock<Block> BUDDYBEAN_PIE;
    public static DeferredItem<BlockItem> BUDDYBEAN_PIE_ITEM;

    public static void registerCards(int startValue, int amount, Rarity rarity, BuddycardsItems.BuddycardRequirement requirement) {
        for (int i = startValue; i < amount + startValue; i++) {
            int finalI = i;
            ITEMS.register("buddycard_delightful" + i, () -> new BuddycardItem(requirement, DELIGHTFUL_SET, finalI, rarity));
        }
    }
}
