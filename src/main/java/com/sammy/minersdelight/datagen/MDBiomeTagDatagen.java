package com.sammy.minersdelight.datagen;

import com.sammy.minersdelight.*;
import com.sammy.minersdelight.setup.*;
import net.minecraft.core.*;
import net.minecraft.data.*;
import net.minecraft.data.tags.*;
import net.neoforged.neoforge.common.*;
import net.neoforged.neoforge.common.data.*;

import javax.annotation.*;
import java.util.concurrent.*;

public class MDBiomeTagDatagen extends BiomeTagsProvider {

    public MDBiomeTagDatagen(PackOutput pOutput, CompletableFuture<HolderLookup.Provider> pProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(pOutput, pProvider, MinersDelightMod.MODID, existingFileHelper);
    }

    @SuppressWarnings("unchecked")
    @Override
    protected void addTags(HolderLookup.Provider pProvider) {
        tag(MDTags.HAS_CAVE_CARROTS).addTags(Tags.Biomes.IS_HOT, Tags.Biomes.IS_DRY, Tags.Biomes.IS_PLAINS, Tags.Biomes.IS_FOREST);
    }
}
