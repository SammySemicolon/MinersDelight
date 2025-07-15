package com.sammy.minersdelight.content.block;

import com.sammy.minersdelight.setup.MDItems;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import vectorwing.farmersdelight.common.block.FeastBlock;

public class GlazedArachnidLimbsFeastBlock extends FeastBlock {
    public GlazedArachnidLimbsFeastBlock(Properties properties) {
        super(properties, MDItems.PLATE_OF_GLAZED_ARACHNID_LIMBS::get, true);
    }
}