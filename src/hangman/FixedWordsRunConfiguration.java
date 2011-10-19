package hangman;

/**
 * Holds all the inputs needed to run an experiment 
 * with a fixed set of words.
 */
public class FixedWordsRunConfiguration extends AbstractRunConfiguration {
	private String wordsToScore;
	
	public FixedWordsRunConfiguration(String validWords, int maxWrongGuesses, int lookAhead, String wordsToScore) {
		super(validWords, maxWrongGuesses, lookAhead);
		this.wordsToScore = wordsToScore;
	}
	
	/* (non-Javadoc)
	 * @see hangman.AbstractRunConfiguration#getScoring()
	 */
	@Override
	public Scoreable getScoring() {
		return new FixedWordsScoring(this);
	}

	public String getWordsToScore() {
		return wordsToScore;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "FixedWordsRunConfiguration [wordsToScore=" + wordsToScore + "]";
	}
}
