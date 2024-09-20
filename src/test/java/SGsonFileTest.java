import com.zeydie.sgson.SGsonFile;
import lombok.extern.java.Log;
import lombok.val;
import org.junit.jupiter.api.*;

import java.nio.file.Path;
import java.nio.file.Paths;

@Log
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public final class SGsonFileTest {
    private static final Path path = Paths.get("test", "sgson.json");

    private static SGsonFile sGsonFile;

    @BeforeAll
    public static void build() {
        sGsonFile = SGsonFile.create(path)
                .setPretty()
                .setDebug();

        Assertions.assertNotNull(sGsonFile);
    }

    @Test
    @Order(2)
    public void fromObjectToString() {
        val data = new Data();

        data.setName("Daniel");
        data.setAge(22);
        data.setGenius(true);

        val stringOfObject = sGsonFile.fromObjectToJson(data);

        sGsonFile.writeJsonFile(stringOfObject);

        log.info("stringOfObject " + stringOfObject);

        Assertions.assertNotNull(stringOfObject);
        Assertions.assertTrue(path.toFile().exists());
    }

    @Test
    @Order(3)
    public void fromStringToObject() {
        val data = sGsonFile.fromJsonToObject(new Data());

        log.info("data " + data);

        Assertions.assertNotNull(data);
    }

    @AfterAll
    public static void deleteFile() {
        val file = path.toFile();
        val folder = file.getParentFile();

        log.info("file " + file);
        file.delete();
        Assertions.assertTrue(folder.exists());

        log.info("folder " + folder);
        folder.delete();
        Assertions.assertFalse(folder.exists());
    }

    @AfterAll
    public static void accessibility() {
        //NO create(path)!
        SGsonFile.createPretty(path).writeJsonFile("");
    }
}