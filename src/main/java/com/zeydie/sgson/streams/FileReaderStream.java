package com.zeydie.sgson.streams;

import lombok.Getter;
import org.jetbrains.annotations.NotNull;
import com.zeydie.sgson.SGsonFile;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.List;

public final class FileReaderStream {
    @Getter
    private final File file;

    public FileReaderStream(@NotNull final SGsonFile gsonFile) {
        this.file = gsonFile.getFile();
    }

    @NotNull
    public String getJsonFile() {
        final StringBuilder json = new StringBuilder();

        this.getLines().forEach(string -> json.append(string).append("\n"));

        return json.toString();
    }

    @NotNull
    public List<String> getLines() {
        try {
            return Files.readAllLines(this.file.toPath(), StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
