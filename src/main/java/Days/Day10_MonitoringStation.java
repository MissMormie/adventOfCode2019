package Days;

import helpers.Coordinate;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Day10_MonitoringStation {

    public static int runA(String input) { // x21, y20 -> 247 astroids
        List<Coordinate> coordinateList = getAsteroidCoord(input);
        Map<Integer, Coordinate> asteroidsVisibleFromCoord = new HashMap<>();
        coordinateList.stream().forEach(coord -> asteroidsVisibleFromCoord.put(getAstroidsVisibleFrom(coord, coordinateList), coord));

        return asteroidsVisibleFromCoord.keySet().stream().mapToInt(value -> value).max().getAsInt();
    }

    public static int getAstroidsVisibleFrom(Coordinate baseCoord, List<Coordinate> coordinateList) {
        Map<Coordinate, SlopeForCoord> slopes = new HashMap<>();
        coordinateList.stream()
                .filter(coord -> baseCoord != coord)
                .forEach(coord -> slopes.put(coord, new SlopeForCoord(baseCoord, coord)));

        return new HashSet<>(slopes.values()).size();
    }

    public static class AngleAndCoord {
        public double angle;
        public Coordinate coordinate;

        public AngleAndCoord(Coordinate coordinate, Coordinate baseCoordinate) {
            Coordinate helper = new Coordinate(coordinate.x - baseCoordinate.x, coordinate.y - baseCoordinate.y);
            this.coordinate = coordinate;
            double localAngle = getAngle(helper);
            if (helper.x == 0 && helper.y < 0) {
                this.angle = 0;
                return;
            }
            if (helper.y <= 0 && helper.x > 0) { // 0 until 90
                this.angle = localAngle;
                return;
            }
            if (helper.y > 0 && helper.x >= 0) { // 90 until 180
                this.angle = 180 - localAngle;
                return;
            }

            if (helper.y >= 0 && helper.x < 0) { // 180 until 270
                this.angle = 180 + localAngle;
                return;
            } else {
                this.angle = 360 - localAngle; // 270 and 360
            }

        }

        private double getAngle(Coordinate helper) {
            int overstaandeZijde = Math.abs(helper.x);
            int aanliggendeZijde = Math.abs(helper.y);
            double tangens = overstaandeZijde * 1.0 / aanliggendeZijde;
            return Math.toDegrees(Math.atan(tangens));
        }
    }

    public static class SlopeForCoord {
        public double slope;
        public Coordinate coordinate;
        public boolean yPositive;
        int quadrant;

        public SlopeForCoord(Coordinate base, Coordinate coordinate) {
            Coordinate helper = new Coordinate(coordinate.x - base.x, coordinate.y - base.y);
            slope = (helper.y * 1.0) / helper.x * -1; // slopes are calculated wrong.
            yPositive = helper.y >= 0;
            this.coordinate = coordinate;
            quadrant = calculateQuadrant();
            if (base.y == coordinate.y && base.x > coordinate.x) {
                quadrant = 4;
            }

        }

        public int getQuadrant() {
            return quadrant;
        }

        public int calculateQuadrant() {
            if (slope >= 0 && !yPositive) return 1;
            if (slope <= 0 && yPositive) return 2;
            if (slope > 0 && yPositive) return 3;
            return 4;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            SlopeForCoord that = (SlopeForCoord) o;
            return Double.compare(that.slope, slope) == 0 &&
                    yPositive == that.yPositive;
        }

        @Override
        public int hashCode() {
            return Objects.hash(slope, yPositive);
        }
    }

    public static List<Coordinate> getAsteroidCoord(String input) {
        List<Coordinate> coordinates = new ArrayList<>();
        String[] split = input.split("\n");
        IntStream.range(0, split.length).forEach(line -> {
            String[] pointArray = split[line].split("");
            IntStream.range(0, pointArray.length).forEach(point -> {
                if (!pointArray[point].equals(".")) { // Asteroid
                    coordinates.add(new Coordinate(point, line));
                }
            });
        });
        return coordinates;
    }

    /**
     * @param baseCoord Coordinate where the base is build
     * @return
     */
    public static int runB(String input, Coordinate baseCoord, int astroidNum) {

        // Get all astroids and accompanying slopes from baseCoord.
        List<Coordinate> coordinateList = getAsteroidCoord(input);
        coordinateList.remove(baseCoord);
        Map<SlopeForCoord, List<Coordinate>> slopesMap = new HashMap<>();
        coordinateList.stream()
                .filter(coord -> baseCoord != coord)
                .forEach(coord -> {
                    SlopeForCoord slopeForCoord = new SlopeForCoord(baseCoord, coord);
                    if (!slopesMap.containsKey(slopeForCoord)) {
                        slopesMap.put(slopeForCoord, new ArrayList<>());
                    }
                    slopesMap.get(slopeForCoord).add(coord);
                });

        // Sort all lists per slope in order of visibility.
        slopesMap.values().stream().forEach(asteroidsInSlope -> asteroidsInSlope.sort(new Comparator<Coordinate>() {
            @Override
            public int compare(Coordinate o1, Coordinate o2) {
                return o1.getManhattanDistance(baseCoord) - o2.getManhattanDistance(baseCoord);
            }
        }));


        // Why is this in my code? What does it do? What was I thinking?
//        List<Coordinate> collect = slopes.entrySet().stream()
//                .filter(b -> b.getKey().quadrant == 4)
//                .map(b -> b.getValue())
//                .flatMap(Collection::stream)
//                .filter(a -> a.x > 16 && a.x < 21)
//                .collect(Collectors.toList());


        List<SlopeForCoord> orderedSlopes = slopesMap.keySet().stream().collect(Collectors.toList());
        orderedSlopes.sort(new Comparator<SlopeForCoord>() {
            @Override
            public int compare(SlopeForCoord slope1, SlopeForCoord slope2) {
                if (slope1.getQuadrant() != slope2.getQuadrant()) {
                    return slope1.getQuadrant() - slope2.getQuadrant();
                }
                return slope1.slope > slope2.slope ? -1 : 1;
            }
        });


        // Remove astroids one by one from ordered lists.
        int asteroidRemoved = 0;
        while (slopesMap.size() > 0) {
            for (SlopeForCoord slope : orderedSlopes) {
                // If there's nothing left, just move on.
                if (!slopesMap.containsKey(slope)) {
                    continue;
                }

                Coordinate removeCoordinate = slopesMap.get(slope).remove(0);// remove first coord in slope
                input = printMap(input, removeCoordinate);
                asteroidRemoved += 1;
                if (asteroidRemoved == astroidNum) {
                    return removeCoordinate.x * 100 + removeCoordinate.y;
                }

                // No more coords in this slope
                if (slopesMap.get(slope).size() == 0) {
                    slopesMap.remove(slope);
                }

                System.out.println("Astroid " + asteroidRemoved + " vaporized: " + removeCoordinate );
            }

        }
        return 0;
    }


    public static void runbAgain(String input, Coordinate baseCoord, int astroidNum) {
        List<Coordinate> coordinateList = getAsteroidCoord(input);
        coordinateList.remove(baseCoord);

        Map<Double, List<Coordinate>> angleDoubleMap = new HashMap<>();
        coordinateList.stream()
                .forEach(coord -> {
                    AngleAndCoord slopeForCoord = new AngleAndCoord(baseCoord, coord);
                    if (!angleDoubleMap.containsKey(slopeForCoord.angle)) {
                        angleDoubleMap.put(slopeForCoord.angle, new ArrayList<>());
                    }
                    angleDoubleMap.get(slopeForCoord.angle).add(coord);
                });

        List<Double> listOfAngles = angleDoubleMap.keySet().stream().collect(Collectors.toList());
        Collections.sort(listOfAngles);
    }


    public static double getAngle(Coordinate coordinate, Coordinate baseCoordinate) {
        Coordinate helper = new Coordinate(coordinate.x - baseCoordinate.x, coordinate.y - baseCoordinate.y);
        double localAngle = calculateAngle(helper);
        if (helper.x == 0 && helper.y < 0) {
            return 0;
        }
        if (helper.y <= 0 && helper.x > 0) { // 0 until 90
            return localAngle;
        }
        if (helper.y > 0 && helper.x >= 0) { // 90 until 180
            return 180 - localAngle;
        }
        if (helper.y >= 0 && helper.x < 0) { // 180 until 270
            return 180 + localAngle;
        }
        return 360 - localAngle; // 270 and 360

    }

    private static double calculateAngle(Coordinate helper) {
        int overstaandeZijde = Math.abs(helper.x);
        int aanliggendeZijde = Math.abs(helper.y);
        double tangens = overstaandeZijde * 1.0 / aanliggendeZijde;
        return Math.toDegrees(Math.atan(tangens));
    }

    public static String printMap(String input, Coordinate remove) {
        input = input.replace('O', '*');
        StringBuilder newInput = new StringBuilder(input);
        int lengthOfLine = input.indexOf("\n") + 1;
        int replace = lengthOfLine * (remove.y) + remove.x;
//        replace = 21;
        newInput.setCharAt(replace, 'O');
        System.out.println(newInput.toString());
        return newInput.toString();
    }

    public static void main(String[] args) {
        long startTime = System.nanoTime();
        System.out.println("answer A: " + runA(getInput()));
        long endTime = System.nanoTime();
        long duration = (endTime - startTime);
        System.out.println(" took this amount of milliSeconds: " + duration / 1000000.0);

        startTime = System.nanoTime();
        System.out.println("answer B: " + runB(getInput(), new Coordinate(20, 21), 200));
        endTime = System.nanoTime();
        duration = (endTime - startTime);
        System.out.println(" took this amount of milliSeconds: " + duration / 1000000.0);
    }

    public static String getInput() {
        return "#..#.#.###.#...##.##....\n" +
                ".#.#####.#.#.##.....##.#\n" +
                "##..#.###..###..#####..#\n" +
                "####.#.#..#....#..##.##.\n" +
                ".#######.#####...#.###..\n" +
                ".##...#.#.###..###.#.#.#\n" +
                ".######.....#.###..#....\n" +
                ".##..##.#..#####...###.#\n" +
                "#######.#..#####..#.#.#.\n" +
                ".###.###...##.##....##.#\n" +
                "##.###.##.#.#..####.....\n" +
                "#.#..##..#..#.#..#####.#\n" +
                "#####.##.#.#.#.#.#.#..##\n" +
                "#...##.##.###.##.#.###..\n" +
                "####.##.#.#.####.#####.#\n" +
                ".#..##...##..##..#.#.##.\n" +
                "###...####.###.#.###.#.#\n" +
                "..####.#####..#####.#.##\n" +
                "..###..###..#..##...#.#.\n" +
                "##.####...##....####.##.\n" +
                "####..#..##.#.#....#..#.\n" +
                ".#..........#..#.#.####.\n" +
                "###..###.###.#.#.#....##\n" +
                "########.#######.#.##.##";
    }
}
