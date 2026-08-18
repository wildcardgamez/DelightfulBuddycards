package com.wildcard.delightfulbuddycards;

import com.google.common.collect.Multimap;
import com.wildcard.buddycards.gear.IMedalTypes;
import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;

import java.util.Optional;

public enum MedalTypes implements IMedalTypes {
    DELIGHTFUL_SET((player, mod) -> {
        if (player.hasEffect(MobEffects.HUNGER) && mod > 0) {
            player.removeEffect(MobEffects.HUNGER);
        }
    }, (map, mod) -> {
        map.put(Attributes.MAX_HEALTH, new AttributeModifier(ResourceLocation.withDefaultNamespace("interaction"), (mod + 1), AttributeModifier.Operation.ADD_VALUE));
    });

    MedalTypes(MedalTick effect, MedalAttributes attributes) {
        this.effect = Optional.ofNullable(effect);
        this.attributes = Optional.ofNullable(attributes);
    }

    private final Optional<MedalTick> effect;
    private final Optional<MedalAttributes> attributes;

    @Override
    public void effectTick(LivingEntity player, int mod) {
        effect.ifPresent(medalTick -> medalTick.applyEffect(player, mod));
    }

    @Override
    public void applyAttributes(Multimap<Holder<Attribute>, AttributeModifier> map, int mod) {
        attributes.ifPresent(medalAttributes -> medalAttributes.applyAttributes(map, mod));
    }

    interface MedalTick {
        void applyEffect(LivingEntity player, int mod);
    }

    interface MedalAttributes {
        void applyAttributes(Multimap<Holder<Attribute>, AttributeModifier> map, int mod);
    }
}
