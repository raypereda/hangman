package hangman;
import java.io.File;


/**
 * A class that parses a command-line with various options.
 * It checks for errors and produces a configuration that
 * specifies all inputs required to running a hangman strategy
 * experiment.
 */
public class CommandLineParser {

	public static void main(String[] args) {
		try {
			CommandLineParser commandLineParser = new CommandLineParser();
			RunConfiguration runConfig = commandLineParser.parseArguments(args);
			Scoreable scoring = runConfig.getScoring();
			scoring.score();
		} catch (CommandLineException e) {
			System.err.println(e.getMessage());
			CommandLineParser.printUsageMessage();
			System.exit(1);					
		}
	}

	/**
	 * @param args command-line arguments
	 * @return a validated run configuration
	 */
	public RunConfiguration parseArguments(String[] args) {
        // the fields for building configuration with default values
		String validWords = null;
		int maxWrongGuesses = 5;
		String wordsToScore = null;		
		int randomSeed = 0;	
		int lookAhead = 1;	
		int randomTestSize = 100;
		
		// print usage message if no arguments
		if (args.length == 0) {
			throw new CommandLineException("");
		}		

		for (String arg : args) {			
			if (!arg.startsWith("--")) {
				throw new CommandLineException("bad option: " + arg);
			}

			// remove the '--' option prefix
			arg = arg.substring(2);
			String[] optionParts = arg.split("=", 2);

		
			if ((optionParts.length == 1) && 
					(optionParts[0].equals("help"))) {
				throw new CommandLineException("");
			}

			if (optionParts.length == 2) {
				if (optionParts[0].equals("max-wrong-guesses")) {
					try {
						int radix = 10;
						maxWrongGuesses = Integer.parseInt(optionParts[1], radix);
					} catch (NumberFormatException e) {
						throw new CommandLineException("Invalid max wrong guesses: " + optionParts[1]);
					}
					if ((maxWrongGuesses < 0) || (maxWrongGuesses > 25)) {
						throw new CommandLineException("Max wrong guesses must between 1 and 25");
					}
				} else if (optionParts[0].equals("random-seed")) {
					try {
						int radix = 10;
						randomSeed = Integer.parseInt(optionParts[1], radix);
					} catch (NumberFormatException e) {
						throw new CommandLineException("Invalid random seed: " + optionParts[1]);
					}
				} else if (optionParts[0].equals("look-ahead")) {
					try {
						int radix = 10;
						lookAhead = Integer.parseInt(optionParts[1], radix);
					} catch (NumberFormatException e) {
						throw new CommandLineException("Invalid look-ahead: " + optionParts[1]);
					}
				} else if (optionParts[0].equals("random-test-size")) {
					try {
						int radix = 10;
						randomTestSize = Integer.parseInt(optionParts[1], radix);
					} catch (NumberFormatException e) {
						throw new CommandLineException("Invalid random test size: " + optionParts[1]);
					}					
				} else if (optionParts[0].equals("valid-words")) {
					validWords = optionParts[1];
					// check if file exists and is not a directory
					File f = new File(validWords);
					if (!f.isFile()) {
						throw new CommandLineException("valid words file does not exists: " + validWords);
					}
				} else if (optionParts[0].equals("words-to-score")) {
					wordsToScore = optionParts[1];
					// check if file exists and is not a directory
					File f = new File(wordsToScore);
					if (!f.isFile()) {
						throw new CommandLineException("words to score file does not exists: " + wordsToScore);
					}				
				} else {
					throw new CommandLineException("bad option: " + arg);				
				}
			} else { // optionParts.length != 2
				throw new CommandLineException("bad option: " + arg);				
			}
		}
		if (validWords == null) {
			throw new CommandLineException("Valid words file must be specified.");
		}
		
		if (wordsToScore != null) {
			return new FixedWordsRunConfiguration(validWords, maxWrongGuesses, lookAhead, wordsToScore);
		} else {
			return new RandomWordsRunConfiguration(validWords, maxWrongGuesses, lookAhead, randomSeed, randomTestSize);				
		}
	}
	
	/**
	 * Prints a message to standard output that explains the command-line interface.
	 */
	private static void printUsageMessage() {
		String NL = System.getProperty("line.separator");
		String usageMesage =
		"Usage: java -jar hangman.jar [--OPTION] ..." + NL +
		"Score a set of words for a hangman strategy." + NL +
		"Example 1: java -jar hangman.jar --valid-words=words.txt --random-seed=7 --random-test-size=100" + NL + 
		"Example 2: java -jar hangman.jar --valid-words=words.txt --max-wrong-guesses=5 --words-to-score=score.txt" + NL +
		NL +
		"Required Option:" + NL +
		"  --valid-words=FILE        obtain list of valid words from FILE" + NL +
		NL +
		"Not Required Options:" + NL +
		"  --max-wrong-guesses=NUM   set maximum number of wrong guesses to NUM. Defaults to 5." + NL +
		"  --look-ahead=NUM          set number of guesses to look ahead to NUM. Defaults to 1." + NL +
		"  --help                    display this help and exit" + NL +
		NL +
		"Option 1: For scoring a set of randomly selected words:" + NL +  
		"  --random-seed=NUM         set the random seed generator to NUM. Defaults to 0." + NL +
		"  --random-test-size=NUM    select NUM random words from valid words file for test." + NL +
		NL +
		"Option 2: For scoring a set of preselected words:" + NL +  
		"  --words-to-score=FILE     obtain list of words to score from FILE" + NL;
		
		System.out.println(usageMesage);
	}
}