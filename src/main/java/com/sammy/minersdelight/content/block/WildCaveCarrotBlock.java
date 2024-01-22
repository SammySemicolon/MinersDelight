package com.sammy.minersdelight.content.block;

import com.sammy.minersdelight.setup.*;
import net.minecraft.core.*;
import net.minecraft.server.level.*;
import net.minecraft.tags.*;
import net.minecraft.util.*;
import net.minecraft.world.effect.*;
import net.minecraft.world.item.context.*;
import net.minecraft.world.level.*;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.*;
import net.minecraft.world.level.block.state.properties.*;
import net.minecraftforge.data.loading.*;
import org.jetbrains.annotations.*;
import vectorwing.farmersdelight.common.block.*;
import vectorwing.farmersdelight.common.registry.*;

public class WildCaveCarrotBlock extends WildCropBlock {

    public static final BooleanProperty STONE = BooleanProperty.create("stone");

    public WildCaveCarrotBlock(Properties properties) {
        super(MobEffects.DIG_SPEED, 10, DatagenModLoader.isRunningDataGen() ?
                properties.noLootTable() :
                properties.randomTicks()); //TODO: help
        this.registerDefaultState(this.getStateDefinition().any().setValue(STONE, false));
    }

    @Override
    public void randomTick(BlockState pState, ServerLevel pLevel, BlockPos pPos, RandomSource pRandom) {
        final int random = pRandom.nextInt(20);
        if (random <= 3 && pLevel.getBlockState(pPos.below()).is(ModBlocks.RICH_SOIL.get())) {
            BlockState state = random == 1 ? MDBlocks.GOSSYPIUM.getDefaultState() : pState;
            int i = 5;

            for (BlockPos blockpos : BlockPos.betweenClosed(pPos.offset(-4, -1, -4), pPos.offset(4, 1, 4))) {
                if (pLevel.getBlockState(blockpos).is(this)) {
                    --i;
                    if (i <= 0) {
                        return;
                    }
                }
            }

            BlockPos blockpos1 = pPos.offset(pRandom.nextInt(3) - 1, pRandom.nextInt(2) - pRandom.nextInt(2), pRandom.nextInt(3) - 1);

            for (int k = 0; k < 4; ++k) {
                if (pLevel.isEmptyBlock(blockpos1) && state.canSurvive(pLevel, blockpos1)) {
                    pPos = blockpos1;
                }

                blockpos1 = pPos.offset(pRandom.nextInt(3) - 1, pRandom.nextInt(2) - pRandom.nextInt(2), pRandom.nextInt(3) - 1);
            }

            if (pLevel.isEmptyBlock(blockpos1) && state.canSurvive(pLevel, blockpos1)) {
                if (pLevel.getBlockState(blockpos1.below()).is(BlockTags.BASE_STONE_OVERWORLD)) {
                    state = state.setValue(STONE, true);
                }
                pLevel.setBlock(blockpos1, state, 2);
            }
        }
    }

    @Override
    protected boolean mayPlaceOn(BlockState state, BlockGetter level, BlockPos pos) {
        return state.is(BlockTags.BASE_STONE_OVERWORLD) || state.is(BlockTags.DIRT) || state.is(BlockTags.SAND);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(STONE);
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext pContext) {
        final BlockState stateForPlacement = super.getStateForPlacement(pContext);
        if (pContext.getLevel().getBlockState(pContext.getClickedPos().below()).is(BlockTags.BASE_STONE_OVERWORLD)) {
            return stateForPlacement.setValue(STONE, true);
        }
        return stateForPlacement;
    }

    @Override
    public BlockState updateShape(BlockState pState, Direction pFacing, BlockState pFacingState, LevelAccessor pLevel, BlockPos pCurrentPos, BlockPos pFacingPos) {
        final BlockState state = super.updateShape(pState, pFacing, pFacingState, pLevel, pCurrentPos, pFacingPos);
        if (state.is(Blocks.AIR)) {
            return state;
        }
        if (pFacing == Direction.DOWN && pFacingState.is(BlockTags.BASE_STONE_OVERWORLD)) {
            state.setValue(STONE, true);
        }
        else {
            state.setValue(STONE, false);
        }
        return state;
    }
}
