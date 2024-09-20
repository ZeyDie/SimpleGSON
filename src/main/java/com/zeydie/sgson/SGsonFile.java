package com.zeydie.sgson;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.zeydie.sgson.streams.FileReaderStream;
import com.zeydie.sgson.streams.FileWriterStream;
import lombok.Getter;
import lombok.NonNull;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

public class SGsonFile extends SGsonBase {
    @Getter
    private final @NotNull File file;

    @Getter
    private final @NotNull FileReaderStream fileReaderStream;
    @Getter
    private final @NotNull FileWriterStream fileWriterStream;

    public <S extends String> SGsonFile(@NonNull final S path) {
        this(Paths.get(path));
    }

    public <P extends Path> SGsonFile(@NonNull final P path) {
        this(path.toFile());
    }

    public <F extends File> SGsonFile(@NonNull final F file) {
        this(file, new GsonBuilder());
    }

    public <F extends File, G extends GsonBuilder> SGsonFile(
            @NonNull final F file,
            @NonNull final G gsonBuilder
    ) {
        this(file, gsonBuilder.create());
    }

    public <F extends File, G extends Gson> SGsonFile(
            @NonNull final F file,
            @NonNull final G gson
    ) {
        super();

        this.setGson(gson);

        this.file = file;

        this.fileReaderStream = new FileReaderStream(this);
        this.fileWriterStream = new FileWriterStream(this);
    }

    public static <S extends String> @NotNull SGsonFile create(@NonNull final S path) {
        return new SGsonFile(path);
    }

    public static <P extends Path> @NotNull SGsonFile create(@NonNull final P path) {
        return new SGsonFile(path);
    }

    public static <F extends File> @NotNull SGsonFile create(@NonNull final F file) {
        return new SGsonFile(file);
    }

    public final @NotNull <T> T fromJsonToObject(@NonNull final T object) {
        return this.fromJsonToObject(object, false);
    }

    public final @NotNull <T> T fromJsonToObject(
            @NonNull final T object,
            final boolean rewrite
    ) {
        if (rewrite || !this.file.exists() || this.file.length() <= 0)
            this.writeJsonFile(this.fromObjectToJson(object));

        return this.fromJsonToObject(this.getJsonFile(), object);
    }

    public final @NotNull String getJsonFile() {
        return this.fileReaderStream.getJsonFile();
    }

    public final <O> void writeJsonFile(@NonNull final O object) {
        this.writeJsonFile(this.fromObjectToJson(object));
    }

    public final <S extends CharSequence> void writeJsonFile(@NonNull final S json) {
        this.fileWriterStream.writeJsonFile(json);
    }
}