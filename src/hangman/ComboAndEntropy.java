package hangman;

import java.util.HashSet;
import java.util.Set;

/**
 * This class provides a convenient pairing of a letter combination and
 * an entropy value. It is useful for functions returning the letter combination with
 * the largest entropy.
 */
public class ComboAndEntropy {
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ComboAndEntropy [combo=" + combo + ", entropy=" + entropy + "]";
	}

	public final Set<Character> combo;
	public final double entropy;
	
	public ComboAndEntropy(Set<Character> combo, double entropy) {
		this.combo = combo;
		this.entropy = entropy;
	}
	
	public static void main(String[] args) {
		Set<Character> combo = new HashSet<Character>();
		combo.add('A'); combo.add('B');
		ComboAndEntropy maxComboAndEntropy = new ComboAndEntropy(combo, 10.0);
		System.out.println(maxComboAndEntropy.toString());
	}

}
