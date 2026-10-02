package com.wildcard.delightfulbuddycards;

import com.wildcard.buddycards.Buddycards;
import com.wildcard.buddycards.core.BuddycardSet;
import com.wildcard.buddycards.datagen.RecipeGen;
import com.wildcard.buddycards.recipe.BuddysteelChargingRecipe;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.client.model.generators.ItemModelBuilder;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.client.model.generators.ModelFile;
import net.neoforged.neoforge.client.model.generators.ModelProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.data.event.GatherDataEvent;

import java.util.concurrent.CompletableFuture;

import static com.wildcard.buddycards.registries.BuddycardsItems.*;
import static com.wildcard.buddycards.registries.BuddycardsItems.CRIMSON_LUMINIS;
import static com.wildcard.buddycards.registries.BuddycardsItems.CRIMSON_LUMINIS_BLOCK;
import static com.wildcard.buddycards.registries.BuddycardsItems.LUMINIS;
import static com.wildcard.buddycards.registries.BuddycardsItems.TRUE_PERFECT_BUDDYSTEEL_INGOT;
import static com.wildcard.buddycards.registries.BuddycardsItems.VOID_ZYLEX;
import static com.wildcard.buddycards.registries.BuddycardsItems.VOID_ZYLEX_BLOCK;
import static com.wildcard.buddycards.registries.BuddycardsItems.ZYLEX;

@Mod(value = DelightfulBuddycards.MOD_ID)
@EventBusSubscriber(modid = DelightfulBuddycards.MOD_ID)
public class Datagen {
    @SubscribeEvent
    static void onGatherData(GatherDataEvent event) {
        event.getGenerator().addProvider(true, new CardModelGen(event.getGenerator().getPackOutput(), "delightfulbuddycards", event.getExistingFileHelper()));
        event.getGenerator().addProvider(true, new DelightfulBuddycardsRecipeGen(event.getGenerator().getPackOutput(), event.getLookupProvider()));
    }

    static private class CardModelGen extends ItemModelProvider {
        public CardModelGen(PackOutput output, String modid, ExistingFileHelper existingFileHelper) {
            super(output, modid, existingFileHelper);
        }

        @Override
        protected void registerModels() {
            for (int i = 1; i <= 18; i++) {
                genCardModel(i);
            }
            ItemModelBuilder medal = getBuilder(ModelProvider.ITEM_FOLDER + "/buddysteel_medal_delightful")
                    .parent(factory.apply(ResourceLocation.withDefaultNamespace("item/generated")))
                    .texture("layer0", ResourceLocation.fromNamespaceAndPath(DelightfulBuddycards.MOD_ID, ModelProvider.ITEM_FOLDER + "/delightful_set/medal"));
            for (int i = 1; i < 5; i++) {
                ItemModelBuilder tierMedal = getBuilder(ModelProvider.ITEM_FOLDER + "/buddysteel_medal_delightful" + i)
                        .parent(factory.apply(ResourceLocation.withDefaultNamespace("item/generated")))
                        .texture("layer0", ResourceLocation.fromNamespaceAndPath(DelightfulBuddycards.MOD_ID, ModelProvider.ITEM_FOLDER + "/delightful_set/medal" + i));
                medal.override().predicate(ResourceLocation.fromNamespaceAndPath(Buddycards.MOD_ID, "tier"), i).model(tierMedal);
            }
            ItemModelBuilder knife = getBuilder(ModelProvider.ITEM_FOLDER + "/charged_buddysteel_knife")
                    .parent(factory.apply(ResourceLocation.withDefaultNamespace("item/generated")))
                    .texture("layer0", ResourceLocation.fromNamespaceAndPath(DelightfulBuddycards.MOD_ID, ModelProvider.ITEM_FOLDER + "/charged_buddysteel_knife"));
            for (int i = 1; i < 5; i++) {
                ItemModelBuilder tierKnife = getBuilder(ModelProvider.ITEM_FOLDER + "/charged_buddysteel_knife" + i)
                        .parent(factory.apply(ResourceLocation.withDefaultNamespace("item/generated")))
                        .texture("layer0", ResourceLocation.fromNamespaceAndPath(DelightfulBuddycards.MOD_ID, ModelProvider.ITEM_FOLDER + "/charged_buddysteel_knife" + i));
                knife.override().predicate(ResourceLocation.fromNamespaceAndPath(Buddycards.MOD_ID, "tier"), i).model(tierKnife);
            }
        }

