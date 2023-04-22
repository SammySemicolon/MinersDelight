package com.sammy.minersdelight.setup;

import com.sammy.minersdelight.*;
import com.sammy.minersdelight.content.block.copper_pot.*;
import net.minecraft.client.gui.screens.*;
import net.minecraft.world.inventory.*;
import net.minecraftforge.common.extensions.*;
import net.minecraftforge.eventbus.api.*;
import net.minecraftforge.fml.common.*;
import net.minecraftforge.fml.event.lifecycle.*;
import net.minecraftforge.registries.*;

public class MDMenuTypes {


    public static final DeferredRegister<MenuType<?>> MENU_TYPES = DeferredRegister.create(ForgeRegistries.MENU_TYPES, MinersDelightMod.MODID);

    public static final RegistryObject<MenuType<CopperPotMenu>> COPPER_POT = MENU_TYPES
            .register("copper_pot", () -> IForgeMenuType.create(CopperPotMenu::new));

    @Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, modid = MinersDelightMod.MODID)
    public static class ClientOnly {

        @SubscribeEvent
        public static void clientSetup(FMLClientSetupEvent event) {
            event.enqueueWork(() -> MenuScreens.register(COPPER_POT.get(), CopperPotScreen::new));
        }
    }
}