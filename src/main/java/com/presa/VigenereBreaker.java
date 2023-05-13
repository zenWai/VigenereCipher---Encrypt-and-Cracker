package main.java.com.presa;

import lib.duke.FileResource;

import java.util.HashMap;
import java.util.HashSet;

/**
 * This class provides methods for breaking the Vigenere Cipher. It includes methods
 * for slicing the encrypted message, finding the key length, reading a dictionary,
 * counting valid words, and deciphering the message.
 * @author zenWai
 */
public class VigenereBreaker {

    private static String dataSourceDirectory = "data/Cryptography/dictionaries";
    private static HashMap< String, HashSet< String > > langs = new HashMap< String, HashSet< String > >();

    /**
     * Constructs a VigenereBreaker with pre-loaded dictionaries in multiple languages.
     * The languages are: Danish, Dutch, English, French, German, Italian, Portuguese, Spanish.
     */
    public VigenereBreaker() {
        String lang[] = {"Danish", "Dutch", "English", "French", "German", "Italian", "Portuguese", "Spanish"};
        for ( int i = 0; i < lang.length - 1; i++ ) {
            FileResource fr = new FileResource(dataSourceDirectory + "/" + lang[i]);
            langs.put(lang[i], readDictionary(fr));
        }

    }

    /**
     * Returns a string that is every totalSlices-th character from message, starting at the whichSlice-th character.
     *
     * @param message The string to be sliced.
     * @param whichSlice The starting index for the slice.
     * @param totalSlices The interval to be used for the slice.
     * @return A string made up of every totalSlices-th character from message, starting at the whichSlice-th character.
     */
    public String sliceString(String message, int whichSlice, int totalSlices) {
        StringBuilder slicedMessage = new StringBuilder();
        for ( int i = whichSlice; i < message.length(); i = i + totalSlices ) {
            slicedMessage.append(message.charAt(i));
        }
        return slicedMessage.toString();
    }

    /**
     * Returns an array of integers representing a potential key for a Caesar cipher encrypted message.
     *
     * @param encrypted The encrypted message.
     * @param klength The length of the key to try.
     * @param mostCommon The most common character in the language of the original (pre-encrypted) message.
     * @return An integer array representing the potential key.
     */
    public int[] tryKeyLength(String encrypted, int klength, char mostCommon) {
        CaesarCracker cracker = new CaesarCracker(mostCommon);
        int[] key = new int[klength];
        for ( int i = 0; i < klength; i++ ) {
            String sliced = sliceString(encrypted, i, klength);
            int keyForSliced = cracker.getKey(sliced);
            key[i] = keyForSliced;
        }
        return key;
    }

    /**
     * Reads a file selected by the user, and attempts to break the Vigenere cipher in all pre-loaded languages.
     */
    public void breakVigenere() {
        System.out.println("Select encrypted file");
        FileResource crypted = new FileResource();
        breakForAllLangs(crypted.asString(), langs);

    }

    /**
     * Reads the dictionary file and stores words in a HashSet.
     * @param fr The FileResource object representing the dictionary file.
     * @return The HashSet containing all the words from the dictionary file.
     */
    public HashSet< String > readDictionary(FileResource fr) {
        HashSet< String > wordsDictionary = new HashSet<>();
        for ( String w : fr.lines() ) {
            wordsDictionary.add(w.toLowerCase());
        }
        return wordsDictionary;
    }

    /**
     * Counts the number of valid words in the message.
     * @param message The string message to be checked.
     * @param dictionary The HashSet containing all the valid words.
     * @return The number of valid words in the message.
     */
    public int countWords(String message, HashSet< String > dictionary) {
        int validWords = 0;
        String[] messageSplited = message.toLowerCase().split("\\W+");
        for ( String s : messageSplited ) {
            if ( dictionary.contains(s) ) validWords++;
        }
        return validWords;
    }

    /**
     * Breaks the Vigenere Cipher for a particular language.
     *
     * This method tries multiple keys of varying lengths (from 1 to 100) to decrypt the given encrypted message.
     * For each decrypted message, it counts the number of valid words according to the provided dictionary.
     * The decrypted message with the highest number of valid words is likely the correct decryption.
     *
     * @param encrypted The encrypted string message.
     * @param dictionary The HashSet containing the dictionary of the language.
     * @return The decrypted string message that has the most valid words according to the language's dictionary.
     */
    public String breakForLanguage(String encrypted, HashSet< String > dictionary) {
        char mostCommon = mostCommonCharIn(dictionary);
        int rightWords = 0;
        String decryption = "";
        for ( int i = 1; i <= 100; i++ ) {
            int[] possibleKey = tryKeyLength(encrypted, i, mostCommon);
            VigenereCipher vCipher = new VigenereCipher(possibleKey);
            String decrypted = vCipher.decrypt(encrypted);
            int rightWordsInDict = countWords(decrypted, dictionary);

            if ( rightWordsInDict > rightWords ) {
                rightWords = rightWordsInDict;
                decryption = decrypted;
            }
        }
        return decryption;
    }

    /**
     * Determines the most common character in the dictionary of a specific language.
     *
     * This method iterates over each word in the provided dictionary,
     * counting the occurrences of each character.
     * It then identifies the character with the highest count and returns it.
     * For example according to the letter frequency analyses of English language texts
     * 'e' is the most commonly used letter and it will be the return of this method
     *
     * @param dictionary The HashSet containing the dictionary of the language.
     * @return The most common character in the words of the provided dictionary.
     */
    public char mostCommonCharIn(HashSet< String > dictionary) {
        HashMap< Character, Integer > charCounter = new HashMap<>();
        for ( String word : dictionary ) {
            for ( char fromWord : word.toLowerCase().toCharArray() ) {
                if ( !charCounter.containsKey(fromWord) ) charCounter.put(fromWord, 1);
                else charCounter.put(fromWord, charCounter.get(fromWord) + 1);
            }
        }

        char mostCommon = 0;
        int max = 0;
        for ( char c : charCounter.keySet() ) {
            if ( charCounter.get(c) > max ) {
                max = charCounter.get(c);
                mostCommon = c;
            }
        }
        return mostCommon;
    }

    /**
     * Breaks the Vigenere Cipher for multiple languages.
     *
     * This method iterates over a collection of languages and their respective dictionaries.
     * For each language, it attempts to break the given encrypted message using the language's dictionary.
     * The language that results in the highest number of valid words in the decrypted message is likely the original language of the message.
     * The method then prints out the most likely language and the decrypted message.
     *
     * @param encrypted The encrypted string message.
     * @param language The HashMap containing the mapping of language name to its dictionary (HashSet of words).
     */
    public void breakForAllLangs(String encrypted,
                                 HashMap< String, HashSet< String > > language) {
        int maxRight = 0;
        String languageRight = null;
        String decryptedRight = null;
        for ( String w : language.keySet() ) {
            String trying = breakForLanguage(encrypted, language.get(w));
            if ( countWords(trying, language.get(w)) > maxRight ) {
                maxRight = countWords(trying, language.get(w));
                languageRight = w;
                decryptedRight = trying;
            }
        }
        System.out.println("Language is: " + languageRight +
                " and the message is:\n" + decryptedRight);
    }
}