        void genCardModel(int cardNum) {
            ItemModelBuilder card = getBuilder(ModelProvider.ITEM_FOLDER + "/buddycard_delightful" + cardNum)
                    .parent(factory.apply(ResourceLocation.fromNamespaceAndPath(Buddycards.MOD_ID, ModelProvider.ITEM_FOLDER + "/buddycard")))
                    .texture("layer0", ResourceLocation.fromNamespaceAndPath(DelightfulBuddycards.MOD_ID, ModelProvider.ITEM_FOLDER + "/delightful_set/" + cardNum));
            for (int i = 0; i <= 5; i++) {
                for (int j = 0; j <= 3; j++)
                    if (j + i != 0)
                        card.override().predicate(ResourceLocation.fromNamespaceAndPath(Buddycards.MOD_ID, "grade"), i).predicate(ResourceLocation.fromNamespaceAndPath(Buddycards.MOD_ID, "foil"), j).model(genFoiledGradedCardModel(cardNum, i, j));
            }
        }

        ModelFile genFoiledGradedCardModel(int cardNum, int grade, int foil) {
            ItemModelBuilder card = getBuilder(ModelProvider.ITEM_FOLDER + "/buddycard_delightful" + cardNum + "_g" + grade + "_f" + foil)
                    .parent(factory.apply(ResourceLocation.fromNamespaceAndPath(Buddycards.MOD_ID, ModelProvider.ITEM_FOLDER + "/buddycard")))
                    .texture("layer0", ResourceLocation.fromNamespaceAndPath(DelightfulBuddycards.MOD_ID, ModelProvider.ITEM_FOLDER + "/delightful_set/" + cardNum));
            if (foil != 0)
                card.texture("layer1", ResourceLocation.fromNamespaceAndPath(Buddycards.MOD_ID,ModelProvider.ITEM_FOLDER + "/foil" + foil));
            if (grade != 0)
                card.texture(foil == 0 ? "layer1" : "layer2", ResourceLocation.fromNamespaceAndPath(Buddycards.MOD_ID,ModelProvider.ITEM_FOLDER + "/grade" + grade));
            return card;
        }
    }

    static class DelightfulBuddycardsRecipeGen extends RecipeGen {
        public DelightfulBuddycardsRecipeGen(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
            super(output, registries);
        }

        @Override
        protected void buildRecipes(RecipeOutput recipeOutput) {
            generateMedalRecipes(RegistryHandler.DELIGHTFUL_SET, recipeOutput);
            generateTieredBuddysteelRecipes("charged_buddysteel_knife", RegistryHandler.BUDDYSTEEL_KNIFE.toStack(), RegistryHandler.CHARGED_BUDDYSTEEL_KNIFE.toStack(), recipeOutput);
        }

