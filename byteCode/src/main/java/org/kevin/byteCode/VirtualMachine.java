package org.kevin.byteCode;

import java.util.Stack;

public class VirtualMachine {

    private final Stack<Integer> stack = new Stack<>();

    private final Wizard[] wizards = new Wizard[2];

    public VirtualMachine() {
        for (int i = 0; i < wizards.length; i++) {
            wizards[i] = new Wizard();
        }
    }

    public void execute(int[] byteCode) {
        for (int i = 0; i < byteCode.length; i++) {
            Instruction instruction = Instruction.getInstruction(byteCode[i]);
            switch (instruction) {
                case LITERAL:
                    int value = byteCode[++i];
                    stack.push(value);
                    break;
                case SET_AGILITY:
                    Integer amount = stack.pop();
                    Integer wizard = stack.pop();
                    setAgility(wizard,amount);
                    break;
                case SET_WISDOM:
                     amount = stack.pop();
                     wizard = stack.pop();
                    setWisdom(wizard,amount);
                    break;
                case SET_HEALTH:
                    amount = stack.pop();
                    wizard = stack.pop();
                    setHealth(wizard,amount);
                    break;
                case GET_HEALTH:
                    wizard = stack.pop();
                    stack.push(getHealth(wizard));
                    break;
                case GET_AGILITY:
                    wizard = stack.pop();
                    stack.push(getAgility(wizard));
                    break;
                case GET_WISDOM:
                    wizard = stack.pop();
                    stack.push(getWisdom(wizard));
                    break;
                case ADD:
                    Integer a = stack.pop();
                    Integer b = stack.pop();
                    stack.push(a + b);
                    break;
                case DIVIDE:
                    a = stack.pop();
                    b = stack.pop();
                    stack.push(b / a);
                    break;
                case PLAY_SOUND:
                    wizard = stack.pop();
                    getWizards()[wizard].playSound();
                    break;
                case SPAWN_PARTICLES:
                    wizard = stack.pop();
                    getWizards()[wizard].spawnParticles();
                    break;
                default:
                    throw new IllegalArgumentException("Invalid instruction value");
            }
        }
    }

    public Stack<Integer> getStack() {
        return stack;
    }

    public void setHealth(int wizard, int amount) {
        wizards[wizard].setHealth(amount);
    }

    public void setWisdom(int wizard, int amount) {
        wizards[wizard].setWisdom(amount);
    }

    public void setAgility(int wizard, int amount) {
        wizards[wizard].setAgility(amount);
    }

    public int getHealth(int wizard) {
        return wizards[wizard].getHealth();
    }

    public int getWisdom(int wizard) {
        return wizards[wizard].getWisdom();
    }

    public int getAgility(int wizard) {
        return wizards[wizard].getAgility();
    }

    public Wizard[] getWizards() {
        return wizards;
    }
}
