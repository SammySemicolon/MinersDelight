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

public class MDBlockStates extends LodestoneBlockStateProvider {

    public MDBlockStates(PackOutput output, ExistingFileHelper exFileHelper, LodestoneItemModelProvider itemModelProvider) {
        super(output, MinersDelightMod.MODID, exFileHelper, itemModelProvider);
    }

    @Nonnull
    @Override
    public String getName() {
        return "Malum BlockStates";
    }

    @Override
    protected void registerStatesAndModels() {
        Set<Supplier<? extends Block>> blocks = new HashSet<>(BLOCKS.getEntries());

        AbstractBlockStateSmith.StateSmithData data = new AbstractBlockStateSmith.StateSmithData(this, blocks::remove);

        STUFFED_SQUID.act(data,
                MDBlocks.STUFFED_SQUID);
        WILD_CAVE_CARROTS.act(data,
                MDBlocks.WILD_CAVE_CARROTS);
        CAVE_CARROTS.act(data,
                MDBlocks.CAVE_CARROTS);

        BlockStateSmithTypes.CUSTOM_MODEL.act(data, ItemModelSmithTypes.BLOCK_MODEL_ITEM, this::simpleBlock, this::predefinedModel,
                CAVE_CARROT_CRATE);
        BlockStateSmithTypes.CROSS_MODEL_BLOCK.act(data,
                GOSSYPIUM);
    }

    public static BlockStateSmith<StuffedSquidFeastBlock> STUFFED_SQUID = new BlockStateSmith<>(StuffedSquidFeastBlock.class, ItemModelSmithTypes.BLOCK_MODEL_ITEM, (block, provider) -> {
        Function<BlockState, ModelFile> modelFunc = s -> {
            int servings = s.getValue(StuffedSquidFeastBlock.SERVINGS);
            String path = servings == 0 ? "block/stuffed_squid_block_leftover" : "block/stuffed_squid_block_stage" + (5-servings);
            return provider.models().getExistingFile(path(path));
        };
        provider.getVariantBuilder(block).forAllStates(s -> ConfiguredModel.builder()
                .modelFile(modelFunc.apply(s))
                .rotationY((((int) s.getValue(FeastBlock.FACING).toYRot() + 180))).build());
    });

    public static BlockStateSmith<WildCaveCarrotBlock> WILD_CAVE_CARROTS = new BlockStateSmith<>(WildCaveCarrotBlock.class, ItemModelSmithTypes.BLOCK_MODEL_ITEM, (block, provider) -> {
        provider.getVariantBuilder(block).forAllStates(s -> {
            String name = provider.getBlockName(block);
            ModelFile cross = provider.models().withExistingParent(name, ResourceLocation.parse("block/cross")).texture("cross", path("block/" + name));
            ConfiguredModel.builder().modelFile(cross).build();
            return ConfiguredModel.builder().modelFile(cross).build();
        });
    });

    public static BlockStateSmith<CaveCarrotBlock> CAVE_CARROTS = new BlockStateSmith<>(CaveCarrotBlock.class, ItemModelSmithTypes.BLOCK_MODEL_ITEM, (block, provider) -> {
        provider.getVariantBuilder(block).forAllStates(s -> {
            String name = provider.getBlockName(block) + "_" + s.getValue(CaveCarrotBlock.AGE);
            ModelFile crop = provider.models().withExistingParent(name, ResourceLocation.parse("block/crop")).texture("crop", path("block/" + name));
            return ConfiguredModel.builder().modelFile(crop).build();
        });
    });

}