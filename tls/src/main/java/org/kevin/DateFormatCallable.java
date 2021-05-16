package org.kevin;

import lombok.extern.slf4j.Slf4j;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.concurrent.Callable;
import java.util.stream.IntStream;

@Slf4j
public class DateFormatCallable implements Callable<Result>{

    private final ThreadLocal<DateFormat> df;

    private final String dateValue;

    public DateFormatCallable(String inDateFormat, String inDateValue) {
        final String idf = inDateFormat;
        this.df = ThreadLocal.withInitial(() -> new SimpleDateFormat(idf));
        this.dateValue = inDateValue;
    }

    @Override
    public Result call() {
        log.info(Thread.currentThread() + "started executing...");
        Result result = new Result();
        IntStream.rangeClosed(1,5).forEach(i->{
            try {
                result.getDateList().add(this.df.get().parse(this.dateValue));
            } catch (ParseException e) {
                result.getExceptionList().add(e.getClass() + ":" + e.getMessage());
            }
        });
        log.info("{} finished processing part of the thread", Thread.currentThread());
        return result;
    }
}
