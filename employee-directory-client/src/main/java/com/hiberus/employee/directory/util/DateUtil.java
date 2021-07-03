package com.hiberus.employee.directory.util;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

/**
 * DateUtil.
 * 
 * @author acachiguango on 01/07/2021
 * @version 1.0
 * @since 1.0.0
 */
public final class DateUtil {
    /**
     * Constructor.
     */
    private DateUtil() {
    }

    /**
     * Get current date.
     * 
     * @author acachiguango on 01/07/2021
     * @return Date
     */
    public static Date currentDate() {
        return Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant());
    }
}
