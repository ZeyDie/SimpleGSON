package ru.zeydie.simplegson.streams;

import ru.zeydie.simplegson.SimpleGsonFile;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public final class FileReaderStream {
    private final File file;

    public FileReaderStream(final SimpleGsonFile gsonFile) {
        this.file = gsonFile.getFile();
    }

    public String getJsonFile() {
        final StringBuilder json = new StringBuilder();

        for (final String string : this.getLines())
            json.append(string).append("\n");

        return json.toString();
    }

    public List<String> getLines() {
        final List<String> lines = new ArrayList<>();

        try (final FileReader fileReader = new FileReader(this.file)) {
            final Scanner scanner = new Scanner(fileReader);

            while (scanner.hasNextLine()) {
                final String line = scanner.nextLine();

                if (!line.isEmpty())
                    lines.add(line);
            }
        } catch (IOException error) {
            error.printStackTrace();
        }

        return lines;
    }
}
