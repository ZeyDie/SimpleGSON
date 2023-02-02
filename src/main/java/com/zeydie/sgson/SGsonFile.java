package com.zeydie.sgson;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.zeydie.sgson.streams.FileReaderStream;
import com.zeydie.sgson.streams.FileWriterStream;
import lombok.Getter;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

public class SGsonFile extends SGsonBase {
    @Getter
    @NotNull
    private final File file;

    @Getter
    @NotNull
    private final FileReaderStream fileReaderStream;
    @Getter
    @NotNull
    private final FileWriterStream fileWriterStream;

    /**
     * Init
     */
    public <S extends String> SGsonFile(@NotNull final S path) {
        this(Paths.get(path));
    }

    public <P extends Path> SGsonFile(@NotNull final P path) {
        this(path.toFile());
    }

    public <F extends File> SGsonFile(@NotNull final F file) {
        this(file, new GsonBuilder().setPrettyPrinting());
    }

    public <F extends File, G extends GsonBuilder> SGsonFile(@NotNull final F file, @NotNull final G gsonBuilder) {
        this(file, gsonBuilder.create());
    }

    public <F extends File, G extends Gson> SGsonFile(@NotNull final F file, @NotNull final G gson) {
        super(gson);

        this.file = file;

        this.fileReaderStream = new FileReaderStream(this);
        this.fileWriterStream = new FileWriterStream(this);
    }

    /**
     * Methods
     */
    @NotNull
    public final <T> T fromJsonToObject(@NotNull final T object) {
        return this.fromJsonToObject(object, false);
    }

    @NotNull
    public final <T> T fromJsonToObject(@NotNull final T object, final boolean rewrite) {
        if (rewrite || !this.file.exists() || this.file.length() <= 0)
            this.writeJsonFile(this.fromObjectToJson(object));

        return this.fromJsonToObject(this.getJsonFile(), object);
    }

    @NotNull
    public final String getJsonFile() {
        return this.fileReaderStream.getJsonFile();
    }

    public final <O> void writeJsonFile(@NotNull final O object) {
        this.writeJsonFile(this.fromObjectToJson(object));
    }

    public final <S extends CharSequence> void writeJsonFile(@NotNull final S json) {
        this.fileWriterStream.writeJsonFile(json);
    }
}
