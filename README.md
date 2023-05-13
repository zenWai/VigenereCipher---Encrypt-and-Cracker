# VigenereCipher Encrypt and Cracker

Vigenere Cipher Decrypter is a Java backend project capable of encrypting and decrypting text encrypted with the Vigenere cipher. Utilizing a blend of statistical and dictionary-based techniques, this project supports multiple languages, including Danish, Dutch, English, French, German, Italian, Portuguese, and Spanish.
 
## Features
***Vigenere Cipher Encryption***: The program allows you to provide a message and a keyword to encrypt your message.

***Vigenere Cipher Decryption***: Given an encrypted message and the original keyword, the program can decrypt it.

***Brute Force Decryption***: The program attempts all possible keywords and evaluates each resulting decryption to find the most likely original message.

***Multi-language Support***: The program can handle messages in various languages, including Danish, Dutch, English, French, German, Italian, Portuguese, and Spanish.

***Language Detection***: During the brute-force decryption process, the program can detect the language of the decrypted message, improving the accuracy of the decryption process. It determines the potential language of the encrypted text based on the dictionaries provided.

## How it Works
The Vigenere Cipher is a polyalphabetic substitution cipher that employs a series of Caesar ciphers based on a keyword. Decrypting a message encrypted with the Vigenere Cipher requires knowledge of this keyword. However, this project ingeniously bypasses this requirement by leveraging a dictionary attack.

### Dictionary Attack
A dictionary attack involves matching words in the encrypted message with words in a known language. Here's how the project executes this:

***Analyzing the Encrypted Text***: The program performs frequency analysis on the text for each supported language, identifying the most commonly occurring character.

***Guessing the Key***: The program tries different key lengths (ranging from 1 to 100). For each key length, it slices the encrypted message into pieces. Each piece contains characters that were encrypted with the same Caesar cipher. The key for each cipher is then guessed based on the frequency analysis.

***Decrypting the Message***: Using the guessed keys, the program decrypts the message.

***Evaluating the Decryption***: The program counts the number of words in the decrypted message that exist in the language's dictionary.

***Optimizing the Result***: The decrypted result with the highest count of dictionary words is selected as the most likely correct decryption.

By leveraging a dictionary attack, this project can successfully break the Vigenere Cipher without prior knowledge of the keyword, basing its guesses on the characteristics of the language in which the original message was written.

This project was created for educational purposes.
