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
        return register(name, CUP_PROPERTIES().food(MDFoodValues.copyAndAddHaste(foodProperties)), p -> new CopperCupFoodItem(p.craftRemainder(COPPER_CUP.get())));
    }

    public static DeferredHolder<Item, ConsumableItem> registerBowlFood(String name, FoodProperties foodProperties) {
        return register(name, CUP_PROPERTIES().food(foodProperties), p -> new ConsumableItem(p.stacksTo(16).craftRemainder(Items.BOWL)));
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

    public static final DeferredHolder<Item, CopperCupItem> COPPER_CUP = register("copper_cup", CUP_PROPERTIES(), (p) -> new CopperCupItem(Fluids.EMPTY, p));
    public static final DeferredHolder<Item, CopperCupItem> WATER_CUP = register("water_cup", CUP_PROPERTIES().stacksTo(1), (p) -> new CopperCupItem(Fluids.WATER, p));
    public static final DeferredHolder<Item, MilkCupItem> MILK_CUP = register("milk_cup", CUP_PROPERTIES().stacksTo(1), MilkCupItem::new);
    public static final DeferredHolder<Item, SolidCupItem> POWDERED_SNOW_CUP = register("powder_snow_cup", CUP_PROPERTIES().stacksTo(1), (p) -> new SolidCupItem(Blocks.POWDER_SNOW, SoundEvents.BUCKET_EMPTY_POWDER_SNOW, p));

    public static final DeferredHolder<Item, BlockItem> WILD_CAVE_CARROTS = register("wild_cave_carrots", p -> new BlockItem(MDBlocks.WILD_CAVE_CARROTS.get(), p));
    public static final DeferredHolder<Item, ItemNameBlockItem> CAVE_CARROT = register("cave_carrot", MDFoodValues.CAVE_CARROT, p -> new ItemNameBlockItem(MDBlocks.CAVE_CARROTS.get(), p));
    public static final DeferredHolder<Item, Item> BAKED_CAVE_CARROT = register("baked_cave_carrot", MDFoodValues.BAKED_CAVE_CARROT);
    public static final DeferredHolder<Item, Item> GOSSYPIUM = register("gossypium", p -> new BlockItem(MDBlocks.GOSSYPIUM.get(), p));
    public static final DeferredHolder<Item, BlockItem> CAVE_CARROT_CRATE = register("cave_carrot_crate", DEFAULT_PROPERTIES(), (p) -> new BlockItem(MDBlocks.CAVE_CARROT_CRATE.get(), p));

    public static final DeferredHolder<Item, Item> BAT_WING = register("bat_wing", MDFoodValues.BAT_WING);
    public static final DeferredHolder<Item, Item> SMOKED_BAT_WING = register("smoked_bat_wing", MDFoodValues.SMOKED_BAT_WING);

    public static final DeferredHolder<Item, SilverfishEggsItem> SILVERFISH_EGGS = register("silverfish_eggs", MDFoodValues.SILVERFISH_EGGS, SilverfishEggsItem::new);
    public static final DeferredHolder<Item, ConsumableItem> WEIRD_CAVIAR = register("weird_caviar", MDFoodValues.WEIRD_CAVIAR, ConsumableItem::new);

    public static final DeferredHolder<Item, Item> SQUID = register("squid", MDFoodValues.SQUID);
    public static final DeferredHolder<Item, Item> GLOW_SQUID = register("glow_squid", MDFoodValues.GLOW_SQUID);
    public static final DeferredHolder<Item, Item> BAKED_SQUID = register("baked_squid", MDFoodValues.BAKED_SQUID);
    public static final DeferredHolder<Item, Item> TENTACLES = register("tentacles", MDFoodValues.TENTACLES);
    public static final DeferredHolder<Item, Item> BAKED_TENTACLES = register("baked_tentacles", MDFoodValues.BAKED_TENTACLES);
    public static final DeferredHolder<Item, Item> IMPROVISED_BARBECUE_STICK = register("improvised_barbecue_stick", MDFoodValues.IMPROVISED_BARBECUE_STICK);
    public static final DeferredHolder<Item, Item> PASTA_WITH_VEGGIEBALLS = register("pasta_with_veggieballs", MDFoodValues.PASTA_WITH_VEGGIEBALLS);
    public static final DeferredHolder<Item, Item> STUFFED_SQUID = register("stuffed_squid", DEFAULT_PROPERTIES().stacksTo(1), p -> new BlockItem(MDBlocks.STUFFED_SQUID.get(), p));

    public static final DeferredHolder<Item, ConsumableItem> CAVE_SOUP = registerBowlFood("cave_soup", MDFoodValues.CAVE_SOUP);
    public static final DeferredHolder<Item, ConsumableItem> BOWL_OF_STUFFED_SQUID = registerBowlFood("bowl_of_stuffed_squid", MDFoodValues.BOWL_OF_STUFFED_SQUID);

    public static final DeferredHolder<Item, CopperCupFoodItem> BEETROOT_SOUP_CUP = registerCupFood("beetroot_soup_cup", MDFoodValues.BEETROOT_SOUP);
    public static final DeferredHolder<Item, CopperCupFoodItem> MUSHROOM_STEW_CUP = registerCupFood("mushroom_stew_cup", MDFoodValues.MUSHROOM_STEW);
    public static final DeferredHolder<Item, CopperCupFoodItem> RABBIT_STEW_CUP = registerCupFood("rabbit_stew_cup", MDFoodValues.RABBIT_STEW);

    public static final DeferredHolder<Item, CopperCupFoodItem> BAKED_COD_STEW_CUP = registerCupFood("baked_cod_stew_cup", FoodValues.BAKED_COD_STEW);
    public static final DeferredHolder<Item, CopperCupFoodItem> NOODLE_SOUP_CUP = registerCupFood("noodle_soup_cup", FoodValues.NOODLE_SOUP);
    public static final DeferredHolder<Item, CopperCupFoodItem> BEEF_STEW_CUP = registerCupFood("beef_stew_cup", FoodValues.BEEF_STEW);
    public static final DeferredHolder<Item, CopperCupFoodItem> CAVE_SOUP_CUP = registerCupFood("cave_soup_cup", MDFoodValues.CAVE_SOUP);
    public static final DeferredHolder<Item, CopperCupFoodItem> CHICKEN_SOUP_CUP = registerCupFood("chicken_soup_cup", FoodValues.CHICKEN_SOUP);
    public static final DeferredHolder<Item, CopperCupFoodItem> FISH_STEW_CUP = registerCupFood("fish_stew_cup", FoodValues.FISH_STEW);
    public static final DeferredHolder<Item, HotCocoaCupItem> HOT_COCOA_CUP = register("hot_cocoa_cup", MDFoodValues.HOT_COCOA, p -> new HotCocoaCupItem(p.stacksTo(16)));
    public static final DeferredHolder<Item, CopperCupFoodItem> PUMPKIN_SOUP_CUP = registerCupFood("pumpkin_soup_cup", FoodValues.PUMPKIN_SOUP);
    public static final DeferredHolder<Item, CopperCupFoodItem> VEGETABLE_SOUP_CUP = registerCupFood("vegetable_soup_cup", FoodValues.VEGETABLE_SOUP);
}