package org.kevin;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

/**
 * 活动对象设计模式使每个驻留在其控制线程中的对象的方法执行与方法调用脱钩。通过异步方法调用和用于处理请求的调度程序来引入并发。
 */
@Slf4j
public class App implements Runnable{

    private static final int NUN_CREATURES = 3;

    public static void main(String[] args) {
        App app = new App();
        app.run();
    }

    @Override
    public void run() {
       List<ActiveCreature> activeCreatures = new ArrayList<>();
        try {
            for (int i = 0; i < NUN_CREATURES; i++) {
                activeCreatures.add(new Orc(Orc.class.getName() + i));
                activeCreatures.get(i).eat();
                activeCreatures.get(i).roam();
            }
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            log.error(e.getMessage());
            Thread.currentThread().interrupt();
        }finally {
            for (int i = 0; i < NUN_CREATURES; i++) {
                activeCreatures.get(i).kill(0);
            }
        }
    }


}
