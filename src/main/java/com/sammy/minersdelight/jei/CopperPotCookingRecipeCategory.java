package com.sammy.minersdelight.jei;

import com.sammy.minersdelight.*;
import com.sammy.minersdelight.logic.*;
import com.sammy.minersdelight.setup.*;
import mezz.jei.api.constants.*;
import mezz.jei.api.gui.builder.*;
import mezz.jei.api.gui.drawable.*;
import mezz.jei.api.gui.ingredient.*;
import mezz.jei.api.helpers.*;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.recipe.*;
import mezz.jei.api.recipe.category.*;
import net.minecraft.*;
import net.minecraft.client.*;
import net.minecraft.client.gui.*;
import net.minecraft.core.*;
import net.minecraft.network.chat.*;
import net.minecraft.resources.*;
import net.minecraft.world.item.*;
import net.minecraft.world.item.crafting.*;
import vectorwing.farmersdelight.common.crafting.*;
import vectorwing.farmersdelight.common.utility.*;

import javax.annotation.*;
import java.util.*;

@ParametersAreNonnullByDefault
@MethodsReturnNonnullByDefault
public class CopperPotCookingRecipeCategory implements IRecipeCategory<CookingPotRecipe> {
	public static final RecipeType<CookingPotRecipe> COOKING = RecipeType.create("miners_delight", "cooking", CookingPotRecipe.class);

	protected final IDrawable heatIndicator;
	protected final IDrawable timeIcon;
	protected final IDrawable expIcon;
	protected final IDrawableAnimated arrow;
	private final Component title;
	private final IDrawable background;
	private final IDrawable icon;

	public CopperPotCookingRecipeCategory(IGuiHelper helper) {
		title = TextUtils.getTranslation("jei.cooking");
		ResourceLocation backgroundImage = MinersDelightMod.path("textures/gui/copper_pot.png");
		ResourceLocation fdBackgroundImage = new ResourceLocation("farmersdelight", "textures/gui/cooking_pot.png");
		background = helper.createDrawable(backgroundImage, 29, 16, 117, 57);
		icon = helper.createDrawableIngredient(VanillaTypes.ITEM_STACK, new ItemStack(MDBlocks.COPPER_POT.get()));
		heatIndicator = helper.createDrawable(backgroundImage, 176, 0, 17, 15);
		timeIcon = helper.createDrawable(fdBackgroundImage, 176, 32, 8, 11);
		expIcon = helper.createDrawable(fdBackgroundImage, 176, 43, 9, 9);
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

		ItemStack resultStack = recipe.getResultItem(Minecraft.getInstance().level.registryAccess());
		boolean cupServed = CupConversionReloadListener.BOWL_TO_CUP.containsKey(resultStack.getItem());
		ItemStack mealContainerStack = cupServed ? MDItems.COPPER_CUP.asStack() : recipe.getOutputContainer();
		if (cupServed) {
			Item cupItem = CupConversionReloadListener.BOWL_TO_CUP.get(resultStack.getItem());
			ItemStack cupResultStack = new ItemStack(cupItem, Math.min(resultStack.getCount()*2, cupItem.getMaxStackSize(cupItem.getDefaultInstance())));
			cupResultStack.setTag(resultStack.getTag());
			resultStack = cupResultStack;
		}
		builder.addSlot(RecipeIngredientRole.OUTPUT, 86, 12).addItemStack(resultStack);

		if (!mealContainerStack.isEmpty()) {
			builder.addSlot(RecipeIngredientRole.OUTPUT, 54, 40).addItemStack(mealContainerStack);
		}
		builder.addSlot(RecipeIngredientRole.OUTPUT, 86, 40).addItemStack(resultStack);
	}

	@Override
	public void draw(CookingPotRecipe recipe, IRecipeSlotsView recipeSlotsView, GuiGraphics guiGraphics, double mouseX, double mouseY) {
		arrow.draw(guiGraphics, 48, 11);
		heatIndicator.draw(guiGraphics, 19, 40);
		timeIcon.draw(guiGraphics, 58, 4);
		if (recipe.getExperience() > 0.0F) {
			expIcon.draw(guiGraphics, 57, 23);
		}
	}
}