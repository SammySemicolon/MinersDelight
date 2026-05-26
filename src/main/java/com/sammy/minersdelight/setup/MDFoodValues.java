package com.sammy.minersdelight.setup;

import com.mojang.datafixers.util.*;
import com.sammy.minersdelight.mixin.*;
import net.minecraft.world.effect.*;
import net.minecraft.world.food.*;
import net.minecraftforge.data.loading.*;
import vectorwing.farmersdelight.common.registry.*;

import java.util.function.*;

public class MDFoodValues {

    public static final int POOR_DURATION = 200;      // 10 seconds
    public static final int BRIEF_DURATION = 600;     // 30 seconds
    public static final int SHORT_DURATION = 1200;    // 1 minute
    public static final int MEDIUM_DURATION = 3600;   // 3 minutes
    public static final int LONG_DURATION = 6000;     // 5 minutes

    public static final FoodProperties CAVE_CARROT = new FoodProperties.Builder()
            .nutrition(3)
            .saturationMod(0.25f)
            .build();

    public static final FoodProperties BAKED_CAVE_CARROT = new FoodProperties.Builder()
            .nutrition(5)
            .saturationMod(0.45f)
            .build();

    public static final FoodProperties COPPER_CARROT = new FoodProperties.Builder()
            .nutrition(3)
            .saturationMod(0.45f)
            .effect(() -> new MobEffectInstance(MobEffects.POISON, BRIEF_DURATION, 0), 1f)
            .build();

    public static final FoodProperties PASTA_WITH_VEGGIEBALLS = new FoodProperties.Builder()
            .nutrition(12)
            .saturationMod(1f)
            .effect(() -> new MobEffectInstance(ModEffects.NOURISHMENT.get(), MEDIUM_DURATION, 0), 1.0F)
            .build();

    public static final FoodProperties CAVE_SOUP = new FoodProperties.Builder()
            .nutrition(10)
            .saturationMod(0.4f)
            .effect(() -> new MobEffectInstance(ModEffects.NOURISHMENT.get(), MEDIUM_DURATION, 0), 1.0F)
            .build();

    public static final FoodProperties VEGAN_PATTY = new FoodProperties.Builder()
            .nutrition(3)
            .saturationMod(0.15f)
            .build();

    public static final FoodProperties VEGAN_HAMBURGER = new FoodProperties.Builder()
            .nutrition(11)
            .saturationMod(0.8f)
            .build();

    public static final FoodProperties VEGAN_WRAP = new FoodProperties.Builder()
            .nutrition(10)
            .saturationMod(0.7f)
            .build();

    public static final FoodProperties VEGAN_STEAK_AND_POTATOES = new FoodProperties.Builder()
            .nutrition(12)
            .saturationMod(0.6f)
            .effect(() -> new MobEffectInstance(ModEffects.NOURISHMENT.get(), MEDIUM_DURATION, 0), 1.0F)
            .build();

    public static final FoodProperties MOSS = new FoodProperties.Builder()
            .nutrition(1)
            .saturationMod(0.05f)
            .effect(() -> new MobEffectInstance(ModEffects.NOURISHMENT.get(), POOR_DURATION, 0), 1f)
            .fast()
            .build();

    public static final FoodProperties BAT_WING = new FoodProperties.Builder()
            .nutrition(1)
            .saturationMod(0.1f)
            .effect(() -> new MobEffectInstance(MobEffects.CONFUSION, BRIEF_DURATION, 0), 0.4F)
            .meat()
            .fast()
            .build();

    public static final FoodProperties SMOKED_BAT_WING = new FoodProperties.Builder()
            .nutrition(3)
            .saturationMod(0.15f)
            .meat()
            .fast()
            .build();

    public static final FoodProperties BAT_ROLLS = new FoodProperties.Builder()
            .nutrition(7)
            .saturationMod(0.45f)
            .build();

    public static final FoodProperties BAT_COOKIE = new FoodProperties.Builder()
            .nutrition(1)
            .saturationMod(0.05f)
            .meat()
            .fast()
            .build();

