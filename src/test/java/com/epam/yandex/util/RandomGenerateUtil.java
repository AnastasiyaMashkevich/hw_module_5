package com.epam.yandex.util;

import org.apache.commons.lang.RandomStringUtils;

public class RandomGenerateUtil {

    public static String generateString() {
        return RandomStringUtils.random(5, true, false);
    }
}