        protected static void generateMedalRecipes(BuddycardSet set, RecipeOutput recipeOutput) {
            ItemStack medal = set.getMedal().getDefaultInstance();
            recipeOutput.accept(ResourceLocation.fromNamespaceAndPath(DelightfulBuddycards.MOD_ID, "buddysteel_medal_" + set.getName()),
                    new BuddysteelChargingRecipe(medal, ingredientOf(BLANK_BUDDYSTEEL_MEDAL.get()),
                            sameIngredient(Ingredient.of(TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(DelightfulBuddycards.MOD_ID, "buddycards_" + set.getName())))),
                            0, 1, set.getName()), null);
            recipeOutput.accept(ResourceLocation.fromNamespaceAndPath(DelightfulBuddycards.MOD_ID, "buddysteel_medal_" + set.getName() + "1"),
                    new BuddysteelChargingRecipe(itemCopyWithTier(medal, 1), Ingredient.of(medal),
                            doubleIngredients(ingredientOf(LUMINIS.get()), ingredientOf(CRIMSON_LUMINIS.get())),
                            1, 1, set.getName()), null);
            recipeOutput.accept(ResourceLocation.fromNamespaceAndPath(DelightfulBuddycards.MOD_ID, "buddysteel_medal_" + set.getName() + "2"),
                    new BuddysteelChargingRecipe(itemCopyWithTier(medal, 2), Ingredient.of(medal),
                            doubleIngredients(ingredientOf(VOID_ZYLEX.get()), ingredientOf(ZYLEX.get())),
                            2, 1, set.getName()), null);
            recipeOutput.accept(ResourceLocation.fromNamespaceAndPath(DelightfulBuddycards.MOD_ID, "buddysteel_medal_" + set.getName() + "3"),
                    new BuddysteelChargingRecipe(itemCopyWithTier(medal, 3), Ingredient.of(medal),
                            doubleIngredients(ingredientOf(CRIMSON_LUMINIS.get()), ingredientOf(VOID_ZYLEX.get())),
                            3, 1, set.getName()), null);
            recipeOutput.accept(ResourceLocation.fromNamespaceAndPath(DelightfulBuddycards.MOD_ID, "buddysteel_medal_" + set.getName() + "4"),
                    new BuddysteelChargingRecipe(itemCopyWithTier(medal, 4), Ingredient.of(medal),
                            doubleIngredients(ingredientOf(CRIMSON_LUMINIS_BLOCK.get()), ingredientOf(VOID_ZYLEX_BLOCK.get())),
                            4, 1, set.getName()), null);
        }

        protected static void generateTieredBuddysteelRecipes(String name, ItemStack basic, ItemStack charged, RecipeOutput recipeOutput) {
            recipeOutput.accept(ResourceLocation.fromNamespaceAndPath(DelightfulBuddycards.MOD_ID, name),
                    new BuddysteelChargingRecipe(charged, Ingredient.of(basic),
                            doubleIngredients(ingredientOf(LUMINIS.get()), ingredientOf(ZYLEX.get())),
                            0, 1, "all"), null);
            recipeOutput.accept(ResourceLocation.fromNamespaceAndPath(DelightfulBuddycards.MOD_ID, name + "1"),
                    new BuddysteelChargingRecipe(itemCopyWithTier(charged, 1), Ingredient.of(charged),
                            doubleIngredients(ingredientOf(LUMINIS.get()), ingredientOf(CRIMSON_LUMINIS.get())),
                            1, 1, "all"), null);
            recipeOutput.accept(ResourceLocation.fromNamespaceAndPath(DelightfulBuddycards.MOD_ID, name + "2"),
                    new BuddysteelChargingRecipe(itemCopyWithTier(charged, 2), Ingredient.of(charged),
                            doubleIngredients(ingredientOf(VOID_ZYLEX.get()), ingredientOf(ZYLEX.get())),
                            2, 1, "all"), null);
            recipeOutput.accept(ResourceLocation.fromNamespaceAndPath(DelightfulBuddycards.MOD_ID, name + "3"),
                    new BuddysteelChargingRecipe(itemCopyWithTier(charged, 3), Ingredient.of(charged),
                            doubleIngredients(ingredientOf(CRIMSON_LUMINIS.get()), ingredientOf(VOID_ZYLEX.get())),
                            3, 1, "all"), null);
            recipeOutput.accept(ResourceLocation.fromNamespaceAndPath(DelightfulBuddycards.MOD_ID, name + "4"),
                    new BuddysteelChargingRecipe(itemCopyWithTier(charged, 4), Ingredient.of(charged),
                            sameIngredient(ingredientOf(TRUE_PERFECT_BUDDYSTEEL_INGOT.get())),
                            4, 1, "all"), null);
        }
    }
}
