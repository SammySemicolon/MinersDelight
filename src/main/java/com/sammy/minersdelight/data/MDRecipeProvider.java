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
    public MDRecipeProvider(DataGenerator pGenerator) {
        super(pGenerator);
    }

    @Override
    public String getName() {
        return "Miner's Delight Recipe Provider";
    }

    @Override
    protected void buildCraftingRecipes(Consumer<FinishedRecipe> consumer) {
        shaped(MDBlocks.COPPER_POT.get())
                .define('X', Tags.Items.INGOTS_COPPER)
                .define('Y', Items.WOODEN_SHOVEL)
                .define('Z', Ingredient.of(Items.WATER_BUCKET, MDItems.WATER_CUP.get()))
                .pattern(" Y ").pattern("XZX").pattern("XXX")
                .unlockedBy("has_copper", has(Tags.Items.INGOTS_COPPER))
                .save(consumer, MinersDelightMod.path("copper_pot"));

        shaped(MDBlocks.STICKY_BASKET.get())
                .define('X', Tags.Items.RODS_WOODEN)
                .define('Y', Items.COBWEB)
                .pattern("X X").pattern("Y Y").pattern("XYX")
                .unlockedBy("has_cobweb", has(Items.COBWEB))
                .save(consumer, MinersDelightMod.path("sticky_basket"));

        shaped(MDItems.COPPER_CUP.get())
                .define('X', Tags.Items.INGOTS_COPPER)
                .pattern("X X").pattern(" X ")
                .unlockedBy("has_copper", has(Tags.Items.INGOTS_COPPER))
                .save(consumer, MinersDelightMod.path("copper_cup"));

        shaped(MDBlocks.CAVE_CARROT_CRATE.get())
                .define('X', MDItems.CAVE_CARROT.get())
                .pattern("XXX").pattern("XXX").pattern("XXX")
                .unlockedBy("has_cave_carrot", has(MDItems.CAVE_CARROT.get()))
                .save(consumer, MinersDelightMod.path("cave_carrot_crate"));

        shapeless(MDItems.IMPROVISED_BARBECUE_STICK.get(), 2)
                .requires(Tags.Items.RODS_WOODEN)
                .requires(Tags.Items.RODS_WOODEN)
                .requires(MDItems.BAKED_CAVE_CARROT.get())
                .requires(Items.BROWN_MUSHROOM)
                .requires(MDItems.SMOKED_BAT_WING.get())
                .requires(MDItems.SMOKED_BAT_WING.get())
                .unlockedBy("has_cave_carrot", has(MDItems.CAVE_CARROT.get()))
                .save(consumer, MinersDelightMod.path("improvised_barbecue_stick"));

        shapeless(MDItems.WEIRD_CAVIAR.get(), 1)
                .requires(Items.BOWL)
                .requires(MDItems.SILVERFISH_EGGS.get(), 3)
                .unlockedBy("has_silverfish_eggs", has(MDItems.SILVERFISH_EGGS.get()))
                .save(consumer, MinersDelightMod.path("weird_caviar"));

        shapeless(MDItems.CAVE_CARROT.get(), 9)
                .requires(MDBlocks.CAVE_CARROT_CRATE.get())
                .unlockedBy("has_cave_carrot", has(MDItems.CAVE_CARROT.get()))
                .save(consumer, MinersDelightMod.path("cave_carrots_from_crate"));

        shapeless(Items.STRING, 2)
                .requires(MDBlocks.GOSSYPIUM.get())
                .unlockedBy("has_gossypium", has(MDBlocks.GOSSYPIUM.get()))
                .save(consumer, MinersDelightMod.path("string_from_gossypium"));

        smelting(Ingredient.of(MDItems.CAVE_CARROT.get()), MDItems.BAKED_CAVE_CARROT.get(), 0.25f, 200)
                .unlockedBy("has_cave_carrot", has(MDItems.CAVE_CARROT.get()))
                .save(consumer, "baked_cave_carrot");
        smoking(Ingredient.of(MDItems.CAVE_CARROT.get()), MDItems.BAKED_CAVE_CARROT.get(), 0.25f, 100)
                .unlockedBy("has_cave_carrot", has(MDItems.CAVE_CARROT.get()))
                .save(consumer, "baked_cave_carrot_smoking");
        campfireCooking(Ingredient.of(MDItems.CAVE_CARROT.get()), MDItems.BAKED_CAVE_CARROT.get(), 0.25f, 600)
                .unlockedBy("has_cave_carrot", has(MDItems.CAVE_CARROT.get()))
                .save(consumer, "baked_cave_carrot_campfire");

        smelting(Ingredient.of(MDItems.BAT_WING.get()), MDItems.SMOKED_BAT_WING.get(), 0.25f, 200)
                .unlockedBy("has_bat_wing", has(MDItems.BAT_WING.get()))
                .save(consumer, "smoked_bat_wing");
        smoking(Ingredient.of(MDItems.BAT_WING.get()), MDItems.SMOKED_BAT_WING.get(), 0.25f, 100)
                .unlockedBy("has_bat_wing", has(MDItems.BAT_WING.get()))
                .save(consumer, "smoked_bat_wing_smoking");
        campfireCooking(Ingredient.of(MDItems.BAT_WING.get()), MDItems.SMOKED_BAT_WING.get(), 0.25f, 600)
                .unlockedBy("has_bat_wing", has(MDItems.BAT_WING.get()))
                .save(consumer, "smoked_bat_wing_campfire");

        smelting(Ingredient.of(MDItems.TENTACLES.get()), MDItems.BAKED_TENTACLES.get(), 0.25f, 200)
                .unlockedBy("has_tentacles", has(MDItems.TENTACLES.get()))
                .save(consumer, "baked_tentacles");
        smoking(Ingredient.of(MDItems.TENTACLES.get()), MDItems.BAKED_TENTACLES.get(), 0.25f, 100)
                .unlockedBy("has_tentacles", has(MDItems.TENTACLES.get()))
                .save(consumer, "baked_tentacles_smoking");
        campfireCooking(Ingredient.of(MDItems.TENTACLES.get()), MDItems.BAKED_TENTACLES.get(), 0.25f, 600)
                .unlockedBy("has_tentacles", has(MDItems.TENTACLES.get()))
                .save(consumer, "baked_tentacles_campfire");

        smelting(Ingredient.of(MDItems.SQUID.get(), MDItems.GLOW_SQUID.get()), MDItems.BAKED_SQUID.get(), 0.25f, 200)
                .unlockedBy("has_squid", has(MDItems.SQUID.get()))
                .save(consumer, "baked_squid");
        smoking(Ingredient.of(MDItems.SQUID.get(), MDItems.GLOW_SQUID.get()), MDItems.BAKED_SQUID.get(), 0.25f, 100)
                .unlockedBy("has_squid", has(MDItems.SQUID.get()))
                .save(consumer, "baked_squid_smoking");
        campfireCooking(Ingredient.of(MDItems.SQUID.get(), MDItems.GLOW_SQUID.get()), MDItems.BAKED_SQUID.get(), 0.25f, 600)
                .unlockedBy("has_squid", has(MDItems.SQUID.get()))
                .save(consumer, "baked_squid_campfire");

        CookingPotRecipeBuilder.cookingPotRecipe(MDItems.PASTA_WITH_VEGGIEBALLS.get(), 1, SLOW_COOKING, LARGE_EXP)
                .addIngredient(MDItems.CAVE_CARROT.get())
                .addIngredient(ModItems.RAW_PASTA.get())
                .addIngredient(ModItems.TOMATO_SAUCE.get())
                .unlockedByAnyIngredient(MDItems.CAVE_CARROT.get(), ModItems.RAW_PASTA.get(), ModItems.TOMATO_SAUCE.get())
                .setRecipeBookTab(CookingPotRecipeBookTab.MEALS)
                .build(consumer, "miners_delight:cooking/pasta_with_veggieballs");

        CookingPotRecipeBuilder.cookingPotRecipe(MDItems.CAVE_SOUP.get(), 1, NORMAL_COOKING, MEDIUM_EXP)
                .addIngredient(MDItems.CAVE_CARROT.get())
                .addIngredient(Items.RED_MUSHROOM)
                .addIngredient(Items.BROWN_MUSHROOM, 2)
                .unlockedByAnyIngredient(MDItems.CAVE_CARROT.get(), Items.RED_MUSHROOM, Items.BROWN_MUSHROOM)
                .setRecipeBookTab(CookingPotRecipeBookTab.MEALS)
                .build(consumer, "miners_delight:cooking/cave_soup");

        CookingPotRecipeBuilder.cookingPotRecipe(MDBlocks.STUFFED_SQUID.get(), 1, SLOW_COOKING, LARGE_EXP, Items.BOWL)
                .addIngredient(Ingredient.of(MDItems.SQUID.get(), MDItems.GLOW_SQUID.get(), MDItems.BAKED_SQUID.get()))
                .addIngredient(ModItems.RICE.get(), 2)
                .addIngredient(Ingredient.of(MDItems.CAVE_CARROT.get(), Items.CARROT))
                .addIngredient(Tags.Items.EGGS)
                .addIngredient(ModItems.ONION.get())
                .unlockedByAnyIngredient(MDItems.SQUID.get(), MDItems.GLOW_SQUID.get(), MDItems.BAKED_SQUID.get())
                .setRecipeBookTab(CookingPotRecipeBookTab.MEALS)
                .build(consumer, "miners_delight:cooking/stuffed_squid");

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


        CuttingBoardRecipeBuilder.cuttingRecipe(Ingredient.of(MDBlocks.WILD_CAVE_CARROTS.get()), Ingredient.of(Tags.Items.SHEARS), MDBlocks.GOSSYPIUM.get(), 1)
                .addResultWithChance(MDBlocks.GOSSYPIUM.get(), 0.5F, 1)
                .addResult(MDItems.CAVE_CARROT.get())
                .addResultWithChance(MDItems.CAVE_CARROT.get(), 0.5f, 2)
                .build(consumer, "miners_delight:cutting/wild_cave_carrot");
    }
}
