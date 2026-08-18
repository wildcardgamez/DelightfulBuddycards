package com.wildcard.delightfulbuddycards;

import net.minecraftforge.fml.common.Mod;

@Mod(DelightfulBuddycards.MOD_ID)
public class DelightfulBuddycards
{
    public static final String MOD_ID = "delightfulbuddycards";

    public DelightfulBuddycards()
    {
        RegistryHandler.init();
    }
}
