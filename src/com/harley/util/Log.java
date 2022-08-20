package com.harley.util;

import javafx.scene.input.DataFormat;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class Log {
    private static final String FORMAT = "yyyy-MM-dd HH:mm:ss:SS";
    public static void println(Object o) {
        LocalDateTime localDateTime = LocalDateTime.now(ZoneId.of("Asia/Shanghai"));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(FORMAT);
        String now = formatter.format(localDateTime);
        String msg = now + ": " + (o == null ? "null" : o.toString());
        System.out.println(msg);
    }
}
