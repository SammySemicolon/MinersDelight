package com.sammy.minersdelight.content.block.copper_pot;

import com.mojang.blaze3d.systems.*;
import com.sammy.minersdelight.*;
import net.minecraft.*;
import net.minecraft.client.gui.*;
import net.minecraft.client.gui.components.*;
import net.minecraft.client.gui.screens.inventory.*;
import net.minecraft.client.gui.screens.recipebook.*;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.*;
import net.minecraft.resources.*;
import net.minecraft.world.entity.player.*;
import net.minecraft.world.inventory.*;
import net.minecraft.world.item.*;
import vectorwing.farmersdelight.common.*;
import vectorwing.farmersdelight.common.utility.*;

import javax.annotation.*;
import java.awt.*;
import java.util.List;
import java.util.*;

@ParametersAreNonnullByDefault
public class CopperPotScreen extends AbstractContainerScreen<CopperPotMenu> implements RecipeUpdateListener {
	private static final ResourceLocation RECIPE_BUTTON_LOCATION = new ResourceLocation("textures/gui/recipe_button.png");
	private static final ResourceLocation BACKGROUND_TEXTURE = MinersDelightMod.path("textures/gui/copper_pot.png");
	private static final Rectangle HEAT_ICON = new Rectangle(49, 57, 17, 15);
	private static final Rectangle PROGRESS_ARROW = new Rectangle(78, 27, 0, 17);

	private final CopperPotRecipeBookComponent recipeBookComponent = new CopperPotRecipeBookComponent();
	private boolean widthTooNarrow;

	public CopperPotScreen(CopperPotMenu screenContainer, Inventory inv, Component titleIn) {
		super(screenContainer, inv, titleIn);
	}

	@Override
	public void init() {
		super.init();
		this.widthTooNarrow = this.width < 379;
		this.titleLabelX = 28;
		this.recipeBookComponent.init(this.width, this.height, this.minecraft, this.widthTooNarrow, this.menu);
		this.leftPos = this.recipeBookComponent.updateScreenPosition(this.width, this.imageWidth);
		if (Configuration.ENABLE_RECIPE_BOOK_COOKING_POT.get()) {
			this.addRenderableWidget(new ImageButton(this.leftPos + 5, this.height / 2 - 49, 20, 18, 0, 0, 19, RECIPE_BUTTON_LOCATION, (button) ->
			{
				this.recipeBookComponent.toggleVisibility();
				this.leftPos = this.recipeBookComponent.updateScreenPosition(this.width, this.imageWidth);
				((ImageButton) button).setPosition(this.leftPos + 5, this.height / 2 - 49);
			}));
		} else {
			this.recipeBookComponent.hide();
			this.leftPos = this.recipeBookComponent.updateScreenPosition(this.width, this.imageWidth);
		}
		this.addWidget(this.recipeBookComponent);
		this.setInitialFocus(this.recipeBookComponent);
	}

	@Override
	protected void containerTick() {
		super.containerTick();
		this.recipeBookComponent.tick();
	}

	@Override
	public void render(GuiGraphics guiGraphics, final int mouseX, final int mouseY, float partialTicks) {
		this.renderBackground(guiGraphics);

		if (this.recipeBookComponent.isVisible() && this.widthTooNarrow) {
			this.renderBg(guiGraphics, partialTicks, mouseX, mouseY);
			this.recipeBookComponent.render(guiGraphics, mouseX, mouseY, partialTicks);
		} else {
			this.recipeBookComponent.render(guiGraphics, mouseX, mouseY, partialTicks);
			super.render(guiGraphics, mouseX, mouseY, partialTicks);
			this.recipeBookComponent.renderGhostRecipe(guiGraphics, this.leftPos, this.topPos, false, partialTicks);
		}

		this.renderMealDisplayTooltip(guiGraphics, mouseX, mouseY);
		this.renderHeatIndicatorTooltip(guiGraphics, mouseX, mouseY);
		this.recipeBookComponent.renderTooltip(guiGraphics, this.leftPos, this.topPos, mouseX, mouseY);
	}

