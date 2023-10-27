package com.sammy.minersdelight.setup;

import com.mojang.datafixers.util.*;
import com.sammy.minersdelight.mixin.*;
import net.minecraft.world.effect.*;
import net.minecraft.world.food.*;
import net.minecraftforge.data.loading.*;
import vectorwing.farmersdelight.common.registry.*;

import java.util.function.*;

public class MDFoodValues {

    public static final FoodProperties CAVE_CARROT = new FoodProperties.Builder().nutrition(3).saturationMod(0.25f).build();
    public static final FoodProperties BAKED_CAVE_CARROT = new FoodProperties.Builder().nutrition(5).saturationMod(0.45f).build();
    public static final FoodProperties COPPER_CARROT = new FoodProperties.Builder().nutrition(3).saturationMod(0.45f).effect(() -> new MobEffectInstance(MobEffects.POISON, 600, 0), 1f).build();
    public static final FoodProperties PASTA_WITH_VEGGIEBALLS = new FoodProperties.Builder().nutrition(12).saturationMod(1f).effect(() -> new MobEffectInstance(ModEffects.NOURISHMENT.get(), 3600, 0), 1.0F).build();
    public static final FoodProperties CAVE_SOUP = new FoodProperties.Builder().nutrition(10).saturationMod(0.4f).effect(() -> new MobEffectInstance(ModEffects.COMFORT.get(), 3600, 0), 1.0F).build();

    public static final FoodProperties VEGAN_PATTY = new FoodProperties.Builder().nutrition(3).saturationMod(0.15f).build();
    public static final FoodProperties VEGAN_HAMBURGER = new FoodProperties.Builder().nutrition(11).saturationMod(0.8f).build();
    public static final FoodProperties VEGAN_WRAP = new FoodProperties.Builder().nutrition(10).saturationMod(0.7f).build();
    public static final FoodProperties VEGAN_STEAK_AND_POTATOES = new FoodProperties.Builder().nutrition(12).saturationMod(0.6f).effect(() -> new MobEffectInstance(ModEffects.NOURISHMENT.get(), 3600, 0), 1.0F).build();

    public static final FoodProperties MOSS = new FoodProperties.Builder().nutrition(1).saturationMod(0.05f).effect(() -> new MobEffectInstance(ModEffects.COMFORT.get(), 200, 0), 1f).fast().build();

    public static final FoodProperties BAT_WING = new FoodProperties.Builder().nutrition(1).saturationMod(0.1f).effect(() -> new MobEffectInstance(MobEffects.CONFUSION, 600, 0), 0.4F).meat().fast().build();
    public static final FoodProperties SMOKED_BAT_WING = new FoodProperties.Builder().nutrition(3).saturationMod(0.15f).meat().fast().build();
    public static final FoodProperties BAT_ROLLS = new FoodProperties.Builder().nutrition(7).saturationMod(0.45f).build();
    public static final FoodProperties BAT_COOKIE = new FoodProperties.Builder().nutrition(1).saturationMod(0.05f).fast().build();
    public static final FoodProperties IMPROVISED_BARBECUE_STICK = new FoodProperties.Builder().nutrition(7).saturationMod(0.7f).meat().fast().build();
    public static final FoodProperties BAT_SOUP = new FoodProperties.Builder().nutrition(7).saturationMod(0.45f).effect(() -> new MobEffectInstance(ModEffects.COMFORT.get(), 3600, 0), 1.0F).build();

    public static final FoodProperties SILVERFISH_EGGS = new FoodProperties.Builder().nutrition(1).saturationMod(0.05f).meat().build();
    public static final FoodProperties WEIRD_CAVIAR = new FoodProperties.Builder().nutrition(5).saturationMod(0.15f).meat().build();
    public static final FoodProperties ARTHROPOD = new FoodProperties.Builder().nutrition(3).saturationMod(0.15f).effect(() -> new MobEffectInstance(MobEffects.CONFUSION, 600, 0), 0.4F).meat().build();
    public static final FoodProperties COOKED_ARTHROPOD = new FoodProperties.Builder().nutrition(5).saturationMod(0.15f).meat().build();
    public static final FoodProperties INSECT_SANDWICH = new FoodProperties.Builder().nutrition(10).saturationMod(0.55f).build();
    public static final FoodProperties INSECT_WRAP = new FoodProperties.Builder().nutrition(12).saturationMod(0.55f).build();
    public static final FoodProperties INSECT_STEW = new FoodProperties.Builder().nutrition(8).saturationMod(0.45f).build();
    public static final FoodProperties SEASONED_ARTHROPODS = new FoodProperties.Builder().nutrition(14).saturationMod(0.75f).effect(() -> new MobEffectInstance(ModEffects.NOURISHMENT.get(), 6000, 0), 1.0F).build();

