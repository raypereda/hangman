package hangman;

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import combinations.Combos;

/**
 * This class provides a guessing strategy based on Shannon entropy.<br>
 * See http://en.wikipedia.org/wiki/Entropy_(information_theory)
 */
public class EntropyGuessingStrategy2  {
	private final Map<Integer, List<char[]>> partitionByLength;
	private final int lookAhead;

	public EntropyGuessingStrategy2(List<char[]> words, int lookAhead) {
		partitionByLength = Collections.unmodifiableMap(partitionByLength(words));
		this.lookAhead = lookAhead;
	}
	
	/* (non-Javadoc)
	 * @see hangman.GuessingStrategy#nextGuess(hangman.HangmanGame)
	 */
	public Guess nextGuess(HangmanGame2 game) {		
		List<char[]> words = filterPossibleWords(game);
		if (words.size() < 25) {
			 System.out.println(toString(words));							
		}
		// System.out.println(toString(words));				
				
		// filter out based on letters remaining in possible words
		Set<Character> guessableChars  = uniqueLetters(words);
		
		// filter out letters already guessed
		Set<Character> guessedLetters = game.getAllGuessedLetters();		
		guessableChars.removeAll(guessedLetters);
		
		// find the combinations of letter guesses with the max entropy
		int r = lookAhead;
		r = Math.min(r, guessableChars.size());
		r = Math.max(r, 1);

		ComboAndEntropy maxR = maxEntropy(words, guessableChars, r);
		
		// System.out.println(max.toString());
		
		ComboAndEntropy max1;
		Character maxCharacter1;
		
		if (maxR.combo.size() == 1) {
			max1 = maxR;
			maxCharacter1 = maxR.combo.iterator().next();
		} else {
			max1 = maxEntropyReduce(words, maxR.combo);
			maxCharacter1 = max1.combo.iterator().next();	
		}
		
		// System.out.printf("max1 = %3.3f \n", max1.entropy);
		
		// what would the entropy be if we guess a word rather than a letter?
		// if it is smaller than the best letter pick, then select the
		// first word in the list of possible words.
		int n = words.size();
		double entropyOfWordPick = entropyOfWordGuess(n);
		
		if (entropyOfWordPick >= max1.entropy) {
			return new GuessWord(new String(words.get(0)));
		} else {		
			return new GuessLetter(maxCharacter1);
		}
	}

	/**
	 * In order to avoid local maxima, we reduce the size of the best combinations
	 * of letters, one step at a time, until we have just a single letter.
	 * @param chars the set of possible letters from which to choose
	 * @return the single letter with the best entropy
	 */
	public ComboAndEntropy maxEntropyReduceIterative(List<char[]> words, Set<Character> chars) {
		ComboAndEntropy max = null;
		int r = chars.size();
		for (int i = r; i > 1; i--) {
			max = maxEntropy(words, chars, i);
		}
		return max;
	}


	/**
	 * Pick the single letter with the largest entropy.
	 * @param chars the set of possible letters from which to choose
	 * @return the single letter with the best entropy
	 */
	public ComboAndEntropy maxEntropyReduce(List<char[]> words, Set<Character> chars) {
		return maxEntropy(words, chars, 1);
	}
	
	
	
	/**
	 * @param words the list of valid words
	 * @param chars the set of possible letters from which to choose
	 * @param r the size of the subsets of chars to be considered
	 * @return the subset (or combination) of letters with the maximum entropy
	 */
	public ComboAndEntropy maxEntropy(List<char[]> words, Set<Character> chars, int r) {
		double         maxComboEntropy = Double.NEGATIVE_INFINITY;
		Set<Character> maxComboChars   = null;
		
		Character[] lettersArray = chars.toArray(new Character[chars.size()]);
		
		for (int i = 1; i <= r; i++) {
			Combos<Character> combos = new Combos<Character>(lettersArray, i);
			for (Set<Character> comboChars : combos) {
				Map<CharArray, List<char[]>> partition = Partition.makePartition(words, comboChars);
				// System.out.println(Partition.toString(partition));
				// compute the entropy per letter guess by dividing by i
				double entropy = Partition.entropy(partition) / (double) i ;
				//	System.out.printf("(combo, entropy) = (%s, %3.3f \n) ", comboChars.toString(), entropy);
				if (entropy > maxComboEntropy) {
					maxComboEntropy = entropy;
					maxComboChars = comboChars;
				}
			}
		}
		return new ComboAndEntropy(maxComboChars, maxComboEntropy);
	}

	
	/**
	 * @param game a hangman game
	 * @return a filter words using incorrect and correct guesses and length of secret word
	 */
	public List<char[]> filterPossibleWords(HangmanGame2 game) {
		// filter possible words based on length of secret word
		Integer length = game.getSecretWordLength();
		List<char[]> filtered = partitionByLength.get(length);
		
		// filter possible words based on correctly and incorrectly guessed letters		
		Set<Character> allGuessedLetters = game.getAllGuessedLetters();		
		char[] mask = game.getGuessedSoFar().toCharArray();
		char mysteryLetter = HangmanGame.MYSTERY_LETTER;
		filtered = EntropyGuessingStrategy2.filter(filtered, mask, allGuessedLetters, mysteryLetter);
		
		// filter possible words based on incorrectly guessed words		
		Set<String> incorrectWords = game.getIncorrectlyGuessedWords();
		filtered = EntropyGuessingStrategy2.filter(filtered, incorrectWords);
				
		return filtered;
	}


