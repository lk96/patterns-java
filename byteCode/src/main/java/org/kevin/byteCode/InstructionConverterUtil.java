package org.kevin.byteCode;

public class InstructionConverterUtil {

    public static int[] convertToByteCode(String instructions) {
        if (instructions == null || instructions.length() == 0) {
            return new int[0];
        }

        String[] strings = instructions.trim().split(" ");
        int[] byteCode = new int[strings.length];
        for (int i = 0; i < strings.length; i++) {
            if (isValidInstruction(strings[i])) {
                byteCode[i] = Instruction.valueOf(strings[i]).getValue();
            } else if (isValidInt(strings[i])) {
                byteCode[i] = Integer.parseInt(strings[i]);
            }else {
                throw new IllegalArgumentException("Invalid instruction or number:" + strings[i]);
            }
        }
        return byteCode;
    }

    public static boolean isValidInstruction(String instruction) {
        try {
            Instruction.valueOf(instruction);
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

    public static boolean isValidInt(String value) {
        try {
            Integer.parseInt(value);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
