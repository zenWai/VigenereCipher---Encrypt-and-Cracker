package main.java.com.presa;

import java.util.Arrays;

/**
 * This class provides methods for encrypting and decrypting messages using Vigenere Cipher.
 * @author zenWai
 */
public class VigenereCipher {
    CaesarCipher[] ciphers;

    /**
     * Constructs an array of CaesarCipher objects.
     * @param key An array of integers to be used as keys to initialize the CaesarCipher objects.
     */
    public VigenereCipher(int[] key) {
        ciphers = new CaesarCipher[key.length];
        for ( int i = 0; i < key.length; i++ ) {
            ciphers[i] = new CaesarCipher(key[i]);
        }
    }

    /**
     * Encrypts the input string using Vigenere Cipher.
     * @param input The string to be encrypted.
     * @return The encrypted string.
     */
    public String encrypt(String input) {
        StringBuilder answer = new StringBuilder();
        int i = 0;
        for ( char c : input.toCharArray() ) {
            int cipherIndex = i % ciphers.length;
            CaesarCipher thisCipher = ciphers[cipherIndex];
            answer.append(thisCipher.encryptLetter(c));
            i++;
        }
        return answer.toString();
    }

    /**
     * Decrypts the input string using Vigenere Cipher.
     * @param input The string to be decrypted.
     * @return The decrypted string.
     */
    public String decrypt(String input) {
        StringBuilder answer = new StringBuilder();
        int i = 0;
        for ( char c : input.toCharArray() ) {
            int cipherIndex = i % ciphers.length;
            CaesarCipher thisCipher = ciphers[cipherIndex];
            answer.append(thisCipher.decryptLetter(c));
            i++;
        }
        return answer.toString();
    }

    public String toString() {
        return Arrays.toString(ciphers);
    }

}
