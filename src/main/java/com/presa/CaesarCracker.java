package main.java.com.presa;

/**
 * This class provides methods for cracking a Caesar Cipher. It includes methods
 * for counting letters, finding the maximum index, getting the key and decrypting
 * the message.
 * @author zenWai
 */
public class CaesarCracker {
    char mostCommon;

    public CaesarCracker() {
        mostCommon = 'e';
    }

    public CaesarCracker(char c) {
        mostCommon = c;
    }

    /**
     * Counts the number of each letter in the message.
     * @param message The string message.
     * @return An array of integers representing the count of each letter in the message.
     */
    public int[] countLetters(String message) {
        String alph = "abcdefghijklmnopqrstuvwxyz";
        int[] counts = new int[26];
        for ( int k = 0; k < message.length(); k++ ) {
            int dex = alph.indexOf(Character.toLowerCase(message.charAt(k)));
            if ( dex != -1 ) {
                counts[dex] += 1;
            }
        }
        return counts;
    }

    /**
     * Finds the index of the maximum value in an integer array.
     * @param vals The integer array.
     * @return The index of the maximum value.
     */
    public int maxIndex(int[] vals) {
        int maxDex = 0;
        for ( int k = 0; k < vals.length; k++ ) {
            if ( vals[k] > vals[maxDex] ) {
                maxDex = k;
            }
        }
        return maxDex;
    }

    /**
     * Gets the decryption key.
     * @param encrypted The encrypted string message.
     * @return The decryption key.
     */
    public int getKey(String encrypted) {
        int[] freqs = countLetters(encrypted);
        int maxDex = maxIndex(freqs);
        int mostCommonPos = mostCommon - 'a';
        int dkey = maxDex - mostCommonPos;
        if ( maxDex < mostCommonPos ) {
            dkey = 26 - (mostCommonPos - maxDex);
        }
        return dkey;
    }

    /**
     * Decrypts the encrypted string message.
     * @param encrypted The encrypted string message.
     * @return The decrypted string message.
     */
    public String decrypt(String encrypted) {
        int key = getKey(encrypted);
        CaesarCipher cc = new CaesarCipher(key);
        return cc.decrypt(encrypted);
    }
}
