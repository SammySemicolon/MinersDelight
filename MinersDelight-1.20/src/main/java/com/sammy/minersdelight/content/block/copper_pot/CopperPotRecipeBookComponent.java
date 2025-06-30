package com.sammy.minersdelight.content.block.copper_pot;

import com.sammy.minersdelight.logic.*;
import com.sammy.minersdelight.setup.MDItems;
import net.minecraft.client.gui.screens.recipebook.RecipeBookComponent;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.*;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Recipe;
import vectorwing.farmersdelight.FarmersDelight;
import vectorwing.farmersdelight.common.crafting.CookingPotRecipe;
import vectorwing.farmersdelight.common.utility.TextUtils;

import javax.annotation.Nonnull;
import java.util.List;

public class CopperPotRecipeBookComponent extends RecipeBookComponent
{
	protected static final ResourceLocation RECIPE_BOOK_BUTTONS = new ResourceLocation(FarmersDelight.MODID, "textures/gui/recipe_book_buttons.png");

	@Override
	protected void initFilterButtonTextures() {
		this.filterButton.initTextureValues(0, 0, 28, 18, RECIPE_BOOK_BUTTONS);
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
	public void setupGhostRecipe(Recipe<?> recipe, List<Slot> slots) {
		ItemStack resultStack = recipe.getResultItem(this.minecraft.level.registryAccess());
		boolean cupServed = CupConversionReloadListener.BOWL_TO_CUP.containsKey(resultStack.getItem());
		if (cupServed) {
			Item cupItem = CupConversionReloadListener.BOWL_TO_CUP.get(resultStack.getItem());
			ItemStack cupResultStack = new ItemStack(cupItem, Math.min(resultStack.getCount()*2, cupItem.getMaxStackSize(cupItem.getDefaultInstance())));
			cupResultStack.setTag(resultStack.getTag());
			resultStack = cupResultStack;
		}
		this.ghostRecipe.setRecipe(recipe);
		if (slots.get(4).getItem().isEmpty()) {
			this.ghostRecipe.addIngredient(Ingredient.of(resultStack), (slots.get(4)).x, (slots.get(4)).y);
		}

		if (recipe instanceof CookingPotRecipe cookingRecipe) {
			ItemStack containerStack = cupServed ? MDItems.COPPER_CUP.asStack() : cookingRecipe.getOutputContainer();
			if (!containerStack.isEmpty()) {
				this.ghostRecipe.addIngredient(Ingredient.of(containerStack), (slots.get(5)).x, (slots.get(5)).y);
			}
		}

		this.placeRecipe(this.menu.getGridWidth(), this.menu.getGridHeight(), this.menu.getResultSlotIndex(), recipe, recipe.getIngredients().iterator(), 0);
	}
}
