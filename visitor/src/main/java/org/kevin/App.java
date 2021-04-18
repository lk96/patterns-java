package org.kevin;

/**
 * 表示要在对象结构的元素上执行的操作。访问者可以定义新操作，而无需更改其所操作元素的类
 * 军队单位的树形结构 指挥官下面有两名中士，每名中士下面有三名士兵
 * 使用访问者模式可以轻松创建指挥官、中士、士兵或所有人互动的新形象
 * 访问者模式可以在数据结构的节点上执行的操作
 */
public class App {

    public static void main(String[] args) {
        Commander commander = new Commander(
                new Sergeant(new Soldier(), new Soldier(), new Soldier()),
                new Sergeant(new Soldier(), new Soldier(), new Soldier())
        );
        commander.accept(new SoldierVisitor());
        commander.accept(new SergeantVisitor());
        commander.accept(new CommanderVisitor());
    }
}
