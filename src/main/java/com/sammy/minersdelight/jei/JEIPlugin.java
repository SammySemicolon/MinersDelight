package com.sammy.minersdelight.jei;

import com.sammy.minersdelight.*;
import com.sammy.minersdelight.content.block.copper_pot.*;
import com.sammy.minersdelight.setup.*;
import mezz.jei.api.*;
import mezz.jei.api.helpers.*;
import mezz.jei.api.recipe.*;
import mezz.jei.api.registration.*;
import net.minecraft.client.*;
import net.minecraft.client.multiplayer.*;
import net.minecraft.resources.*;
import team.lodestar.lodestone.systems.recipe.*;
import vectorwing.farmersdelight.common.crafting.*;
import vectorwing.farmersdelight.common.registry.*;

import javax.annotation.*;
import java.util.*;

@JeiPlugin
@SuppressWarnings("unused")
public class JEIPlugin implements IModPlugin
{
	private static final ResourceLocation ID = MinersDelightMod.path("jei_plugin");
	private static final Minecraft MC = Minecraft.getInstance();

	public static final RecipeType<CookingPotRecipe> COPPER_POT_COOKING = new RecipeType<>(CopperPotCookingRecipeCategory.UID, CookingPotRecipe.class);

	@Override
	public void registerCategories(IRecipeCategoryRegistration registry) {
		IGuiHelper guiHelper = registry.getJeiHelpers().getGuiHelper();
		registry.addRecipeCategories(new CopperPotCookingRecipeCategory(guiHelper));
	}

	@Override
	public void registerRecipes(@Nonnull IRecipeRegistration registry) {
		ClientLevel level = Minecraft.getInstance().level;
		if (level != null) {
			List<CookingPotRecipe> cooking = LodestoneRecipeType.getRecipes(level, ModRecipeTypes.COOKING.get());
			cooking.removeIf(r -> r.getIngredients().stream().filter(i -> !i.isEmpty()).count() > 4);
			registry.addRecipes(COPPER_POT_COOKING, cooking);
		}
	}

	@Override
	public void registerRecipeCatalysts(IRecipeCatalystRegistration registry) {
		registry.addRecipeCatalyst(MDItems.COPPER_POT.get().getDefaultInstance(), COPPER_POT_COOKING);
	}

	@Override
	public void registerGuiHandlers(IGuiHandlerRegistration registration) {
		registration.addRecipeClickArea(CopperPotScreen.class, 78, 28, 28, 15, COPPER_POT_COOKING);
	}

	@Override
	public void registerRecipeTransferHandlers(IRecipeTransferRegistration registration) {
		registration.addRecipeTransferHandler(CopperPotMenu.class, MDMenuTypes.COPPER_POT.get(), COPPER_POT_COOKING, 0, 4, 7, 36);
	}

	@Override
	public ResourceLocation getPluginUid() {
		return ID;
	}
}
