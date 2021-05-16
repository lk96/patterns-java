package org.kevin;

import lombok.Getter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
public class Result {

    private final List<Date> dateList = new ArrayList<>();

    private final List<String> exceptionList = new ArrayList<>();

}
