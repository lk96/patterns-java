package org.kevin;

import lombok.extern.slf4j.Slf4j;

import java.util.Calendar;
import java.util.concurrent.Callable;
import java.util.concurrent.Executors;

@Slf4j
public class App {

    public static void main(String[] args) {
        int counterDateValues = 0;
        int counterExceptions = 0;
        Callable<Result> callable = new DateFormatCallable("yyyy-MM-dd", "2020-12-19");
        var executor = Executors.newCachedThreadPool();
        var result1 = executor.submit(callable);
        var result2 = executor.submit(callable);
        var result3 = executor.submit(callable);
        var result4 = executor.submit(callable);
        try {
            Result[] results = new Result[4];
            results[0] = result1.get();
            results[1] = result2.get();
            results[2] = result3.get();
            results[3] = result4.get();

            for (Result result : results) {
                counterDateValues = counterDateValues + printAndCountDates(result);
                counterExceptions = counterExceptions + printAndCountExceptions(result);
                log.info("The List dateList contains " + counterDateValues + " date values");
                log.info("The List exceptionList contains " + counterExceptions + " exceptions");
            }
        } catch (Exception e) {
            log.error("Abnormal end of program. Program throws exception: {}", e.getMessage());
        }
        executor.shutdown();
    }

    private static int printAndCountDates(Result res) {
        var counter = 0;
        for (var dt : res.getDateList()) {
            counter++;
            var cal = Calendar.getInstance();
            cal.setTime(dt);
            log.info(cal.get(Calendar.DAY_OF_MONTH) + "."
                    + cal.get(Calendar.MONTH) + "."
                    + cal.get(Calendar.YEAR)
            );
        }
        return counter;
    }

    private static int printAndCountExceptions(Result res) {
        var counter = 0;
        for (var ex : res.getExceptionList()) {
            counter++;
            log.info(ex);
        }
        return counter;
    }
}
