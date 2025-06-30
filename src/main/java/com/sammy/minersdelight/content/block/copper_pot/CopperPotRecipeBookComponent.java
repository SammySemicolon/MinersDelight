package com.sammy.minersdelight.content.block.copper_pot;

import com.sammy.minersdelight.content.data.*;
import net.minecraft.client.gui.components.*;
import net.minecraft.client.gui.screens.recipebook.*;
import net.minecraft.network.chat.*;
import net.minecraft.resources.*;
import net.minecraft.world.inventory.*;
import net.minecraft.world.item.*;
import net.minecraft.world.item.crafting.*;
import vectorwing.farmersdelight.*;
import vectorwing.farmersdelight.common.crafting.*;
import vectorwing.farmersdelight.common.utility.*;

import javax.annotation.*;
import java.util.*;

public class CopperPotRecipeBookComponent extends RecipeBookComponent
{
	protected static final WidgetSprites RECIPE_BOOK_BUTTONS = new WidgetSprites(
			ResourceLocation.fromNamespaceAndPath(FarmersDelight.MODID, "recipe_book/cooking_pot_enabled"),
			ResourceLocation.fromNamespaceAndPath(FarmersDelight.MODID, "recipe_book/cooking_pot_disabled"),
			ResourceLocation.fromNamespaceAndPath(FarmersDelight.MODID, "recipe_book/cooking_pot_enabled_highlighted"),
			ResourceLocation.fromNamespaceAndPath(FarmersDelight.MODID, "recipe_book/cooking_pot_disabled_highlighted"));

	@Override
	protected void initFilterButtonTextures() {
		this.filterButton.initTextureValues(RECIPE_BOOK_BUTTONS);
	}

	public void hide() {
		this.setVisible(false);
	}

	@Override
	@Nonnull
	protected Component getRecipeFilterName() {
		return TextUtils.getTranslation("container.recipe_book.cookable");
	}

	@Override
	public void setupGhostRecipe(RecipeHolder<?> recipe, List<Slot> slots) {
		ItemStack resultStack = recipe.value().getResultItem(this.minecraft.level.registryAccess());
		Optional<ItemStack> cupVariant = CupConversionDataMap.getCupVariant(resultStack);
		if (cupVariant.isPresent()) {
			resultStack = cupVariant.get();
		}
		this.ghostRecipe.setRecipe(recipe);
		if (slots.get(6).getItem().isEmpty()) {
			this.ghostRecipe.addIngredient(Ingredient.of(resultStack), (slots.get(6)).x, (slots.get(6)).y);
		}

		if (recipe.value() instanceof CookingPotRecipe cookingRecipe) {
			ItemStack containerStack = cookingRecipe.getOutputContainer();
			if (!containerStack.isEmpty()) {
				this.ghostRecipe.addIngredient(Ingredient.of(containerStack), (slots.get(7)).x, (slots.get(7)).y);
			}
		}

		this.placeRecipe(this.menu.getGridWidth(), this.menu.getGridHeight(), this.menu.getResultSlotIndex(), recipe, recipe.value().getIngredients().iterator(), 0);
	}
}