package com.digitalhouse.clinicaodontologicag6.utils;

import com.fasterxml.jackson.databind.ObjectMapper;

// Classe com funções uteis, evitar repetição de codigo desnecessaria
public class ClinicaOdontologicaUtils {

    private static final ObjectMapper mapper = new ObjectMapper();

    public static String asJsonString(final Object obj) {
        try {
            return mapper.writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }

    public static <T> T objectFromString(Class<T> aClass, String value) {
        try {
            return mapper.readValue(value, aClass);
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }

}