	private void renderHeatIndicatorTooltip(GuiGraphics gui, int mouseX, int mouseY) {
		if (this.isHovering(HEAT_ICON.x, HEAT_ICON.y, HEAT_ICON.width, HEAT_ICON.height, mouseX, mouseY)) {
			String key = "container.cooking_pot." + (this.menu.isHeated() ? "heated" : "not_heated");
			gui.renderTooltip(this.font, TextUtils.getTranslation(key, this.menu), mouseX, mouseY);
		}

	}

	protected void renderMealDisplayTooltip(GuiGraphics gui, int mouseX, int mouseY) {
		if (this.minecraft != null && this.minecraft.player != null && (this.menu).getCarried().isEmpty() && this.hoveredSlot != null && this.hoveredSlot.hasItem()) {
			if (this.hoveredSlot.index == 6) {
				List<Component> tooltip = new ArrayList();
				ItemStack mealStack = this.hoveredSlot.getItem();
				tooltip.add(((MutableComponent)mealStack.getItem().getDescription()).withStyle(mealStack.getRarity().color));
				ItemStack containerStack = this.menu.blockEntity.getContainer();
				String container = !containerStack.isEmpty() ? containerStack.getItem().getDescription().getString() : "";
				tooltip.add(TextUtils.getTranslation("container.cooking_pot.served_on", new Object[]{container}).withStyle(ChatFormatting.GRAY));
				gui.renderComponentTooltip(this.font, tooltip, mouseX, mouseY);
			} else {
				gui.renderTooltip(this.font, this.hoveredSlot.getItem(), mouseX, mouseY);
			}
		}

	}

	@Override
	protected void renderLabels(GuiGraphics gui, int mouseX, int mouseY) {
		super.renderLabels(gui, mouseX, mouseY);
		gui.drawString(this.font, this.playerInventoryTitle, 8, this.imageHeight - 96 + 2, 4210752, false);
	}

	@Override
	protected void renderBg(GuiGraphics gui, float partialTicks, int mouseX, int mouseY) {
		RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
		if (minecraft != null) {
			gui.blit(BACKGROUND_TEXTURE, leftPos, topPos, 0, 0, imageWidth, imageHeight);
			if (menu.isHeated()) {
				gui.blit(BACKGROUND_TEXTURE, leftPos + HEAT_ICON.x, topPos + HEAT_ICON.y, 176, 0, HEAT_ICON.width, HEAT_ICON.height);
			}

			int l = menu.getCookProgressionScaled();
			gui.blit(BACKGROUND_TEXTURE, leftPos + PROGRESS_ARROW.x, topPos + PROGRESS_ARROW.y, 176, 15, l + 1, PROGRESS_ARROW.height);
		}
	}

	@Override
	protected boolean isHovering(int x, int y, int width, int height, double mouseX, double mouseY) {
		return (!this.widthTooNarrow || !this.recipeBookComponent.isVisible()) && super.isHovering(x, y, width, height, mouseX, mouseY);
	}

	@Override
	public boolean mouseClicked(double mouseX, double mouseY, int buttonId) {
		if (this.recipeBookComponent.mouseClicked(mouseX, mouseY, buttonId)) {
			this.setFocused(this.recipeBookComponent);
			return true;
		}
		return this.widthTooNarrow && this.recipeBookComponent.isVisible() || super.mouseClicked(mouseX, mouseY, buttonId);
	}

	@Override
	protected boolean hasClickedOutside(double mouseX, double mouseY, int x, int y, int buttonIdx) {
		boolean flag = mouseX < (double) x || mouseY < (double) y || mouseX >= (double) (x + this.imageWidth) || mouseY >= (double) (y + this.imageHeight);
		return flag && this.recipeBookComponent.hasClickedOutside(mouseX, mouseY, this.leftPos, this.topPos, this.imageWidth, this.imageHeight, buttonIdx);
	}

	@Override
	protected void slotClicked(Slot slot, int mouseX, int mouseY, ClickType clickType) {
		super.slotClicked(slot, mouseX, mouseY, clickType);
		this.recipeBookComponent.slotClicked(slot);
	}

	@Override
	public void recipesUpdated() {
		this.recipeBookComponent.recipesUpdated();
	}

	@Override
	@Nonnull
	public RecipeBookComponent getRecipeBookComponent() {
		return this.recipeBookComponent;
	}
}