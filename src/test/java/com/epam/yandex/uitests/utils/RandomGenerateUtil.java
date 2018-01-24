package com.epam.yandex.uitests.utils;

import java.security.SecureRandom;
import java.util.LinkedList;
import java.util.List;

public class RandomGenerateUtil {

    private static List<String> processed = new LinkedList<>();

    private static final String AB = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";

    private static final int DEFAULT_LENGTH = 5;

    private static int iterationsCount = 0;

    private static SecureRandom rnd = new SecureRandom();

    public static String randomString() {
        return randomString(DEFAULT_LENGTH);
    }

    public static String randomString(int length) {
        return generate(length);
    }

    public static String randomStringUnique() {
        return randomStringUnique(DEFAULT_LENGTH);
    }

    private static String randomStringUnique(int length) {
        String generated;
        do {
            generated = generate(length);
        } while (processed.contains(generated));
        processed.add(generated);
        return generated;
    }

    private static String generate(int length) {
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            sb.append(AB.charAt(rnd.nextInt(AB.length())));
        }
        iterationsCount++;
        return sb.toString();
    }
}
