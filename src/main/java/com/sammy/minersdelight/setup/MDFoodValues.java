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
    public static final FoodProperties BAT_WING = new FoodProperties.Builder().nutrition(1).saturationMod(0.1f).effect(() -> new MobEffectInstance(MobEffects.CONFUSION, 600, 0), 0.4F).meat().fast().build();
    public static final FoodProperties SMOKED_BAT_WING = new FoodProperties.Builder().nutrition(3).saturationMod(0.15f).meat().fast().build();
    public static final FoodProperties SILVERFISH_EGGS = new FoodProperties.Builder().nutrition(1).saturationMod(0.05f).build();
    public static final FoodProperties WEIRD_CAVIAR = new FoodProperties.Builder().nutrition(5).saturationMod(0.15f).build();
    public static final FoodProperties SQUID = new FoodProperties.Builder().nutrition(3).saturationMod(0.15f).effect(() -> new MobEffectInstance(MobEffects.BLINDNESS, 600, 0), 0.4F).meat().build();
    public static final FoodProperties GLOW_SQUID = new FoodProperties.Builder().nutrition(3).saturationMod(0.15f).effect(() -> new MobEffectInstance(MobEffects.GLOWING, 600, 0), 1f).meat().build();
    public static final FoodProperties BAKED_SQUID = new FoodProperties.Builder().nutrition(6).saturationMod(0.4f).meat().build();
    public static final FoodProperties TENTACLES = new FoodProperties.Builder().nutrition(1).saturationMod(0.05f).meat().build();
    public static final FoodProperties BAKED_TENTACLES = new FoodProperties.Builder().nutrition(2).saturationMod(0.1f).meat().fast().build();
    public static final FoodProperties IMPROVISED_BARBECUE_STICK = new FoodProperties.Builder().nutrition(7).saturationMod(0.7f).meat().fast().build();
    public static final FoodProperties PASTA_WITH_VEGGIEBALLS = new FoodProperties.Builder().nutrition(12).saturationMod(1f).effect(() -> new MobEffectInstance(ModEffects.NOURISHMENT.get(), 3600, 0), 1.0F).build();
    public static final FoodProperties CAVE_SOUP = new FoodProperties.Builder().nutrition(10).saturationMod(0.4f).effect(() -> new MobEffectInstance(ModEffects.COMFORT.get(), 3600, 0), 1.0F).build();
    public static final FoodProperties BOWL_OF_STUFFED_SQUID = new FoodProperties.Builder().nutrition(10).saturationMod(0.8f).effect(() -> new MobEffectInstance(ModEffects.NOURISHMENT.get(), 6000, 0), 1.0F).build();
    public static final FoodProperties HOT_COCOA = new FoodProperties.Builder().effect(()-> new MobEffectInstance(MobEffects.DIG_SPEED, 1800), 1).build();

    //Not really sure how to get the values for these with the comfort effect included
    public static final FoodProperties BEETROOT_SOUP = new FoodProperties.Builder().nutrition(6).saturationMod(0.6F).effect(() -> new MobEffectInstance(ModEffects.COMFORT.get(), 3600, 0), 1.0F).build();
    public static final FoodProperties MUSHROOM_STEW = new FoodProperties.Builder().nutrition(6).saturationMod(0.6F).effect(() -> new MobEffectInstance(ModEffects.COMFORT.get(), 3600, 0), 1.0F).build();
    public static final FoodProperties RABBIT_STEW = new FoodProperties.Builder().nutrition(10).saturationMod(0.6F).effect(() -> new MobEffectInstance(ModEffects.COMFORT.get(), 6000, 0), 1.0F).build();

    public static FoodProperties copyAndAddHaste(FoodProperties foodProperties) {
        if (DatagenModLoader.isRunningDataGen()) { //I shouldn't have to do this, this shouldn't need to be done.
            return foodProperties;
        }
        FoodProperties.Builder builder = new FoodProperties.Builder().nutrition(foodProperties.getNutrition()).saturationMod(foodProperties.getSaturationModifier());
        for (Pair<Supplier<MobEffectInstance>, Float> pair : ((FoodPropertiesMixin) foodProperties).getEffectsRaw()) {
            builder.effect(pair.getFirst(), pair.getSecond());
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
        builder.effect(()-> new MobEffectInstance(MobEffects.DIG_SPEED, 1800), 1);
        return builder.build();
    }
}
