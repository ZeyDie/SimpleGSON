package ru.zeydie.simplegson;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import ru.zeydie.simplegson.streams.FileReaderStream;
import ru.zeydie.simplegson.streams.FileWriterStream;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

public class SimpleGsonFile extends SimpleGsonBase {
    private File file;

    private FileReaderStream fileReaderStream;
    private FileWriterStream fileWriterStream;

    public SimpleGsonFile(final String path) {
        this(Paths.get(path));
    }

    public SimpleGsonFile(final Path path) {
        this(path.toFile());
    }

    public SimpleGsonFile(final File file) {
        this(file, new GsonBuilder().setPrettyPrinting());
    }

    public SimpleGsonFile(final File file, final GsonBuilder gsonBuilder) {
        this(file, gsonBuilder.create());
    }

    public SimpleGsonFile(final File file, final Gson gson) {
        super(gson);

        this.file = file;

        this.fileReaderStream = new FileReaderStream(this);
        this.fileWriterStream = new FileWriterStream(this);
    }

    public final <T> T fromJsonToObject(final T object) {
        return this.fromJsonToObject(object, false);
    }

    public final <T> T fromJsonToObject(final T object, final boolean rewrite) {
        if (rewrite || !this.file.exists() || this.file.length() <= 0)
            this.writeJsonFile(
                    this.fromObjectToJson(object)
            );

        return this.fromJsonToObject(this.getJsonFile(), object);
    }

    public final String getJsonFile() {
        return this.fileReaderStream.getJsonFile();
    }

    public final void writeJsonFile(final Object object) {
        this.writeJsonFile(
                this.fromObjectToJson(object)
        );
    }

    public final void writeJsonFile(final String json) {
        this.fileWriterStream.writeJsonFile(json);
    }

    public final File getFile() {
        return this.file;
    }

    public final SimpleGsonFile setFile(final String path) {
        return this.setFile(Paths.get(path));
    }

    public final SimpleGsonFile setFile(final Path path) {
        return this.setFile(path.toFile());
    }

    public final SimpleGsonFile setFile(final File file) {
        this.file = file;

        this.fileReaderStream = new FileReaderStream(this);
        this.fileWriterStream = new FileWriterStream(this);

        return this;
    }
}
