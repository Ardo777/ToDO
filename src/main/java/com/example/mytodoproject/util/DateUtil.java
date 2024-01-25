package com.example.mytodoproject.util;

import lombok.experimental.UtilityClass;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


@UtilityClass
public class DateUtil {

    private final SimpleDateFormat SIMPLE_DATE_FORMAT = new SimpleDateFormat("MM-dd-yyyy");

    public  Date stringToDate(String dateStr) throws ParseException {
        return SIMPLE_DATE_FORMAT.parse(dateStr);
    }

    public String dateToString(Date date) {
        return SIMPLE_DATE_FORMAT.format(date);
    }
}
