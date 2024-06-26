package com.campuslands.modules.auth.domain.models;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import org.apache.commons.codec.binary.Hex;

public class Auth {

    public static Auth instance;
    String uid;
    String email;
    String password;
    String rol;
    String token;

    public void setRol(String rol) {
        this.rol = rol;
    }

    public String getRol() {
        return rol;
    }

    public Auth() {
    }

    public Auth(String uid, String email, String password) {
        this.uid = uid;
        this.email = email;
        this.password = password;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

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
    public boolean verifyUserPassword(String providedPassword, String securedPassword, byte[] salt)
            throws NoSuchAlgorithmException {
        String newSecurePassword = getSecurePassword(providedPassword, salt);
        return newSecurePassword.equals(securedPassword);
    }

    public static void setInstance(Auth instance) {
        Auth.instance = instance;
    }

    public static Auth getInstance() {
        // Verifica si la instancia ya ha sido creada
        if (instance == null) {
            // Si no ha sido creada, crea una nueva instancia
            instance = new Auth();
        }
        // Retorna la instancia única
        return instance;
    }
}
