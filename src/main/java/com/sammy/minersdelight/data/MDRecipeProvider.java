package com.sammy.minersdelight.data;

import com.sammy.minersdelight.*;
import com.sammy.minersdelight.setup.*;
import net.minecraft.data.*;
import net.minecraft.data.recipes.*;
import net.minecraft.world.item.*;
import net.minecraft.world.item.crafting.*;
import net.minecraftforge.common.*;
import net.minecraftforge.common.crafting.CompoundIngredient;
import vectorwing.farmersdelight.client.recipebook.*;
import vectorwing.farmersdelight.common.registry.*;
import vectorwing.farmersdelight.common.tag.*;
import vectorwing.farmersdelight.data.builder.*;

import java.util.function.*;

import static net.minecraft.data.recipes.ShapedRecipeBuilder.*;
import static net.minecraft.data.recipes.ShapelessRecipeBuilder.*;
import static net.minecraft.data.recipes.SimpleCookingRecipeBuilder.*;
import static vectorwing.farmersdelight.data.recipe.CookingRecipes.*;

public class MDRecipeProvider extends RecipeProvider {
    public final MDVanillaRecipeReplacements vanillaReplacements;

    public MDRecipeProvider(PackOutput output) {
        super(output);
        vanillaReplacements = new MDVanillaRecipeReplacements(output);
    }

