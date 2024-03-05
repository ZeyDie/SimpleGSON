package com.zeydie.sgson.streams;

import com.zeydie.sgson.SGsonFile;
import lombok.Getter;
import lombok.NonNull;
import lombok.SneakyThrows;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.List;
import java.util.stream.Collectors;

public final class FileReaderStream {
    @Getter
    private final @NotNull File file;

    public FileReaderStream(@NonNull final SGsonFile gsonFile) {
        this.file = gsonFile.getFile();
    }

    public @NotNull String getJsonFile() {
        return this.getLines()
                .stream()
                .map(String::toString)
                .collect(Collectors.joining());
    }

    @SneakyThrows
    public @NotNull List<String> getLines() {
        return Files.readAllLines(this.file.toPath(), StandardCharsets.UTF_8);
    }
}