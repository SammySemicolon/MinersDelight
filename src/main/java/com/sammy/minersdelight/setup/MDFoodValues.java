package com.sammy.minersdelight.setup;

import net.minecraft.util.*;
import net.minecraft.world.effect.*;
import net.minecraft.world.food.*;
import vectorwing.farmersdelight.common.registry.*;

public class MDFoodValues {


    public static final FoodProperties CAVE_CARROT = new FoodProperties.Builder().nutrition(3).saturationModifier(0.25f).build();
    public static final FoodProperties BAKED_CAVE_CARROT = new FoodProperties.Builder().nutrition(5).saturationModifier(0.45f).build();
    public static final FoodProperties COPPER_CARROT = new FoodProperties.Builder().nutrition(3).saturationModifier(0.45f).effect(() -> new MobEffectInstance(MobEffects.POISON, 600, 0, false, false), 1f).build();
    public static final FoodProperties PASTA_WITH_VEGGIEBALLS = new FoodProperties.Builder().nutrition(12).saturationModifier(1f).effect(() -> new MobEffectInstance(ModEffects.NOURISHMENT, 3600, 0, false, false), 1.0F).build();
    public static final FoodProperties CAVE_SOUP = new FoodProperties.Builder().nutrition(10).saturationModifier(0.4f).effect(() -> new MobEffectInstance(ModEffects.COMFORT, 3600, 0, false, false), 1.0F).build();

    public static final FoodProperties VEGAN_PATTY = new FoodProperties.Builder().nutrition(3).saturationModifier(0.15f).build();
    public static final FoodProperties VEGAN_HAMBURGER = new FoodProperties.Builder().nutrition(11).saturationModifier(0.8f).build();
    public static final FoodProperties VEGAN_WRAP = new FoodProperties.Builder().nutrition(10).saturationModifier(0.7f).build();
    public static final FoodProperties VEGAN_STEAK_AND_POTATOES = new FoodProperties.Builder().nutrition(12).saturationModifier(0.6f).effect(() -> new MobEffectInstance(ModEffects.NOURISHMENT, 3600, 0, false, false), 1.0F).build();
    public static final FoodProperties PLATE_OF_FAKE_MEATLOAF = new FoodProperties.Builder().nutrition(8).saturationModifier(0.65f).effect(() -> new MobEffectInstance(ModEffects.NOURISHMENT, 1800, 0, false, false), 1.0F).build();

    public static final FoodProperties MOSS = new FoodProperties.Builder().nutrition(1).saturationModifier(0.05f).effect(() -> new MobEffectInstance(ModEffects.COMFORT, 200, 0, false, false), 1f).fast().build();

    public static final FoodProperties BAT_WING = new FoodProperties.Builder().nutrition(1).saturationModifier(0.1f).effect(() -> new MobEffectInstance(MobEffects.CONFUSION, 600, 0, false, false), 0.4F).fast().build();
    public static final FoodProperties SMOKED_BAT_WING = new FoodProperties.Builder().nutrition(3).saturationModifier(0.15f).fast().build();
    public static final FoodProperties BAT_ROLLS = new FoodProperties.Builder().nutrition(7).saturationModifier(0.45f).build();
    public static final FoodProperties CAVE_HAMBURGER = new FoodProperties.Builder().nutrition(12).saturationModifier(0.6f).build();
    public static final FoodProperties BAT_COOKIE = new FoodProperties.Builder().nutrition(1).saturationModifier(0.05f).fast().build();
    public static final FoodProperties IMPROVISED_BARBECUE_STICK = new FoodProperties.Builder().nutrition(7).saturationModifier(0.7f).fast().build();
    public static final FoodProperties BAT_SOUP = new FoodProperties.Builder().nutrition(7).saturationModifier(0.45f).effect(() -> new MobEffectInstance(ModEffects.COMFORT, 3600, 0, false, false), 1.0F).build();

    public static final FoodProperties SILVERFISH_EGGS = new FoodProperties.Builder().nutrition(1).saturationModifier(0.05f).build();
    public static final FoodProperties WEIRD_CAVIAR = new FoodProperties.Builder().nutrition(5).saturationModifier(0.15f).build();

    public static final FoodProperties SPIDER_LEG = new FoodProperties.Builder().nutrition(6).saturationModifier(0.2f).build();
    public static final FoodProperties BAKED_SPIDER_LEG = new FoodProperties.Builder().nutrition(10).saturationModifier(0.2f).build();
    public static final FoodProperties PLATE_OF_GLAZED_ARACHNID_LIMBS = new FoodProperties.Builder().nutrition(12).saturationModifier(0.8f).effect(() -> new MobEffectInstance(ModEffects.NOURISHMENT, 6000, 0, false, false), 1.0F).build();
    public static final FoodProperties ARTHROPOD = new FoodProperties.Builder().nutrition(3).saturationModifier(0.15f).effect(() -> new MobEffectInstance(MobEffects.CONFUSION, 600, 0, false, false), 0.4F).build();
    public static final FoodProperties COOKED_ARTHROPOD = new FoodProperties.Builder().nutrition(5).saturationModifier(0.15f).build();
    public static final FoodProperties INSECT_SANDWICH = new FoodProperties.Builder().nutrition(10).saturationModifier(0.55f).build();
    public static final FoodProperties INSECT_WRAP = new FoodProperties.Builder().nutrition(12).saturationModifier(0.55f).build();
    public static final FoodProperties INSECT_STEW = new FoodProperties.Builder().nutrition(8).saturationModifier(0.45f).build();
    public static final FoodProperties SEASONED_ARTHROPODS = new FoodProperties.Builder().nutrition(14).saturationModifier(0.75f).effect(() -> new MobEffectInstance(ModEffects.NOURISHMENT, 6000, 0, false, false), 1.0F).build();

