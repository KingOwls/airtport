package com.campuslands.modules.auth.domain.models;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import org.apache.commons.codec.binary.Hex;

public class Auth {

    String uid;
    String email;
    String password;


    // Genera una sal segura usando SecureRandom
    public static byte[] getSalt() throws NoSuchAlgorithmException {
        SecureRandom sr = SecureRandom.getInstanceStrong();
        byte[] salt = new byte[16];
        sr.nextBytes(salt);
        return salt;
    }

    // Genera un hash seguro para la contraseña con la sal proporcionada
    public static String getSecurePassword(String password, byte[] salt) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        md.update(salt);
        byte[] hashedPassword = md.digest(password.getBytes());
        return Hex.encodeHexString(hashedPassword);
    }

    // Verifica si la contraseña proporcionada coincide con el hash almacenado
    public static boolean verifyUserPassword(String providedPassword, String securedPassword, byte[] salt) throws NoSuchAlgorithmException {
        String newSecurePassword = getSecurePassword(providedPassword, salt);
        return newSecurePassword.equals(securedPassword);
    }
}
