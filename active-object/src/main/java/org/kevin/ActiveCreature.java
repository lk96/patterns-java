package org.kevin;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;

@Slf4j
public class ActiveCreature {

    private BlockingDeque<Runnable> requests;

    private String name;

    private Thread thread; //执行线程

    private int status; //线程状态

    protected ActiveCreature(String name) {
        this.name=name;
        this.status = 0;
        this.requests = new LinkedBlockingDeque<>();
        thread=new Thread(()->{
            boolean infinite = true;
            while (infinite) {
                try {
                    requests.take().run();
                } catch (InterruptedException e) {
                    if (this.status != 0) {
                        log.error("Thread was interrupted.{}",e.getMessage());
                    }
                    infinite = false;
                    Thread.currentThread().interrupt();
                }
            }
        });
        thread.start();
    }

    /**
     * 吃饭
     * @throws InterruptedException
     */
    public void eat() throws InterruptedException {
        requests.put(()->{
            log.info("{} is eating.",name);
            log.info("{} has finished eating.",name);
        });
    }

    /**
     * 在草原漫游
     * @throws InterruptedException
     */
    public void roam() throws InterruptedException {
        requests.put(()->{
            log.info("{} has started to roam in the wastelands.",name);
        });
    }

    /**
     * 返回名称
     * @return
     */
    public String name(){
        return name;
    }

    /**
     * 终止
     * @param status 状态
     */
    public void kill(int status) {
        this.status = status;
        this.thread.interrupt();
    }

    /**
     * 返回执行线程状态
     * @return
     */
    public int getStatus(){
        return status;
    }

}
