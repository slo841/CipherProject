import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.*;

public class HelperMethods {
	public static final String ALPHABET = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789,.() '\"![]/%-_;?-=:"
			+ '\n' + '\r';
	public static final String SIMPLE_ALPHABET = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
	public static final String NEW_ALPHABET = "abcdefghijklmnopqrstuvwxyz";

	// Set this variable to the default alphabet you wish to use
	public static final String DEFAULT_ALPHABET = ALPHABET;
	
	private static Dictionary dictionary = Dictionary.buildDictionary("E:\\CipherBlankTemplate\\words.txt");
	
	
	public static int[] codeIndex(String code, String alphabet) {
		int[] arr = new int[code.length()];

		for (int i = 0; i < code.length(); i++) {
			int index = alphabet.indexOf(code.substring(i, i + 1));
			arr[i] = index;
		}
		return arr;
	}

	public static String slowReadFileAsString(String filepath) {
		String output = "";

		try (Scanner scanner = new Scanner(new File(filepath))) {
			while (scanner.hasNext()) {
				String line = scanner.nextLine();
				output += line;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return output;
	}
	
	/**
	 * returns true if plaintext is valid English.
	 * 
	 * @param plaintext
	 *            the text you wish to test for whether it's valid English
	 * @return boolean returns true if plaintext is valid English.
	 */
	public static boolean isEnglish(String plaintext) {
		String[] words = getWords(plaintext);
		int count = 0;
		
		for (int i = 0; i < words.length; i++) {
			if (dictionary.isWord(words[i])) count++;
		}
		double numOfWords = words.length * (3/4);
		
		if (count > numOfWords) return true;
		return false;
	}

	public static int getWordsArrayLength(String text) {
		int c = 0;
		for (int i = 0; i < text.length(); i++) {
			String letter = text.substring(i, i + 1);
			if (letter.equals(" ")) {
				c++;
			}
		}
		return c;
	}

	public static String[] getWords(String text) {
		text = text.trim();
		String[] words = new String[getWordsArrayLength(text)];
		String end = text.substring(text.length() - 1, text.length());
		if (!end.equals(" "))
			text += " ";

		for (int j = 0; j < words.length; j++) {
			for (int i = 0; i < text.length(); i++) {
				String letter = text.substring(i, i + 1);

				if (!letter.equals(" ")) {
					int index = text.indexOf(letter, i);
					int space = text.indexOf(" ", i);
					String word = text.substring(index, space);
					words[j] = word;
					i += getLengthOfWord(word);
				}
			}
		}
		return words;
	}

	public static int getLengthOfWord(String word) {
		int num = 0;
		for (int i = 0; i < word.length(); i++) {
			num++;
		}
		return num;
	}

	public static String getLettersOut(String cipher, int index, int length) {
		String group = "";
		
		for (int i = index; i < cipher.length(); i += length) {
			group += cipher.substring(i, i + 1);
		} return group;
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

			if (index < 0) {
				index += alphabet.length();
			}
			String newLetter = alphabet.substring(index, index + 1);
			decryptedText += newLetter;
		}
		return decryptedText;
	}

	public static String findCodeLetter(String cipherLetterGroup, String alphabet) {
		double minScore = 100.0;
		int bestShiftValue = 0;
		
		for (int shiftAmount = 0; shiftAmount < alphabet.length(); shiftAmount++) {
			String decoded = rotationCipherDecrypt(cipherLetterGroup, shiftAmount, alphabet);
			LetterBag bag = getBagForString(decoded);
			
			double score = getSimilarityToEnglishScore(bag);
			if (score < minScore) {
				minScore = score;
				bestShiftValue = shiftAmount;
			}
		} return alphabet.substring(bestShiftValue, bestShiftValue + 1);
	}
	
	private static double getSimilarityToEnglishScore(LetterBag bag) {
		double score = 10000.0;
		int letterE = bag.getNumOccurances("e");
		if (letterE >= 1) {
			double average = Math.round((letterE / (double) bag.getTotalLetters()) * 1000.0) / 100.0;
			score = (12.70 - average);
		}
		return score;
	}

	public static LetterBag getBagForString(String decoded) {
		LetterBag b = new LetterBag();
		
		for (int i = 0; i < decoded.length(); i++) {
			b.add(decoded.substring(i, i + 1));
		} return b;
	}
}