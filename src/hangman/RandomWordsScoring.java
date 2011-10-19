package hangman;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

/**
 * A class that is capable of running an experiment for testing
 * a strategy on a random set of words.
 */
public class RandomWordsScoring implements Scoreable {
    final RandomWordsRunConfiguration config;
    
    public RandomWordsScoring(RandomWordsRunConfiguration config) {
    	this.config = config;
    }    
    
	/* (non-Javadoc)
	 * @see hangman.Scoreable#score()
	 */
	@Override
	public void score() {
		List<String> wordsToScore = buildRandomWordsToScoreList();
		AbstractScoring.score(wordsToScore, config);		
	}

	/**
	 * Builds a list of words randomly selected from the valid words file,
	 * AKA dictionary. 
	 * @return a list of stings
	 */
	private List<String> buildRandomWordsToScoreList() {
		ArrayList<String> validWords = FileReader.readLines(config.getValidWords());		
		List<String> wordsToScore = new LinkedList<String>();
		
		Random randomGenerator = new Random(config.getRandomSeed());
		final int numOfWords = validWords.size();			
		int testSize = config.getRandomTestSize();

		for (int gameNumber = 1; gameNumber <= testSize; gameNumber++) {
			int randomIndex = randomGenerator.nextInt(numOfWords);
			String secretWord = validWords.get(randomIndex);
			wordsToScore.add(secretWord);
		}
		return wordsToScore;
	}
}
