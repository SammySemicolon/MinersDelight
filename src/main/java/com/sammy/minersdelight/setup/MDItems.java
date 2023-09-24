package com.sammy.minersdelight.setup;

import com.sammy.minersdelight.*;
import com.sammy.minersdelight.content.item.*;
import com.tterrag.registrate.*;
import com.tterrag.registrate.builders.*;
import com.tterrag.registrate.util.entry.*;
import com.tterrag.registrate.util.nullness.*;
import net.minecraft.sounds.*;
import net.minecraft.world.food.*;
import net.minecraft.world.item.*;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.material.*;
import vectorwing.farmersdelight.common.*;
import vectorwing.farmersdelight.common.item.*;
import vectorwing.farmersdelight.common.tag.*;

public class MDItems {
    public static final Registrate ITEM_REGISTRATE = MinersDelightMod.registrate().defaultCreativeTab(MDCreativeTabs.TAB_MINERS_DELIGHT.getKey());

    public static final ItemEntry<CopperCupItem> COPPER_CUP = setupItem("copper_cup", (p) -> new CopperCupItem(Fluids.EMPTY, p.stacksTo(16))).register();
    public static final ItemEntry<CopperCupItem> WATER_CUP = setupItem("water_cup", (p) -> new CopperCupItem(Fluids.WATER, p.stacksTo(1).craftRemainder(COPPER_CUP.get()))).register();
    public static final ItemEntry<MilkCupItem> MILK_CUP = setupItem("milk_cup", MilkCupItem::new).tag(ForgeTags.MILK).properties(p -> p.stacksTo(1).craftRemainder(COPPER_CUP.get())).register();
    public static final ItemEntry<SolidCupItem> POWDERED_SNOW_CUP = setupItem("powder_snow_cup", (p) -> new SolidCupItem(Blocks.POWDER_SNOW, SoundEvents.BUCKET_EMPTY_POWDER_SNOW, p.stacksTo(1).craftRemainder(COPPER_CUP.get()))).register();

    public static final ItemEntry<ItemNameBlockItem> CAVE_CARROT = setupItem("cave_carrot", MDFoodValues.CAVE_CARROT, p -> new ItemNameBlockItem(MDBlocks.CAVE_CARROTS.get(), p)).register();
    public static final ItemEntry<Item> BAKED_CAVE_CARROT = setupItem("baked_cave_carrot", MDFoodValues.BAKED_CAVE_CARROT).register();

    public static final ItemEntry<Item> BAT_WING = setupItem("bat_wing", MDFoodValues.BAT_WING).register();
    public static final ItemEntry<Item> SMOKED_BAT_WING = setupItem("smoked_bat_wing", MDFoodValues.SMOKED_BAT_WING).register();

    public static final ItemEntry<SilverfishEggsItem> SILVERFISH_EGGS = setupItem("silverfish_eggs", MDFoodValues.SILVERFISH_EGGS, SilverfishEggsItem::new).register();
    public static final ItemEntry<ConsumableItem> WEIRD_CAVIAR = setupBowlFoodItem("weird_caviar", MDFoodValues.WEIRD_CAVIAR).register();

    public static final ItemEntry<Item> SQUID = setupItem("squid", MDFoodValues.SQUID).register();
    public static final ItemEntry<Item> GLOW_SQUID = setupItem("glow_squid", MDFoodValues.GLOW_SQUID).register();
    public static final ItemEntry<Item> BAKED_SQUID = setupItem("baked_squid", MDFoodValues.BAKED_SQUID).register();
    public static final ItemEntry<Item> TENTACLES = setupItem("tentacles", MDFoodValues.TENTACLES).register();
    public static final ItemEntry<Item> BAKED_TENTACLES = setupItem("baked_tentacles", MDFoodValues.BAKED_TENTACLES).register();
    public static final ItemEntry<Item> IMPROVISED_BARBECUE_STICK = setupItem("improvised_barbecue_stick", MDFoodValues.IMPROVISED_BARBECUE_STICK).register();
    public static final ItemEntry<Item> PASTA_WITH_VEGGIEBALLS = setupItem("pasta_with_veggieballs", MDFoodValues.PASTA_WITH_VEGGIEBALLS).properties(p -> p.stacksTo(16)).register();
    public static final ItemEntry<ConsumableItem> CAVE_SOUP = setupBowlFoodItem("cave_soup", MDFoodValues.CAVE_SOUP).register();
    public static final ItemEntry<ConsumableItem> BOWL_OF_STUFFED_SQUID = setupBowlFoodItem("bowl_of_stuffed_squid", MDFoodValues.BOWL_OF_STUFFED_SQUID).register();

