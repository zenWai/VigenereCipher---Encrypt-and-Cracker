package main.java.com.presa;

import lib.duke.FileResource;
/**
 * This class serves as a tester for the CaesarCracker class.
 * It tests the getKey method of the CaesarCracker class with different languages
 * and encrypted messages.
 */
public class TesterCaesarCracker {
    /**
     * Main method for testing the CaesarCracker class.
     * It reads an encrypted file, determines the key used for encryption,
     * and prints it out for different languages (English and Portuguese in this case).
     * It assumes that the most common character in the encrypted text is 'e' for English
     * and 'a' for Portuguese.
     *
     * @param args Command line arguments, not used in this program.
     */
    public static void main(String[] args){
        String fr = new FileResource().asString();

        //test class CaesarCracker().getKey English
        // with file titus-small_key5.txt for example
        char commonCharEnglish = 'e';//e for english a for Portuguese
        CaesarCracker cCrackerEng = new CaesarCracker(commonCharEnglish);
        int keyFoundEng = cCrackerEng.getKey(fr);
        System.out.println("This message is crypted with key: " +
                keyFoundEng + "Considering message is in English");

        //test class CaesarCracker().getKey Portuguese
        // with file oslusiadas_key17.txt for example
        char commonCharPortuguese= 'a';//e for english a for Portuguese
        CaesarCracker cCrackerPt = new CaesarCracker(commonCharPortuguese);
        int keyFoundPt = cCrackerPt.getKey(fr);
        System.out.println("This message is crypted with key: " +
                keyFoundPt + "Considering message is in Portuguese");
    }
}

