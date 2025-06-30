package com.sammy.minersdelight.data;

import com.sammy.minersdelight.*;
import com.sammy.minersdelight.setup.*;
import net.minecraft.data.*;
import net.minecraft.data.recipes.*;
import net.minecraft.world.item.*;
import net.minecraft.world.item.crafting.*;
import net.minecraftforge.common.*;
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
        vanillaReplacements.buildRecipes(consumer);

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

        shapeless(RecipeCategory.FOOD, MDItems.IMPROVISED_BARBECUE_STICK.get(), 2)
                .requires(Tags.Items.RODS_WOODEN)
                .requires(Tags.Items.RODS_WOODEN)
                .requires(MDTags.BAKED_CAVE_CARROT)
                .requires(Items.BROWN_MUSHROOM)
                .requires(MDItems.SMOKED_BAT_WING.get())
                .requires(MDItems.SMOKED_BAT_WING.get())
                .unlockedBy("has_cave_carrot", has(MDBlocks.CAVE_CARROTS.get()))
                .save(consumer, MinersDelightMod.path("improvised_barbecue_stick"));

        shapeless(RecipeCategory.FOOD, MDItems.VEGAN_STEAK_AND_POTATOES.get(), 1)
                .requires(Items.BAKED_POTATO)
                .requires(MDTags.BAKED_CAVE_CARROT)
                .requires(ModItems.ONION.get())
                .requires(ModItems.COOKED_RICE.get())
                .requires(Items.BOWL)
                .unlockedBy("has_cave_carrot", has(MDBlocks.CAVE_CARROTS.get()))
                .save(consumer, MinersDelightMod.path("vegan_steak_and_potatoes"));

        shapeless(RecipeCategory.FOOD, MDItems.VEGAN_HAMBURGER.get(), 1)
                .requires(ForgeTags.BREAD)
                .requires(MDItems.VEGAN_PATTY.get())
                .requires(ForgeTags.CROPS_CABBAGE)
                .requires(ModItems.TOMATO.get())
                .requires(ModItems.ONION.get())
                .unlockedBy("has_cave_carrot", has(MDBlocks.CAVE_CARROTS.get()))
                .save(consumer, MinersDelightMod.path("vegan_hamburger"));

        shapeless(RecipeCategory.FOOD, MDItems.VEGAN_WRAP.get(), 1)
                .requires(ForgeTags.BREAD)
                .requires(ForgeTags.BREAD)
                .requires(MDTags.BAKED_CAVE_CARROT)
                .unlockedBy("has_cave_carrot", has(MDBlocks.CAVE_CARROTS.get()))
                .save(consumer, MinersDelightMod.path("vegan_wrap"));

        shapeless(RecipeCategory.FOOD, MDItems.BAT_COOKIE.get(), 8)
                .requires(Tags.Items.CROPS_WHEAT)
                .requires(MDItems.SMOKED_BAT_WING.get(), 1)
                .requires(Tags.Items.CROPS_WHEAT)
                .unlockedBy("has_bat_wing", has(MDItems.BAT_WING.get()))
                .save(consumer, MinersDelightMod.path("bat_cookie"));

        shapeless(RecipeCategory.FOOD, MDItems.SQUID_SANDWICH.get(), 1)
                .requires(MDTags.COOKED_FISHES_SQUID)
                .requires(ForgeTags.BREAD)
                .unlockedBy("has_squid", has(MDTags.SQUID))
                .save(consumer, MinersDelightMod.path("squid_sandwich"));

        shapeless(RecipeCategory.FOOD, MDItems.WEIRD_CAVIAR.get(), 1)
                .requires(Items.BOWL)
                .requires(MDItems.SILVERFISH_EGGS.get(), 3)
                .unlockedBy("has_silverfish_eggs", has(MDItems.SILVERFISH_EGGS.get()))
                .save(consumer, MinersDelightMod.path("weird_caviar"));

        shapeless(RecipeCategory.FOOD, MDItems.SEASONED_ARTHROPODS.get(), 1)
                .requires(Items.BOWL)
                .requires(MDItems.COOKED_ARTHROPOD.get())
                .requires(MDItems.WEIRD_CAVIAR.get())
                .unlockedBy("has_arthropod", has(MDItems.ARTHROPOD.get()))
                .save(consumer, MinersDelightMod.path("seasoned_arthropods"));

        shapeless(RecipeCategory.FOOD, MDItems.INSECT_SANDWICH.get(), 1)
                .requires(MDTags.COOKED_INSECT_MEAT)
                .requires(MDTags.COOKED_INSECT_MEAT)
                .requires(ForgeTags.BREAD)
                .unlockedBy("has_arthropod", has(MDItems.ARTHROPOD.get()))
                .save(consumer, MinersDelightMod.path("insect_sandwich"));

        shapeless(RecipeCategory.FOOD, MDItems.INSECT_WRAP.get(), 1)
                .requires(MDTags.INSECT_MEAT)
                .requires(MDTags.INSECT_MEAT)
                .requires(ForgeTags.BREAD)
                .unlockedBy("has_arthropod", has(MDItems.ARTHROPOD.get()))
                .save(consumer, MinersDelightMod.path("insect_wrap"));

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
                .unlockedBy("has_arthropod", has(MDItems.ARTHROPOD))
                .save(consumer, MinersDelightMod.path("cooked_arthropod"));

        CookingPotRecipeBuilder.cookingPotRecipe(MDItems.PASTA_WITH_VEGGIEBALLS.get(), 1, SLOW_COOKING, LARGE_EXP)
                .addIngredient(MDTags.BAKED_CAVE_CARROT)
                .addIngredient(ModItems.RAW_PASTA.get())
                .addIngredient(ModItems.TOMATO_SAUCE.get())
                .unlockedByAnyIngredient(MDItems.CAVE_CARROT.get(), ModItems.RAW_PASTA.get(), ModItems.TOMATO_SAUCE.get())
                .setRecipeBookTab(CookingPotRecipeBookTab.MEALS)
                .build(consumer, "miners_delight:cooking/pasta_with_veggieballs");

        CookingPotRecipeBuilder.cookingPotRecipe(MDItems.CAVE_SOUP.get(), 1, NORMAL_COOKING, MEDIUM_EXP)
                .addIngredient(MDTags.CAVE_CARROTS_VEGETABLE_ITEM)
                .addIngredient(Items.RED_MUSHROOM)
                .addIngredient(Items.BROWN_MUSHROOM, 2)
                .unlockedByAnyIngredient(MDItems.CAVE_CARROT.get(), Items.RED_MUSHROOM, Items.BROWN_MUSHROOM)
                .setRecipeBookTab(CookingPotRecipeBookTab.MEALS)
                .build(consumer, "miners_delight:cooking/cave_soup");

        CookingPotRecipeBuilder.cookingPotRecipe(MDItems.INSECT_STEW.get(), 1, NORMAL_COOKING, MEDIUM_EXP)
                .addIngredient(MDItems.ARTHROPOD.get(), 2)
                .unlockedByItems("has_arthropod", MDItems.ARTHROPOD.get())
                .setRecipeBookTab(CookingPotRecipeBookTab.MEALS)
                .build(consumer, "miners_delight:cooking/insect_stew");

        CookingPotRecipeBuilder.cookingPotRecipe(MDItems.BAT_SOUP.get(), 1, NORMAL_COOKING, MEDIUM_EXP)
                .addIngredient(MDTags.BAT_WING)
                .addIngredient(MDTags.BAT_WING)
                .unlockedByItems("has_bat_wing", MDItems.BAT_WING.get())
                .setRecipeBookTab(CookingPotRecipeBookTab.MEALS)
                .build(consumer, "miners_delight:cooking/bat_soup");

        CookingPotRecipeBuilder.cookingPotRecipe(MDItems.BAT_ROLLS.get(), 1, NORMAL_COOKING, MEDIUM_EXP)
                .addIngredient(MDTags.BAT_WING)
                .addIngredient(ModTags.CABBAGE_ROLL_INGREDIENTS)
                .unlockedByItems("has_bat_wing", MDItems.BAT_WING.get())
                .setRecipeBookTab(CookingPotRecipeBookTab.MEALS)
                .build(consumer, "miners_delight:cooking/bat_rolls");

        CookingPotRecipeBuilder.cookingPotRecipe(MDItems.TAKOYAKI.get(), 1, NORMAL_COOKING, MEDIUM_EXP, Items.BOWL)
                .addIngredient(MDTags.TENTACLES)
                .addIngredient(MDTags.TENTACLES)
                .addIngredient(ForgeTags.DOUGH)
                .addIngredient(ModItems.ONION.get())
                .unlockedBy("has_squid", has(MDTags.SQUID))
                .setRecipeBookTab(CookingPotRecipeBookTab.MEALS)
                .build(consumer, "miners_delight:cooking/takoyaki");

        CookingPotRecipeBuilder.cookingPotRecipe(MDBlocks.STUFFED_SQUID.get(), 1, SLOW_COOKING, LARGE_EXP, Items.BOWL)
                .addIngredient(Ingredient.of(MDTags.SQUID))
                .addIngredient(ModItems.RICE.get(), 2)
                .addIngredient(Ingredient.of(MDItems.CAVE_CARROT.get(), Items.CARROT))
                .addIngredient(Tags.Items.EGGS)
                .addIngredient(ModItems.ONION.get())
                .unlockedByAnyIngredient(MDItems.SQUID.get(), MDItems.GLOW_SQUID.get(), MDItems.BAKED_SQUID.get())
                .setRecipeBookTab(CookingPotRecipeBookTab.MEALS)
                .build(consumer, "miners_delight:cooking/stuffed_squid");

        CookingPotRecipeBuilder.cookingPotRecipe(MDItems.GLOW_INK_PASTA.get(), 1, SLOW_COOKING, LARGE_EXP, Items.BOWL)
                .addIngredient(ForgeTags.RAW_FISHES)
                .addIngredient(ModItems.RAW_PASTA.get())
                .addIngredient(Items.GLOW_BERRIES)
                .addIngredient(Items.GLOW_INK_SAC)
                .unlockedBy("has_glow_ink_sac", has(Items.GLOW_INK_SAC))
                .setRecipeBookTab(CookingPotRecipeBookTab.MEALS)
                .build(consumer, "miners_delight:cooking/glow_ink_pasta");

        CuttingBoardRecipeBuilder.cuttingRecipe(Ingredient.of(Items.MOSS_BLOCK), Ingredient.of(Tags.Items.SHEARS), MDItems.MOSS.get(), 4)
                .build(consumer, "miners_delight:cutting/moss");

        CuttingBoardRecipeBuilder.cuttingRecipe(Ingredient.of(MDItems.BAKED_CAVE_CARROT.get()), Ingredient.of(ForgeTags.TOOLS_KNIVES), MDItems.VEGAN_PATTY, 2)
                .build(consumer, "miners_delight:cutting/vegan_patty");

        CuttingBoardRecipeBuilder.cuttingRecipe(Ingredient.of(MDItems.BAT_WING.get()), Ingredient.of(ForgeTags.TOOLS_KNIVES), Items.PHANTOM_MEMBRANE, 1)
                .addResultWithChance(Items.PHANTOM_MEMBRANE, 0.5f, 2)
                .build(consumer, "miners_delight:cutting/bat_wing");

        CuttingBoardRecipeBuilder.cuttingRecipe(Ingredient.of(MDItems.SQUID.get()), Ingredient.of(ForgeTags.TOOLS_KNIVES), MDItems.TENTACLES.get(), 3)
                .addResultWithChance(MDItems.TENTACLES.get(), 0.5f)
                .addResult(Items.INK_SAC)
                .addResultWithChance(Items.INK_SAC, 0.5f, 2)
                .build(consumer, "miners_delight:cutting/squid");

        CuttingBoardRecipeBuilder.cuttingRecipe(Ingredient.of(MDItems.GLOW_SQUID.get()), Ingredient.of(ForgeTags.TOOLS_KNIVES), MDItems.TENTACLES.get(), 3)
                .addResultWithChance(MDItems.TENTACLES.get(), 0.5f)
                .addResult(Items.GLOW_INK_SAC)
                .addResultWithChance(Items.GLOW_INK_SAC, 0.5f, 2)
                .build(consumer, "miners_delight:cutting/glow_squid");

        CuttingBoardRecipeBuilder.cuttingRecipe(Ingredient.of(MDItems.BAKED_SQUID.get()), Ingredient.of(ForgeTags.TOOLS_KNIVES), MDItems.BAKED_TENTACLES.get(), 3)
                .addResultWithChance(MDItems.BAKED_TENTACLES.get(), 0.5f)
                .build(consumer, "miners_delight:cutting/baked_squid");

        CuttingBoardRecipeBuilder.cuttingRecipe(Ingredient.of(MDBlocks.WILD_CAVE_CARROTS.get()), Ingredient.of(Tags.Items.SHEARS), MDBlocks.GOSSYPIUM.get(), 1)
                .addResultWithChance(MDBlocks.GOSSYPIUM.get(), 0.5F, 1)
                .addResult(MDItems.CAVE_CARROT.get())
                .addResultWithChance(MDItems.CAVE_CARROT.get(), 0.5f, 2)
                .build(consumer, "miners_delight:cutting/wild_cave_carrot");
    }
}
