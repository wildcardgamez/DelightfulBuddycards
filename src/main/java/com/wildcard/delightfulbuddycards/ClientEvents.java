package com.wildcard.delightfulbuddycards;

import com.wildcard.buddycards.Buddycards;
import com.wildcard.buddycards.client.renderer.MedalRenderer;
import com.wildcard.buddycards.item.BuddycardItem;
import com.wildcard.buddycards.item.tiered.ICollectionTieredItem;
import com.wildcard.buddycards.registries.BuddycardsComponents;
import com.wildcard.buddycards.registries.BuddycardsItems;
import com.wildcard.buddycards.registries.BuddycardsMisc;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TieredItem;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.registries.DeferredHolder;
import top.theillusivec4.curios.api.client.CuriosRendererRegistry;

@Mod(value = DelightfulBuddycards.MOD_ID, dist = Dist.CLIENT)
@EventBusSubscriber(modid = DelightfulBuddycards.MOD_ID, value = Dist.CLIENT)
public class ClientEvents {
    @SubscribeEvent
    public static void clientSetup(FMLClientSetupEvent event) {
        setupRenderers();
        for (DeferredHolder<Item, ? extends Item> item : RegistryHandler.ITEMS.getEntries())
            if (item.get() instanceof ICollectionTieredItem)
                event.enqueueWork(() -> ItemProperties.register(item.get(), Buddycards.buddycardsLocation("tier"), (stack, world, entity, idk) -> {
                    if (stack.has(BuddycardsComponents.COLLECTION_TIER))
                        return stack.get(BuddycardsComponents.COLLECTION_TIER);
                    return 0;
                }));
    }

    @SubscribeEvent
    public static void creativeTabSetup(BuildCreativeModeTabContentsEvent event) {
        if (event.getTabKey().equals(BuddycardsMisc.MAIN_TAB.getKey())) {
            for (DeferredHolder<Item, ? extends Item> i : RegistryHandler.ITEMS.getEntries())
                if(!(i.get() instanceof BuddycardItem) && !(i.get() instanceof ICollectionTieredItem))
                    event.accept(i.get());
        } else if (event.getTabKey().equals(BuddycardsMisc.CARDS_TAB.getKey())) {
            for (DeferredHolder<Item, ? extends Item> i : RegistryHandler.ITEMS.getEntries())
                if(i.get() instanceof BuddycardItem)
                    event.accept(i.get());
        }
        else if (event.getTabKey().equals(BuddycardsMisc.GEAR_TAB.getKey())) {
            for (DeferredHolder<Item, ? extends Item> i : RegistryHandler.ITEMS.getEntries())
                if(i.get() instanceof ICollectionTieredItem)
                    for (int j = 0; j < 5; j++) {
                        ItemStack stack = i.get().getDefaultInstance();
                        stack.set(BuddycardsComponents.COLLECTION_TIER, j);
                        event.accept(stack);
                    }
        }
    }

    public static void setupRenderers() {
        CuriosRendererRegistry.register(RegistryHandler.MEDAL.get(), () -> new MedalRenderer("textures/models/medal/buddysteel_medal_delightful"));
    }
}
