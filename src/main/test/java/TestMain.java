import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import java.util.concurrent.TimeUnit;


public class TestMain {
//    @Disabled("Тест временно отключен!")
    @Test
    @Timeout(value = 22)
    public void failsIfExecutionTimeExceeds22Seconds() throws Exception {
        Main.main(null);
    }
}
