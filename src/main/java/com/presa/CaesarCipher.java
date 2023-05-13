package main.java.com.presa;

/**
 * This class provides methods for encrypting and decrypting messages using Caesar Cipher.
 * It includes methods for transformation, encryption, decryption and other helper methods.
 * @author zenWai
 */
public class CaesarCipher {
    private String alphabet;
    private String shiftedAlphabet;
    private int theKey;
    
    public CaesarCipher(int key) {
        theKey = key;
        alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        shiftedAlphabet = alphabet.substring(key) +
                            alphabet.substring(0,key);
        alphabet = alphabet + alphabet.toLowerCase();
        shiftedAlphabet = shiftedAlphabet + shiftedAlphabet.toLowerCase();
    }

    /**
     * Transforms a letter using the from and to string parameters.
     * @param c The character to be transformed.
     * @param from The original alphabet string.
     * @param to The transformed alphabet string.
     * @return The transformed character.
     */
    private char transformLetter(char c, String from, String to) {
        int idx = from.indexOf(c);
        if (idx != -1) {
            return to.charAt(idx);
        }
        return c;
    }

    /**
     * Encrypts a letter using Caesar Cipher.
     * @param c The character to be encrypted.
     * @return The encrypted character.
     */
    public char encryptLetter(char c) {
        return transformLetter(c, alphabet, shiftedAlphabet);
    }

    /**
     * Decrypts a letter using Caesar Cipher.
     * @param c The encrypted character.
     * @return The decrypted character.
     */
    public char decryptLetter(char c) {
        return transformLetter(c, shiftedAlphabet, alphabet);
    }

    /**
     * Transforms the input string using the from and to string parameters.
     * @param input The input string to be transformed.
     * @param from The original alphabet string.
     * @param to The transformed alphabet string.
     * @return The transformed string.
     */
    private String transform(String input, String from, String to){
        StringBuilder sb = new StringBuilder(input);
        for (int i = 0; i < sb.length(); i++) {
            char c = sb.charAt(i);
            c = transformLetter(c, from, to);
            sb.setCharAt(i, c);
        }
        return sb.toString();
    }

    /**
     * Encrypts the input string using Caesar Cipher.
     * @param input The string to be encrypted.
     * @return The encrypted string.
     */
    public String encrypt(String input) {
        return transform(input, alphabet, shiftedAlphabet);
    }

    /**
     * Decrypts a string using the stored Caesar Cipher key.
     *
     * @param input The string to be decrypted.
     * @return The decrypted string, where each character has been shifted back by the key length in the alphabet.
     */
    public String decrypt(String input) {
        return transform(input, shiftedAlphabet, alphabet);
    }
    
    public String toString() {
        return "" + theKey;
    }
    
}
