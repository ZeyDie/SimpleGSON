package ru.zeydie.simplegson.streams;

import ru.zeydie.simplegson.SimpleGsonFile;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public final class FileWriterStream {
    private final SimpleGsonFile gsonFile;
    private final File file;

    public FileWriterStream(final SimpleGsonFile gsonFile) {
        this.gsonFile = gsonFile;
        this.file = gsonFile.getFile();
    }

    public void writeJsonFile(final String json) {
        this.createFile();

        try (final FileWriter fileWriter = new FileWriter(this.file)) {
            this.gsonFile.info("Writing to %s", this.file.getAbsoluteFile());

            fileWriter.write(json);
            fileWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void createFile() {
        if (!this.file.exists()) {
            try {
                final File parent = this.file.getParentFile();

                if (parent != null)
                    if (parent.mkdirs())
                        this.gsonFile.info("Creating folders... %s", parent.getAbsoluteFile());

                if (this.file.createNewFile())
                    this.gsonFile.info("Creating file... %s", this.file);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
