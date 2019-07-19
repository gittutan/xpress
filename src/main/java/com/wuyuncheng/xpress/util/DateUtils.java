package com.wuyuncheng.xpress.util;

import java.time.Instant;
import java.util.Date;

public class DateUtils {

    /**
     * Get current unix time.
     *
     * @return return current unix time.
     */
    public static int nowUnix() {
        return (int) Instant.now().getEpochSecond();
    }

    public static Date toDate(long unixTime) {
        return Date.from(Instant.ofEpochSecond(unixTime));
    }

}
