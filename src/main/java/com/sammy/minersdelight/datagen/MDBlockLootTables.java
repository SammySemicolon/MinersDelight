package com.sammy.minersdelight.datagen;

import com.sammy.minersdelight.content.block.CaveCarrotBlock;
import com.sammy.minersdelight.content.block.StuffedSquidFeastBlock;
import com.sammy.minersdelight.setup.MDBlocks;
import com.sammy.minersdelight.setup.MDItems;
import net.minecraft.advancements.critereon.StatePropertiesPredicate;
import net.minecraft.core.*;
import net.minecraft.data.*;
import net.minecraft.data.loot.*;
import net.minecraft.resources.*;
import net.minecraft.world.flag.*;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.storage.loot.*;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.parameters.*;
import net.minecraft.world.level.storage.loot.predicates.ExplosionCondition;
import net.minecraft.world.level.storage.loot.predicates.InvertedLootItemCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemBlockStatePropertyCondition;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import net.neoforged.neoforge.registries.*;
import vectorwing.farmersdelight.common.block.FeastBlock;

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

            createFeastTable(take(blocks, FAKE_MEATLOAF).get());
            createFeastTable(take(blocks, GLAZED_ARACHNID_LIMBS).get());
            createStuffedSquidTable(take(blocks, STUFFED_SQUID).get());
            createCaveCarrotCropTable(take(blocks, CAVE_CARROTS).get());

            takeAll(blocks, b -> b.get() instanceof FlowerPotBlock).forEach(b -> add(b.get(), createPotFlowerItemTable(((FlowerPotBlock) b.get()).getPotted())));

            takeAll(blocks, b -> true).forEach(b -> add(b.get(), createSingleItemTable(b.get().asItem())));
        }


        protected void createStuffedSquidTable(Block block) {
            LootTable.Builder builder = LootTable.lootTable();
            LootPool.Builder fullBlock = LootPool.lootPool().when(ExplosionCondition.survivesExplosion())
                    .add(LootItem.lootTableItem(MDBlocks.STUFFED_SQUID.get().asItem())
                            .when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(block)
                                    .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(StuffedSquidFeastBlock.SERVINGS, 5)))
                            .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1))));
            LootPool.Builder bowl = LootPool.lootPool().when(ExplosionCondition.survivesExplosion())
                    .add(LootItem.lootTableItem(Items.BOWL)
                            .when(InvertedLootItemCondition.invert(LootItemBlockStatePropertyCondition.hasBlockStateProperties(block)
                                    .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(StuffedSquidFeastBlock.SERVINGS, 5))))
                            .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1))));
            add(block, builder.withPool(fullBlock).withPool(bowl));
        }

        protected void createFeastTable(Block block) {
            LootTable.Builder builder = LootTable.lootTable();
            LootPool.Builder fullBlock = LootPool.lootPool().when(ExplosionCondition.survivesExplosion())
                    .add(LootItem.lootTableItem(block.asItem())
                            .when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(block)
                                    .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(FeastBlock.SERVINGS, 4)))
                            .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1))));
            LootPool.Builder bowl = LootPool.lootPool().when(ExplosionCondition.survivesExplosion())
                    .add(LootItem.lootTableItem(Items.BOWL)
                            .when(InvertedLootItemCondition.invert(LootItemBlockStatePropertyCondition.hasBlockStateProperties(block)
                                    .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(FeastBlock.SERVINGS, 4))))
                            .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1))));
            add(block, builder.withPool(fullBlock).withPool(bowl));
        }

        protected void createCaveCarrotCropTable(Block block) {
            LootTable.Builder builder = LootTable.lootTable();
            LootPool.Builder fullyGrown = LootPool.lootPool().when(ExplosionCondition.survivesExplosion())
                    .add(LootItem.lootTableItem(MDItems.CAVE_CARROT.get())
                            .when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(block)
                                    .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(CaveCarrotBlock.AGE, 3)))
                            .apply(SetItemCountFunction.setCount(UniformGenerator.between(2, 4))));
            LootPool.Builder notFullyGrown = LootPool.lootPool().when(ExplosionCondition.survivesExplosion())
                    .add(LootItem.lootTableItem(MDItems.CAVE_CARROT.get())
                            .when(InvertedLootItemCondition.invert(LootItemBlockStatePropertyCondition.hasBlockStateProperties(block)
                                    .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(CaveCarrotBlock.AGE, 3))))
                            .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1))));
            add(block, builder.withPool(fullyGrown).withPool(notFullyGrown));
        }

        //This removes the error that's thrown if some blocks lack a loot table generated via datagen
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