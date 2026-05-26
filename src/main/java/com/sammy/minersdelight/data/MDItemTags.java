package com.sammy.minersdelight.data;

import com.sammy.minersdelight.*;
import com.sammy.minersdelight.setup.*;
import net.minecraft.core.*;
import net.minecraft.data.*;
import net.minecraft.data.tags.*;
import net.minecraft.world.item.*;
import net.minecraft.world.level.block.*;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.*;
import org.jetbrains.annotations.*;
import vectorwing.farmersdelight.common.tag.CommonTags;
import vectorwing.farmersdelight.common.tag.ModTags;

import java.util.concurrent.*;

public class MDItemTags extends ItemTagsProvider {

    public MDItemTags(PackOutput pOutput, CompletableFuture<HolderLookup.Provider> pLookupProvider, CompletableFuture<TagLookup<Block>> pBlockTags, @Nullable ExistingFileHelper existingFileHelper) {
        super(pOutput, pLookupProvider, pBlockTags, MinersDelightMod.MODID, existingFileHelper);
    }

    @Override
    public String getName() {
        return "MD Item Tags";
    }

    @Override
    protected void addTags(HolderLookup.Provider pProvider) {
        tag(MDTags.MOSS).add(Items.MOSS_BLOCK, MDItems.MOSS.get());

        // Milk
        tag(CommonTags.Items.MILK).add(MDItems.MILK_CUP.get());
        tag(CommonTags.Items.MILK_BUCKET).add(MDItems.MILK_CUP.get());


        // Crops
        tag(Tags.Items.CROPS).add(MDItems.CAVE_CARROT.get());
        tag(MDTags.CAVE_CARROTS_CROP_ITEM).add(MDItems.CAVE_CARROT.get());
        tag(MDTags.CAVE_CARROTS_VEGETABLE_ITEM).add(MDItems.CAVE_CARROT.get());

        // Baked cave carrot
        tag(MDTags.BAKED_CAVE_CARROT).add(
                MDItems.BAKED_CAVE_CARROT.get(),
                MDItems.VEGAN_PATTY.get()
        );

        // Bat wings
        tag(MDTags.BAT_WING).add(
                MDItems.BAT_WING.get(),
                MDItems.SMOKED_BAT_WING.get()
        );

        // Insects
        tag(MDTags.RAW_INSECT_MEAT).add(
                MDItems.SPIDER_LEG.get(),
                MDItems.ARTHROPOD.get()
        );
        tag(MDTags.BC_RAW_MEATS).add(
                MDItems.SPIDER_LEG.get(),
                MDItems.ARTHROPOD.get(),
                MDItems.CRUNCHY_BAR.get()
        );
        tag(MDTags.COOKED_INSECT_MEAT).add(
                MDItems.BAKED_SPIDER_LEG.get(),
                MDItems.COOKED_ARTHROPOD.get(),
                MDItems.CRUNCHY_BAR.get()
        );

        // Squids / fish
        tag(net.minecraft.tags.ItemTags.FISHES).add(
                MDItems.SQUID.get(),
                MDItems.GLOW_SQUID.get(),
                MDItems.BAKED_SQUID.get()
        );
        tag(MDTags.SQUID).add(
                MDItems.SQUID.get(),
                MDItems.GLOW_SQUID.get(),
                MDItems.BAKED_SQUID.get()
        );
        tag(MDTags.RAW_FISHES_SQUID).add(
                MDItems.SQUID.get(),
                MDItems.GLOW_SQUID.get(),
                MDItems.TENTACLES.get()
        );
        tag(MDTags.GLOW_SQUID).add(MDItems.GLOW_SQUID.get());
        tag(MDTags.COOKED_FISHES_SQUID).add(MDItems.BAKED_SQUID.get());
        tag(MDTags.TENTACLES).add(
                MDItems.TENTACLES.get(),
                MDItems.BAKED_TENTACLES.get()
        );

        tag(CommonTags.Items.RAW_FISHES).addTag(MDTags.RAW_FISHES_SQUID);

        // Meals
        tag(ModTags.Items.MEALS).add(
                MDItems.CAVE_SOUP.get(),
                MDItems.BAT_SOUP.get(),
                MDItems.INSECT_STEW.get(),
                MDItems.GLOW_INK_PASTA.get(),
                MDItems.PASTA_WITH_VEGGIEBALLS.get(),
                MDItems.VEGAN_STEAK_AND_POTATOES.get(),
                MDItems.PLATE_OF_FAKE_MEATLOAF.get(),
                MDItems.SEASONED_ARTHROPODS.get(),
                MDItems.PLATE_OF_GLAZED_ARACHNID_LIMBS.get(),
                MDItems.BOWL_OF_STUFFED_SQUID.get()
        );

        // Raw meat
        tag(CommonTags.Items.RAW_MEAT).addTag(MDTags.RAW_INSECT_MEAT);

        // Cooked meat
        tag(CommonTags.Items.COOKED_FISHES).addTag(MDTags.COOKED_FISHES_SQUID);

        // Vegetables
        tag(CommonTags.Items.VEGETABLES).add(MDItems.CAVE_CARROT.get());

        // Piglin loved
        tag(net.minecraft.tags.ItemTags.PIGLIN_LOVED).add(MDItems.GOLDEN_NUTRITIONAL_BAR.get());
    }
}