    public static final FoodProperties SQUID = new FoodProperties.Builder().nutrition(3).saturationMod(0.15f).effect(() -> new MobEffectInstance(MobEffects.BLINDNESS, 600, 0), 0.4F).meat().build();
    public static final FoodProperties GLOW_SQUID = new FoodProperties.Builder().nutrition(3).saturationMod(0.15f).effect(() -> new MobEffectInstance(MobEffects.GLOWING, 600, 0), 1f).meat().build();
    public static final FoodProperties BAKED_SQUID = new FoodProperties.Builder().nutrition(6).saturationMod(0.4f).meat().build();
    public static final FoodProperties TENTACLES = new FoodProperties.Builder().nutrition(1).saturationMod(0.05f).meat().build();
    public static final FoodProperties BAKED_TENTACLES = new FoodProperties.Builder().nutrition(2).saturationMod(0.1f).meat().fast().build();
    public static final FoodProperties SQUID_SANDWICH = new FoodProperties.Builder().nutrition(10).saturationMod(0.45f).build();
    public static final FoodProperties TAKOYAKI = new FoodProperties.Builder().nutrition(12).saturationMod(0.55f).build();
    public static final FoodProperties BOWL_OF_STUFFED_SQUID = new FoodProperties.Builder().nutrition(10).saturationMod(0.8f).effect(() -> new MobEffectInstance(ModEffects.NOURISHMENT.get(), 6000, 0), 1.0F).build();
    public static final FoodProperties GLOW_INK_PASTA = new FoodProperties.Builder().nutrition(14).saturationMod(1.1f).effect(() -> new MobEffectInstance(ModEffects.NOURISHMENT.get(), 6000, 0), 1.0F).effect(() -> new MobEffectInstance(MobEffects.GLOWING, 1800, 0), 1.0F).build();

    //Not really sure how to get the values for these with the comfort effect included
    public static final FoodProperties BEETROOT_SOUP = new FoodProperties.Builder().nutrition(6).saturationMod(0.6F).effect(() -> new MobEffectInstance(ModEffects.COMFORT.get(), 3600, 0), 1.0F).build();
    public static final FoodProperties MUSHROOM_STEW = new FoodProperties.Builder().nutrition(6).saturationMod(0.6F).effect(() -> new MobEffectInstance(ModEffects.COMFORT.get(), 3600, 0), 1.0F).build();
    public static final FoodProperties RABBIT_STEW = new FoodProperties.Builder().nutrition(10).saturationMod(0.6F).effect(() -> new MobEffectInstance(ModEffects.COMFORT.get(), 6000, 0), 1.0F).build();

    public static FoodProperties cupFoodProperties(FoodProperties foodProperties) {
        if (DatagenModLoader.isRunningDataGen()) { //I shouldn't have to do this, this shouldn't need to be done.
            return foodProperties;
        }
        FoodProperties.Builder builder = new FoodProperties.Builder().nutrition((int) Math.floor(foodProperties.getNutrition()/2f)).saturationMod(foodProperties.getSaturationModifier()/2f);
        for (Pair<Supplier<MobEffectInstance>, Float> pair : ((FoodPropertiesMixin) foodProperties).getEffectsRaw()) {
            Supplier<MobEffectInstance> effectSupplier = () -> {
                final MobEffectInstance mobEffectInstance = pair.getFirst().get();
                return new MobEffectInstance(mobEffectInstance.getEffect(), (int) Math.floor(mobEffectInstance.getDuration()/2f), (int) Math.floor(mobEffectInstance.getAmplifier()/2f));
            };
            builder.effect(effectSupplier, pair.getSecond());
        }
        if (foodProperties.isFastFood()) {
            builder.fast();
        }
        if (foodProperties.canAlwaysEat()) {
            builder.alwaysEat();
        }
        if (foodProperties.isMeat()) {
            builder.meat();
        }
        return builder.build();
    }
}
