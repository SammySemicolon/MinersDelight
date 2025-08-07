package com.sammy.minersdelight.setup;

import com.sammy.minersdelight.*;
import com.sammy.minersdelight.content.item.*;
import net.minecraft.core.registries.*;
import net.minecraft.sounds.*;
import net.minecraft.world.food.*;
import net.minecraft.world.item.*;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.material.*;
import net.neoforged.neoforge.registries.*;
import team.lodestar.lodestone.systems.item.*;
import vectorwing.farmersdelight.common.*;
import vectorwing.farmersdelight.common.item.*;

import java.util.function.*;

import static com.sammy.minersdelight.MinersDelightMod.*;

public class MDItems {

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(BuiltInRegistries.ITEM, MODID);

    public static LodestoneItemProperties DEFAULT_PROPERTIES() {
        return new LodestoneItemProperties(MDCreativeTabs.CONTENT);
    }

    public static LodestoneItemProperties BOWL_PROPERTIES() {
        return DEFAULT_PROPERTIES().stacksTo(16).craftRemainder(Items.BOWL);
    }

    public static LodestoneItemProperties CUP_PROPERTIES() {
        return DEFAULT_PROPERTIES().stacksTo(16);
    }

    public static DeferredHolder<Item, Item> register(String name, FoodProperties foodProperties) {
        return register(name, foodProperties, Item::new);
    }

    public static DeferredHolder<Item, CopperCupFoodItem> registerCupFood(String name, FoodProperties foodProperties) {
        return register(name, CUP_PROPERTIES().food(MDFoodValues.createCupFoodProperties(foodProperties)), p -> new CopperCupFoodItem(p.craftRemainder(COPPER_CUP.get())));
    }

    public static DeferredHolder<Item, ConsumableItem> registerBowlFood(String name, FoodProperties foodProperties, boolean hasFoodEffectTooltip) {
        return register(name, BOWL_PROPERTIES().food(foodProperties), p -> new ConsumableItem(p.stacksTo(16).craftRemainder(Items.BOWL), hasFoodEffectTooltip));
    }

    public static <T extends Item> DeferredHolder<Item, T> register(String name, FoodProperties foodProperties, Function<LodestoneItemProperties, T> function) {
        return register(name, DEFAULT_PROPERTIES().food(foodProperties), function);
    }

    public static <T extends Item> DeferredHolder<Item, T> register(String name, Function<LodestoneItemProperties, T> function) {
        return register(name, DEFAULT_PROPERTIES(), function);
    }

    public static <T extends Item> DeferredHolder<Item, T> register(String name, LodestoneItemProperties properties, Function<LodestoneItemProperties, T> function) {
        LodestoneItemProperties.addToTabSorting(MinersDelightMod.path(name), properties);
        return ITEMS.register(name, () -> function.apply(properties));
    }

    public static final DeferredHolder<Item, BlockItem> COPPER_POT = register("copper_pot", DEFAULT_PROPERTIES(), (p) -> new BlockItem(MDBlocks.COPPER_POT.get(), p));
    public static final DeferredHolder<Item, BlockItem> STICKY_BASKET = register("sticky_basket", DEFAULT_PROPERTIES(), (p) -> new BlockItem(MDBlocks.STICKY_BASKET.get(), p));
    public static final DeferredHolder<Item, BlockItem> CAVE_CARROT_CRATE = register("cave_carrot_crate", DEFAULT_PROPERTIES(), (p) -> new BlockItem(MDBlocks.CAVE_CARROT_CRATE.get(), p));

