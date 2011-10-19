package hangman;

/**
 * A class that holds the configuration data needed to run a scoring of random words.
 */
public class RandomWordsRunConfiguration extends AbstractRunConfiguration {
	private final int randomSeed;
	private final int randomTestSize;
	
	/**
	 * @param validWords the list of valid words, aka dictionary
	 * @param maxWrongGuesses maximum number of wrong guesses allowed
	 * @param randomSeed the random number generator seed
	 * @param randomTestSize the size of the randomly selected words list
	 */
	public RandomWordsRunConfiguration(String validWords, int maxWrongGuesses, int lookAhead, int randomSeed, int randomTestSize) {
		super(validWords, maxWrongGuesses, lookAhead);
		this.randomSeed = randomSeed;
		this.randomTestSize = randomTestSize;	
	}

	/* (non-Javadoc)
	 * @see hangman.AbstractRunConfiguration#getScoring()
	 */
	@Override
	public Scoreable getScoring() {
		return new RandomWordsScoring(this);
	}

	/**
	 * @return the random number generator seed
	 */
	public int getRandomSeed() {
		return randomSeed;
	}

	/**
	 * @return the size of the list of randomly selected words
	 */
	public int getRandomTestSize() {
		return randomTestSize;
	}
}