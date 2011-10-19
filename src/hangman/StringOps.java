package hangman;

import java.util.HashSet;
import java.util.Set;

public class StringOps {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String s = "BOOKER";
		char firstGuess = rightMostVowel(s);
		System.out.println(firstGuess);
	}

	/**
	 * @param s a word with a vowel
	 * @return the right most most vowel in the word
	 */
	public static char rightMostVowel(String s) {
		Set<Character> vowels = new HashSet<Character>();	
		vowels.add('A');
		vowels.add('E');
		vowels.add('I');
		vowels.add('O');
		vowels.add('U');
		vowels.add('Y');
		
		char[] word = s.toCharArray();
		for (int i = s.length()-1; i > 0; i--) {
			if (vowels.contains(word[i])) {
				return word[i];
			}
		}
		// all words should have a vowel
		assert false;
		return 0;
	}

}
