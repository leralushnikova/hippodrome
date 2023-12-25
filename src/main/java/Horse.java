import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static java.util.Objects.isNull;

public class Horse {

    private final String name;
    private final double speed;
    private double distance;

    private static final Logger logger = LogManager.getLogger(Horse.class);

    public Horse(String name, double speed, double distance) {
        String error;
        if (isNull(name)) {
            error = "Name cannot be null.";
            logger.error(error);
            throw new IllegalArgumentException(error);
        } else if (name.isBlank()) {
            error = "Name cannot be blank.";
            logger.error(error);
            throw new IllegalArgumentException(error);
        }
        if (speed < 0) {
            error = "Speed cannot be negative.";
            logger.error(error);
            throw new IllegalArgumentException(error);
        }
        if (distance < 0) {
            error = "Distance cannot be negative.";
            logger.error(error);
            throw new IllegalArgumentException(error);
        }

        this.name = name;
        this.speed = speed;
        this.distance = distance;
    }

    public Horse(String name, double speed) {
        this(name, speed, 0);
    }

    public String getName() {
        return name;
    }

    public double getSpeed() {
        return speed;
    }

    public double getDistance() {
        return distance;
    }

    public void move() {
        distance += speed * getRandomDouble(0.2, 0.9);
    }

    public static double getRandomDouble(double min, double max) {
        return (Math.random() * (max - min)) + min;
    }
}