    @Override
    protected void buildRecipes(Consumer<FinishedRecipe> consumer) {
//        vanillaReplacements.buildRecipes(consumer);


        shaped(RecipeCategory.DECORATIONS, MDBlocks.COPPER_POT.get())
                .define('X', Tags.Items.INGOTS_COPPER)
                .define('Y', Items.WOODEN_SHOVEL)
                .define('Z', Ingredient.of(Items.WATER_BUCKET, MDItems.WATER_CUP.get()))
                .pattern(" Y ").pattern("XZX").pattern("XXX")
                .unlockedBy("has_copper", has(Tags.Items.INGOTS_COPPER))
                .save(consumer, MinersDelightMod.path("copper_pot"));

        shaped(RecipeCategory.DECORATIONS, MDBlocks.STICKY_BASKET.get())
                .define('X', Tags.Items.RODS_WOODEN)
                .define('Y', Items.COBWEB)
                .pattern("X X").pattern("Y Y").pattern("XYX")
                .unlockedBy("has_cobweb", has(Items.COBWEB))
                .save(consumer, MinersDelightMod.path("sticky_basket"));

        shaped(RecipeCategory.DECORATIONS, MDItems.COPPER_CUP.get())
                .define('X', Tags.Items.INGOTS_COPPER)
                .pattern("X X").pattern(" X ")
                .unlockedBy("has_copper", has(Tags.Items.INGOTS_COPPER))
                .save(consumer, MinersDelightMod.path("copper_cup"));

        shaped(RecipeCategory.BUILDING_BLOCKS, MDBlocks.CAVE_CARROT_CRATE.get())
                .define('X', MDItems.CAVE_CARROT.get())
                .pattern("XXX").pattern("XXX").pattern("XXX")
                .unlockedBy("has_cave_carrot", has(MDItems.CAVE_CARROT.get()))
                .save(consumer, MinersDelightMod.path("cave_carrot_crate"));

        shaped(RecipeCategory.FOOD, MDItems.COPPER_CARROT.get())
                .define('X', Tags.Items.INGOTS_COPPER)
                .define('Y', MDItems.CAVE_CARROT.get())
                .pattern("XXX").pattern("XYX").pattern("XXX")
                .unlockedBy("has_copper", has(Tags.Items.INGOTS_COPPER))
                .save(consumer, MinersDelightMod.path("copper_carrot"));

        shapeless(RecipeCategory.FOOD, MDItems.IMPROVISED_BARBECUE_STICK.get(), 1)
                .requires(MDTags.BAKED_CAVE_CARROT)
                .requires(Items.BROWN_MUSHROOM)
                .requires(MDItems.SMOKED_BAT_WING.get())
                .requires(Tags.Items.RODS_WOODEN)
                .unlockedBy("has_cave_carrot", has(MDBlocks.CAVE_CARROTS.get()))
                .save(consumer, MinersDelightMod.path("improvised_barbecue_stick"));

        shapeless(RecipeCategory.FOOD, MDItems.VEGAN_STEAK_AND_POTATOES.get(), 1)
                .requires(Items.BAKED_POTATO)
                .requires(MDTags.BAKED_CAVE_CARROT)
                .requires(CommonTags.Items.CROPS_ONION)
                .requires(ModItems.COOKED_RICE.get())
                .requires(Items.BOWL)
                .unlockedBy("has_cave_carrot", has(MDBlocks.CAVE_CARROTS.get()))
                .save(consumer, MinersDelightMod.path("vegan_steak_and_potatoes"));

        shapeless(RecipeCategory.FOOD, MDItems.VEGAN_HAMBURGER.get(), 1)
                .requires(CommonTags.Items.BREAD)
                .requires(MDItems.VEGAN_PATTY.get())
                .requires(CommonTags.Items.CROPS_CABBAGE)
                .requires(CommonTags.Items.CROPS_TOMATO)
                .requires(CommonTags.Items.CROPS_ONION)
                .unlockedBy("has_cave_carrot", has(MDBlocks.CAVE_CARROTS.get()))
                .save(consumer, MinersDelightMod.path("vegan_hamburger"));
        shapeless(RecipeCategory.FOOD, MDItems.CAVE_HAMBURGER.get(), 1)
                .requires(CommonTags.Items.BREAD)
                .requires(CompoundIngredient.of(
                        Ingredient.of(MDItems.CRUNCHY_BAR.get()),
                        Ingredient.of(MDItems.VEGAN_PATTY.get())
                ))
                .requires(MDItems.SMOKED_BAT_WING.get())
                .requires(Ingredient.of(MDItems.BAKED_TENTACLES.get(),Items.BROWN_MUSHROOM),2)
                .unlockedBy("has_cave_carrot", has(MDBlocks.CAVE_CARROTS.get()))
                .save(consumer, MinersDelightMod.path("cave_hamburger"));

        shapeless(RecipeCategory.FOOD, MDItems.VEGAN_WRAP.get(), 1)
                .requires(CommonTags.Items.BREAD)
                .requires(CommonTags.Items.CROPS_ONION)
                .requires(MDTags.BAKED_CAVE_CARROT)
                .unlockedBy("has_cave_carrot", has(MDBlocks.CAVE_CARROTS.get()))
                .save(consumer, MinersDelightMod.path("vegan_wrap"));

        shapeless(RecipeCategory.FOOD, MDItems.BAT_COOKIE.get(), 8)
                .requires(MDItems.SMOKED_BAT_WING.get(), 1)
                .requires(Tags.Items.CROPS_WHEAT)
                .requires(Tags.Items.CROPS_WHEAT)
                .unlockedBy("has_bat_wing", has(MDItems.BAT_WING.get()))
                .save(consumer, MinersDelightMod.path("bat_cookie"));

        shapeless(RecipeCategory.FOOD, MDItems.TENTACLES_ON_A_STICK.get(), 1)
                .requires(Tags.Items.RODS_WOODEN)
                .requires(MDItems.BAKED_TENTACLES.get(),2)
                .unlockedBy("has_squid", has(MDTags.SQUID))
                .save(consumer, MinersDelightMod.path("tentacles_on_a_stick"));
        shapeless(RecipeCategory.FOOD, MDItems.SQUID_SANDWICH.get(), 1)
                .requires(MDTags.COOKED_FISHES_SQUID)
                .requires(CommonTags.Items.BREAD)
                .unlockedBy("has_squid", has(MDTags.SQUID))
                .save(consumer, MinersDelightMod.path("squid_sandwich"));

        shapeless(RecipeCategory.FOOD, MDItems.WEIRD_CAVIAR.get(), 1)
                .requires(Items.BOWL)
                .requires(MDItems.SILVERFISH_EGGS.get(), 3)
                .unlockedBy("has_silverfish_eggs", has(MDItems.SILVERFISH_EGGS.get()))
                .save(consumer, MinersDelightMod.path("weird_caviar"));

        shapeless(RecipeCategory.FOOD, MDItems.SEASONED_ARTHROPODS.get(), 1)
                .requires(Items.BOWL)
                .requires((Ingredient.of(MDItems.COOKED_ARTHROPOD.get(), MDItems.CRUNCHY_BAR.get())))
                .requires(MDItems.WEIRD_CAVIAR.get())
                .unlockedBy("has_arthropod", has(MDItems.ARTHROPOD.get()))
                .save(consumer, MinersDelightMod.path("seasoned_arthropods"));

        shapeless(RecipeCategory.FOOD, MDItems.INSECT_SANDWICH.get(), 2)
                .requires(CommonTags.Items.BREAD)
                .requires(MDTags.COOKED_INSECT_MEAT)
                .requires(CompoundIngredient.of(Ingredient.of(MDTags.COOKED_INSECT_MEAT), Ingredient.of(MDItems.WEIRD_CAVIAR.get())))
                .requires(CommonTags.Items.BREAD)
                .unlockedBy("has_arthropod", has(MDItems.ARTHROPOD.get()))
                .save(consumer, MinersDelightMod.path("insect_sandwich"));

        shapeless(RecipeCategory.FOOD, MDItems.INSECT_WRAP.get(), 1)
                .requires(CommonTags.Items.BREAD)
                .requires(CompoundIngredient.of(Ingredient.of(CommonTags.Items.CROPS_ONION), Ingredient.of(MDTags.RAW_INSECT_MEAT), Ingredient.of(MDTags.COOKED_INSECT_MEAT)))
                .requires(Ingredient.of(MDItems.ARTHROPOD.get(), MDItems.SILVERFISH_EGGS.get()),2)
                .unlockedBy("has_arthropod", has(MDItems.ARTHROPOD.get()))
                .save(consumer, MinersDelightMod.path("insect_wrap"));
        shapeless(RecipeCategory.FOOD, MDItems.INSECT_WRAP.get(), 1)
                .requires(CommonTags.Items.BREAD)
                .requires(CompoundIngredient.of( Ingredient.of(CommonTags.Items.CROPS_ONION), Ingredient.of(MDTags.RAW_INSECT_MEAT), Ingredient.of(MDTags.COOKED_INSECT_MEAT)))
                .requires(MDItems.CRUNCHY_BAR.get())
                .unlockedBy("has_arthropod", has(MDItems.ARTHROPOD.get()))
                .save(consumer, MinersDelightMod.path("alt_insect_wrap"));

        shapeless(RecipeCategory.FOOD, MDItems.CRUNCHY_BAR.get(), 4)
                .requires(MDItems.COOKED_ARTHROPOD.get(),3)
                .requires(Ingredient.of(MDItems.COOKED_ARTHROPOD.get(),MDItems.SILVERFISH_EGGS.get()))
                .requires(MDItems.SILVERFISH_EGGS.get(),3)
                .unlockedBy("has_arthropod", has(MDItems.ARTHROPOD.get()))
                .save(consumer, MinersDelightMod.path("crunchy_bar"));
        shapeless(RecipeCategory.FOOD, MDItems.NUTRITIONAL_BAR.get(), 4)
                .requires(MDItems.CRUNCHY_BAR.get(),4)
                .requires(Items.WHEAT,2)
                .requires(Items.HONEY_BOTTLE)
                .unlockedBy("has_crunchy_bar", has(MDItems.CRUNCHY_BAR.get()))
                .save(consumer, MinersDelightMod.path("nutritional_bar"));
        shaped(RecipeCategory.FOOD, MDItems.GOLDEN_NUTRITIONAL_BAR.get(), 1)
                .define('X', Tags.Items.NUGGETS_GOLD)
                .define('Y', MDItems.NUTRITIONAL_BAR.get())
                .pattern("XXX").pattern("XYX").pattern("XXX")
                .unlockedBy("has_nutritional_bar", has(MDItems.NUTRITIONAL_BAR.get()))
                .save(consumer, MinersDelightMod.path("golden_nutritional_bar"));

        shapeless(RecipeCategory.FOOD, MDBlocks.GLAZED_ARACHNID_LIMBS.get(), 1)
                .requires(MDItems.BAKED_SPIDER_LEG.get()).requires(Items.HONEY_BOTTLE).requires(MDItems.BAKED_SPIDER_LEG.get())
                .requires(MDItems.BAKED_SPIDER_LEG.get()).requires(ModItems.COOKED_RICE.get()).requires(MDItems.BAKED_SPIDER_LEG.get())
                .requires(Items.GLOW_BERRIES).requires(Items.BOWL).requires(Items.GLOW_BERRIES)
                .unlockedBy("has_spider_leg", has(MDItems.SPIDER_LEG.get()))
                .save(consumer, MinersDelightMod.path("glazed_arachnid_limbs"));

        shapeless(RecipeCategory.FOOD, MDItems.CAVE_CARROT.get(), 9)
                .requires(MDBlocks.CAVE_CARROT_CRATE.get())
                .unlockedBy("has_cave_carrot", has(MDItems.CAVE_CARROT.get()))
                .save(consumer, MinersDelightMod.path("cave_carrots_from_crate"));

        shapeless(RecipeCategory.MISC, Items.STRING, 2)
                .requires(MDBlocks.GOSSYPIUM.get())
                .unlockedBy("has_gossypium", has(MDBlocks.GOSSYPIUM.get()))
                .save(consumer, MinersDelightMod.path("string_from_gossypium"));

        smelting(Ingredient.of(MDItems.CAVE_CARROT.get()), RecipeCategory.FOOD, MDItems.BAKED_CAVE_CARROT.get(), 0.25f, 200)
                .unlockedBy("has_cave_carrot", has(MDItems.CAVE_CARROT.get()))
                .save(consumer, MinersDelightMod.path("baked_cave_carrot"));
        smoking(Ingredient.of(MDItems.CAVE_CARROT.get()), RecipeCategory.FOOD, MDItems.BAKED_CAVE_CARROT.get(), 0.25f, 100)
                .unlockedBy("has_cave_carrot", has(MDItems.CAVE_CARROT.get()))
                .save(consumer, MinersDelightMod.path("baked_cave_carrot_smoking"));
        campfireCooking(Ingredient.of(MDItems.CAVE_CARROT.get()), RecipeCategory.FOOD, MDItems.BAKED_CAVE_CARROT.get(), 0.25f, 600)
                .unlockedBy("has_cave_carrot", has(MDItems.CAVE_CARROT.get()))
                .save(consumer, MinersDelightMod.path("baked_cave_carrot_campfire"));

        smelting(Ingredient.of(MDItems.BAT_WING.get()), RecipeCategory.FOOD, MDItems.SMOKED_BAT_WING.get(), 0.25f, 200)
                .unlockedBy("has_bat_wing", has(MDItems.BAT_WING.get()))
                .save(consumer, MinersDelightMod.path("smoked_bat_wing"));
        smoking(Ingredient.of(MDItems.BAT_WING.get()), RecipeCategory.FOOD, MDItems.SMOKED_BAT_WING.get(), 0.25f, 100)
                .unlockedBy("has_bat_wing", has(MDItems.BAT_WING.get()))
                .save(consumer, MinersDelightMod.path("smoked_bat_wing_smoking"));
        campfireCooking(Ingredient.of(MDItems.BAT_WING.get()), RecipeCategory.FOOD, MDItems.SMOKED_BAT_WING.get(), 0.25f, 600)
                .unlockedBy("has_bat_wing", has(MDItems.BAT_WING.get()))
                .save(consumer, MinersDelightMod.path("smoked_bat_wing_campfire"));

        smelting(Ingredient.of(MDItems.TENTACLES.get()), RecipeCategory.FOOD, MDItems.BAKED_TENTACLES.get(), 0.25f, 200)
                .unlockedBy("has_tentacles", has(MDItems.TENTACLES.get()))
                .save(consumer, MinersDelightMod.path("baked_tentacles"));
        smoking(Ingredient.of(MDItems.TENTACLES.get()), RecipeCategory.FOOD, MDItems.BAKED_TENTACLES.get(), 0.25f, 100)
                .unlockedBy("has_tentacles", has(MDItems.TENTACLES.get()))
                .save(consumer, MinersDelightMod.path("baked_tentacles_smoking"));
        campfireCooking(Ingredient.of(MDItems.TENTACLES.get()), RecipeCategory.FOOD, MDItems.BAKED_TENTACLES.get(), 0.25f, 600)
                .unlockedBy("has_tentacles", has(MDItems.TENTACLES.get()))
                .save(consumer, MinersDelightMod.path("baked_tentacles_campfire"));

        smelting(Ingredient.of(MDItems.SQUID.get(), MDItems.GLOW_SQUID.get()), RecipeCategory.FOOD, MDItems.BAKED_SQUID.get(), 0.25f, 200)
                .unlockedBy("has_squid", has(MDTags.SQUID))
                .save(consumer, MinersDelightMod.path("baked_squid"));
        smoking(Ingredient.of(MDItems.SQUID.get(), MDItems.GLOW_SQUID.get()), RecipeCategory.FOOD, MDItems.BAKED_SQUID.get(), 0.25f, 100)
                .unlockedBy("has_squid", has(MDTags.SQUID))
                .save(consumer, MinersDelightMod.path("baked_squid_smoking"));
        campfireCooking(Ingredient.of(MDItems.SQUID.get(), MDItems.GLOW_SQUID.get()), RecipeCategory.FOOD, MDItems.BAKED_SQUID.get(), 0.25f, 600)
                .unlockedBy("has_squid", has(MDTags.SQUID))
                .save(consumer, MinersDelightMod.path("baked_squid_campfire"));

        smelting(Ingredient.of(MDItems.ARTHROPOD.get()), RecipeCategory.FOOD, MDItems.COOKED_ARTHROPOD.get(), 0.25f, 200)
                .unlockedBy("has_arthropod", has(MDItems.ARTHROPOD.get()))
                .save(consumer, MinersDelightMod.path("cooked_arthropod"));

        smelting(Ingredient.of(MDItems.SPIDER_LEG.get()), RecipeCategory.FOOD, MDItems.BAKED_SPIDER_LEG.get(), 0.25f, 300)
                .unlockedBy("has_spider_leg", has(MDItems.SPIDER_LEG.get()))
                .save(consumer, MinersDelightMod.path("baked_spider_leg"));
        smoking(Ingredient.of(MDItems.SPIDER_LEG.get()), RecipeCategory.FOOD, MDItems.BAKED_SPIDER_LEG.get(), 0.25f, 150)
                .unlockedBy("has_spider_leg", has(MDItems.SPIDER_LEG.get()))
                .save(consumer, MinersDelightMod.path("baked_spider_leg_smoking"));
        campfireCooking(Ingredient.of(MDItems.SPIDER_LEG.get()), RecipeCategory.FOOD, MDItems.BAKED_SPIDER_LEG.get(), 0.25f, 600)
                .unlockedBy("has_spider_leg", has(MDItems.SPIDER_LEG.get()))
                .save(consumer, MinersDelightMod.path("baked_spider_leg_campfire"));


        CookingPotRecipeBuilder.cookingPotRecipe(MDItems.PASTA_WITH_VEGGIEBALLS.get(), 1, SLOW_COOKING, LARGE_EXP)
                .addIngredient(MDTags.BAKED_CAVE_CARROT)
                .addIngredient(CommonTags.Items.PASTA)
                .addIngredient(ModItems.TOMATO_SAUCE.get())
                .unlockedByAnyIngredient(MDItems.CAVE_CARROT.get(), ModItems.RAW_PASTA.get(), ModItems.TOMATO_SAUCE.get())
                .setRecipeBookTab(CookingPotRecipeBookTab.MEALS)
                .save(consumer, "minersdelight:cooking/pasta_with_veggieballs");

        CookingPotRecipeBuilder.cookingPotRecipe(MDBlocks.FAKE_MEATLOAF.get(), 1, SLOW_COOKING, LARGE_EXP, Items.BOWL)
                .addIngredient(Ingredient.of(MDTags.BAKED_CAVE_CARROT),2)
                .addIngredient(Ingredient.of(MDItems.BAKED_CAVE_CARROT.get(), Items.CARROT))
                .addIngredient(CommonTags.Items.CROPS_ONION)
                .unlockedByAnyIngredient(MDItems.CAVE_CARROT.get())
                .setRecipeBookTab(CookingPotRecipeBookTab.MEALS)
                .save(consumer, "minersdelight:cooking/fake_meatloaf");

        CookingPotRecipeBuilder.cookingPotRecipe(MDItems.CAVE_SOUP.get(), 1, NORMAL_COOKING, MEDIUM_EXP)
                .addIngredient(MDTags.CAVE_CARROTS_VEGETABLE_ITEM)
                .addIngredient(Items.RED_MUSHROOM)
                .addIngredient(Items.BROWN_MUSHROOM, 2)
                .unlockedByAnyIngredient(MDItems.CAVE_CARROT.get(), Items.RED_MUSHROOM, Items.BROWN_MUSHROOM)
                .setRecipeBookTab(CookingPotRecipeBookTab.MEALS)
                .save(consumer, "minersdelight:cooking/cave_soup");

        CookingPotRecipeBuilder.cookingPotRecipe(MDItems.INSECT_STEW.get(), 1, NORMAL_COOKING, MEDIUM_EXP)
                .addIngredient(MDItems.ARTHROPOD.get())
                .addIngredient(Ingredient.of(MDItems.ARTHROPOD.get(), MDItems.SILVERFISH_EGGS.get()),3)
                .unlockedByItems("has_arthropod", MDItems.ARTHROPOD.get())
                .setRecipeBookTab(CookingPotRecipeBookTab.MEALS)
                .save(consumer, "minersdelight:cooking/insect_stew");
        CookingPotRecipeBuilder.cookingPotRecipe(MDItems.INSECT_STEW.get(), 1, NORMAL_COOKING, MEDIUM_EXP)
                .addIngredient(MDItems.CRUNCHY_BAR.get())
                .addIngredient((Ingredient.of(MDItems.ARTHROPOD.get(), MDItems.CRUNCHY_BAR.get())))
                .unlockedByItems("has_arthropod", MDItems.ARTHROPOD.get())
                .setRecipeBookTab(CookingPotRecipeBookTab.MEALS)
                .save(consumer, "minersdelight:cooking/alt_insect_stew");

        CookingPotRecipeBuilder.cookingPotRecipe(MDItems.BAT_SOUP.get(), 1, NORMAL_COOKING, MEDIUM_EXP)
                .addIngredient(MDTags.BAT_WING)
                .addIngredient(MDTags.BAT_WING)
                .unlockedByItems("has_bat_wing", MDItems.BAT_WING.get())
                .setRecipeBookTab(CookingPotRecipeBookTab.MEALS)
                .save(consumer, "minersdelight:cooking/bat_soup");

        CookingPotRecipeBuilder.cookingPotRecipe(MDItems.BAT_ROLLS.get(), 1, NORMAL_COOKING, MEDIUM_EXP)
                .addIngredient(MDTags.BAT_WING)
                .addIngredient(CompoundIngredient.of(
                        Ingredient.of(CommonTags.Items.RAW_MEAT),
                        Ingredient.of(CommonTags.Items.RAW_FISHES),
                        Ingredient.of(CommonTags.Items.VEGETABLES),
                        Ingredient.of(Tags.Items.MUSHROOMS)
                ))
                .unlockedByItems("has_bat_wing", MDItems.BAT_WING.get())
                .setRecipeBookTab(CookingPotRecipeBookTab.MEALS)
                .save(consumer, "minersdelight:cooking/bat_rolls");

        CookingPotRecipeBuilder.cookingPotRecipe(MDItems.TAKOYAKI.get(), 1, NORMAL_COOKING, MEDIUM_EXP, Items.BOWL)
                .addIngredient(MDTags.TENTACLES)
                .addIngredient(MDTags.TENTACLES)
                .addIngredient(CommonTags.Items.DOUGH)
                .addIngredient(CommonTags.Items.CROPS_ONION)
                .unlockedBy("has_squid", has(MDTags.SQUID))
                .setRecipeBookTab(CookingPotRecipeBookTab.MEALS)
                .save(consumer, "minersdelight:cooking/takoyaki");

        CookingPotRecipeBuilder.cookingPotRecipe(MDBlocks.STUFFED_SQUID.get(), 1, SLOW_COOKING, LARGE_EXP, Items.BOWL)
                .addIngredient(Ingredient.of(MDTags.SQUID))
                .addIngredient(ModItems.RICE.get(), 2)
                .addIngredient(Ingredient.of(MDItems.CAVE_CARROT.get(), Items.CARROT))
                .addIngredient(Tags.Items.EGGS)
                .addIngredient(CommonTags.Items.CROPS_ONION)
                .unlockedByAnyIngredient(MDItems.SQUID.get(), MDItems.GLOW_SQUID.get(), MDItems.BAKED_SQUID.get())
                .setRecipeBookTab(CookingPotRecipeBookTab.MEALS)
                .save(consumer, "minersdelight:cooking/stuffed_squid");

        CookingPotRecipeBuilder.cookingPotRecipe(MDItems.GLOW_INK_PASTA.get(), 1, SLOW_COOKING, LARGE_EXP, Items.BOWL)
                .addIngredient(CommonTags.Items.RAW_FISHES)
                .addIngredient(CommonTags.Items.PASTA)
                .addIngredient(Items.GLOW_BERRIES)
                .addIngredient(Items.GLOW_INK_SAC)
                .unlockedBy("has_glow_ink_sac", has(Items.GLOW_INK_SAC))
                .setRecipeBookTab(CookingPotRecipeBookTab.MEALS)
                .save(consumer, "minersdelight:cooking/glow_ink_pasta");

        CuttingBoardRecipeBuilder.cuttingRecipe(Ingredient.of(Items.MOSS_BLOCK), Ingredient.of(Tags.Items.SHEARS), MDItems.MOSS.get(), 4)
                .save(consumer, MinersDelightMod.path("cutting/moss"));

        CuttingBoardRecipeBuilder.cuttingRecipe(Ingredient.of(MDItems.BAKED_CAVE_CARROT.get()), Ingredient.of(CommonTags.Items.TOOLS_KNIVES), MDItems.VEGAN_PATTY.get(), 2)
                .save(consumer, MinersDelightMod.path("cutting/vegan_patty"));

        CuttingBoardRecipeBuilder.cuttingRecipe(Ingredient.of(MDItems.BAT_WING.get()), Ingredient.of(CommonTags.Items.TOOLS_KNIVES), Items.PHANTOM_MEMBRANE, 1)
                .addResultWithChance(Items.PHANTOM_MEMBRANE, 0.5f, 2)
                .save(consumer, MinersDelightMod.path("cutting/bat_wing"));

        CuttingBoardRecipeBuilder.cuttingRecipe(Ingredient.of(MDItems.SQUID.get()), Ingredient.of(CommonTags.Items.TOOLS_KNIVES), MDItems.TENTACLES.get(), 3)
                .addResultWithChance(MDItems.TENTACLES.get(), 0.5f)
                .addResult(Items.INK_SAC)
                .addResultWithChance(Items.INK_SAC, 0.5f, 2)
                .save(consumer, MinersDelightMod.path("cutting/squid"));

        CuttingBoardRecipeBuilder.cuttingRecipe(Ingredient.of(MDItems.GLOW_SQUID.get()), Ingredient.of(CommonTags.Items.TOOLS_KNIVES), MDItems.TENTACLES.get(), 3)
                .addResultWithChance(MDItems.TENTACLES.get(), 0.5f)
                .addResult(Items.GLOW_INK_SAC)
                .addResultWithChance(Items.GLOW_INK_SAC, 0.5f, 2)
                .save(consumer, MinersDelightMod.path("cutting/glow_squid"));

        CuttingBoardRecipeBuilder.cuttingRecipe(Ingredient.of(MDItems.BAKED_SQUID.get()), Ingredient.of(CommonTags.Items.TOOLS_KNIVES), MDItems.BAKED_TENTACLES.get(), 3)
                .addResultWithChance(MDItems.BAKED_TENTACLES.get(), 0.5f)
                .save(consumer, MinersDelightMod.path("cutting/baked_squid"));

        CuttingBoardRecipeBuilder.cuttingRecipe(Ingredient.of(MDBlocks.WILD_CAVE_CARROTS.get()), Ingredient.of(Tags.Items.SHEARS), MDBlocks.GOSSYPIUM.get(), 1)
                .addResultWithChance(MDBlocks.GOSSYPIUM.get(), 0.5F, 1)
                .addResult(MDItems.CAVE_CARROT.get())
                .addResultWithChance(MDItems.CAVE_CARROT.get(), 0.5f, 2)
                .save(consumer, MinersDelightMod.path("cutting/wild_cave_carrot"));

        CuttingBoardRecipeBuilder.cuttingRecipe(Ingredient.of(MDItems.SPIDER_LEG.get()), Ingredient.of(CommonTags.Items.TOOLS_KNIVES),
                        MDItems.ARTHROPOD.get(), 2).addResult(Items.BONE_MEAL)
                .save(consumer, MinersDelightMod.path("cutting/baked_spider_leg"));
        CuttingBoardRecipeBuilder.cuttingRecipe(Ingredient.of(MDItems.BAKED_SPIDER_LEG.get()), Ingredient.of(CommonTags.Items.TOOLS_KNIVES),
                        MDItems.COOKED_ARTHROPOD.get(), 2).addResult(Items.BONE_MEAL)
                .save(consumer, MinersDelightMod.path("cutting/spider_leg"));
    }
}
