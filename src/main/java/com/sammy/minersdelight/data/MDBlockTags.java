package com.sammy.minersdelight.data;

import com.sammy.minersdelight.*;
import net.minecraft.core.*;
import net.minecraft.data.*;
import net.minecraftforge.common.data.*;
import org.jetbrains.annotations.*;

import java.util.concurrent.*;

public class MDBlockTags extends BlockTagsProvider {


    public MDBlockTags(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, MinersDelightMod.MODID, existingFileHelper);
    }


    @Override
    public String getName() {
        return "MD Block Tags";
    }

    @Override
    protected void addTags(HolderLookup.Provider pProvider) {
    }
}
