package com.zeydie.sgson;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.Getter;
import org.jetbrains.annotations.NotNull;

import java.util.logging.Logger;

public class SGsonBase {
    private final Logger logger = Logger.getLogger("SGson");

    public final void info(@NotNull final String message, final Object... args) {
        if (this.debugging)
            this.logger.info(String.format(message, args));
    }

    public final void warning(@NotNull final String message, final Object... args) {
        if (this.debugging)
            this.logger.warning(String.format(message, args));
    }

    @Getter
    private Gson gson;
    @Getter
    private boolean debugging;

    public SGsonBase() {
        this(new GsonBuilder().setPrettyPrinting());
    }

    public <G extends GsonBuilder> SGsonBase(@NotNull final G gsonBuilder) {
        this(gsonBuilder.create());
    }

    public <G extends Gson> SGsonBase(@NotNull final G gson) {
        this.gson = gson;
    }

    public final <G extends GsonBuilder> void setGsonBuilder(@NotNull final G gsonBuilder) {
        this.setGson(gsonBuilder.create());
    }

    public SGsonBase setDebug() {
        this.debugging = true;

        return this;
    }

    public <G extends Gson> SGsonBase setGson(final @NotNull G gson) {
        this.gson = gson;

        return this;
    }

    @NotNull
    public <O> String fromObjectToJson(@NotNull final O object) {
        return this.gson.toJson(object);
    }

    public <O, S extends String> O fromJsonToObject(@NotNull final S json, @NotNull final O object) {
        return (O) this.gson.fromJson(json, object.getClass());
    }
}
