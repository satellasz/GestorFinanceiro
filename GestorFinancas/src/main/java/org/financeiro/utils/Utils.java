package org.financeiro.utils;

import org.financeiro.exceptions.CampoInvalidoException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Utils {
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
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

    public static LocalDate getData(String str) throws CampoInvalidoException {
        try {
            return LocalDate.parse(str, formatter);
        } catch (DateTimeParseException e) {
            throw new CampoInvalidoException("Data não é válida");
        }
    }

    public static String getData(LocalDate date) {
        return date.format(formatter);
    }

    public static String getDataAtual() {
        return getData(LocalDate.now());
    }
}
