import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class TestHippodrome {
    @Spy
    List<Horse> mockList;

    {
        mockList = List.of(
                new Horse("Max", 1, 2), //1
                new Horse("Billy", 2, 3), //2
                new Horse("Chad", 3, 4), //3
                new Horse("Chance", 4, 5), //4
                new Horse("Charlie", 5, 6), //5
                new Horse("Chief", 6, 7), //6
                new Horse("Chip", 7, 8), //7
                new Horse("Cinnamon", 8, 9), //8
                new Horse("Dakota", 9, 10), //9
                new Horse("Dash", 10, 11), //10
                new Horse("Doggy", 11, 12), //11
                new Horse("Doug", 12, 13), //12
                new Horse("Dusty", 13, 14), //13
                new Horse("Flash", 14, 15), //14
                new Horse("Fury", 15, 16), //15
                new Horse("Gypsy", 16, 17), //16
                new Horse("Hidalgo", 17, 18), //17
                new Horse("Jack", 18, 19), //18
                new Horse("Jackie", 19, 20), //19
                new Horse("Joey", 20, 21), //20
                new Horse("King", 21, 22), //21
                new Horse("Lucky", 22, 23), //22
                new Horse("Magic", 23, 24), //23
                new Horse("Maximus", 24, 25), //24
                new Horse("Misty", 25, 26), //25
                new Horse("Mocha", 26, 27), //26
                new Horse("Molly", 27, 28), //27
                new Horse("Napoleon", 28, 29), //28
                new Horse("Nutmeg", 29, 30), //29
                new Horse("Poppy", 30, 31), //30
                new Horse("Ranger", 31, 32), //31
                new Horse("Poppy", 32, 33), //32
                new Horse("Rose", 33, 34), //33
                new Horse("Ruby", 34, 35), //34
                new Horse("Rusty", 35, 36), //35
                new Horse("Scout", 36, 37), //36
                new Horse("Silver", 37, 38), //37
                new Horse("Snowball", 38, 39), //38
                new Horse("Sparky", 39, 40), //39
                new Horse("Spirit", 40, 41), //40
                new Horse("Star", 41, 42), //41
                new Horse("Sugar", 42, 43), //42
                new Horse("Sunny", 43, 44), //43
                new Horse("Sunshine", 44, 45), //44
                new Horse("Teddy", 45, 46), //45
                new Horse("Toffee", 46, 47), //46
                new Horse("Trixie", 47, 48), //47
                new Horse("Albert", 48, 49), //48
                new Horse("Angus", 49, 50), //49
                new Horse("Barbaro", 50, 51) //50
        );
    }

    // test a. constructor
   @Test
    public void testHippodromeConstructorNullListThrows(){
        assertThrows(IllegalArgumentException.class, () -> new Hippodrome(null));
    }

    @Test
    public void testHippodromeConstructorNullListEquals() {
        String text = "Horses cannot be null.";
        try {
            new Hippodrome(null);
        } catch (IllegalArgumentException e) {
            assertEquals(text, e.getMessage());
        }
    }
    @Test
    public void testHippodromeConstructorEmptyListThrows() {
        mockList = new ArrayList<>();
        assertThrows(IllegalArgumentException.class, () -> new Hippodrome(mockList));
    }

    @Test
    public void testHippodromeConstructorEmptyListEquals() {
        mockList = new ArrayList<>();
        String text = "Horses cannot be empty.";
        try {
            new Hippodrome(mockList);
        } catch (IllegalArgumentException e) {
            assertEquals(text, e.getMessage());
        }
    }



    // test b. getHorses
    @Test
    public void testHippodromeGetHorses() {
        Hippodrome hippodrome = new Hippodrome(mockList);
        assertArrayEquals(hippodrome.getHorses().toArray(), mockList.toArray());
    }

    // test c. move
    @ParameterizedTest
    @ValueSource(doubles = {0.3, 0.5, 0.7})
    public void testHippodromeMove(double arg) {
        try (MockedStatic<Horse> mockito = Mockito.mockStatic(Horse.class)){
            mockito.when(() -> Horse.getRandomDouble(0.2, 0.9)).thenReturn(arg);
            Hippodrome hippodrome = new Hippodrome(mockList);
            List<Double> moveList = new ArrayList<>();
            for (Horse horse : hippodrome.getHorses()) {
                double moveHorse = horse.getDistance() + horse.getSpeed() * Horse.getRandomDouble(0.2, 0.9);
                moveList.add(moveHorse);
            }
            hippodrome.move();
            for (int i = 0; i < hippodrome.getHorses().size(); i++) {
                assertEquals(moveList.get(i), hippodrome.getHorses().get(i).getDistance());
            }
        }
    }

    // test d. getWinner
    @Test
    public void testHippodromeGetWinner() {
        Hippodrome hippodrome = new Hippodrome(mockList);
        Horse bestHorse = mockList.stream()
                .max(Comparator.comparing(Horse::getDistance))
                .get();
        assertEquals(bestHorse, hippodrome.getWinner());
    }


}
