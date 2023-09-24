package com.sammy.minersdelight.setup;

import net.minecraft.core.registries.*;
import net.minecraft.network.chat.*;
import net.minecraft.world.item.*;
import net.minecraftforge.registries.*;
import vectorwing.farmersdelight.*;
import vectorwing.farmersdelight.common.registry.*;

public class MDCreativeTabs
{
    public static final DeferredRegister<CreativeModeTab> CREATIVE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, FarmersDelight.MODID);

    public static final RegistryObject<CreativeModeTab> TAB_MINERS_DELIGHT = CREATIVE_TABS.register(FarmersDelight.MODID,
            () -> CreativeModeTab.builder()
                    .title(Component.translatable("itemGroup.miners_delight"))
                    .icon(() -> new ItemStack(ModBlocks.STOVE.get()))
                    .displayItems((parameters, output) -> ModItems.CREATIVE_TAB_ITEMS.forEach((item) -> output.accept(item.get())))
                    .build());
}