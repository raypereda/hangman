package hangman;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * This version of Hangman is used to playing Hanging with Friends.<br>
 * 
 * This class simulates a word guessing game called Hangman.
 * Hangman is a paper and pencil guessing game for two players. 
 * One player thinks of a word and the other tries to guess it by suggesting letters.
 * A more detailed description of the game came be found at
 * <a href="http://en.wikipedia.org/wiki/Hangman_(game)">Wikipedia entry Hangman</a> 
 */
public class HangmanGame2 {

  /**
   * A marker for the letters in the secret words that have not been guessed yet.
   */
  public static final Character MYSTERY_LETTER = '-';

  /**
   * The letters guessed so far (unknown letters will be marked by the MYSTERY_LETTER constant). For example, 'F-CTU-L'
   */
  private final char[] guessedSoFar;

  /**
   * Set of all correct letter guesses so far (e.g. 'C', 'F', 'L', 'T', 'U')
   */
  private final Set<Character> correctlyGuessedLetters;

  /**
   * Set of all incorrect letter guesses so far (e.g. 'R', 'S')
   */
  private final Set<Character> incorrectlyGuessedLetters;

  /**
   * Set of all incorrect word guesses so far (e.g. 'FACTORS')
   */
  private final Set<String> incorrectlyGuessedWords;
    
  /**
   * @param secretWord The word that needs to be guessed
   * @param maxWrongGuesses The maximum number of incorrect word/letter guesses that are allowed
   */
  public HangmanGame2(char[] guessedSoFar, Set<Character> incorrectlyGuessedLetters) {
      this.guessedSoFar = guessedSoFar;
      correctlyGuessedLetters = new HashSet<Character>();
      for (Character c : guessedSoFar) {
    	  if (!c.equals(MYSTERY_LETTER)) {
    		  correctlyGuessedLetters.add(c);
    	  }
      }
      this.incorrectlyGuessedLetters = incorrectlyGuessedLetters;
      incorrectlyGuessedWords = new HashSet<String>();
  }


  /**
   * @return The string representation of the current game state
   * (which will contain MYSTERY_LETTER in place of unknown letters)
   */
  public String getGuessedSoFar() {
    return new String(guessedSoFar);
  }

  /**
   * @return Set of all correctly guessed letters so far
   */
  public Set<Character> getCorrectlyGuessedLetters() {
    return Collections.unmodifiableSet(correctlyGuessedLetters);
  }

  /**
   * @return Set of all incorrectly guessed letters so far
   */
  public Set<Character> getIncorrectlyGuessedLetters() {
    return Collections.unmodifiableSet(incorrectlyGuessedLetters);
  }

  /**
   * @return Set of all guessed letters so far
   */
  public Set<Character> getAllGuessedLetters() {
    Set<Character> guessed = new HashSet<Character>();
    guessed.addAll(correctlyGuessedLetters);
    guessed.addAll(incorrectlyGuessedLetters);
    return guessed;
  }

  /**
   * @return Set of all incorrectly guessed words so far
   */
  public Set<String> getIncorrectlyGuessedWords() {
    return Collections.unmodifiableSet(incorrectlyGuessedWords);
  }

  /**
   * @return The length of the secret word
   */
  public int getSecretWordLength() {
    return guessedSoFar.length;
  }


	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "HangmanGame2 [guessedSoFar=" + Arrays.toString(guessedSoFar)
				+ ", correctlyGuessedLetters=" + correctlyGuessedLetters
				+ ", incorrectlyGuessedLetters=" + incorrectlyGuessedLetters + "]";
	}

	public static void main(String[] args) {
		char[] guessedSoFar = {'C', 'R', 'A', '-',  '-', '-'};
		Set<Character> incorrectlyGuessedLetters = new HashSet<Character>();
		String incorrectlyGuessedLettersStr = "HST";
		for (Character c : incorrectlyGuessedLettersStr.toCharArray()) {
			incorrectlyGuessedLetters.add(c);
		}
		HangmanGame2 game = new HangmanGame2(guessedSoFar, incorrectlyGuessedLetters);
		int lookAhead = 1;
		String validWordsFilename = "words.txt";
		List<char[]> words = FileReader.readLinesAsArrays(validWordsFilename);
		
		EntropyGuessingStrategy2 guessingStrategy = new EntropyGuessingStrategy2(words, lookAhead);
		Guess guess = guessingStrategy.nextGuess(game);
		System.out.println(guess);
	}

}