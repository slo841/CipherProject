
public class LetterBag {
	private final String alphabet = "abcdefghijklmnopqrstuvwxyz ";
	private int[] letterFrequencies;
	
	public LetterBag() {
		letterFrequencies = new int[alphabet.length()];
	}
	
	/**
	 * deletes all of the letters in letterFrequencies
	 */
	public void clear() {
		for (int i = 0; i < letterFrequencies.length; i++) {
			letterFrequencies[i] = 0;
		}
	}
	
	/**
	 * adds a new letter to the bag by adding one to 
	 * letterFrequencies, which corresponds to a letter from
	 * the alphabet (getIndexForLetter(String))
	 * 
	 * @param letter
	 *            the letter to be added to the bag
	 */
	public void add (String letter) {
		String lower = letter.toLowerCase();
		int index = getIndexForLetter(lower);
		letterFrequencies[index]++;
	}

	private int getIndexForLetter(String lower) {
		return alphabet.indexOf(lower);
	}

	/**
	 * returns total num of letters in bag
	 * 
	 * @param letter
	 *            the letter to be added to the bag
	 * @return returns total num of letters
	 */
	public int getTotalLetters() {
		int total = 0;
		for (int i = 0; i < letterFrequencies.length; i++) {
			total += letterFrequencies[i];
		} return total;
	}

	/**
	 * returns total num of unique letters in bag
	 * 
	 * @return returns total num non repeating letters
	 */
	public int getNumUniqueWords() {
		int count = 0;
		for (int i = 0; i < letterFrequencies.length; i++) {
			if (letterFrequencies[i] != 0) count++;	
		} return count;
	}
	
	/**
	 * returns num of times a letter appears in the bag
	 * 
	 *  @param letter
	 *            the letter to be tested for how frequently it appears.
	 * @return returns num of times letter appears
	 */
	public int getNumOccurances(String letter) {
		int index = getIndexForLetter(letter);
		return letterFrequencies[index];
	}

	/**
	 * returns most frequent letter that appears in the bag
	 * 
	 * @return returns most frequent letter
	 */
	public String getMostFrequent() {
		int max = -1;
		String mostFrequent = "";
		for (int i = 0; i < letterFrequencies.length; i++) {
			if (letterFrequencies[i] > max) {
				max = letterFrequencies[i];
				mostFrequent = alphabet.substring(i, i + 1);
			}
		} return mostFrequent;
	}
}
