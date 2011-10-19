package hangman;

/**
 * This is a simple class for measuring run times. <br>
 * Example usage: <code>
 * <pre>
 * {@code
 * long startTime = Timer.startTime();
 * Thread.sleep(2000); // simulated work	
 * System.out.println("Simulated Work " + elapsedTime(startTime));
 * 
 * Simulated Work time (sec) = 2.001
 * }
 * </pre>
 */
public class Timer {
	/**
	 * @return the start time
	 */
	public static long startTime() {
		return System.nanoTime();
	}

	/**
	 * Elapsed time is the time since the start time.
	 * @param startTime the time when the timer started
	 * @return a formatted string representation in seconds of the elapsed time
	 */
	public static String elapsedTime(long startTime) {
		long endTime = System.nanoTime();		
	    long elapsedTime = endTime - startTime;
	    double elapsedTimeInSeconds = (double)elapsedTime / 1000000000.0;
	    return String.format("time (sec) = %3.3f", elapsedTimeInSeconds) ;	    	   
	}
}