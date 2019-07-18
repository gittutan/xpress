package com.wuyuncheng.xpress.util;

import java.time.Instant;

public class DateUtils {

    /**
     * Get current unix time.
     *
     * @return return current unix time.
     */
    public static int nowUnix() {
        return (int) Instant.now().getEpochSecond();
    }

}
