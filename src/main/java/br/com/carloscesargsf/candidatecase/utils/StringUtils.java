package br.com.carloscesargsf.candidatecase.utils;

public class StringUtils {

    private StringUtils() {
    }

    public static String removeAllNonNumbers(String s) {
        return s.replaceAll("[^\\d.]", "");
    }

}
