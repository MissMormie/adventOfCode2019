package Days;

import helpers.Coordinate;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class Day10_MonitoringStationTest {

    @Test
    void getAsteroidCoords() {
        String input = ".#..#\n" +
                ".....\n" +
                "#####\n" +
                "....#\n" +
                "...##";
        List<Coordinate> asteroidCoords = Day10_MonitoringStation.getAsteroidCoord(input);
        assertEquals(10, asteroidCoords.size(), "Asteroids in space");
        assertEquals(true, asteroidCoords.contains(new Coordinate(3,4)), "Asteroid exists");
    }

    @Test
    void getSlope() {
        Coordinate baseCoord = new Coordinate(4, 4);
        Coordinate target = new Coordinate(5,7);
        double slope = new Day10_MonitoringStation.SlopeForCoord(baseCoord, target).slope;
        assertEquals(3.0, slope, "slopen");
    }

    @Test
    void runA() {
        String input;
        int answer;

        input = ".#..#\n" +
                ".....\n" +
                "#####\n" +
                "....#\n" +
                "...##";
        answer = 8;
        assertEquals(answer, Day10_MonitoringStation.runA(input), "input = " + input + " answer: " + answer);

    }

    @Test
    void runA_largerSample() {
        String input;
        int answer;

        input = "......#.#.\n" +
                "#..#.#....\n" +
                "..#######.\n" +
                ".#.#.###..\n" +
                ".#..#.....\n" +
                "..#....#.#\n" +
                "#..#....#.\n" +
                ".##.#..###\n" +
                "##...#..#.\n" +
                ".#....####";
        answer = 33;
        assertEquals(answer, Day10_MonitoringStation.runA(input), "input = " + input + " answer: " + answer);

    }


    @Test
    void runB() {
        String input;
        int answer;

        input = ".#..##.###...#######\n" +
                "##.############..##.\n" +
                ".#.######.########.#\n" +
                ".###.#######.####.#.\n" +
                "#####.##.#.##.###.##\n" +
                "..#####..#.#########\n" +
                "####################\n" +
                "#.####....###.#.#.##\n" +
                "##.#################\n" +
                "#####.##.###..####..\n" +
                "..######..##.#######\n" +
                "####.##.####...##..#\n" +
                ".#####..#.######.###\n" +
                "##...#.##########...\n" +
                "#.##########.#######\n" +
                ".####.#.###.###.#.##\n" +
                "....##.##.###..#####\n" +
                ".#.#.###########.###\n" +
                "#.#.#.#####.####.###\n" +
                "###.##.####.##.#..##";
        answer = 802;
        assertEquals(answer, Day10_MonitoringStation.runB(input, new Coordinate(1,1), 200), "input = " + input + " answer: " + answer);
    }

    @Test
    void runB_small() {
        String input;
        int answer;

        input = ".#....#####...#..\n" +
                "##...##.#####..##\n" +
                "##...#...#.#####.\n" +
                "..#.....#...###..\n" +
                "..#.#.....#....##\n";

//        input = ".........24......\n" +
//                "........13.67..9.\n" +
//                ".........5.8.....\n" +
//                "........X........\n" +
//                ".................\n";
        answer = 1403;
        assertEquals(answer, Day10_MonitoringStation.runB(input, new Coordinate(8,3), 36), "input = " + input + " answer: " + answer);
    }

    @Test
    void runB_verySmall() {
        String input;
        int answer;

        input = "#.#.#\n" +
                ".....\n" +
                "#.#.#\n" +
                ".....\n" +
                "#.#.#\n";
        answer = 1501;
        assertEquals(answer, Day10_MonitoringStation.runB(input, new Coordinate(2,2), 9), "input = " + input + " answer: " + answer);
    }

    @Test
    void calculateAngle() {
        Day10_MonitoringStation.AngleAndCoord angleAndCoord = new Day10_MonitoringStation.AngleAndCoord(new Coordinate(2, 0), new Coordinate(2, 2));
        assertEquals(0, angleAndCoord.angle);

        angleAndCoord = new Day10_MonitoringStation.AngleAndCoord(new Coordinate(4, 0), new Coordinate(2, 2));
        assertEquals(45, angleAndCoord.angle);

        angleAndCoord = new Day10_MonitoringStation.AngleAndCoord(new Coordinate(4, 2), new Coordinate(2, 2));
        assertEquals(90, angleAndCoord.angle);

        angleAndCoord = new Day10_MonitoringStation.AngleAndCoord(new Coordinate(4, 4), new Coordinate(2, 2));
        assertEquals(135, angleAndCoord.angle);

        angleAndCoord = new Day10_MonitoringStation.AngleAndCoord(new Coordinate(2, 4), new Coordinate(2, 2));
        assertEquals(180, angleAndCoord.angle);

        angleAndCoord = new Day10_MonitoringStation.AngleAndCoord(new Coordinate(0, 4), new Coordinate(2, 2));
        assertEquals(225, angleAndCoord.angle);

        angleAndCoord = new Day10_MonitoringStation.AngleAndCoord(new Coordinate(0, 2), new Coordinate(2, 2));
        assertEquals(270, angleAndCoord.angle);

        angleAndCoord = new Day10_MonitoringStation.AngleAndCoord(new Coordinate(0, 0), new Coordinate(2, 2));
        assertEquals(315, angleAndCoord.angle);
    }
}