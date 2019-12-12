package Days;

import helpers.Coordinate3D;
import helpers.Moon;
import helpers.Moon.MoonPair;

import java.math.BigInteger;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Day12_Orbits_NBodyProblem {

    public static int runA(String input, int numOfTicks) {
        List<Moon> moons = getMoons(input);
        List<MoonPair> pairsOfMoons = createPairsOfMoon(moons);

        startTicks(moons, pairsOfMoons, numOfTicks);

        return moons.stream().mapToInt(Moon::getEnergy).sum();
    }

    public static void startTicks(List<Moon> moons, List<MoonPair> pairsOfMoons , int numOfTicks) {
        IntStream.range(0, numOfTicks).forEach(i -> {
            // Calculate effects of gravity of all objects.
            doTick(moons, pairsOfMoons);

        });
    }

    private static void doTick(List<Moon> moons, List<MoonPair> moonPairs) {
        moonPairs.stream().forEach(moonPair -> {
            moonPair.firstMoon.calculateGravityInfluenceFrom(moonPair.secondMoon);
            moonPair.secondMoon.calculateGravityInfluenceFrom(moonPair.firstMoon);
        });

        // Apply effects of gravity for all objects.
        moons.stream().forEach(Moon::applyVelocity);
    }

    public static List<MoonPair> createPairsOfMoon(List<Moon> moons) {
        // Yeah i could do something smart,  but there's so little stuff that i'll do it by hand.
        // So i should've done something smart, took me forever to find I made a stupid mistake here.
        // Leaving this comment for lolz.
        List<MoonPair> moonPairs = new ArrayList<>();

        moonPairs.add(new MoonPair(moons.get(0), moons.get(1)));
        moonPairs.add(new MoonPair(moons.get(0), moons.get(2)));
        moonPairs.add(new MoonPair(moons.get(0), moons.get(3)));
        moonPairs.add(new MoonPair(moons.get(1), moons.get(2)));
        moonPairs.add(new MoonPair(moons.get(1), moons.get(3)));
        moonPairs.add(new MoonPair(moons.get(2), moons.get(3)));

        moons.get(0).name = "Ganymede";
        moons.get(1).name = "Io";
        moons.get(2).name = "Callisto";
        moons.get(3).name = "Europe";

        return moonPairs;
    }

    public static List<Moon> getMoons(String input) {
        return Arrays.stream(input.split("\n")).map(Day12_Orbits_NBodyProblem::createMoon).collect(Collectors.toList());
    }

    public static Moon createMoon(String input) {
        Coordinate3D position = new Coordinate3D(0, 0, 0);
        input = input.replace(">", "");
        String[] s = input.split(", ");
        position.x = Integer.parseInt(s[0].substring(s[0].indexOf('=') +1 ));
        position.y = Integer.parseInt(s[1].substring(s[1].indexOf('=') +1 ));
        position.z = Integer.parseInt(s[2].substring(s[2].indexOf('=') +1 ));
        Coordinate3D velocity = new Coordinate3D(0,0,0);

        return new Moon(position,velocity);

    }

    public static BigInteger runB(String input) {
        List<Moon> moons = getMoons(input);
        List<MoonPair> pairsOfMoons = createPairsOfMoon(moons);

        BigInteger tick = new BigInteger("0");

        // variables to solve x
        Map<String, BigInteger> xPositions = new HashMap<>();
        BigInteger xStartRepeating = null;
        BigInteger xStepsBeforeRepeat = null;
        boolean xSolved = false;

        // variables to solve y
        Map<String, BigInteger> yPositions = new HashMap<>();
        BigInteger yStartRepeating = null;
        BigInteger yStepsBeforeRepeat = null;
        boolean ySolved = false;

        // variables to solve z
        Map<String, BigInteger> zPositions = new HashMap<>();
        BigInteger zStartRepeating = null;
        BigInteger zStepsBeforeRepeat = null;
        boolean zSolved = false;

        while(!xSolved || !ySolved || !zSolved) {

            // Check if x repeats
            String xPosition = getXposition(moons);
            if (!xSolved && !xPositions.containsKey(xPosition)) {
                xPositions.put(xPosition, tick);
            } else if (!xSolved) {// found first repeater
                xStartRepeating = xPositions.get(xPosition);
                xStepsBeforeRepeat = tick.subtract(xStartRepeating);
                xSolved = true;
            }

            // Check if y repeats
            String yPosition = getYpositions(moons);
            if (!ySolved && !yPositions.containsKey(yPosition)) {
                yPositions.put(yPosition, tick);
            } else if (!ySolved) {// found first repeater
                yStartRepeating = yPositions.get(yPosition);
                yStepsBeforeRepeat = tick.subtract(yStartRepeating);
                ySolved = true;
            }

            // Check if z repeats
            String zPosition = getZpositions(moons);
            if (!zSolved && !zPositions.containsKey(zPosition)) {
                zPositions.put(zPosition, tick);
            } else if (!zSolved) {// found first repeater
                zStartRepeating = zPositions.get(zPosition);
                zStepsBeforeRepeat = tick.subtract(zStartRepeating);
                zSolved = true;
            }

            doTick(moons, pairsOfMoons);
            tick = tick.add(new BigInteger("1"));
        }

        return getLowestCommonMultiplyer(xStepsBeforeRepeat, yStepsBeforeRepeat, zStepsBeforeRepeat);
    }

    public static BigInteger getLowestCommonMultiplyer(BigInteger x, BigInteger y, BigInteger z) {
        BigInteger lcm1 = lowestCommonMultiplyer(x, y);
        BigInteger lcm2 = lowestCommonMultiplyer(y, z);
        return lowestCommonMultiplyer(lcm1, lcm2);

    }

    public static BigInteger lowestCommonMultiplyer(BigInteger number1, BigInteger number2) {
        BigInteger gcd = number1.gcd(number2);
        BigInteger absProduct = number1.multiply(number2).abs();
        return absProduct.divide(gcd);
    }

    public static String getXposition(List<Moon> moons) {
        StringBuilder sb = new StringBuilder();
        moons.stream().map(moon -> Integer.toString(moon.position.x)).forEach(s -> sb.append(s + "."));
        moons.stream().map(moon -> Integer.toString(moon.velocity.x)).forEach(s -> sb.append(s + "."));
        return sb.toString();
    }

    public static String getYpositions(List<Moon> moons) {
        StringBuilder sb = new StringBuilder();
        moons.stream().map(moon -> Integer.toString(moon.position.y)).forEach(s -> sb.append(s));
        moons.stream().map(moon -> Integer.toString(moon.velocity.y)).forEach(s -> sb.append(s));
        return sb.toString();
    }

    public static String getZpositions(List<Moon> moons) {
        StringBuilder sb = new StringBuilder();
        moons.stream().map(moon -> Integer.toString(moon.position.z)).forEach(s -> sb.append(s));
        moons.stream().map(moon -> Integer.toString(moon.velocity.z)).forEach(s -> sb.append(s));
        return sb.toString();
    }

    public static void main(String[] args) {
        long startTime = System.nanoTime();
        System.out.println("answer A: " + runA(getInput(), 1000));
        long endTime = System.nanoTime();
        long duration = (endTime - startTime);
        System.out.println(" took this amount of milliSeconds: " + duration / 1000000.0);


        startTime = System.nanoTime();
        System.out.println("answer B: " + runB(getInput()));
        endTime = System.nanoTime();
        duration = (endTime - startTime);
        System.out.println(" took this amount of milliSeconds: " + duration / 1000000.0);
    }

    public static String getInput() {
        return "<x=13, y=9, z=5>\n" +
                "<x=8, y=14, z=-2>\n" +
                "<x=-5, y=4, z=11>\n" +
                "<x=2, y=-6, z=1>";
    }
}
