package com.sammy.minersdelight.setup;

import com.sammy.minersdelight.*;
import net.minecraft.core.registries.*;
import net.minecraft.network.chat.*;
import net.minecraft.world.item.*;
import net.minecraftforge.registries.*;

public class MDCreativeTabs
{
    public static final DeferredRegister<CreativeModeTab> CREATIVE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, MinersDelightMod.MODID);

    public static final RegistryObject<CreativeModeTab> TAB_MINERS_DELIGHT = CREATIVE_TABS.register(MinersDelightMod.MODID,
            () -> CreativeModeTab.builder()
                    .title(Component.translatable("itemGroup.miners_delight"))
                    .icon(() -> new ItemStack(MDBlocks.COPPER_POT.get()))
                    .build());
}