package Days;

import java.util.Arrays;

public class Day1_Fuel {

    public static void main(String[] args) {
        System.out.println("answer A: " + runA(getInput()));
        System.out.println("answer B: " + runB(getInput()));
    }

    public static int runA(String input) {
        return Arrays.stream(input.split("\n"))
                .mapToInt(mass -> getFuelRequiredForMass(Integer.parseInt(mass)))
                .sum();
    }

    static Integer getFuelRequiredForMass(int mass) {
        return (mass / 3) -2;
    }

    public static int runB(String input) {
        return Arrays.stream(input.split("\n"))
                .mapToInt(Day1_Fuel::calculateTotalFuelForModuel)
                .sum();
    }


    static int calculateTotalFuelForModuel(String moduleMass) {
        // Get starting fuel
        int totalFuel = getFuelRequiredForMass(Integer.parseInt(moduleMass));
        int addedFuel = totalFuel;
        do {
            addedFuel = getFuelRequiredForMass(addedFuel);
            if(addedFuel > 0) {
                totalFuel += addedFuel;
            } else {
                break;
            }
        } while(true);

        return totalFuel;
    }

    public static String getInput() {
        return "138828\n" +
                "82053\n" +
                "75644\n" +
                "86659\n" +
                "85337\n" +
                "63842\n" +
                "88120\n" +
                "144319\n" +
                "58294\n" +
                "82233\n" +
                "81964\n" +
                "108059\n" +
                "117326\n" +
                "65553\n" +
                "105367\n" +
                "122086\n" +
                "70431\n" +
                "89418\n" +
                "108818\n" +
                "61254\n" +
                "97351\n" +
                "75645\n" +
                "88868\n" +
                "140241\n" +
                "112242\n" +
                "119866\n" +
                "86519\n" +
                "81313\n" +
                "60462\n" +
                "89313\n" +
                "134057\n" +
                "96984\n" +
                "57528\n" +
                "112293\n" +
                "94987\n" +
                "71785\n" +
                "104896\n" +
                "108760\n" +
                "68391\n" +
                "95901\n" +
                "112259\n" +
                "51337\n" +
                "75020\n" +
                "99526\n" +
                "132617\n" +
                "63797\n" +
                "121541\n" +
                "111211\n" +
                "70179\n" +
                "51681\n" +
                "60569\n" +
                "56247\n" +
                "55871\n" +
                "61344\n" +
                "56965\n" +
                "76208\n" +
                "137230\n" +
                "99499\n" +
                "109960\n" +
                "81224\n" +
                "93064\n" +
                "137496\n" +
                "111009\n" +
                "99572\n" +
                "135425\n" +
                "121418\n" +
                "79337\n" +
                "110813\n" +
                "87081\n" +
                "110898\n" +
                "71344\n" +
                "99419\n" +
                "139493\n" +
                "52847\n" +
                "112560\n" +
                "144685\n" +
                "79700\n" +
                "139438\n" +
                "127821\n" +
                "133377\n" +
                "82892\n" +
                "107311\n" +
                "118376\n" +
                "96197\n" +
                "66839\n" +
                "63551\n" +
                "145689\n" +
                "94461\n" +
                "93739\n" +
                "106350\n" +
                "67884\n" +
                "124828\n" +
                "141116\n" +
                "78967\n" +
                "143686\n" +
                "137159\n" +
                "85746\n" +
                "115543\n" +
                "73157\n" +
                "132343";
    }
}
