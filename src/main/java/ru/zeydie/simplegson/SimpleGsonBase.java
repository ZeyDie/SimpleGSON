package ru.zeydie.simplegson;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.logging.Logger;

public class SimpleGsonBase {
    private final Logger logger = Logger.getLogger("Simple-GSON");

    public final void info(final String message, final Object... args) {
        if (this.debugging)
            this.logger.info(String.format(message, args));
    }

    public final void warning(final String message, final Object... args) {
        if (this.debugging)
            this.logger.warning(String.format(message, args));
    }

    private Gson gson;
    private boolean debugging;

    public SimpleGsonBase() {
        this(new GsonBuilder().setPrettyPrinting());
    }

    public SimpleGsonBase(final GsonBuilder gsonBuilder) {
        this(gsonBuilder.create());
    }

    public SimpleGsonBase(final Gson gson) {
        this.gson = gson;
    }

    public SimpleGsonBase setDebug() {
        this.debugging = true;

        return this;
    }

    public final String fromObjectToJson(final Object object) {
        return this.gson.toJson(object);
    }

    public final <T> T fromJsonToObject(final String json, final Object object) {
        return (T) this.gson.fromJson(json, object.getClass());
    }

    public final Gson getGson() {
        return this.gson;
    }

    public final void setGson(final GsonBuilder gsonBuilder) {
        this.setGson(gsonBuilder.create());
    }

    public final void setGson(final Gson gson) {
        this.gson = gson;
    }
}
