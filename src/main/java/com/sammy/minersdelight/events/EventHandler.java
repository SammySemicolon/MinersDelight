package com.sammy.minersdelight.events;

import com.sammy.minersdelight.setup.*;
import net.neoforged.bus.api.*;
import net.neoforged.fml.common.*;
import net.neoforged.neoforge.event.brewing.*;

@EventBusSubscriber(bus = EventBusSubscriber.Bus.GAME)
public class EventHandler {

    @SubscribeEvent
    public static void registerPotionBrewing(RegisterBrewingRecipesEvent event) {
        MDPotions.registerPotionBrewing(event);
    }

}