package com.sammy.minersdelight.logic;

import com.sammy.minersdelight.*;
import net.minecraftforge.event.*;
import net.minecraftforge.eventbus.api.*;
import net.minecraftforge.fml.common.*;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.FORGE, modid = MinersDelightMod.MODID)
public class EventHandler {

    @SubscribeEvent
    public static void registerListeners(AddReloadListenerEvent event) {
        CupConversionReloadListener.register(event);
    }
}