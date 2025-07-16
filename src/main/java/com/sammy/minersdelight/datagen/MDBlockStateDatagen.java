package com.sammy.minersdelight.datagen;

import com.sammy.minersdelight.*;
import com.sammy.minersdelight.content.block.*;
import com.sammy.minersdelight.setup.*;
import net.minecraft.data.*;
import net.minecraft.resources.*;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.*;
import net.neoforged.neoforge.client.model.generators.*;
import net.neoforged.neoforge.common.data.*;
import team.lodestar.lodestone.systems.datagen.*;
import team.lodestar.lodestone.systems.datagen.providers.*;
import team.lodestar.lodestone.systems.datagen.statesmith.*;
import vectorwing.farmersdelight.common.block.*;

import javax.annotation.*;
import java.util.*;
import java.util.function.*;

import static com.sammy.minersdelight.MinersDelightMod.*;
import static com.sammy.minersdelight.setup.MDBlocks.*;

public class MDBlockStateDatagen extends LodestoneBlockStateProvider {

    public MDBlockStateDatagen(PackOutput output, ExistingFileHelper exFileHelper, LodestoneItemModelProvider itemModelProvider) {
        super(output, MinersDelightMod.MODID, exFileHelper, itemModelProvider);
    }

    @Nonnull
    @Override
    public String getName() {
        return "Miner's Delight BlockStates";
    }

    @Override
    protected void registerStatesAndModels() {
        Set<Supplier<? extends Block>> blocks = new HashSet<>(BLOCKS.getEntries());

        AbstractBlockStateSmith.StateSmithData data = new AbstractBlockStateSmith.StateSmithData(this, blocks::remove);

        STUFFED_SQUID.act(data, MDBlocks.STUFFED_SQUID);
        GLAZED_ARACHNID_LIMBS.act(data, MDBlocks.GLAZED_ARACHNID_LIMBS);
        WILD_CROP_BLOCK.act(data, MDBlocks.WILD_CAVE_CARROTS, MDBlocks.GOSSYPIUM);
        CAVE_CARROTS.act(data, MDBlocks.CAVE_CARROTS);

        BlockStateSmithTypes.POTTED_PLANT.act(data, MDBlocks.POTTED_GOSSYPIUM);

        BlockStateSmithTypes.CUSTOM_MODEL.act(data, ItemModelSmithTypes.BLOCK_MODEL_ITEM, this::simpleBlock, this::predefinedModel,
                CAVE_CARROT_CRATE);
    }

    public static BlockStateSmith<StuffedSquidFeastBlock> STUFFED_SQUID = new BlockStateSmith<>(StuffedSquidFeastBlock.class, ItemModelSmithTypes.GENERATED_ITEM, (block, provider) -> {
        Function<BlockState, ModelFile> modelFunc = s -> {
            int servings = s.getValue(StuffedSquidFeastBlock.SERVINGS);
            String path = servings == 0 ? "block/stuffed_squid_block_leftover" : "block/stuffed_squid_block_stage" + (5-servings);
            return provider.models().getExistingFile(path(path));
        };
        provider.getVariantBuilder(block).forAllStates(s -> ConfiguredModel.builder()
                .modelFile(modelFunc.apply(s))
                .rotationY((((int) s.getValue(FeastBlock.FACING).toYRot() + 180))).build());
    });

    public static BlockStateSmith<GlazedArachnidLimbsFeastBlock> GLAZED_ARACHNID_LIMBS = new BlockStateSmith<>(GlazedArachnidLimbsFeastBlock.class, ItemModelSmithTypes.GENERATED_ITEM, (block, provider) -> {
        Function<BlockState, ModelFile> modelFunc = s -> {
            int servings = s.getValue(GlazedArachnidLimbsFeastBlock.SERVINGS);
            String path = servings == 0 ? "block/glazed_arachnid_limbs_block_leftover" : "block/glazed_arachnid_limbs_block_stage" + (4-servings);
            return provider.models().getExistingFile(path(path));
        };
        provider.getVariantBuilder(block).forAllStates(s -> ConfiguredModel.builder()
                .modelFile(modelFunc.apply(s))
                .rotationY((((int) s.getValue(FeastBlock.FACING).toYRot() + 180))).build());
    });

    public static BlockStateSmith<FlowerBlock> WILD_CROP_BLOCK = new BlockStateSmith<>(FlowerBlock.class, ItemModelSmithTypes.BLOCK_TEXTURE_ITEM, (block, provider) -> {
        provider.getVariantBuilder(block).forAllStates(s -> {
            String name = provider.getBlockName(block);
            if (s.getValue(WildCaveCarrotBlock.STONE)) {
                name = "stone_" + name;
            }
            ModelFile cross = provider.models().withExistingParent(name, ResourceLocation.parse("block/cross")).texture("cross", path("block/" + name));
            ConfiguredModel.builder().modelFile(cross).build();
            return ConfiguredModel.builder().modelFile(cross).build();
        });
    });

    public static BlockStateSmith<CaveCarrotBlock> CAVE_CARROTS = new BlockStateSmith<>(CaveCarrotBlock.class, ItemModelSmithTypes.GENERATED_ITEM, (block, provider) -> {
        provider.getVariantBuilder(block).forAllStates(s -> {
            String name = provider.getBlockName(block) + "_" + s.getValue(CaveCarrotBlock.AGE);
            ModelFile crop = provider.models().withExistingParent(name, ResourceLocation.parse("block/crop")).texture("crop", path("block/" + name));
            return ConfiguredModel.builder().modelFile(crop).build();
        });
    });

}