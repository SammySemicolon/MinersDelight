package com.sammy.minersdelight.data;

import com.sammy.minersdelight.setup.*;
import net.minecraft.data.*;
import net.minecraft.data.recipes.*;
import net.minecraft.data.recipes.packs.*;
import net.minecraft.resources.*;
import net.minecraft.tags.*;
import net.minecraft.world.item.*;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.*;
import net.minecraftforge.registries.*;

import java.lang.reflect.*;
import java.util.*;
import java.util.function.*;


public class MDVanillaRecipeReplacements extends VanillaRecipeProvider {

    private final Map<Item, TagKey<Item>> replacements = new HashMap<>();
    private final Set<ResourceLocation> excludes = new HashSet<>();

    public MDVanillaRecipeReplacements(PackOutput pOutput) {
        super(pOutput);
    }

    @Override
    protected void buildRecipes(Consumer<FinishedRecipe> pWriter) {
        replace(Items.MOSS_BLOCK, MDTags.MOSS);

        super.buildRecipes(vanilla -> {
            FinishedRecipe modified = enhance(vanilla);
            if (modified != null)
                pWriter.accept(modified);
        });
    }

    private void exclude(ItemLike item) {
        excludes.add(ForgeRegistries.ITEMS.getKey(item.asItem()));
    }

    private void replace(ItemLike item, TagKey<Item> tag) {
        replacements.put(item.asItem(), tag);
    }

    private FinishedRecipe enhance(FinishedRecipe vanilla) {
        if (vanilla instanceof ShapelessRecipeBuilder.Result)
            return enhance((ShapelessRecipeBuilder.Result) vanilla);
        if (vanilla instanceof ShapedRecipeBuilder.Result)
            return enhance((ShapedRecipeBuilder.Result) vanilla);
        return null;
    }

    private FinishedRecipe enhance(ShapelessRecipeBuilder.Result vanilla) {
        List<Ingredient> ingredients = getField(ShapelessRecipeBuilder.Result.class, vanilla, 4);
        boolean modified = false;
        for (int x = 0; x < ingredients.size(); x++) {
            Ingredient ing = enhance(vanilla.getId(), ingredients.get(x));
            if (ing != null) {
                ingredients.set(x, ing);
                modified = true;
            }
        }
        return modified ? vanilla : null;
    }

    private FinishedRecipe enhance(ShapedRecipeBuilder.Result vanilla) {
        Map<Character, Ingredient> ingredients = getField(ShapedRecipeBuilder.Result.class, vanilla, 5);
        boolean modified = false;
        for (Character x : ingredients.keySet()) {
            Ingredient ing = enhance(vanilla.getId(), ingredients.get(x));
            if (ing != null) {
                ingredients.put(x, ing);
                modified = true;
            }
        }
        return modified ? vanilla : null;
    }

    private Ingredient enhance(ResourceLocation name, Ingredient vanilla) {
        if (excludes.contains(name))
            return null;

        boolean modified = false;
        List<Ingredient.Value> items = new ArrayList<>();
        Ingredient.Value[] vanillaItems = getField(Ingredient.class, vanilla, 2); //This will probably crash between versions, if null fix index
        for (Ingredient.Value entry : vanillaItems) {
            if (entry instanceof Ingredient.ItemValue) {
                ItemStack stack = entry.getItems().stream().findFirst().orElse(ItemStack.EMPTY);
                TagKey<Item> replacement = replacements.get(stack.getItem());
                if (replacement != null) {
                    items.add(new Ingredient.TagValue(replacement));
                    modified = true;
                } else
                    items.add(entry);
            } else
                items.add(entry);
        }
        return modified ? Ingredient.fromValues(items.stream()) : null;
    }

    @SuppressWarnings("unchecked")
    private <T, R> R getField(Class<T> clz, T inst, int index) {
        Field fld = clz.getDeclaredFields()[index];
        fld.setAccessible(true);
        try {
            return (R) fld.get(inst);
        } catch (IllegalArgumentException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
}
