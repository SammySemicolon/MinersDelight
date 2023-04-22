package com.sammy.minersdelight.content.block;

import net.minecraft.core.*;
import net.minecraft.tags.*;
import net.minecraft.world.effect.*;
import net.minecraft.world.level.*;
import net.minecraft.world.level.block.state.*;
import net.minecraftforge.common.*;
import net.minecraftforge.data.loading.*;
import vectorwing.farmersdelight.common.block.*;

public class WildCaveCarrotBlock extends WildCropBlock {
    public WildCaveCarrotBlock(Properties properties) {
        super(MobEffects.DIG_SPEED, 10, DatagenModLoader.isRunningDataGen() ?
                properties.noLootTable() :
                properties); //TODO: help
    }

    @Override
    protected boolean mayPlaceOn(BlockState state, BlockGetter level, BlockPos pos) {
        return state.is(Tags.Blocks.STONE) || state.is(BlockTags.DIRT) || state.is(BlockTags.SAND);
    }
}
