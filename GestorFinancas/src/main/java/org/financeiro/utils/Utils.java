package org.financeiro.utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Utils {
    private static final DateTimeFormatter formatterPadrao = DateTimeFormatter.ofPattern("dd/MM/yyyy");

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

    public static LocalDate getDataFormatada(String str) {
        return LocalDate.parse(str, formatterPadrao);
    }
}
