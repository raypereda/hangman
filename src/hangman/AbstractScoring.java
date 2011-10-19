package hangman;

import java.util.List;

/**
 * This class holds the common method score() used by both FixedWordsScoring and
 * RandomWordsScoring.
 */
public class AbstractScoring {
    
    /**
	 * @param wordsToScore
	 */
	public static void score(List<String> wordsToScore, AbstractRunConfiguration config) {
		List<char[]> validWords = FileReader.readLinesAsArrays(config.getValidWords());
		int totalScore = 0;				
		int gameNumber = 0;

		int lookAhead = config.getLookAhead();
		GuessingStrategy guessingStrategy = new EntropyGuessingStrategy(validWords, lookAhead);		

		long startTime = Timer.startTime();
		
		for (String secretWord : wordsToScore) {
			gameNumber++;
			
			HangmanGame game = new HangmanGame(secretWord, config.getMaxWrongGuesses());

			int score = game.score(guessingStrategy);
			
			System.out.printf("%3d. secret word = %-30s score = %2d",
					gameNumber,
					secretWord,
					score);
			System.out.println();

			totalScore += score;
		}
		System.out.println();
		System.out.printf("total score = %5d", totalScore);
		System.out.println();
	
	    System.out.printf("scoring " + Timer.elapsedTime(startTime));
	    System.out.println();
	}
}
