package org.kevin;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.*;

@Slf4j
public class AsynchronousService {

    private final ExecutorService service;

    public AsynchronousService(BlockingQueue<Runnable> workQueue) {
        this.service = new ThreadPoolExecutor(10, 10, 10, TimeUnit.SECONDS, workQueue);
    }

    public <T> void execute(final AsyncTask<T> task) {

        try {
            task.onPreCall();
        } catch (Exception e) {
            task.onError(e);
            return;
        }

        service.submit(new FutureTask<T>(task){
            @Override
            protected void done() {
                super.done();
                try {
                    task.onPostCall(get());
                } catch (InterruptedException e) {

                } catch (ExecutionException e) {
                    task.onError(e);
                }
            }
        });
    }

    public void close() {
        service.shutdown();
        try {
            service.awaitTermination(10, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            log.error("Error waiting for executor service shutdown");
        }
    }
}
