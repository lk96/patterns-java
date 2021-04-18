package org.kevin.byteCode;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Stack;

public class App {

    private static final Logger log = LoggerFactory.getLogger(App.class);

    public static void main(String[] args) {
        Wizard wizard = new Wizard();
        wizard.setHealth(5);
        wizard.setAgility(17);
        wizard.setWisdom(10);
        VirtualMachine virtualMachine = new VirtualMachine();
        virtualMachine.getWizards()[0] = wizard;

        String literal = "LITERAL 0";
        interpretInstruction(literal,virtualMachine);
        interpretInstruction(literal,virtualMachine);
        interpretInstruction("GET_HEALTH",virtualMachine);
        interpretInstruction(literal,virtualMachine);
        interpretInstruction("GET_AGILITY",virtualMachine);
        interpretInstruction(literal,virtualMachine);
        interpretInstruction("GET_WISDOM",virtualMachine);
        interpretInstruction("ADD",virtualMachine);
        interpretInstruction("LITERAL 2",virtualMachine);
        interpretInstruction("DIVIDE",virtualMachine);
        interpretInstruction("ADD",virtualMachine);
        interpretInstruction("SET_HEALTH",virtualMachine);

    }

    public static void interpretInstruction(String instruction, VirtualMachine virtualMachine) {
        virtualMachine.execute(InstructionConverterUtil.convertToByteCode(instruction));
        Stack<Integer> stack = virtualMachine.getStack();
        log.info(instruction + String.format("%" + (12 - instruction.length()) + "s", "") + stack);
    }
}
