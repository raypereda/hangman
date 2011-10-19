package hangman;

/**
 * This class provides a simple container for the items that are common
 * to both RandomWordsConfigurations and FixedWordsConfigurations.
 */
public abstract class AbstractRunConfiguration implements RunConfiguration {
	private final String validWords;
	private final int maxWrongGuesses;
	private final int lookAhead;
	
	public AbstractRunConfiguration(String validWords, int maxWrongGuesses, int lookAhead) {
		this.validWords = validWords;
		this.maxWrongGuesses = maxWrongGuesses;
		this.lookAhead = lookAhead;
	}

	public String getValidWords() {
		return validWords;
	}	
	
	public int getMaxWrongGuesses() {
		return maxWrongGuesses;
	}

	public int getLookAhead() {
		return lookAhead;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "AbstractRunConfiguration [validWords=" + validWords
				+ ", maxWrongGuesses=" + maxWrongGuesses + "]";
	}

	public abstract Scoreable getScoring();	
}