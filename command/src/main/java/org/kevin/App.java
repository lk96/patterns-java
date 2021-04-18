package org.kevin;


/**
 * 命令模式是一种行为模式，其中的对象用于封装以后执行动作或触发事件所需的事件信息。包括方法名称，拥有方法的对象和方法的参数值
 */
public class App {

    public static void main(String[] args) {

        Wizard wizard = new Wizard();
        Goblin goblin = new Goblin();

        goblin.printStatus();

        wizard.castSpell(goblin::changeSize);
        goblin.printStatus();

        wizard.castSpell(goblin::changeVisibility);
        goblin.printStatus();

        wizard.undoLastSpell();
        goblin.printStatus();

        wizard.undoLastSpell();
        goblin.printStatus();

        wizard.redoLastSpell();
        goblin.printStatus();

        wizard.redoLastSpell();
        goblin.printStatus();
    }
}
