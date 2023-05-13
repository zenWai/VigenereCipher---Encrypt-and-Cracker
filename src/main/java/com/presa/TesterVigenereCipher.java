package main.java.com.presa;

import lib.duke.FileResource;

/**
 * This class tests the VigenereCipher class by creating an instance and encrypting
 * and decrypting a message.
 */
public class TesterVigenereCipher {
    public static void main(String[] args){
        FileResource fr = new FileResource();
        String frr = fr.asString();

            //test class VigenereCipher()
            //with titus-small.txt for example
            String key = "rome";
            int[] stuff = {17, 14, 12, 4};
            VigenereCipher vCipher = new VigenereCipher(stuff);
            //test cc.encrypt
            String vcEncrypted = vCipher.encrypt(frr);
            System.out.println("Encrypted Vigenere Cipher message with key " + key +
                    ":\n" + vcEncrypted);
            //test cc.decrypt
            String vCipherCracked = vCipher.decrypt(vcEncrypted);
            System.out.println("Decrypted Vigenere Cipher message with key " + key +
                    ":\n" + vCipherCracked);
    }
}

