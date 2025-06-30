package com.sammy.minersdelight.setup;

import com.sammy.minersdelight.*;
import com.sammy.minersdelight.content.block.copper_pot.*;
import com.sammy.minersdelight.content.block.sticky_basket.*;
import net.minecraft.core.*;
import net.minecraft.core.registries.*;
import net.minecraft.world.level.block.entity.*;
import net.neoforged.neoforge.capabilities.*;
import net.neoforged.neoforge.registries.*;
import vectorwing.farmersdelight.common.block.entity.inventory.*;

public class MDBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister.create(Registries.BLOCK_ENTITY_TYPE, MinersDelightMod.MODID);

    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<CopperPotBlockEntity>> COPPER_POT = BLOCK_ENTITIES.register("copper_pot",
            () -> BlockEntityType.Builder.of(CopperPotBlockEntity::new, MDBlocks.COPPER_POT.get()).build(null));

    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<StickyBasketBlockEntity>> STICKY_BASKET = BLOCK_ENTITIES.register("sticky_basket",
            () -> BlockEntityType.Builder.of(StickyBasketBlockEntity::new, MDBlocks.STICKY_BASKET.get()).build(null));

    public static void registerCapabilities(RegisterCapabilitiesEvent event) {
        event.registerBlockEntity(
                Capabilities.ItemHandler.BLOCK,
                STICKY_BASKET.get(),
                (be, context) -> new BasketInvWrapper(be)
        );
        event.registerBlockEntity(
                Capabilities.ItemHandler.BLOCK,
                COPPER_POT.get(),
                (be, context) -> {
                    if (context == Direction.UP) {
                        return be.inputHandler;
                    }
                    return be.outputHandler;
                }
        );
    }
}