    public static final DeferredHolder<Item, CopperCupItem> COPPER_CUP = register("copper_cup", CUP_PROPERTIES(), (p) -> new CopperCupItem(Fluids.EMPTY, p));
    public static final DeferredHolder<Item, CopperCupItem> WATER_CUP = register("water_cup", CUP_PROPERTIES().stacksTo(1), (p) -> new CopperCupItem(Fluids.WATER, p.craftRemainder(COPPER_CUP.get())));
    public static final DeferredHolder<Item, MilkCupItem> MILK_CUP = register("milk_cup", CUP_PROPERTIES().stacksTo(1), p -> new MilkCupItem(p.craftRemainder(COPPER_CUP.get())));
    public static final DeferredHolder<Item, SolidCupItem> POWDERED_SNOW_CUP = register("powder_snow_cup", CUP_PROPERTIES().stacksTo(1), (p) -> new SolidCupItem(Blocks.POWDER_SNOW, SoundEvents.BUCKET_EMPTY_POWDER_SNOW, p.craftRemainder(COPPER_CUP.get())));

    public static final DeferredHolder<Item, BlockItem> WILD_CAVE_CARROTS = register("wild_cave_carrots", p -> new BlockItem(MDBlocks.WILD_CAVE_CARROTS.get(), p));
    public static final DeferredHolder<Item, Item> GOSSYPIUM = register("gossypium", p -> new BlockItem(MDBlocks.GOSSYPIUM.get(), p));

    public static final DeferredHolder<Item, ItemNameBlockItem> CAVE_CARROT = register("cave_carrot", MDFoodValues.CAVE_CARROT, p -> new ItemNameBlockItem(MDBlocks.CAVE_CARROTS.get(), p));
    public static final DeferredHolder<Item, Item> BAKED_CAVE_CARROT = register("baked_cave_carrot", MDFoodValues.BAKED_CAVE_CARROT);
    public static final DeferredHolder<Item, Item> COPPER_CARROT = register("copper_carrot", MDFoodValues.COPPER_CARROT);
    public static final DeferredHolder<Item, Item> PASTA_WITH_VEGGIEBALLS = register("pasta_with_veggieballs", MDFoodValues.PASTA_WITH_VEGGIEBALLS);
    public static final DeferredHolder<Item, ConsumableItem> CAVE_SOUP = registerBowlFood("cave_soup", MDFoodValues.CAVE_SOUP, true);
    // Vegan food
    public static final DeferredHolder<Item, Item> VEGAN_PATTY = register("vegan_patty", MDFoodValues.VEGAN_PATTY);
    public static final DeferredHolder<Item, Item> VEGAN_HAMBURGER = register("vegan_hamburger", MDFoodValues.VEGAN_HAMBURGER);
    public static final DeferredHolder<Item, Item> VEGAN_WRAP = register("vegan_wrap", MDFoodValues.VEGAN_WRAP);
    public static final DeferredHolder<Item, ConsumableItem> VEGAN_STEAK_AND_POTATOES = registerBowlFood("vegan_steak_and_potatoes", MDFoodValues.VEGAN_STEAK_AND_POTATOES, true);
    public static final DeferredHolder<Item, Item> FAKE_MEATLOAF = register("fake_meatloaf", DEFAULT_PROPERTIES().stacksTo(1), p -> new BlockItem(MDBlocks.FAKE_MEATLOAF.get(), p));
    public static final DeferredHolder<Item, ConsumableItem> PLATE_OF_FAKE_MEATLOAF = registerBowlFood("plate_of_fake_meatloaf", MDFoodValues.PLATE_OF_FAKE_MEATLOAF, true);

    // Moss
    public static final DeferredHolder<Item, Item> MOSS = register("moss", MDFoodValues.MOSS);

    // Bat food
    public static final DeferredHolder<Item, Item> BAT_WING = register("bat_wing", MDFoodValues.BAT_WING);
    public static final DeferredHolder<Item, Item> SMOKED_BAT_WING = register("smoked_bat_wing", MDFoodValues.SMOKED_BAT_WING);
    public static final DeferredHolder<Item, Item> BAT_ROLLS = register("bat_rolls", MDFoodValues.BAT_ROLLS);
    public static final DeferredHolder<Item, Item> CAVE_HAMBURGER = register("cave_hamburger", MDFoodValues.CAVE_HAMBURGER);
    public static final DeferredHolder<Item, ConsumableItem> BAT_SOUP = registerBowlFood("bat_soup", MDFoodValues.BAT_SOUP, true);
    public static final DeferredHolder<Item, Item> IMPROVISED_BARBECUE_STICK = register("improvised_barbecue_stick", MDFoodValues.IMPROVISED_BARBECUE_STICK);
    public static final DeferredHolder<Item, Item> BAT_COOKIE = register("bat_cookie", MDFoodValues.BAT_COOKIE);

