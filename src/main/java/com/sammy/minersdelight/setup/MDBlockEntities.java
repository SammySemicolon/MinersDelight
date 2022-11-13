package com.sammy.minersdelight.setup;

import com.sammy.minersdelight.MinersDelightMod;
import com.sammy.minersdelight.content.block.copper_pot.CopperPotBlockEntity;
import com.tterrag.registrate.Registrate;
import com.tterrag.registrate.util.entry.BlockEntityEntry;

public class MDBlockEntities {

    public static final BlockEntityEntry<CopperPotBlockEntity> COPPER_POT =
            MinersDelightMod.registrate().blockEntity("copper_pot", CopperPotBlockEntity::new).validBlocks(MDBlocks.COPPER_POT).register();


    public static void register() {
    }
}
