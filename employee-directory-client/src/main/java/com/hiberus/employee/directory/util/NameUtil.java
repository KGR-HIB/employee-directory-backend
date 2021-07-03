package com.hiberus.employee.directory.util;

import java.util.Arrays;
import org.apache.commons.lang3.StringUtils;

/**
 * NameUtil.
 * 
 * @author acachiguango on 01/07/2021
 * @version 1.0
 */
public final class NameUtil {

    /**
     * Constructor.
     */
    private NameUtil() {
    }

    /**
     * Remove accents.
     * 
     * @author acachiguango on 01/07/2021
     * @param name value
     * @return string without accents.
     */
    public static String clearAccents(String name) {
        String[] names = Arrays.stream(name.split(" ")).map(String::trim).toArray(String[]::new);
        return StringUtils.stripAccents(String.join(" ", names)).toUpperCase();
    }
}
