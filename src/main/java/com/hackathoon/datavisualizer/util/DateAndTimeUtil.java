package com.hackathoon.datavisualizer.util;

import lombok.NoArgsConstructor;

import java.time.format.DateTimeFormatter;

import static lombok.AccessLevel.NONE;

@NoArgsConstructor(access = NONE)
public class DateAndTimeUtil {

    public static final String dateTimePattern = "yyyy-MM-dd HH:mm:ss";
    public static final DateTimeFormatter format = DateTimeFormatter.ofPattern(dateTimePattern);

}
