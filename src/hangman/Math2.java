package hangman;

/**
 * This class provides functions for computing logarithms base 2.
 */
public class Math2 {

	/**
	 * Returns the logarithm base 2 of a double value. Special cases: <ul>
     * <li>If the argument is positive zero or negative zero or plain zero, then the result is negative infinity.
     * <li>If the argument is NaN or less than zero, then the result is NaN.
     * <li>If the argument is positive infinity, then the result is positive infinity.
     * </ul>
	 * @param x - a value
	 * @return the value log2(x), the logarithm base 2 of x.
	 */
	public static double log2(double x) {
		return Math.log(x)/Math.log(2.0);
	}

	/**
	 * Returns the logarithm base 2 of a double value. Special cases: <ul>
     * <li>If the argument is positive zero or negative zero or plain zero, then the result is negative infinity.
     * <li>If the argument is NaN or less than zero, then the result is NaN.
     * <li>If the argument is positive infinity, then the result is positive infinity.
     * </ul>
	 * @param x - a value
	 * @return the value log2(n), the logarithm base 2 of n.
	 */	
	public static double log2(int n) {
		return Math.log(n)/Math.log(2.0);
	}	

}
