package org.kevin;

/**
 * 在操作定义算法的框架，将某些步骤推迟到子类。模板方法允许子类在不更改算法结构的情况下重新定义算法的某些步骤
 */
public class App {

    public static void main(String[] args) {
        HalflingThief thief = new HalflingThief(new HitAndRunMethod());
        thief.steal();
        thief.changeMethod(new SubtleMethod());
        thief.steal();
    }
}
