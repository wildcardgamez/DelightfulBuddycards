package com.wildcard.delightfulbuddycards;

import com.wildcard.buddycards.Buddycards;
import com.wildcard.buddycards.block.BuddycardBoosterBoxBlock;
import com.wildcard.buddycards.block.CardStandBlock;
import com.wildcard.buddycards.block.PlaymatBlock;
import com.wildcard.buddycards.core.BuddycardSet;
import com.wildcard.buddycards.gear.BuddycardsToolTier;
import com.wildcard.buddycards.item.*;
import com.wildcard.buddycards.registries.BuddycardsBlocks;
import com.wildcard.buddycards.registries.BuddycardsItems;
import net.minecraft.core.registries.Registries;
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
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import vectorwing.farmersdelight.common.block.PieBlock;
import vectorwing.farmersdelight.common.item.KnifeItem;

import java.util.function.Supplier;

public class RegistryHandler {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(Registries.BLOCK, DelightfulBuddycards.MOD_ID);
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(Registries.ITEM, DelightfulBuddycards.MOD_ID);

    public static void init() {
        BOOSTER_BOX = BLOCKS.register("buddycard_booster_box_delightful", () -> new BuddycardBoosterBoxBlock(BuddycardsItems.DEFAULT_BUDDYCARD_REQUIREMENT, BuddycardsBlocks.BOOSTER_BOX_PROPERTIES));
        PLAYMAT = registerPlaymat("playmat_delightful", () -> new PlaymatBlock(BuddycardsBlocks.PLAYMAT_PROPERTIES));

        PACK = ITEMS.register("buddycard_pack_delightful", DelightfulBuddycardPackItem::new);
        BINDER = ITEMS.register("buddycard_binder_delightful", () -> new BuddycardBinderItem(BuddycardsItems.DEFAULT_BINDER_PROPERTIES, DELIGHTFUL_SET, new ResourceLocation(Buddycards.MOD_ID, "textures/gui/buddycard_binder_delightful.png"), false));
        LARGE_BINDER = ITEMS.register("large_buddycard_binder_delightful", () -> new BuddycardBinderItem(BuddycardsItems.DEFAULT_BINDER_PROPERTIES, DELIGHTFUL_SET, new ResourceLocation(Buddycards.MOD_ID, "textures/gui/large_buddycard_binder_delightful.png"), true));
        MEDAL = ITEMS.register("buddysteel_medal_delightful", () -> new BuddysteelSetMedalItem(MedalTypes.DELIGHTFUL_SET, DELIGHTFUL_SET, BuddycardsItems.DEFAULT_CURIO_PROPERTIES));
        LUMINIS_MEDAL = ITEMS.register("luminis_medal_delightful", () -> new LuminisSetMedalItem(MedalTypes.DELIGHTFUL_SET, DELIGHTFUL_SET, BuddycardsItems.DEFAULT_CURIO_PROPERTIES));
        ZYLEX_MEDAL = ITEMS.register("zylex_medal_delightful", () -> new ZylexSetMedalItem(MedalTypes.DELIGHTFUL_SET, DELIGHTFUL_SET, BuddycardsItems.DEFAULT_CURIO_PROPERTIES));

        BOOSTER_BOX_ITEM = ITEMS.register("buddycard_booster_box_delightful", () -> new BuddycardBoosterBoxItem(BOOSTER_BOX.get(), PACK, BuddycardsItems.DEFAULT_UNCOMMON_PROPERTIES));
        PLAYMAT_ITEM = ITEMS.register("playmat_delightful", () -> new SetBasedBlockItem(PLAYMAT.get(), BuddycardsItems.DEFAULT_PROPERTIES, DELIGHTFUL_SET));

        DICED_BUDDYCARDS = ITEMS.register("diced_buddycards", () -> new Item(new Item.Properties()));
        BUDDYBEANS = BLOCKS.register("buddybeans", () -> new CropBlock(BlockBehaviour.Properties.copy(Blocks.POTATOES)));
        BUDDYBEANS_ITEM = ITEMS.register("buddybeans", () ->  new BlockItem(BUDDYBEANS.get(), new Item.Properties().food(new FoodProperties.Builder().nutrition(2).saturationMod(0.2F).build())));
        BUDDYBEAN_CRATE = BLOCKS.register("buddybean_crate", () -> new Block(BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
        BUDDYBEAN_CRATE_ITEM = ITEMS.register("buddybean_crate", () ->  new BlockItem(BUDDYBEAN_CRATE.get(), new Item.Properties()));
        DICED_BUDDYBEANS = ITEMS.register("diced_buddybeans", () -> new Item(new Item.Properties()));
        BUDDYBEAN_PASTE = ITEMS.register("buddybean_paste", () -> new Item(new Item.Properties().craftRemainder(Items.BOWL)));
        BUDDYGUMMIES = ITEMS.register("buddygummies", () -> new Item(new Item.Properties().food(new FoodProperties.Builder().nutrition(2).saturationMod(0.2F).fast().build())));
        BUDDYBEAN_PIE_SLICE = ITEMS.register("buddybean_pie_slice", () -> new Item((new Item.Properties()).food(new FoodProperties.Builder().nutrition(3).saturationMod(0.5F).fast().effect(() -> new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 600, 0), 1.0F).build())));
        BUDDYCOOKIE = ITEMS.register("buddycookie", () ->  new Item((new Item.Properties()).food(new FoodProperties.Builder().nutrition(2).saturationMod(0.3F).build())));
        BUDDYBEAN_PIE = BLOCKS.register("buddybean_pie", () -> new PieBlock(BlockBehaviour.Properties.copy(Blocks.CAKE), BUDDYBEAN_PIE_SLICE));
        BUDDYBEAN_PIE_ITEM = ITEMS.register("buddybean_pie", () -> new BlockItem(BUDDYBEAN_PIE.get(), new Item.Properties()));

        BUDDYSTEEL_KNIFE = ITEMS.register("buddysteel_knife", () -> new KnifeItem(BuddycardsToolTier.BUDDYSTEEL, 0.5F, -2.0F, new Item.Properties()));
        CHARGED_BUDDYSTEEL_KNIFE = ITEMS.register("charged_buddysteel_knife", () -> new KnifeItem(BuddycardsToolTier.CHARGED_BUDDYSTEEL, 0.5F, -2.0F, new Item.Properties()));
        CRIMSON_BUDDYSTEEL_KNIFE = ITEMS.register("crimson_buddysteel_knife", () -> new KnifeItem(BuddycardsToolTier.CRIMSON_BUDDYSTEEL, 0.5F, -2.0F, new Item.Properties()));
        VOID_BUDDYSTEEL_KNIFE = ITEMS.register("void_buddysteel_knife", () -> new KnifeItem(BuddycardsToolTier.VOID_BUDDYSTEEL, 0.5F, -2.0F, new Item.Properties()));
        PERFECT_BUDDYSTEEL_KNIFE = ITEMS.register("perfect_buddysteel_knife", () -> new KnifeItem(BuddycardsToolTier.PERFECT_BUDDYSTEEL, 0.5F, -2.0F, new Item.Properties()));
        TRUE_PERFECT_BUDDYSTEEL_KNIFE = ITEMS.register("true_perfect_buddysteel_knife", () -> new KnifeItem(BuddycardsToolTier.TRUE_PERFECT_BUDDYSTEEL, 0.5F, -2.0F, new Item.Properties()));

        registerCards(1, 7, Rarity.COMMON, DELIGHTFUL_REQUIREMENT);
        registerCards(8, 6, Rarity.UNCOMMON, DELIGHTFUL_REQUIREMENT);
        registerCards(14, 3, Rarity.RARE, DELIGHTFUL_REQUIREMENT);
        registerCards(17, 2, Rarity.EPIC, DELIGHTFUL_REQUIREMENT);

        BLOCKS.register(FMLJavaModLoadingContext.get().getModEventBus());
        ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());
    }

