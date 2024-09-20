package com.zeydie.sgson;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.Getter;
import lombok.NonNull;
import lombok.extern.java.Log;
import org.jetbrains.annotations.NotNull;

@Log
public class SGsonBase {
    @Getter
    private Gson gson;
    @Getter
    private boolean debugging;

    public SGsonBase() {
        this.setDefaultGsonBuilder();
    }

    public void setDefaultGsonBuilder() {
        this.setGsonBuilder(new GsonBuilder());
    }

    public static @NotNull SGsonBase create() {
        return new SGsonBase();
    }

    public @NotNull <G extends GsonBuilder, O extends SGsonBase> O setGsonBuilder(@NonNull final G gsonBuilder) {
        return this.setGson(gsonBuilder.create());
    }

    public @NotNull <G extends Gson, O extends SGsonBase> O setGson(final @NonNull G gson) {
        this.gson = gson;

        return (O) this;
    }

    public @NotNull <O extends SGsonBase> O setPretty() {
        return this.setGsonBuilder(this.gson.newBuilder().setPrettyPrinting());
    }

    public @NotNull <O extends SGsonBase> O setDebug() {
        this.debugging = true;

        return (O) this;
    }

    public @NotNull <O> String fromObjectToJson(@NonNull final O object) {
        return this.gson.toJson(object);
    }

    public @NotNull <O, S extends String> O fromJsonToObject(
            @NonNull final S json,
            @NonNull final O object
    ) {
        return (O) this.gson.fromJson(json, object.getClass());
    }

    public final void info(
            @NonNull final String message,
            final Object... args
    ) {
        if (this.debugging)
            log.info(String.format(message, args));
    }

    public final void warning(
            @NonNull final String message,
            final Object... args
    ) {
        if (this.debugging)
            log.warning(String.format(message, args));
    }
}