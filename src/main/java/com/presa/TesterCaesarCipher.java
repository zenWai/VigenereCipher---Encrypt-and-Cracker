package main.java.com.presa;

import lib.duke.FileResource;

public class TesterCaesarCipher {
    public static void main(String[] args){
        String fr = new FileResource().asString();

        //test class CaesarCipher().encrypt
        //with titus-small.txt for example
        int ccKey = 4;
        CaesarCipher ccipher = new CaesarCipher(ccKey);
        //test cc.encrypt
        String ccEncrypted = ccipher.encrypt(fr);
        System.out.println("Encrypted Caesar Cipher message with key " + ccKey +
                ":\n" + ccEncrypted);
        //test cc.decrypt
        String cCipherCracked = ccipher.decrypt(ccEncrypted);
        System.out.println("Decrypted Caesar Cipher message with key " + ccKey +
                ":\n" + cCipherCracked);
    }
}


