package hangman;

import java.util.List;

/**
 * A class that is capable of running an experiment for testing
 * a strategy on a fixed set of words.
 */
public class FixedWordsScoring implements Scoreable {
    private FixedWordsRunConfiguration config;
    
    public FixedWordsScoring(FixedWordsRunConfiguration config) {
    	this.config = config;
    }
    
	/* (non-Javadoc)
	 * @see hangman.Scoreable#score()
	 */
	@Override
	public void score() {		
		List<String> wordsToScore = FileReader.readLines(config.getWordsToScore());
		AbstractScoring.score(wordsToScore, config);	
	}
}