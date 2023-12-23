import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


@ExtendWith(MockitoExtension.class)
public class TestHorse {

    private final String name = "Max";

    @Test
    public void testHorseConstructorNameThrows(){
        assertThrows(IllegalArgumentException.class, () -> new Horse(null, 0));
    }

    @Test
    public void testHorseConstructorNameEquals(){
        String message = "Name cannot be null.";
        try {
            new Horse(null, 0);
        } catch (IllegalArgumentException e) {
            String str = e.getMessage();
            assertEquals(message, str);
        }
    }

    @ParameterizedTest
    @ValueSource(strings = {"" , "     ", "\t", "\n"})
    public void testHorseConstructorNameManyThrows(String input){
        assertThrows(IllegalArgumentException.class, () -> new Horse(input, 0));
    }

    @ParameterizedTest
    @ValueSource(strings = {"" , "     ", "\t", "\n"})
    public void testHorseConstructorNameManyEquals(String input){
        String message = "Name cannot be blank.";
        try {
            new Horse(input, 0);
        } catch (IllegalArgumentException e) {
            String str = e.getMessage();
            assertEquals(message, str);
        }
    }

    @Test
    public void testHorseConstructorSpeedThrows(){
        assertThrows(IllegalArgumentException.class, () -> new Horse(name, -5));
    }


    @Test
    public void testHorseConstructorSpeedEquals(){
        String message = "Speed cannot be negative.";
        try {
            new Horse(name, -5);
        } catch (IllegalArgumentException e) {
            String str = e.getMessage();
            assertEquals(message, str);
        }
    }

    @Test
    public void testHorseConstructorDistanceThrows(){
        assertThrows(IllegalArgumentException.class, () -> new Horse("Max", 10, -5));
    }


    @Test
    public void testHorseConstructorDistanceEquals(){
        String message = "Distance cannot be negative.";
        try {
            new Horse(name, 10, -5);
        } catch (IllegalArgumentException e) {
            String str = e.getMessage();
            assertEquals(message, str);
        }
    }

    @Test
    public void testHorseGetName(){
        Horse h = new Horse(name, 10, 10);
        assertEquals(name, h.getName());
    }

    @Test
    public void testHorseGetSpeed(){
        double speed = 10;
        Horse h = new Horse(name, speed);
        assertEquals(speed, h.getSpeed());
    }

    @Test
    public void testHorseGetDistanceWithConstructor(){
        double distance = 10;
        Horse h = new Horse(name, 10, distance);
        assertEquals(distance, h.getDistance());
    }

    @Test
    public void testHorseGetDistanceWithoutConstructor(){
        double distance = 0;
        Horse h = new Horse(name, 10);
        assertEquals(distance, h.getDistance());
    }

    @ParameterizedTest
    @ValueSource(doubles = {0.3, 0.5, 0.7})
    public void testHorseMove(double arg){
        try (MockedStatic<Horse> mock = Mockito.mockStatic(Horse.class)) {
            mock.when(() -> Horse.getRandomDouble(0.2, 0.9)).thenReturn(arg);
            Horse h = new Horse(name, 10, 10);
            Double moveH = h.getDistance() + h.getSpeed() * Horse.getRandomDouble(0.2, 0.9);
            h.move();
            assertEquals(moveH, h.getDistance());
        }
    }

}
