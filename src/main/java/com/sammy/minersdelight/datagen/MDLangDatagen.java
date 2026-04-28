package com.sammy.minersdelight.datagen;

import com.sammy.minersdelight.*;
import com.sammy.minersdelight.content.item.*;
import net.minecraft.data.*;
import net.minecraft.world.item.*;
import net.minecraft.world.level.block.*;
import net.neoforged.neoforge.common.data.*;
import team.lodestar.lodestone.helpers.*;

import java.util.*;

import static com.sammy.minersdelight.setup.MDBlocks.*;
import static com.sammy.minersdelight.setup.MDItems.*;

public class MDLangDatagen extends LanguageProvider {
    public static MDLangDatagen lang;

    public MDLangDatagen(PackOutput gen) {
        super(gen, MinersDelightMod.MODID, "en_us");
        lang = this;
    }

    @Override
    protected void addTranslations() {

        var blocks = new HashSet<>(BLOCKS.getEntries());
        var items = new HashSet<>(ITEMS.getEntries());

        DataHelper.takeAll(blocks, i -> i.get() instanceof WallTorchBlock);
        DataHelper.takeAll(blocks, i -> i.get() instanceof WallSignBlock);
        blocks.forEach(b -> {
            String name = b.get().getDescriptionId().replaceFirst("block\\.minersdelight\\.", "");
            name = makeProper(DataHelper.toTitleCase(correctItemName(name), "_"));
            add(b.get().getDescriptionId(), name);
        });
        DataHelper.takeAll(items, i -> i.get() instanceof BlockItem && !(i.get() instanceof ItemNameBlockItem) && !(i.get() instanceof SolidCupItem));
        items.forEach(i -> {
            String name = i.get().getDescriptionId().replaceFirst("item\\.minersdelight\\.", "");
            name = makeProper(DataHelper.toTitleCase(correctItemName(name), "_"));
            add(i.get().getDescriptionId(), name);
        });
        add("itemGroup.minersdelight", "Miner's Delight");
        add("minersdelight.container.cooking_pot", "Copper Pot");
        add("minersdelight.container.sticky_basket", "Sticky Basket");
        add("farmersdelight.tooltip.honey_bar", "Soothing honey center!");

        add("item.minecraft.potion.effect.haste", "Potion of the Haste");
        add("item.minecraft.potion.effect.long_haste", "Potion of the Haste");
        add("item.minecraft.potion.effect.strong_haste", "Potion of the Haste");
        add("item.minecraft.splash_potion.effect.haste", "Splash Potion of the Haste");
        add("item.minecraft.splash_potion.effect.long_haste", "Splash Potion of the Haste");
        add("item.minecraft.splash_potion.effect.strong_haste", "Splash Potion of the Haste");
        add("item.minecraft.lingering_potion.effect.haste", "Lingering Potion of the Haste");
        add("item.minecraft.lingering_potion.effect.long_haste", "Lingering Potion of the Haste");
        add("item.minecraft.lingering_potion.effect.strong_haste", "Lingering Potion of the Haste");

        add("item.minecraft.tipped_arrow.effect.haste", "Arrow of the Haste");
        add("item.minecraft.tipped_arrow.effect.long_haste", "Arrow of the Haste");
        add("item.minecraft.tipped_arrow.effect.strong_haste", "Arrow of the Haste");

        add("item.minecraft.potion.effect.mining_fatigue", "Potion of the Mining Fatigue");
        add("item.minecraft.potion.effect.long_mining_fatigue", "Potion of the Mining Fatigue");
        add("item.minecraft.potion.effect.strong_mining_fatigue", "Potion of the Mining Fatigue");
        add("item.minecraft.splash_potion.effect.mining_fatigue", "Splash Potion of the Mining Fatigue");
        add("item.minecraft.splash_potion.effect.long_mining_fatigue", "Splash Potion of the Mining Fatigue");
        add("item.minecraft.splash_potion.effect.strong_mining_fatigue", "Splash Potion of the Mining Fatigue");
        add("item.minecraft.lingering_potion.effect.mining_fatigue", "Lingering Potion of the Mining Fatigue");
        add("item.minecraft.lingering_potion.effect.long_mining_fatigue", "Lingering Potion of the Mining Fatigue");
        add("item.minecraft.lingering_potion.effect.strong_mining_fatigue", "Lingering Potion of the Mining Fatigue");

        add("item.minecraft.tipped_arrow.effect.mining_fatigue", "Arrow of the Mining Fatigue");
        add("item.minecraft.tipped_arrow.effect.long_mining_fatigue", "Arrow of the Mining Fatigue");
        add("item.minecraft.tipped_arrow.effect.strong_mining_fatigue", "Arrow of the Mining Fatigue");
    }

    @Override
    public String getName() {
        return "Miners Delight Lang Datagen";
    }

    public String makeProper(String s) {
        s = s
                .replaceAll("Of", "of")
                .replaceAll("The", "the");
        return Character.toUpperCase(s.charAt(0)) + s.substring(1);
    }

    public String correctSoundName(String name) {
        if ((name.endsWith("_step"))) {
            return "footsteps";
        }
        if ((name.endsWith("_place"))) {
            return "block_placed";
        }
        if ((name.endsWith("_break"))) {
            return "block_broken";
        }
        if ((name.endsWith("_hit"))) {
            return "block_breaking";
        }
        return name;
    }

    public String correctItemName(String name) {
        if (name.contains("music_disc")) {
            return "music_disc";
        }
        if ((!name.endsWith("_bricks"))) {
            if (name.contains("bricks")) {
                name = name.replaceFirst("bricks", "brick");
            }
        }
        if ((!name.endsWith("_boards"))) {
            if (name.contains("boards")) {
                name = name.replaceFirst("boards", "board");
            }
        }
        if (name.contains("_fence") || name.contains("_button")) {
            if (name.contains("planks")) {
                name = name.replaceFirst("_planks", "");
            }
        }
        return makeProperEnglish(name);
    }

    public String makeProperEnglish(String name) {
        String[] replacements = new String[]{"ns_", "rs_", "ts_"};
        String properName = name;
        for (String replacement : replacements) {
            int index = properName.indexOf(replacement);
            if (index != -1) {
                properName = properName.replaceFirst("s_", "'s_");
                break;
            }
        }
        return properName;
    }
}