    public static final ItemEntry<CopperCupFoodItem> BEETROOT_SOUP_CUP = setupCupFoodItem("beetroot_soup_cup", MDFoodValues.BEETROOT_SOUP).register();
    public static final ItemEntry<CopperCupFoodItem> MUSHROOM_STEW_CUP = setupCupFoodItem("mushroom_stew_cup", MDFoodValues.MUSHROOM_STEW).register();
    public static final ItemEntry<CopperCupFoodItem> RABBIT_STEW_CUP = setupCupFoodItem("rabbit_stew_cup", MDFoodValues.RABBIT_STEW).register();

    public static final ItemEntry<CopperCupFoodItem> BAKED_COD_STEW_CUP = setupCupFoodItem("baked_cod_stew_cup", FoodValues.BAKED_COD_STEW).register();
    public static final ItemEntry<CopperCupFoodItem> NOODLE_SOUP_CUP = setupCupFoodItem("noodle_soup_cup", FoodValues.NOODLE_SOUP).register();
    public static final ItemEntry<CopperCupFoodItem> BEEF_STEW_CUP = setupCupFoodItem("beef_stew_cup", FoodValues.BEEF_STEW).register();
    public static final ItemEntry<CopperCupFoodItem> CAVE_SOUP_CUP = setupCupFoodItem("cave_soup_cup", MDFoodValues.CAVE_SOUP).register();
    public static final ItemEntry<CopperCupFoodItem> CHICKEN_SOUP_CUP = setupCupFoodItem("chicken_soup_cup", FoodValues.CHICKEN_SOUP).register();
    public static final ItemEntry<CopperCupFoodItem> FISH_STEW_CUP = setupCupFoodItem("fish_stew_cup", FoodValues.FISH_STEW).register();
    public static final ItemEntry<HotCocoaCupItem> HOT_COCOA_CUP = setupCupFoodItem("hot_cocoa_cup", HotCocoaCupItem::new).properties(p -> p.food(MDFoodValues.HOT_COCOA)).register();
    public static final ItemEntry<CopperCupFoodItem> PUMPKIN_SOUP_CUP = setupCupFoodItem("pumpkin_soup_cup", FoodValues.PUMPKIN_SOUP).register();
    public static final ItemEntry<CopperCupFoodItem> VEGETABLE_SOUP_CUP = setupCupFoodItem("vegetable_soup_cup", FoodValues.VEGETABLE_SOUP).register();

    public static ItemBuilder<CopperCupFoodItem, Registrate> setupCupFoodItem(String name, FoodProperties foodProperties) {
        return setupCupFoodItem(name, CopperCupFoodItem::new).properties(p -> p.food(MDFoodValues.copyAndAddHaste(foodProperties)));
    }

    public static <T extends Item> ItemBuilder<T, Registrate> setupCupFoodItem(String name, NonNullFunction<Item.Properties, T> factory) {
        return ITEM_REGISTRATE.item(name, factory).properties((p) -> p.craftRemainder(COPPER_CUP.get()).stacksTo(16));
    }

    public static ItemBuilder<ConsumableItem, Registrate> setupBowlFoodItem(String name, FoodProperties foodProperties) {
        return setupBowlFoodItem(name, p -> new ConsumableItem(p, true)).properties(p -> p.food(foodProperties));
    }

    public static <T extends Item> ItemBuilder<T, Registrate> setupBowlFoodItem(String name, NonNullFunction<Item.Properties, T> factory) {
        return ITEM_REGISTRATE.item(name, factory).properties((p) -> p.craftRemainder(Items.BOWL).stacksTo(16));
    }

    public static <T extends Item> ItemBuilder<T, Registrate> setupItem(String name, FoodProperties foodProperties, NonNullFunction<Item.Properties, T> factory) {
        return setupItem(name, factory).properties(p -> p.food(foodProperties));
    }
    public static ItemBuilder<Item, Registrate> setupItem(String name, FoodProperties foodProperties) {
        return setupItem(name, Item::new).properties(p -> p.food(foodProperties));
    }

    public static <T extends Item> ItemBuilder<T, Registrate> setupItem(String name, NonNullFunction<Item.Properties, T> factory) {
        return ITEM_REGISTRATE.item(name, factory);
    }

    public static void register() {
    }
}