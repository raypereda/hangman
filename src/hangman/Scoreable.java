package hangman;

/**
 * An interface for running experiments that test a hangman strategy.
 */
public interface Scoreable {
	/**
	 * runs the experiment that gives scoring of a hangman strategy.
	 */
	void score();
}