    public static final FoodProperties IMPROVISED_BARBECUE_STICK = new FoodProperties.Builder()
            .nutrition(7)
            .saturationMod(0.7f)
            .fast()
            .build();

    public static final FoodProperties BAT_SOUP = new FoodProperties.Builder()
            .nutrition(7)
            .saturationMod(0.45f)
            .effect(() -> new MobEffectInstance(ModEffects.NOURISHMENT.get(), MEDIUM_DURATION, 0), 1.0F)
            .build();

    public static final FoodProperties SILVERFISH_EGGS = new FoodProperties.Builder()
            .nutrition(1)
            .saturationMod(0.05f)
            .build();

    public static final FoodProperties WEIRD_CAVIAR = new FoodProperties.Builder()
            .nutrition(5)
            .saturationMod(0.15f)
            .build();

    public static final FoodProperties ARTHROPOD = new FoodProperties.Builder()
            .nutrition(3)
            .saturationMod(0.15f)
            .effect(() -> new MobEffectInstance(MobEffects.CONFUSION, BRIEF_DURATION, 0), 0.4F)
            .meat()
            .build();

    public static final FoodProperties COOKED_ARTHROPOD = new FoodProperties.Builder()
            .nutrition(5)
            .saturationMod(0.15f)
            .meat()
            .build();

    public static final FoodProperties INSECT_SANDWICH = new FoodProperties.Builder()
            .nutrition(10)
            .saturationMod(0.55f)
            .build();

    public static final FoodProperties INSECT_WRAP = new FoodProperties.Builder()
            .nutrition(12)
            .saturationMod(0.55f)
            .build();

    public static final FoodProperties INSECT_STEW = new FoodProperties.Builder()
            .nutrition(8)
            .saturationMod(0.45f)
            .effect(() -> new MobEffectInstance(ModEffects.NOURISHMENT.get(), SHORT_DURATION, 0), 1.0F)
            .build();

    public static final FoodProperties SEASONED_ARTHROPODS = new FoodProperties.Builder()
            .nutrition(14)
            .saturationMod(0.75f)
            .effect(() -> new MobEffectInstance(ModEffects.NOURISHMENT.get(), LONG_DURATION, 0), 1.0F)
            .build();

    public static final FoodProperties SPIDER_LEG = new FoodProperties.Builder()
            .nutrition(6)
            .saturationMod(0.2f)
            .meat()
            .build();

    public static final FoodProperties BAKED_SPIDER_LEG = new FoodProperties.Builder()
            .nutrition(10)
            .saturationMod(0.2f)
            .meat()
            .build();

    public static final FoodProperties PLATE_OF_GLAZED_ARACHNID_LIMBS = new FoodProperties.Builder()
            .nutrition(12)
            .saturationMod(0.8f)
            .effect(() -> new MobEffectInstance(ModEffects.NOURISHMENT.get(), LONG_DURATION, 0), 1.0F)
            .build();

    public static final FoodProperties FAKE_MEATLOAF_MEAL = new FoodProperties.Builder()
            .nutrition(8)
            .saturationMod(0.65f)
            .effect(() -> new MobEffectInstance(ModEffects.NOURISHMENT.get(), SHORT_DURATION, 0), 1.0F)
            .build();

    public static final FoodProperties CAVE_HAMBURGER = new FoodProperties.Builder()
            .nutrition(12)
            .saturationMod(0.6f)
            .build();

    public static final FoodProperties CRUNCHY_BAR = new FoodProperties.Builder()
            .nutrition(7)
            .saturationMod(0.45f)
            .build();

    public static final FoodProperties NUTRITIONAL_BAR = new FoodProperties.Builder()
            .nutrition(10)
            .saturationMod(0.55f)
            .effect(() -> new MobEffectInstance(ModEffects.NOURISHMENT.get(), BRIEF_DURATION, 0), 1.0F)
            .build();

    public static final FoodProperties GOLDEN_NUTRITIONAL_BAR = new FoodProperties.Builder()
            .nutrition(10)
            .saturationMod(0.8f)
            .effect(() -> new MobEffectInstance(ModEffects.NOURISHMENT.get(), SHORT_DURATION, 0), 1.0F)
            .build();

