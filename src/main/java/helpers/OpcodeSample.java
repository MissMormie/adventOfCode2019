package helpers;

import java.util.Map;
import java.util.Set;

public class OpcodeSample {
    public enum Opcodes {
        ADDR, ADDI, MULR, MULI, BANR, BANI, BORR, BORI, SETR, SETI, GTIR, GTRI, GTRR, EQIR, EQRI, EQRR
    }

    public int[] register;
    private int registerA;
    private int registerB;
    private int registerC;
    private int valueA;
    private int valueB;
    
    
    public OpcodeSample(int[] before) {
        this.register = before;

    }

    public void runOpcode(int[] instruction, Map<Integer, Opcodes> opcodesMap) {
        getValuesFromInstruction(instruction);
        Opcodes opcodes = opcodesMap.get(instruction[0]);
        int temp = run(register, opcodes);
        register[registerC] = temp;
    }

    public void checkImpossibles(Map<Opcodes, Set<Integer>> possibilities, int[] instruction, int[] after) {
        getValuesFromInstruction(instruction);
        for(Opcodes opcode : Opcodes.values()) {
            if(checkMatch(register, after, opcode) == 0) {
                possibilities.get(opcode).remove(instruction[0]);
            }
        }
    }

    public void getValuesFromInstruction(int[] instruction) {
        this.valueA = this.registerA = instruction[1];
        this.valueB = this.registerB = instruction[2];
        this.registerC = instruction[3];
    }

    public int getNumberOfMatches(int[] after, int[] instruction) {
        getValuesFromInstruction(instruction);
        int numMatches = 0;
        for(Opcodes opcode : Opcodes.values()) {
            numMatches += checkMatch(register, after, opcode);
        }

        return numMatches;
    }

    public int checkMatch(int[] before, int[] after, Opcodes opcode) {
        int[] workingCopy = before.clone();
        workingCopy[registerC] = run(workingCopy, opcode);
        return isEqual(workingCopy, after);
    }

    public int run(int[] before, Opcodes opcode) {
        switch(opcode) {
            case ADDR: return before[registerA] + before[registerB];
            case ADDI: return before[registerA] + valueB;
            case MULR: return before[registerA] * before[registerB];
            case MULI: return before[registerA] * valueB;
            case SETR: return before[registerA] ;
            case SETI: return valueA;
            case GTIR: return valueA > before[registerB] ? 1 : 0;
            case GTRI: return before[registerA] > valueB ?  1 : 0;
            case GTRR: return before[registerA]> before[registerB] ? 1 : 0;
            case EQIR: return valueA == before[registerB] ? 1 : 0;
            case EQRI: return before[registerA] == valueB ? 1 : 0;
            case EQRR: return before[registerA] == before[registerB] ? 1 : 0;
            case BANR: return before[registerA] & before[registerB];
            case BANI: return before[registerA] & valueB;
            case BORR: return before[registerA] | before[registerB];
            case BORI: return before[registerA] | valueB;
        }
        return 0;
    }

    public int matchesAddr(int[] before, int[] after) {
        before[registerC] = before[registerA] + before[registerB];
        return isEqual(before, after);
    }


    public int isEqual(int[] array1, int[] array2) {
        for(int i = 0; i < array1.length; i++) {
            if(array1[i] != array2[i]) {
                return 0;
            }
        }
        return 1;
    }
}
