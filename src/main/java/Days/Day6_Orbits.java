package Days;

import java.util.*;

public class Day6_Orbits {

    public static void main(String[] args) {
        long startTime = System.nanoTime();
        System.out.println("answer A: " + runA(getInput()));
        long endTime = System.nanoTime();
        long duration = (endTime - startTime);
        System.out.println(" took this amount of milliSeconds: " + duration / 1000000.0);


        startTime = System.nanoTime();
        System.out.println("answer B: " + runB(getInput()));
        endTime = System.nanoTime();
        duration = (endTime - startTime);
        System.out.println(" took this amount of milliSeconds: " + duration / 1000000.0);
    }

    public static int runA(String input) {
        Map<String, List<String>> orbitMap = createOrbitMap(input);

        // Universal centre of Mass.
        return calculateOrbits(orbitMap, "COM", 0);
    }

    private static Map<String, List<String>> createOrbitMap(String input) {
        // Create map with orbits.
        Map<String, List<String>> orbitMap = new HashMap<>();
        Arrays.stream(input.split("\n")).forEach(
                orbit -> {
                    String[] split = orbit.split("\\)");
                    if(!orbitMap.containsKey(split[0])) {
                        orbitMap.put(split[0], new ArrayList<>());
                    }
                    orbitMap.get(split[0]).add(split[1]);
                }
        );
        return orbitMap;
    }

    public static int calculateOrbits( Map<String, List<String>> orbitMap, String object, int steps) {
        if(!orbitMap.containsKey(object)) {
            return steps;
        }

        return orbitMap.get(object).stream()
                .mapToInt(nextObject -> calculateOrbits(orbitMap, nextObject, steps + 1))
                .sum() + steps;
    }

    public static int runB(String input) {
        Map<String, String> orbitMap = new HashMap<>();
        Arrays.stream(input.split("\n")).forEach(
                orbit -> {
                    String[] split = orbit.split("\\)");
                    orbitMap.put(split[1], split[0]);
                }
        );

        List<String> santasRouteToCentre = getRouteToCentreOfMass(orbitMap, "SAN", new ArrayList<>());
        List<String> yourRouteToCentre = getRouteToCentreOfMass(orbitMap, "YOU", new ArrayList<>());

        Collections.reverse(santasRouteToCentre);
        Collections.reverse(yourRouteToCentre);

        // DON'T FORGET TO ADD THE ANSWER
        while(santasRouteToCentre.get(0).equals(yourRouteToCentre.get(0))) {
            santasRouteToCentre.remove(0);
            yourRouteToCentre.remove(0);
        }

        return santasRouteToCentre.size() + yourRouteToCentre.size();
    }

    private static List<String> getRouteToCentreOfMass(Map<String, String> orbitMap, String key, List<String> objects) {
        while(orbitMap.containsKey(key)) {
            key = orbitMap.get(key);
            objects.add(key);
        }
        return objects;
    }

