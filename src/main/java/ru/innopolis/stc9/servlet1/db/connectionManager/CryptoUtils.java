package ru.innopolis.stc9.servlet1.db.connectionManager;

import java.security.NoSuchAlgorithmException;

/**
 * Класс, выполняющий шифрование паролей
 */
public class CryptoUtils {

    /**
     * Метод, получающий строку, которую надо зашифровать
     *
     * @param stringToHash
     * @return массив байтов
     * @throws NoSuchAlgorithmException
     */
    public static byte[] computeHash(String stringToHash) throws NoSuchAlgorithmException {
        java.security.MessageDigest messageDigest;
        messageDigest = java.security.MessageDigest.getInstance("SHA-512");
        messageDigest.reset();
        messageDigest.update(stringToHash.getBytes());
        return messageDigest.digest();
    }

    /**
     * Метод, получающий массив байтов и выводящий строку
     *
     * @param b
     * @return String
     */
    public static String byteArrayToHexString(byte[] b) {
        StringBuffer sb = new StringBuffer(b.length * 2);
        for (int i = 0; i < b.length; i++) {
            int v = b[i] & 0xff;
            if (v < 16) {
                sb.append('0');
            }
            sb.append(Integer.toHexString(v));
        }
        return sb.toString().toUpperCase();
    }
}
