package com.sammy.minersdelight.content.block;

import net.minecraft.core.BlockPos;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.Tags;
import net.minecraftforge.data.loading.DatagenModLoader;
import vectorwing.farmersdelight.common.block.WildCropBlock;

public class WildCaveCarrotBlock extends WildCropBlock {
    public WildCaveCarrotBlock(Properties properties) {
        super(MobEffects.DIG_SPEED, 10, DatagenModLoader.isRunningDataGen() ?
                properties.noDrops() :
                properties); //TODO: help
    }

    @Override
    protected boolean mayPlaceOn(BlockState state, BlockGetter level, BlockPos pos) {
        return state.is(Tags.Blocks.STONE) || state.is(BlockTags.DIRT) || state.is(BlockTags.SAND);
    }
}
