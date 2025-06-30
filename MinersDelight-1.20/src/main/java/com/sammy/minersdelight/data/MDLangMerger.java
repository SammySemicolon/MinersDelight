package com.sammy.minersdelight.data;

import com.google.gson.*;
import com.google.gson.internal.*;
import com.google.gson.stream.*;
import com.sammy.minersdelight.*;
import net.minecraft.data.*;
import net.minecraft.util.*;
import net.minecraftforge.common.data.*;

import java.io.*;
import java.nio.file.*;

public class MDLangMerger extends LanguageProvider {

    private static final Gson GSON = (new GsonBuilder()).setPrettyPrinting()
            .disableHtmlEscaping()
            .create();

    private final PackOutput output; //TODO: this should be an access transformer instead but I'm lazy

    public MDLangMerger(PackOutput output) {
        super(output, MinersDelightMod.MODID, "en_us");
        this.output = output;
    }

    @Override
    protected void addTranslations() {
        Path path = output.getOutputFolder().resolve("assets/" + MinersDelightMod.MODID + "/lang/" + "en_us.json");
        JsonObject original = null;
        try {
            original = collectExistingEntries(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
        addAll(fromResource("assets/" + MinersDelightMod.MODID + "/lang/extra_lang.json").getAsJsonObject(), original);
    }

    private JsonObject collectExistingEntries(Path path) throws IOException {
        if (!Files.exists(path)) {
            return null;
        }

        try (BufferedReader reader = Files.newBufferedReader(path)) {
            final JsonObject jsonObject = GsonHelper.fromJson(GSON, reader, JsonObject.class);
            addAll(jsonObject, null);
            return jsonObject;
        }
    }

    private void addAll(JsonObject jsonObject, JsonObject compareAgainst) {
        jsonObject.entrySet().forEach(e -> {
            String key = e.getKey();
            String value = e.getValue().getAsString();
            if (compareAgainst == null || !compareAgainst.has(key)) {
                add(key, value);
            }
        });
    }

    private JsonElement fromResource(String filepath) {
        JsonElement element = loadJsonResource(filepath);
        if (element == null)
            throw new IllegalStateException(String.format("Could not find default lang file: %s", filepath));
        return element;
    }

    public static JsonElement loadJsonResource(String filepath) {
        return loadJson(ClassLoader.getSystemResourceAsStream(filepath));
    }

    private static JsonElement loadJson(InputStream inputStream) {
        try {
            JsonReader reader = new JsonReader(new BufferedReader(new InputStreamReader(inputStream)));
            reader.setLenient(true);
            JsonElement element = Streams.parse(reader);
            reader.close();
            inputStream.close();
            return element;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}