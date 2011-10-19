package hangman;

/**
 * This is a common interface for RandomWordsRunConfiguration and FixedWordsRunConfiguration.
 */
public interface RunConfiguration {
	/**
	 * @return something that can be scored
	 */
	Scoreable getScoring();
}