    // Insects
    public static final DeferredHolder<Item, Item> SPIDER_LEG = register("spider_leg", MDFoodValues.SPIDER_LEG);
    public static final DeferredHolder<Item, Item> BAKED_SPIDER_LEG = register("baked_spider_leg", MDFoodValues.BAKED_SPIDER_LEG);
    public static final DeferredHolder<Item, Item> GLAZED_ARACHNID_LIMBS = register("glazed_arachnid_limbs", DEFAULT_PROPERTIES().stacksTo(1), p -> new BlockItem(MDBlocks.GLAZED_ARACHNID_LIMBS.get(), p));
    public static final DeferredHolder<Item, ConsumableItem> PLATE_OF_GLAZED_ARACHNID_LIMBS = registerBowlFood("plate_of_glazed_arachnid_limbs", MDFoodValues.PLATE_OF_GLAZED_ARACHNID_LIMBS, true);
    public static final DeferredHolder<Item, SilverfishEggsItem> SILVERFISH_EGGS = register("silverfish_eggs", MDFoodValues.SILVERFISH_EGGS, SilverfishEggsItem::new);
    public static final DeferredHolder<Item, ConsumableItem> WEIRD_CAVIAR = registerBowlFood("weird_caviar", MDFoodValues.WEIRD_CAVIAR, false);
    public static final DeferredHolder<Item, Item> ARTHROPOD = register("arthropod", MDFoodValues.ARTHROPOD);
    public static final DeferredHolder<Item, Item> COOKED_ARTHROPOD = register("cooked_arthropod", MDFoodValues.COOKED_ARTHROPOD);
    public static final DeferredHolder<Item, Item> INSECT_SANDWICH = register("insect_sandwich", MDFoodValues.INSECT_SANDWICH);
    public static final DeferredHolder<Item, Item> INSECT_WRAP = register("insect_wrap", MDFoodValues.INSECT_WRAP);
    public static final DeferredHolder<Item, ConsumableItem> INSECT_STEW = registerBowlFood("insect_stew", MDFoodValues.INSECT_STEW, false);
    public static final DeferredHolder<Item, ConsumableItem> SEASONED_ARTHROPODS = registerBowlFood("seasoned_arthropods", MDFoodValues.SEASONED_ARTHROPODS, true);

    public static final DeferredHolder<Item, Item> CRUNCHY_BAR = register("crunchy_bar", MDFoodValues.CRUNCHY_BAR);
    public static final DeferredHolder<Item, Item> NUTRITIONAL_BAR = register("nutritional_bar", MDFoodValues.NUTRITIONAL_BAR);
    public static final DeferredHolder<Item, Item> GOLDEN_NUTRITIONAL_BAR = register("golden_nutritional_bar", MDFoodValues.GOLDEN_NUTRITIONAL_BAR);

