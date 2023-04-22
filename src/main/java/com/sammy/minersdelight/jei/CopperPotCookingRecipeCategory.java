package com.sammy.minersdelight.jei;

import com.mojang.blaze3d.vertex.PoseStack;
import com.sammy.minersdelight.MinersDelightMod;
import com.sammy.minersdelight.logic.*;
import com.sammy.minersdelight.setup.MDBlocks;
import com.sammy.minersdelight.setup.MDItems;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.gui.drawable.IDrawableAnimated;
import mezz.jei.api.gui.ingredient.IRecipeSlotsView;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.minecraft.MethodsReturnNonnullByDefault;
import net.minecraft.core.NonNullList;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import vectorwing.farmersdelight.common.crafting.CookingPotRecipe;
import vectorwing.farmersdelight.common.utility.TextUtils;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.Arrays;

@ParametersAreNonnullByDefault
@MethodsReturnNonnullByDefault
public class CopperPotCookingRecipeCategory implements IRecipeCategory<CookingPotRecipe> {
	public static final RecipeType<CookingPotRecipe> COOKING = RecipeType.create("miners_delight", "cooking", CookingPotRecipe.class);

	protected final IDrawable heatIndicator;
	protected final IDrawableAnimated arrow;
	private final Component title;
	private final IDrawable background;
	private final IDrawable icon;

	public CopperPotCookingRecipeCategory(IGuiHelper helper) {
		title = TextUtils.getTranslation("jei.cooking");
		ResourceLocation backgroundImage = MinersDelightMod.path("textures/gui/copper_pot.png");
		background = helper.createDrawable(backgroundImage, 29, 16, 117, 57);
		icon = helper.createDrawableIngredient(VanillaTypes.ITEM_STACK, new ItemStack(MDBlocks.COPPER_POT.get()));
		heatIndicator = helper.createDrawable(backgroundImage, 176, 0, 17, 15);
		arrow = helper.drawableBuilder(backgroundImage, 176, 15, 29, 17)
				.buildAnimated(200, IDrawableAnimated.StartDirection.LEFT, false);
	}

	@Override
	public RecipeType<CookingPotRecipe> getRecipeType() {
		return COOKING;
	}

	@Override
	public Component getTitle() {
		return this.title;
	}

	@Override
	public IDrawable getBackground() {
		return this.background;
	}

	@Override
	public IDrawable getIcon() {
		return this.icon;
	}

	@Override
	public void setRecipe(IRecipeLayoutBuilder builder, CookingPotRecipe recipe, IFocusGroup focuses) {
		NonNullList<Ingredient> recipeIngredients = recipe.getIngredients();

		int borderSlotSize = 18;
		for (int row = 0; row < 2; ++row) {
			for (int column = 0; column < 2; ++column) {
				int inputIndex = row * 2 + column;
				if (inputIndex < recipeIngredients.size()) {
					builder.addSlot(RecipeIngredientRole.INPUT, 11+column * borderSlotSize, 2+row * borderSlotSize).addItemStacks(Arrays.asList(recipeIngredients.get(inputIndex).getItems()));
				}
			}
		}
		ItemStack resultStack = recipe.getResultItem();
		boolean cupServed = CupConversionReloadListener.BOWL_TO_CUP.containsKey(resultStack.getItem());
		ItemStack mealContainerStack = cupServed ? MDItems.COPPER_CUP.asStack() : recipe.getOutputContainer();
		if (cupServed) {
			ItemStack cupResultStack = new ItemStack(CupConversionReloadListener.BOWL_TO_CUP.get(resultStack.getItem()), resultStack.getCount());
			cupResultStack.setTag(resultStack.getTag());
			resultStack = cupResultStack;
		}
		builder.addSlot(RecipeIngredientRole.OUTPUT, 86, 12).addItemStack(resultStack);

		if (!mealContainerStack.isEmpty()) {
			//Containers must be recognized as OUTPUT for JEI's builtin transfer handler to work
			builder.addSlot(RecipeIngredientRole.OUTPUT, 54, 40).addItemStack(mealContainerStack);
		}
		builder.addSlot(RecipeIngredientRole.OUTPUT, 86, 40).addItemStack(resultStack);
	}

	@Override
	public void draw(CookingPotRecipe recipe, IRecipeSlotsView recipeSlotsView, PoseStack matrixStack, double mouseX, double mouseY) {
		arrow.draw(matrixStack, 48, 11);
		heatIndicator.draw(matrixStack, 19, 40);
	}
}