    public static final FoodProperties CRUNCHY_BAR = new FoodProperties.Builder().nutrition(7).saturationModifier(0.45f).build();
    public static final FoodProperties NUTRITIONAL_BAR = new FoodProperties.Builder().nutrition(10).saturationModifier(0.55f).effect(() -> new MobEffectInstance(ModEffects.NOURISHMENT, 600, 0, false, false), 1.0F).build();
    public static final FoodProperties GOLDEN_NUTRITIONAL_BAR = new FoodProperties.Builder().nutrition(10).saturationModifier(0.8f).effect(() -> new MobEffectInstance(ModEffects.NOURISHMENT, 1200, 0, false, false), 1.0F).build();

    public static final FoodProperties SQUID = new FoodProperties.Builder().nutrition(3).saturationModifier(0.15f).effect(() -> new MobEffectInstance(MobEffects.BLINDNESS, 600, 0, false, false), 0.4F).build();
    public static final FoodProperties GLOW_SQUID = new FoodProperties.Builder().nutrition(3).saturationModifier(0.15f).effect(() -> new MobEffectInstance(MobEffects.GLOWING, 600, 0, false, false), 1f).build();
    public static final FoodProperties BAKED_SQUID = new FoodProperties.Builder().nutrition(6).saturationModifier(0.4f).build();
    public static final FoodProperties TENTACLES = new FoodProperties.Builder().nutrition(1).saturationModifier(0.05f).build();
    public static final FoodProperties BAKED_TENTACLES = new FoodProperties.Builder().nutrition(2).saturationModifier(0.1f).fast().build();
    public static final FoodProperties TENTACLES_ON_A_STICK = new FoodProperties.Builder().nutrition(4).saturationModifier(0.2f).fast().build();
    public static final FoodProperties SQUID_SANDWICH = new FoodProperties.Builder().nutrition(10).saturationModifier(0.45f).build();
    public static final FoodProperties TAKOYAKI = new FoodProperties.Builder().nutrition(12).saturationModifier(0.55f).build();
    public static final FoodProperties BOWL_OF_STUFFED_SQUID = new FoodProperties.Builder().nutrition(10).saturationModifier(0.8f).effect(() -> new MobEffectInstance(ModEffects.NOURISHMENT, 6000, 0, false, false), 1.0F).build();
    public static final FoodProperties GLOW_INK_PASTA = new FoodProperties.Builder().nutrition(14).saturationModifier(1.1f).effect(() -> new MobEffectInstance(ModEffects.NOURISHMENT, 6000, 0, false, false), 1.0F).effect(() -> new MobEffectInstance(MobEffects.GLOWING, 1800, 0, false, false), 1.0F).build();

    //Not really sure how to get the values for these with the comfort effect included
    public static final FoodProperties BEETROOT_SOUP = new FoodProperties.Builder().nutrition(6).saturationModifier(0.6F).effect(() -> new MobEffectInstance(ModEffects.COMFORT, 3600, 0, false, false), 1.0F).build();
    public static final FoodProperties MUSHROOM_STEW = new FoodProperties.Builder().nutrition(6).saturationModifier(0.6F).effect(() -> new MobEffectInstance(ModEffects.COMFORT, 3600, 0, false, false), 1.0F).build();
    public static final FoodProperties RABBIT_STEW = new FoodProperties.Builder().nutrition(10).saturationModifier(0.6F).effect(() -> new MobEffectInstance(ModEffects.COMFORT, 6000, 0, false, false), 1.0F).build();

    public static FoodProperties createCupFoodProperties(FoodProperties foodProperties) {
        int foodLevel = foodProperties.nutrition();
        var builder = new FoodProperties.Builder()
                .nutrition(Mth.floor(foodLevel / 2f))
                .saturationModifier(saturationModifier(foodLevel, foodProperties.saturation()));
        for (FoodProperties.PossibleEffect possibleEffect : foodProperties.effects()) {
            builder.effect(() -> {
                MobEffectInstance effectInstance = possibleEffect.effectSupplier().get();
                return new MobEffectInstance(effectInstance.getEffect(), effectInstance.getDuration() / 2, effectInstance.getAmplifier(),
                        effectInstance.isAmbient(), effectInstance.isVisible(), effectInstance.showIcon());
            }, possibleEffect.probability());
        }
        builder.fast();
        if (foodProperties.canAlwaysEat()) {
            builder.alwaysEdible();
        }
        return builder.build();
    }

    public static float saturationModifier(int foodLevel, float saturation) {
        return saturation / foodLevel / 2.0F;
    }
}