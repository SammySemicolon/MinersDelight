package com.sammy.minersdelight.setup;

import com.sammy.minersdelight.*;
import com.sammy.minersdelight.content.block.copper_pot.*;
import com.sammy.minersdelight.content.block.sticky_basket.*;
import com.tterrag.registrate.util.entry.*;

public class MDBlockEntities {

    public static final BlockEntityEntry<CopperPotBlockEntity> COPPER_POT =
            MinersDelightMod.registrate().blockEntity("copper_pot", CopperPotBlockEntity::new).validBlocks(MDBlocks.COPPER_POT).register();

    public static final BlockEntityEntry<StickyBasketBlockEntity> STICKY_BASKET =
            MinersDelightMod.registrate().blockEntity("sticky_basket", StickyBasketBlockEntity::new).validBlocks(MDBlocks.STICKY_BASKET).register();


    public static void register() {
    }
}
