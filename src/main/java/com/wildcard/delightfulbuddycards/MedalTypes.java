package com.wildcard.delightfulbuddycards;

import com.wildcard.buddycards.item.IMedalTypes;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import vectorwing.farmersdelight.common.registry.ModEffects;

public enum MedalTypes implements IMedalTypes {
    DELIGHTFUL_SET((player, mod) -> {
        player.addEffect(new MobEffectInstance(ModEffects.COMFORT.get(), 300, 0, true, false));
        if(mod > 0) {
            player.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 300, 0, true, false));
            if (mod > 1)
                player.addEffect(new MobEffectInstance(ModEffects.NOURISHMENT.get(), 300, 0, true, false));
        }
    });

    MedalTypes(MedalTypes.MedalEffect effect) {
        this.effect = effect;
    }
    private final MedalTypes.MedalEffect effect;

    @Override
    public void applyEffect(Player player, int mod) {
        effect.applyEffect(player, mod);
    }

    interface MedalEffect {
        void applyEffect(Player player, int mod);
    }
}
