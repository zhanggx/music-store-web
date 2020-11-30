package com.example.musicstore.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateTimeUtils {
    private final static String YYYYMM = "yyyy-MM";
    public final static String YYYYMMDD = "yyyy-MM-dd";
    public final static String YYYYMMDDHHMMSS = "yyyy-MM-dd HH:mm:ss";
    public final static String YYYYMMDDHHMM = "yyyy-MM-dd HH:mm";
    private final static String CODE_DATE_FORMAT = "yyyyMMddHHmmss";
    private static final SimpleDateFormat sf_YYYYMM = new SimpleDateFormat(YYYYMM);
    private static final SimpleDateFormat sf_YYYYMMDDHHMMSS = new SimpleDateFormat(YYYYMMDDHHMMSS);
    private static final SimpleDateFormat sf_YYYYMMDDHHMM = new SimpleDateFormat(YYYYMMDDHHMM);
    private static final SimpleDateFormat sf_YYYYMMDD = new SimpleDateFormat(YYYYMMDD);
    private static final SimpleDateFormat sf_CODEDATEFORMAT = new SimpleDateFormat(CODE_DATE_FORMAT);

    public static String toYYYYMMDDHHMMSS(Date date) {
        return sf_YYYYMMDDHHMMSS.format(date);
    }
    public static String toYYYYMMDDHHMM(Date date) {
        return sf_YYYYMMDDHHMM.format(date);
    }

    /**
     * 加一天
     * @param date
     * @return
     */
    public static java.sql.Date increaseDate(java.sql.Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.DAY_OF_MONTH, 1);// 今天+1天
        date =new java.sql.Date(c.getTimeInMillis());
        return date;
    }
}
