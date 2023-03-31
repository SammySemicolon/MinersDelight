package com.sammy.minersdelight.content.loot;

import com.google.common.collect.*;
import com.google.gson.*;
import net.minecraft.core.*;
import net.minecraft.resources.*;
import net.minecraft.tags.*;
import net.minecraft.util.*;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.*;
import net.minecraft.world.level.storage.loot.*;
import net.minecraft.world.level.storage.loot.parameters.*;
import net.minecraft.world.level.storage.loot.predicates.*;

import java.util.*;

public class LootItemBlockTagCondition implements LootItemCondition {

   public static final LootItemConditionType BLOCK_TAG = new LootItemConditionType(new LootItemBlockTagCondition.Serializer());

   final TagKey<Block> blockTagKey;
   LootItemBlockTagCondition(TagKey<Block> blockTagKey) {
      this.blockTagKey = blockTagKey;
   }

   public LootItemConditionType getType() {
      return BLOCK_TAG;
   }

   /**
    * Get the parameters used by this object.
    */
   public Set<LootContextParam<?>> getReferencedContextParams() {
      return ImmutableSet.of(LootContextParams.BLOCK_STATE);
   }

   public boolean test(LootContext p_81772_) {
      BlockState blockstate = p_81772_.getParamOrNull(LootContextParams.BLOCK_STATE);
      return blockstate != null && blockstate.is(blockTagKey);
   }

   public static class Serializer implements net.minecraft.world.level.storage.loot.Serializer<LootItemBlockTagCondition> {
      public void serialize(JsonObject p_81795_, LootItemBlockTagCondition p_81796_, JsonSerializationContext p_81797_) {
         p_81795_.addProperty("block_tag", p_81796_.blockTagKey.location().toString());
      }

      public LootItemBlockTagCondition deserialize(JsonObject p_81805_, JsonDeserializationContext p_81806_) {
         ResourceLocation resourcelocation = new ResourceLocation(GsonHelper.getAsString(p_81805_, "block_tag"));
         return new LootItemBlockTagCondition(TagKey.create(Registry.BLOCK_REGISTRY, resourcelocation));
      }
   }
}