    public static final FoodProperties TENTACLES_ON_A_STICK = new FoodProperties.Builder()
            .nutrition(4)
            .saturationMod(0.2f)
            .fast()
            .build();

    public static final FoodProperties SQUID = new FoodProperties.Builder()
            .nutrition(3)
            .saturationMod(0.15f)
            .effect(() -> new MobEffectInstance(MobEffects.BLINDNESS, BRIEF_DURATION, 0), 0.4F)
            .build();

    public static final FoodProperties GLOW_SQUID = new FoodProperties.Builder()
            .nutrition(3)
            .saturationMod(0.15f)
            .effect(() -> new MobEffectInstance(MobEffects.GLOWING, BRIEF_DURATION, 0), 1f)
            .build();

    public static final FoodProperties BAKED_SQUID = new FoodProperties.Builder()
            .nutrition(6)
            .saturationMod(0.4f)
            .build();

    public static final FoodProperties TENTACLES = new FoodProperties.Builder()
            .nutrition(1)
            .saturationMod(0.05f)
            .build();

    public static final FoodProperties BAKED_TENTACLES = new FoodProperties.Builder()
            .nutrition(2)
            .saturationMod(0.1f)
            .fast()
            .build();

    public static final FoodProperties SQUID_SANDWICH = new FoodProperties.Builder()
            .nutrition(10)
            .saturationMod(0.45f)
            .build();

    public static final FoodProperties TAKOYAKI = new FoodProperties.Builder()
            .nutrition(12)
            .saturationMod(0.55f)
            .build();

    public static final FoodProperties BOWL_OF_STUFFED_SQUID = new FoodProperties.Builder()
            .nutrition(10)
            .saturationMod(0.8f)
            .effect(() -> new MobEffectInstance(ModEffects.NOURISHMENT.get(), LONG_DURATION, 0), 1.0F)
            .build();

    public static final FoodProperties GLOW_INK_PASTA = new FoodProperties.Builder()
            .nutrition(14)
            .saturationMod(1.1f)
            .effect(() -> new MobEffectInstance(ModEffects.NOURISHMENT.get(), LONG_DURATION, 0), 1.0F)
            .effect(() -> new MobEffectInstance(MobEffects.GLOWING, SHORT_DURATION, 0), 1.0F)
            .build();

    public static final FoodProperties BEETROOT_SOUP = new FoodProperties.Builder()
            .nutrition(6)
            .saturationMod(0.6F)
            .effect(() -> new MobEffectInstance(ModEffects.NOURISHMENT.get(), 3600, 0), 1.0F)
            .build();

    public static final FoodProperties MUSHROOM_STEW = new FoodProperties.Builder()
            .nutrition(6)
            .saturationMod(0.6F)
            .effect(() -> new MobEffectInstance(ModEffects.NOURISHMENT.get(), 3600, 0), 1.0F)
            .build();

    public static final FoodProperties RABBIT_STEW = new FoodProperties.Builder()
            .nutrition(10)
            .saturationMod(0.6F)
            .effect(() -> new MobEffectInstance(ModEffects.NOURISHMENT.get(), 6000, 0), 1.0F)
            .build();

    public static FoodProperties cupFoodProperties(FoodProperties foodProperties) {
        FoodProperties.Builder builder = new FoodProperties.Builder()
                .nutrition((int) Math.floor(foodProperties.getNutrition() / 2f))
                .saturationMod(foodProperties.getSaturationModifier() / 2f);

        for (Pair<MobEffectInstance, Float> pair : foodProperties.getEffects()) {
            MobEffectInstance effect = pair.getFirst();

            builder.effect(
                    () -> new MobEffectInstance(
                            effect.getEffect(),
                            (int) Math.floor(effect.getDuration() / 2f),
                            effect.getAmplifier()
                    ),
                    pair.getSecond()
            );
        }

        if (foodProperties.canAlwaysEat()) {
            builder.alwaysEat();
        }
        if (foodProperties.isMeat()) {
            builder.meat();
        }
        return builder.fast().build();
    }
}
