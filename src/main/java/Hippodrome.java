import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import static java.util.Objects.isNull;

public class Hippodrome {

    private final List<Horse> horses;
    private static final Logger logger = LogManager.getLogger(Hippodrome.class);

    public Hippodrome(List<Horse> horses) {
        if (isNull(horses)) {
            String error = "Horses cannot be null.";
            logger.error(error);
            throw new IllegalArgumentException(error);
        } else if (horses.isEmpty()) {
            String error = "Horses cannot be empty.";
            logger.error(error);
            throw new IllegalArgumentException(error);
        }

        this.horses = horses;
    }

    public List<Horse> getHorses() {
        return Collections.unmodifiableList(horses);
    }

    public void move() {
        horses.forEach(Horse::move);
    }

    public Horse getWinner() {
        return horses.stream()
                .max(Comparator.comparing(Horse::getDistance))
                .get();
    }
}