    public static final BuddycardSet DELIGHTFUL_SET = new BuddycardSet("delightful");

    public static final BuddycardsItems.BuddycardRequirement DELIGHTFUL_REQUIREMENT = () -> ModList.get().isLoaded("farmersdelight");

    public static RegistryObject<Block> BOOSTER_BOX;
    public static RegistryObject<PlaymatBlock> PLAYMAT;

    public static RegistryObject<BuddycardPackItem> PACK;
    public static RegistryObject<BuddycardBinderItem> BINDER;
    public static RegistryObject<BuddycardBinderItem> LARGE_BINDER;
    public static RegistryObject<BuddysteelSetMedalItem> MEDAL;
    public static RegistryObject<LuminisSetMedalItem> LUMINIS_MEDAL;
    public static RegistryObject<ZylexSetMedalItem> ZYLEX_MEDAL;

    public static RegistryObject<BuddycardBoosterBoxItem> BOOSTER_BOX_ITEM;
    public static RegistryObject<BlockItem> PLAYMAT_ITEM;

    public static RegistryObject<Item> DICED_BUDDYCARDS;
    public static RegistryObject<Block> BUDDYBEANS;
    public static RegistryObject<BlockItem> BUDDYBEANS_ITEM;
    public static RegistryObject<Block> BUDDYBEAN_CRATE;
    public static RegistryObject<BlockItem> BUDDYBEAN_CRATE_ITEM;
    public static RegistryObject<Item> DICED_BUDDYBEANS;
    public static RegistryObject<Item> BUDDYBEAN_PASTE;
    public static RegistryObject<Item> BUDDYGUMMIES;
    public static RegistryObject<Item> BUDDYCOOKIE;
    public static RegistryObject<Item> BUDDYBEAN_PIE_SLICE;
    public static RegistryObject<Block> BUDDYBEAN_PIE;
    public static RegistryObject<BlockItem> BUDDYBEAN_PIE_ITEM;

    public static RegistryObject<Item> BUDDYSTEEL_KNIFE;
    public static RegistryObject<Item> CHARGED_BUDDYSTEEL_KNIFE;
    public static RegistryObject<Item> CRIMSON_BUDDYSTEEL_KNIFE;
    public static RegistryObject<Item> VOID_BUDDYSTEEL_KNIFE;
    public static RegistryObject<Item> PERFECT_BUDDYSTEEL_KNIFE;
    public static RegistryObject<Item> TRUE_PERFECT_BUDDYSTEEL_KNIFE;

    public static void registerCards(int startValue, int amount, Rarity rarity, BuddycardsItems.BuddycardRequirement requirement) {
        for (int i = startValue; i < amount + startValue; i++) {
            int finalI = i;
            ITEMS.register("buddycard_delightful" + finalI, () -> new BuddycardItem(requirement, DELIGHTFUL_SET, finalI, rarity, BuddycardsItems.DEFAULT_CARD_PROPERTIES, 2, 1, BuddycardsItems.DEFAULT_NO_ABILITIES));
        }
    }

    public static RegistryObject<PlaymatBlock> registerPlaymat(String id, Supplier<PlaymatBlock> supplier) {
        RegistryObject<PlaymatBlock> playmat = BLOCKS.register(id, supplier);
        BuddycardsBlocks.PLAYMAT_BLOCKS.add(playmat);
        return playmat;
    }
}
