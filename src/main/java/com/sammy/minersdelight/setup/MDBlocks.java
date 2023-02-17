package com.sammy.minersdelight.setup;

import com.sammy.minersdelight.MinersDelightMod;
import com.sammy.minersdelight.content.block.CaveCarrotBlock;
import com.sammy.minersdelight.content.block.GossypiumFlowerBlock;
import com.sammy.minersdelight.content.block.StuffedSquidFeastBlock;
import com.sammy.minersdelight.content.block.WildCaveCarrotBlock;
import com.sammy.minersdelight.content.block.copper_pot.CopperPotBlock;
import com.tterrag.registrate.Registrate;
import com.tterrag.registrate.builders.BlockBuilder;
import com.tterrag.registrate.providers.loot.RegistrateBlockLootTables;
import com.tterrag.registrate.util.entry.BlockEntry;
import com.tterrag.registrate.util.nullness.NonNullBiConsumer;
import com.tterrag.registrate.util.nullness.NonNullFunction;
import net.minecraft.advancements.critereon.StatePropertiesPredicate;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.ItemNameBlockItem;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.FlowerBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.predicates.ExplosionCondition;
import net.minecraft.world.level.storage.loot.predicates.InvertedLootItemCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemBlockStatePropertyCondition;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import net.minecraftforge.client.model.generators.ConfiguredModel;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.registries.ForgeRegistries;
import org.spongepowered.asm.mixin.injection.selectors.ITargetSelector;
import vectorwing.farmersdelight.common.block.FeastBlock;
import vectorwing.farmersdelight.common.registry.ModBlocks;

import java.util.function.Function;

import static com.sammy.minersdelight.MinersDelightMod.path;

public class MDBlocks {
    public static final Registrate BLOCK_REGISTRATE = MinersDelightMod.registrate().creativeModeTab(MDItems.MinersDelightTab::get);

    public static final BlockEntry<CopperPotBlock> COPPER_POT = setupBlock("copper_pot", CopperPotBlock::new, BlockBehaviour.Properties.of(Material.METAL).strength(0.5F, 6.0F).sound(SoundType.LANTERN))
            .blockstate((ctx, p) -> {
            })
            .item().properties(p -> p.stacksTo(1)).build()
            .tag(BlockTags.MINEABLE_WITH_PICKAXE)
            .addLayer(()-> RenderType::cutout)
            .register();

    public static final BlockEntry<StuffedSquidFeastBlock> STUFFED_SQUID = setupBlock("stuffed_squid", StuffedSquidFeastBlock::new, BlockBehaviour.Properties.copy(Blocks.CAKE))
            .blockstate((ctx, p) -> {
                Function<BlockState, ModelFile> modelFunc = s -> {
                    int servings = s.getValue(StuffedSquidFeastBlock.SERVINGS);
                    String path = servings == 0 ? "block/stuffed_squid_block_leftover" : "block/stuffed_squid_block_stage" + (5-servings);
                    return p.models().getExistingFile(path(path));
                };
                p.getVariantBuilder(ctx.get()).forAllStates(s -> ConfiguredModel.builder()
                        .modelFile(modelFunc.apply(s))
                        .rotationY((((int) s.getValue(FeastBlock.FACING).toYRot() + 180))).build());
            })
            .loot(stuffedSquidTable())
            .item().defaultModel().properties(p -> p.stacksTo(1)).build()
            .tag(BlockTags.MINEABLE_WITH_AXE)
            .register();

    public static final BlockEntry<WildCaveCarrotBlock> WILD_CAVE_CARROTS = setupBlock("wild_cave_carrots", WildCaveCarrotBlock::new, BlockBehaviour.Properties.copy(Blocks.TALL_GRASS))
            .blockstate((ctx, p) -> p.getVariantBuilder(ctx.get()).forAllStates(s -> {
                String name = ctx.getId().getPath();
                ModelFile cross = p.models().withExistingParent(name, new ResourceLocation("block/cross")).texture("cross", path("block/" + name));
                return ConfiguredModel.builder().modelFile(cross).build();
            }))
            .item().model((ctx, prov) -> prov.blockSprite(ctx::getEntry)).build()
            .tag(BlockTags.SMALL_FLOWERS)
            .addLayer(()-> RenderType::cutout)
            .register();

