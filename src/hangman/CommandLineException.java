package hangman;

/**
 * This exception represents an error in the configuration that was
 * incorrectly specified on the command-line. The message gives details
 * on the particular error.
 */
@SuppressWarnings("serial")
public class CommandLineException extends RuntimeException {
	public CommandLineException(String message) { 
		super(message); 
	}
}
