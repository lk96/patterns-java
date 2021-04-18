package org.kevin;

import lombok.extern.slf4j.Slf4j;

/**
 * Composite 是一种分区设计模式
 * 组合模式描述了要以对象的单个实例相同的方式对待一组对象。组合的目的是将对象的组成为树状结构，以表示部分整体层次结构。
 * 实施组合模式可以使客户端统一对待单个对象和构图
 * 我们由字母组成的单词组成的句子，可以通过相同的处理界面处理对象
 */
@Slf4j
public class App {

    public static void main(String[] args) {
        log.info("Message from the orcs: ");
        LetterComposite orcMessage = new Messenger().messageFromOrcs();
        orcMessage.print();

        log.info("\nMessage from the elves: ");

        LetterComposite elvesMessage = new Messenger().messageFromElves();
        elvesMessage.print();
    }
}
