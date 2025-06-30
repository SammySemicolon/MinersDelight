package com.sammy.minersdelight.jei;

import com.sammy.minersdelight.*;
import com.sammy.minersdelight.content.block.copper_pot.*;
import com.sammy.minersdelight.setup.*;
import mezz.jei.api.*;
import mezz.jei.api.registration.*;
import net.minecraft.*;
import net.minecraft.resources.*;
import net.minecraft.world.item.*;
import vectorwing.farmersdelight.common.crafting.*;
import vectorwing.farmersdelight.integration.jei.*;

import javax.annotation.*;
import java.util.*;

@JeiPlugin
@ParametersAreNonnullByDefault
@MethodsReturnNonnullByDefault
@SuppressWarnings("unused")
public class JEIPlugin implements IModPlugin
{
	private static final ResourceLocation ID = new ResourceLocation(MinersDelightMod.MODID, "jei_plugin");

	@Override
	public void registerCategories(IRecipeCategoryRegistration registry) {
		registry.addRecipeCategories(new CopperPotCookingRecipeCategory(registry.getJeiHelpers().getGuiHelper()));
	}

	@Override
	public void registerRecipes(IRecipeRegistration registration) {
		FDRecipes modRecipes = new FDRecipes();
		List<CookingPotRecipe> copperPotRecipes = new ArrayList<>(modRecipes.getCookingPotRecipes());
		copperPotRecipes.removeIf(r -> r.getIngredients().stream().filter(i -> !i.isEmpty()).count() > 4);
		registration.addRecipes(CopperPotCookingRecipeCategory.COOKING, copperPotRecipes);
	}

	@Override
	public void registerRecipeCatalysts(IRecipeCatalystRegistration registration) {
		registration.addRecipeCatalyst(new ItemStack(MDBlocks.COPPER_POT.get()), CopperPotCookingRecipeCategory.COOKING);
	}

	@Override
	public void registerGuiHandlers(IGuiHandlerRegistration registration) {
		registration.addRecipeClickArea(CopperPotScreen.class, 78, 28, 28, 15, CopperPotCookingRecipeCategory.COOKING);
	}

	@Override
	public void registerRecipeTransferHandlers(IRecipeTransferRegistration registration) {
		registration.addRecipeTransferHandler(CopperPotMenu.class, MDMenuTypes.COPPER_POT.get(), CopperPotCookingRecipeCategory.COOKING, 0, 4, 6, 36);
	}

	@Override
	public ResourceLocation getPluginUid() {
		return ID;
	}
}