    public static String getInput() {
        return "J1C)J1M\n" +
                "N2W)2DM\n" +
                "DST)VZL\n" +
                "555)45Q\n" +
                "S4C)DGK\n" +
                "H51)JLX\n" +
                "K4L)3F5\n" +
                "L58)T9K\n" +
                "GBC)NZT\n" +
                "L7B)CQB\n" +
                "L7L)R63\n" +
                "4LQ)KHZ\n" +
                "XWW)L61\n" +
                "R6W)DVN\n" +
                "91C)GPM\n" +
                "YWZ)51W\n" +
                "XK8)22K\n" +
                "NV2)DTP\n" +
                "5LQ)F4V\n" +
                "4H9)R4B\n" +
                "29X)R9H\n" +
                "1G5)W91\n" +
                "TZL)S4C\n" +
                "1Z7)1MY\n" +
                "N7V)1Z4\n" +
                "7YX)LQ9\n" +
                "PK9)D1Y\n" +
                "QVK)K1H\n" +
                "4FT)3X5\n" +
                "5M5)29X\n" +
                "5XH)H5K\n" +
                "LGK)V51\n" +
                "HMN)LNG\n" +
                "5QX)KHJ\n" +
                "797)TJR\n" +
                "SRV)4RT\n" +
                "1R4)MSZ\n" +
                "3SJ)XRT\n" +
                "X6N)J1N\n" +
                "WM8)LH2\n" +
                "KGZ)5ZP\n" +
                "ZY5)9GY\n" +
                "H5K)2LX\n" +
                "4QD)1ND\n" +
                "JDK)WRX\n" +
                "KSR)4LD\n" +
                "8Q3)W8B\n" +
                "51W)QZT\n" +
                "HSQ)CV5\n" +
                "35F)VW2\n" +
                "8Q9)845\n" +
                "8FS)XB4\n" +
                "WFX)N2F\n" +
                "BDG)G3G\n" +
                "Q7S)K1X\n" +
                "1MY)PCB\n" +
                "FRW)TZL\n" +
                "N78)Z9K\n" +
                "J4F)98D\n" +
                "4J2)6S3\n" +
                "9QY)1HG\n" +
                "RRX)SW4\n" +
                "DH7)CLW\n" +
                "M8W)4QD\n" +
                "9K9)1NV\n" +
                "KHW)T14\n" +
                "DQC)ZCG\n" +
                "R4H)MPG\n" +
                "FY9)HXB\n" +
                "7SF)J1C\n" +
                "SPT)P66\n" +
                "BB6)XZR\n" +
                "7MR)MDH\n" +
                "M4G)DBH\n" +
                "7RP)BHH\n" +
                "2H8)QKS\n" +
                "RL2)8ZM\n" +
                "598)68Q\n" +
                "MPG)WWF\n" +
                "BWN)J2D\n" +
                "FRF)JJM\n" +
                "QFJ)57P\n" +
                "NSS)BVH\n" +
                "3SM)HTP\n" +
                "N1K)SYL\n" +
                "33P)TY2\n" +
                "WWF)7SK\n" +
                "4V3)6PT\n" +
                "4LD)13S\n" +
                "9HN)CTC\n" +
                "Q87)48X\n" +
                "7MB)96M\n" +
                "6S3)B65\n" +
                "KFJ)5YL\n" +
                "DGK)TS5\n" +
                "2C3)YL7\n" +
                "M79)GRM\n" +
                "BRG)41X\n" +
                "GPJ)YLW\n" +
                "8FS)2LC\n" +
                "SL5)G5L\n" +
                "2MW)RHN\n" +
                "DDZ)J4F\n" +
                "ZQZ)4LQ\n" +
                "FZF)Z6X\n" +
                "XF1)JWS\n" +
                "B7C)6DM\n" +
                "GN9)N7V\n" +
                "F5K)2JX\n" +
                "7QR)FCC\n" +
                "3DG)2X7\n" +
                "L9C)RMW\n" +
                "XKM)26Q\n" +
                "FPK)5VT\n" +
                "RGQ)XF1\n" +
                "174)79T\n" +
                "1CP)KVB\n" +
                "X5Y)WWH\n" +
                "M1K)2X2\n" +
                "L53)76G\n" +
                "5MS)YX8\n" +
                "8P2)DPP\n" +
                "LMJ)B6X\n" +
                "NKD)24R\n" +
                "LH2)X86\n" +
                "D4N)CCC\n" +
                "1R2)N5J\n" +
                "LGF)YCQ\n" +
                "YL7)Y93\n" +
                "NCG)7NJ\n" +
                "RR2)MNR\n" +
                "24R)FPK\n" +
                "H62)RC8\n" +
                "HRX)H53\n" +
                "ZKS)N86\n" +
                "B95)RS5\n" +
                "Z8Y)QY7\n" +
                "J21)YL8\n" +
                "T32)FL9\n" +
                "92L)PLW\n" +
                "41X)YLV\n" +
                "8NY)DQC\n" +
                "3FS)VVC\n" +
                "2F2)W4C\n" +
                "PH4)WGK\n" +
                "TS5)4WQ\n" +
                "YMS)RHC\n" +
                "FW6)PQG\n" +
                "L3F)RV3\n" +
                "6XN)KSR\n" +
                "BZ3)88B\n" +
                "QJ6)LTG\n" +
                "BQJ)7J3\n" +
                "B5P)L22\n" +
                "MWG)QQ8\n" +
                "R4B)MGY\n" +
                "G5L)46V\n" +
                "XNW)3JL\n" +
                "QY7)HZV\n" +
                "2HK)J38\n" +
                "Y8W)WQV\n" +
                "GBT)L7L\n" +
                "SZP)87F\n" +
                "FN6)RP1\n" +
                "43G)4H9\n" +
                "TTZ)ZP2\n" +
                "NV2)F3M\n" +
                "Y3J)9K9\n" +
                "87F)8HN\n" +
                "RXZ)PH4\n" +
                "5YL)QQQ\n" +
                "S5Q)388\n" +
                "RJR)5MS\n" +
                "DBV)7FQ\n" +
                "D7F)QQ9\n" +
                "VG5)N78\n" +
                "JSD)6YZ\n" +
                "C7B)TRM\n" +
                "KVB)P7C\n" +
                "HL7)WL3\n" +
                "HNW)75P\n" +
                "Z9K)H51\n" +
                "Z4V)43G\n" +
                "G2D)Q6D\n" +
                "C94)DBV\n" +
                "6MK)FYV\n" +
                "Z4X)P5K\n" +
                "T9M)D7F\n" +
                "D4B)PNW\n" +
                "LB3)JDK\n" +
                "F3M)LPV\n" +
                "NJN)Y18\n" +
                "FV7)HSQ\n" +
                "XRT)YQG\n" +
                "4ML)RJR\n" +
                "6YZ)2N3\n" +
                "JCG)HSX\n" +
                "6MN)5LQ\n" +
                "TX5)JXH\n" +
                "596)NRC\n" +
                "2LC)KMP\n" +
                "3ML)THS\n" +
                "BHH)ZQZ\n" +
                "WLK)62P\n" +
                "WSB)BBL\n" +
                "ZBT)J1T\n" +
                "R1F)DS2\n" +
                "KBK)BY4\n" +
                "BWN)ZW6\n" +
                "HPP)3SZ\n" +
                "STM)BRG\n" +
                "WFX)XGR\n" +
                "2X2)2HK\n" +
                "V51)V9K\n" +
                "8LF)Q6N\n" +
                "2RB)M6G\n" +
                "HMW)NKD\n" +
                "R7R)9VS\n" +
                "L2Z)JFZ\n" +
                "W8B)Z99\n" +
                "PCG)4SK\n" +
                "4YG)JHN\n" +
                "WC8)N5C\n" +
                "798)J6P\n" +
                "4XX)MZT\n" +
                "7K7)XJY\n" +
                "8QZ)111\n" +
                "QZT)551\n" +
                "HQC)7VS\n" +
                "D5Y)JGF\n" +
                "X3X)ZHM\n" +
                "8SD)TVZ\n" +
                "Y67)N44\n" +
                "1S1)P2G\n" +
                "1W4)4BH\n" +
                "RH1)8NV\n" +
                "9D6)4W1\n" +
                "NJN)L2Z\n" +
                "JXH)1CP\n" +
                "4L7)3C7\n" +
                "3SZ)YL5\n" +
                "TF4)53L\n" +
                "96V)KKD\n" +
                "8TS)NCJ\n" +
                "C8W)JHB\n" +
                "GWZ)WDM\n" +
                "1PL)CLT\n" +
                "QJX)VDD\n" +
                "R63)BJT\n" +
                "1Z4)HHF\n" +
                "VNM)STM\n" +
                "Y7C)3BP\n" +
                "NQM)9V2\n" +
                "WTK)NKM\n" +
                "PQZ)584\n" +
                "26Q)BZ3\n" +
                "L6Q)J23\n" +
                "W7Z)RXZ\n" +
                "ZHM)PBW\n" +
                "HFS)9CB\n" +
                "VVY)822\n" +
                "64W)27N\n" +
                "SPS)5ZD\n" +
                "JJM)TW4\n" +
                "M5K)7FM\n" +
                "5ZY)X3Q\n" +
                "5VT)SYG\n" +
                "QP4)Z4S\n" +
                "9V2)FRW\n" +
                "DTP)VVY\n" +
                "3YF)4NN\n" +
                "9GY)5MV\n" +
                "WH4)1R2\n" +
                "2CC)1WL\n" +
                "39N)MR4\n" +
                "X21)6MN\n" +
                "LTG)64W\n" +
                "RDK)FCQ\n" +
                "NQ6)7J6\n" +
                "K7Y)8S7\n" +
                "M79)PFH\n" +
                "C21)WTK\n" +
                "3X5)HWM\n" +
                "987)BTG\n" +
                "HYH)Y8L\n" +
                "XRT)LPK\n" +
                "JYS)HFS\n" +
                "S4G)6D7\n" +
                "N37)356\n" +
                "D9F)MWG\n" +
                "P43)F8L\n" +
                "RHC)P3S\n" +
                "MWG)NSZ\n" +
                "ST7)HYY\n" +
                "Z4S)RRX\n" +
                "D39)XJR\n" +
                "Y3L)4XX\n" +
                "5X2)79C\n" +
                "WL1)7Q5\n" +
                "KYS)RW4\n" +
                "Q87)74P\n" +
                "YDN)HNN\n" +
                "3XH)4LW\n" +
                "596)VWL\n" +
                "RW4)RCF\n" +
                "WCG)CNL\n" +
                "S9D)GHQ\n" +
                "G31)DST\n" +
                "9VY)WC8\n" +
                "4WQ)MLF\n" +
                "388)Y9Y\n" +
                "845)R7H\n" +
                "PWR)M2T\n" +
                "L61)4GP\n" +
                "LML)L3F\n" +
                "H1H)GZQ\n" +
                "C21)DLS\n" +
                "XB4)L53\n" +
                "N5J)NQM\n" +
                "THS)CSB\n" +
                "XGR)QCB\n" +
                "SPT)4FT\n" +
                "7FQ)WMP\n" +
                "13S)8QZ\n" +
                "BVH)WMC\n" +
                "GSN)7CQ\n" +
                "ZDY)ZBD\n" +
                "T2Q)5J3\n" +
                "J6P)R6W\n" +
                "N5C)4H7\n" +
                "KKD)YKQ\n" +
                "KBJ)P9J\n" +
                "14B)K27\n" +
                "N9F)HYS\n" +
                "2LG)G2D\n" +
                "3C7)S2G\n" +
                "HB9)XT1\n" +
                "QQX)K1K\n" +
                "47H)14B\n" +
                "48Q)FQW\n" +
                "STP)PWR\n" +
                "NWW)ZY5\n" +
                "63F)FRL\n" +
                "4W1)8G8\n" +
                "7W6)CVN\n" +
                "RPS)4NP\n" +
                "9HN)1MR\n" +
                "M96)454\n" +
                "8ZM)RVB\n" +
                "XVT)7YX\n" +
                "M6G)WVP\n" +
                "ZPR)BNN\n" +
                "Q83)6SS\n" +
                "FM2)YMS\n" +
                "P7R)59B\n" +
                "WTB)7RP\n" +
                "YT1)D27\n" +
                "PW6)QBQ\n" +
                "J2D)L9L\n" +
                "8G8)8Q3\n" +
                "L12)GBC\n" +
                "FQW)2RB\n" +
                "Z8T)D1X\n" +
                "HYS)QQX\n" +
                "63F)72B\n" +
                "34Z)DSG\n" +
                "2GK)3XH\n" +
                "F5K)LD1\n" +
                "DQ3)QVK\n" +
                "SL5)88N\n" +
                "XWP)7SF\n" +
                "NSZ)47H\n" +
                "1Z2)NT6\n" +
                "FYV)M7W\n" +
                "KHJ)91C\n" +
                "RKZ)FS3\n" +
                "P7C)GW6\n" +
                "BYN)33X\n" +
                "QZN)HRX\n" +
                "7J5)L6X\n" +
                "2X8)QH4\n" +
                "KHZ)BB6\n" +
                "NCJ)9FL\n" +
                "JHN)Y67\n" +
                "FC5)BYN\n" +
                "GRM)CYQ\n" +
                "NST)3SJ\n" +
                "VZL)3LQ\n" +
                "1VH)XKM\n" +
                "LQZ)YD8\n" +
                "CD5)LGK\n" +
                "32Y)3V7\n" +
                "CTH)56S\n" +
                "K27)VNM\n" +
                "4NP)C7B\n" +
                "VDK)DR4\n" +
                "ZGY)848\n" +
                "ZNL)M96\n" +
                "WVP)91F\n" +
                "RHC)2D1\n" +
                "53L)YXF\n" +
                "CCC)L9C\n" +
                "81J)K36\n" +
                "CCM)2HF\n" +
                "6LG)CWR\n" +
                "98D)S2R\n" +
                "N2F)1W4\n" +
                "DMP)DMT\n" +
                "BGS)2MP\n" +
                "BHD)D5Y\n" +
                "7VS)6LG\n" +
                "WSN)6SD\n" +
                "HYP)1CV\n" +
                "K58)FLX\n" +
                "2X7)Y8X\n" +
                "Q6D)Z4V\n" +
                "RCF)MTW\n" +
                "J5B)F5K\n" +
                "5V1)CP9\n" +
                "CSQ)ZC8\n" +
                "J9F)WQW\n" +
                "8NV)BSQ\n" +
                "8HV)S7S\n" +
                "ZP2)3MZ\n" +
                "1CY)WCG\n" +
                "RMW)HCL\n" +
                "46V)J5B\n" +
                "9QF)JYP\n" +
                "LPV)LQZ\n" +
                "K6M)ZTH\n" +
                "MZQ)VDM\n" +
                "N16)CD5\n" +
                "Y5W)GX3\n" +
                "XQC)QJ6\n" +
                "7CQ)CSF\n" +
                "79C)X23\n" +
                "W7G)798\n" +
                "J23)LSZ\n" +
                "5J3)K4L\n" +
                "67H)WM3\n" +
                "GJF)LMX\n" +
                "TJR)YLG\n" +
                "3LQ)29L\n" +
                "BY4)63F\n" +
                "YL5)MPJ\n" +
                "BZV)951\n" +
                "RJ1)VG5\n" +
                "RTY)KS9\n" +
                "51W)BDG\n" +
                "P66)H62\n" +
                "Y89)JT5\n" +
                "KM7)VPJ\n" +
                "CSF)9HN\n" +
                "X89)G9X\n" +
                "G9X)J9F\n" +
                "CTC)32Y\n" +
                "FLX)FWV\n" +
                "6PN)HMN\n" +
                "V8Y)ST7\n" +
                "ZZ3)2FT\n" +
                "J1T)JMT\n" +
                "FH7)PW6\n" +
                "Y93)CMF\n" +
                "DK2)LB3\n" +
                "SWN)WSB\n" +
                "Z6R)QS4\n" +
                "7LB)29W\n" +
                "5ZX)96V\n" +
                "D1X)KFJ\n" +
                "X23)9F5\n" +
                "2FT)PR3\n" +
                "YX8)HL7\n" +
                "NFY)ZZ3\n" +
                "756)Y6V\n" +
                "D2C)LP3\n" +
                "1MH)1HR\n" +
                "TFS)NFY\n" +
                "CMF)271\n" +
                "1PS)QVR\n" +
                "J1M)FW6\n" +
                "9QF)GYC\n" +
                "D27)SWN\n" +
                "WMC)2CC\n" +
                "J38)RPY\n" +
                "1HG)1MF\n" +
                "5LQ)B43\n" +
                "N4P)ZPS\n" +
                "PR3)FNP\n" +
                "4H7)T9M\n" +
                "3BR)SC6\n" +
                "K1X)GPR\n" +
                "9TP)948\n" +
                "YQG)BRK\n" +
                "JYP)S4G\n" +
                "D1Y)4J2\n" +
                "Y89)RG2\n" +
                "DVQ)XY9\n" +
                "DMT)2MJ\n" +
                "K27)TY1\n" +
                "88B)YDT\n" +
                "43N)HB9\n" +
                "MGY)1MH\n" +
                "YFR)CTH\n" +
                "74N)JYS\n" +
                "X86)JCG\n" +
                "948)GY2\n" +
                "7MR)5RL\n" +
                "PZL)GR4\n" +
                "32Y)QZN\n" +
                "K1T)987\n" +
                "5ZD)644\n" +
                "7X3)9R8\n" +
                "WGK)XQC\n" +
                "DLS)2X8\n" +
                "RHN)T2Q\n" +
                "GR4)1SJ\n" +
                "6DM)L7B\n" +
                "4NP)JBQ\n" +
                "KS9)YJX\n" +
                "YLG)X21\n" +
                "G1S)1R4\n" +
                "5ZP)8HV\n" +
                "D1Y)K7Y\n" +
                "8MW)WY3\n" +
                "QJX)X75\n" +
                "GY2)3DG\n" +
                "CV5)GBG\n" +
                "K36)X1M\n" +
                "BTG)N1K\n" +
                "RV3)PKZ\n" +
                "V9K)W11\n" +
                "22K)G1S\n" +
                "CMH)M8W\n" +
                "M1J)LMJ\n" +
                "CMN)XVT\n" +
                "BSQ)PMB\n" +
                "J6P)MH2\n" +
                "LL3)H7T\n" +
                "KR1)KWQ\n" +
                "M7W)NLS\n" +
                "RS5)P6B\n" +
                "1ND)Q26\n" +
                "JBQ)NV2\n" +
                "RPL)JCW\n" +
                "56S)NPJ\n" +
                "6YZ)XWP\n" +
                "52S)X5Y\n" +
                "Z99)1DF\n" +
                "L22)28X\n" +
                "GKJ)S9D\n" +
                "4GP)9QY\n" +
                "NSZ)HYP\n" +
                "45Q)3BR\n" +
                "RSD)61Y\n" +
                "356)Y89\n" +
                "P5K)797\n" +
                "C84)NST\n" +
                "CJG)LZC\n" +
                "1MR)DNQ\n" +
                "LDQ)YZ4\n" +
                "61Y)174\n" +
                "822)R1F\n" +
                "6PT)VB1\n" +
                "8YY)QDQ\n" +
                "VYG)X6C\n" +
                "HHF)T68\n" +
                "46F)M9V\n" +
                "1MR)B7C\n" +
                "2MP)LL3\n" +
                "CTK)YWZ\n" +
                "T98)WL1\n" +
                "14J)BHD\n" +
                "J9N)GWM\n" +
                "MZT)K28\n" +
                "RPL)FH7\n" +
                "T58)35F\n" +
                "DPP)4L7\n" +
                "RVB)DQ3\n" +
                "QBZ)BSY\n" +
                "TSB)PCG\n" +
                "RG2)L6N\n" +
                "8G8)S6P\n" +
                "DQ3)X6N\n" +
                "88N)ML3\n" +
                "6NF)3M4\n" +
                "JFG)F85\n" +
                "X3Q)8FS\n" +
                "96M)3YF\n" +
                "D4B)SAN\n" +
                "LD1)P6P\n" +
                "9B8)416\n" +
                "BJT)C74\n" +
                "QVR)XLT\n" +
                "B43)RPM\n" +
                "QS4)6RG\n" +
                "YD8)96R\n" +
                "8HN)1G5\n" +
                "RJ1)9B8\n" +
                "BRK)ZBT\n" +
                "BHH)RKZ\n" +
                "TGZ)2QQ\n" +
                "NWM)1N6\n" +
                "WWH)46F\n" +
                "LP3)SPS\n" +
                "28X)LNX\n" +
                "X2X)CMH\n" +
                "CP9)T43\n" +
                "9F5)9QW\n" +
                "2MP)7MR\n" +
                "26D)67H\n" +
                "951)RGQ\n" +
                "P6P)KYS\n" +
                "H3H)T6Y\n" +
                "PY6)N37\n" +
                "M9V)Y3L\n" +
                "V56)M29\n" +
                "XWM)DMP\n" +
                "B65)3VX\n" +
                "BBL)W7G\n" +
                "96W)KHW\n" +
                "JGF)VNS\n" +
                "6KR)NWG\n" +
                "7P8)TQ6\n" +
                "394)M79\n" +
                "7QK)YZZ\n" +
                "BZ6)1Z2\n" +
                "D41)14J\n" +
                "848)LDQ\n" +
                "HYY)JP5\n" +
                "S7S)8YF\n" +
                "7HW)KD6\n" +
                "TW4)BMN\n" +
                "VPJ)X89\n" +
                "QCB)R4H\n" +
                "QHX)Z6R\n" +
                "WRX)YT1\n" +
                "3BP)SPT\n" +
                "1HR)1Z7\n" +
                "9LR)26D\n" +
                "S5Q)2C3\n" +
                "9W5)HYH\n" +
                "72B)BZV\n" +
                "LMX)MLN\n" +
                "JFZ)TCD\n" +
                "271)82P\n" +
                "8YF)RCS\n" +
                "9W5)C94\n" +
                "XJY)2GK\n" +
                "KMP)NWM\n" +
                "LZC)2MG\n" +
                "LPK)7MB\n" +
                "S2G)14Y\n" +
                "BMN)5MR\n" +
                "YL8)TQQ\n" +
                "TW4)8LF\n" +
                "HWM)P7R\n" +
                "QQQ)KLG\n" +
                "VFV)BWN\n" +
                "LKN)RH1\n" +
                "V51)RKK\n" +
                "R7H)8S2\n" +
                "LLY)X2X\n" +
                "3W9)FRF\n" +
                "KTP)Y7C\n" +
                "DSG)5QT\n" +
                "7J3)NPB\n" +
                "1R2)HL2\n" +
                "CSB)HPP\n" +
                "X7H)NWW\n" +
                "BSY)F4S\n" +
                "RS5)GSN\n" +
                "F85)N62\n" +
                "HYP)X7H\n" +
                "ZTH)KR1\n" +
                "69D)PTP\n" +
                "CLT)9JZ\n" +
                "LT1)GRR\n" +
                "YXF)LT1\n" +
                "FNP)QFJ\n" +
                "HL2)6R1\n" +
                "ML3)KNR\n" +
                "LQ9)ZSD\n" +
                "84B)5ZX\n" +
                "Q5S)Z8T\n" +
                "6FJ)P43\n" +
                "RP1)19V\n" +
                "GW6)FZF\n" +
                "2DK)WCR\n" +
                "J9X)Q83\n" +
                "WQW)6TK\n" +
                "TS5)8P2\n" +
                "V5R)5H5\n" +
                "D9Z)7QK\n" +
                "B6X)T32\n" +
                "29W)VDB\n" +
                "MLF)WSN\n" +
                "COM)5M5\n" +
                "HZV)XWM\n" +
                "M2T)9TP\n" +
                "F4V)ZXJ\n" +
                "PNQ)NQ6\n" +
                "YLW)DVQ\n" +
                "9VS)CTK\n" +
                "6TK)3ML\n" +
                "SHK)8TS\n" +
                "RC8)596\n" +
                "NRC)JFG\n" +
                "9CB)84B\n" +
                "S2R)263\n" +
                "W11)8CJ\n" +
                "7Q5)7J5\n" +
                "S12)KBK\n" +
                "DBH)HLK\n" +
                "DS2)DXC\n" +
                "9QW)SHK\n" +
                "BPQ)M4G\n" +
                "SC6)VV6\n" +
                "NT6)X8W\n" +
                "584)G31\n" +
                "D7S)5X2\n" +
                "T6Y)CCM\n" +
                "JMT)LML\n" +
                "91F)1CY\n" +
                "CVN)J9X\n" +
                "TY2)VFV\n" +
                "RKK)RTQ\n" +
                "QZS)Z7G\n" +
                "MH2)XWW\n" +
                "ZVQ)DH7\n" +
                "VB1)HP5\n" +
                "H7T)3W9\n" +
                "XN6)51K\n" +
                "1N6)1S1\n" +
                "ZSD)HQC\n" +
                "CYQ)SZP\n" +
                "NZT)LGF\n" +
                "GBG)HNG\n" +
                "L6X)WH4\n" +
                "2LJ)FN6\n" +
                "JT5)XNS\n" +
                "Q17)C5C\n" +
                "3JL)D39\n" +
                "VDD)R7R\n" +
                "55P)XNW\n" +
                "S8K)7LB\n" +
                "N86)8YY\n" +
                "RPM)X3X\n" +
                "HCL)XH1\n" +
                "H53)J9N\n" +
                "Y4R)MZQ\n" +
                "7ZX)D9Z\n" +
                "HTP)Y3J\n" +
                "MTW)BWW\n" +
                "T1M)Q87\n" +
                "B8K)C8W\n" +
                "R9H)NCG\n" +
                "48X)T58\n" +
                "BZV)N2W\n" +
                "SYL)YFR\n" +
                "YCQ)FM2\n" +
                "ZXJ)2H8\n" +
                "644)2MW\n" +
                "1CV)BZ6\n" +
                "6YD)81J\n" +
                "LPK)KTP\n" +
                "QQ8)N4P\n" +
                "VDM)H1H\n" +
                "7FM)JJJ\n" +
                "VZN)V56\n" +
                "RPY)62F\n" +
                "6SD)K1T\n" +
                "2Y9)BQJ\n" +
                "76G)598\n" +
                "CNL)H3H\n" +
                "S7K)69D\n" +
                "YYJ)D4B\n" +
                "WMP)GPJ\n" +
                "XH1)KM7\n" +
                "TJS)S77\n" +
                "6VN)PY6\n" +
                "N44)5PS\n" +
                "G3G)GJF\n" +
                "9FV)M1J\n" +
                "ZCG)J9T\n" +
                "CSF)BBR\n" +
                "WCR)L6Q\n" +
                "2HF)Q7S\n" +
                "9FL)BGS\n" +
                "P3S)PQZ\n" +
                "79T)NJN\n" +
                "ZJY)9FV\n" +
                "P9J)2PH\n" +
                "X8W)96W\n" +
                "GZQ)XH6\n" +
                "WCG)B81\n" +
                "HNN)912\n" +
                "KLG)KBJ\n" +
                "NLS)19J\n" +
                "JCW)Y8W\n" +
                "62F)LLY\n" +
                "QH4)V5R\n" +
                "7NJ)WS7\n" +
                "TY1)DTS\n" +
                "MNR)39N\n" +
                "72B)RL2\n" +
                "DPP)TF4\n" +
                "KWQ)1VH\n" +
                "NPJ)74N\n" +
                "Z7G)RT6\n" +
                "5MR)CJG\n" +
                "82P)TJS\n" +
                "M29)YDN\n" +
                "9LR)C21\n" +
                "26D)NSS\n" +
                "RT6)Z8Y\n" +
                "19J)ZJY\n" +
                "J71)WFX\n" +
                "QDQ)QZS\n" +
                "YZZ)756\n" +
                "9JZ)KPD\n" +
                "3V7)QW6\n" +
                "T9K)QJX\n" +
                "8S2)5V1\n" +
                "Y18)6VN\n" +
                "S6P)TQS\n" +
                "X75)K8H\n" +
                "8CJ)Q3J\n" +
                "ZPS)LYC\n" +
                "454)RTY\n" +
                "PBW)STP\n" +
                "Q26)VDK\n" +
                "45Q)D9F\n" +
                "TVZ)9QF\n" +
                "N1K)T2S\n" +
                "L6N)Y5W\n" +
                "RTQ)RPS\n" +
                "SSY)LFY\n" +
                "TQS)WM8\n" +
                "Y6V)ZKS\n" +
                "Q83)VXD\n" +
                "VDB)SSR\n" +
                "F4S)CMN\n" +
                "NSV)GWZ\n" +
                "MDH)D41\n" +
                "MCT)XK8\n" +
                "32Q)C84\n" +
                "QQ9)PK9\n" +
                "QW6)T1M\n" +
                "LGK)HMW\n" +
                "B81)V8Y\n" +
                "5RL)4CF\n" +
                "3SJ)KGZ\n" +
                "6RG)VMN\n" +
                "416)ZPR\n" +
                "T1M)VZN\n" +
                "PTP)ZDY\n" +
                "C8P)N16\n" +
                "3F5)T98\n" +
                "BNN)DDZ\n" +
                "6D7)D2C\n" +
                "YDT)Q5S\n" +
                "N62)JWH\n" +
                "YSY)QBZ\n" +
                "VDK)W5N\n" +
                "1DF)5ZY\n" +
                "9K5)J21\n" +
                "X6C)1PS\n" +
                "MSZ)G4Z\n" +
                "3VX)Z4X\n" +
                "P2G)K58\n" +
                "L58)RSD\n" +
                "51K)W88\n" +
                "CQB)RDK\n" +
                "TCD)D4N\n" +
                "PLW)L12\n" +
                "78J)LKN\n" +
                "9FL)QHX\n" +
                "WY3)5XH\n" +
                "68Q)J71\n" +
                "4RT)K6M\n" +
                "2MG)ZH1\n" +
                "19V)32Q\n" +
                "YJX)9VY\n" +
                "GHQ)6MK\n" +
                "KNR)VMM\n" +
                "GRR)NSV\n" +
                "JJJ)8NY\n" +
                "2DM)2DK\n" +
                "4NN)7P8\n" +
                "PMB)JSD\n" +
                "XT1)RR2\n" +
                "GPR)VS4\n" +
                "CN8)N9F\n" +
                "DNQ)6XN\n" +
                "8Q9)TX5\n" +
                "MR4)YSY\n" +
                "DR4)8Q9\n" +
                "PNW)CSQ\n" +
                "VSV)ZNL\n" +
                "FCC)SSY\n" +
                "GX3)2LG\n" +
                "J6G)V9M\n" +
                "2QH)7W6\n" +
                "33X)FY9\n" +
                "QKS)78J\n" +
                "N37)2L1\n" +
                "LYC)SRV\n" +
                "CLW)43N\n" +
                "91F)92L\n" +
                "XJR)TFS\n" +
                "XLT)TSB\n" +
                "YKQ)J6G\n" +
                "5H5)15Z\n" +
                "HSX)7HW\n" +
                "ZC8)6YD\n" +
                "7J6)S8K\n" +
                "2RN)HNW\n" +
                "CN8)YYJ\n" +
                "KPD)7ZX\n" +
                "VXD)VYG\n" +
                "ZW6)7K7\n" +
                "5QT)XN6\n" +
                "2D1)B95\n" +
                "P6B)7QR\n" +
                "MPJ)48Q\n" +
                "CMH)8MW\n" +
                "57P)B5P\n" +
                "GYC)5QX\n" +
                "2L1)M5K\n" +
                "K8H)4V3\n" +
                "GRR)9LR\n" +
                "HL7)ZVQ\n" +
                "WS7)MCT\n" +
                "57P)FV7\n" +
                "RJR)PL4\n" +
                "5MV)CPJ\n" +
                "5PS)55P\n" +
                "HLK)4ML\n" +
                "LNX)ML6\n" +
                "2MJ)BPQ\n" +
                "2N3)WLK\n" +
                "WM3)GN9\n" +
                "WDM)3SM\n" +
                "YZ4)B8K\n" +
                "LFY)VVJ\n" +
                "15Z)9K5\n" +
                "CWR)TGZ\n" +
                "XBY)6NF\n" +
                "MLN)MPK\n" +
                "NRC)33P\n" +
                "VVJ)PZL\n" +
                "K1K)6FJ\n" +
                "Q6N)XBY\n" +
                "4CF)RJ1\n" +
                "3M4)NWC\n" +
                "JP5)DT8\n" +
                "62P)1PL\n" +
                "K28)S12\n" +
                "PQG)S5Q\n" +
                "X1M)Q17\n" +
                "RCS)6PN\n" +
                "WL3)FC5\n" +
                "L6X)S7K\n" +
                "NWC)2LJ\n" +
                "29L)CN8\n" +
                "4BH)9W5\n" +
                "NWG)555\n" +
                "VWL)RPL\n" +
                "FRL)VSV\n" +
                "YLV)394\n" +
                "FL9)2QH\n" +
                "8S7)M1K\n" +
                "VW2)TTZ\n" +
                "PL4)2Y9\n" +
                "VV6)SL5\n" +
                "14Y)D7S\n" +
                "74P)G6Y\n" +
                "SSR)YOU\n" +
                "Y8L)2RN\n" +
                "W4C)W7Z\n" +
                "ML6)PNQ\n" +
                "K1H)QP4\n" +
                "75P)ZGY\n" +
                "W5N)WTB\n" +
                "WQV)Y4R\n" +
                "2QQ)9D6\n" +
                "59B)C8P\n" +
                "CPJ)3FS\n" +
                "PFH)KHN\n" +
                "6SS)2F2\n" +
                "C74)4YG\n" +
                "SYG)DK2\n" +
                "ZBD)7X3\n" +
                "W91)13L\n" +
                "X6C)52S\n" +
                "T43)8SD\n" +
                "2PH)GBT\n" +
                "G4Z)GKJ\n" +
                "598)L58\n" +
                "6R1)34Z\n" +
                "DVN)6KR\n" +
                "VMM)687";
    }
}