    public static final BlockEntry<CaveCarrotBlock> CAVE_CARROTS = setupBlock("cave_carrots", CaveCarrotBlock::new, BlockBehaviour.Properties.copy(Blocks.CARROTS))
            .blockstate((ctx, p) -> p.getVariantBuilder(ctx.get()).forAllStates(s -> {
                String name = ctx.getId().getPath()  + "_" + s.getValue(CaveCarrotBlock.AGE);
                ModelFile crop = p.models().withExistingParent(name, new ResourceLocation("block/crop")).texture("crop", path("block/" + name));
                return ConfiguredModel.builder().modelFile(crop).build();
            }))
            .loot(caveCarrotTable())
            .addLayer(()-> RenderType::cutout)
            .register();

    public static final BlockEntry<Block> CAVE_CARROT_CRATE = setupBlock("cave_carrot_crate", Block::new, BlockBehaviour.Properties.of(Material.WOOD).strength(2.0F, 3.0F).sound(SoundType.WOOD))
            .blockstate((ctx, p) -> p.getVariantBuilder(ctx.get()).forAllStates(s -> {
                String name = ctx.getId().getPath();
                return ConfiguredModel.builder().modelFile(p.models().getExistingFile(path("block/"+name))).build();
            }))
            .simpleItem()
            .tag(BlockTags.MINEABLE_WITH_AXE)
            .register();

    public static final BlockEntry<GossypiumFlowerBlock> GOSSYPIUM = setupBlock("gossypium", GossypiumFlowerBlock::new, BlockBehaviour.Properties.copy(Blocks.TALL_GRASS))
            .blockstate((ctx, p) -> p.getVariantBuilder(ctx.get()).forAllStates(s -> {
                String name = ctx.getId().getPath();
                ModelFile cross = p.models().withExistingParent(name, new ResourceLocation("block/cross")).texture("cross", path("block/" + name));
                return ConfiguredModel.builder().modelFile(cross).build();
            }))
            .item().model((ctx, prov) -> prov.blockSprite(ctx::getEntry)).build()
            .addLayer(()-> RenderType::cutout)
            .register();


    public static <T extends Block> BlockBuilder<T, Registrate> setupBlock(String name, NonNullFunction<BlockBehaviour.Properties, T> factory, BlockBehaviour.Properties properties) {
        return BLOCK_REGISTRATE.block(name, factory).properties((x) -> properties);
    }

    public static <T extends StuffedSquidFeastBlock> NonNullBiConsumer<RegistrateBlockLootTables, T> stuffedSquidTable() {
        return (l, b) -> {
            LootTable.Builder builder = LootTable.lootTable();
            LootPool.Builder fullBlock = LootPool.lootPool().when(ExplosionCondition.survivesExplosion()).add(LootItem.lootTableItem(MDBlocks.STUFFED_SQUID.get().asItem())
                    .when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(b).setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(StuffedSquidFeastBlock.SERVINGS, 5)))
                    .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1))));
            LootPool.Builder bowl = LootPool.lootPool().when(ExplosionCondition.survivesExplosion()).add(LootItem.lootTableItem(Items.BOWL)
                    .when(InvertedLootItemCondition.invert(LootItemBlockStatePropertyCondition.hasBlockStateProperties(b).setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(StuffedSquidFeastBlock.SERVINGS, 5))))
                    .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1))));
            l.add(b, builder.withPool(fullBlock).withPool(bowl));
        };
    }

    public static <T extends CaveCarrotBlock> NonNullBiConsumer<RegistrateBlockLootTables, T> caveCarrotTable() {
        return (l, b) -> {
            LootTable.Builder builder = LootTable.lootTable();
            LootPool.Builder fullyGrown = LootPool.lootPool().when(ExplosionCondition.survivesExplosion()).add(LootItem.lootTableItem(MDItems.CAVE_CARROT.get())
                    .when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(b).setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(CaveCarrotBlock.AGE, 3)))
                    .apply(SetItemCountFunction.setCount(UniformGenerator.between(2, 4))));
            LootPool.Builder notFullyGrown = LootPool.lootPool().when(ExplosionCondition.survivesExplosion()).add(LootItem.lootTableItem(MDItems.CAVE_CARROT.get())
                    .when(InvertedLootItemCondition.invert(LootItemBlockStatePropertyCondition.hasBlockStateProperties(b).setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(CaveCarrotBlock.AGE, 3))))
                    .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1))));
            l.add(b, builder.withPool(fullyGrown).withPool(notFullyGrown));
        };
    }

    public static void register() {
    }
}