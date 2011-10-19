package combinations;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * This class provides functions for computing combinations in lexicographical
 * order. A combinations is represented by an array of indices into the list
 * of items to be selected. That list of items must be sorted for the combinations
 * to be generated in the proper order.<br>
 * The Combos class provides a more user friendly interface to the generating of
 * items. Typically, that is what you want to use.
<pre>
// This code show how to use combination generating functions 
// for a list of characters.
char[] uniqueChars = {'A', 'B', 'C', 'D', 'E'};		
System.out.println("indices combo");

int n = uniqueChars.length;		
int[] indices = initialCombo(2);

while (indices != null) {
	System.out.print(Arrays.toString(indices) + "  ");
	// you have to write your own application specific select function.
	Set<Character> cset = select(indices, uniqueChars);
	System.out.println(Arrays.toString(cset.toArray()));
	indices = nextCombo(indices, n);			
}
</pre>
 */
public class Combinations {
	
	/**
	 * This specifies the indices to the first combination.
	 * @param r the size of the array to build
	 * @return an array [1, 2, 3, ... ]
	 */
	public static int[] initialCombo(int r) {
		int[] indices = new int[r];
		for (int i = 0; i < r; i++) {
			indices[i] = i;
		}
		return indices;
	}
	
	/**
	 * This function generates the indices that specify a combination.
	 * The combinations are generated lexicographically.
	 * Algorithm L (Lexicographic combinations) on page 4. 
	 * of Donald Knuth's The Art of Computer Programming, Pre-fascicle 3A
	 * freely available at http://www-cs-faculty.stanford.edu/~knuth/fasc3a.ps.gz <br>
	 * I haven't bought the book but this information is also in
	 * Knuth's The Art of Computer Programming, Volume 4A: Combinatorial Algorithms, Part 1
	 * Section 7.2.1.3 Generating All Combinations. 
	 * @param indicesIn the current set of indices for a combination
	 * @param n the size of the set from which we're making combinations
	 * @return the next set of indices for a combination or null if there is none.
	 */
	public static int[] nextCombo(int[] indicesIn, int n) {
		int[] indices = (int[])indicesIn.clone();
		
		int r = indices.length;

		// find the rightmost index that is not at its final highest value
		int i = 0;
		for (i = r - 1; i >= 0; i--) {
		    if (indices[i] != (i + n - r)) {
		    	break;
		    }
		}
		if (i == -1) {
		    return null;
		}
		indices[i] += 1;

		// reset all the indices to the right of indices[i]
		// to their smallest possible value.
		for (int j = i+1; j < r; j += 1) {
			indices[j] = indices[j-1] + 1;
		}
						
		return indices;
	}	
	
	/**
	 * @param indices a list of indices that specify a combination
	 * @param chars the letters from which to generate samples 
	 * @return a combination of characters
	 */
	private static Set<Character> select(int[] indices, char[] chars) {
		Set<Character> cset = new HashSet<Character>();
		for (int i : indices) {
			cset.add(chars[i]);
		}
		return cset;
	}
	
	public static void main(String[] args) {
		// This code show how to use combination generating functions 
		// for a list of characters.
		char[] uniqueChars = {'A', 'B', 'C', 'D', 'E'};		
		System.out.println("indices combo");

		int n = uniqueChars.length;		
		int[] indices = initialCombo(2);
		
		while (indices != null) {
			System.out.print(Arrays.toString(indices) + "  ");
			Set<Character> cset = select(indices, uniqueChars);
			System.out.println(Arrays.toString(cset.toArray()));
			indices = nextCombo(indices, n);			
		}		
	}
}