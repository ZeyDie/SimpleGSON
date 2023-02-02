package com.zeydie.sgson.streams;

import lombok.Getter;
import org.jetbrains.annotations.NotNull;
import com.zeydie.sgson.SGsonFile;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.Collections;

public final class FileWriterStream {
    @Getter
    private final SGsonFile simpleGson;

    public <S extends SGsonFile> FileWriterStream(@NotNull final S simpleGson) {
        this.simpleGson = simpleGson;
    }

    public <S extends CharSequence> void writeJsonFile(@NotNull final S json) {
        this.createFile();

        final File file = this.simpleGson.getFile();

        try {
            this.simpleGson.info("Writing to... %s", file.getAbsoluteFile());

            Files.write(file.toPath(), Collections.singleton(json), StandardCharsets.UTF_8);
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    public void createFile() {
        final File file = this.simpleGson.getFile();

        if (!file.exists()) {
            try {
                final File parent = file.getParentFile();

                if (parent != null)
                    if (parent.mkdirs())
                        this.simpleGson.info("Creating folders... %s", parent.getAbsoluteFile());

                if (file.createNewFile())
                    this.simpleGson.info("Creating file... %s", file);
            } catch (IOException exception) {
                exception.printStackTrace();
            }
        }
    }
}
