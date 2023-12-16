package com.sammy.minersdelight.setup;

import com.sammy.minersdelight.*;
import net.minecraft.world.effect.*;
import net.minecraft.world.item.*;
import net.minecraft.world.item.alchemy.*;
import net.minecraftforge.fml.event.lifecycle.*;
import net.minecraftforge.registries.*;

public class MDPotions {

    public static final DeferredRegister<Potion> POTIONS = DeferredRegister.create(ForgeRegistries.POTIONS, MinersDelightMod.MODID);

    public static final RegistryObject<Potion> HASTE = POTIONS.register("haste", () -> new Potion("haste", new MobEffectInstance(MobEffects.DIG_SPEED, 3600)));
    public static final RegistryObject<Potion> LONG_HASTE = POTIONS.register("long_haste", () -> new Potion("long_haste", new MobEffectInstance(MobEffects.DIG_SPEED, 9600)));
    public static final RegistryObject<Potion> STRONG_HASTE = POTIONS.register("strong_haste", () -> new Potion("strong_haste", new MobEffectInstance(MobEffects.DIG_SPEED, 1800, 1)));
    public static final RegistryObject<Potion> MINING_FATIGUE = POTIONS.register("mining_fatigue", () -> new Potion("mining_fatigue", new MobEffectInstance(MobEffects.DIG_SLOWDOWN, 3600)));
    public static final RegistryObject<Potion> LONG_MINING_FATIGUE = POTIONS.register("long_mining_fatigue", () -> new Potion("long_mining_fatigue", new MobEffectInstance(MobEffects.DIG_SLOWDOWN, 9600)));
    public static final RegistryObject<Potion> STRONG_MINING_FATIGUE = POTIONS.register("strong_mining_fatigue", () -> new Potion("strong_mining_fatigue", new MobEffectInstance(MobEffects.DIG_SLOWDOWN, 1800, 1)));

    public static void addPotionMixing(FMLCommonSetupEvent event) {
        event.enqueueWork(() -> {
            PotionBrewing.addMix(Potions.WATER, MDItems.COPPER_CARROT.get(), Potions.MUNDANE);
            PotionBrewing.addMix(Potions.AWKWARD, MDItems.COPPER_CARROT.get(), HASTE.get());

            PotionBrewing.addMix(HASTE.get(), Items.REDSTONE, LONG_HASTE.get());
            PotionBrewing.addMix(HASTE.get(), Items.GLOWSTONE_DUST, STRONG_HASTE.get());

            PotionBrewing.addMix(HASTE.get(), Items.FERMENTED_SPIDER_EYE, MINING_FATIGUE.get());
            PotionBrewing.addMix(LONG_HASTE.get(), Items.FERMENTED_SPIDER_EYE, LONG_HASTE.get());
            PotionBrewing.addMix(STRONG_HASTE.get(), Items.FERMENTED_SPIDER_EYE, STRONG_HASTE.get());

            PotionBrewing.addMix(MINING_FATIGUE.get(), Items.REDSTONE, LONG_MINING_FATIGUE.get());
            PotionBrewing.addMix(MINING_FATIGUE.get(), Items.GLOWSTONE_DUST, STRONG_MINING_FATIGUE.get());
        });
    }
}
