package com.wildcard.delightfulbuddycards;

import com.wildcard.buddycards.client.renderer.MedalRenderer;
import com.wildcard.buddycards.item.BuddycardItem;
import com.wildcard.buddycards.registries.BuddycardsMisc;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.registries.RegistryObject;
import top.theillusivec4.curios.api.client.CuriosRendererRegistry;

@Mod.EventBusSubscriber(value = Dist.CLIENT, modid = DelightfulBuddycards.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ClientEvents {
    @SubscribeEvent
    public static void clientSetup(FMLClientSetupEvent event) {
        setupRenderers();
    }

    @SubscribeEvent
    public static void creativeTabSetup(BuildCreativeModeTabContentsEvent event) {
        if (event.getTabKey().equals(BuddycardsMisc.MAIN_TAB.getKey())) {
            for (RegistryObject<Item> i : RegistryHandler.ITEMS.getEntries())
                if(!(i.get() instanceof BuddycardItem))
                    event.accept(i.get());
        } else if (event.getTabKey().equals(BuddycardsMisc.CARDS_TAB.getKey())) {
            for (RegistryObject<Item> i : RegistryHandler.ITEMS.getEntries())
                if(i.get() instanceof BuddycardItem)
                    event.accept(i.get());
        }
    }

    public static void setupRenderers() {
        CuriosRendererRegistry.register(RegistryHandler.MEDAL.get(), () -> new MedalRenderer(getDefaultMedalTexture("buddysteel_medal_delightful")));
        CuriosRendererRegistry.register(RegistryHandler.LUMINIS_MEDAL.get(), () -> new MedalRenderer(getDefaultMedalTexture("luminis_medal_delightful")));
        CuriosRendererRegistry.register(RegistryHandler.ZYLEX_MEDAL.get(), () -> new MedalRenderer(getDefaultMedalTexture("zylex_medal_delightful")));
    }

    protected static ResourceLocation getDefaultMedalTexture(String name) {
        return new ResourceLocation(DelightfulBuddycards.MOD_ID, "textures/models/medal/" + name + ".png");
    }
}