	/**
	 * This is for debugging. The list cuts off at 20 items
	 * @param words a list of of strings represented with char arrays
	 * @return a string representing of the list
	 */
	@SuppressWarnings("unused")
	private static String toString(List<char[]> words) {
		StringBuilder sb = new StringBuilder();
		sb.append("size = ");
		sb.append(words.size());
		sb.append(" ");
		int i = 0;
		for (char[] word : words) {
			sb.append(word);
			sb.append(", ");
			i++;
			if (i > 20) {
				break;
			}
		}
		return sb.toString();
	}

	/**
	 * @param words a list of words
	 * @return the set of all characters represented in the words
	 */
	private static Set<Character> uniqueLetters(List<char[]> words) {
		Set<Character> uniqueLetters = new TreeSet<Character>();
		for (char[] word : words) {
			for (char c : word) {
				uniqueLetters.add(c);
			}
			// a speed optimization, may remove later
			if (uniqueLetters.size() == 26) {
				break;
			}
		}
		return uniqueLetters;
	}
		
	/**
	 * @param words a list of words
	 * @return a partition of the words by length of word
	 */
	private static Map<Integer, List<char[]>> partitionByLength(List<char[]> words) {
		Map<Integer, List<char[]>> partition = new HashMap<Integer, List<char[]>>();
		for (char[] word : words) {
			Integer length = word.length;
			if (partition.containsKey(length)) {
				partition.get(length).add(word);
			} else {
				List<char[]> aNewPartition = new LinkedList<char[]>();
				aNewPartition.add(word);
				partition.put(length, aNewPartition);
			}
		}
		return partition;
	}
	
	/**
	 * Filters the words based on the mask.
	 * @param words a list of words
	 * @param mask a representation of the correctly guessed letters
	 * @param allGuessedLetters the set of all guessed letters
	 * @param mysteryLetter the mystery character used in the mask
	 * @return a copy of the word list with the words not matching the mask removed
	 */
	private static List<char[]> filter(List<char[]> words, char[] mask, Set<Character> allGuessedLetters, char mysteryLetter) {
		List<char[]> filtered = new LinkedList<char[]>();
		
		for (char[] word : words) {
			boolean passedFilter = true;
			for (int i = 0; i < word.length; i++) {
				if (mask[i] == mysteryLetter) {
					if (!allGuessedLetters.contains(word[i])) { 
						continue;
					} else {
						passedFilter = false;
						break;
					}
				} else { // not mystery letter
					if (mask[i] == word[i]) {
						continue;
					} else {
						passedFilter = false;
						break;
					}
				}
			}
			if (passedFilter) {
				filtered.add(word);
			}
		}
		return filtered;
	}
	
	/**
	 * Filters a the incorrect words from a list.
	 * @param words a list of words
	 * @param incorrectWords the set of incorrect words
	 * @return a list with the incorrect words removed
	 */
	private static List<char[]> filter(List<char[]> words, Set<String> incorrectWords) {
		List<char[]> filtered = new LinkedList<char[]>();
		for (char[] word : words) {
			String wordString = new String(word);
			if (!incorrectWords.contains(wordString)) {
				filtered.add(word);
			}
		}
		return filtered;
	}
	
	/**
	 * Rather than build the actual partition, here we compute the entropy
	 * directly.
	 * @param n the size of the word list
	 * @return the entropy from partition in two parts: one word and the rest.
	 */
	private double entropyOfWordGuess(int n) {
		assert(n > 0);
		// special case n equals 1, so it selected.
		if (n == 1) {
			return Double.POSITIVE_INFINITY;
		}
		
		double[] p = { (double)(n-1)/(double)n, 
				       (double)   1 /(double)n };
		double sum = 0.0;
		for (double p_i : p) {
			sum += p_i * Math2.log2(p_i);
		}
		return -sum;
	}
}