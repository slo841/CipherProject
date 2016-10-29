import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.*;


public class Cipher {
	public static final String ALPHABET = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789,.() '\"![]/%-_;?-=:"
			+ '\n' + '\r';
	public static final String SIMPLE_ALPHABET = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
	public static final String NEW_ALPHABET = "abcdefghijklmnopqrstuvwxyz";

	// Set this variable to the default alphabet you wish to use
	public static final String DEFAULT_ALPHABET = ALPHABET;
	
	private static Dictionary dictionary = Dictionary.buildDictionary("E:\\CipherBlankTemplate\\words.txt");
	
	/**
	 * Returns plaintext encrypted by the vigenere cipher encoded with the
	 * String code
	 * 
	 * @param alphabet
	 *            the alphabet to be used for the encryption
	 * @param plainText
	 *            the plain text to be encrypted.
	 * @param code
	 *            the code to use as the encryption key.
	 * @return returns the encrypted plainText.
	 */
	public static String vigenereCipherEncrypt(String plain, String password, String alphabet) {
		String encryptedCode = "";
		int[] codeIndex = HelperMethods.codeIndex(password, alphabet);
		int num = 0;

		for (int i = 0; i < plain.length(); i++) {
			int index = alphabet.indexOf(plain.substring(i, i + 1));
			index = (index + codeIndex[num]) % alphabet.length();
			num++;
				
			if (num == codeIndex.length)
				num = 0;
			String newLetter = alphabet.substring(index, index + 1);
			encryptedCode += newLetter;
		}
		return encryptedCode;
	}

	/**
	 * Returns the result of decrypting cipherText with the key code.
	 * 
	 * @param alphabet
	 *            the alphabet to be used for the encryption
	 * @param cipherText
	 *            the encrypted text.
	 * @param code
	 *            the decryption key
	 * @return returns the decrypted cipherText.
	 */
	public static String vigenereCipherDecrypt(String cipher, String password, String alphabet) {
		String decryptedCode = "";
		int[] codeIndex = HelperMethods.codeIndex(password, alphabet);
		int num = 0;

		for (int i = 0; i < cipher.length(); i++) {
			int index = alphabet.indexOf(cipher.substring(i, i + 1));
			index = (index - codeIndex[num]);
			if (index < 0) index += alphabet.length();
			num++;

			if (num == codeIndex.length)
				num = 0;
			String newLetter = alphabet.substring(index, index + 1);
			decryptedCode += newLetter;
		}
		return decryptedCode;
	}
	
	/**
	 * Returns plaintext encrypted by the rotation cipher with a shift of
	 * movement.
	 * 
	 * @param alphabet
	 *            the alphabet to be used for the encryption
	 * @param plain
	 *            the plain text to be encrypted.
	 * @param shift
	 *            the number of characters in ALPHABET to shift by.
	 * @return returns the encrypted plainText.
	 */
	public static String rotationCipherEncrypt(String plain, int shift, String alphabet) {
		String encryptedText = "";
		int index = 0;
		for (int i = 0; i < plain.length(); i++) {
			index = alphabet.indexOf(plain.substring(i, i + 1));

			index = (index + shift) % alphabet.length();

			if (shift < 0) {
				shift = alphabet.length() + shift;
			}
			String newLetter = alphabet.substring(index, index + 1);
			encryptedText += newLetter;
		}
		return encryptedText;
	}

	/**
	 * Returns the result of decrypting cipherText by shiftAmount using the
	 * rotation cipher.
	 * 
	 * @param alphabet
	 *            the alphabet to be used for the encryption
	 * @param cipher
	 *            the encrypted text.
	 * @param shift
	 *            the key to decrypt the cipher.
	 * @return returns the decrypted cipherText.
	 */
	public static String rotationCipherDecrypt(String cipher, int shift, String alphabet) {
		String decryptedText = "";

		for (int i = 0; i < cipher.length(); i++) {
			int index = alphabet.indexOf(cipher.substring(i, i + 1));

			index = (index - shift);

			while (index < 0) {
				index += alphabet.length();
			}
			String newLetter = alphabet.substring(index, index + 1);
			decryptedText += newLetter;
		}
		return decryptedText;
	}

	/**
	 * Returns the result of decrypting cipherText by shiftAmount using the
	 * rotation cipher and BRUTE FORCING IT.
	 * 
	 * @param alphabet
	 *            the alphabet to be used for the encryption
	 * @param cipherText
	 *            the encrypted text.
	 * @return returns the decrypted cipherText.
	 */
	public static String rotationCipherCrack (String cipher, String alphabet) {
		int rotationShift = 0;
		String plaintext = "";
		while (!HelperMethods.isEnglish(plaintext)) {
			plaintext = rotationCipherDecrypt(cipher, rotationShift, alphabet);
			if (HelperMethods.isEnglish(plaintext))
				break;
			rotationShift++;
		}
		return plaintext;
	}

	public static String vigenereCipherCrackThreeLetter(String cipher, String alphabet) {
		String password = "";
		for (int i = 0; i < 3; i++) {
			String group = HelperMethods.getLettersOut(cipher, i, 3);
			
			password += HelperMethods.findCodeLetter(group, alphabet);
		} return vigenereCipherDecrypt(cipher, password, alphabet);
	}
	
	public static String vigenereCipherCrack(String cipher, int passwordLength, String alphabet) {
		String password = "";
		for (int i = 0; i < passwordLength; i++) {
			String group = HelperMethods.getLettersOut(cipher, i, passwordLength);
			
			password += HelperMethods.findCodeLetter(group, alphabet);
		} return vigenereCipherDecrypt(cipher, password, alphabet);
	}
}