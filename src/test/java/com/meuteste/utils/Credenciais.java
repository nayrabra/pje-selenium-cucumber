package com.meuteste.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Credenciais {
	private static Properties properties = new Properties();

    static {

        try {

            FileInputStream file = new FileInputStream("credenciais-login.properties");

            properties.load(file);

        } catch (IOException e) {

            e.printStackTrace();
        }
    }

    public static String getUsuario() {

        return properties.getProperty("usuario");
    }

    public static String getSenha() {

        return properties.getProperty("senha");
    }
}
