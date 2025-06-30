package com.sammy.minersdelight.content.item;

import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

import static net.minecraft.world.level.block.InfestedBlock.infestedStateByHost;
import static net.minecraft.world.level.block.InfestedBlock.isCompatibleHostBlock;

public class SilverfishEggsItem extends Item {
    public SilverfishEggsItem(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public InteractionResult useOn(UseOnContext pContext) {
        Level level = pContext.getLevel();
        Player player = pContext.getPlayer();
        InteractionHand hand = pContext.getHand();
        BlockPos pos = pContext.getClickedPos();
        BlockState state = level.getBlockState(pos);
        if (isCompatibleHostBlock(state)) {
            player.swing(hand);
            if (level.isClientSide) {
                return InteractionResult.SUCCESS;
            }
            BlockState infestedState = infestedStateByHost(state);
            level.setBlock(pos, infestedState, 3);
            level.levelEvent(2001, pos, Block.getId(infestedState));
            if (!player.isCreative()) {
                player.getItemInHand(hand).shrink(1);
            }
            return InteractionResult.SUCCESS;

        }
        return super.useOn(pContext);
    }
}
