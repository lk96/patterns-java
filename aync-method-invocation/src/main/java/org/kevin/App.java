package org.kevin;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Callable;

@Slf4j
public class App {

    public static void main(String[] args) throws Exception {
        ThreadAsyncExecutor executor = new ThreadAsyncExecutor();
        final AsyncResult<Integer> asyncResult1 = executor.startProcess(lazyVal(2, 50));
        final AsyncResult<String> asyncResult2 = executor.startProcess(lazyVal("test", 200));
        final AsyncResult<Long> asyncResult3 = executor.startProcess(lazyVal(50L, 300));
        final AsyncResult<Integer> asyncResult4 = executor.startProcess(lazyVal(10, 200), callback("Deploying"));
        final AsyncResult<String> asyncResult5 = executor.startProcess(lazyVal("callback", 300), callback("Deploying"));

        Thread.sleep(350); // Oh boy, we are working hard here
        log("Mission command is sipping coffee");

        final var result1 = executor.endProcess(asyncResult1);
        final var result2 = executor.endProcess(asyncResult2);
        final var result3 = executor.endProcess(asyncResult3);
        asyncResult4.await();
        asyncResult5.await();

        // log the results of the tasks, callbacks log immediately when complete
        log("Space rocket <" + result1 + "> launch complete");
        log("Space rocket <" + result2 + "> launch complete");
        log("Space rocket <" + result3 + "> launch complete");
    }

    private static <T> Callable<T> lazyVal(T value, long delayMillis) {
        return () -> {
            Thread.sleep(delayMillis);
            log("Space rocket <" + value + "> launched successfully");
            return value;
        };
    }

    private static <T> AsyncCallback<T> callback(String name) {
        return (value, ex) -> {
            if (ex.isPresent()) {
                log(name + " failed: " + ex.map(Exception::getMessage).orElse(""));
            } else {
                log(name + " <" + value + ">");
            }
        };
    }

    private static void log(String msg) {
        log.info(msg);
    }
}
