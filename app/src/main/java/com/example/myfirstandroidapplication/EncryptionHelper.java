package com.example.myfirstandroidapplication;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.spec.KeySpec;

public class EncryptionHelper {
    public static byte[] encrypt(String text, String password) throws Exception {
        Cipher cipher = Cipher.getInstance("AES");
        SecretKey secretKey = generateKey(password);
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        return cipher.doFinal(text.getBytes());
    }

    public static String decrypt(byte[] encryptedText, String password) throws Exception {
        Cipher cipher = Cipher.getInstance("AES");
        SecretKey secretKey = generateKey(password);
        cipher.init(Cipher.DECRYPT_MODE, secretKey);
        return new String(cipher.doFinal(encryptedText));
    }

    private static SecretKey generateKey(String password) throws Exception {
        byte[] salt = new byte[] { 1, 2, 3, 4, 5, 6, 7, 8 };
        KeySpec keySpec = new PBEKeySpec(password.toCharArray(), salt, 65536, 256);
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
        byte[] keyBytes = keyFactory.generateSecret(keySpec).getEncoded();
        return new SecretKeySpec(keyBytes, "AES");
    }
}
