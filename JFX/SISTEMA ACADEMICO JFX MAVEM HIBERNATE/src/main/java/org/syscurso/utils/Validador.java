package org.syscurso.utils;

public class Validador {
    public static boolean isCampoVazio(String texto) {
        return texto == null || texto.trim().isEmpty();
    }

    public static boolean isNumeroValido(String texto) {
        try {
            Integer.parseInt(texto);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
