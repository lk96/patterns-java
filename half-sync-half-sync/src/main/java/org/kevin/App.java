package org.kevin;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.LinkedBlockingQueue;

@Slf4j
public class App {

    public static void main(String[] args) {
        AsynchronousService service = new AsynchronousService(new LinkedBlockingQueue< >());

        service.execute(new ArithmeticSumTask(1000));

        service.execute(new ArithmeticSumTask(500));
        service.execute(new ArithmeticSumTask(2000));
        service.execute(new ArithmeticSumTask(1));

        service.close();
    }


    static class ArithmeticSumTask implements AsyncTask<Long> {
        private final long numberOfElements;

        public ArithmeticSumTask(long numberOfElements) {
            this.numberOfElements = numberOfElements;
        }


        @Override
        public Long call() throws Exception {
            return ap(numberOfElements);
        }

        @Override
        public void onPreCall() {
            if (numberOfElements < 0) {
                throw new IllegalArgumentException("n is less than 0");
            }
        }

        @Override
        public void onPostCall(Long result) {
            log.info(result.toString());
        }

        @Override
        public void onError(Throwable throwable) {
            throw new IllegalStateException("Should not occur");
        }
    }

    private static long ap(long i) {
        try {
            Thread.sleep(i);
        } catch (InterruptedException e) {
            log.error("Exception caught.", e);
        }
        return i * (i + 1) / 2;
    }
}
