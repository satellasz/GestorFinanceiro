package org.financeiro.utils;

public class Utils {
    private Utils() {
    }

    public static boolean isNumero(String str) {
        try {
            Double.parseDouble(str);

            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static boolean isStringVazia(String str) {
        return str == null || str.trim().isEmpty();
    }
}
