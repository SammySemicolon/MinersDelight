package com.sammy.minersdelight.content.worldgen;

import net.minecraft.core.*;
import net.minecraft.tags.*;
import net.minecraft.util.*;
import net.minecraft.world.level.*;
import net.minecraft.world.level.block.state.*;
import net.minecraft.world.level.levelgen.feature.*;

import static com.sammy.minersdelight.content.block.WildCaveCarrotBlock.modifyState;

public class WildCaveCropFeature extends Feature<WildCaveCropFeatureConfiguration> {
    public WildCaveCropFeature() {
        super(WildCaveCropFeatureConfiguration.CODEC);
    }

    @Override
    public boolean place(FeaturePlaceContext<WildCaveCropFeatureConfiguration> context) {
        WorldGenLevel worldgenlevel = context.level();
        BlockPos blockpos = context.origin();
        BlockState blockstate = worldgenlevel.getBlockState(blockpos.below());
        WildCaveCropFeatureConfiguration config = context.config();
        RandomSource randomsource = context.random();

        if (blockstate.is(BlockTags.BASE_STONE_OVERWORLD) || blockstate.is(BlockTags.DIRT) || blockstate.is(BlockTags.SAND)) {
            int i = blockpos.getY();
            if (i >= worldgenlevel.getMinBuildHeight() + 1 && i + 1 < worldgenlevel.getMaxBuildHeight()) {
                int j = 0;

                for (int k = 0; k < config.spreadWidth * config.spreadWidth; k++) {
                    BlockPos offsetPos = blockpos.offset(
                            randomsource.nextInt(config.spreadWidth) - randomsource.nextInt(config.spreadWidth),
                            randomsource.nextInt(config.spreadHeight) - randomsource.nextInt(config.spreadHeight),
                            randomsource.nextInt(config.spreadWidth) - randomsource.nextInt(config.spreadWidth)
                    );
                    BlockState plantState = config.stateProvider.getState(randomsource, offsetPos);

                    if (worldgenlevel.isEmptyBlock(offsetPos)
                            && offsetPos.getY() > worldgenlevel.getMinBuildHeight()
                            && plantState.canSurvive(worldgenlevel, offsetPos)) {
                        plantState = modifyState(worldgenlevel, plantState, offsetPos);
                        worldgenlevel.setBlock(offsetPos, plantState, 2);
                        j++;
                    }
                }

                return j > 0;
            } else {
                return false;
            }
        }
        return false;
    }
}