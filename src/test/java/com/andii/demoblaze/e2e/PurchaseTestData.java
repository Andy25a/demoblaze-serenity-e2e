package com.andii.demoblaze.e2e;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public final class PurchaseTestData {

    private static final String DATA_FILE = "test-data.properties";
    private static final Properties DATA = loadData();

    private PurchaseTestData() {
    }

    public static String get(String key) {
        String value = DATA.getProperty(key);
        if (value == null || value.trim().isEmpty()) {
            throw new IllegalStateException("No existe valor para la clave: " + key);
        }
        return value.trim();
    }

    private static Properties loadData() {
        Properties properties = new Properties();
        try (InputStream input = PurchaseTestData.class.getClassLoader().getResourceAsStream(DATA_FILE)) {
            if (input == null) {
                throw new IllegalStateException("No se encontro el archivo de datos: " + DATA_FILE);
            }
            properties.load(input);
            return properties;
        } catch (IOException e) {
            throw new IllegalStateException("Error al cargar datos de prueba", e);
        }
    }
}
