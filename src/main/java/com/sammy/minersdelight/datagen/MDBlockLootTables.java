package com.sammy.minersdelight.datagen;

import net.minecraft.core.*;
import net.minecraft.data.*;
import net.minecraft.data.loot.*;
import net.minecraft.resources.*;
import net.minecraft.world.flag.*;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.storage.loot.*;
import net.minecraft.world.level.storage.loot.parameters.*;
import net.neoforged.neoforge.registries.*;

import java.util.*;
import java.util.concurrent.*;
import java.util.function.*;
import java.util.stream.*;

import static com.sammy.minersdelight.setup.MDBlocks.*;
import static team.lodestar.lodestone.helpers.DataHelper.*;

public class MDBlockLootTables extends LootTableProvider {

    public MDBlockLootTables(PackOutput pOutput, CompletableFuture<HolderLookup.Provider> provider) {
        super(pOutput, Set.of(), List.of(
                new SubProviderEntry(BlocksLoot::new, LootContextParamSets.BLOCK)
        ), provider);
    }


    public static class BlocksLoot extends BlockLootSubProvider {

        protected BlocksLoot(HolderLookup.Provider provider) {
            super(Set.of(), FeatureFlags.REGISTRY.allFlags(), provider);
        }

        @Override
        protected Iterable<Block> getKnownBlocks() {
            return BLOCKS.getEntries().stream().map(Supplier::get).collect(Collectors.toList());
        }

        @Override
        protected void generate() {
            Set<DeferredHolder<Block, ? extends Block>> blocks = new HashSet<>(BLOCKS.getEntries());

            take(blocks, COPPER_POT);
            take(blocks, STICKY_BASKET);
            take(blocks, WILD_CAVE_CARROTS);
            take(blocks, STUFFED_SQUID);
            take(blocks, GLAZED_ARACHNID_LIMBS);
            takeAll(blocks, b -> true).forEach(b -> add(b.get(), createSingleItemTable(b.get().asItem())));
        }

        @Override
        public void generate(BiConsumer<ResourceKey<LootTable>, LootTable.Builder> output) {
            this.generate();
            Set<ResourceKey<LootTable>> set = new HashSet<>();

            for(Block block : getKnownBlocks()) {
                if (block.isEnabled(this.enabledFeatures)) {
                    ResourceKey<LootTable> resourcekey = block.getLootTable();
                    if (resourcekey != BuiltInLootTables.EMPTY && set.add(resourcekey)) {
                        LootTable.Builder loottable$builder = this.map.remove(resourcekey);
                        if (loottable$builder == null) {
                            continue;
                        }

                        output.accept(resourcekey, loottable$builder);
                    }
                }
            }

            if (!this.map.isEmpty()) {
                throw new IllegalStateException("Created block loot tables for non-blocks: " + this.map.keySet());
            }
        }

    }
}