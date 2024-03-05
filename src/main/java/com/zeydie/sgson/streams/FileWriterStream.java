package com.zeydie.sgson.streams;

import com.zeydie.sgson.SGsonFile;
import lombok.Getter;
import lombok.NonNull;
import lombok.SneakyThrows;
import lombok.val;
import org.jetbrains.annotations.NotNull;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.Collections;

public final class FileWriterStream {
    @Getter
    private final @NotNull SGsonFile simpleGson;

    public <S extends SGsonFile> FileWriterStream(@NonNull final S simpleGson) {
        this.simpleGson = simpleGson;
    }

    @SneakyThrows
    public <S extends CharSequence> void writeJsonFile(@NonNull final S json) {
        this.createFile();

        @NonNull val file = this.simpleGson.getFile();

        this.simpleGson.info("Writing to %s", file.getAbsoluteFile());

        Files.write(file.toPath(), Collections.singleton(json), StandardCharsets.UTF_8);
    }

    @SneakyThrows
    public void createFile() {
        @NonNull val file = this.simpleGson.getFile();

        if (!file.exists()) {
            @NonNull val parent = file.getParentFile();

            if (parent != null)
                if (parent.mkdirs())
                    this.simpleGson.info("Creating folders %s", parent.getAbsoluteFile());

            if (file.createNewFile())
                this.simpleGson.info("Creating file %s", file);
        }
    }
}