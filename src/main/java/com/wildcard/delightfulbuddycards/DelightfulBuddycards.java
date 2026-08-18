package com.wildcard.delightfulbuddycards;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;

@Mod(DelightfulBuddycards.MOD_ID)
public class DelightfulBuddycards
{
    public static final String MOD_ID = "delightfulbuddycards";

    public DelightfulBuddycards(IEventBus eventBus, ModContainer modContainer)
    {
        RegistryHandler.registerAll(eventBus);
    }
}
