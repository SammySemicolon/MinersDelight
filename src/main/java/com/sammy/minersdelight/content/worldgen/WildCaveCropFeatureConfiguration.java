package com.sammy.minersdelight.content.worldgen;

import com.mojang.serialization.*;
import com.mojang.serialization.codecs.*;
import net.minecraft.util.*;
import net.minecraft.world.level.levelgen.feature.configurations.*;
import net.minecraft.world.level.levelgen.feature.stateproviders.*;

public class WildCaveCropFeatureConfiguration extends BlockPileConfiguration {
    public static final Codec<WildCaveCropFeatureConfiguration> CODEC = RecordCodecBuilder.create(
        p_191267_ -> p_191267_.group(
                    BlockStateProvider.CODEC.fieldOf("state_provider").forGetter(p_191273_ -> p_191273_.stateProvider),
                    ExtraCodecs.POSITIVE_INT.fieldOf("spread_width").forGetter(p_191271_ -> p_191271_.spreadWidth),
                    ExtraCodecs.POSITIVE_INT.fieldOf("spread_height").forGetter(p_191269_ -> p_191269_.spreadHeight)
                )
                .apply(p_191267_, WildCaveCropFeatureConfiguration::new)
    );
    public final int spreadWidth;
    public final int spreadHeight;

    public WildCaveCropFeatureConfiguration(BlockStateProvider stateProvider, int spreadWidth, int spreadHeight) {
        super(stateProvider);
        this.spreadWidth = spreadWidth;
        this.spreadHeight = spreadHeight;
    }
}