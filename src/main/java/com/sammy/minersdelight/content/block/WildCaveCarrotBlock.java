package com.sammy.minersdelight.content.block;

import net.minecraft.core.*;
import net.minecraft.tags.*;
import net.minecraft.world.effect.*;
import net.minecraft.world.item.context.*;
import net.minecraft.world.level.*;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.*;
import net.minecraft.world.level.block.state.properties.*;
import net.minecraftforge.data.loading.*;
import org.jetbrains.annotations.*;
import vectorwing.farmersdelight.common.block.*;

public class WildCaveCarrotBlock extends WildCropBlock {

    public static final BooleanProperty STONE = BooleanProperty.create("stone");

    public WildCaveCarrotBlock(Properties properties) {
        super(MobEffects.DIG_SPEED, 10, DatagenModLoader.isRunningDataGen() ?
                properties.noLootTable() :
                properties); //TODO: help
        this.registerDefaultState(this.getStateDefinition().any().setValue(STONE, false));
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
