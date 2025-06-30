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
	private static final WidgetSprites RECIPE_BUTTON = new WidgetSprites(ResourceLocation.withDefaultNamespace("recipe_book/button"), ResourceLocation.withDefaultNamespace("recipe_book/button"));
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
			this.addRenderableWidget(new ImageButton(this.leftPos + 5, this.height / 2 - 49, 20, 18, RECIPE_BUTTON, (button) ->
			{
				this.recipeBookComponent.toggleVisibility();
				this.leftPos = this.recipeBookComponent.updateScreenPosition(this.width, this.imageWidth);
				button.setPosition(this.leftPos + 5, this.height / 2 - 49);
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
	public void render(GuiGraphics graphics, final int mouseX, final int mouseY, float partialTicks) {
		this.renderBackground(graphics, mouseX, mouseY, partialTicks);

		if (this.recipeBookComponent.isVisible() && this.widthTooNarrow) {
			this.renderBg(graphics, partialTicks, mouseX, mouseY);
			this.recipeBookComponent.render(graphics, mouseX, mouseY, partialTicks);
		} else {
			this.recipeBookComponent.render(graphics, mouseX, mouseY, partialTicks);
			super.render(graphics, mouseX, mouseY, partialTicks);
			this.recipeBookComponent.renderGhostRecipe(graphics, this.leftPos, this.topPos, false, partialTicks);
		}

		this.renderMealDisplayTooltip(graphics, mouseX, mouseY);
		this.renderHeatIndicatorTooltip(graphics, mouseX, mouseY);
		this.recipeBookComponent.renderTooltip(graphics, this.leftPos, this.topPos, mouseX, mouseY);
	}

	private void renderHeatIndicatorTooltip(GuiGraphics graphics, int mouseX, int mouseY) {
		if (this.isHovering(HEAT_ICON.x, HEAT_ICON.y, HEAT_ICON.width, HEAT_ICON.height, mouseX, mouseY)) {
			String key = "container.cooking_pot." + (this.menu.isHeated() ? "heated" : "not_heated");
			graphics.renderTooltip(this.font, TextUtils.getTranslation(key), mouseX, mouseY);
		}
	}

	protected void renderMealDisplayTooltip(GuiGraphics gui, int mouseX, int mouseY) {
		if (this.minecraft != null && this.minecraft.player != null && this.menu.getCarried().isEmpty() && this.hoveredSlot != null && this.hoveredSlot.hasItem()) {
			if (this.hoveredSlot.index == 6) {
				List<Component> tooltip = new ArrayList<>();

				ItemStack mealStack = this.hoveredSlot.getItem();
				tooltip.add(((MutableComponent) mealStack.getItem().getDescription()).withStyle(mealStack.getRarity().getStyleModifier()));

				ItemStack containerStack = this.menu.blockEntity.getContainer();
				String container = !containerStack.isEmpty() ? containerStack.getItem().getDescription().getString() : "";

				tooltip.add(TextUtils.getTranslation("container.cooking_pot.served_on", container).withStyle(ChatFormatting.GRAY));

				gui.renderComponentTooltip(font, tooltip, mouseX, mouseY);
			} else {
				gui.renderTooltip(font, this.hoveredSlot.getItem(), mouseX, mouseY);
			}
		}
	}

	@Override
	protected void renderLabels(GuiGraphics graphics, int mouseX, int mouseY) {
		super.renderLabels(graphics, mouseX, mouseY);
		graphics.drawString(font, this.playerInventoryTitle, 8, (this.imageHeight - 96 + 2), 4210752, false);
	}

	@Override
	protected void renderBg(GuiGraphics graphics, float partialTicks, int mouseX, int mouseY) {
		// Render UI background
		RenderSystem.setShaderColor(1.0f, 1.0f, 1.0f, 1.0f);
		if (this.minecraft == null)
			return;

		RenderSystem.setShaderTexture(0, BACKGROUND_TEXTURE);
		graphics.blit(BACKGROUND_TEXTURE, this.leftPos, this.topPos, 0, 0, this.imageWidth, this.imageHeight);

		// Render heat icon
		if (this.menu.isHeated()) {
			graphics.blit(BACKGROUND_TEXTURE, this.leftPos + HEAT_ICON.x, this.topPos + HEAT_ICON.y, 176, 0, HEAT_ICON.width, HEAT_ICON.height);
		}

		// Render progress arrow
		int l = this.menu.getCookProgressionScaled();
		graphics.blit(BACKGROUND_TEXTURE, this.leftPos + PROGRESS_ARROW.x, this.topPos + PROGRESS_ARROW.y, 176, 15, l + 1, PROGRESS_ARROW.height);
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

//	@Override
//	public void removed() {
//		this.recipeBookComponent.removed();
//		super.removed();
//	}

	@Override
	@Nonnull
	public RecipeBookComponent getRecipeBookComponent() {
		return this.recipeBookComponent;
	}
}