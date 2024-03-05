import com.zeydie.sgson.SGsonBase;
import lombok.extern.java.Log;
import lombok.val;
import org.junit.jupiter.api.*;

@Log
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public final class SGsonBaseTest {
    private static SGsonBase sGsonBase;
    private static String stringOfObject;

    @BeforeAll
    public static void build() {
        sGsonBase = new SGsonBase()
                .setPretty()
                .setDebug();

        Assertions.assertNotNull(sGsonBase);
    }

    @Test
    @Order(2)
    public void fromObjectToString() {
        val data = new Data();

        data.setName("Daniel");
        data.setAge(22);
        data.setGenius(true);

        stringOfObject = sGsonBase.fromObjectToJson(data);

        log.info("stringOfObject " + stringOfObject);

        Assertions.assertNotNull(stringOfObject);
    }

    @Test
    @Order(3)
    public void fromStringToObject() {
        val data = sGsonBase.fromJsonToObject(stringOfObject, new Data());

        log.info("data " + data);

        Assertions.assertNotNull(data);
    }
}