    // Squid
    public static final DeferredHolder<Item, ConsumableItem> GLOW_INK_PASTA = registerBowlFood("glow_ink_pasta", MDFoodValues.GLOW_INK_PASTA, true);
    public static final DeferredHolder<Item, Item> GLOW_SQUID = register("glow_squid", MDFoodValues.GLOW_SQUID);
    public static final DeferredHolder<Item, Item> SQUID = register("squid", MDFoodValues.SQUID);
    public static final DeferredHolder<Item, Item> BAKED_SQUID = register("baked_squid", MDFoodValues.BAKED_SQUID);
    public static final DeferredHolder<Item, Item> TENTACLES = register("tentacles", MDFoodValues.TENTACLES);
    public static final DeferredHolder<Item, Item> BAKED_TENTACLES = register("baked_tentacles", MDFoodValues.BAKED_TENTACLES);
    public static final DeferredHolder<Item, Item> SQUID_SANDWICH = register("squid_sandwich", MDFoodValues.SQUID_SANDWICH);
    public static final DeferredHolder<Item, ConsumableItem> TAKOYAKI = registerBowlFood("takoyaki", MDFoodValues.TAKOYAKI, false);
    public static final DeferredHolder<Item, Item> TENTACLES_ON_A_STICK = register("tentacles_on_a_stick", MDFoodValues.TENTACLES_ON_A_STICK);
    public static final DeferredHolder<Item, Item> STUFFED_SQUID = register("stuffed_squid", DEFAULT_PROPERTIES().stacksTo(1), p -> new BlockItem(MDBlocks.STUFFED_SQUID.get(), p));
    public static final DeferredHolder<Item, ConsumableItem> BOWL_OF_STUFFED_SQUID = registerBowlFood("bowl_of_stuffed_squid", MDFoodValues.BOWL_OF_STUFFED_SQUID, true);

    // Cups
    public static final DeferredHolder<Item, CopperCupFoodItem> BEETROOT_SOUP_CUP = registerCupFood("beetroot_soup_cup", MDFoodValues.BEETROOT_SOUP);
    public static final DeferredHolder<Item, CopperCupFoodItem> MUSHROOM_STEW_CUP = registerCupFood("mushroom_stew_cup", MDFoodValues.MUSHROOM_STEW);
    public static final DeferredHolder<Item, CopperCupFoodItem> RABBIT_STEW_CUP = registerCupFood("rabbit_stew_cup", MDFoodValues.RABBIT_STEW);
    public static final DeferredHolder<Item, CopperCupFoodItem> BAKED_COD_STEW_CUP = registerCupFood("baked_cod_stew_cup", FoodValues.BAKED_COD_STEW);
    public static final DeferredHolder<Item, CopperCupFoodItem> NOODLE_SOUP_CUP = registerCupFood("noodle_soup_cup", FoodValues.NOODLE_SOUP);
    public static final DeferredHolder<Item, CopperCupFoodItem> BEEF_STEW_CUP = registerCupFood("beef_stew_cup", FoodValues.BEEF_STEW);
    public static final DeferredHolder<Item, CopperCupFoodItem> CHICKEN_SOUP_CUP = registerCupFood("chicken_soup_cup", FoodValues.CHICKEN_SOUP);
    public static final DeferredHolder<Item, CopperCupFoodItem> FISH_STEW_CUP = registerCupFood("fish_stew_cup", FoodValues.FISH_STEW);
    public static final DeferredHolder<Item, CopperCupFoodItem> PUMPKIN_SOUP_CUP = registerCupFood("pumpkin_soup_cup", FoodValues.PUMPKIN_SOUP);
    public static final DeferredHolder<Item, CopperCupFoodItem> VEGETABLE_SOUP_CUP = registerCupFood("vegetable_soup_cup", FoodValues.VEGETABLE_SOUP);
    public static final DeferredHolder<Item, CopperCupFoodItem> BONE_BROTH_CUP = registerCupFood("bone_broth_cup", FoodValues.BONE_BROTH);
    public static final DeferredHolder<Item, CopperCupFoodItem> CAVE_SOUP_CUP = registerCupFood("cave_soup_cup", MDFoodValues.CAVE_SOUP);
    public static final DeferredHolder<Item, CopperCupFoodItem> BAT_SOUP_CUP = registerCupFood("bat_soup_cup", MDFoodValues.BAT_SOUP);
    public static final DeferredHolder<Item, CopperCupFoodItem> INSECT_STEW_CUP = registerCupFood("insect_stew_cup", MDFoodValues.INSECT_STEW);

}