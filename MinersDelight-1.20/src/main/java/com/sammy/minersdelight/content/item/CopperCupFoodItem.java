package com.sammy.minersdelight.content.item;

import vectorwing.farmersdelight.common.item.ConsumableItem;

public class CopperCupFoodItem extends ConsumableItem {
    public CopperCupFoodItem(Properties properties) {
        super(properties, true, false);
    }
    public CopperCupFoodItem(Properties properties, boolean hasFoodEffectTooltip) {
        super(properties, hasFoodEffectTooltip);
    }
    public CopperCupFoodItem(Properties properties, boolean hasFoodEffectTooltip, boolean hasCustomTooltip) {
        super(properties, hasFoodEffectTooltip, hasCustomTooltip);
    }
}
